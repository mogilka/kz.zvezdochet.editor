package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Cross;
import kz.zvezdochet.analytics.service.CrossService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника крестов
 * @author Nataly Didenko
 */
public class CrossExtension extends DiagramExtension {

	@Override
	public String getName() {
		return "Кресты";
	}
	
	@Override
	public IModelService getService() {
		return new CrossService();
	}

	@Override
	public Model create() {
		return new Cross();
	}
}
