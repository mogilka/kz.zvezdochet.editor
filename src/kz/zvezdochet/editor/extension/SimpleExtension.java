package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;

import org.eclipse.jface.viewers.IBaseLabelProvider;

/**
 * Прототип расширения простого справочника
 * @author Nataly Didenko
 */
public abstract class SimpleExtension extends EditorExtension {

	@Override
	public String[] getTableColumns() {
		return new String[] {"Наименование"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Dictionary model = (Dictionary)element;
				switch (columnIndex) {
					case 0: return model.getName();
				}
				return null;
			}
		};
	}
}
