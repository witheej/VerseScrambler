<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>Verse Scrambler Login</title>
</head>
<body onload='document.f.j_username.focus()'>

	<h3>Verse Scrambler Login Page</h3>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login was unsuccessful. <br /> Cause:
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"]}
		</div>
	</c:if>
	<form action="j_spring_security_check" name="f" method="post">
		<div>
			User: <input type="text" name="j_username" value="" />
		</div>
		<div>
			Password: <input type="password" name="j_password" />
		</div>
		<div>
			<input type="submit" name="Submit" value="Submit" />
		</div>
		<div>
			<input type="reset" value="Reset" />
		</div>

	</form>
</body>
</html>