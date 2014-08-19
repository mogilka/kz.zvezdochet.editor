package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Aspect;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.AspectComposite;
import kz.zvezdochet.service.AspectService;

/**
 * Расширение справочника аспектов
 * @author Nataly Didenko
 */
public class AspectExtension extends SimpleExtension {

	@Override
	public String getName() {
		return "Аспекты";
	}
	
	@Override
	public IModelService getService() {
		return new AspectService();
	}

	public ModelComposite initExtensionComposite() {
		return new AspectComposite();
	}

	@Override
	public Model create() {
		return new Aspect();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/aspect.gif";
	}
}
