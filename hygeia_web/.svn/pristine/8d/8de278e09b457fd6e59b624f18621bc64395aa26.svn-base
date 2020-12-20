<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<powersi:html>
<head>
<powersi:head title="dubbo测试" />
<script type="text/javascript">
	function callChuangZhi(method){
	
		postAjax(rootPath + "/sample/Sample!callChuangZhi.action", {
			"method": method,
			"text": $("#okText1").val()
		}, function(ret){
			if(checkResult(ret)) {
				//alert(powersi.tostring(ret.data));
				  $.each(ret.data,
			    	      function(key, value) {
			    	        if($("#"+key).length > 0){ 
			    	           $("#"+ key).val(value);
			    	         }
				         } 
			     
			      );
				
				//callInsure();
			}
		});
	}
	
	function callInsure() {
			var	aae140 = '职工社会医疗保险';
			var	aac049 = '2016-03-20';
			var	aac066 = '在职人员 ';
			var	aac031 = '正常参保';
			var	aaa027 = '广州市本级';
			var	aab069 = '广州曼伯特机械设备有限公司';
			var	aae100 = '有效';
   	    $("#row>tbody tr").remove();
		$('#row tr').remove('.empty');
		$('#row>tbody').append("<tr><td>"+ aae140 +"</td><td>"
				+ aac049+ "</td><td>"
				+ aac066 + "</td><td>"
				+ aac031 + "</td><td>"
				+ aaa027 + "</td><td>"
				+ aab069 + "</td><td>"
				+ aae100 + "</td></tr>");
	}
	
