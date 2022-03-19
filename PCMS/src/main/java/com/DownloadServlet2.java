package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.jdbc.Blob;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet2")
public class DownloadServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DownloadServlet2() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        final int BUFFER_SIZE = 4096;
        java.sql.Connection con=null;
        String pid=request.getParameter("pid");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", "root", "root");
            System.out.println("Connected");
            String sql="select * from reports where pid=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,pid );
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                java.sql.Blob blob=rs.getBlob("report");
                InputStream inp=blob.getBinaryStream();
                int fl=inp.available();
                ServletContext context=getServletContext();
                String mimeType=context.getMimeType("report");
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                response.setContentLength(fl);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", "report");
                response.setHeader(headerKey, headerValue);

                // writes the file to the client
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inp.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inp.close();
                outStream.close();

            }

        }catch(Exception e) {
            e.printStackTrace();
        }


    }



}
