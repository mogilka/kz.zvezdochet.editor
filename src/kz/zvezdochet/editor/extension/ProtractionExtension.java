package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Protraction;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.ProtractionService;

/**
 * Расширение справочника начертаний аспектов
 * @author Nataly Didenko
 */
public class ProtractionExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Начертания аспектов";
	}
	
	@Override
	public IModelService getService() {
		return new ProtractionService();
	}

	@Override
	public Model create() {
		return new Protraction();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/protraction.png";
	}
}
