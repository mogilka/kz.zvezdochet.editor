package kz.zvezdochet.editor.handler;

import javax.inject.Named;

import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.editor.part.EditorListPart;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Обработчик открытия справочника
 * @author Nataly Didenko
 *
 */
public class DictHandler extends Handler {
	
	@Execute
	public void execute(EPartService partService, @Named("kz.zvezdochet.editor.commandparameter.dict") String dict) {
		MPart part = partService.findPart("kz.zvezdochet.editor.part.items");
		EditorListPart dictPart = (EditorListPart)part.getObject();
	    dictPart.initExtension(dict);
	}
}