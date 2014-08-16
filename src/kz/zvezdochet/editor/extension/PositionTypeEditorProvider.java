package kz.zvezdochet.editor.extension;

import kz.zvezdochet.service.PositionTypeService;

/**
 * Расширитель справочника позиций планет
 * @author Nataly Didenko
 */
public class PositionTypeEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Виды позиций планет";
	}
	
	@Override
	public Object getExtensionService() {
		return new PositionTypeService();
	}
}
