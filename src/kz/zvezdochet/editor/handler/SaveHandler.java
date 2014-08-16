package kz.zvezdochet.editor.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Обработка сохранения справочника
 * @author Nataly Didenko
 */
public class SaveHandler {

	@Execute
	public void execute(EPartService partService) {
		partService.saveAll(true);
	}
}
