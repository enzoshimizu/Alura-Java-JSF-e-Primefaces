<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador</title>
    </head>
    <body>
        <c:if test="${ not empty nomeEmpresa }">
            <p>Empresa ${ nomeEmpresa } criada com sucesso.</p>
        </c:if>
        <c:if test="${ empty nomeEmpresa }">
            <p>Nenhuma empresa cadastrada.</p>
        </c:if>
    </body>
</html>
