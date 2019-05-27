package kz.zvezdochet.editor.part;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.extension.ModelExtension;
import kz.zvezdochet.core.ui.listener.ISaveListener;
import kz.zvezdochet.core.ui.view.ModelListView;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.editor.Activator;

/**
 * Представление списка справочника
 * @author Natalie Didenko
 *
 */
public class EditorListPart extends ModelListView implements ISaveListener {

	@PostConstruct @Override
	public View create(Composite parent) {
		extPointId = Activator.PLUGIN_ID + ".editorList";
		return super.create(parent);
	}
	
	@Override
	public void addModel(Model model) {
		if (null != extension)
			extension.create();
	}

	/**
	 * Расширение справочника
	 */
	private ModelExtension extension;
	
	/**
	 * Инициализация расширения
	 * @param code имя таблицы БД
	 */
	public void initExtension(String code) {
		initExtensions();
		if (null == extensions) return;
		for (ModelExtension extension : extensions) {
			if (extension.canHandle(code)) {
				this.extension = extension;
				break;
			}
		}
		addColumns();
		tableViewer.setLabelProvider(getLabelProvider());
//		try {
//			if (extension != null)
//				setData(extension.getModelList());
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		}
//		table.update();
		if (extension != null) {
			part.setLabel(extension.getName());
			part.setIconURI(extension.getIconURI());

			extension.initFilter(this, grFilter);
			grFilter.layout(true, true);
			grFilter.setSize(grFilter.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			container.layout(true, true);
		}
	}

	public ModelExtension getExtension() {
		return extension;
	}

	@Override
	protected IBaseLabelProvider getLabelProvider() {
		if (null == extension)
			return super.getLabelProvider();
		else
			return extension.getLabelProvider();
	}

	@Override
	public void onSave(Model model, boolean update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel(Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] initTableColumns() {
		if (null == extension)
			return null;
		else
			return extension.getTableColumns();
	}

	@Override
	public boolean check(int mode) throws Exception {
		return false;
	}

	@Override
	public Model createModel() {
		if (null != extension)
			return extension.create();
		return null;
	}

	@Override
	public void initFilter(Composite parent) {
		grFilter = new Group(container, SWT.NONE);
		grFilter.setText("Поиск");
		grFilter.setLayout(new GridLayout());
	}
}
