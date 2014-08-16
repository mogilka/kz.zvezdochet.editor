package kz.zvezdochet.editor.ui;

import kz.zvezdochet.analytics.bean.PlanetHouseTextDictionary;
import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.bean.House;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.service.AspectService;
import kz.zvezdochet.service.HouseService;
import kz.zvezdochet.service.PlanetService;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Композит справочника "Планеты в астрологических домах"
 * @author Nataly 
 */
public class PlanetHouseComposite extends PlanetSignComposite {
	
	@Override
	protected void decorate() {
		lbType.setText("Сочетание");
		lbObject1.setText("Планета");
		lbObject2.setText("Дом");
	}
	
	@Override
	protected void initControls() throws DataAccessException {
		cvType.setContentProvider(new ArrayContentProvider());
		cvType.setLabelProvider(new DictionaryLabelProvider());
		cvType.setInput(new AspectService().getList());

		cvObject1.setContentProvider(new ArrayContentProvider());
		cvObject1.setLabelProvider(new DictionaryLabelProvider());
		cvObject1.setInput(new PlanetService().getList());

		cvObject2.setContentProvider(new ArrayContentProvider());
		cvObject2.setLabelProvider(new DictionaryLabelProvider());
		cvObject2.setInput(new HouseService().getList());
	}
	
	@Override
	protected void syncView() {
		reset();
		setCodeEdit(true);
		if (model != null) {
			PlanetHouseTextDictionary dict = (PlanetHouseTextDictionary)model;
			if (dict.getAspectType() != null)
				cmbType.setText(dict.getAspectType().getName());
			if (dict.getPlanet() != null)
				cmbObject1.setText(dict.getPlanet().getName());
			if (dict.getHouse() != null)
				cmbObject2.setText(dict.getHouse().getName());
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void viewToModel() {
		if (model == null) return;
		PlanetHouseTextDictionary dict = (PlanetHouseTextDictionary)model;
		IStructuredSelection selection = (IStructuredSelection)cvType.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setAspectType((AspectType)selection.getFirstElement());
		selection = (IStructuredSelection)cvObject1.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setPlanet((Planet)selection.getFirstElement());
		selection = (IStructuredSelection)cvObject2.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setHouse((House)selection.getFirstElement());
	}
}
