package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Halfsphere;
import kz.zvezdochet.analytics.service.HalfsphereService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника полусфер
 * @author Nataly Didenko
 */
public class HalfsphereExtension extends DiagramExtension {

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
}
