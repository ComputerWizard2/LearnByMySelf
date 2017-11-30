<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style>
<!--
	用来修饰表格样式的内部的样式
-->
body{
	margin: 800px auto;

}
 table { 
 	text-align: center;
 	margin-left:400px;
 	background-color: lightgreen;
 
 }

</style>
</head>
<body>
	<span color="red" font-size="14px">${sessionScope.mess}</span>
<form action="login" method="post">
	<table>
	<tr>
	<td>用户名：</td>
	<td><input type="text" name="uname"></td>
	
	</tr>
	<tr>
	<td>密码：</td>
	<td><input type="password" name="upwd"></td>
	
	</tr>
	<tr>
	<td><button onclick="">注册</button></td>
	
	
	</tr>
	<tr>
	<td><input type="submit" value="登录"></td>
	<td><input type="reset" value="重置"></td>
	
	</tr>
	
	
	</table>


</form>

</body>
</html>