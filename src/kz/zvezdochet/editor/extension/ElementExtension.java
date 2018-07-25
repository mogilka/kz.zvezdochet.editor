package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Element;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.ElementService;

/**
 * Расширение справочника стихий
 * @author Nataly Didenko
 */
public class ElementExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Стихии";
	}
	
	@Override
	public IModelService getService() {
		return new ElementService();
	}

	@Override
	public Model create() {
		return new Element();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/lightning.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("elements");
	}
}
