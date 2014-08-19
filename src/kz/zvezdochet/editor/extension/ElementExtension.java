package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Element;
import kz.zvezdochet.analytics.service.ElementService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника стихий
 * @author Nataly Didenko
 */
public class ElementExtension extends DiagramExtension {

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
}
