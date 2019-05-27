package kz.zvezdochet.editor.ui;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.Messages;
import kz.zvezdochet.core.ui.view.View;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Композит редактора справочника для моделей,
 * имеющих наименование, код и описание
 * @author Natalie Didenko
 */
public class DictionaryComposite extends EditorComposite {
	protected Text txtName;
	protected Text txtCode;
	protected Text txtDescription;
	protected Label lbName;
	protected StateChangedListener stateChangedListener = null;
	private Section secDescription;
	private Composite cmpText;

	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		FormToolkit toolkit = new FormToolkit(group.getDisplay());
		secDescription = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secDescription.setText("Основные параметры");
		secDescription.setBackgroundMode(SWT.INHERIT_NONE);
		secDescription.setBackground(parent.getBackground());
		
		cmpText = new Composite(secDescription, SWT.NONE);
		lbName = new Label(cmpText, SWT.NONE);
		lbName.setText(Messages.getString("ReferenceView.Name")); //$NON-NLS-1$
		txtName = new Text(cmpText, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		new RequiredDecoration(lbName, SWT.TOP | SWT.RIGHT);
		
		Label lb = new Label(cmpText, SWT.NONE);
		lb.setText(Messages.getString("ReferenceView.Code")); //$NON-NLS-1$
		txtCode = new Text(cmpText, SWT.BORDER);

		lb = new Label(cmpText, SWT.NONE);
		lb.setText(Messages.getString("ReferenceView.Description")); //$NON-NLS-1$
		txtDescription = new Text(cmpText, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secDescription.setClient(cmpText);

		GridLayoutFactory.swtDefaults().applyTo(parent);
		init(group);
		return this;
	}

	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridLayoutFactory.swtDefaults().applyTo(cmpText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpText);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			grab(true, true).applyTo(secDescription);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).
			hint(SWT.DEFAULT, 48).grab(true, false).applyTo(txtName);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).
			grab(true, false).applyTo(txtCode);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).
			hint(SWT.DEFAULT, 48).grab(true, false).applyTo(txtDescription);
	}

//	/**
//	 * Обработчик изменения состояния композита
//	 */
//	protected IEditorStateListener infoStateListener = null;

	@Override
	public void notifyChange() {
		super.notifyChange();
		if (infoStateListener != null)
			infoStateListener.notifyStateChanged();
	}

	@Override
	public boolean check(int mode) throws Exception {
		String msgBody = ""; //$NON-NLS-1$
		if (txtName.getText().equals(""))  //$NON-NLS-1$
			msgBody += lbName.getText() + '\n';
		if (!msgBody.equals("")) { //$NON-NLS-1$
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else return true;
	}

	@Override
	protected void syncView() {		
		reset();
		if (null == model) return;
		txtName.setText(((Dictionary)model).getName());
		txtCode.setText(((Dictionary)model).getCode());
		txtDescription.setText(((Dictionary)model).getDescription());
	}

	@Override
	public void syncModel(int mode) throws Exception {
		if (null == model) return;
		((Dictionary)model).setName(txtName.getText());
		((Dictionary)model).setCode(txtCode.getText());	
		((Dictionary)model).setDescription(txtDescription.getText());	
	}

	@Override
	public void reset() {
		txtName.setText(""); //$NON-NLS-1$
		txtCode.setText(""); //$NON-NLS-1$
		txtDescription.setText(""); //$NON-NLS-1$
	}

//	public class StateChangedListener implements ModifyListener, ISelectionChangedListener {
//		public void modifyText(ModifyEvent e) {
//			notifyChange();
//			deactivateUnaccessable();
//		}
//		public void selectionChanged(SelectionChangedEvent event) {
//			notifyChange();
//			deactivateUnaccessable();
//		}
//	}
}
