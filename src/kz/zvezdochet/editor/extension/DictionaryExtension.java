package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.extension.ModelExtension;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.DictionaryComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника
 * @author Nataly Didenko
 */
public class DictionaryExtension extends ModelExtension {

	@Override
	public View initComposite(Composite parent) {
		return new DictionaryComposite().create(parent);
	}

	@Override
	public boolean canHandle(Object object) {
		return object instanceof Dictionary;
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
		return new String[] {"Наименование"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Dictionary model = (Dictionary)element;
				switch (columnIndex) {
					case 0: return model.getName();
				}
				return null;
			}
		};
	}

	@Override
	public String getIconURI() {
		// TODO Auto-generated method stub
		return null;
	}
}
