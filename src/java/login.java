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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rohit
 */
public class login extends HttpServlet {

   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
          String i = req.getParameter("email");
        String j= req.getParameter("roll");
         PrintWriter out= res.getWriter();
        try{
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sir","root","");
		Statement st =con.createStatement();
                if(j.equals("admin") && i.equals("123")){
                        res.sendRedirect("..//Client/main.jsp");
                        }
		ResultSet rs = st.executeQuery("select name from student_info where rollno="+j+" && email='"+i+"';");
		String userName="";
		while(rs.next()) {
                    userName=rs.getString(1);
		HttpSession session = req.getSession();
                session.setAttribute("user",userName);
               
                        
			if(userName.equals("")){
                             
                             res.sendRedirect("index.html");
                        }
                        else{
                            
                         
                            res.sendRedirect("dashboard.html");
                         
                        }
		}
		st.close();
		con.close();}
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
