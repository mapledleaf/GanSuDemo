<%@ tag language="java" pageEncoding="UTF-8" display-name="刷新医保中心拨付码表至结算云" description="刷新医保中心拨付码表至结算云"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<script type="text/javascript">
	$(function() {
		$("#refresh_pay_codetype").click(function() {
			popupConfirm(this.title + "需要刷新当前页面，确定需要刷新吗？", "确认", function(isOk) {
				if (isOk) {
					postJSON("${rootPath}/medicarepay/accountDeclare!refreshCenterCodetype.action", null, function(ret) {
						if (ret.errortype > 0) {
							popupAlert(ret.message, "提示", "error");
						} else {
							setTimeout(function() {
								location.reload();
							}, 100);
						}
					});
				}
			});
		});
	});
</script>
<i class="icon-refresh" id="refresh_pay_codetype" style="cursor: pointer;" title="刷新医保中心拨付码表至结算云"></i>
&nbsp;&nbsp;
