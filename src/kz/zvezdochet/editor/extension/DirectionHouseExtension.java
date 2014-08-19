package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.DirectionHouseService;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Дирекции планет по астрологическим домам
 * @author Nataly Didenko
 */
public class DirectionHouseExtension extends PlanetHouseExtension {

	@Override
	public String getName() {
		return "Дирекции планет по астрологическим домам";
	}
	
	@Override
	public IModelService getService() {
		return new DirectionHouseService();
	}
}
