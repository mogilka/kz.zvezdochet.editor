package kz.zvezdochet.editor.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import kz.zvezdochet.bean.House;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.view.View;

/**
 * Композит астрологического дома
 * @author Natalie Didenko
 */
public class HouseComposite extends EditorComposite {
	private Text txShort;
	private Text txCombination;
	private Text txDesignation;
	private Text txHeader;
	private Text txLink;
	private Spinner spNumber;
	//TODO добавить комбобоксы разделений
	
	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("Астрологический дом");
		Label lb = new Label(group, SWT.NONE);
		lb.setText("Порядковый номер");
		spNumber = new Spinner(group, SWT.BORDER);
		spNumber.setMinimum(1);
		spNumber.setMaximum(36);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Краткое наименование");
		txShort = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Описание местонахождения");
		txCombination = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Обозначение");
		txDesignation = new Text(group, SWT.BORDER);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Наименование для отчета");
		txHeader = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Наименование для ссылки");
		txLink = new Text(group, SWT.BORDER);
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
			span(3, 1).grab(true, false).applyTo(txShort);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txCombination);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txDesignation);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txHeader);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txLink);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(spNumber);

		StateChangedListener listener = new StateChangedListener();
		txShort.addModifyListener(listener);
		txCombination.addModifyListener(listener);
		txDesignation.addModifyListener(listener);
		txHeader.addModifyListener(listener);
		txLink.addModifyListener(listener);
		spNumber.addModifyListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			House house = (House)model;
			if (house.getName() != null)
				txShort.setText(house.getName());
			if (house.getDesignation() != null)
				txDesignation.setText(house.getDesignation());
			if (house.getName() != null)
				txHeader.setText(house.getName());
			if (house.getCategory() != null)
				txLink.setText(house.getCategory());
			spNumber.setSelection(house.getNumber());
		} 
	}
	
	@Override
	public void reset() {
		txShort.setText("");
		txCombination.setText("");
		txDesignation.setText("");
		txHeader.setText("");
		txLink.setText("");
		spNumber.setSelection(0);
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		House house = (House)model;
		house.setName(txShort.getText());
		house.setDesignation(txDesignation.getText());
		house.setCategory(txLink.getText());
		house.setNumber(spNumber.getSelection());
	}

	@Override
	public boolean check(int mode) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
