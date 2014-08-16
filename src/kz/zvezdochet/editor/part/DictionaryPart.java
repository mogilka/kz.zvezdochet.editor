package kz.zvezdochet.editor.part;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.Messages;
import kz.zvezdochet.core.ui.view.ModelPart;
import kz.zvezdochet.core.util.StringUtil;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Прототип редактора справочника.
 * Используется для простых моделей, имеющих наименование, код и описание
 * @author Nataly Didenko
 */
public abstract class DictionaryPart extends ModelPart {
	protected Text txtName;
	protected Text txtCode;
	protected Text txtDescription;
	protected Label lbName;
	protected Label lbCode;
	protected StateChangedListener stateChangedListener = null;
	private Composite container;
	private Section secDescription;
	private Composite cmpText;
	protected ScrolledComposite scrolledComposite;
	
	public Composite getContainer() {
		if (container == null)
			container = new Composite(scrolledComposite, SWT.NONE);
		return container;
	}
	
	protected void onActivated() {
		txtName.setFocus();
	}

	public void setContainer(Composite container) {
		this.container = container;
	}

	@Override
	public Composite create(Composite parent) {
		scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setContent(getContainer());
		
		FormToolkit toolkit = new FormToolkit(getContainer().getDisplay());
		secDescription = toolkit.createSection(getContainer(), Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secDescription.setText("Описание");
		secDescription.setBackgroundMode(SWT.INHERIT_NONE);
		secDescription.setBackground(parent.getBackground());
		
		cmpText = new Composite(secDescription, SWT.NONE);
		lbName = new Label(cmpText, SWT.NONE);
		lbName.setText(Messages.getString("ReferenceView.Name")); //$NON-NLS-1$
		txtName = new Text(cmpText, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
//		new RequiredDecoration(lbName, SWT.TOP | SWT.RIGHT);

		lbCode = new Label(cmpText, SWT.NONE);
		lbCode.setText(Messages.getString("ReferenceView.Code")); //$NON-NLS-1$
		txtCode = new Text(cmpText, SWT.BORDER);

		Label lb = new Label(cmpText, SWT.NONE);
		lb.setText(Messages.getString("ReferenceView.Description")); //$NON-NLS-1$
		txtDescription = new Text(cmpText, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secDescription.setClient(cmpText);

		scrolledComposite.setMinSize(parent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		GridLayoutFactory.swtDefaults().applyTo(parent);
		init(getContainer());
		return null;
	}
	
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, true).applyTo(scrolledComposite);
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
	
	protected void setListeners() {
		stateChangedListener = new StateChangedListener();
		txtName.addModifyListener(stateChangedListener);
		txtCode.addModifyListener(stateChangedListener);
		txtDescription.addModifyListener(stateChangedListener);
	}
	
	public class StateChangedListener implements ModifyListener, ISelectionChangedListener {
		public void modifyText(ModifyEvent e) {
			notifyChange();
			deactivateUnaccessable();
		}
		public void selectionChanged(SelectionChangedEvent event) {
			notifyChange();
			deactivateUnaccessable();
		}
	}
	
	@Override
	public boolean check(int mode) throws Exception {
		String msgBody = ""; //$NON-NLS-1$
//		if (txtName.getText().equals(""))  //$NON-NLS-1$
//			msgBody += lbName.getText() + '\n';
		if (!msgBody.equals("")) { //$NON-NLS-1$
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else return true;
	}
	
	@Override
	protected void syncView() {		
		reset();
		if (model == null) return;
		txtName.setText(StringUtil.safeString(((Dictionary)model).getName()));
		txtCode.setText(StringUtil.safeString(((Dictionary)model).getCode()));
		txtDescription.setText(StringUtil.safeString(((Dictionary)model).getDescription()));
	}

	@Override
	protected void syncModel(int mode) throws Exception {
		if (!check(mode)) return;
		if (model == null) 
			model = (Model)addModel();
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

	public Section getSectionDescription() {
		return secDescription;
	}

	protected void refreshView() {
		scrolledComposite.setMinSize(container.computeSize(SWT.MIN, SWT.DEFAULT));
		container.layout(true, true);
	}
}