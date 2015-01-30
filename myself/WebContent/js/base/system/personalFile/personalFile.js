var projectName="";
/**
 * 基本信息修改
 */
function updateArchiveInfo(divId){
	liger.get("userName").setValue($("#"+divId+"userName").text());
	liger.get("positionName").setValue($("#"+divId+"positionName").text());
	$("#birthday").val($("#"+divId+"birthday").text());
	liger.get("natonalName").setValue($("#"+divId+"natonalName").text());
	liger.get("sex").setValue($("#"+divId+"sex").text());
	liger.get("sex").setText($("#"+divId+"sex").text());
	liger.get("zzmm").setValue($("#"+divId+"zzmm").text());
	liger.get("zzmm").setText($("#"+divId+"zzmm").text());
	liger.get("tel").setValue($("#"+divId+"tel").text());
	liger.get("homeTel").setValue($("#"+divId+"homeTel").text());
	liger.get("homeAddr").setValue($("#"+divId+"homeAddr").text());
	liger.get("jobTitle").setValue($("#"+divId+"jobTitle").text());
	liger.get("orgId").setValue($("#"+divId+"orgId").text());
	ligerDialog_open("修改档案基本信息",$("#targetArchiveInfo"),"archiveInfoEditOK","","450");
}
function archiveInfoEditOK(item, dialog){
	var param = {
		archiveId: $("#archiveId").val(),
		userName: liger.get("userName").getValue(),
		positionName: liger.get("positionName").getValue(),
		birthday: $("#birthday").val(),
		natonalName: liger.get("natonalName").getValue(),
		sex: liger.get("sex").getValue(),
		tel: liger.get("tel").getValue(),
		zzmm: liger.get("zzmm").getValue(),
		homeTel: liger.get("homeTel").getValue(),
		homeAddr: liger.get("homeAddr").getValue(),
		jobTitle: liger.get("jobTitle").getValue(),
		orgId: liger.get("orgId").getValue()
	};
	$.post(projectName+"/PersonalFileAction.action?method=savePersonalFileEdit&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('1');
		} else {
			error_alert(projectName,"修改档案基本信息失败",mapToString(param));
		}
	});
}
/**
 * 教育信息 新增
 */
function addArchiveEducation(){
	liger.get("school").setValue("");
	liger.get("profession").setValue("");
	liger.get("education").setValue("");
	liger.get("degreeName").setValue("");
	liger.get("education_startDate").setValue("");
	liger.get("education_endDate").setValue("");
	ligerDialog_open("新增教育信息",$("#targetArchiveEducation"),"archiveEducationAddOK","","300");
}
function archiveEducationAddOK(item, dialog){
	var param = {
		archiveId: $("#archiveId").val(),
		school: liger.get("school").getValue(),
		profession: liger.get("profession").getValue(),
		education: liger.get("education").getValue(),
		degreeName: liger.get("degreeName").getValue(),
		startDate: $("#education_startDate").val(),
		endDate: $("#education_endDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveEducationAdd&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('2');
		} else {
			error_alert(projectName,"新增档案教育信息失败",mapToString(param));
		}
	});
}
/**
 * 教育信息 修改
 */
function updateArchiveEducation(divId,id){
	$("#id").val(id);
	liger.get("school").setValue($("#"+divId+"school").text());
	liger.get("profession").setValue($("#"+divId+"profession").text());
	liger.get("education").setValue($("#"+divId+"education").text());
	liger.get("degreeName").setValue($("#"+divId+"degreeName").text());
	liger.get("education_startDate").setValue($("#"+divId+"startDate").text());
	liger.get("education_endDate").setValue($("#"+divId+"endDate").text());
	ligerDialog_open("修改教育信息",$("#targetArchiveEducation"),"archiveEducationEditOK","","300");
}
function archiveEducationEditOK(item, dialog){
	var param = {
		id:$("#id").val(),
		school: liger.get("school").getValue(),
		profession: liger.get("profession").getValue(),
		education: liger.get("education").getValue(),
		degreeName: liger.get("degreeName").getValue(),
		startDate: $("#education_startDate").val(),
		endDate: $("#education_endDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveEducationEdit&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('2');
		} else {
			error_alert(projectName,"修改档案教育信息失败",mapToString(param));
		}
	});
}
/**
 * 工作经历 新增
 */
