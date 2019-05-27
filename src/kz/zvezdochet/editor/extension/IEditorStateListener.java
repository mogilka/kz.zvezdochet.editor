package kz.zvezdochet.editor.extension;

/**
 * Интерфейс обработчика изменений состояния расширений справочника
 * @author Natalie Didenko
 */
public interface IEditorStateListener {
	/**
	 * Оповещение расширяемого объекта, что расширение изменилось
	 */
	public void notifyStateChanged();
}
