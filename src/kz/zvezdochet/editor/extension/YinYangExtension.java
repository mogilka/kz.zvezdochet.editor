package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.YinYang;
import kz.zvezdochet.analytics.service.YinYangService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Инь-Ян
 * @author Nataly Didenko
 */
public class YinYangExtension extends DiagramExtension {

	@Override
	public String getName() {
		return "Инь-Ян";
	}
	
	@Override
	public IModelService getService() {
		return new YinYangService();
	}

	@Override
	public Model create() {
		return new YinYang();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/yinyang.png";
	}
}
