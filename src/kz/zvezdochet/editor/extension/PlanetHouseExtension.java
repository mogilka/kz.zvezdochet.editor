package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.PlanetHouseTextDictionary;
import kz.zvezdochet.analytics.service.PlanetHouseService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.editor.ui.PlanetHouseComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Расширение справочника Планеты в астрологических домах
 * @author Nataly Didenko
 */
public class PlanetHouseExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Планеты в астрологических домах";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Астрологический дом", "Сочетание"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				PlanetHouseTextDictionary model = (PlanetHouseTextDictionary)element;
				switch (columnIndex) {
					case 0: return model.getPlanet().getName();
					case 1: return model.getHouse().getName();
					case 2: return model.getAspectType().getName();
				}
				return null;
			}
		};
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetHouseComposite();
	}

	@Override
	public IModelService getService() {
		return new PlanetHouseService();
	}

	@Override
	public Model create() {
		return new PlanetHouseTextDictionary();
	}
}
