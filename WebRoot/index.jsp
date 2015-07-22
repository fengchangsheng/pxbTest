<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>"/>
	
	<script src="<c:url value='/js/jquery-2.1.3.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/js/pxb.js'/>"></script>
</head>
<body>
	<div>
		
	<div>
	
	<div class="container">
	    <ul class="nav nav-pills">
			<li role="presentation" class="active"><a href="#">Home</a></li>
			<li role="presentation"><a href="neixun/search3">Neixun</a></li>
			<li role="presentation" class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Resource<span class="caret"></span>
			</li>
			
		</ul>
	</div>
	
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li><a href="#">Library</a></li>
			<li class="active">Data</li>
		</ol>
	</div>
	
	<div class="container">
	<label>----------------------------------------------------</label></br>
	<label>文件上传</label>
		<form action="fileupload" method="post" enctype="multipart/form-data">
	
			<input id="file" type="file" name="file" />
			<input type="submit" value="上传">
			<c:if test="${not empty message}">
				<div id="message" class="success">${message}</div>	  		
			</c:if>
		
		</form>
	</div>
	
	
	<div class="container">
	<label>----------------------------------------------------</label></br>
		<label id="leb">异步请求测试</label>
		<input id="btn1" type="button" value="试试">
	</div>
	
	
	<div class="container">
	<label>----------------------------------------------------</label></br>
	<label>类型转换</label>
		<ul>
			<li>
				<a id="primitive" class="textLink" href="<c:url value="/convert/primitive?value=3" />">Primitive</a>
			</li>
			<li>
				<a id="date" class="textLink" href="<c:url value="/convert/date/2010-07-04" />">Date</a>
			</li>
			<li>
				<a id="collection" class="textLink" href="<c:url value="/convert/collection?values=1&values=2&values=3&values=4&values=5" />">Collection 1 (multi-value parameter)</a>
			</li>
			<li>
				<a id="collection2" class="textLink" href="<c:url value="/convert/collection?values=1,2,3,4,5" />">Collection 2 (single comma-delimited parameter value)</a>
			</li>
			<li>
				<a id="formattedCollection" class="textLink" href="<c:url value="/convert/formattedCollection?values=2010-07-04,2011-07-04" />">@Formatted Collection</a>
			</li>		
			<li>
				<a id="valueObject" class="textLink" href="<c:url value="/convert/value?value=123456789" />">Custom Value Object</a>
			</li>
			<li>
				<a id="customConverter" class="textLink" href="<c:url value="/convert/custom?value=123-45-6789" />">Custom Converter</a>
			</li>		
		</ul>
	</div>	
	
	
	
	<div class="container">
	<label>----------------------------------------------------</label></br>
	<label>数据校验</label>
		<ul>
			<li>
				<a id="validateNoErrors" class="textLink" href="<c:url value="/validate?number=4&date=2025-07-04" />">Validate, no errors</a>
			</li>
			<li>
				<a id="validateErrors" class="textLink" href="<c:url value="/validate?number=6&date=2010-07-01" />">Validate, errors</a>
			</li>
		</ul>	
	</div>
	
	
		
	<div class="container">
	<label>----------------------------------------------------</label></br>
	<label>根据模板导入数据</label>
		<ul>
			<li>
				<input type="button" id="btn" value="点击导入模板数据">
			</li>
		</ul>	
	</div>
	
	<div class="container">
	<label>----------------------------------------------------</label></br>
	<label>二维码显示区域</label>
		<ul>
			<li id="li">
				<img alt="查询网站" src="img/1437124993.png">
			</li>
		</ul>	
	</div>
	<script>
	$(function(){
		$("#btn1").bind("click",test);
		$("#btn").bind("click",imp);
	})
	
	function suc(data){
		$("#leb").html(data);
	}
	
	function success(data){
		alert(data);
	}
	
	function test(){
		var url = "asy";
		var params = "test=2";
		pxb.call(url,params,suc);
	}
	
	function imp(){
		var url = "imp/doimp";
		pxb.call(url,null,success);
	}
	
	function getMa(){
		var url = "imp/read";
		pxb.call(url,null,success);
	}
	
	
	</script>
</body>
</html>