package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.PlanetSignTextDictionary;
import kz.zvezdochet.analytics.service.PlanetSignService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.editor.ui.PlanetSignComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Расширение справочника Планеты в знаках Зодиака
 * @author Nataly Didenko
 */
public class PlanetSignExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Планеты в знаках Зодиака";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Знак Зодиака", "Категория"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				PlanetSignTextDictionary model = (PlanetSignTextDictionary)element;
				switch (columnIndex) {
					case 0: return model.getPlanet().getName();
					case 1: return model.getSign().getName();
					case 2: return model.getCategory().getName();
				}
				return null;
			}
		};
	}

	public ModelComposite initExtensionComposite() {
		return new PlanetSignComposite();
	}

	@Override
	public IModelService getService() {
		return new PlanetSignService();
	}

	@Override
	public Model create() {
		return new PlanetSignTextDictionary();
	}
}
