package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.CrossService;

/**
 * Расширитель справочника крестов
 * @author Nataly Didenko
 */
public class CrossEditorProvider extends DiagramEditorProvider {

	@Override
	public String getName() {
		return "Кресты";
	}
	
	@Override
	public Object getExtensionService() {
		return new CrossService();
	}
}
