package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.YinYang;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.YinYangService;

/**
 * Расширение справочника Инь-Ян
 * @author Natalie Didenko
 */
public class YinYangExtension extends DictionaryExtension {

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

	@Override
	public boolean canHandle(Object object) {
		return object.equals("yinyang");
	}
}
