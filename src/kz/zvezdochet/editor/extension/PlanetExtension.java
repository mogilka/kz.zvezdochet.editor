package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.PlanetComposite;
import kz.zvezdochet.service.PlanetService;

/**
 * Расширение справочника Планет
 * @author Nataly Didenko
 */
public class PlanetExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Планеты";
	}
	
	@Override
	public IModelService getService() {
		return new PlanetService();
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetComposite();
	}

	@Override
	public Model create() {
		return new Planet();
	}
	
	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/planet.gif";
	}
}
