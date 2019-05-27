package kz.zvezdochet.editor.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import kz.zvezdochet.analytics.bean.Degree;
import kz.zvezdochet.analytics.service.DegreeService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.view.ListView;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;

/**
 * Расширение справочника Зодиакальных градусов
 * @author Natalie Didenko
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
					case 1: return model.getCode() + " (" + model.getName() + ")";
					case 2: return model.getDescription();
					case 3: return model.isPositive() ? "+" : "";
					case 4: return model.isRoyal() ? "+" : "";
					case 5: return model.isDestructive() ? "+" : "";
				}
				return null;
			}
		};
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"ID", "Планета", "Описание", "Позитив", "Королевский", "Разрушительный"};
	}

	@Override
	public boolean canHandle(Object object) {
		return object.toString().equals("degrees");
	}

	@Override
	public void initFilter(ListView listView, Composite parent) {
//		Group group = new Group(parent, SWT.BORDER);
//		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//		group.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_RED));
		final Text txSearch = new Text(parent, SWT.BORDER);
		txSearch.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		txSearch.setFocus();
		txSearch.addListener(SWT.DefaultSelection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String text = txSearch.getText();
				if (text.trim().length() > 1)
					try {
						Model model = getService().find(Long.parseLong(text));
						if (model != null) {
							List<Model> list = new ArrayList<>();
							list.add(model);
							listView.setData(list);
						}
					} catch (DataAccessException e) {
						e.printStackTrace();
					} catch (NumberFormatException e) {
						DialogUtil.alertWarning("Введите целое число");
					}			
			}
		});
	}
}
