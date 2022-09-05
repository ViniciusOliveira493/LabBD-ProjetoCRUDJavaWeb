<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title></title>
		<link rel="stylesheet" href="./css/style.css">
	</head>
	<body>
		<nav>
			<uL>
				<li>
					<a href="index.jsp">Home</a>
				</li>
				
				<li>
					<a href="marcas.jsp">Marcas</a>
				</li>
				
				<li>
					<!--<a href="/produto">Produtos</a>-->
					<a href="${pageContext.request.contextPath}/produto">Produtos</a>
				</li>
			</uL>
		</nav>
	</body>
</html>