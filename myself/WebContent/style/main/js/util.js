var inputWidth=250;
var DialogWidth=420;
var DialogHeight=200;
/**
 * 弹出框
 * @param title 标题列
 * @param target 内容如$("#id")
 * @param okClick(item, dialog)
 * @param width
 * @param height
 * 如：ligerDialog_open("菜单修改",$("#targetQuery"),"queryOK","200","300");
 * @returns
 */
function ligerDialog_open(title,target,okClick,width,height){
	if(width=="")width=DialogWidth;
	if(height=="")height=DialogHeight;
	if(okClick==""){
		return $.ligerDialog.open({
			title: title,height: height,width: width,target: target
		});
	}else{
		return $.ligerDialog.open({
			title: title,height: height,width: width,target: target,
			buttons: [
			    { text: '确定', onclick: eval(okClick)}, 
			    { text: '取消', onclick: function (item, dialog) {
			    	//$(".l-textarea-invalid").removeClass("l-textarea-invalid");
                    //$(".l-text-invalid").removeClass("l-text-invalid");
			    	dialog.hidden();
					}
				}]
		});
	}
}
function showMenuDesc(text){
	var desc = window.parent.getMenuDesc();
	ligerDialog_open(text,$("<div>"+desc+"</div>"),"","","");
}
function comboBox(id,url,type){
	if("tree" == type){//下拉框中显示树
		$("#"+id).ligerComboBox({
	        width : inputWidth, 
	        selectBoxWidth: inputWidth,
	        selectBoxHeight: 200, 
	        valueField: 'ID',
	        textField: 'TEXT', 
	        treeLeafOnly:false,
	        tree: {
	        	checkbox : false,
	        	slide: true,
	        	url: url,
	        	ajaxType:'post',
	        	idFieldName : 'ID',
		    	parentIDFieldName : 'PID',
		    	textFieldName : 'TEXT'}
	    });
	}else{//默认的下拉框
		$("#"+id).ligerComboBox({
            url: url,
            valueField: 'ID',
            textField: 'TEXT', 
            selectBoxWidth: inputWidth,
            autocomplete: true,
            width: inputWidth 
        });
	}
	
}


/**
 * 提示错误信息，记录错误信息
 * projectName:项目名称
 * errorSummary:错误概述
 * errorDesc:错误描述
 */
function error_alert(projectName,errorSummary,errorDesc){
	var tab_0 = window.parent.tab;
	var tabid = tab_0.getSelectedTabItemID();
	var text = $("li[tabid=" + tabid + "] a", tab_0.tab.links.ul).text();
	$.post(projectName+"/ErrorLogAction.action?method=addErrorLog",{
		menuName: text,
		errorSummary: errorSummary,
		errorDesc: errorDesc
		},function(info){
			if (info.info == undefined) {
				info = eval("(" + info + ")");
			}
			if(info.info=="ok"){
				$.ligerDialog.error(errorSummary);
			}
	});
}
function init_page(){
	$(".self-form").ligerForm({inputWidth: inputWidth,validate : true});
	$.metadata.setType("attr", "validate");
}
/**
 * 提示成功信息
 * @param msg
 */
function success_alert(msg){
	$.ligerDialog.success(msg);
}
/**
 * 提示警告信息
 * @param msg
 */
function warn_alert(msg){
	$.ligerDialog.warn(msg);
}

function mapToString(map){
	var str = "";
	$.each(map ,function(i){
		if(str != "")str+=", <br>";
        str+=i+" : “"+map[i]+"”";
    });
	return str;
}