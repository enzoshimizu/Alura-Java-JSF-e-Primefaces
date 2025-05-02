<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/alteraEmpresa" var="linkServletAlteraEmpresa"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <form name="empresa" action="${ linkServletAlteraEmpresa }" method="POST">
            Nome: <input type="text" name="nome" value="${empresa.nome}">
            <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy" var="data" />
            Data Abertura: <input type="text" name="data" value="${data}">
            <input type="type" name="id" value="${empresa.id}" hidden="true">
            <input type="submit" value="Enviar" name="enviar" />
        </form>
    </body>
</html>
