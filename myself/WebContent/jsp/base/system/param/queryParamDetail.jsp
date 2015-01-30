<%@ page language="java" contentType="text/html; charset=UTF-8"%><%@ include file="/jsp/base/core/taglibs.jsp"%><html>	<head>		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">		<script type="text/javascript">			var grid,quer;	        //表格	        $(function (){	        	init_page();	            grid = $("#maingrid").ligerGrid({	                height:'99%',enabledSort:false,usePager:false,rownumbers:true,	                columns: [	                	{ display: '编号', name: 'DETAIL_ID', align: 'left', width: 150 },	                	{ display: '名称', name: 'DETAIL_NAME', align: 'left', width: 250 },	                	{ display: 'KEY', name: 'DETAIL_KEY', align: 'left', width: 150 },	                	{ display: 'VALUE', name: 'DETAIL_VALUE', align: 'left', width: 250 }	                ], 	                url:"${ctx}/ParamDetailAction.action?method=getParamDetailJSON&paramId=${paramId}", 	                toolbar: { items: [	                	{ line: true },	                	{ text: '增加', click: itemclick, icon: 'add' },	                	{ line: true },	                	{ text: '修改', click: itemclick, icon: 'modify' },	                	{ line: true },	                	{ text: '删除', click: itemclick, icon: 'delete' },	                	{ line: true },	                	{ text: '返回', click: itemclick, icon: 'back' }	                ]}	            });	            	            $("#pageloading").hide();	        });	        			function itemclick(item){	            var text = item.text;	            if(text == '增加'){	            	liger.get("detailName").setValue("");	            	liger.get("detailKey").setValue("");	            	liger.get("detailValue").setValue("");	            		            	ligerDialog_open(text,$("#target"),"addOK","","");	            }else if(text == '修改'){	            	var row = grid.getSelectedRow();            		if (!row) { warn_alert('请选择行'); return; }					$("#detailId").val(row.DETAIL_ID);					liger.get("detailName").setValue(row.DETAIL_NAME);	            	liger.get("detailKey").setValue(row.DETAIL_KEY);	            	liger.get("detailValue").setValue(row.DETAIL_VALUE);            		ligerDialog_open(text,$("#target"),"editOK","","");	            }else if(text == '删除'){	            	var row = grid.getSelectedRow();            		if (!row) { warn_alert('请选择行'); return; }            		$.ligerDialog.confirm('是否删除参数明细？', function (isOk) {            			if(isOk){		            		$.post("${ctx}/ParamDetailAction.action?method=deleteParamDetail&anticache="									+ Math.floor(Math.random() * 10000), {								id : row.DETAIL_ID							}, function(info) {								if (info.info == undefined) {									info = eval("(" + info + ")");								}								if (info.info == 'ok') {									grid.reload();								} else {									error_alert("${ctx}","删除参数明细失败","编号："+row.DETAIL_ID);								}							});						}					});            			            }else if(text == '返回'){	            	window.location.href="${ctx}/ParamAction.action?method=gotoParamList";	            }	        }			//新增点击确定按钮			function addOK(item, dialog){				var param = {					paramId : $("#paramId").val(),					detailName : liger.get("detailName").getValue(),					detailKey : liger.get("detailKey").getValue(),					detailValue : liger.get("detailValue").getValue()				};				$.post("${ctx }/ParamDetailAction.action?method=saveParamDetailAdd&anticache="						+ Math.floor(Math.random() * 10000), param, function(info) {					if (info.info == undefined) {						info = eval("(" + info + ")");					}					if (info.info == 'ok') {	            		grid.reload();	            		dialog.hidden();					} else {						error_alert("${ctx}","新增参数明细失败",mapToString(param));					}				});			}			//编辑点击确定按钮			function editOK(item, dialog){				var param = {					detailId : $("#detailId").val(),					detailName : liger.get("detailName").getValue(),					detailKey : liger.get("detailKey").getValue(),					detailValue : liger.get("detailValue").getValue()				};				$.post("${ctx }/ParamDetailAction.action?method=saveParamDetailEdit&anticache="						+ Math.floor(Math.random() * 10000), param, function(info) {					if (info.info == undefined) {						info = eval("(" + info + ")");					}					if (info.info == 'ok') {	            		grid.reload();	            		dialog.hidden();					} else {						error_alert("${ctx}","修改参数明细失败",mapToString(param));					}				});			}		</script>	</head>	<body style="overflow-x:hidden; padding:2px;">		<div class="l-loading" style="display:block" id="pageloading"></div>    	<div id="maingrid"></div>    	<div id="target" class="self-form" style="display:none;">        	<table class="self-table">    			<tr>    				<td width="90px">名称</td>    				<td><input type="text" id="detailName" value=""></td>    			</tr>    			<tr>    				<td width="90px">KEY</td>    				<td><input type="text" id="detailKey" value=""></td>    			</tr>    			<tr>    				<td width="90px">VALUE</td>    				<td><input type="text" id="detailValue" value=""></td>    			</tr>    		</table>    	</div>    	<input type="hidden" id="paramId" value="${paramId }">    	<input type="hidden" id="detailId" value="" />	</body></html>