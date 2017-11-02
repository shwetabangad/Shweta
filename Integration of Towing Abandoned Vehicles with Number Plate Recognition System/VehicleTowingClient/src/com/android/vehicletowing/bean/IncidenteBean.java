package com.android.vehicletowing.bean;

public class IncidenteBean {

	private int incidenteId;
	private String incidenteType;
	private String incidenteDescription;
	
	private byte[] incidenteVehicleNoImage;
	private int incidenteUserId;
	private int incidenteTowingAgentId;
	private int incidenteFineAmount;
	private String incidenteDate;
	private Double incidentLat;
	private Double incidentLong;
	private int incidentStatus;
	private String noPlateText;
	
	
	
	
	
	public String getNoPlateText() {
		return noPlateText;
	}

	public void setNoPlateText(String noPlateText) {
		this.noPlateText = noPlateText;
	}

	public int getIncidentStatus() {
		return incidentStatus;
	}

	public void setIncidentStatus(int incidentStatus) {
		this.incidentStatus = incidentStatus;
	}

	public Double getIncidentLat() {
		return incidentLat;
	}

	public void setIncidentLat(Double incidentLat) {
		this.incidentLat = incidentLat;
	}

	public Double getIncidentLong() {
		return incidentLong;
	}

	public void setIncidentLong(Double incidentLong) {
		this.incidentLong = incidentLong;
	}

	public int getIncidenteId() {
		return incidenteId;
	}

	public void setIncidenteId(int incidenteId) {
		this.incidenteId = incidenteId;
	}

	public String getIncidenteType() {
		return incidenteType;
	}

	public void setIncidenteType(String incidenteType) {
		this.incidenteType = incidenteType;
	}

	public String getIncidenteDescription() {
		return incidenteDescription;
	}

	public void setIncidenteDescription(String incidenteDescription) {
		this.incidenteDescription = incidenteDescription;
	}

	
	public int getIncidenteTowingAgentId() {
		return incidenteTowingAgentId;
	}

	public void setIncidenteTowingAgentId(int incidenteTowingAgentId) {
		this.incidenteTowingAgentId = incidenteTowingAgentId;
	}

	public byte[] getIncidenteVehicleNoImage() {
		return incidenteVehicleNoImage;
	}

	public void setIncidenteVehicleNoImage(byte[] incidenteVehicleNoImage) {
		this.incidenteVehicleNoImage = incidenteVehicleNoImage;
	}

	public int getIncidenteUserId() {
		return incidenteUserId;
	}

	public void setIncidenteUserId(int incidenteUserId) {
		this.incidenteUserId = incidenteUserId;
	}

	
	public int getIncidenteFineAmount() {
		return incidenteFineAmount;
	}

	public void setIncidenteFineAmount(int incidenteFineAmount) {
		this.incidenteFineAmount = incidenteFineAmount;
	}

	public String getIncidenteDate() {
		return incidenteDate;
	}

	public void setIncidenteDate(String incidenteDate) {
		this.incidenteDate = incidenteDate;
	}

}
