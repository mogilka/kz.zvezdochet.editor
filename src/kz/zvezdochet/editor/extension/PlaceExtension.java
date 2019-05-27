package kz.zvezdochet.editor.extension;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.core.util.CalcUtil;
import kz.zvezdochet.editor.ui.PlaceComposite;
import kz.zvezdochet.service.PlaceService;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника Административно-территориальных единиц
 * @author Natalie Didenko
 */
public class PlaceExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Местонахождение";
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet/icons/globe.png";
	}

	@Override
	public IModelService getService() {
		return new PlaceService();
	}

	@Override
	public View initComposite(Composite parent) {
		return new PlaceComposite().create(parent);
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Place model = (Place)element;
				switch (columnIndex) {
					case 0: return model.getName();
					case 1: return CalcUtil.formatNumber("###.##", model.getLatitude());
					case 2: return CalcUtil.formatNumber("###.##", model.getLongitude());
				}
				return null;
			}
		};
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Наименование", "Широта", "Долгота"};
	}

	@Override
	public Model create() {
		return new Place();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
