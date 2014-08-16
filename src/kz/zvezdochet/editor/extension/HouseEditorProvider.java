package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.HouseComposite;
import kz.zvezdochet.service.HouseService;

/**
 * Расширитель справочника домов
 * @author Nataly Didenko
 */
public class HouseEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Астрологические дома";
	}
	
	@Override
	public Object getExtensionService() {
		return new HouseService();
	}

	@Override
	public ModelComposite initExtensionComposite() {
		return new HouseComposite();
	}
}
