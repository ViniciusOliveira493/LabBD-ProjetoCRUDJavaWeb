<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>...::: Marcas :::...</title>
		<link rel="stylesheet" href="./css/style.css">
	</head>
	<body>
		<jsp:include page="components/menu.jsp"/>
		<main>
			<h1 align="center">Marcas</h1>
			<div class="divForm">				
				<form action="marca" method="post">
					<div class="inline">
						<p>ID:</p>
						<input type="number" id="txtID" name="txtID" min="0" step="1" placeholder = "ID" 
							value='<c:out value="${marca.id}"></c:out>'>
					</div>
					<div class="inline">
						<p>Nome da Marca:</p>
						<input type="text" id="txtNome" name="txtNome" placeholder = "Nome" 
							value='<c:out value="${marca.nome}"></c:out>'>
					</div>
					<div align="center" id="botoes">
						<input type="submit" id="btn" name="btn" value="Buscar">
						<input type="submit" id="btn" name="btn" value="Inserir">
						<input type="submit" id="btn" name="btn" value="Atualizar">
						<input type="submit" id="btn" name="btn" value="Excluir">
						<input type="submit" id="btn" name="btn" value="Listar">
					</div>
				</form>
			</div>
			<c:if test="${(not empty erro) || (not empty saida) || (not empty lista)}">
				<div class="divForm">
				<c:if test="${not empty erro}">
					<c:out value="${erro}"></c:out>
				</c:if>
				
				<c:if test="${not empty saida}">
					<c:out value="${saida}"></c:out>
				</c:if>
				
				<c:if test="${not empty lista}">
					<table border="1">
						<thead>
							<tr>
								<th>#ID</th>
								<th>Nome</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${lista}" var="m">
								<tr>
									<td><c:out value="${m.id}"></c:out></td>
									<td align="center"><c:out value="${m.nome}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				</div>
			</c:if>
		</main>
		<jsp:include page="components/footer.jsp"/>
	</body>
</html>