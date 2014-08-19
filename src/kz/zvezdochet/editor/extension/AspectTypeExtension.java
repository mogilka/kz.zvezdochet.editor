package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.AspectTypeComposite;
import kz.zvezdochet.service.AspectTypeService;

/**
 * Расширение справочника видов аспектов
 * @author Nataly Didenko
 */
public class AspectTypeExtension extends SimpleExtension {

	@Override
	public String getName() {
		return "Виды аспектов";
	}
	
	@Override
	public IModelService getService() {
		return new AspectTypeService();
	}

	public ModelComposite initExtensionComposite() {
		return new AspectTypeComposite();
	}

	@Override
	public Model create() {
		return new AspectType();
	}
}
