package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.DiagramComposite;

/**
 * Прототип расширения справочника диаграммных объектов
 * @author Nataly Didenko
 */
public abstract class DiagramExtension extends SimpleExtension {

	public ModelComposite initExtensionComposite() {
		return new DiagramComposite();
	}

	@Override
	public Dictionary getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