function addArchiveJob(){
	liger.get("companyName").setValue("");
	liger.get("companyAddr").setValue("");
	liger.get("position").setValue("");
	$("#jobDes").val("");
	liger.get("leaveReason").setValue("");
	$("#addDate").val("");
	$("#leaveDate").val("");
	ligerDialog_open("新增工作经历信息",$("#targetArchiveJob"),"archiveJobAddOK","","420");
}
function archiveJobAddOK(item, dialog){
	var param = {
		archiveId: $("#archiveId").val(),
		companyName: liger.get("companyName").getValue(),
		companyAddr: liger.get("companyAddr").getValue(),
		position: liger.get("position").getValue(),
		jobDes: $("#jobDes").val(),
		leaveReason: liger.get("leaveReason").getValue(),
		addDate: $("#addDate").val(),
		leaveDate: $("#leaveDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveJobAdd&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('3');
		} else {
			error_alert(projectName,"新增工作经历信息失败",mapToString(param));
		}
	});
}
/**
 * 工作经历 修改
 */
function updateArchiveJob(divId,id){
	$("#id").val(id);
	liger.get("companyName").setValue($("#"+divId+"companyName").text());
	liger.get("companyAddr").setValue($("#"+divId+"companyAddr").text());
	liger.get("position").setValue($("#"+divId+"position").text());
	$("#jobDes").val($("#"+divId+"jobDes").text());
	liger.get("leaveReason").setValue($("#"+divId+"leaveReason").text());
	$("#addDate").val($("#"+divId+"addDate").text());
	$("#leaveDate").val($("#"+divId+"leaveDate").text());
	ligerDialog_open("修改工作经历信息",$("#targetArchiveJob"),"archiveJobEditOK","","420");
}
function archiveJobEditOK(item, dialog){
	var param = {
		id:$("#id").val(),
		companyName: liger.get("companyName").getValue(),
		companyAddr: liger.get("companyAddr").getValue(),
		position: liger.get("position").getValue(),
		jobDes: $("#jobDes").val(),
		leaveReason: liger.get("leaveReason").getValue(),
		addDate: $("#addDate").val(),
		leaveDate: $("#leaveDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveJobEdit&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('3');
		} else {
			error_alert(projectName,"修改档案工作经历信息失败",mapToString(param));
		}
	});
}
/**
 * 教育经历 新增
 */
function addArchiveTrain(){
	liger.get("train_school").setValue("");
	$("#train_trainDesc").val("");
	$("#train_startDate").val("");
	$("#train_endDate").val("");
	ligerDialog_open("新增培训经历信息",$("#targetArchiveTrain"),"archiveTrainAddOK","","300");
}
function archiveTrainAddOK(item, dialog){
	var param = {
		archiveId: $("#archiveId").val(),
		school: liger.get("train_school").getValue(),
		trainDesc: $("#train_trainDesc").val(),
		startDate: $("#train_startDate").val(),
		endDate: $("#train_endDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveTrainAdd&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('4');
		} else {
			error_alert(projectName,"新增培训经历信息失败",mapToString(param));
		}
	});
}
/**
 * 教育经历 修改
 */
function updateArchiveTrain(divId,id){
	$("#id").val(id);
	liger.get("train_school").setValue($("#"+divId+"school").text());
	$("#train_trainDesc").val($("#"+divId+"trainDesc").text());
	$("#train_startDate").val($("#"+divId+"startDate").text());
	$("#train_endDate").val($("#"+divId+"endDate").text());
	ligerDialog_open("修改培训经历信息",$("#targetArchiveTrain"),"archiveTrainEditOK","","420");
}
function archiveTrainEditOK(item, dialog){
	var param = {
		id: $("#id").val(),
		school: liger.get("train_school").getValue(),
		trainDesc: $("#train_trainDesc").val(),
		startDate: $("#train_startDate").val(),
		endDate: $("#train_endDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveTrainEdit&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('4');
		} else {
			error_alert(projectName,"修改档案培训经历信息失败",mapToString(param));
		}
	});
}
/**
 * 奖罚经历 新增
 */
