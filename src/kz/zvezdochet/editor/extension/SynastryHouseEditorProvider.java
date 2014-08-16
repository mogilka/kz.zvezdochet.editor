package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.SynastryHouseService;

/**
 * Расширитель справочника Планеты в синастрических домах
 * @author Nataly Didenko
 */
public class SynastryHouseEditorProvider extends PlanetHouseEditorProvider {

	@Override
	public String getExtensionName() {
		return "Планеты в синастрических домах";
	}
	
	@Override
	public Object getExtensionService() {
		return new SynastryHouseService();
	}
}
