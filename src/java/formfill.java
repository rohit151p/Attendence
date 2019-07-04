/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class formfill extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
            int roll = Integer.parseInt(req.getParameter("roll"));
            String name=req.getParameter("name");
            String email=req.getParameter("email");
           String sec=req.getParameter("sec");
       int branch = Integer.parseInt(req.getParameter("branch"));
         int year = Integer.parseInt(req.getParameter("year"));   
            String phone =req.getParameter("phone");
           
            PrintWriter out= res.getWriter();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sir","root","");
                PreparedStatement st =con.prepareStatement("insert into student_info values (?,?,?,?,?,?,?)");
                st.setString(1, name);
                st.setInt(2, roll);
                st.setString(3,email);
                st.setInt(4, branch);
                st.setInt(5, year);
                st.setString(6, sec);
                st.setString(7, phone);
                int count  = st.executeUpdate();
               
                st.close();
                con.close();
            if(count>0){
            res.sendRedirect("index.html");
            }
            }
            catch(Exception e){
                System.out.println(e);
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
