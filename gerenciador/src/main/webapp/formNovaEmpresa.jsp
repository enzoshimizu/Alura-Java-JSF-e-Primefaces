<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/novaEmpresa" var="linkServletNovaEmpresa"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <form name="empresa" action="${ linkServletNovaEmpresa }" method="POST">
            Nome: <input type="text" name="nome">
            Data Abertura: <input type="text" name="data">
            <input type="submit" value="Enviar" name="enviar" />
        </form>
    </body>
</html>
