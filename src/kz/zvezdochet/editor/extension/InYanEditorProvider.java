package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.InYanService;

/**
 * Расширитель справочника Инь-Ян
 * @author Nataly Didenko
 */
public class InYanEditorProvider extends DiagramEditorProvider {

	@Override
	public String getName() {
		return "Инь-Ян";
	}
	
	@Override
	public Object getExtensionService() {
		return new InYanService();
	}
}
