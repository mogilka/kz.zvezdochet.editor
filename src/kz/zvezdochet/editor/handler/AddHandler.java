package kz.zvezdochet.editor.handler;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.core.ui.listener.ISaveListener;
import kz.zvezdochet.core.ui.view.ModelListView;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.extension.EditorListProvider;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

/**
 * Обработчик добавления элемента
 * @author Nataly Didenko
 */
public class AddHandler extends Handler implements ISaveListener {

	@CanExecute
	public boolean canExecute(@Active MPart activePart) {
		return ((ModelListView)activePart).isEditable();
	}

	@Execute
	public void execute(@Active MPart activePart) {
//		updateStatus(Messages.getString("AddElementAction.AddingNewElement"), false); //$NON-NLS-1$
		IConfigurationElement[] extensions = 
			(IConfigurationElement[])((ModelListView)activePart).initExtensions();
		for (int x = 0 ; x < extensions.length ; x++) {
			try {
				EditorListProvider provider 
					= (EditorListProvider)extensions[x].createExecutableExtension("class"); //$NON-NLS-1$
				provider.addModel((View)activePart, this);
			} catch (Exception genex) {
//				updateStatus(Messages.getString("AddElementAction.ErrorWhileAddingNewElement"), true); //$NON-NLS-1$
				genex.printStackTrace(System.err);
			}
		}	
	}

	@Override
	public void onSave(Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel(Model model) {
		// TODO Auto-generated method stub
		
	}
}
