package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.core.util.CalcUtil;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
 * Композит знака Зодиака
 * @author Nataly 
 */
public class SignComposite extends EditorComposite {
	private Text txInitialPoint;
	private Text txFinalPoint;
	private Spinner spNumber;
	//TODO добавить разделение по стихиям и проч
	
	@Override
	public View create(Composite parent) {
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
			span(3, 1).grab(true, false).applyTo(txInitialPoint);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txFinalPoint);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(spNumber);

		StateChangedListener listener = new StateChangedListener();
		txInitialPoint.addModifyListener(listener);
		txFinalPoint.addModifyListener(listener);
		spNumber.addModifyListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			Sign sign = (Sign)model;
			txInitialPoint.setText(CalcUtil.formatNumber("###.##", sign.getI0()));
//			txFinalPoint.setText(CalcUtil.formatNumber("###.##", sign.getCoord()));
			spNumber.setSelection(sign.getNumber());
		} 
	}
	
	@Override
	public void reset() {
		txInitialPoint.setText("");
		txFinalPoint.setText("");
		spNumber.setSelection(0);
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		Sign sign = (Sign)model;
		sign.setI0(Double.parseDouble(txInitialPoint.getText()));
//		sign.setCoord(Double.parseDouble(txFinalPoint.getText()));
		sign.setNumber(spNumber.getSelection());
	}

	@Override
	public boolean check(int mode) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
