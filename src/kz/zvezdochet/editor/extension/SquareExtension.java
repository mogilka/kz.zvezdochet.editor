package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Square;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.SquareService;

/**
 * Расширение справочника квадратов
 * @author Nataly Didenko
 */
public class SquareExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Квадраты";
	}
	
	@Override
	public IModelService getService() {
		return new SquareService();
	}

	@Override
	public Model create() {
		return new Square();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/square.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("squares");
	}
}
