package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.extension.Messages;
import kz.zvezdochet.core.ui.extension.ModelListProvider;
import kz.zvezdochet.core.ui.listener.ISaveListener;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.ModelListView;
import kz.zvezdochet.core.ui.view.ModelView;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.part.EditorListPart;
import kz.zvezdochet.editor.part.EditorPart;

import org.eclipse.swt.widgets.TableItem;

/**
 * Расширитель справочника для отображения редактора значений
 * @author Nataly Didenko
 */
public class EditorListProvider extends ModelListProvider {

	public EditorListProvider() {
		modelViewId = EditorPart.ID;
	}

	@Override
	public void editModel(View view, ISaveListener listener) {
		try {
			EditorListPart listview = (EditorListPart)view;
			Object object = listview.getModel();
			if (object == null) return;
			TableItem tableItem = (TableItem)object;
			Long id = Long.valueOf(tableItem.getText(0));
			Model model = listview.getExtService().find(id);
			if (model != null) {
//				elementView = listview.getSite().getPage().showView(getElementViewId());
//				((ModelView)modelView).setListener(listener);
				((EditorPart)modelView).setDictionary(listview.getDictionary());
				((EditorPart)modelView).setEditable(listview.isEditable());
				((ModelView)modelView).setModel(model, true);
			}
			Handler.updateStatus(Messages.getString("ElementListExtPoint.EditingElement"), false); //$NON-NLS-1$
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteModel(View view, ISaveListener listener) {
		EditorListPart listview = (EditorListPart)view;
		Object object = listview.getModel();
		if (object == null) return;
		TableItem tableItem = (TableItem)object;
		Long id = Long.valueOf(tableItem.getText(0));
		if (DialogUtil.alertConfirm(GUIutil.DO_YOU_REALLY_WANNA_DELETE_ENTRY)) {
//			if (entity.getChildren() != null && entity.getChildren().size() > 0) {
//				DialogUtil.alertWarning(GUIutil.DELETING_OBJECT_HAS_CHILDREN);
//				return;
//			} TODO написать свой обработчик экстремальных удалений
			try {
				listview.getExtService().delete(id);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			Handler.updateStatus(Messages.getString("ElementListExtPoint.ElementDeleted"), false); //$NON-NLS-1$
			listener.onSave(null);
//			elementView = view.getSite().getPage().findView(getElementViewId()); 
			if (modelView != null) 
				((View)modelView).reset();
		} else {
			Handler.updateStatus(Messages.getString("ElementListExtPoint.CancelDeletingElement"), false); //$NON-NLS-1$
			listener.onCancel((Model)((ModelListView)view).getModel());
		}
	}

	@Override
	public void addModel(View view, ISaveListener listener) {
		EditorListPart listview = (EditorListPart)view;
		String code = listview.getDictionary();
		try {
			Model model = (Model)listview.createElement(code);
			if (model != null) {
//					elementView = view.getSite().getPage().showView(getElementViewId());
//					((EditorPart)modelView).setListener(listener);
					((EditorPart)modelView).setDictionary(code);
					((EditorPart)modelView).setModel(model, true);
					((EditorPart)modelView).setEditable(listview.isEditable());
//					((EditorView)elementView).setFocus();
			}
			Handler.updateStatus(Messages.getString("ElementListExtPoint.AddingElement"), false); //$NON-NLS-1$
		} catch (Exception e) {
			Handler.updateStatus(Messages.getString("ElementListExtPoint.ErrorElementAdding"), true); //$NON-NLS-1$
			DialogUtil.alertError(e.getMessage());
		}
	}
}
