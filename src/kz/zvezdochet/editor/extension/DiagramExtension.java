package kz.zvezdochet.editor.extension;

import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.core.bean.DiagramObject;
import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.DiagramComposite;

/**
 * Прототип расширения справочника диаграммных объектов
 * @author Natalie Didenko
 */
public class DiagramExtension extends DictionaryExtension { 

	@Override
	public View initComposite(Composite parent) {
		return new DiagramComposite().create(parent);
	}

	@Override
	public boolean canHandle(Object object) {
		return object instanceof DiagramObject;
	}

	@Override
	public Dictionary getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModelService getService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIconURI() {
		// TODO Auto-generated method stub
		return null;
	}
}
