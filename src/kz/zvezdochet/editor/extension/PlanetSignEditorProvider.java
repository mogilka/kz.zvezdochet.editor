package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.analytics.bean.PlanetSignTextDictionary;
import kz.zvezdochet.analytics.service.PlanetSignService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.PlanetSignComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Расширитель справочника Планеты в знаках Зодиака
 * @author Nataly Didenko
 */
public class PlanetSignEditorProvider extends EditorExtensionProvider {

	@Override
	public String getExtensionName() {
		return "Планеты в знаках Зодиака";
	}
	
	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Планета", "Знак Зодиака", "Категория"};
		initTableColumns(table, columns);
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList();
		if (list != null) {
			for (Model model : list)
				if (model instanceof PlanetSignTextDictionary) {
					PlanetSignTextDictionary dict = (PlanetSignTextDictionary)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(dict.getId()),
							dict.getPlanet().getName(), 
							dict.getSign().getName(), 
							dict.getCategory().getName()});
				}
			table.getColumn(0).setWidth(0);
		}
		table.update();
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetSignComposite();
	}

	@Override
	public Object getExtensionService() {
		return new PlanetSignService();
	}
}
