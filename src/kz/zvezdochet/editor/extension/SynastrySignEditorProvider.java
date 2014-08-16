package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.analytics.bean.SynastryTextDictionary;
import kz.zvezdochet.analytics.service.SynastrySignService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.SynastrySignComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Расширитель справочника Планеты в знаках Зодиака
 * @author Nataly Didenko
 */
public class SynastrySignEditorProvider extends EditorExtensionProvider {

	@Override
	public String getExtensionName() {
		return "Планеты в синастрических знаках";
	}
	
	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Планета", "Знак Зодиака", "Знак Зодиака"};
		initTableColumns(table, columns);
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList();
		if (list != null) {
			for (Model model : list)
				if (model instanceof SynastryTextDictionary) {
					SynastryTextDictionary dict = (SynastryTextDictionary)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(dict.getId()),
							dict.getPlanet().getName(), 
							dict.getSign1().getName(), 
							dict.getSign2().getName()});
				}
			table.getColumn(0).setWidth(0);
		}
		table.update();
	}

	public ModelComposite initExtensionComposite() {
		return new SynastrySignComposite();
	}

	@Override
	public Object getExtensionService() {
		return new SynastrySignService();
	}
}
