package kz.zvezdochet.editor.ui;

import kz.zvezdochet.bean.Planet;
import kz.zvezdochet.core.ui.decoration.RequiredDecoration;
import kz.zvezdochet.core.ui.listener.NumberInputListener;
import kz.zvezdochet.core.ui.view.View;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Композит планеты
 * @author Natalie Didenko
 */
public class PlanetComposite extends EditorComposite {
	private Text txScore;
	private Label lbScore;
	private Section secSword;
	private Section secShield;
	private Section secBelt;
	private Section secKernel;
	private Section secMine;
	private Section secStrong;
	private Section secWeak;
	private Section secDamaged;
	private Section secPerfect;
	private Section secRetro;
	private Composite cmpSword;
	private Composite cmpShield;
	private Composite cmpBelt;
	private Composite cmpKernel;
	private Composite cmpMine;
	private Composite cmpStrong;
	private Composite cmpWeak;
	private Composite cmpDamaged;
	private Composite cmpPerfect;
	private Composite cmpRetro;
	private Text txSword;
	private Text txShield;
	private Text txBelt;
	private Text txKernel;
	private Text txMine;
	private Text txStrong;
	private Text txWeak;
	private Text txDamaged;
	private Text txPerfect;
	private Text txRetro;
	private Spinner spNumber;
	private Button btFictitious;
	
	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		group.setText("Планета");
		Label lb = new Label(group, SWT.NONE);
		lb.setText("Порядковый номер");
		spNumber = new Spinner(group, SWT.BORDER);
		spNumber.setMinimum(1);
		spNumber.setMaximum(36);
		new RequiredDecoration(lb, SWT.TOP | SWT.RIGHT);

		lbScore = new Label(group, SWT.NONE);
		lbScore.setText("Очки");
		txScore = new Text(group, SWT.BORDER);
		new RequiredDecoration(lbScore, SWT.TOP | SWT.RIGHT);

		btFictitious = new Button(group, SWT.BORDER | SWT.CHECK);
		btFictitious.setText("Фиктивная планета");
		
