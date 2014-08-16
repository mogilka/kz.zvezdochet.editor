package kz.zvezdochet.editor.extension;

/**
 * Интерфейс обработчика изменений состояния расширений справочника
 * @author nataly
 */
public interface IEditorStateListener {
	/**
	 * Оповещение расширяемого объекта, что расширение изменилось
	 */
	public void notifyStateChanged();
}
