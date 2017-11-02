package com.java.towing.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.towing.db.DBConnection;

/**
 * Servlet implementation class IncidentImageServlet
 */
@WebServlet("/IncidentImageServlet")
public class IncidentImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncidentImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBConnection dbConnection=new DBConnection();
        OutputStream out = response.getOutputStream();
        response.setContentType("image/png");
        String id=request.getParameter("id");
        
        System.out.println("incident="+id);
        try
        {

            Connection con=(Connection) dbConnection.connect();
            ResultSet rs=null;
            InputStream in = null;
            
            

            System.out.println("image id ="+id);
            PreparedStatement display_img=(PreparedStatement)con.prepareStatement("SELECT incidente_vehicle_no_image from incidente_table where incidente_id="+id+"");
            rs=(ResultSet)display_img.executeQuery();

            if(rs.next())
            {
                    in =rs.getBinaryStream(1);
                    byte b[]=new byte[in.available()];
                    System.out.println("total bytes : "+in.available());
                    int index = 0;
                    while((index=in.read(b))!=-1)
                    {
                            out.write(b,0,index);
                    }
            }

            in.close();
            out.close();
            System.out.println("Images Retrieved Successfully.....");

        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
    }
		
		
		
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
