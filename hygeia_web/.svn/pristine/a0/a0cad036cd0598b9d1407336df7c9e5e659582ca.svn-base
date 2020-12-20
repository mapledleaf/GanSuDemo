package com.powersi.ssm.biz.medicare.hosp.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.hosp.pojo.TestDTO;
import com.powersi.biz.medicare.hosp.service.db.TestService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

@Action(value = "TestAction", results = {
		@Result(name = "success", location = "/pages/biz/medicare/hosp/HospAreaManage.jsp"),
		@Result(name = "areaNew", location = "/pages/biz/medicare/hosp/HospAreaNew.jsp") })
public class TestAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private TestDTO testDTO;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;

	public String queryTest() {
		PagerHelper.initPagination(getRequest());
		if (testDTO == null) {
			testDTO = new TestDTO();
		}
		String akb020 = BizHelper.getAkb020();
		TestService service = hygeiaServiceManager.getBeanByClass(TestService.class, akb020);
		List<TestDTO> list = service.queryTest01(akb020, testDTO);

		DataGridHelper.render(getRequest(), getResponse(), list);

		return NONE;
	}

	public TestDTO getTestDTO() {
		return testDTO;
	}

	public void setTestDTO(TestDTO testDTO) {
		this.testDTO = testDTO;
	}

}
