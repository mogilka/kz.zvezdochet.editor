package kz.zvezdochet.editor.handler;

import java.util.List;

import javax.inject.Inject;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.handler.ModelOpenHandler;
import kz.zvezdochet.core.ui.view.ModelView;
import kz.zvezdochet.editor.part.EditorListPart;
import kz.zvezdochet.editor.part.EditorPart;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
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
	protected EditorListPart listpart;

	@Inject
	protected MApplication app;
	@Inject
	protected EModelService modelService;

	@Execute
	public void execute(@Active MPart activePart) {
		listpart = (EditorListPart)activePart.getObject();
		Model model = (Model)listpart.getModel();
		if (null == model) return;
		initPart(model);
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
			editorPart.setDictionary(listpart.getDictionary());
			editorPart.setModel(model, true);
//			editorPart.setListener(listpart);
			editorPart.setEditable(true);
//			Handler.updateStatus(Messages.getString("ElementListExtPoint.EditingElement"), false); //$NON-NLS-1$
		}
		return modelView;
	}

	/**
	 * Возвращает идентификатор представления
	 * @return идентификатор открываемого представления
	 */
	protected String getViewId() {
		return "kz.zvezdochet.editor.part.item";
	}
	
	/**
	 * Инициализация представления модели
	 * @param model модель
	 */
	protected void initPart(Model model) {
		MPart part = partService.findPart(getViewId());
		if (null == part) {
		    part = MBasicFactory.INSTANCE.createPart();
		    part.setContributionURI("platform:/plugin/kz.zvezdochet.editor/kz.zvezdochet.editor.part.item");
		    List<MPartStack> stacks = modelService.findElements(app, null, MPartStack.class, null);
		    stacks.get(0).getChildren().add(part);
		    partService.showPart(part, PartState.ACTIVATE);
		    openPart(part, model);
		} else
			checkPart(part, model); //TODO не затираем открытый вью, создаёмновый		
	}
}
