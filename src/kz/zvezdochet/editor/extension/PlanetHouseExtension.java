package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.PlanetHouseText;
import kz.zvezdochet.analytics.service.PlanetHouseService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.PlanetHouseComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Расширение справочника Планеты в астрологических домах
 * @author Natalie Didenko
 */
public class PlanetHouseExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Планеты в домах";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Астрологический дом", "Сочетание"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				PlanetHouseText model = (PlanetHouseText)element;
				switch (columnIndex) {
					case 0: return model.getPlanet().getName();
					case 1: return model.getHouse().getName();
					case 2: return model.getAspectType().getName();
				}
				return null;
			}
			@Override
			public Color getForeground(Object element, int columnIndex) {
				if (2 == columnIndex) {
					PlanetHouseText model = (PlanetHouseText)element;
					return model.getAspectType().getColor();
				}
				return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
			}
		};
	}

	@Override
	public View initComposite(Composite parent) {
		return new PlanetHouseComposite().create(parent);
	}

	@Override
	public IModelService getService() {
		return new PlanetHouseService();
	}

	@Override
	public Model create() {
		return new PlanetHouseText();
	}
	
	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.editor/icons/standards.gif";
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
