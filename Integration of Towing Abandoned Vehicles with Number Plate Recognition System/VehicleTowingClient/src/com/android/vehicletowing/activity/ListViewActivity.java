package com.android.vehicletowing.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.vehicletowing.bean.IncidenteBean;

public class ListViewActivity extends Activity {

	private static final String TAG = "com.android.vehicletowing";
	private ArrayList<IncidenteBean> incidentBeanList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		ApplicationContext appcontext =(ApplicationContext) getApplicationContext();
		incidentBeanList = appcontext.getIncidentBeanList();

		// Getting a reference to listview of main.xml layout file
		ListView listView = ( ListView ) findViewById(R.id.listview);

		IncidenteListAdapter incidentListAdapter = new IncidenteListAdapter(this, incidentBeanList);
		// Setting the adapter to the listView
		listView.setAdapter(incidentListAdapter);                           

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final IncidenteBean incidenteBean = incidentBeanList.get(arg2);
				final Dialog dialog = new Dialog(ListViewActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
				dialog.setContentView(R.layout.incidente_list_layout);
				// set title and cancelable
				ImageView incidentImageView = (ImageView)dialog.findViewById(R.id.incident_list_layout_ImageView);
				TextView fineAmount=(TextView)dialog.findViewById(R.id.incident_list_layout_Fine_amount_TextView);
				TextView status=(TextView)dialog.findViewById(R.id.incident_list_layout_status_TextView);
				TextView vehicleNo=(TextView)dialog.findViewById(R.id.incident_list_layout_vehicle_no_TextView);
				
				Bitmap bitmap = BitmapFactory.decodeByteArray(incidenteBean.getIncidenteVehicleNoImage(), 0, incidenteBean.getIncidenteVehicleNoImage().length);
				incidentImageView.setImageBitmap(bitmap);
				
				
				vehicleNo.setText("VehicleNo:" + incidenteBean.getNoPlateText());
				fineAmount.setText("FineAmount:" + incidenteBean.getIncidenteFineAmount());
				if(incidenteBean.getIncidentStatus()==0)
				{
				status.setText("Status:"+"Pending");
				}
				else
				{
					status.setText("Status:"+"Complete");
				}
				
				
				dialog.setCancelable(true);
				dialog.show();
				
				
			}
		});

	}
}