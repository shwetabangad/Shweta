package com.java.towing.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.java.towing.bean.PoliceStationBean;
import com.java.towing.db.IncidenteDBWrapper;


/**
 * Servlet implementation class PieChartServlet
 */
@WebServlet("/PieChartServlet")
public class PieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PieChartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OutputStream out = response.getOutputStream();
        response.setContentType("image/png");
		
		IncidenteDBWrapper dbWrapper = new IncidenteDBWrapper();
		HashMap<PoliceStationBean, Integer> policeStations = dbWrapper.fetchIncidentCountPerPoliceStation();
		System.out.println("policeStations size" + policeStations.size());
		

		DefaultPieDataset dataset = new DefaultPieDataset( );
		
		for (Map.Entry<PoliceStationBean, Integer> entry : policeStations.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    PoliceStationBean bean = (PoliceStationBean) entry.getKey();
	        int count = (Integer)entry.getValue();
	        
	        dataset.setValue(bean.getPoliceStationName(),  count);
		}
		
	        
	    
		JFreeChart chart = ChartFactory.createPieChart(      
				"Police Station Wise Towing Count",  // chart title 
				dataset,        // data    
				true,           // include legend   
				true, 
				false);

		BufferedImage chartImage = chart.createBufferedImage(400, 500);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(chartImage, "png", os);
		InputStream in = new ByteArrayInputStream(os.toByteArray());
		
		byte b[]=new byte[in.available()];
        System.out.println("total bytes : "+in.available());
        int index = 0;
        while((index=in.read(b))!=-1)
        {
                out.write(b,0,index);
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}