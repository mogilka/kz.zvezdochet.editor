package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.bean.SynastryText;
import kz.zvezdochet.analytics.service.SynastrySignService;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.core.ui.view.ModelLabelProvider;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.ui.SynastrySignComposite;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

/**
 * Расширение справочника Планеты в знаках Зодиака
 * @author Nataly Didenko
 */
public class SynastrySignExtension extends EditorExtension {

	@Override
	public String getName() {
		return "Планеты в синастрических знаках";
	}

	@Override
	public String[] getTableColumns() {
		return new String[] {"Планета", "Знак Зодиака", "Знак Зодиака"};
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				SynastryText model = (SynastryText)element;
				switch (columnIndex) {
					case 0: return model.getPlanet().getName();
					case 1: return model.getSign1().getName();
					case 2: return model.getSign2().getName();
				}
				return null;
			}
		};
	}

	public ModelComposite initExtensionComposite() {
		return new SynastrySignComposite();
	}

	@Override
	public IModelService getService() {
		return new SynastrySignService();
	}

	@Override
	public Model create() {
		return new SynastryText();
	}

	@Override
	public String getIconURI() {
		return "platform:/plugin/kz.zvezdochet.editor/icons/standards.gif";
	}

	@Override
	public View initComposite(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
