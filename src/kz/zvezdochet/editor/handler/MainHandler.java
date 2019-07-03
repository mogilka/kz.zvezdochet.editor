package kz.zvezdochet.editor.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import kz.zvezdochet.core.handler.Handler;

/**
 * Обработчик открытия перспективы справочников
 * @author Natalie Didenko
 *
 */
public class MainHandler extends Handler {
	
	@Execute
	public void execute(MApplication app, EModelService service, EPartService partService) {
		MPerspective perspective = (MPerspective)service.find("kz.zvezdochet.editor.perspective.dict", app);
		if (perspective != null)
			partService.switchPerspective(perspective);
	}
}