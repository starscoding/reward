<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>404 - 页面不存在</title>
	<style>
		*{font-family:"Microsoft Yahei";margin:0;font-weight:lighter;text-decoration:none;text-align:center;line-height:2.2em;}
		html,body{height:100%;}
		h1{font-size:100px;line-height:1em;}
		table{width:100%;height:100%;border:0;}
	</style>
</head>

<body>
<table cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<h1>404</h1>
						<h3> :( 崩溃啦啦啦，啦啦，啦......</h3>
						<p>你访问的页面好像不小心被弄丢了~<br/>
							<a href="<c:url value="/"/>">返回首页></a>
						</p>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