		FormToolkit toolkit = new FormToolkit(group.getDisplay());
		secSword = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secSword.setText("Меч");
		secSword.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secSword.setBackgroundMode(SWT.INHERIT_NONE);
		secSword.setBackground(parent.getBackground());
		cmpSword = new Composite(secSword, SWT.NONE);
		txSword = new Text(cmpSword, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secSword.setClient(cmpSword);
		
		toolkit = new FormToolkit(group.getDisplay());
		secShield = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secShield.setText("Щит");
		secShield.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secShield.setBackgroundMode(SWT.INHERIT_NONE);
		secShield.setBackground(parent.getBackground());
		cmpShield = new Composite(secShield, SWT.NONE);
		txShield = new Text(cmpShield, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secShield.setClient(cmpShield);
		
		toolkit = new FormToolkit(group.getDisplay());
		secBelt = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secBelt.setText("Пояс");
		secBelt.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secBelt.setBackgroundMode(SWT.INHERIT_NONE);
		secBelt.setBackground(parent.getBackground());
		cmpBelt = new Composite(secBelt, SWT.NONE);
		txBelt = new Text(cmpBelt, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secBelt.setClient(cmpBelt);
		
		toolkit = new FormToolkit(group.getDisplay());
		secKernel = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secKernel.setText("Ядро");
		secKernel.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secKernel.setBackgroundMode(SWT.INHERIT_NONE);
		secKernel.setBackground(parent.getBackground());
		cmpKernel = new Composite(secKernel, SWT.NONE);
		txKernel = new Text(cmpKernel, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secKernel.setClient(cmpKernel);
		
		toolkit = new FormToolkit(group.getDisplay());
		secMine = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secMine.setText("Шахта");
		secMine.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secMine.setBackgroundMode(SWT.INHERIT_NONE);
		secMine.setBackground(parent.getBackground());
		cmpMine = new Composite(secMine, SWT.NONE);
		txMine = new Text(cmpMine, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secMine.setClient(cmpMine);
		
		toolkit = new FormToolkit(group.getDisplay());
		secStrong = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secStrong.setText("Сила");
		secStrong.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secStrong.setBackgroundMode(SWT.INHERIT_NONE);
		secStrong.setBackground(parent.getBackground());
		cmpStrong = new Composite(secStrong, SWT.NONE);
		txStrong = new Text(cmpStrong, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secStrong.setClient(cmpStrong);
		
		toolkit = new FormToolkit(group.getDisplay());
		secWeak = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secWeak.setText("Слабость");
		secWeak.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secWeak.setBackgroundMode(SWT.INHERIT_NONE);
		secWeak.setBackground(parent.getBackground());
		cmpWeak = new Composite(secWeak, SWT.NONE);
		txWeak = new Text(cmpWeak, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secWeak.setClient(cmpWeak);
		
		toolkit = new FormToolkit(group.getDisplay());
		secDamaged = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secDamaged.setText("Пораженность");
		secDamaged.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secDamaged.setBackgroundMode(SWT.INHERIT_NONE);
		secDamaged.setBackground(parent.getBackground());
		cmpDamaged = new Composite(secDamaged, SWT.NONE);
		txDamaged = new Text(cmpDamaged, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secDamaged.setClient(cmpDamaged);
		
		toolkit = new FormToolkit(group.getDisplay());
		secPerfect = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secPerfect.setText("Непораженность");
		secPerfect.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secPerfect.setBackgroundMode(SWT.INHERIT_NONE);
		secPerfect.setBackground(parent.getBackground());
		cmpPerfect = new Composite(secPerfect, SWT.NONE);
		txPerfect = new Text(cmpPerfect, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secPerfect.setClient(cmpPerfect);
		
		toolkit = new FormToolkit(group.getDisplay());
		secRetro = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secRetro.setText("Ретроград");
		secRetro.setExpanded(false);
		//secEvent.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		secRetro.setBackgroundMode(SWT.INHERIT_NONE);
		secRetro.setBackground(parent.getBackground());
		cmpRetro = new Composite(secRetro, SWT.NONE);
		txRetro = new Text(cmpRetro, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secRetro.setClient(cmpRetro);
		
		decorate();
		init(group);
		syncView();
		return this;
	}
	
	@Override
	protected void init(Composite composite) {
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(composite);
		GridLayoutFactory.swtDefaults().applyTo(cmpSword);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpSword);
		GridLayoutFactory.swtDefaults().applyTo(cmpShield);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpShield);
		GridLayoutFactory.swtDefaults().applyTo(cmpBelt);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpBelt);
		GridLayoutFactory.swtDefaults().applyTo(cmpKernel);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpKernel);
		GridLayoutFactory.swtDefaults().applyTo(cmpMine);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpMine);
		GridLayoutFactory.swtDefaults().applyTo(cmpStrong);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpStrong);
		GridLayoutFactory.swtDefaults().applyTo(cmpWeak);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpWeak);
		GridLayoutFactory.swtDefaults().applyTo(cmpDamaged);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpDamaged);
		GridLayoutFactory.swtDefaults().applyTo(cmpPerfect);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpPerfect);
		GridLayoutFactory.swtDefaults().applyTo(cmpRetro);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cmpRetro);
		
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secSword);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secShield);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secBelt);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secKernel);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secMine);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secStrong);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secWeak);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secDamaged);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secPerfect);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(3, 1).grab(true, true).applyTo(secRetro);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			span(2, 1).grab(false, false).applyTo(txScore);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			span(2, 1).grab(false, false).applyTo(spNumber);
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).
			span(3, 1).grab(false, false).applyTo(btFictitious);
		
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			grab(true, false).applyTo(txScore);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txSword);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txShield);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txBelt);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txKernel);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txMine);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txStrong);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txWeak);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txDamaged);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txPerfect);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).
			hint(SWT.DEFAULT, 100).grab(true, false).applyTo(txRetro);

		StateChangedListener listener = new StateChangedListener();
		txScore.addModifyListener(listener);
		txScore.addListener(SWT.Verify, new NumberInputListener());
		txSword.addModifyListener(listener);
		txShield.addModifyListener(listener);
		txBelt.addModifyListener(listener);
		txKernel.addModifyListener(listener);
		txMine.addModifyListener(listener);
		txStrong.addModifyListener(listener);
		txWeak.addModifyListener(listener);
		txDamaged.addModifyListener(listener);
		txPerfect.addModifyListener(listener);
		txRetro.addModifyListener(listener);
		spNumber.addModifyListener(listener);
		btFictitious.addSelectionListener(listener);
	}
	
	@Override
	protected void syncView() {
		reset();
		if (model != null) {
			Planet planet = (Planet)model;
			txScore.setText(String.valueOf(planet.getScore()));
//			if (planet.getSwordText() != null)
//				txSword.setText(planet.getSwordText());
//			if (planet.getShieldText() != null)
//				txShield.setText(planet.getShieldText());
//			if (planet.getBeltText() != null)
//				txBelt.setText(planet.getBeltText());
//			if (planet.getKernelText() != null)
//				txKernel.setText(planet.getKernelText());
//			if (planet.getMineText() != null)
//				txMine.setText(planet.getMineText());
//			if (planet.getStrongText() != null)
//				txStrong.setText(planet.getStrongText());
//			if (planet.getWeakText() != null)
//				txWeak.setText(planet.getWeakText());
//			if (planet.getDamagedText() != null)
//				txDamaged.setText(planet.getDamagedText());
//			if (planet.getPerfectText() != null)
//				txPerfect.setText(planet.getPerfectText());
//			if (planet.getRetroText() != null)
//				txRetro.setText(planet.getRetroText());
			spNumber.setSelection(planet.getNumber());
			btFictitious.setSelection(planet.isFictitious());
		} 
	}
	
	@Override
	public void reset() {
		txScore.setText("");
		txSword.setText("");
		txShield.setText("");
		txBelt.setText("");
		txKernel.setText("");
		txMine.setText("");
		txStrong.setText("");
		txWeak.setText("");
		txDamaged.setText("");
		txPerfect.setText("");
		txRetro.setText("");
		spNumber.setSelection(0);
		btFictitious.setSelection(false);
	}
	
	@Override
	public void syncModel(int mode) {
		if (null == model) return;
		Planet planet = (Planet)model;
		planet.setScore(Double.parseDouble(txScore.getText()));
//		planet.setSwordText(txSword.getText());
//		planet.setShieldText(txShield.getText());
//		planet.setBeltText(txBelt.getText());
//		planet.setKernelText(txKernel.getText());
//		planet.setMineText(txMine.getText());
//		planet.setStrongText(txStrong.getText());
//		planet.setWeakText(txWeak.getText());
//		planet.setDamagedText(txDamaged.getText());
//		planet.setPerfectText(txPerfect.getText());
//		planet.setRetroText(txRetro.getText());
		planet.setNumber(spNumber.getSelection());
		planet.setFictitious(btFictitious.getSelection());
	}

	@Override
	public boolean check(int mode) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
