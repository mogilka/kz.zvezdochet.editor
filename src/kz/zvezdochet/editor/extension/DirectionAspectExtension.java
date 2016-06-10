package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.direction.service.DirectionAspectService;

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
