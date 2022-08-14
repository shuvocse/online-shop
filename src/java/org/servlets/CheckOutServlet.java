package org.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.db.DataAccess;
import org.models.Product;

/**
 *
 * @author User
 */
public class CheckOutServlet extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        DataAccess db = new DataAccess();
        String url = "jdbc:mysql://localhost/app";
        String user = "john";
        String pass = "123456";
        
        boolean result = db.CreateConnection(url, user, pass);
        if(result)
        {
            HttpSession session = request.getSession();
            ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
            String username = (String)session.getAttribute("username");
            String orderid = "1";
            if(!cart.isEmpty())
            {
                try
                {
                    String insertsql = "insert into orders values (?,?)";
                    Connection conn = db.getConn();
                    PreparedStatement stmt = conn.prepareStatement(insertsql);
                    stmt.setString(1,username);
                    stmt.setString(2, orderid);
                    stmt.executeUpdate();
                    
                    insertsql = "insert into orderitems values (?,?,?)";
                    stmt = conn.prepareStatement(insertsql);
                    stmt.setString(1, orderid);
                    for(Product p: cart)
                    {
                        stmt.setString(2, p.getName());
                        stmt.setString(3, p.getAmount());
                        stmt.executeUpdate();
                    }
                    out.println("Your order successfully processed.");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    out.println("Your orders could not be processed successfully.");
                }
                
            }
            //
            // out.print("DB connection successful");
        }
        else
        {
            out.print("DB connection not successful");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
