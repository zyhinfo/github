<%@ page language="java" contentType="text/html; charset=UTF-8"%><%@ include file="/jsp/base/core/taglibs.jsp"%><!DOCTYPE html><html>	<head>		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">		<script type="text/javascript">			$(function(){				init_page();			});			function f_save(){                var form = liger.get('form1');	            if (form.valid()) {	            	var param = {						ownMenuId: $("#ownMenuId").val(),						menuName: liger.get("menuName").getValue(),						menuDesc: $("#menuDesc").val(),						linkUrl: liger.get("linkUrl").getValue(),						menuIcon: liger.get("menuIcon").getValue(),						seqNum: liger.get("seqNum").getValue()					};					$.post("${ctx}/MenuAction.action?method=addMenu",param,function(info){						if (info.info == undefined) {							info = eval("(" + info + ")");						}						if(info.info=='ok'){							window.parent.refresh();							liger.get("menuName").setValue("");							$("#menuDesc").val("");							liger.get("linkUrl").setValue("");							liger.get("menuIcon").setValue("");							liger.get("seqNum").setValue("");						}else{							error_alert("${ctx}","新增菜单失败",mapToString(param));						}					});	            }else {	                form.showInvalid();	            }			}		</script>	</head>	<body style="overflow-x:hidden; padding:2px;">    	<form id="form1" class="self-form">        	<table class="self-table">    			<tr>    				<td width="90px">上级菜单</td>    				<td><input type="text" id="ownMenuName" disabled="disabled" value="${ownMenuName}"></td>    			</tr>    			<tr>    				<td width="90px">菜单名称</td>    				<td><input type="text" id="menuName" value="" validate="{required:true,maxlength:20}"></td>    			</tr>    			<tr>    				<td width="90px">菜单描述</td>    				<td>    					<textarea rows="5" cols="40" id="menuDesc" name="menuDesc"></textarea>    				</td>    			</tr>    			<tr>    				<td width="90px">链接地址</td>    				<td><input type="text" id="linkUrl" value="" validate="{required:true,maxlength:20}"></td>    			</tr>    			<tr>    				<td width="90px">显示图标</td>    				<td><input type="text" id="menuIcon" value=""></td>    			</tr>    			<tr>    				<td width="90px">显示顺序</td>    				<td><input type="text" id="seqNum" value="" ltype="int"></td>    			</tr>    		</table>    		<input type="button" onclick="f_save()" class="l-button" value="保存">    	</form>    	<input type="hidden" id="ownMenuId" value="${ownMenuId }">	</body></html>