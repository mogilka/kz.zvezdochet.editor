package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.HalfsphereService;

/**
 * Расширитель справочника полусфер
 * @author Nataly Didenko
 */
public class HalfsphereEditorProvider extends DiagramEditorProvider {

	@Override
	public String getName() {
		return "Полусферы";
	}
	
	@Override
	public Object getExtensionService() {
		return new HalfsphereService();
	}
}
