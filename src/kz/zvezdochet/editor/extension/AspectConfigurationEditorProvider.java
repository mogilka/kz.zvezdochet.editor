package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.AspectConfigurationService;

/**
 * Расширитель справочника конфигураций аспектов
 * @author Nataly Didenko
 */
public class AspectConfigurationEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Конфигурации аспектов";
	}
	
	@Override
	public Object getExtensionService() {
		return new AspectConfigurationService();
	}
}
