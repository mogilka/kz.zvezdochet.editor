package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.CardKind;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.CardKindService;

/**
 * Расширение справочника видов космограммы
 * @author Natalie Didenko
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

	@Override
	public boolean canHandle(Object object) {
		return object.equals("cardkinds");
	}
}
