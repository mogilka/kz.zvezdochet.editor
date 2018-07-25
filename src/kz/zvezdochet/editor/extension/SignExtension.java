package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.SignComposite;
import kz.zvezdochet.service.SignService;

import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника знаков Зодиака
 * @author Nataly Didenko
 */
public class SignExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Знаки Зодиака";
	}
	
	@Override
	public IModelService getService() {
		return new SignService();
	}

	@Override
	public View initComposite(Composite parent) {
		return new SignComposite().create(parent);
	}

	@Override
	public Model create() {
		return new Sign();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/constellation.gif";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("signs");
	}
}
