<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.powersi.hygeia.framework.ParameterMapping"%>
<%
	String systemName = ParameterMapping.getStringByCode("center_org_name", ParameterMapping.getSystemName());
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="powersi" uri="http://www.powersi.com.cn/tags"%>

<powersi:html>
	<powersi:head title="显示部门树" />
	<script src="${strutsPath}/include/ztree.js" type="text/javascript"></script>
	<script type="text/javascript">
	var system_name = "<%=systemName%>";
    var _depList = [];
	    
   function getDepTree() {
        postJSON("${rootPath}/manager/DeptManager!getDeptTree.action",  showDepTee);
    }
    
    function showDepTee(json) {
    	if (!checkJSONResult(json)) {
            return;
        }
		
    	_depList.length = 0;
        _depList = json.data;
        buildeDepTree(_depList);
    }
	
	function buildeDepTree(menus){
	    try{
	    	var root = -1;
	    	var trees = [];
	    	trees.push({"id": 0, "pId": root, "name": system_name, "open": true, "font": {"color": "#f00"}, "iconSkin" :"home"});
	        if (powersi.isarray(menus)) {
	            for (var idx in menus) {
	                var menu = menus[idx];
	                var treeNode = {"id": menu.dept_id, "pId": menu.dept_up_id, "name": menu.dept_name};
	                treeNode["iconSkin"] = "dept";
	                if(menu.dept_type == "1"){
	                	treeNode["font"] = {"color": "#00f", "background-color":"transparent"};
	                }
	                trees.push(treeNode);
	            }//end for menus
	        }
	        
	       var setting = {
	    			view: {
		    			fontCss: getTreeFont,
	    				autoCancelSelected: false,
	    				selectedMulti: false,
	    				dblClickExpand: false,
	    				showLine: false,
	    				expandSpeed: 0
	    			},
	    			data: {
	    				simpleData: {
	    					enable: true,
	    					rootPId: root
	    				}
	    			},
					callback: {
						onClick: onTreeClick
					},
					check: {
							enable: true,
							chkStyle: "radio",
							radioType: "all",
							autoCheckTrigger: false
						}
	    	};
	        $.fn.zTree.init($("#tvUp"), setting, trees);
	        trees.length = 0;
	    } catch(e){
	    	alert(e.message);
	    }
	}
	
	   function onTreeClick(event, treeId, treeNode, clickFlag) {
	    	expandNode(treeId, treeNode);
			
	    	clickTree(treeNode.id);
		}
	
    function getTreeFont(treeId, node) {
    	return (!!node.highlight) ? {"background-color":"#ffff96"} : (node.font ? node.font : {"background-color":"transparent"});
	}
	
	function selDeps(){
    		var staffUpId = $('#dep_up_id').val();
    		var zTree = $.fn.zTree.getZTreeObj("tvUp");
    		zTree.expandAll(false);
    		
    		var node = zTree.getNodeByParam("id", staffUpId, null);
    		if(node == null){
    			alert('无法获取上级菜单');
    			return;
    		}
    		
    		zTree.checkNode(node, true, false, false);
    		if(node.isParent){
				zTree.expandNode(node, true, false, true, false);	
			} else {
   				zTree.expandNode(node.getParentNode(), true, false, true, false);
   			}
    		zTree.selectNode(node, false);
    		
    		$('body').block({ 
    			message: $('#divUpDep'),
    		 	css: { 
                    width: '400px', 
                    top: '10px', 
                    left: '', 
                    right: '10px', 
                    border: 'none', 
                    padding: '5px', 
                    backgroundColor: '#fff', 
                    "-moz-border-radius": '10px',
    				"-khtml-border-radius": '10px',
    				"-webkit-border-radius": '10px',
    				"border-radius": '10px', 
                    color: '#07519a' ,
                    border: '5px solid #cad5eb',
                    cursor: 'auto'
                },
                overlayCSS: {
                	backgroundColor: '#e6e6e6'
                }
    		}); 
	}
	    
    	function getSelectMenu(){
			var ids=[];
			var names = [];
			var nodes = $.fn.zTree.getZTreeObj("tvUp").getCheckedNodes(true);
			for(var idx in nodes){
				var node = nodes[idx];
				var parnode = node.getParentNode();
				if(parnode==null){
					ids.push("0:"+nodes[idx].id);
					names.push('['+system_name+']'+nodes[idx].name);
				}else{
					ids.push(nodes[idx].id);
					names.push(nodes[idx].name);
				}
			}
			
			var retObj = {
				"id":ids.join(","),
				"name":names.join(",")
			};
	        return retObj;
		}
	    
	    function onUpCancel(){
	    	hideUpMenu();
	    }
	    
	    function hideUpMenu() {
	    	$('body').unblock();
		}
	</script>
</powersi:html>