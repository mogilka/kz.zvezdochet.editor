package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.CardType;
import kz.zvezdochet.analytics.service.CardTypeService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника типов космограммы
 * @author Nataly Didenko
 */
public class CardTypeExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Типы космограммы";
	}
	
	@Override
	public IModelService getService() {
		return new CardTypeService();
	}

	@Override
	public Model create() {
		return new CardType();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/sun.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("cardtypes");
	}
}
