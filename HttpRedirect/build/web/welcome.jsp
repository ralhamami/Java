<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <%
        if (session.getAttribute("user")==null){
            response.sendRedirect("unauthorized.jsp");
            return;
        }
    %>
    <body>
        <h1>Hi, <%= session.getAttribute("user")%>!</h1>
    </body>
</html>
