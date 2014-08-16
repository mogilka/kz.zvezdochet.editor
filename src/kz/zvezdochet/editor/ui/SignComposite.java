package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.util.CalcUtil;

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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * Композит знака Зодиака
 * @author Nataly 
 */
public class SignComposite extends DictionaryComposite {
	private Text txInitialPoint;
	private Text txFinalPoint;
	private Text txDiagram;
	private Spinner spNumber;
	private Label lbColor;
	private Label lbColorView;
	private Button btColor;
	
	@Override
	public Composite createComposite(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("Знак Зодиака");
		Label lb = new Label(group, SWT.NONE);
		lb.setText("Порядковый номер");
		spNumber = new Spinner(group, SWT.BORDER);
		spNumber.setMinimum(1);
		spNumber.setMaximum(36);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Начальная координата");
		txInitialPoint = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Конечная координата");
		txFinalPoint = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Наименование для диаграммы");
		txDiagram = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
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

		decorate();
		prepareView();
		setListeners();
		syncView();
		return group;
	}
	
	@Override
	protected void prepareView() {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(group);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(group);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txInitialPoint);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txFinalPoint);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txDiagram);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(spNumber);
	}
	
	@Override
	protected void setListeners() {
		StateChangedListener listener = new StateChangedListener();
		txInitialPoint.addModifyListener(listener);
		txFinalPoint.addModifyListener(listener);
		txDiagram.addModifyListener(listener);
		spNumber.addModifyListener(listener);
		btColor.addMouseListener(listener);
	}
	
	@Override
	protected void modelToView() {
		clearComposite();
		setCodeEdit(true);
		if (model != null) {
			Sign sign = (Sign)model;
			txInitialPoint.setText(CalcUtil.formatNumber("###.##", sign.getInitialPoint()));
			txFinalPoint.setText(CalcUtil.formatNumber("###.##", sign.getCoord()));
			if (sign.getDiaName() != null)
				txDiagram.setText(sign.getDiaName());
			spNumber.setSelection(sign.getNumber());
		    lbColorView.setBackground(sign.getColor());
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void clearComposite() {
		setCodeEdit(true);
		txInitialPoint.setText("");
		txFinalPoint.setText("");
		txDiagram.setText("");
		spNumber.setSelection(0);
		lbColorView.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		setCodeEdit(false);
	}
	
	@Override
	public void viewToModel() {
		if (model == null) return;
		Sign sign = (Sign)model;
		sign.setInitialPoint(Double.parseDouble(txInitialPoint.getText()));
		sign.setCoord(Double.parseDouble(txFinalPoint.getText()));
		sign.setDiaName(txDiagram.getText());
		sign.setNumber(spNumber.getSelection());
		sign.setColor(lbColorView.getBackground());
	}
}
