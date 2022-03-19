package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
@WebServlet("/PersonalServlet")
public class PersonalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        Connection con=null;
		String pid=request.getParameter("pid");
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String address=request.getParameter("add");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", "root", "root");
            System.out.println("Connected");
		String sql="insert into personal values(?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, pid);
		ps.setString(2, name);
		ps.setInt(3, age);
		ps.setString(4, address);
		ps.setString(5, gender);
		ps.setString(6, phone);
		ps.setString(7, email);
		ps.execute();
		out.println("Record Added Successfully");
		out.println("<a href='index1.html'>Go To Home</a>");
		out.println("<a href='treatment.html'>Add Treatment Details..</a>");
		       }catch(Exception e) {
            e.printStackTrace();
        }


    }

}
