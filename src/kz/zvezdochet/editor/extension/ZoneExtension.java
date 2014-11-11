package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Zone;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.ZoneService;

/**
 * Расширение справочника Зоны
 * @author Nataly Didenko
 */
public class ZoneExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Зоны";
	}
	
	@Override
	public IModelService getService() {
		return new ZoneService();
	}

	@Override
	public Model create() {
		return new Zone();
	}
	
	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/selection.ico";
	}
}
