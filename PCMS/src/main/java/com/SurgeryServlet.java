package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonalServlet
 */
@WebServlet("/SurgeryServlet")
public class SurgeryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurgeryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        Connection con=null;
        String pid=request.getParameter("pid");
        String surgery=request.getParameter("surgery");
        Date dos= Date.valueOf(request.getParameter("dos"));
        Date dod=Date.valueOf(request.getParameter("dod"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", "root", "root");
            System.out.println("Connected");
            String sql="insert into surgery values(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, pid);
           ps.setString(2,surgery);
           ps.setDate(3,dos);
           ps.setDate(4,dod);
            ps.execute();
            out.println("Record Added Successfully");
            out.println("<a href='index.html'>Go To Home</a>");
        }catch(Exception e) {
            e.printStackTrace();
        }


    }

}
