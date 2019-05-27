package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.ITextGender;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.extension.ModelExtension;
import kz.zvezdochet.core.ui.view.ListView;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.GenderTextComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника толкований
 * @author Natalie Didenko
 */
public class GenderTextExtension extends ModelExtension {

	@Override
	public View initComposite(Composite parent) {
		return new GenderTextComposite().create(parent);
	}

	@Override
	public boolean canHandle(Object object) {
		return object instanceof ITextGender;
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
	public String[] getTableColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIconURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initFilter(ListView listView, Composite parent) {}

	@Override
	public String getName() {
		return "Гендерные параметры";
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
