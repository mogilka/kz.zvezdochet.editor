package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.analytics.bean.PlanetAspectTextDictionary;
import kz.zvezdochet.analytics.service.PlanetAspectService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.PlanetAspectComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Расширитель справочника Аспекты планет
 * @author Nataly Didenko
 */
public class PlanetAspectEditorProvider extends EditorExtensionProvider {

	@Override
	public String getExtensionName() {
		return "Аспекты планет";
	}
	
	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Планета", "Тип аспекта", "Планета"};
		initTableColumns(table, columns);
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList();
		if (list != null) {
			for (Model model : list)
				if (model instanceof PlanetAspectTextDictionary) {
					PlanetAspectTextDictionary dict = (PlanetAspectTextDictionary)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(dict.getId()),
							dict.getPlanet1().getName(),
							dict.getType().getName(),
							dict.getPlanet2().getName()});
				}
			table.getColumn(0).setWidth(0);
		}
		table.update();
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetAspectComposite();
	}

	@Override
	public Object getExtensionService() {
		return new PlanetAspectService();
	}
}
