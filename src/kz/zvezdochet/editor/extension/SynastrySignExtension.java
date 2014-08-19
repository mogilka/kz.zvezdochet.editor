package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.SynastryTextDictionary;
import kz.zvezdochet.analytics.service.SynastrySignService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.editor.ui.SynastrySignComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Расширение справочника Планеты в знаках Зодиака
 * @author Nataly Didenko
 */
public class SynastrySignExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Планеты в синастрических знаках";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Знак Зодиака", "Знак Зодиака"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				SynastryTextDictionary model = (SynastryTextDictionary)element;
				switch (columnIndex) {
					case 0: return model.getPlanet().getName();
					case 1: return model.getSign1().getName();
					case 2: return model.getSign2().getName();
				}
				return null;
			}
		};
	}

	public ModelComposite initExtensionComposite() {
		return new SynastrySignComposite();
	}

	@Override
	public IModelService getService() {
		return new SynastrySignService();
	}

	@Override
	public Model create() {
		return new SynastryTextDictionary();
	}
}
