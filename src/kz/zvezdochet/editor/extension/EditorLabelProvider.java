package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.PlanetSignTextDictionary;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Обработчик отображения справочника в таблице
 * @author Nataly Didenko
 *
 */
public class EditorLabelProvider extends LabelProvider implements ITableLabelProvider {
	private final int COLUMN_PLANET = 0;
	private final int COLUMN_SIGN = 1;		
	private final int COLUMN_DESCR = 2;
	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof PlanetSignTextDictionary) {
			PlanetSignTextDictionary dict = (PlanetSignTextDictionary)element;
			switch (columnIndex) {
			case COLUMN_PLANET: return dict.getPlanet().getName();
			case COLUMN_SIGN: return dict.getSign().getName();		
			case COLUMN_DESCR: return dict.getCategory().getName();					
			}
		}
		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}
