<%-- 
    Document   : products
    Created on : Oct 1, 2016, 7:19:34 PM
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
            if(username==null || username.equals("") )
            {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        %>
        <form action="AddProduct.do">
            <h1>Hi! USERNAME </h1>
            <br>Please select product and amount (in Kg.):<br/>
            <ul style="list-style-type: none">
                <li>
                    <select name="product">
                        <option value="mango">Mango</option>
                        <option value="apple">Apple</option>
                        <option value="guava">Guava</option>
                        <option value="orange">Orange</option>
                    </select> <br/>
                </li>
                <li>
                    Amount (Kg.): <input type="text" name="amount" maxlength="2" size="2"/> <br>
                </li>
                <li>
                    <input type="submit" value="Add" />
                </li>
            </ul>
        </form>
    </body>
</html>
