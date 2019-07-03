package kz.zvezdochet.editor.extension;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.analytics.bean.PlanetSignText;
import kz.zvezdochet.analytics.service.PlanetSignService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.PlanetSignComposite;

/**
 * Расширение справочника Планеты в знаках Зодиака
 * @author Natalie Didenko
 */
public class PlanetSignExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Планеты в знаках";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Знак Зодиака", "Категория"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
//				PlanetSignText model = (PlanetSignText)element;
//				switch (columnIndex) {
//					case 0: return model.getPlanet().getName();
//					case 1: return model.getSign().getName();
//					case 2: return model.getCategory().getName();
//				}
				return null;
			}
		};
	}

	@Override
	public View initComposite(Composite parent) {
		return new PlanetSignComposite().create(parent);
	}

	@Override
	public IModelService getService() {
		return new PlanetSignService();
	}

	@Override
	public Model create() {
		return new PlanetSignText();
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
