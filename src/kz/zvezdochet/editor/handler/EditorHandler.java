package kz.zvezdochet.editor.handler;

import javax.inject.Inject;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.editor.part.EditorListPart;
import kz.zvezdochet.part.EventPart;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

/**
 * Обработчик открытия редактора справочника
 * @author Nataly Didenko
 *
 */
public class EditorHandler {
	@Inject
	private EPartService partService;

	@Execute
	public void execute(@Active MPart activePart) {
		EditorListPart part = (EditorListPart)activePart.getObject();
		Model model = (Model)part.getModel();
		if (model != null)
			checkPart(model);
	}
	
	@CanExecute
	public boolean canExecute() {
		return true;
	}

	/**
	 * Отображение события в его представлении
	 * @param part представление
	 * @param model событие
	 */
	private void openEvent(MPart part, Model model) {
		if (model != null)
			((EventPart)part.getObject()).setModel(model, true);
	    part.setVisible(true);
	    try {
		    partService.showPart(part, PartState.VISIBLE);
		} catch (IllegalStateException e) {
			//Application does not have an active window
		}
	}

	/**
	 * Проверка состояния представления
	 * @param model модель
	 */
	protected void checkPart(Model model) {
		MPart part = partService.findPart("kz.zvezdochet.editor.part.item");
	    if (part.isDirty()) {
			if (DialogUtil.alertConfirm(
					"Открытый ранее редактор не сохранён\n"
					+ "и утратит все внесённые изменения,\n"
					+ "если Вы откроете новый. Продолжить?")) {
				openEvent(part, model);
			}
	    } else
	    	openEvent(part, model);
	}
}
