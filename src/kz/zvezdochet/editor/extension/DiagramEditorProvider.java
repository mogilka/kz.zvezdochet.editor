package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.ui.DiagramComposite;

/**
 * Прототип расширителя справочника диаграммных объектов
 * @author Nataly Didenko
 */
public class DiagramEditorProvider extends SimpleEditorProvider {

	public ModelComposite initExtensionComposite() {
		return new DiagramComposite();
	}

	@Override
	public Dictionary getExtended() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getExtensionService() {
		// TODO Auto-generated method stub
		return null;
	}
}
