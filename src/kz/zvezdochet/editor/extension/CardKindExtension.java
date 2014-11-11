package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.CardKind;
import kz.zvezdochet.analytics.service.CardKindService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника видов космограммы
 * @author Nataly Didenko
 */
public class CardKindExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Виды космограммы";
	}
	
	@Override
	public IModelService getService() {
		return new CardKindService();
	}

	@Override
	public Model create() {
		return new CardKind();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/necklace.png";
	}
}
