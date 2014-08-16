package kz.zvezdochet.editor.ui;

import kz.zvezdochet.analytics.bean.SynastryTextDictionary;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.core.ui.util.GUIutil;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * Композит синастрии планет в знаках партнеров
 * @author Nataly 
 */
public class SynastrySignComposite extends DictionaryComposite {
	protected ComboViewer cvPlanet;
	protected Combo cmbPlanet;
	protected ComboViewer cvSign1;
	protected Combo cmbSign1;
	protected ComboViewer cvSign2;
	protected Combo cmbSign2;
	protected Label lbPlanet;
	protected Label lbSign1;
	protected Label lbSign2;
	
	@Override
	public Composite createComposite(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("");
		
		lbPlanet = new Label(group, SWT.NONE);
		lbPlanet.setText("Планета");
		cvPlanet = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbPlanet = cvPlanet.getCombo();
		new RequiredDecoration(lbPlanet, SWT.TOP | SWT.RIGHT);

		lbSign1 = new Label(group, SWT.NONE);
		lbSign1.setText("Знак планеты первого партнера");
		cvSign1 = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbSign1 = cvSign1.getCombo();
		new RequiredDecoration(lbSign1, SWT.TOP | SWT.RIGHT);
		
		lbSign2 = new Label(group, SWT.NONE);
		lbSign2.setText("Знак планеты второго партнера");
		cvSign2 = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
		cmbSign2 = cvSign2.getCombo();
		new RequiredDecoration(lbSign2, SWT.TOP | SWT.RIGHT);

		decorate();
		prepareView();
		try {
			initializeControls();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		setListeners();
		syncView();
		return group;
	}
	
	@Override
	protected void initControls() throws DataAccessException {
		cvPlanet.setContentProvider(new ArrayContentProvider());
		cvPlanet.setLabelProvider(new DictionaryLabelProvider());
		cvPlanet.setInput(StargazerDataSet.getInstance().getPlanetList());

		cvSign1.setContentProvider(new ArrayContentProvider());
		cvSign1.setLabelProvider(new DictionaryLabelProvider());
		cvSign1.setInput(StargazerDataSet.getInstance().getSignList());

		cvSign2.setContentProvider(new ArrayContentProvider());
		cvSign2.setLabelProvider(new DictionaryLabelProvider());
		cvSign2.setInput(StargazerDataSet.getInstance().getSignList());
	}
	
	@Override
	protected void prepareView() {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(group);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);
		
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbPlanet);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbSign1);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(cmbSign2);
	}
	
	@Override
	protected void setListeners() {
		StateChangedListener listener = new StateChangedListener();
		cvPlanet.addSelectionChangedListener(listener);
		cvSign1.addSelectionChangedListener(listener);
		cvSign2.addSelectionChangedListener(listener);
	}
	
	@Override
	protected void syncView() {
		clear();
		setCodeEdit(true);
		if (model != null) {
			SynastryTextDictionary dict = (SynastryTextDictionary)model;
			if (dict.getPlanet() != null)
				cmbPlanet.setText(dict.getPlanet().getName());
			if (dict.getSign1() != null)
				cmbSign1.setText(dict.getSign1().getName());
			if (dict.getSign2() != null)
				cmbSign2.setText(dict.getSign2().getName());
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void clear() {
		setCodeEdit(true);
		cvPlanet.setSelection(null);
		cvSign1.setSelection(null);
		cvSign2.setSelection(null);
		setCodeEdit(false);
	}
	
	@Override
	public void syncModel() {
		if (model == null) return;
		SynastryTextDictionary dict = (SynastryTextDictionary)model;
		IStructuredSelection selection = (IStructuredSelection)cvPlanet.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setPlanet((Planet)selection.getFirstElement());
		selection = (IStructuredSelection)cvSign1.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setSign1((Sign)selection.getFirstElement());
		selection = (IStructuredSelection)cvSign2.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setSign2((Sign)selection.getFirstElement());
	}

	@Override
	public boolean check() {
		String msgBody = "";  //$NON-NLS-1$
		if (cvPlanet.getSelection().isEmpty())
			msgBody += lbPlanet.getText() + '\n';
		if (cvSign1.getSelection().isEmpty())
			msgBody += lbSign1.getText() + '\n';
		if (cvSign2.getSelection().isEmpty())
			msgBody += lbSign2.getText() + '\n';
		if (msgBody.length() > 0) {
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		} else 
			return true;
	}
}
