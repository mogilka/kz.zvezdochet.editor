package kz.zvezdochet.editor.part;

import java.util.ArrayList;

import kz.zvezdochet.analytics.bean.GenderText;
import kz.zvezdochet.analytics.bean.TextGenderDictionary;
import kz.zvezdochet.bean.TextDictionary;
import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.extension.ExtensionUtil;
import kz.zvezdochet.core.ui.extension.IExtendableView;
import kz.zvezdochet.core.ui.extension.IExtension;
import kz.zvezdochet.core.ui.extension.ModelExtensionProvider;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.editor.Activator;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Редактор справочника,
 * отображающий набор стандартных параметров,
 * а также композиты расширений
 * @author Nataly Didenko
 */
public class EditorPart extends DictionaryPart implements IExtendableView {
	public static final String ID = EditorPart.class.getCanonicalName();

	private Text txText;
	private Text txMale;
	private Text txFemale;
	private Section secText;
	private Section secMale;
	private Section secFemale;

	@Override
	public Composite create(Composite parent) {
		createTextSection(parent);
		createMaleSection(parent);
		createFemaleSection(parent);
		init(getContainer());

//		stateListener = new ExtensionStateListener(this);
		providers = new ArrayList<ModelExtensionProvider>();
		providers.addAll(ExtensionUtil.getExtensionProviders(EXT_POINT_ID));  
		notifyChange();
		return null;
	}

