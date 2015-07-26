<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML >
<html>
	<head>

		<title>My JSP 'main.jsp' starting page</title>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
	
	
		<shiro:user>  
		
		    Welcome back John!  Not John? Click <a href="login.jsp">here<a> to login.  //认证通过或者已记住的用户<br>
		
		</shiro:user>
		
		
		Hello, <shiro:principal/>, how are you today?  //输出登录者信息
		
		<shiro:hasRole name="administrator">  
		
		    <a href="admin.jsp">Administer the system</a>  
		
		</shiro:hasRole>
		
		
		<ul>
			<li>
				<h2>
					<a target="_self" href="user.do?myjsp">myjsp</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="_self" href="user.do?notmyjsp">无权访问</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="_self" href="login.jsp">登录</a>
				</h2>
			</li>
			<li>
				<h2>
					<a target="_self" href="login.do?logout">注销</a>
				</h2>
			</li>
		</ul>
	</body>
</html>
