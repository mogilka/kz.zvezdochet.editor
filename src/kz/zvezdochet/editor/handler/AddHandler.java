package kz.zvezdochet.editor.handler;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.editor.part.EditorListPart;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

/**
 * Обработчик добавления модели
 * @author Nataly Didenko
 */
public class AddHandler extends EditHandler {

	@Execute
	public void execute(@Active MPart activePart) {
		listpart = (EditorListPart)activePart.getObject();
//		Model model = (Model)listpart.addModel();
//		if (null == model) return;
//		MPart part = partService.findPart(getViewId());
//		checkPart(part, model);
	}
}