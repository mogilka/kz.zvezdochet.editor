package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Прототип расширителя простого справочника
 * @author Nataly Didenko
 */
public abstract class SimpleEditorProvider extends EditorExtensionProvider {

	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Наименование"};
		initTableColumns(table, columns);
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList();
		if (list != null) {
			for (Model model : list)
				if (model instanceof Dictionary) {
					Dictionary dict = (Dictionary)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(dict.getId()),
							dict.getName()});
				}
			table.getColumn(0).setWidth(0);
		}
		table.update();
	}
}
