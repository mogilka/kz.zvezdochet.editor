package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Square;
import kz.zvezdochet.analytics.service.SquareService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника квадратов
 * @author Nataly Didenko
 */
public class SquareExtension extends DiagramExtension {

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
}
