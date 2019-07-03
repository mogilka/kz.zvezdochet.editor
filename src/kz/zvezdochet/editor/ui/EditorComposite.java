package kz.zvezdochet.editor.ui;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import kz.zvezdochet.core.ui.view.ModelComposite;
import kz.zvezdochet.editor.extension.IEditorStateListener;

/**
 * Прототип композита, встраиваемого в редактор справочника
 * @author Natalie Didenko
 */
public abstract class EditorComposite extends ModelComposite {
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

	public class StateChangedListener implements ModifyListener, ISelectionChangedListener,
			MouseListener, SelectionListener {
		public void modifyText(ModifyEvent e) {
			notifyChange();
			deactivateUnaccessable();
		}
		public void selectionChanged(SelectionChangedEvent event) {
			notifyChange();
			deactivateUnaccessable();
		}
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseDown(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseUp(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
