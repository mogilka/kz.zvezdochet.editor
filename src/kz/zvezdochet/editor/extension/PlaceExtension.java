package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.util.CalcUtil;
import kz.zvezdochet.editor.ui.PlaceComposite;
import kz.zvezdochet.service.PlaceService;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Расширение справочника АТЕ
 * @author Nataly Didenko
 */
public class PlaceExtension extends EditorExtension {

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
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Place model = (Place)element;
				switch (columnIndex) {
					case 0: return model.getName();
					case 1: return CalcUtil.formatNumber("###.##", model.getLatitude());
					case 2: return CalcUtil.formatNumber("###.##", model.getLongitude());
				}
				return null;
			}
		};
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Наименование", "Широта", "Долгота"};
	}

	@Override
	public Model create() {
		return new Place();
	}
}
