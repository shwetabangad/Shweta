package com.android.vehicletowing.activity;

import java.util.ArrayList;

import com.android.vehicletowing.bean.IncidenteBean;
import com.android.vehicletowing.bean.TowingAgentBean;

import android.app.Application;

public class ApplicationContext extends Application {

	// private ConfigBean ipConfigBean;
	private TowingAgentBean towingAgentBean;
	private IncidenteBean incidenteBean;
	private ArrayList<IncidenteBean> incidentBeanList;
	
	
	
	

	public ArrayList<IncidenteBean> getIncidentBeanList() {
		return incidentBeanList;
	}

	public void setIncidentBeanList(ArrayList<IncidenteBean> incidentBeanList) {
		this.incidentBeanList = incidentBeanList;
	}

	public IncidenteBean getIncidenteBean() {
		return incidenteBean;
	}

	public void setIncidenteBean(IncidenteBean incidenteBean) {
		this.incidenteBean = incidenteBean;
	}

	public TowingAgentBean getTowingAgentBean() {
		return towingAgentBean;
	}

	public void setTowingAgentBean(TowingAgentBean towingAgentBean) {
		this.towingAgentBean = towingAgentBean;
	}
	
	

	

	
}
