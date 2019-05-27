package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.Aspect;
import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.listener.NumberInputListener;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.service.AspectTypeService;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Композит типа аспекта
 * @author Natalie Didenko
 */
public class AspectComposite extends EditorComposite {
	private ComboViewer cvType;
	private Label lbType;
	private Label lbValue;
	private Label lbOrbis;
	private Text txValue;
	private Text txOrbis;
	
	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("Аспект");
		
		lbType = new Label(group, SWT.NONE);
		lbType.setText("Тип");
		cvType = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		new RequiredDecoration(lbType, SWT.TOP | SWT.RIGHT);

		lbValue = new Label(group, SWT.NONE);
		lbValue.setText("Значение");
		txValue = new Text(group, SWT.BORDER);
		new RequiredDecoration(lbValue, SWT.TOP | SWT.RIGHT);

		lbOrbis = new Label(group, SWT.NONE);
		lbOrbis.setText("Орбис");
		txOrbis = new Text(group, SWT.BORDER);
		new RequiredDecoration(lbOrbis, SWT.TOP | SWT.RIGHT);

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
		cvType.setInput(new AspectTypeService().getList());
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cvType.getCombo());
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txValue);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txOrbis);

		StateChangedListener listener = new StateChangedListener();
		cvType.addSelectionChangedListener(listener);
		txValue.addModifyListener(listener);
		txValue.addListener(SWT.Verify, new NumberInputListener());
		txOrbis.addModifyListener(listener);
		txOrbis.addListener(SWT.Verify, new NumberInputListener());
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			Aspect dict = (Aspect)model;
			if (dict.getType() != null)
				cvType.getCombo().setText(dict.getType().getName());
			txValue.setText(String.valueOf(dict.getValue()));
			txOrbis.setText(String.valueOf(dict.getOrbis()));
		} 
	}
	
	@Override
	public void reset() {
		cvType.setSelection(null);
		txValue.setText("");
		txOrbis.setText("");
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		Aspect dict = (Aspect)model;
		IStructuredSelection selection = (IStructuredSelection)cvType.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setType((AspectType)selection.getFirstElement());
		dict.setValue(Double.parseDouble(txValue.getText()));
		dict.setOrbis(Double.parseDouble(txOrbis.getText()));
	}

	@Override
	public boolean check(int mode) {
		String msgBody = "";  //$NON-NLS-1$
		if (cvType.getSelection().isEmpty())
			msgBody += lbType.getText() + '\n';
		if (txValue.getText().length() == 0)
			msgBody += lbValue.getText() + '\n';
		if (txOrbis.getText().length() == 0)
			msgBody += lbOrbis.getText() + '\n';
		if (msgBody.length() > 0) {
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else 
			return true;
	}
}
