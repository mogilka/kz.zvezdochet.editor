package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.analytics.bean.Category;
import kz.zvezdochet.analytics.service.CategoryService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.CategoryComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Расширитель справочника категорий
 * @author Nataly Didenko
 */
public class CategoryEditorProvider extends EditorExtensionProvider {

	@Override
	public String getExtensionName() {
		return "Категории толкований планет";
	}
	
	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Приоритет", "Наименование", "Планета", "Код"};
		initTableColumns(table, columns);
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList();
		if (list != null) {
			for (Model model : list)
				if (model instanceof Category) {
					Category dict = (Category)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(dict.getId()),
							String.valueOf(dict.getPriority()), 
							dict.getName(), 
							dict.getPlanet().getName(), 
							dict.getCode()});
				}
			table.getColumn(0).setWidth(0);
		}
		table.update();
	}

	public ModelComposite initExtensionComposite() {
		return new CategoryComposite();
	}

	@Override
	public Object getExtensionService() {
		return new CategoryService();
	}
}
