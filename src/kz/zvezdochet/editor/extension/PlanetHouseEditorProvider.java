package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.analytics.bean.PlanetHouseTextDictionary;
import kz.zvezdochet.analytics.service.PlanetHouseService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.PlanetHouseComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Расширитель справочника Планеты в астрологических домах
 * @author Nataly Didenko
 */
public class PlanetHouseEditorProvider extends EditorExtension {

	@Override
	public String getExtensionName() {
		return "Планеты в астрологических домах";
	}
	
	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Планета", "Астрологический дом", "Сочетание"};
		initTableColumns(table, columns);
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList();
		if (list != null) {
			for (Model model : list)
				if (model instanceof PlanetHouseTextDictionary) {
					PlanetHouseTextDictionary dict = (PlanetHouseTextDictionary)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(dict.getId()),
							dict.getPlanet().getName(), 
							dict.getHouse().getName(), 
							dict.getAspectType().getName()});
				}
			table.getColumn(0).setWidth(0);
		}
		table.update();
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetHouseComposite();
	}

	@Override
	public Object getExtensionService() {
		return new PlanetHouseService();
	}
}
