package kz.zvezdochet.editor.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.bean.TextGenderDictionary;
import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.core.ui.view.ModelView;
import kz.zvezdochet.editor.part.EditorPart;

/**
 * Обработчик открытия браузера редактора
 * @author Natalie Didenko
 *
 */
public class BrowserHandler extends Handler {
	@Inject
	protected EPartService partService;

	@Execute
	public void execute(@Active MPart activePart) {
	    try {
			EditorPart epart = (EditorPart)activePart.getObject();
			Model model = (Model)epart.getModel(0, true);
			if (null == model || !(model instanceof TextGenderDictionary)) return;
			MPart part = partService.findPart("kz.zvezdochet.editor.part.browser");
		    part.setVisible(true);
		    partService.showPart(part, PartState.VISIBLE);
			ModelView modelView = (ModelView)part.getObject();
			modelView.setModel(model, true);
		} catch (IllegalStateException e) {
			//Application does not have an active window
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/* TODO
 * org.eclipse.swt.SWTError: No more handles because no underlying browser available.
   SWT on GTK 2.x detected. It is reccomended to use SWT on GTK 3.x and Webkit2 API.
 */
