package kz.zvezdochet.editor.part;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.util.DateUtil;
import kz.zvezdochet.part.ImportPart;
import kz.zvezdochet.service.PlaceService;

/**
 * Импорт городов
 * @author Natalie Didenko
 */
public class ImportPlacePart extends ImportPart {
	protected DateTime dtDate;
	protected DateTime dtTime;

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
						case 6: return String.valueOf(model.getZone());
						case 7: return DateUtil.formatDateTime(model.getDate());
					}
				return null;
			}
			
			@Override
			public Image getColumnImage(Object element, int columnIndex) {
//				Place model = (Place)element;
//				switch (columnIndex) {
//					case 0: String file = "map-marker.png";
//						if (model.getType().equals("country"))
//							file = "globe.png";
//						else if (model.getType().equals("region"))
//							file = "region.png";
//						return AbstractUIPlugin.imageDescriptorFromPlugin("kz.zvezdochet", "icons/" + file).createImage();
//				}
				return null;
			}
		};
	}

	@Override
	protected void initControls() throws DataAccessException {
		super.initControls();
		try {
			Date date = new PlaceService().findLastDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			dtDate.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initFilter(Composite parent) {
		grFilter = new Group(parent, SWT.NONE);
		grFilter.setText("Поиск");
		grFilter.setLayout(new GridLayout());
		dtDate = new DateTime(grFilter, SWT.DROP_DOWN);
		dtTime = new DateTime(grFilter, SWT.TIME);
	}

	/**
	 * Поиск выбранной даты
	 * @return дата последнего импорта
	 */
	@Override
	public long getObject() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dtDate.getDay());
		calendar.set(Calendar.MONTH, dtDate.getMonth());
		calendar.set(Calendar.YEAR, dtDate.getYear());
		calendar.set(Calendar.HOUR_OF_DAY, dtTime.getHours());
		calendar.set(Calendar.MINUTE, dtTime.getMinutes());
		calendar.set(Calendar.SECOND, dtTime.getSeconds());
		return calendar.getTime().getTime() / 1000;
	}
}
