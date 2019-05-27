package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.House;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.HouseComposite;
import kz.zvezdochet.service.HouseService;

import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника домов
 * @author Natalie Didenko
 */
public class HouseExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Астрологические дома";
	}
	
	@Override
	public IModelService getService() {
		return new HouseService();
	}

	@Override
	public View initComposite(Composite parent) {
		return new HouseComposite().create(parent);
	}

	@Override
	public Model create() {
		return new House();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/home.gif";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("houses");
	}
}
