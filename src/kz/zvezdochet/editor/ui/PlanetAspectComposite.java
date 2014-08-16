package kz.zvezdochet.editor.ui;

import java.util.List;

import kz.zvezdochet.analytics.bean.PlanetAspectTextDictionary;
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
 * @author Nataly 
 */
public class PlanetAspectComposite extends PlanetSignComposite {
	
	@Override
	protected void decorate() {
		lbType.setText("Тип аспекта");
		lbObject1.setText("Планета");
		lbObject2.setText("Планета");
	}
	
	@Override
	protected void initializeControls() throws DataAccessException {
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
		setCodeEdit(true);
		if (model != null) {
			PlanetAspectTextDictionary dict = (PlanetAspectTextDictionary)model;
			if (dict.getType() != null)
				cmbType.setText(dict.getType().getName());
			if (dict.getPlanet1() != null)
				cmbObject1.setText(dict.getPlanet1().getName());
			if (dict.getPlanet2() != null)
				cmbObject2.setText(dict.getPlanet2().getName());
		} 
		setCodeEdit(false);
	}
	
	@Override
	public void viewToModel() {
		if (model == null) return;
		PlanetAspectTextDictionary dict = (PlanetAspectTextDictionary)model;
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
