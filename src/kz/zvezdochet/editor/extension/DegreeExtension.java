package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.Degree;
import kz.zvezdochet.analytics.service.DegreeService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Расширение справочника Зодиакальных градусов
 * @author Nataly Didenko
 */
public class DegreeExtension extends DictionaryExtension {

	@Override
	public String getName() {
		return "Значения градусов";
	}
	
	@Override
	public IModelService getService() {
		return new DegreeService();
	}

	@Override
	public Model create() {
		return new Degree();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.analytics/icons/degree.png";
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Degree model = (Degree)element;
				switch (columnIndex) {
					case 0: return model.getId().toString();
					case 1: return model.getCode();
				}
				return null;
			}
		};
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Градус", "Планета"};
	}
}
