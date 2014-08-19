package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.PositionType;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.service.PositionTypeService;

/**
 * Расширение справочника позиций планет
 * @author Nataly Didenko
 */
public class PositionTypeExtension extends SimpleExtension {

	@Override
	public String getName() {
		return "Виды позиций планет";
	}
	
	@Override
	public IModelService getService() {
		return new PositionTypeService();
	}

	@Override
	public Model create() {
		return new PositionType();
	}
}