</script>
</head>
<body>
	<powersi:groupbox title="查询条件">
			<powersi:editorlayout cols="6">
				<td class="tdInput" colspan="4"><input type="text" id="okText1"  style="" placeholder="输入文本" /></td>
				<td class="tdInput" colspan="1"><input type="button" value="查询" class="button" onclick="callChuangZhi('queryInjury')" /></td>
			</powersi:editorlayout>
	</powersi:groupbox>
	
	<br />
	  <powersi:groupbox title="事故信息">
            <powersi:div id="accshow">
              <powersi:editorlayout cols="8">
                <tr>
                  <powersi:textfield name="lc31.aab001" id="aab001" key="单位电脑号"
                    disabled="true"></powersi:textfield>
                  <powersi:textfield name="lc31.aab004" id="aab069" label="单位名称"
                    disabled="true" colspan="5"></powersi:textfield>
                </tr>
                <tr>
                  <powersi:textfield name="lca2.alc020" id="alc020"
                    disabled="true" key="工伤发生时间  " mask="datetime"></powersi:textfield>
                  <powersi:textfield name="lca2.alc026" id="alc026"
                    disabled="true" key="报告日期" mask="date"></powersi:textfield>
                  <powersi:textfield name="lca2.ala028" id="ala028"
                    disabled="true" codeType="ala028" key="事故类别  "></powersi:textfield>

                </tr>
                <tr>
                  <powersi:textfield name="lca2.plc105" id="plc105"
                    disabled="true" codeType="plc105" key="报告方式  "></powersi:textfield>
                  <powersi:textfield name="lca2.plk100" id="plk100" key="伤亡人数"
                    disabled="true"></powersi:textfield>
                  <powersi:textfield name="lca2.plk101" id="plk101" key="死亡人数"
                    disabled="true"></powersi:textfield>
                  <powersi:textfield name="lca2.plk102" id="plk102" key="事故发生地点"
                    disabled="true"></powersi:textfield>
                </tr>
                <tr>
                   <powersi:textarea name="lca2.plc103" colspan="7" id="plc103"
                    disabled="true" key="事故经过描述"></powersi:textarea>
                </tr>
                <tr>
                  <powersi:textfield name="lca2.ala023" id="ala023"
                    disabled="true" codeType="ala023" key="申请主体  "></powersi:textfield>
                  <powersi:textfield name="lca2.aac042" id="aac042" key="申报人姓名  "
                    disabled="true"></powersi:textfield>
                  <powersi:textfield name="lca2.aac045" id="aac045" key="申报人电话  "
                    disabled="true" validate="integer"></powersi:textfield>
                  <powersi:textfield name="lca2.aac047" id="aac047" key="申报人邮编  "
                    disabled="true" validate="zipcode"></powersi:textfield>
                </tr>
                <tr>
                  <powersi:textfield name="lca2.aac046" id="aac046" key="申报人地址"
                    disabled="true" colspan="8"></powersi:textfield>
                </tr>
              </powersi:editorlayout>
            </powersi:div>
          </powersi:groupbox>
          <powersi:groupbox title="个人申报信息">
            <powersi:editorlayout cols="6">
              <tr>
                <powersi:textfield name="lc30.aac001" id="aac001" key="个人电脑号"
                  disabled="true"></powersi:textfield>
                <powersi:textfield name="lc30.aac003" id="aac003" key="姓名"
                  disabled="true"></powersi:textfield>
                <powersi:textfield name="lc30.aac002" id="aac002" key="身份证号"
                  disabled="true"></powersi:textfield>
              </tr>
              <tr>
                <powersi:textfield name="lc30.alc005" id="alc005" key="工伤事故序列号"
                  disabled="true"></powersi:textfield>
                <powersi:textfield name="lc30.alc027" disabled="true"
                  id="alc027"  key="工伤类别  "></powersi:textfield>
                <powersi:textfield name="lc30.alc022" id="alc022"
                  disabled="true" codeType="alc022" key="伤害部位  "></powersi:textfield>
                <powersi:hidden name="lc30.aaz128" id="aaz128"></powersi:hidden>
                <powersi:hidden name="lc30.aac001" id="aac001t"></powersi:hidden>
              </tr>
              <tr id="alcViw">
                <powersi:textfield name="lc30.ala017" id="ala017"
                  disabled="true" codeType="ala017" key="职业病  "></powersi:textfield>
                <powersi:textfield id="alc007" colspan="4" name="lc30.alc007"
                  key="工伤就诊医院  " disabled="true" />
                <powersi:hidden id="plc516" name="lc30.plc516"></powersi:hidden>
              </tr>
              <tr>
                <powersi:textfield name="lc30.alc021" id="alc021"
                  disabled="true" codeType="alc021" label="伤害程度"></powersi:textfield>
                <powersi:textfield name="lc30.plc563" id="plc563"
                  disabled="true" key="工伤性质"></powersi:textfield>
                <powersi:textfield name="lc30.alc040" id="alc040" key="因工死亡（失踪）日期  "
                  disabled="true" mask="date"></powersi:textfield>
              </tr>
              <tr>
                <powersi:textfield name="lc30.aae004" id="aae004" label="联系人"
                  disabled="true"></powersi:textfield>
                <powersi:textfield name="lc30.aae005" id="aae005" label="联系电话"
                  disabled="true" validate="integer"></powersi:textfield>
                <powersi:textfield name="lc30.aae013" id="aae013" label="备注"
                  disabled="true"></powersi:textfield>
              </tr>
            </powersi:editorlayout>
          </powersi:groupbox>
          <powersi:groupbox title="认定结果">
            <powersi:editorlayout cols="6"
              cssStyle="width:550;table-layout:fixed;">
              <tr>
                <powersi:textfield name="lc31.alc031" id="alc031"
                  disabled="true" key="工伤认定日期  " mask="date"></powersi:textfield>
                <powersi:textfield name="lc31.alc029" id="alc029"
                  disabled="true" codeType="alc029" key="工伤认定标志  "></powersi:textfield>
                <powersi:textfield name="lc31.plc553" id="plc553"
                  disabled="true" codeType="plc553" key="工伤类型  "></powersi:textfield>
                <powersi:hidden name="lc31.aaz127" id="aaz127"></powersi:hidden>
              </tr>
              <tr>
                <powersi:textfield colspan="6" name="lc31.ala016"
                  disabled="true" id="ala016" key="认定依据类别  "></powersi:textfield>
              </tr>
              <tr>
                <powersi:textfield name="lc31.alc011" id="alc011" key="认定文书号  "
                  disabled="true"></powersi:textfield>
                <powersi:textfield name="lc31.aae060" id="aae060" key="初定工伤医疗期开始时间  "
                  mask="date" disabled="true"></powersi:textfield>
                <powersi:textfield name="lc31.aae061" id="aae061" key="初定工伤医疗期结束时间  "
                  mask="date" disabled="true"></powersi:textfield>
              </tr>

              <tr>
                <powersi:textfield name="lc31.alc030" disabled="true"
                  id="alc030" codeType="alc030" colspan="8" key="工伤认定单位  "></powersi:textfield>

              </tr>
              <tr>
                <powersi:hidden name="lc31.plc901" id="plc901" disabled="true"
                  label="伤残病种名称"></powersi:hidden>
                
              </tr>
              <tr>
                <powersi:textarea name="lc31.alc014" id="alc014" key="诊断结论 "
                  disabled="true" colspan="5"></powersi:textarea>
              </tr>
              <tr>
                <powersi:textarea name="lc31.plc723" colspan="5" id="plc723"
                  disabled="true" key="认定或者不予认定意见 同意 "></powersi:textarea>
                <powersi:hidden name="lc31.prseno" id="prseno"></powersi:hidden>
              </tr>
            </powersi:editorlayout>
          </powersi:groupbox>
	<powersi:errors />
</body>
</powersi:html>