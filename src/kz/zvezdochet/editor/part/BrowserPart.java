package kz.zvezdochet.editor.part;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;

import kz.zvezdochet.core.bean.TextGenderDictionary;
import kz.zvezdochet.core.ui.view.BrowserModelView;
import kz.zvezdochet.core.ui.view.View;

/**
 * Представление публикации для отображения в браузере
 * @author Natalie Didenko
 *
 */
public class BrowserPart extends BrowserModelView {

	@PostConstruct @Override
	public View create(Composite parent) {
		return super.create(parent);
	}

	@Override
	protected void syncView() {
		reset();
		if (null == model) return;
		if (model instanceof TextGenderDictionary) {
			TextGenderDictionary post = (TextGenderDictionary)model;
			if (post.getText() != null)
				browser.setText(post.getText());
//			/home/nataly/workspacercp/kz.zvezdochet.export/out/horoscope_files/horoscope.css
		}
	}

	@Override
	public void syncModel(int mode) throws Exception {}
	@Override
	public boolean check(int mode) throws Exception {
		return true;
	}
}
