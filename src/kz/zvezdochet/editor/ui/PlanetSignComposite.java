package kz.zvezdochet.editor.ui;

import kz.zvezdochet.analytics.bean.Category;
import kz.zvezdochet.analytics.bean.PlanetSignText;
import kz.zvezdochet.analytics.service.CategoryService;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.service.PlanetService;
import kz.zvezdochet.service.SignService;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
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
 * TODO не слишком ли много типов?
 * @author Nataly Didenko
 */
public class PlanetSignComposite extends EditorComposite {
	protected ComboViewer cvType;
	protected ComboViewer cvObject1;
	protected ComboViewer cvObject2;
	protected Label lbType;
	protected Label lbObject1;
	protected Label lbObject2;
	
	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("");
		
		lbType = new Label(group, SWT.NONE);
		lbType.setText("Тип");
		cvType = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		new RequiredDecoration(lbType, SWT.TOP | SWT.RIGHT);

		lbObject1 = new Label(group, SWT.NONE);
		lbObject1.setText("Объект1");
		cvObject1 = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		new RequiredDecoration(lbObject1, SWT.TOP | SWT.RIGHT);
		
		lbObject2 = new Label(group, SWT.NONE);
		lbObject2.setText("Объект2");
		cvObject2 = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		new RequiredDecoration(lbObject2, SWT.TOP | SWT.RIGHT);

		decorate();
		init(group);
		try {
			initControls();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		syncView();
		return this;
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
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cvType.getCombo());
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cvObject1.getCombo());
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cvObject2.getCombo());

		StateChangedListener listener = new StateChangedListener();
		cvType.addSelectionChangedListener(listener);
		cvObject1.addSelectionChangedListener(listener);
		cvObject2.addSelectionChangedListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			PlanetSignText dict = (PlanetSignText)model;
			if (dict.getCategory() != null)
				cvType.getCombo().setText(dict.getCategory().getName());
			if (dict.getPlanet() != null)
				cvObject1.getCombo().setText(dict.getPlanet().getName());
			if (dict.getSign() != null)
				cvObject2.getCombo().setText(dict.getSign().getName());
		} 
	}
	
	@Override
	public void reset() {
		cvType.setSelection(null);
		cvObject1.setSelection(null);
		cvObject2.setSelection(null);
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		PlanetSignText dict = (PlanetSignText)model;
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
	public boolean check(int mode) {
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
