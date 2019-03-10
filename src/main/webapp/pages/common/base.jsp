<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.smile.azxx.util.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="ctx" value="<%=CommonUtil.getFullWebContext(request) %>" />

<!--  基础的JS-->
<c:set var="bootstrapJs" value="${ctx}/static/vendor/bootstrap/js/bootstrap.min.js"/>
<c:set var="jqueryJs" value="${ctx}/static/vendor/jquery/jquery.min.js"/>
<c:set var="metismenuJs" value="${ctx}/static/vendor/metisMenu/metisMenu.min.js"/>
<c:set var="commnJs" value="${ctx}/scripts/common/Utils.js"/>
<c:set var="dataTablesJs" value="${ctx}/static/vendor/datatables/js/jquery.dataTables.js"/>
<c:set var="dataTables4bJs" value="${ctx}/static/vendor/datatables/js/dataTables.bootstrap.js"/>
<c:set var="jqgridJs" value="${ctx}/static/vendor/jqgrid/js/jquery.jqGrid.min.js"/>

<!--  基础的CSS-->
<c:set var="bootstrapCss" value="${ctx}/static/vendor/bootstrap/css/bootstrap.min.css"/>
<c:set var="bootstrapThemeCss" value="${ctx}/static/vendor/bootstrap/css/bootstrap-theme.css"/>
<c:set var="metismenuCss" value="${ctx}/static/vendor/metisMenu/metisMenu.min.css"/>
<c:set var="awesomeCss" value="${ctx}/static/vendor/font-awesome/css/font-awesome.css"/>
<c:set var="elegantCss" value="${ctx}/static/css/elegant-icons-style.css"/>
<c:set var="jqgridCss" value="${ctx}/static/vendor/jqgrid/css/ui.jqgrid.css"/>


<!-- 基础目录-->
<c:set var="cssDir" value="${ctx}/static/css"/>
<c:set var="imgDir" value="${ctx}/static/img"/>
<c:set var="fontDir" value="${ctx}/static/fonts"/>
<c:set var="jsDir" value="${ctx}/scripts"/>
<c:set var="pageDir" value="${ctx}/pages"/>
<c:set var="vendorDir" value="${ctx}/static/vendor"/>
<c:set var="distDir" value="${ctx}/static/dist"/>
<c:set var="dataDir" value="${ctx}/static/data"/>
<c:set var="jqGridDir" value="${ctx}/static/vendor/jqgrid"/>

<c:set var="CurrenccyName" value="贝壳🐚"/>


