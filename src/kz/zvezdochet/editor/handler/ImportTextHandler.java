package kz.zvezdochet.editor.handler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import kz.zvezdochet.bean.Place;
import kz.zvezdochet.core.handler.Handler;
import kz.zvezdochet.core.ui.util.DialogUtil;
import kz.zvezdochet.core.util.DateUtil;
import kz.zvezdochet.core.util.PlatformUtil;
import kz.zvezdochet.editor.Activator;
import kz.zvezdochet.editor.part.ImportTextPart;
import kz.zvezdochet.service.PlaceService;

/**
 * Обработчик импорта толкований
 * @author Natalie Didenko
 */
public class ImportTextHandler extends Handler {
	@Inject
	private EPartService partService;

	@Execute
	public void execute() {
		try {
			updateStatus("Импорт", false);
			MPart part = partService.findPart("kz.zvezdochet.editor.part.importext");
			ImportTextPart importPart = (ImportTextPart)part.getObject();
			@SuppressWarnings("unchecked")
			List<Place> models = (List<Place>)importPart.getData();

			StringBuffer log = new StringBuffer();
			log.append(DateUtil.formatDateTime(new Date()) + "\n\n");

			int imported = 0, updated = 0;
			PlaceService service = new PlaceService();
			for (Place place : models) {
				Place back = (Place)service.find(place.getId());
				if (null == back || null == back.getId()) { //оригинальная запись не найдена, создаём
					service.create(place);
					++imported;
					log.append("Новый добавлен: " + place.toLog() + "\n");
				} else { //оригинальная запись найдена
					back.setName(place.getName());
					back.setCode(place.getCode());
					back.setDescription(place.getDescription());
					back.setGreenwich(place.getGreenwich());
					back.setLatitude(place.getLatitude());
					back.setLongitude(place.getLongitude());
					back.setParentid(place.getParentid());
					back.setType(place.getType());
					service.save(back);
					log.append("Старый обновлён: " + back.toLog() + "\n");
					++updated;
				}
			}
			//логируем
			log.append("Добавлено: " + imported + "\t");
			log.append("Обновлено: " + updated + "\n\n");

			String datafile = PlatformUtil.getPath(Activator.PLUGIN_ID, "/out/importext.log").getPath(); //$NON-NLS-1$
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(datafile, true), "UTF-8"));
			writer.append(log);
			writer.close();
			//TODO показывать диалог, что документ сформирован
			//а ещё лучше открывать его
			System.out.println("Импорт завершён");
			updateStatus("Импорт завершён", false);
		} catch (Exception e) {
			DialogUtil.alertError(e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void importAspectConfigurations(MPart part) {
//		try {
//			updateStatus("Поиск конфигураций аспектов", false);
//			Date date = new AspectConfigurationService().getLastModified();
//			if (null == date)
//				return;
//			long time = date.getTime() / 1000;
//
//			String url = String.format("https://zvezdochet.guru/aspectconfiguration/export?datetime=%d", time);
//			System.out.println(url);
//			String res = IOUtil.getUriContent(url);
//			if (null == res)
//				return;
//
//			JSONObject obj = new JSONObject(res);
//			boolean success = obj.getBoolean("success");
//			if (!success) {
//				DialogUtil.alertInfo(obj.getString("message"));
//				return;
//			}
//
//			List<Model> models = new ArrayList<Model>();
//			JSONArray list = obj.getJSONArray("models");
//			int count = list.length();
//			for (int i = 0; i < count; i++) {
//				JSONObject object = list.getJSONObject(i);
//				Model model = new AspectConfiguration(object);
//				if (model != null)
//					models.add(model);
//			}
//			
//			updateStatus("Импорт конфигураций аспектов", false);
//			StringBuffer log = new StringBuffer();
//			log.append(DateUtil.formatDateTime(new Date()) + "\n\n");
//
//			int imported = 0, updated = 0;
//			AspectConfigurationService service = new AspectConfigurationService();
//			for (Model model : models) {
//				AspectConfiguration back = (AspectConfiguration)service.find(model.getId());
//				if (null == back || null == back.getId()) { //оригинальная запись не найдена, создаём
//					service.create((AspectConfiguration)model);
//					++imported;
//					log.append("Новый добавлен: " + model.toLog() + "\n");
//				} else { //оригинальная запись найдена
//					back.setName(model.getName());
//					back.setCode(model.getCode());
//					back.setDescription(model.getDescription());
//					back.setGreenwich(model.getGreenwich());
//					back.setLatitude(model.getLatitude());
//					back.setLongitude(model.getLongitude());
//					back.setParentid(model.getParentid());
//					back.setType(model.getType());
//					service.save(back);
//					log.append("Старый обновлён: " + back.toLog() + "\n");
//					++updated;
//				}
//			}
//			//логируем
//			log.append("Добавлено: " + imported + "\t");
//			log.append("Обновлено: " + updated + "\n\n");
//
//			String datafile = PlatformUtil.getPath(Activator.PLUGIN_ID, "/out/importplace.log").getPath(); //$NON-NLS-1$
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(datafile, true), "UTF-8"));
//			writer.append(log);
//			writer.close();
//			//TODO показывать диалог, что документ сформирован
//			//а ещё лучше открывать его
//			System.out.println("Импорт завершён");
//			updateStatus("Импорт завершён", false);
//		} catch (Exception e) {
//			DialogUtil.alertError(e.getMessage());
//			e.printStackTrace();
//		}
	}
}
