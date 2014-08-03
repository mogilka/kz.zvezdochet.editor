 
package kz.zvezdochet.editor.handler;

import javax.inject.Inject;

import kz.zvezdochet.editor.part.EditorListView;
import kz.zvezdochet.service.PlaceService;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

/**
 * Обработчик открытия справочника мест
 * @author Nataly Didenko
 *
 */
public class PlaceHandler {
	
	@Execute
	public void execute(MApplication app, EModelService service, EPartService partService) {
	    //TODO если перспектива не открыта, открываем её
		MPerspective perspective = (MPerspective)service.find("kz.zvezdochet.editor.perspective.dict", app);
		if (perspective != null) {
//			partService.saveAll(true);
			partService.switchPerspective(perspective);
		}
		
//		MPart part = partService.findPart("kz.zvezdochet.editor.part.items");
//	    part.setVisible(true);
//	    partService.showPart(part, PartState.VISIBLE);
//	    ((EditorListView)part).setReferenceCode(new PlaceService().getTableName());
	    //TODO как-то надо передавать параметр в обработчик чтобы открывать именно плэйс
	    
	    //вот так тоже можно создавать вьюшку
//	    MPart newPart = MBasicFactory.INSTANCE.createPart();
//	    mWindow.getChildren().add(newPart);
	}
}