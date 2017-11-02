package com.java.towing.service;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebService.path = "E:/FinalCode/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/VehicleTowingServer/WEB-INF/";
		String vehicleNo=com.java.towing.utilty.NumberRecognation.FinalString(new File("C:\\Users\\abc\\Desktop\\photo\\1.jpg"));
		System.out.println("vehicleNo" + vehicleNo);
	}

}
