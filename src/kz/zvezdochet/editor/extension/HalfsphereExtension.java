package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Halfsphere;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.HalfsphereService;

/**
 * Расширение справочника полусфер
 * @author Natalie Didenko
 */
public class HalfsphereExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Полусферы";
	}
	
	@Override
	public IModelService getService() {
		return new HalfsphereService();
	}

	@Override
	public Model create() {
		return new Halfsphere();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/contrast.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("halfspheres");
	}
}
