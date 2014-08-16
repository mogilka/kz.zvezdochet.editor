package kz.zvezdochet.editor.ui;

import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.util.IColorizedObject;
import kz.zvezdochet.util.IDiagramObject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
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
 * Композит диаграммного объекта
 * @author Nataly 
 */
public class DiagramComposite extends DictionaryComposite {
	protected Label lbColor;
	protected Label lbColorView;
	protected Button btColor;
	protected Label lbDianame;
	protected Text txDianame;
	
	@Override
	public Composite create(Composite composite) {
	    group = new Group(composite, SWT.NONE);
		group.setText("Объект диаграммы");
		
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
	    
	    lbDianame = new Label(group, SWT.NONE);
	    lbDianame.setText("Наименование для диаграммы");
	    txDianame = new Text(group, SWT.BORDER);
		
		decorate();
		init(group);
		setListeners();
		syncView();
		return group;
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).span(2, 1).applyTo(txDianame);
	}
	
	@Override
	protected void setListeners() {
		StateChangedListener listener = new StateChangedListener();
		txDianame.addModifyListener(listener);
		btColor.addMouseListener(listener);
	}
	
	@Override
	protected void modelToView() {
		clearComposite();
		setCodeEdit(true);
		if (model != null) {
		    lbColorView.setBackground(((IColorizedObject)model).getColor());
		    txDianame.setText(String.valueOf(((IDiagramObject)model).getDiaName()));
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void clearComposite() {
		setCodeEdit(true);
		lbColorView.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txDianame.setText("");
		setCodeEdit(false);
	}
	
	@Override
	public void viewToModel() {
		if (model == null) return;
		((IColorizedObject)model).setColor(lbColorView.getBackground());
		((IDiagramObject)model).setDiaName(txDianame.getText());
	}

	@Override
	public boolean checkViewValues() {
		String msgBody = "";  //$NON-NLS-1$
		if (txDianame.getText().length() == 0)
			msgBody += lbDianame.getText() + '\n';
		if (msgBody.length() > 0) {
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else 
			return true;
	}
}
