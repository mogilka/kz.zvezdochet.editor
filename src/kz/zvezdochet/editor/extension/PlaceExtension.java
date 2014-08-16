package kz.zvezdochet.editor.extension;

import java.util.List;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.util.CalcUtil;
import kz.zvezdochet.editor.ui.PlaceComposite;
import kz.zvezdochet.service.PlaceService;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Расширение справочника АТЕ
 * @author Nataly Didenko
 */
public class PlaceExtension extends EditorExtensionProvider {

	@Override
	public String getExtensionName() {
		return "Административно-территориальные единицы";
	}
	
	@Override
	public IModelService getExtensionService() {
		return new PlaceService();
	}

	@Override
	public ModelComposite initExtensionComposite() {
		return new PlaceComposite();
	}

	@Override
	public void setModelList(Table table) throws DataAccessException {
		table.removeAll();
		List<Model> list = getModelList(); //TODO сделать через setdata
		if (list != null) {
			for (Model model : list)
				if (model instanceof Place) {
					Place place = (Place)model;
					TableItem tableItem = new TableItem(table, SWT.LEFT);
					tableItem.setText(new String[] {
							String.valueOf(place.getId()),
							place.getName(),
							CalcUtil.formatNumber("###.##", place.getLatitude()),
							CalcUtil.formatNumber("###.##", place.getLongitude())});
				}
			for (int i = 0; i < table.getColumnCount(); i++)
				table.getColumn(i).pack();
		}
		table.update();
	}

	@Override
	protected String[] getTableColumns() {
		return new String[] {"ID", "Наименование", "Широта", "Долгота"};
	}
}
