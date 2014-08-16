package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.ElementService;

/**
 * Расширитель справочника стихий
 * @author Nataly Didenko
 */
public class ElementEditorProvider extends DiagramEditorProvider {

	@Override
	public String getName() {
		return "Стихии";
	}
	
	@Override
	public Object getExtensionService() {
		return new ElementService();
	}
}
