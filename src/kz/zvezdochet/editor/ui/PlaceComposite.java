package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.listener.NumberInputListener;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.View;
import kz.zvezdochet.core.util.CalcUtil;
import kz.zvezdochet.part.Messages;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Композит АТЕ
 * @author Nataly Didenko
 */
public class PlaceComposite extends EditorComposite {
	private Text txLatitude;
	private Text txLongitude;
	private Text txGreenwich;
	private Label lbLatitude;
	private Label lbLongitude;
	
	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("Местность");
		lbLatitude = new Label(group, SWT.NONE);
		lbLatitude.setText(Messages.getString("PersonView.Latitude")); //$NON-NLS-1$
		txLatitude = new Text(group, SWT.BORDER);
		new RequiredDecoration(lbLatitude, SWT.TOP | SWT.RIGHT);

		lbLongitude = new Label(group, SWT.NONE);
		lbLongitude.setText(Messages.getString("PersonView.Longitude")); //$NON-NLS-1$
		txLongitude = new Text(group, SWT.BORDER);
		new RequiredDecoration(lbLongitude, SWT.TOP | SWT.RIGHT);

		Label lb = new Label(group, SWT.NONE);
		lb.setText(Messages.getString("PersonView.Greenwith")); //$NON-NLS-1$
		txGreenwich = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		
		decorate();
		init(group);
		syncView();
		return this;
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txLatitude);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txLongitude);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txGreenwich);

		StateChangedListener listener = new StateChangedListener();
		txLatitude.addModifyListener(listener);
		txLatitude.addListener(SWT.Verify, new NumberInputListener());
		txLongitude.addModifyListener(listener);
		txLongitude.addListener(SWT.Verify, new NumberInputListener());
		txLongitude.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				calcGreenwich();
			}
		});
	}
	
	/**
	 * Вычисление разницы с Гринвичем
	 */
	private void calcGreenwich() {
		if (txLongitude.getText().length() == 0) return;
		double lon = CalcUtil.degToDec(Double.parseDouble(txLongitude.getText()));
		/*
		 * Скорость вращения Земли с запада на восток составляет примерно
		 *  15 градусов в час (1 градус в 4 минуты, 15' в минуту).
		 */
		final double EARTH_MIN_PER_DEG = 4.0;
		double greenwich = CalcUtil.decToDeg(lon * EARTH_MIN_PER_DEG / 60);
		txGreenwich.setText(String.valueOf(greenwich));
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			Place place = (Place)model;
			txLatitude.setText(CalcUtil.formatNumber("###.##", place.getLatitude())); //$NON-NLS-1$
			txLongitude.setText(CalcUtil.formatNumber("###.##", place.getLongitude())); //$NON-NLS-1$
			txGreenwich.setText(CalcUtil.formatNumber("###.##", place.getGreenwich())); //$NON-NLS-1$
		} 
	}
	
	@Override
	public void reset() {
		txLatitude.setText("");
		txLongitude.setText("");
		txGreenwich.setText("");
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		Place place = (Place)model;
		place.setLatitude(Double.parseDouble(txLatitude.getText()));
		place.setLongitude(Double.parseDouble(txLongitude.getText()));
		place.setGreenwich(Double.parseDouble(txGreenwich.getText()));
	}

	@Override
	public boolean check(int mode) throws Exception {
		String msgBody = ""; //$NON-NLS-1$
		if (txLatitude.getText().length() == 0) 
			msgBody += lbLatitude.getText();
		if (txLongitude.getText().length() == 0) 
			msgBody += lbLongitude.getText();
		if (msgBody.length() > 0) { //$NON-NLS-1$
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		}
		return true;
	}
}
