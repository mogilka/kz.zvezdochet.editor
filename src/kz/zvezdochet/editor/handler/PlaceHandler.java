 
package kz.zvezdochet.editor.handler;

import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.editor.part.EditorListPart;
import kz.zvezdochet.editor.part.EditorPart;
import kz.zvezdochet.service.PlaceService;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Обработчик открытия справочника мест
 * @author Nataly Didenko
 *
 */
public class PlaceHandler extends Handler {
	
	@Execute
	public void execute(MApplication app, EModelService service, EPartService partService) {
		MPerspective perspective = (MPerspective)service.find("kz.zvezdochet.editor.perspective.dict", app);
		if (perspective != null)
			partService.switchPerspective(perspective);
		
		MPart part = partService.findPart("kz.zvezdochet.editor.part.items");
		EditorListPart dictPart = (EditorListPart)part.getObject();
	    dictPart.setDictionary(new PlaceService().getTableName());
	    //TODO как-то надо передавать параметр в обработчик чтобы открывать именно плэйс
	    
	    //вот так тоже можно создавать вьюшку
//	    MPart newPart = MBasicFactory.INSTANCE.createPart();
//	    mWindow.getChildren().add(newPart);
	}
}