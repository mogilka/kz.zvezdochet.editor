package kz.zvezdochet.editor.ui;

import kz.zvezdochet.analytics.bean.PlanetSignTextDictionary;
import kz.zvezdochet.analytics.service.CategoryService;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.service.PlanetService;
import kz.zvezdochet.service.SignService;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * Композит, используемый для следующих расширений справочников:<br>
 * Планеты в знаках Зодиака<br>
 * Планеты в астрологических домах<br>
 * Аспекты планет<br>
 * Дирекции планет<br>
 * Синастрические аспекты<br>
 * Планеты в синастрических домах
 * @author Nataly 
 */
public class PlanetSignComposite extends DictionaryComposite {
	protected ComboViewer cvType;
	protected Combo cmbType;
	protected ComboViewer cvObject1;
	protected Combo cmbObject1;
	protected ComboViewer cvObject2;
	protected Combo cmbObject2;
	protected Label lbType;
	protected Label lbObject1;
	protected Label lbObject2;
	
	@Override
	public Composite create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("");
		
		lbType = new Label(group, SWT.NONE);
		lbType.setText("Тип");
		cvType = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbType = cvType.getCombo();
		new RequiredDecoration(lbType, SWT.TOP | SWT.RIGHT);

		lbObject1 = new Label(group, SWT.NONE);
		lbObject1.setText("Объект1");
		cvObject1 = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbObject1 = cvObject1.getCombo();
		new RequiredDecoration(lbObject1, SWT.TOP | SWT.RIGHT);
		
		lbObject2 = new Label(group, SWT.NONE);
		lbObject2.setText("Объект2");
		cvObject2 = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbObject2 = cvObject2.getCombo();
		new RequiredDecoration(lbObject2, SWT.TOP | SWT.RIGHT);

		decorate();
		init();
		try {
			initControls();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		setListeners();
		syncView();
		return group;
	}
	
	@Override
	protected void initControls() throws DataAccessException {
		cvType.setContentProvider(new ArrayContentProvider());
		cvType.setLabelProvider(new DictionaryLabelProvider());
		cvType.setInput(new CategoryService().getList());

		cvObject1.setContentProvider(new ArrayContentProvider());
		cvObject1.setLabelProvider(new DictionaryLabelProvider());
		cvObject1.setInput(new PlanetService().getList());

		cvObject2.setContentProvider(new ArrayContentProvider());
		cvObject2.setLabelProvider(new DictionaryLabelProvider());
		cvObject2.setInput(new SignService().getList());
	}
	
	@Override
	protected void init() {
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(group);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);
		
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbType);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbObject1);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbObject2);
	}
	
	@Override
	protected void setListeners() {
		StateChangedListener listener = new StateChangedListener();
		cvType.addSelectionChangedListener(listener);
		cvObject1.addSelectionChangedListener(listener);
		cvObject2.addSelectionChangedListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		setCodeEdit(true);
		if (model != null) {
			PlanetSignTextDictionary dict = (PlanetSignTextDictionary)model;
			if (dict.getCategory() != null)
				cmbType.setText(dict.getCategory().getName());
			if (dict.getPlanet() != null)
				cmbObject1.setText(dict.getPlanet().getName());
			if (dict.getSign() != null)
				cmbObject2.setText(dict.getSign().getName());
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void reset() {
		setCodeEdit(true);
		cvType.setSelection(null);
		cvObject1.setSelection(null);
		cvObject2.setSelection(null);
		setCodeEdit(false);
	}
	
	@Override
	public void syncModel() {
		if (model == null) return;
		PlanetSignTextDictionary dict = (PlanetSignTextDictionary)model;
		IStructuredSelection selection = (IStructuredSelection)cvType.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setCategory((Category)selection.getFirstElement());
		selection = (IStructuredSelection)cvObject1.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setPlanet((Planet)selection.getFirstElement());
		selection = (IStructuredSelection)cvObject2.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setSign((Sign)selection.getFirstElement());
	}

	@Override
	public boolean check() {
		String msgBody = "";  //$NON-NLS-1$
		if (cvType.getSelection().isEmpty())
			msgBody += lbType.getText() + '\n';
		if (cvObject1.getSelection().isEmpty())
			msgBody += lbObject1.getText() + '\n';
		if (cvObject2.getSelection().isEmpty())
			msgBody += lbObject2.getText() + '\n';
		if (msgBody.length() > 0) {
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else 
			return true;
	}
}
