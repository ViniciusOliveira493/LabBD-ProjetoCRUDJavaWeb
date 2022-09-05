<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>...::: Produtos :::...</title>
		<link rel="stylesheet" href="./css/style.css">
	</head>
	<body>
		<jsp:include page="components/menu.jsp"/>
		<main>
				<main>
			<h1 align="center">Produtos</h1>
			<div class="divForm">				
				<form action="produto" method="post">
					<div class="inline">
						<p>ID:</p>
						<input type="number" id="txtID" name="txtID" min="0" step="1" placeholder = "ID" 
							value='<c:out value="${produto.id}"></c:out>'>
					</div>
					<div class="inline">
						<p>Nome do produto:</p>
						<input type="text" id="txtNome" name="txtNome" placeholder = "Nome" 
							value='<c:out value="${produto.nome}"></c:out>'>
					</div>
					<div>
						<p>Preço do produto:</p>
						<input type="number" id="txtPreco" name="txtPreco" placeholder = "Preço"  
							min="0" step="0.01" value='<c:out value="${produto.preco}"></c:out>'>
					</div>
					<div>
						<p>Marca do produto:</p>
						<select class="fullWidth" id="cbMarca" name="cbMarca">
							<option value="0">Selecione uma marca...</option>
							<c:if test="${not empty marcas}">
								<c:forEach items="${marcas}" var="m">
									<c:if test="${not empty marca}">
										<c:if test="${marca.id eq m.id}">
											<option value='<c:out value="${m.id}"></c:out>' selected="selected"><c:out value="${m.nome}"></c:out></option>
										</c:if>
									</c:if>
										<c:if test="${(empty marca) || (m.id ne marca.id)}">
										<option value='<c:out value="${m.id}"></c:out>'><c:out value="${m.nome}"></c:out></option>
									</c:if>
								</c:forEach>
							</c:if>
						</select>
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
			<c:if test="${(not empty erro) || (not empty saida) || (not empty produtos)}">
				<div class="divForm">
				<c:if test="${not empty erro}">
					<c:out value="${erro}"></c:out>
				</c:if>
				
				<c:if test="${not empty saida}">
					<c:out value="${saida}"></c:out>
				</c:if>
				
				<c:if test="${not empty produtos}">
					<table border="1">
						<thead>
							<tr>
								<th>#ID</th>
								<th>Nome</th>
								<th>Preco</th>
								<th>Marca</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${produtos}" var="p">
								<tr>
									<td><c:out value="${p.id}"></c:out></td>
									<td align="center"><c:out value="${p.nome}"></c:out></td>
									<td align="right"><c:out value="${p.preco}"></c:out></td>
									<td align="center"><c:out value="${p.marca.nome}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				</div>
			</c:if>
		</main>
		</main>
		<jsp:include page="components/footer.jsp"/>
	</body>
</html>