package com.java.towing.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.java.towing.bean.ReportBean;
import com.java.towing.db.ReportDBWrapper;

/**
 * Servlet implementation class BarChartServlet
 */
@WebServlet("/BarChartServletMonthlyFineAmount")
public class BarChartServletMonthlyFineAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarChartServletMonthlyFineAmount() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ReportDBWrapper reportDBWrapper = new ReportDBWrapper();
		ArrayList<ReportBean> reportBeanList=reportDBWrapper.fetchAllReportoInfoMonthly();

		OutputStream out = response.getOutputStream();
		response.setContentType("image/png");
		String chartName="Monthly Towing Fine Amount Report";
		DefaultCategoryDataset dataset= new DefaultCategoryDataset();
		for(int i = 0; i < reportBeanList.size(); i++)
		{
			String fine=reportBeanList.get(i).getFineAmount();
			String date=reportBeanList.get(i).getDate();
			
			int fineamount = Integer.parseInt(fine);
			System.out.println("count"+fineamount);
			dataset.addValue( fineamount , date ,"");  
		}

		JFreeChart chart = ChartFactory.createBarChart(
				chartName,  // chart title 
				"Month",
				"Fine",
				dataset       // data    
				);

		BufferedImage chartImage = chart.createBufferedImage(300, 400);
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