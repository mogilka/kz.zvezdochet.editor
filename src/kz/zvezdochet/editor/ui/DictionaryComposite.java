package kz.zvezdochet.editor.ui;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.extension.IEditorStateListener;

/**
 * Прототип композита, встраиваемого в редактор справочника
 * @author Nataly Didenko
 */
public abstract class DictionaryComposite extends ModelComposite {
	/**
	 * Обработчик изменения состояния композита
	 */
	protected IEditorStateListener infoStateListener = null;

	@Override
	public void notifyChange() {
		super.notifyChange();
		if (infoStateListener != null)
			infoStateListener.notifyStateChanged();
	}
}
