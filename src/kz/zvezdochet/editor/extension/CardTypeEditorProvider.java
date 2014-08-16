package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.CardTypeService;

/**
 * Расширитель справочника типов космограммы
 * @author Nataly Didenko
 */
public class CardTypeEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Типы космограммы";
	}
	
	@Override
	public Object getExtensionService() {
		return new CardTypeService();
	}
}