function addArchiveReward(){
	liger.get("jfReason").setValue("");
	$("#jfDetail").val("");
	liger.get("jfType").setValue("奖励");
	liger.get("jfType").setText("奖励");
	$("#jfDate").val("");
	ligerDialog_open("新增奖罚经历信息",$("#targetArchiveReward"),"archiveRewardAddOK","","300");
}
function archiveRewardAddOK(item, dialog){
	var param = {
		archiveId: $("#archiveId").val(),
		jfReason: liger.get("jfReason").getValue(),
		jfDetail: $("#jfDetail").val(),
		jfType: liger.get("jfType").getValue(),
		jfDate: $("#jfDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveRewardAdd&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('5');
		} else {
			error_alert(projectName,"新增奖罚经历信息失败",mapToString(param));
		}
	});
}
/**
 * 奖罚经历 修改
 */
function updateArchiveReward(divId,id){
	$("#id").val(id);
	liger.get("jfReason").setValue($("#"+divId+"jfReason").text());
	$("#jfDetail").val($("#"+divId+"jfDetail").text());
	liger.get("jfType").setValue($("#"+divId+"jfType").text());
	liger.get("jfType").setText($("#"+divId+"jfType").text());
	$("#jfDate").val($("#"+divId+"jfDate").text());
	ligerDialog_open("修改奖罚经历信息",$("#targetArchiveReward"),"archiveRewardEditOK","","420");
}
function archiveRewardEditOK(item, dialog){
	var param = {
		id: $("#id").val(),
		jfReason: liger.get("jfReason").getValue(),
		jfDetail: $("#jfDetail").val(),
		jfType: liger.get("jfType").getValue(),
		jfDate: $("#jfDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveRewardEdit&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('5');
		} else {
			error_alert(projectName,"修改奖罚经历信息失败",mapToString(param));
		}
	});
}
/**
 * 年度休假 新增
 */
function addArchiveHoliday(){
	liger.get("reason").setValue("");
	liger.get("isType").setValue("事假");
	liger.get("isType").setText("事假");
	$("#holiday_startDate").val("");
	$("#holiday_endDate").val("");
	ligerDialog_open("新增休假信息",$("#targetArchiveHoliday"),"archiveHolidayAddOK","","300");
}
function archiveHolidayAddOK(item, dialog){
	var param = {
		archiveId: $("#archiveId").val(),
		reason: liger.get("reason").getValue(),
		isType: liger.get("isType").getValue(),
		startDate: $("#holiday_startDate").val(),
		endDate: $("#holiday_endDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveHolidayAdd&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('6');
		} else {
			error_alert(projectName,"新增休假信息失败",mapToString(param));
		}
	});
}
/**
 * 年度休假 修改
 */
function updateArchiveHoliday(divId,id){
	$("#id").val(id);
	liger.get("reason").setValue($("#"+divId+"reason").text());
	liger.get("isType").setValue($("#"+divId+"isType").text());
	liger.get("isType").setText($("#"+divId+"isType").text());
	$("#holiday_startDate").val($("#"+divId+"startDate").text());
	$("#holiday_endDate").val($("#"+divId+"endDate").text());
	
	ligerDialog_open("修改休假信息",$("#targetArchiveHoliday"),"archiveHolidayEditOK","","300");
}
function archiveHolidayEditOK(item, dialog){
	var param = {
		id: $("#id").val(),
		reason: liger.get("reason").getValue(),
		isType: liger.get("isType").getValue(),
		startDate: $("#holiday_startDate").val(),
		endDate: $("#holiday_endDate").val()
	};
	$.post(projectName+"/PersonalFileAction.action?method=saveArchiveHolidayEdit&anticache="
			+ Math.floor(Math.random() * 10000), param, function(info) {
		if (info.info == undefined) {
			info = eval("(" + info + ")");
		}
		if (info.info == 'ok') {
    		dialog.hidden();
    		leftLoad('6');
		} else {
			error_alert(projectName,"修改休假信息失败",mapToString(param));
		}
	});
}
function leftLoad(titId){
	location.href=projectName+"/PersonalFileAction.action?method=showPersonalFile&titId="+titId+"&archiveId="
		+$("#archiveId").val();
}


