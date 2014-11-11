package kz.zvezdochet.editor.ui;

import kz.zvezdochet.core.bean.IColorizedObject;
import kz.zvezdochet.core.bean.IDiagramObject;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.View;

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
public class DiagramComposite extends EditorComposite {
	protected Label lbColor;
	protected Label lbColorView;
	protected Button btColor;
	protected Label lbDianame;
	protected Text txDianame;
	
	@Override
	public View create(Composite composite) {
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
		syncView();
		return this;
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).span(2, 1).applyTo(txDianame);

		StateChangedListener listener = new StateChangedListener();
		txDianame.addModifyListener(listener);
		btColor.addMouseListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
		    lbColorView.setBackground(((IColorizedObject)model).getColor());
		    txDianame.setText(String.valueOf(((IDiagramObject)model).getDiaName()));
		} 
	}
	
	@Override
	public void reset() {
		lbColorView.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		txDianame.setText("");
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		((IColorizedObject)model).setColor(lbColorView.getBackground());
		((IDiagramObject)model).setDiaName(txDianame.getText());
	}

	@Override
	public boolean check(int mode) {
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
