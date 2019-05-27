package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.PlanetAspectText;
import kz.zvezdochet.analytics.service.PlanetAspectService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.PlanetAspectComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника Аспекты планет
 * @author Natalie Didenko
 */
public class PlanetAspectExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Аспекты планет";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Тип аспекта", "Планета"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				PlanetAspectText model = (PlanetAspectText)element;
				switch (columnIndex) {
					case 0: return model.getPlanet1().getName();
					case 1: return model.getType().getName();
					case 2: return model.getPlanet2().getName();
				}
				return null;
			}
		};
	}

	@Override
	public View initComposite(Composite parent) {
		return new PlanetAspectComposite().create(parent);
	}

	@Override
	public IModelService getService() {
		return new PlanetAspectService();
	}

	@Override
	public Model create() {
		return new PlanetAspectText();
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
