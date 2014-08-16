package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.AspectComposite;
import kz.zvezdochet.service.AspectService;

/**
 * Расширитель справочника аспектов
 * @author Nataly Didenko
 */
public class AspectEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Аспекты";
	}
	
	@Override
	public Object getExtensionService() {
		return new AspectService();
	}

	public ModelComposite initExtensionComposite() {
		return new AspectComposite();
	}
}
