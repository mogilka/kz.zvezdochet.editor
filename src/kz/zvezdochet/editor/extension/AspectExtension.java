package kz.zvezdochet.editor.extension;

import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.bean.Aspect;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.AspectComposite;
import kz.zvezdochet.service.AspectService;

/**
 * Расширение справочника аспектов
 * @author Natalie Didenko
 */
public class AspectExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Аспекты";
	}
	
	@Override
	public IModelService getService() {
		return new AspectService();
	}

	@Override
	public View initComposite(Composite parent) {
		return new AspectComposite().create(parent);
	}

	@Override
	public Model create() {
		return new Aspect();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/aspect.gif";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("aspects");
	}
}
