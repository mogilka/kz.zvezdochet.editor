package kz.zvezdochet.editor.part;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.extension.IExtendableView;
import kz.zvezdochet.core.ui.extension.ModelExtension;
import kz.zvezdochet.core.ui.view.ModelPart;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.Activator;
import kz.zvezdochet.editor.extension.EditorExtension;

/**
 * Редактор справочника, отображающий композиты расширений
 * @author Nataly Didenko
 */
public class EditorPart extends ModelPart implements IExtendableView {
	/**
	 * Контейнер композитов
	 */
	private Composite container;
	private ScrolledComposite scrollContainer;

	@PostConstruct @Override
	public View create(Composite parent) {
		extPointId = Activator.PLUGIN_ID + ".editorPage";
		scrollContainer = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.NONE);
		scrollContainer.setExpandVertical(true);
		scrollContainer.setExpandHorizontal(true);
		container = new Composite(scrollContainer, SWT.NONE);
		scrollContainer.setContent(container);

		super.create(parent);

//		stateListener = new ExtensionStateListener(this);
//		notifyChange();
		return null;
	}

	@Override
	protected void init(Composite composite) {
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);

		scrollContainer.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		GridDataFactory.fillDefaults().grab(true, false).applyTo(scrollContainer);

		GridLayoutFactory.swtDefaults().applyTo(container);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(container);
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
		super.initExtensions();
		if (null == extensions) return;

		List<ModelExtension> exts = new ArrayList<ModelExtension>();
		String dict = model.getService().getTableName();
		for (ModelExtension extension : extensions) {
			if (extension instanceof EditorExtension) {
				if (extension.canHandle(dict))
					exts.add(extension);
			} else if (extension.canHandle(model))
				exts.add(extension);
		}
		extensions = exts;
		for (ModelExtension extension : extensions) {
			extension.setModel(model);
			extension.setPart(this);
//			extension.initStateListener(stateListener);
			extension.initComposites(container);
		}
		refreshPart();
		decorate();
	}

	@Override
	public void close() {
		if (null != extensions)
			for (ModelExtension extension : extensions)
				extension.close();
		super.close();
	}

	protected void refreshPart() {
//		scrollContainer.setMinSize(container.computeSize(SWT.MIN, SWT.DEFAULT));
//		container.layout(true, true);
	}
}
