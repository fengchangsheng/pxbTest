<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
</head>
<body>
this is new page  
${projects}
<table>
<c:forEach items="${projects}"  var="pro">

<tr><td>name:${pro.name}</td></tr>
</c:forEach>
</table>
</body>
</html>