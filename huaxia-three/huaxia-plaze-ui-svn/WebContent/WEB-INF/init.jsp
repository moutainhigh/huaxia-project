<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<link rel="stylesheet" type="text/css" href="${basePath}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/normalize.css"></link>
<link rel="stylesheet" type="text/css" href="${basePath}/css/normalize-ext.css"></link>
<script type="text/javascript" src="${basePath}/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath}/js/sha256.js"></script>