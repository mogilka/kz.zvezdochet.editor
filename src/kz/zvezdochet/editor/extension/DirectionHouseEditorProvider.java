package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.DirectionHouseService;

/**
 * Расширитель справочника Дирекции планет по астрологическим домам
 * @author Nataly Didenko
 */
public class DirectionHouseEditorProvider extends PlanetHouseEditorProvider {

	@Override
	public String getExtensionName() {
		return "Дирекции планет по астрологическим домам";
	}
	
	@Override
	public Object getExtensionService() {
		return new DirectionHouseService();
	}
}
