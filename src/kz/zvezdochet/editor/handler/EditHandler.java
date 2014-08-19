package kz.zvezdochet.editor.handler;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.handler.ModelOpenHandler;
import kz.zvezdochet.core.ui.view.ModelView;
import kz.zvezdochet.editor.part.EditorListPart;
import kz.zvezdochet.editor.part.EditorPart;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

/**
 * Обработчик модификации модели
 * @author Nataly Didenko
 *
 */
public class EditHandler extends ModelOpenHandler {
	protected EditorListPart listpart;

	@Execute
	public void execute(@Active MPart activePart) {
		listpart = (EditorListPart)activePart.getObject();
		Model model = (Model)listpart.getModel();
		if (null == model) return;
		checkPart("kz.zvezdochet.editor.part.item", model);
	}
	
	@CanExecute
	public boolean canExecute() {
		return true;
	}

	@Override
	protected ModelView openPart(MPart part, Model model) {
		ModelView modelView = super.openPart(part, model);
		if (modelView != null) {
			EditorPart editorPart = (EditorPart)modelView;
			editorPart.setExtension(listpart.getExtension());
			editorPart.setModel(model, true);
//			editorPart.setListener(listpart);
			editorPart.setEditable(true);
//			Handler.updateStatus(Messages.getString("ElementListExtPoint.EditingElement"), false); //$NON-NLS-1$
		}
		return modelView;
	}
}
