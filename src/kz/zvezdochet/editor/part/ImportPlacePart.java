package kz.zvezdochet.editor.part;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.util.DateUtil;
import kz.zvezdochet.part.ImportPart;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Импорт городов
 * @author Natalie Didenko
 */
public class ImportPlacePart extends ImportPart {

	@Override
	protected String[] initTableColumns() {
		String[] columns = {
			"Тип",
			"№",
			"Наименование",
			"Описание",
			"Широта",
			"Долгота",
			"GMT",
			"Дата изменения" };
		return columns;
	}

	@Override
	protected IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Place model = (Place)element;
				if (model != null)
					switch (columnIndex) {
						case 1: return model.getId().toString();
						case 2: return model.getName();
						case 3: return model.getDescription();
						case 4: return String.valueOf(model.getLatitude());
						case 5: return String.valueOf(model.getLongitude());
						case 6: return String.valueOf(model.getGreenwich());
						case 7: return DateUtil.formatDateTime(model.getDate());
					}
				return null;
			}
			
			@Override
			public Image getColumnImage(Object element, int columnIndex) {
				Place model = (Place)element;
				switch (columnIndex) {
					case 0: String file = "map-marker.png";
						if (model.getType().equals("country"))
							file = "globe.png";
						else if (model.getType().equals("region"))
							file = "region.png";
						return AbstractUIPlugin.imageDescriptorFromPlugin("kz.zvezdochet", "icons/" + file).createImage();
				}
				return null;
			}
		};
	}
}
