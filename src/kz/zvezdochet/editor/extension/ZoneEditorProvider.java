package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Zone;
import kz.zvezdochet.analytics.service.ZoneService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Зоны
 * @author Nataly Didenko
 */
public class ZoneEditorProvider extends DiagramExtension {

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
}
