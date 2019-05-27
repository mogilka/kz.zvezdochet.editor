package kz.zvezdochet.editor.ui;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

import kz.zvezdochet.analytics.bean.PlanetHouseText;
import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.bean.House;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.service.AspectTypeService;
import kz.zvezdochet.service.HouseService;
import kz.zvezdochet.service.PlanetService;

/**
 * Композит справочника "Планеты в астрологических домах"
 * @author Natalie Didenko
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
		cvType.setInput(new AspectTypeService().getList());

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
		if (model != null) {
			PlanetHouseText dict = (PlanetHouseText)model;
			if (dict.getAspectType() != null)
				cvType.getCombo().setText(dict.getAspectType().getName());
			if (dict.getPlanet() != null)
				cvObject1.getCombo().setText(dict.getPlanet().getName());
			if (dict.getHouse() != null)
				cvObject2.getCombo().setText(dict.getHouse().getName());
		} 
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		PlanetHouseText dict = (PlanetHouseText)model;
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
