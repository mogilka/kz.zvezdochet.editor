package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.CardKindService;

/**
 * Расширитель справочника видов космограммы
 * @author Nataly Didenko
 */
public class CardKindEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Виды космограммы";
	}
	
	@Override
	public Object getExtensionService() {
		return new CardKindService();
	}
}
