package kz.zvezdochet.editor.extension;

import kz.zvezdochet.editor.part.EditorPart;

/**
 * Обработчик изменений состояния расширений справочника 
 * @author Natalie Didenko
 *
 */
public class EditorStateListener implements IEditorStateListener {
	/**
	 * Ссылка на основное представление
	 */
	private EditorPart editorView = null;
	
	public EditorStateListener(EditorPart editView) {
		this.editorView = editView;		
	}
	
	public void notifyStateChanged() {
		editorView.notifyStateChanged();
	}
}
