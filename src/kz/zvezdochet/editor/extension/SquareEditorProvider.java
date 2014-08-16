package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.SquareService;

/**
 * Расширитель справочника квадратов
 * @author Nataly Didenko
 */
public class SquareEditorProvider extends DiagramEditorProvider {

	@Override
	public String getName() {
		return "Квадраты";
	}
	
	@Override
	public Object getExtensionService() {
		return new SquareService();
	}
}
