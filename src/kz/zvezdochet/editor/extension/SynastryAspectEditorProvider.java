package kz.zvezdochet.editor.extension;

import kz.zvezdochet.analytics.service.SynastryAspectService;

import org.eclipse.swt.widgets.Table;

/**
 * Расширитель справочника Синастрические аспекты планет
 * @author Nataly Didenko
 */
public class SynastryAspectEditorProvider extends PlanetAspectEditorProvider {

	@Override
	public String getExtensionName() {
		return "Синастрические аспекты планет";
	}
	
	@Override
	public void createTableColumns(Table table) {
		String[] columns = {"ID", "Планета", "Тип аспекта", "Планета"};
		initTableColumns(table, columns);
	}

	@Override
	public Object getExtensionService() {
		return new SynastryAspectService();
	}
}
