package com.android.vehicletowing.activity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.vehicletowing.bean.IncidenteBean;
import com.android.vehicletowing.bean.TowingAgentBean;
import com.android.vehicletowing.webService.WebServiceParser;

public class TowingAgentActivity extends Activity {
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in
	private static final String TAG = "com.android.trafficpolice.activity";
																	// Milliseconds
	private Button submit;
	private Button incidenteButton;
	private Button showIncidentButton;
	private LocationManager locationManager;
	private Button logoutButton;
	byte[] userImageByteArray = null ;
	private ArrayList<IncidenteBean> incidentBeanList;
	GPSTracker gps;
	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_towing_agent);
		gps = new GPSTracker(TowingAgentActivity.this);

        if(gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Log.d(TAG, "LATITUDE"+latitude);
            Log.d(TAG, "LONGITUDE"+longitude);
            
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            
        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }
		
		image=(ImageView)findViewById(R.id.vehicle_no_image_view);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener(
						this));

		logoutButton = (Button) findViewById(R.id.LogoutButton);
		logoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				Intent intent = new Intent(TowingAgentActivity.this,
						WelcomeActivity.class);
				startActivity(intent);
			}
		}); // END logoutButton .setOnClickListener

		submit = (Button) findViewById(R.id.submitIncident);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IncidentTask task =new IncidentTask();
				task.execute();
			}
		}); // END updateLocation .setOnClickListener

		incidenteButton = (Button) findViewById(R.id.NewIncidenteButton);
		incidenteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View paramView) {
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 0);
			}
		});
		
		
		showIncidentButton=(Button)findViewById(R.id.showIncidentBotton);
		showIncidentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				ShowIncidentTask task=new ShowIncidentTask();
				task.execute();
				
				
			}
		});
		
		
	}
	//...........for camera......
		// Call Back method  to get the Message form other Activity  
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);

			try{
				//converting image into bitmap
				Bitmap bp = (Bitmap) data.getExtras().get("data");
				image.setImageBitmap(bp);

				//Convert binary image into String so that we can send it to server..
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bp.compress(Bitmap.CompressFormat.PNG, 50, stream);
				userImageByteArray = stream.toByteArray();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}

		@Override
		protected void onDestroy() {
			super.onDestroy();
		}

		
		
		private	class IncidentTask extends AsyncTask<String, Void, IncidenteBean> 
		{
			@SuppressWarnings("unused")
			@Override
			protected IncidenteBean doInBackground(String... urls) 
			{
				//String response = urls[0];
				WebServiceParser webServiceParser = new WebServiceParser(TowingAgentActivity.this,
						"http://" + WelcomeActivity.serverIp + ":8080/VehicleTowingServer/rest/webService/");
				Map<String, String> params = new HashMap<String, String>();


				TowingAgentBean bean= ((ApplicationContext)TowingAgentActivity.this.getApplicationContext()).getTowingAgentBean();
				
				int  userid =bean.getTowing_agent_id();
				
				
				Log.d(TAG,"In side doInbackground USERid "+userid);
				Log.d(TAG, "In side doInbackgroundLATITUDE"+gps.getLatitude());
	            Log.d(TAG, "In side doInbackground LONGITUDE"+gps.getLongitude());
				
	            params.put("userid",userid+"");
				params.put("lat",gps.getLatitude()+"");
				params.put("longitude",gps.getLongitude()+"");
				params.put("imageData", Base64.encodeBytes(userImageByteArray));
				


				IncidenteBean incidenteBean = webServiceParser.AddNewIncidente(params);
				((ApplicationContext)TowingAgentActivity.this.getApplicationContext()).setIncidenteBean(incidenteBean);
				return incidenteBean;
			}
			@Override
			protected void onPostExecute(IncidenteBean bean)
			{
				if(bean != null)
				{
					Toast.makeText(TowingAgentActivity.this, "Successful.", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(TowingAgentActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
				}
			}
		}
		
		
		private	class ShowIncidentTask extends AsyncTask<String,String,String> 
		{
			@SuppressWarnings("unused")
			@Override
			protected String doInBackground(String... urls) 
			{
				//String response = urls[0];
				WebServiceParser webServiceParser = new WebServiceParser(TowingAgentActivity.this,
						"http://" + WelcomeActivity.serverIp + ":8080/VehicleTowingServer/rest/webService/");
				Map<String, String> params = new HashMap<String, String>();


TowingAgentBean bean= ((ApplicationContext)TowingAgentActivity.this.getApplicationContext()).getTowingAgentBean();
				
				int  userid =bean.getTowing_agent_id();
				
			    params.put("agentid",userid+"");
				incidentBeanList = webServiceParser.ShowAllIncidente(params);
				((ApplicationContext)TowingAgentActivity.this.getApplicationContext()).setIncidentBeanList(incidentBeanList);
				return incidentBeanList+"";
			}
			@Override
			protected void onPostExecute(String kal)
			{
				
				Intent intent = new Intent(TowingAgentActivity.this,ListViewActivity.class);
				startActivity(intent);

			}
		
		
		}
}

