package kz.zvezdochet.editor.part;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.extension.IExtendableView;
import kz.zvezdochet.core.ui.extension.ModelExtension;
import kz.zvezdochet.core.ui.view.ModelPart;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.Activator;
import kz.zvezdochet.editor.extension.EditorExtension;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * Редактор справочника, отображающий композиты расширений
 * @author Nataly Didenko
 */
public class EditorPart extends ModelPart implements IExtendableView {
	/**
	 * Контейнер композитов
	 */
	private Composite container;
	private ScrolledComposite scrolledComposite;
	/**
	 * Расширения справочника
	 */
	private List<ModelExtension> extensions;

	@PostConstruct @Override
	public View create(Composite parent) {
		extPointId = Activator.PLUGIN_ID + ".editorPage";
		scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setMinSize(parent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(scrolledComposite);
		container = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(container);

		super.create(parent);

//		stateListener = new ExtensionStateListener(this);
//		notifyChange();
		return null;
	}
	
// 	@Override
//	protected void setListeners() {
//		stateChangedListener = new StateChangedListener();
//		txtName.addModifyListener(stateChangedListener);
//		txtCode.addModifyListener(stateChangedListener);
//		txtDescription.addModifyListener(stateChangedListener);
//		StateChangedListener listener = new StateChangedListener();
//		if (!txText.isDisposed()) txText.addModifyListener(listener);
//		if (!txMale.isDisposed()) txMale.addModifyListener(listener);
//		if (!txFemale.isDisposed()) txFemale.addModifyListener(listener);
//	}
	
	@Override
	public boolean check(int mode) throws Exception {
		if (null == extensions) return true;
		for (ModelExtension extension : extensions)
			if (!extension.check())
				return false;
		return true;
	}

	@Override
	public void syncModel(int mode) throws Exception {
		if (extensions != null)
			for (ModelExtension extension : extensions)
				extension.syncModel(mode);
	}
	
	@Override
	protected void syncView() {		
		reset();
		if (null == model) return;
		if (extensions != null)
			for (ModelExtension extension : extensions)
				extension.reset();
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
		if (model instanceof Dictionary)
			part.setLabel(((Dictionary)model).getName());
		if (extensions != null)
			for (ModelExtension extension : extensions)
				if (extension instanceof EditorExtension)
					part.setIconURI(extension.getIconURI());
	}

	@Override
	public void initExtensions() {
		extensions = new ArrayList<ModelExtension>();
		List<ModelExtension> allext = getExtensions();
		if (null == allext) return;
		for (ModelExtension extension : allext) {
			if (extension instanceof EditorExtension) {
				if (extension.canHandle(dictionary))
					extensions.add(extension);
			} else if (extension.canHandle(model))
				extensions.add(extension);
		}
		for (ModelExtension extension : extensions) {
			extension.setModel(model);
			extension.initExtensionView(this);
//			extension.initStateListener(stateListener);
			extension.initComposites(container);
			extension.initView();
		}
		refreshView();
		decorate();
	}

	@Override
	public void close() {
		if (null != extensions)
			for (ModelExtension extension : extensions)
				extension.close();
		super.close();
	}

	protected void refreshView() {
		scrolledComposite.setMinSize(container.computeSize(SWT.MIN, SWT.DEFAULT));
		container.layout(true, true);
	}

	/**
	 * Код загружаемого справочника
	 */
	private String dictionary;

	/**
	 * Инициализация кода справочника и расширений
	 * @param code имя таблицы БД
	 */
	public void setDictionary(String code) {
		this.dictionary = code;
	}
}
