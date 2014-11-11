package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.bean.Protraction;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.service.AspectTypeService;
import kz.zvezdochet.service.ProtractionService;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Композит типа аспекта
 * @author Nataly Didenko
 */
public class AspectTypeComposite extends EditorComposite {
	protected ComboViewer cvProtraction;
	protected Label lbProtraction;
	protected Label lbColor;
	protected Label lbDimColor;
	protected Label lbColorView;
	protected Label lbDimColorView;
	protected Button btColor;
	protected Button btDimColor;
	protected ComboViewer cvType;
	protected Label lbType;
	protected Label lbSymbol;
	protected Text txSymbol;
	
	@Override
	public View create(Composite composite) {
	    group = new Group(composite, SWT.NONE);
		group.setText("Тип аспекта");
		
		lbType = new Label(group, SWT.NONE);
		lbType.setText("Основной тип");
		cvType = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);

		lbProtraction = new Label(group, SWT.NONE);
		lbProtraction.setText("Начертание");
		cvProtraction = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		new RequiredDecoration(lbProtraction, SWT.TOP | SWT.RIGHT);

	    lbColor = new Label(group, SWT.NONE);
	    lbColor.setText("Цвет");
	    lbColorView = new Label(group, SWT.BORDER);
	    lbColorView.setText("          ");
	    btColor = new Button(group, SWT.PUSH);
	    btColor.setText("...");
	    btColor.setToolTipText("Выбрать цвет");
		btColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				GUIutil.setBackgroundViaDialog(group.getShell(), lbColorView);
			}
		});
		new RequiredDecoration(lbColor, SWT.TOP | SWT.RIGHT);
	    
	    lbDimColor = new Label(group, SWT.NONE);
	    lbDimColor.setText("Контрастный цвет (для диаграмм)");
	    lbDimColorView = new Label(group, SWT.BORDER);
	    lbDimColorView.setText("          ");
	    btDimColor = new Button(group, SWT.PUSH);
	    btDimColor.setText("...");
	    btDimColor.setToolTipText("Выбрать цвет");
		btDimColor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				GUIutil.setBackgroundViaDialog(group.getShell(), lbDimColorView);
			}
		});
		new RequiredDecoration(lbDimColor, SWT.TOP | SWT.RIGHT);
		
	    lbSymbol = new Label(group, SWT.NONE);
	    lbSymbol.setText("Символ");
	    txSymbol = new Text(group, SWT.BORDER);
		
		decorate();
		init(group);
		try {
			initControls();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		syncView();
		return this;
	}
	
	@Override
	protected void initControls() throws DataAccessException {
		cvType.setContentProvider(new ArrayContentProvider());
		cvType.setLabelProvider(new DictionaryLabelProvider());
		cvType.setInput(new AspectTypeService().getList());

		cvProtraction.setContentProvider(new ArrayContentProvider());
		cvProtraction.setLabelProvider(new DictionaryLabelProvider());
		cvProtraction.setInput(new ProtractionService().getList());
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).span(2, 1).applyTo(cvProtraction.getCombo());
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).span(2, 1).applyTo(cvType.getCombo());
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).span(2, 1).applyTo(txSymbol);

		StateChangedListener listener = new StateChangedListener();
		cvProtraction.addSelectionChangedListener(listener);
		cvType.addSelectionChangedListener(listener);
		txSymbol.addModifyListener(listener);
		btColor.addMouseListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			AspectType dict = (AspectType)model;
			if (dict.getParentType() != null)
				cvType.getCombo().setText(dict.getParentType().getName());
			else
				cvType.setSelection(null);
			if (dict.getProtraction() != null)
				cvProtraction.getCombo().setText(dict.getProtraction().getName());
			else
				cvProtraction.setSelection(null);
		    lbColorView.setBackground(dict.getColor());
		    lbDimColorView.setBackground(dict.getDimColor());
		    txSymbol.setText(String.valueOf(dict.getSymbol()));
		} 
	}
	
	@Override
	public void reset() {
		cvProtraction.setSelection(null);
		cvType.setSelection(null);
		lbColorView.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		lbDimColorView.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txSymbol.setText("");
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		AspectType dict = (AspectType)model;
		IStructuredSelection selection = (IStructuredSelection)cvProtraction.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setProtraction((Protraction)selection.getFirstElement());
		selection = (IStructuredSelection)cvType.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setParentType((AspectType)selection.getFirstElement());
		dict.setColor(lbColorView.getBackground());
		dict.setDimColor(lbDimColorView.getBackground());
		dict.setSymbol(txSymbol.getText().charAt(0));
	}

	@Override
	public boolean check(int mode) {
		String msgBody = "";  //$NON-NLS-1$
		if (cvProtraction.getSelection().isEmpty())
			msgBody += lbProtraction.getText() + '\n';
		if (msgBody.length() > 0) {
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else 
			return true;
	}
}
