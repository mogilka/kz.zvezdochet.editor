package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.SynastryHouseService;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Планеты в синастрических домах
 * @author Nataly Didenko
 */
public class SynastryHouseExtension extends PlanetHouseExtension {

	@Override
	public String getName() {
		return "Планеты в синастрических домах";
	}
	
	@Override
	public IModelService getService() {
		return new SynastryHouseService();
	}
}
