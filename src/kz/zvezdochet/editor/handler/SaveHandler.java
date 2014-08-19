package kz.zvezdochet.editor.handler;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.editor.part.EditorPart;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

/**
 * Сохранение значения справочника
 * @author Nataly Didenko
 */
public class SaveHandler {

	@Execute
	public void execute(@Active MPart activePart) {
		EditorPart part = (EditorPart)activePart.getObject();
		Model model = null;
		try {
			model = part.getModel(Handler.MODE_SAVE, true);
			model = model.getService().save(model);
			part.setModel(model, false);
			//TODO обновлять в таблице данные
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == model) return;
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}
}
