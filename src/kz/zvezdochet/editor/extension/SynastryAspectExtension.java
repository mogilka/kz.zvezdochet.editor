package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.SynastryAspectService;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Синастрические аспекты планет
 * @author Natalie Didenko
 */
public class SynastryAspectExtension extends PlanetAspectExtension {

	@Override
	public String getName() {
		return "Синастрические аспекты планет";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Тип аспекта", "Планета"};
	}

	@Override
	public IModelService getService() {
		return new SynastryAspectService();
	}
}
