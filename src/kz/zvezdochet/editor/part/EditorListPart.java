package kz.zvezdochet.editor.part;

import java.util.List;

import javax.annotation.PostConstruct;

import kz.zvezdochet.analytics.bean.AspectConfiguration;
import kz.zvezdochet.analytics.bean.CardKind;
import kz.zvezdochet.analytics.bean.CardType;
import kz.zvezdochet.analytics.bean.Category;
import kz.zvezdochet.analytics.bean.Cross;
import kz.zvezdochet.analytics.bean.Degree;
import kz.zvezdochet.analytics.bean.Element;
import kz.zvezdochet.analytics.bean.Halfsphere;
import kz.zvezdochet.analytics.bean.InYan;
import kz.zvezdochet.analytics.bean.PlanetAspectTextDictionary;
import kz.zvezdochet.analytics.bean.PlanetHouseTextDictionary;
import kz.zvezdochet.analytics.bean.PlanetSignTextDictionary;
import kz.zvezdochet.analytics.bean.Square;
import kz.zvezdochet.analytics.bean.SynastryTextDictionary;
import kz.zvezdochet.analytics.bean.Zone;
import kz.zvezdochet.bean.Aspect;
import kz.zvezdochet.bean.AspectType;
import kz.zvezdochet.bean.House;
import kz.zvezdochet.bean.Place;
import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.bean.PositionType;
import kz.zvezdochet.bean.Protraction;
import kz.zvezdochet.bean.Sign;
import kz.zvezdochet.core.bean.Dictionary;
import kz.zvezdochet.core.service.DataAccessException;
import kz.zvezdochet.core.service.IModelService;
import kz.zvezdochet.core.ui.extension.ExtensionUtil;
import kz.zvezdochet.core.ui.extension.ModelExtensionProvider;
import kz.zvezdochet.core.ui.view.ModelListView;
import kz.zvezdochet.editor.Activator;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * Представление списка справочника
 * @author Nataly Didenko
 *
 */
public class EditorListPart extends ModelListView {
	public static final String ID = EditorListPart.class.getCanonicalName();

	@PostConstruct @Override
	public Composite create(Composite parent) {
		extPointId = Activator.PLUGIN_ID + ".editorList";
		return super.create(parent);
	}
	
	@Override
	public void addModel(Object element) {
		getExtension();
	}

	@Override
	public void deleteModel(Object element) {
		getExtension();
	}

	@Override
	public void editModel(Object element) {
		getExtension();
	}
	
	ModelExtensionProvider extProvider;
	
	public IModelService getExtService() {
		return (extProvider != null) ? extProvider.getExtensionService() : null;
	}

	/**
	 * Код загружаемого справочника
	 */
	private String dictionary;
	
	public void setDictionary(String code) {
		this.dictionary = code;
		refreshData();
	}

	public String getDictionary() {
		return dictionary;
	}

	/**
	 * Инициализация справочника, соответствующего выбранному описанию 
	 */
	private void getExtension() {
		List<ModelExtensionProvider> providers = ExtensionUtil.getExtensionProviders(Activator.PLUGIN_ID + ".editorList"); 
		if (null == providers) return;
		for (ModelExtensionProvider provider : providers) {
			extProvider = provider;
			if (extProvider.canHandle(dictionary)) {
				extProvider.initTableColumns(table);
				try {
					extProvider.setModelList(table);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	@Override
	protected void initTable() {}

//	@Override
//	public Object getModel() { //TODO зачем этот оверайд?
//		TableItem[] items = table.getSelection();
//		return (items != null && items.length > 0) ? items[0] : null;
//	}

	public Object createElement(String code) { //TODO реализовать в подклассах?
		if (null == code) return null;
		if (code.equals("AspectConfigurations")) return new AspectConfiguration();
		if (code.equals("Aspects")) return new Aspect();
		if (code.equals("AspectTypes")) return new AspectType();
		if (code.equals("CardKinds")) return new CardKind();
		if (code.equals("CardTypes")) return new CardType();
		if (code.equals("Categories")) return new Category();
		if (code.equals("Crosses")) return new Cross();
		if (code.equals("Degrees")) return new Degree();
		if (code.equals("DirectionHouses")) return new PlanetHouseTextDictionary();
		if (code.equals("Elements")) return new Element();
		if (code.equals("Halfspheres")) return new Halfsphere();
		if (code.equals("Houses")) return new House();
		if (code.equals("InYan")) return new InYan();
		if (code.equals("Places")) return new Place();
		if (code.equals("PlanetAspects")) return new PlanetAspectTextDictionary();
		if (code.equals("Planets")) return new Planet();
		if (code.equals("PlanetHouses")) return new PlanetHouseTextDictionary();
		if (code.equals("PlanetSigns")) return new PlanetSignTextDictionary();
		if (code.equals("PositionTypes")) return new PositionType();
		if (code.equals("Protractions")) return new Protraction();
		if (code.equals("Signs")) return new Sign();
		if (code.equals("Squares")) return new Square();
		if (code.equals("SynastryAspects")) return new PlanetAspectTextDictionary();
		if (code.equals("SynastryHouses")) return new PlanetHouseTextDictionary();
		if (code.equals("SynastrySigns")) return new SynastryTextDictionary();
		if (code.equals("Zones")) return new Zone();
		return null;
	}

	@Override
	public void refreshData() {
		getExtension();
	}

	@Override
	protected IBaseLabelProvider getLabelProvider() {
		return new ModelLabelProvider() {
			private static final int COLUMN_NAME = 0;
			private static final int COLUMN_CODE = 1;
			
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				if (element instanceof Dictionary) {
					switch (columnIndex) {
					case COLUMN_NAME: return ((Dictionary)element).getName();
					case COLUMN_CODE: return ((Dictionary)element).getCode();
					}
				}
				return null;
			}
		};
	}
}
