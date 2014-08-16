package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.PlanetComposite;
import kz.zvezdochet.service.PlanetService;

/**
 * Расширитель справочника Планет
 * @author Nataly Didenko
 */
public class PlanetEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Планеты";
	}
	
	@Override
	public Object getExtensionService() {
		return new PlanetService();
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetComposite();
	}
}
