package kz.zvezdochet.editor.extension;

import kz.zvezdochet.service.ProtractionService;

/**
 * Расширитель справочника начертаний аспектов
 * @author Nataly Didenko
 */
public class ProtractionEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Начертания аспектов";
	}
	
	@Override
	public Object getExtensionService() {
		return new ProtractionService();
	}
}
