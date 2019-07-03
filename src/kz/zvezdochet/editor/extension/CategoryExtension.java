package kz.zvezdochet.editor.extension;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.analytics.bean.Category;
import kz.zvezdochet.analytics.service.CategoryService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.CategoryComposite;

/**
 * Расширение справочника категорий
 * @author Natalie Didenko
 */
public class CategoryExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Категории толкований планет";
	}
	
	@Override
	public String[] getTableColumns() {
		return new String[] { "Приоритет", "Наименование", "Планета", "Код"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Category model = (Category)element;
				switch (columnIndex) {
					case 0: return String.valueOf(model.getPriority());
					case 1: return model.getName();
					case 2: return model.getPlanet().getName();
					case 3: return model.getCode();
				}
				return null;
			}
		};
	}

	@Override
	public View initComposite(Composite parent) {
		return new CategoryComposite().create(parent);
	}

	@Override
	public IModelService getService() {
		return new CategoryService();
	}

	@Override
	public Model create() {
		return new Category();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/samples16.png";
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
