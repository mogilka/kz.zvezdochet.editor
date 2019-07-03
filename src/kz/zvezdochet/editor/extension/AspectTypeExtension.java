package kz.zvezdochet.editor.extension;

import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.AspectTypeComposite;
import kz.zvezdochet.service.AspectTypeService;

/**
 * Расширение справочника видов аспектов
 * @author Natalie Didenko
 */
public class AspectTypeExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Виды аспектов";
	}
	
	@Override
	public IModelService getService() {
		return new AspectTypeService();
	}

	@Override
	public View initComposite(Composite parent) {
		return new AspectTypeComposite().create(parent);
	}

	@Override
	public Model create() {
		return new AspectType();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/aspectype.png";
	}

	@Override
	public boolean canHandle(Object object) {
		return object.equals("aspecttypes");
	}
}