	private void createTextSection(Composite parent) {
		FormToolkit toolkit = new FormToolkit(getContainer().getDisplay());
		secText = toolkit.createSection(getContainer(), Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secText.setText("Толкование");
//		secText.setExpanded(true);
		secText.setBackgroundMode(SWT.INHERIT_NONE);
		secText.setBackground(parent.getBackground());
		
		txText = new Text(secText, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secText.setClient(txText);
	}

	private void createMaleSection(Composite parent) {
		FormToolkit toolkit = new FormToolkit(getContainer().getDisplay());
		secMale = toolkit.createSection(getContainer(), Section.TWISTIE | Section.TITLE_BAR);
		secMale.setText("Мужчина");
		secMale.setExpanded(false);
		secMale.setBackgroundMode(SWT.INHERIT_NONE);
		secMale.setBackground(parent.getBackground());
		txMale = new Text(secMale, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secMale.setClient(txMale);
	}

	private void createFemaleSection(Composite parent) {
		FormToolkit toolkit = new FormToolkit(getContainer().getDisplay());
		secFemale = toolkit.createSection(getContainer(), Section.TWISTIE | Section.TITLE_BAR);
		secFemale.setText("Женщина");
		secFemale.setExpanded(false);
		secFemale.setBackgroundMode(SWT.INHERIT_NONE);
		secFemale.setBackground(parent.getBackground());
		txFemale = new Text(secFemale, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secFemale.setClient(txFemale);
	}
	
	@Override
	protected void init(Composite composite) {
		if (secText == null) return;
		super.init(getContainer());
		if (!secText.isDisposed()) { 
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
				hint(SWT.DEFAULT, 200).grab(true, true).applyTo(secText);
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
				grab(true, true).applyTo(txText);
		}
		if (!secMale.isDisposed()) {
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
				hint(SWT.DEFAULT, 100).grab(true, true).applyTo(secMale);
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
				grab(true, true).applyTo(txMale);
		}
		if (!secFemale.isDisposed()) {
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
				hint(SWT.DEFAULT, 100).grab(true, true).applyTo(secFemale);
			GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
				grab(true, true).applyTo(txFemale);
		}
	}
	
	@Override
	public void decorate() {
		if (model == null) return;
		getSectionDescription().setExpanded(txtName.getText().length() > 0 |
				txtCode.getText().length() > 0 |
				txtDescription.getText().length() > 0);
		if (!(model instanceof TextDictionary)) 
			secText.dispose();
//		else if (secText == null || secText.isDisposed())
//			createTextSection();
//		if (!(element instanceof TextGenderReference)) {
//			secMale.dispose();
//			secFemale.dispose();
//		} else {
//			if (secMale == null || secMale.isDisposed()) 
//				createMaleSection();
//			if (secFemale == null || secFemale.isDisposed()) 
//				createFemaleSection();
//		}
		refreshView();
		//parent.layout(true, true);
	}
	
 	@Override
	protected void setListeners() {
		super.setListeners();
		StateChangedListener listener = new StateChangedListener();
		if (!txText.isDisposed()) txText.addModifyListener(listener);
		if (!txMale.isDisposed()) txMale.addModifyListener(listener);
		if (!txFemale.isDisposed()) txFemale.addModifyListener(listener);
	}
	
	@Override
	public boolean check(int mode) throws Exception {
		String msgBody = ""; //$NON-NLS-1$
		if ((!txText.isDisposed()) && txText.getText().length() == 0) 
			msgBody += secText.getText();
//		if ((!txMale.isDisposed()) && txMale.getText().length() == 0) 
//			msgBody += secMale.getText();
//		if ((!txFemale.isDisposed()) && txFemale.getText().length() == 0) 
//			msgBody += secFemale.getText();
		if (msgBody.length() > 0) { //$NON-NLS-1$
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		}
		if (providers == null) return true;
		for (ModelExtensionProvider provider : providers) {
			if (provider.canHandle(dictionary)) 
				if (!provider.check())
					return false;
		}
		return true && super.check(mode);
	}

	@Override
	protected void syncModel(int mode) throws Exception {
		if (!check(mode)) return;
		if (model instanceof TextGenderDictionary) {
			model = (model == null) ? new TextGenderDictionary() : model;
			TextGenderDictionary dict = (TextGenderDictionary)model;
			if (dict.getGenderText() == null)
				dict.setGenderText(new GenderText());
			if (!txMale.isDisposed()) dict.getGenderText().setMaletext(txMale.getText());
			if (!txFemale.isDisposed()) dict.getGenderText().setFemaletext(txFemale.getText());
		}
		if (model instanceof TextDictionary) {
			model = (model == null) ? new TextDictionary() : model;
			TextDictionary dict = (TextDictionary)model;
			if (!txText.isDisposed()) 
				dict.setText(txText.getText());
		}
		super.syncModel(mode);
		
		if (providers == null) return;
		for (ModelExtensionProvider provider : providers) {
			provider.initExtended((Dictionary)model);
			if (provider.canHandle(dictionary)) 
				model = provider.getExtended();
		}
	}
	
	@Override
	protected void syncView() {		
		decorate();
		reset();
		if (model == null) return;
		super.syncView();
		if (!(model instanceof TextDictionary)) return;
		TextDictionary dict = (TextDictionary)model;
		if (!txText.isDisposed()) {
			if (dict.getText() != null)
				txText.setText(dict.getText());
			secText.setExpanded(txText.getText().length() > 0);
		}
		if (!(model instanceof TextGenderDictionary)) return;
		TextGenderDictionary genderef = (TextGenderDictionary)model;
		if (genderef.getGenderText() != null) {
			if (!txMale.isDisposed()) { 
				if (genderef.getGenderText().getMaletext() != null)
					txMale.setText(genderef.getGenderText().getMaletext());
			}
			if (!txFemale.isDisposed()) { 
				if (genderef.getGenderText().getFemaletext() != null)
					txFemale.setText(genderef.getGenderText().getFemaletext());
			}
		}
		if (!secMale.isDisposed() && !txMale.isDisposed()) 
			secMale.setExpanded(txMale.getText().length() > 0);
		if (!secFemale.isDisposed() && !txFemale.isDisposed())	
			secFemale.setExpanded(txFemale.getText().length() > 0);
	}
	
	@Override
	public void reset() {
		super.reset();
		if (!txText.isDisposed()) txText.setText(""); //$NON-NLS-1$
		if (!txMale.isDisposed()) txMale.setText(""); //$NON-NLS-1$
		if (!txFemale.isDisposed()) txFemale.setText(""); //$NON-NLS-1$
	}
	
//	@Override
//	public Object createElement() {
//		return new Event();
//	}

	public void notifyStateChanged() {
		deactivateUnaccessable();
	}
	
	@Override
	public void setModel(Model model, boolean sync) {
		super.setModel(model, sync);
		initExtensions();
	}

	public static final String EXT_POINT_ID = Activator.PLUGIN_ID + ".editorPage"; //$NON-NLS-1$
	private java.util.List<ModelExtensionProvider> providers = null;
	
	@Override
	public java.util.List<ModelExtensionProvider> getExtensionProviders() {
		return providers;
	}

	@Override
	public void initExtensions() {
		if (providers == null) return;
		Dictionary dict = (Dictionary)model;
		for (IExtension extension : providers) {
			ModelExtensionProvider provider = (ModelExtensionProvider)extension;
			provider.initExtended(dict);
			if (provider.canHandle(dictionary)) {
				provider.initExtensionView(this);
//				provider.initStateListener(stateListener);
				provider.initComposites(getContainer());
				provider.initView();
//				setService((IBaseService)extProvider.getExtensionService());
			} else 
				provider.close();
		}
		refreshView();
		decorate();
	}

	@Override
	public void close() {
		for (ModelExtensionProvider provider : providers) 
			provider.close();
		super.close();
	}

	/**
	 * Код справочника, элемент которого загружен в редакторе
	 */
	private String dictionary;
	
	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dict) {
		this.dictionary = dict;
	}

	@Override
	public Model addModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
