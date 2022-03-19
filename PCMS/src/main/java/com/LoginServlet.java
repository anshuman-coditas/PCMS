package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String uname=request.getParameter("admin");
        String pass=request.getParameter("pass");
        Connection con=null;

        ServletContext app=getServletConfig().getServletContext();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", "root", "root");
            PreparedStatement ps=con.prepareStatement("select user,password from login where user=? and password=?");
            ps.setString(1, uname);
            ps.setString(2,pass);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {

                RequestDispatcher rd=request.getRequestDispatcher("index.html");
                rd.forward(request, response);

            }
            else {
                out.println("<h1><center>Wrong Entries</center.</h1>");
                RequestDispatcher rd=request.getRequestDispatcher("index1.html");
                rd.include(request, response);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
