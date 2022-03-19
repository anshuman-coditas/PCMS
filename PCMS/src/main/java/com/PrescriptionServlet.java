package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.jdi.connect.spi.Connection;
@MultipartConfig(maxFileSize=169999999)
@WebServlet("/PrescriptionServlet")
public class PrescriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrescriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        java.sql.Connection con;
        try {

            Part part1= request.getPart("pres");
            InputStream inp=null,inp1=null;
            String pid=request.getParameter("pid");
            if(part1!=null){
                long size1=part1.getSize();
                String content1=part1.getContentType();
                inp1=part1.getInputStream();
            }

            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", "root", "root");
            System.out.println("Connected");
            PreparedStatement ps=con.prepareStatement("insert into prescription values(?,?)");
            ps.setString(1, pid);
            ps.setBlob(2,inp1);
            int ret=ps.executeUpdate();
            if(ret==0)
                out.println("Sorry");
            else
                out.println("Successful");

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
