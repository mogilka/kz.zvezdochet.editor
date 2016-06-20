package kz.zvezdochet.editor.handler;

import java.util.List;

import javax.inject.Inject;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.handler.ModelOpenHandler;
import kz.zvezdochet.core.ui.view.ModelView;
import kz.zvezdochet.editor.part.EditorListPart;
import kz.zvezdochet.editor.part.EditorPart;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

/**
 * Обработчик модификации модели
 * @author Nataly Didenko
 *
 */
public class EditHandler extends ModelOpenHandler {

	@Inject
	protected MApplication app;
	@Inject
	protected EModelService modelService;

	@Override
	protected void openPart(MPart part, Model model) {
		ModelView view = ((ModelView)part.getObject());
		if (view != null) {
			EditorPart editorPart = (EditorPart)view;
			editorPart.setDictionary(((EditorListPart)listpart).getDictionary());
			if (null == model)
				model = listpart.createModel();
			editorPart.setModel(model, true);
//			editorPart.setListener(listpart);
			editorPart.setEditable(true);
//			Handler.updateStatus(Messages.getString("ElementListExtPoint.EditingElement"), false); //$NON-NLS-1$
		}
	    part.setVisible(true);
	    try {
		    partService.showPart(part, PartState.VISIBLE);
		    afterOpenPart();
		} catch (IllegalStateException e) {
			//Application does not have an active window
		}

	}

	/**
	 * Инициализация представления модели
	 * @param model модель
	 */
	protected void checkPart(Model model) {
		MPart part = partService.findPart(partid);
		if (null == part) {
		    part = MBasicFactory.INSTANCE.createPart();
		    part.setContributionURI("platform:/plugin/kz.zvezdochet.editor/" + partid);
		    List<MPartStack> stacks = modelService.findElements(app, null, MPartStack.class, null);
		    stacks.get(0).getChildren().add(part);
		    partService.showPart(part, PartState.ACTIVATE);
		    openPart(part, model);
		} else
			openPart(part, model); //TODO не затираем открытый вью, создаём новый		
	}

	@Override
	protected void afterOpenPart() {}
}
