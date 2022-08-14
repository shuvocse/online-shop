<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            String counter = (String) application.getAttribute("counter");
            if(counter==null)
            {
                counter = "0";
            }
            int cnt = Integer.parseInt(counter) ; 
            cnt++; //increament
            counter = cnt + ""; //convert to string again
            application.setAttribute("counter", counter);
        %>
        
        <form action="LoginServlet.do">
            <h1>Please enter your username: </h1>
            <input type="text" name="username" /> <br/>
            <input type="submit" value="Login" />
        </form>
     
        <p> Access count: <%=counter%> </p>
    </body>
</html>
