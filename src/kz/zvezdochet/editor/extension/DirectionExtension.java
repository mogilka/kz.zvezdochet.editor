package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.direction.bean.DirectionText;
import kz.zvezdochet.direction.service.DirectionService;

/**
 * Расширение справочника Дирекции планет по астрологическим домам
 * @author Nataly Didenko
 */
public class DirectionExtension extends PlanetHouseExtension {

	@Override
	public String getName() {
		return "Дирекции планет по астрологическим домам";
	}
	
	@Override
	public IModelService getService() {
		return new DirectionService();
	}

	@Override
	public Model create() {
		return new DirectionText();
	}
}
