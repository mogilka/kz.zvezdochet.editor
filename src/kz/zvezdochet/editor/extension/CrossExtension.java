package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Cross;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.CrossService;

/**
 * Расширение справочника крестов
 * @author Nataly Didenko
 */
public class CrossExtension extends DictionaryExtension {

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

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/cross.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("crosses");
	}
}
