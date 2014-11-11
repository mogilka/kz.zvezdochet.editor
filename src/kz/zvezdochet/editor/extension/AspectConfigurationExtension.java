package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.AspectConfiguration;
import kz.zvezdochet.analytics.service.AspectConfigurationService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника конфигураций аспектов
 * @author Nataly Didenko
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
}
