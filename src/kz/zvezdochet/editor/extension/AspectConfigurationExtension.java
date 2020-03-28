package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.AspectConfiguration;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.AspectConfigurationService;

/**
 * Расширение справочника конфигураций аспектов
 * @author Natalie Didenko
 */
public class AspectConfigurationExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Конфигурации аспектов";
	}
	
	@Override
	public IModelService getService() {
		return new AspectConfigurationService();
	}

	@Override
	public Model create() {
		return new AspectConfiguration();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/aspectconf.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("aspectconfigurations");
	}
}
