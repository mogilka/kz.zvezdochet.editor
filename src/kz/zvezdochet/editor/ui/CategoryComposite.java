package kz.zvezdochet.editor.ui;

import kz.zvezdochet.analytics.bean.Category;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.listener.DigitInputListener;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.service.PlanetService;

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
import org.eclipse.swt.widgets.Text;

/**
 * Композит категории
 * @author Nataly 
 */
public class CategoryComposite extends DictionaryComposite {
	private Text txPriority;
	private Label lbPriority;
	protected ComboViewer cvObject;
	protected Combo cmbObject;
	protected Label lbObject;
	
	@Override
	public Composite create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("Категория");
		lbPriority = new Label(group, SWT.NONE);
		lbPriority.setText("Приоритет");
		txPriority = new Text(group, SWT.BORDER);
		new RequiredDecoration(lbPriority, SWT.TOP | SWT.RIGHT);
		
		lbObject = new Label(group, SWT.NONE);
		lbObject.setText("Планета");
		cvObject = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbObject = cvObject.getCombo();
		new RequiredDecoration(lbObject, SWT.TOP | SWT.RIGHT);
		
		decorate();
		init(group);
		try {
			initControls();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		syncView();
		return group;
	}
	
	@Override
	protected void initControls() throws DataAccessException {
		cvObject.setContentProvider(new ArrayContentProvider());
		cvObject.setLabelProvider(new DictionaryLabelProvider());
		cvObject.setInput(new PlanetService().getList());
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txPriority);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbObject);

		StateChangedListener listener = new StateChangedListener();
		txPriority.addModifyListener(listener);
		txPriority.addListener(SWT.Verify, new DigitInputListener());
		cvObject.addSelectionChangedListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			Category dict = (Category)model;
			txPriority.setText(String.valueOf(dict.getPriority()));
			if (dict.getPlanet() != null)
				cmbObject.setText(dict.getPlanet().getName());
		} 
	}
	
	@Override
	public void reset() {
		txPriority.setText("");
		cvObject.setSelection(null);
	}
	
	@Override
	public void syncModel(int mode) {
		if (model == null) return;
		Category dict = (Category)model;
		dict.setPriority(Integer.parseInt(txPriority.getText()));
		IStructuredSelection selection = (IStructuredSelection)cvObject.getSelection();
		if (selection.getFirstElement() != null) {
			Planet planet = (Planet)selection.getFirstElement();
			dict.setPlanet(planet);
			dict.setObjectId(planet.getId());
		}
	}

	@Override
	public boolean check(int mode) {
		String msgBody = "";  //$NON-NLS-1$
		if (txPriority.getText().length() == 0)
			msgBody += lbPriority.getText() + '\n';
		if (cvObject.getSelection().isEmpty())
			msgBody += lbObject.getText() + '\n';
		if (msgBody.length() > 0) {
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else 
			return true;
	}
}