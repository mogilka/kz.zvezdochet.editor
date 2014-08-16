package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.DegreeService;

/**
 * Расширитель справочника Зодиакальных градусов
 * @author Nataly Didenko
 */
public class DegreeEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Значения градусов";
	}
	
	@Override
	public Object getExtensionService() {
		return new DegreeService();
	}
}
