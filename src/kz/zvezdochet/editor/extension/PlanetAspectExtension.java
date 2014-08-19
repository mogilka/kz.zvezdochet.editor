package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.PlanetAspectTextDictionary;
import kz.zvezdochet.analytics.service.PlanetAspectService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.editor.ui.PlanetAspectComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Расширение справочника Аспекты планет
 * @author Nataly Didenko
 */
public class PlanetAspectExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Аспекты планет";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Тип аспекта", "Планета"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				PlanetAspectTextDictionary model = (PlanetAspectTextDictionary)element;
				switch (columnIndex) {
					case 0: return model.getPlanet1().getName();
					case 1: return model.getType().getName();
					case 2: return model.getPlanet2().getName();
				}
				return null;
			}
		};
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetAspectComposite();
	}

	@Override
	public IModelService getService() {
		return new PlanetAspectService();
	}

	@Override
	public Model create() {
		return new PlanetAspectTextDictionary();
	}
}
