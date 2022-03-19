package com;

import java.io.*;
import java.sql.Connection;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.Blob;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/RecordPdf")
public class RecordPdf extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RecordPdf() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        try {
            String pid=request.getParameter("pid");
            Document doc=new Document();
            PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\Coditas\\Desktop\\record_"+pid+".pdf"));
            doc.open();
            Connectionget connectionget=new Connectionget();
            Connection conn=connectionget.getConnection();
            PreparedStatement ps=null;
            String sql="select * from personal where pid=?";
            ps= conn.prepareStatement(sql);
            ps.setString(1,pid);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                doc.add(new Paragraph(rs.getString(1)+" \n"+rs.getString(2)
                        +"\n"+rs.getInt(3)+" \n"+rs.getString(4)
                        +"\n"+rs.getString(5)+" \n"+rs.getString(6)
                        +"\n"+rs.getString(7)
                ));

            }
            doc.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        }


    }




