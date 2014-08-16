package kz.zvezdochet.editor.extension;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.ui.comparator.TableSortListenerFactory;
import kz.zvezdochet.core.ui.extension.ModelExtensionProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Прототип провайдера расширения справочника
 * @author Nataly Didenko
 */
public abstract class EditorExtensionProvider extends ModelExtensionProvider {

	/**
	 * Метод, возвращающий код справочника
	 * @return имя класса справочника
	 */
	protected String getDictionary() {
		return getExtensionService().getTableName();
	}

	@Override
	public boolean canHandle(Object object) {
		return object != null && object.equals(getDictionary());
	}

	@Override
	public void initTableColumns(Table table) {
		String[] columns = getTableColumns();
		for (TableColumn column : table.getColumns())
			column.dispose();
		for (String column : columns) {
			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setWidth(100);
			tableColumn.setText(column);		
			tableColumn.addListener(SWT.Selection, TableSortListenerFactory.getListener(
						TableSortListenerFactory.STRING_COMPARATOR));
			tableColumn.pack();
		}
	}
	
	/**
	 * Поиск колонок таблицы расширения
	 * @return массив колонок таблицы
	 */
	protected abstract String[] getTableColumns();

	@Override
	public boolean isChanged() {
		return false;
	}

	@Override
	public void close() {
		model = null;
		if (composite != null) {
			composite.dispose();
			composite = null;
		}
	}

	public ITableLabelProvider getLabelProvider() {
		return new EditorLabelProvider();
	}

	@Override
	public void deleteExtension() {}

	@Override
	public Model getExtended() {
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
//				if (view == null)
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
}
