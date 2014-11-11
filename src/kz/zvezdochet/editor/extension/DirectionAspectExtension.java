package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.DirectionAspectService;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Дирекции планет
 * @author Nataly Didenko
 */
public class DirectionAspectExtension extends PlanetAspectExtension {

	@Override
	public String getName() {
		return "Дирекции планет";
	}
	
	@Override
	public IModelService getService() {
		return new DirectionAspectService();
	}
}
