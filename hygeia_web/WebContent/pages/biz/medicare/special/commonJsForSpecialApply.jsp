<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	var pageMap = {
			'default' : {'pageFlag':'', 
							'modifyPH': 400, 
							'detailPH': 620, 
							'auditPH': 550, 
							'auditFirstPH': 0, 
							'auditSecondPH': 0,
							'assiKey': ['aac003', 
										'aac004', 
										'aac002', 
										'aac001', 
										'aac006', 
										'bka035', 
										'aaz217', 
										'bke053', 
										'aaz286', 
										'bke051', 
										'bke046', 
										'ake014', 
										'aae140',
										'aab069',
										'aaa027']},
			'120' : {'pageFlag':'120', 'modifyPH': 410, 'detailPH': 620, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'12S' : {'pageFlag':'12S', 'modifyPH': 300, 'detailPH': 500, 'auditPH': 450, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'121' : {'pageFlag':'121', 'modifyPH': 400, 'detailPH': 620, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'122' : {'pageFlag':'122', 'modifyPH': 400, 'detailPH': 620, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'123' : {'pageFlag':'123', 'modifyPH': 580, 'detailPH': 800, 'auditPH': 730, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'125' : {'pageFlag':'125', 'modifyPH': 300, 'detailPH': 450, 'auditPH': 350, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'126' : {'pageFlag':'126', 'modifyPH': 550, 'detailPH': 800, 'auditPH': 750, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'131' : {'pageFlag':'13', 'modifyPH': 720, 'detailPH': 800, 'auditPH': 0, 'auditFirstPH': 820, 'auditSecondPH': 820},
			'132' : {'pageFlag':'13', 'modifyPH': 720, 'detailPH': 800, 'auditPH': 0, 'auditFirstPH': 820, 'auditSecondPH': 820},
			'133' : {'pageFlag':'13', 'modifyPH': 720, 'detailPH': 800, 'auditPH': 0, 'auditFirstPH': 820, 'auditSecondPH': 820},
			'134' : {'pageFlag':'134', 'modifyPH': 400, 'detailPH': 620, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'140' : {'pageFlag':'140', 'modifyPH': 400, 'detailPH': 620, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'165' : {'pageFlag':'165', 'modifyPH': 450, 'detailPH': 650, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'166' : {'pageFlag':'166', 'modifyPH': 350, 'detailPH': 570, 'auditPH': 0, 'auditFirstPH': 500, 'auditSecondPH': 450},
			'180' : {'pageFlag':'180', 'modifyPH': 580, 'detailPH': 800, 'auditPH': 0, 'auditFirstPH': 800, 'auditSecondPH': 700},
			'191' : {'pageFlag':'191', 'modifyPH': 400, 'detailPH': 600, 'auditPH': 0, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'200' : {'pageFlag':'200', 'modifyPH': 250, 'detailPH': 500, 'auditPH': 0, 'auditFirstPH': 480, 'auditSecondPH': 350},
			'201' : {'pageFlag':'201', 'modifyPH': 340, 'detailPH': 550, 'auditPH': 0, 'auditFirstPH': 480, 'auditSecondPH': 400},
			'202' : {'pageFlag':'202', 'modifyPH': 300, 'detailPH': 500, 'auditPH': 0, 'auditFirstPH': 450, 'auditSecondPH': 380},
			'203' : {'pageFlag':'203', 'modifyPH': 340, 'detailPH': 550, 'auditPH': 0, 'auditFirstPH': 480, 'auditSecondPH': 400},
			'206' : {'pageFlag':'206', 'modifyPH': 400, 'detailPH': 500, 'auditPH': 0, 'auditFirstPH': 450, 'auditSecondPH': 450},
			'207' : {'pageFlag':'207', 'modifyPH': 400, 'detailPH': 550, 'auditPH': 0, 'auditFirstPH': 480, 'auditSecondPH': 400},
			'208' : {'pageFlag':'208', 'modifyPH': 400, 'detailPH': 500, 'auditPH': 0, 'auditFirstPH': 450, 'auditSecondPH': 450},
			'213' : {'pageFlag':'213', 'modifyPH': 500, 'detailPH': 600, 'auditPH': 0, 'auditFirstPH': 0, 'auditSecondPH': 0},
			'500' : {'pageFlag':'500', 'modifyPH': 400, 'detailPH': 620, 'auditPH': 550, 'auditFirstPH': 0, 'auditSecondPH': 0},
	}
	
	
	//根据申请类型，获取页面跳转所需参数：pageFlag页面标志、modifyPageHeight修改页面高度、detailPageHeight详情页面高度
	function getPageInitParamByApplyType(aka083, key) {
		if(null != pageMap[aka083]) {
			return pageMap[aka083][key];
		} else {
			return pageMap['default'][key];
		}
	}
	
	
	//将所有申请类型选项的值用逗号分隔赋值给全部选项
	function dealwithAllAka083Option() {
		var allAka083 = '';
		$("#aka083 option").each(function(){
			if('' != $(this).val()) {
				allAka083 += $(this).val() + ',';
			}
		});
		$("#aka083 option[value='']").val(allAka083);
	}
</script>
</html>