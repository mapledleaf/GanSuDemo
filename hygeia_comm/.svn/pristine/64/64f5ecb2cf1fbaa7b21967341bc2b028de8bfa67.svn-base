package com.powersi.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.comm.exception.ApusException;

/**
 * 事件
 * 
 * @author 刘勇
 *
 */
@Service
public class EventsServiceImpl implements EventsService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CommunalService communalService;
	@Autowired
	private DateService dateService;

	@Override
	public List<KE68> eventsDetails(KE67 ke67, Object oldObject, Object newObject) {
		List<KE68> ke68Rows = new ArrayList<KE68>();
		KE68 ke68 = null;
		Class<? extends Object> newObjectClass = newObject.getClass();
		Method[] methods = newObjectClass.getMethods();
		String aae013 = newObjectClass.getSimpleName();
		String newObjectColValue = null, oldObjectColValue = null;
		for (Method method : methods) {
			if (method.getName().equals("getClass")) {
				continue;
			}
			if (method.getName().startsWith("get", 0)) {
				try {
					newObjectColValue = method.invoke(newObject) == null ? ""
							: "java.util.Date".equals(method.getReturnType().getName())
									? this.dateService.dateToString((java.util.Date) method.invoke(newObject),
											DateService.DATETIME_FORMAT)
									: method.invoke(newObject).toString();
					oldObjectColValue = method.invoke(oldObject) == null ? ""
							: "java.util.Date".equals(method.getReturnType().getName())
									? this.dateService.dateToString((java.util.Date) method.invoke(oldObject),
											DateService.DATETIME_FORMAT)
									: method.invoke(oldObject).toString();
					if (!newObjectColValue.equals(oldObjectColValue)) {
						ke68 = new KE68();
						ke68.setBke586(this.communalService.uuid());
						ke68.setBke581(ke67.getBke581());
						ke68.setBke587(method.getName().replaceFirst("get", "").toLowerCase());
						ke68.setBke588(oldObjectColValue);
						ke68.setBke589(newObjectColValue);
						ke68.setAae013(aae013);
						ke68.setAae100("1");
						ke68Rows.add(ke68);
					}
				} catch (Exception e) {
					throw new ApusException(e);
				}
			}
		}
		return ke68Rows;
	}

}
