/*package com.powersi.biz.bizutil.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.powersi.biz.bizutil.service.BizAuditService;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

*//**
 * 业务疑点申诉或审核公共action
* @Description: TODO() 
* @author tanrui
* @date 2016年7月19日 下午3:34:27 
*
 *//*
@SuppressWarnings("all")
public class BizAuditAction extends BaseAction {
	
	private static final long serialVersionUID = -9110131411463861131L;

	private static BizAuditService service = BeanHelper.getBean(BizAuditService.class);
	
	*//**
	 * 获取业务违反的规则信息
	* @Description: TODO() 
	* @return
	* @date 2016年7月22日 上午10:06:14 
	* @author tanrui
	 *//*
	public String getViolateRules(){
		try {
			List<Map<String, Object>> list = service.searchViolateRules(getAllParameters());
			renderJson(list);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 手工疑点录入
	* @Description: TODO() 
	* @return
	* @date 2016年9月22日 上午10:58:53 
	* @author tanrui
	 *//*
	public String saveDoubtInfo(){
		try {
			service.saveDoubtInfo(getAllParameters());
			setJSONReturn("疑点录入成功!");
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	*//**
	 * @date 2016年11月4日 上午9:07:53 
	 * @author liuhao
	 * @return
	 *//*
	public String queryDoubtInfo(){
		try {
			Map m =this.getAllParameters();
			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(service.queryDoubtMasagge(m)));
		} catch (Exception e) {
			saveJSONError(e);
			return ERROR;
		}
		return NONE;
	}
	
	*//**
	 * @date 2016年11月7日 上午10:28:53 
	 * @author liuhao
	 * @return
	 *//*
	public String queryDoctorName(){
		try {
			String aaz263 = this.getParameter("aaz263");
            String DoctorName =  service.queryDoctorName(aaz263);
            this.setJSONReturn(DoctorName);
		} catch (Exception e) {
			saveJSONError(e);
			return ERROR;
		}
		return NONE;
	}
	
	*//**
	 * @date 2016年11月7日 上午10:28:53 
	 * @author liuhao
	 * @return
	 *//*
	public String queryHospotleName(){
		try {
			String aaz107 = this.getParameter("aaz107");
            String HospotleName =  service.queryHospotleName(aaz107);
            this.setJSONReturn(HospotleName);
		} catch (Exception e) {
			saveJSONError(e);
			return ERROR;
		}
		return NONE;
	}
}
*/