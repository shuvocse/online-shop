<%-- 
    Document   : product
    Created on : Oct 1, 2016, 7:19:38 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Page</title>
    </head>
    <body>
        <%
            String username = (String) session.getAttribute("username");
            if (username == null || username.equals("")) {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        %>
        <form action="AddProduct.do"><center>
                <h1>Hello! USERNAME</h1>
                <br> Please Select products:<br/>
                <select name="product">
                    <option value="mango"> Mango</option>
                    <option value="apple"> Apple</option>
                    <option value="guava"> Guava</option>
                    <option value="orange"> Orange</option>
                </select>
                <input type="submit" value="Add"/>
                <input type="submit" value="Remove"/>
            </center> </form>

    </body>
</html>
