package kz.zvezdochet.editor.ui;

import java.util.List;

import kz.zvezdochet.analytics.bean.PlanetAspectText;
import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.core.bean.Model;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.ui.provider.DictionaryLabelProvider;
import kz.zvezdochet.service.AspectService;
import kz.zvezdochet.service.PlanetService;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Композит справочника "Аспекты планет"
 * @author Nataly Didenko
 */
public class PlanetAspectComposite extends PlanetSignComposite {
	
	@Override
	protected void decorate() {
		lbType.setText("Тип аспекта");
		lbObject1.setText("Планета");
		lbObject2.setText("Планета");
	}
	
	@Override
	protected void initControls() throws DataAccessException {
		cvType.setContentProvider(new ArrayContentProvider());
		cvType.setLabelProvider(new DictionaryLabelProvider());
		cvType.setInput(new AspectService().getList());

		cvObject1.setContentProvider(new ArrayContentProvider());
		cvObject1.setLabelProvider(new DictionaryLabelProvider());
		List<Model> planets = new PlanetService().getList();
		cvObject1.setInput(planets);

		cvObject2.setContentProvider(new ArrayContentProvider());
		cvObject2.setLabelProvider(new DictionaryLabelProvider());
		cvObject2.setInput(planets);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			PlanetAspectText dict = (PlanetAspectText)model;
			if (dict.getType() != null)
				cvType.getCombo().setText(dict.getType().getName());
			if (dict.getPlanet1() != null)
				cvObject1.getCombo().setText(dict.getPlanet1().getName());
			if (dict.getPlanet2() != null)
				cvObject2.getCombo().setText(dict.getPlanet2().getName());
		} 
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		PlanetAspectText dict = (PlanetAspectText)model;
		IStructuredSelection selection = (IStructuredSelection)cvType.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setType((AspectType)selection.getFirstElement());
		selection = (IStructuredSelection)cvObject1.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setPlanet1((Planet)selection.getFirstElement());
		selection = (IStructuredSelection)cvObject2.getSelection();
		if (selection.getFirstElement() != null) 
			dict.setPlanet2((Planet)selection.getFirstElement());
	}
}
