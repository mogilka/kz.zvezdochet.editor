package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.SignComposite;
import kz.zvezdochet.service.SignService;

/**
 * Расширитель справочника знаков Зодиака
 * @author Nataly Didenko
 */
public class SignEditorProvider extends SimpleEditorProvider {

	@Override
	public String getName() {
		return "Знаки Зодиака";
	}
	
	@Override
	public Object getExtensionService() {
		return new SignService();
	}

	@Override
	public ModelComposite initExtensionComposite() {
		return new SignComposite();
	}
}
