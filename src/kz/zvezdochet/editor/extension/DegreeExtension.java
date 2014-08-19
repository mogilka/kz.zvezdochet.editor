package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Degree;
import kz.zvezdochet.analytics.service.DegreeService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;

/**
 * Расширение справочника Зодиакальных градусов
 * @author Nataly Didenko
 */
public class DegreeExtension extends SimpleExtension {

	@Override
	public String getName() {
		return "Значения градусов";
	}
	
	@Override
	public IModelService getService() {
		return new DegreeService();
	}

	@Override
	public Model create() {
		return new Degree();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/degree.png";
	}
}
