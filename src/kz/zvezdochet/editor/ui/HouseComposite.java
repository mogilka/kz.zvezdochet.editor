package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.House;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.util.GUIutil;

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
 * Композит дома
 * @author Nataly 
 */
public class HouseComposite extends DictionaryComposite {
	private Text txShort;
	private Text txCombination;
	private Text txDesignation;
	private Text txDiagram;
	private Text txHeader;
	private Text txLink;
	private Spinner spNumber;
	private Label lbColor;
	private Label lbColorView;
	private Button btColor;
	
	@Override
	public Composite createComposite(Composite parent) {
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
		lb.setText("Наименование для диаграммы");
		txDiagram = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Наименование для отчета");
		txHeader = new Text(group, SWT.BORDER);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);
		
		lb = new Label(group, SWT.NONE);
		lb.setText("Наименование для ссылки");
		txLink = new Text(group, SWT.BORDER);
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
			span(3, 1).grab(true, false).applyTo(txShort);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txCombination);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txDesignation);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txDiagram);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txHeader);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(txLink);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(3, 1).grab(true, false).applyTo(spNumber);
	}
	
	@Override
	protected void setListeners() {
		StateChangedListener listener = new StateChangedListener();
		txShort.addModifyListener(listener);
		txCombination.addModifyListener(listener);
		txDesignation.addModifyListener(listener);
		txDiagram.addModifyListener(listener);
		txHeader.addModifyListener(listener);
		txLink.addModifyListener(listener);
		spNumber.addModifyListener(listener);
		btColor.addMouseListener(listener);
	}
	
	@Override
	protected void modelToView() {
		clearComposite();
		setCodeEdit(true);
		if (model != null) {
			House house = (House)model;
			if (house.getShortName() != null)
				txShort.setText(house.getShortName());
			if (house.getCombination() != null)
				txCombination.setText(house.getCombination());
			if (house.getDesignation() != null)
				txDesignation.setText(house.getDesignation());
			if (house.getDiaName() != null)
				txDiagram.setText(house.getDiaName());
			if (house.getHeaderName() != null)
				txHeader.setText(house.getHeaderName());
			if (house.getLinkName() != null)
				txLink.setText(house.getLinkName());
			spNumber.setSelection(house.getNumber());
		    lbColorView.setBackground(house.getColor());
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void clearComposite() {
		setCodeEdit(true);
		txShort.setText("");
		txCombination.setText("");
		txDesignation.setText("");
		txDiagram.setText("");
		txHeader.setText("");
		txLink.setText("");
		spNumber.setSelection(0);
		lbColorView.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		setCodeEdit(false);
	}
	
	@Override
	public void viewToModel() {
		if (model == null) return;
		House house = (House)model;
		house.setShortName(txShort.getText());
		house.setCombination(txCombination.getText());
		house.setDesignation(txDesignation.getText());
		house.setDiaName(txDiagram.getText());
		house.setHeaderName(txHeader.getText());
		house.setLinkName(txLink.getText());
		house.setNumber(spNumber.getSelection());
		house.setColor(lbColorView.getBackground());
	}
}
