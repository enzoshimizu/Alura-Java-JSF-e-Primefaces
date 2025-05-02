<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empresas</title>
    </head>
    <body>
        <h1>Empresas</h1>
        <ul>
            <c:forEach var="empresa" items="${listaEmpresas}">

                <li>
                    ${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy" />
                    <a href="/gerenciador/removeEmpresa?id=${empresa.id}">Remover</a>
                    <a href="/gerenciador/mostraEmpresa?id=${empresa.id}"> Alterar</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
