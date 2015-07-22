<%@ include file="/common/taglib.jsp"%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>"/>
	
	<script src="<c:url value='/js/jquery-2.1.3.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/js/pxb.js'/>"></script>
</head>
<body>
	<table id="main" class="table table-hover">
		<tr>
			<th><fmt:message key="neixun.project.sn"/></th>
			<th><fmt:message key="neixun.project.name"/></th>
			<th><fmt:message key="neixun.project.starttime"/></th>
			<th><fmt:message key="neixun.project.endtime"/></th>
		</tr>
	</table>
	<script>
		$(function(){
			init();
		});
		
		function init() {
			pxb.call("neixun/search", "", function(data){
				if (data) {
					data = eval("(" + data + ")");
					
					var html = [];
					for (var i = 0; i < data.length; i++) {
						html.push("<tr>");
						html.push("<td>" + (i + 0) + "</td>");
						html.push("<td>" + data[i].name + "</td>");
						html.push("<td>" + data[i].startTime + "</td>");
						html.push("<td>" + data[i].endTime + "</td>");
						html.push("</tr>");
					}
					$("#main").append(html.join(""));
				}
			});
		}
	</script>
</body>
</html>