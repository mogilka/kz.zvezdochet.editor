package kz.zvezdochet.editor.ui;

import kz.zvezdochet.core.bean.ITextGender;
import kz.zvezdochet.core.bean.TextGender;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.ui.util.GUIutil;
import kz.zvezdochet.core.ui.view.View;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Композит редактора справочника для моделей,
 * имеющих текст и гендерные описания
 * @author Nataly Didenko
 */
public class GenderTextComposite extends EditorComposite {
	private Text txText;
	private Text txMale;
	private Text txFemale;
	private Text txChild;
	private Section secText;
	private Section secMale;
	private Section secFemale;
	private Section secChild;

	@Override
	public View create(Composite parent) {
		group = new Group(parent, SWT.NONE);
		FormToolkit toolkit = new FormToolkit(group.getDisplay());
		secText = toolkit.createSection(group, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		secText.setText("Толкование");
//		secText.setExpanded(true);
		secText.setBackgroundMode(SWT.INHERIT_NONE);
		secText.setBackground(group.getBackground());
		txText = new Text(secText, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secText.setClient(txText);

		toolkit = new FormToolkit(group.getDisplay());
		secMale = toolkit.createSection(group, Section.TWISTIE | Section.TITLE_BAR);
		secMale.setText("Мужчина");
//		secMale.setExpanded(false);
		secMale.setBackgroundMode(SWT.INHERIT_NONE);
		secMale.setBackground(group.getBackground());
		txMale = new Text(secMale, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secMale.setClient(txMale);
		
		toolkit = new FormToolkit(group.getDisplay());
		secFemale = toolkit.createSection(group, Section.TWISTIE | Section.TITLE_BAR);
		secFemale.setText("Женщина");
//		secFemale.setExpanded(false);
		secFemale.setBackgroundMode(SWT.INHERIT_NONE);
		secFemale.setBackground(group.getBackground());
		txFemale = new Text(secFemale, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secFemale.setClient(txFemale);

		toolkit = new FormToolkit(group.getDisplay());
		secChild = toolkit.createSection(group, Section.TWISTIE | Section.TITLE_BAR);
		secChild.setText("Ребёнок");
//		secChild.setExpanded(false);
		secChild.setBackgroundMode(SWT.INHERIT_NONE);
		secChild.setBackground(group.getBackground());
		txChild = new Text(secChild, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		secChild.setClient(txChild);

		init(group);
		return this;
	}

	@Override
	protected void init(Composite composite) {
		GridLayoutFactory.swtDefaults().applyTo(composite);
		if (null == secText) return;
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			hint(SWT.DEFAULT, 200).grab(true, true).applyTo(secText);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			grab(true, true).applyTo(txText);

		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			hint(SWT.DEFAULT, 100).grab(true, true).applyTo(secMale);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			grab(true, true).applyTo(txMale);

		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			hint(SWT.DEFAULT, 100).grab(true, true).applyTo(secFemale);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			grab(true, true).applyTo(txFemale);

		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			hint(SWT.DEFAULT, 100).grab(true, true).applyTo(secChild);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).
			grab(true, true).applyTo(txChild);
	}

//	/**
//	 * Обработчик изменения состояния композита
//	 */
//	protected IEditorStateListener infoStateListener = null;

	@Override
	public boolean check(int mode) throws Exception {
		String msgBody = ""; //$NON-NLS-1$
		if (0 == txText.getText().length()) 
			msgBody += secText.getText();
		if (msgBody.length() > 0) { //$NON-NLS-1$
			DialogUtil.alertWarning(GUIutil.SOME_FIELDS_NOT_FILLED + msgBody);
			return false;
		}
		return true;
	}

	@Override
	protected void syncView() {		
		reset();
		if (null == model) return;
		ITextGender dict = (ITextGender)model;
		if (dict.getText() != null) {
			txText.setText(dict.getText());
			secText.setExpanded(txText.getText().length() > 0);
		}
		TextGender gtext = dict.getGenderTexts();
		if (gtext != null) {
			if (gtext.getText() != null) {
				txMale.setText(gtext.getText());
				secMale.setExpanded(txMale.getText().length() > 0);
			}
			if (gtext.getType() != null) {
				txFemale.setText(gtext.getType());
				secFemale.setExpanded(txFemale.getText().length() > 0);
			}
			if (gtext.getObjectId() != null) {
				txChild.setText(gtext.getObjectId());
				secChild.setExpanded(txChild.getText().length() > 0);
			}
		}
	}

	@Override
	public void syncModel(int mode) throws Exception {
		if (!check(mode)) return;
		ITextGender dict = (ITextGender)model;
		if (null == dict.getGenderTexts())
			dict.setGenderText(new TextGender());
		dict.getGenderTexts().setText(txMale.getText());
		dict.getGenderTexts().setType(txFemale.getText());
		dict.getGenderTexts().setObjectId(txChild.getText());
		dict.setText(txText.getText());
	}

	@Override
	public void reset() {
		txText.setText(""); //$NON-NLS-1$
		txMale.setText(""); //$NON-NLS-1$
		txFemale.setText(""); //$NON-NLS-1$
		txChild.setText(""); //$NON-NLS-1$
	}
}
