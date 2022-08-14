<%-- 
    Document   : showcart
    Created on : Oct 1, 2016, 7:33:14 PM
    Author     : user
--%>
<%@page import="org.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Showcart Page</title>
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
        <h1>Your current shopping cart!</h1> <br/>
        <form action="DeleteProduct.do">
            <table>
                <%
                    ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
                    int i = 1;
                    for(Product product: cart)
                    {
                %>       
                        <tr>
                            <td> <input type="checkbox" name="products" value="<%=product.getName()%>"/> </td>
                            <td> <%=i%>. </td>
                            <td> <%=product.getName()%> </td>
                            <td> <%=product.getAmount()%> Kg. </td>
                        </tr>

                <%  
                        i++;
                    }
                %>
            </table>
            <input type="submit" value="Delete" /> <br/>
            <a href="CheckOut.do">Checkout</a>
        </form>
                <!--This is a comment -->
                <%-- JSP comment --%>
    </body>
</html>
