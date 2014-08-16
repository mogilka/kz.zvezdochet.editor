package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.AspectTypeComposite;
import kz.zvezdochet.service.AspectTypeService;

/**
 * Расширитель справочника видов аспектов
 * @author Nataly Didenko
 */
public class AspectTypeEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Виды аспектов";
	}
	
	@Override
	public Object getExtensionService() {
		return new AspectTypeService();
	}

	public ModelComposite initExtensionComposite() {
		return new AspectTypeComposite();
	}
}
