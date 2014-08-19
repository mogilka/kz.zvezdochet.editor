package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.extension.ModelExtension;

/**
 * Прототип провайдера расширения справочника
 * @author Nataly Didenko
 */
public abstract class EditorExtension extends ModelExtension {
	
	/**
	 * Возвращает код справочника
	 * @return имя класса справочника
	 */
	protected String getDictionary() {
		return getService().getTableName();
	}

	@Override
	public boolean canHandle(Object object) {
		return object != null && object.equals(getDictionary());
	}

	@Override
	public boolean isChanged() {
		return false;
	}

	@Override
	public void close() {
		model = null;
		if (composite != null) {
//			composite.dispose();TODO
			composite = null;
		}
	}

	@Override
	public void delete() {}

	@Override
	public Model getModel() {
		try {
			return composite != null ? (Model)composite.getModel(0, true) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void initView() {
//		String viewid = getExtensionViewId();
//		if (viewid != null) {
//			try {
//				ModelView view = null;
//				IWorkbenchPage page = PlatformUtil.getActivePage();
//				view = (ModelView)page.findView(viewid);
//				if (null == view)
//					view = (ModelView)page.showView(viewid, null, IWorkbenchPage.VIEW_CREATE);
//			} catch (PartInitException e) {
//				e.printStackTrace();
//			}	
////			view.setStateListener(stateListener);
//			view.setModel((Model)extension, true);
//			addShowingView(view);	
//		}
	}
	
	@Override
	public String getExtensionViewId() {
		return "kz.zvezdochet.editor.part.item";
	}

//	@Override
//	public void deleteModel(View view, ISaveListener listener) {
//		EditorListPart listview = (EditorListPart)view;
//		Object object = listview.getModel();
//		if (null == object) return;
//		TableItem tableItem = (TableItem)object;
//		Long id = Long.valueOf(tableItem.getText(0));
//		if (DialogUtil.alertConfirm(GUIutil.DO_YOU_REALLY_WANNA_DELETE_ENTRY)) {
////			if (entity.getChildren() != null && entity.getChildren().size() > 0) {
////				DialogUtil.alertWarning(GUIutil.DELETING_OBJECT_HAS_CHILDREN);
////				return;
////			} TODO написать свой обработчик экстремальных удалений
//			try {
//				getService().delete(id);
//			} catch (DataAccessException e) {
//				e.printStackTrace();
//			}
//			Handler.updateStatus(Messages.getString("ElementListExtPoint.ElementDeleted"), false); //$NON-NLS-1$
//			listener.onSave(null);
////			elementView = view.getSite().getPage().findView(getElementViewId()); 
//			if (modelView != null) 
//				((View)modelView).reset();
//		} else {
//			Handler.updateStatus(Messages.getString("ElementListExtPoint.CancelDeletingElement"), false); //$NON-NLS-1$
//			listener.onCancel((Model)((ModelListView)view).getModel());
//		}
//	}
}
