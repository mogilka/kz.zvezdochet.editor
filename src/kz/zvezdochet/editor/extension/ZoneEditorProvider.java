package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.ZoneService;

/**
 * Расширитель справочника Зоны
 * @author Nataly Didenko
 */
public class ZoneEditorProvider extends DiagramEditorProvider {

	@Override
	public String getName() {
		return "Зоны";
	}
	
	@Override
	public Object getExtensionService() {
		return new ZoneService();
	}
}
