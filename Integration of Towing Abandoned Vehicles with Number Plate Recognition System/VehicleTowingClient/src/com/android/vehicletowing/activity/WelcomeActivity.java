package com.android.vehicletowing.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	private static final String TAG = "com.android.seedblockalgorithm.WelcomeActivity";
	public static String serverIp;
	Button StartButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		StartButton = (Button) findViewById(R.id.StartButton);
		StartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (serverIp != null && !serverIp.equals("")) {
						Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(),"Please fill details using configure",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ipconfig_menu, menu);
		return true;
	}// end onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_settings:
			final Dialog dialog = new Dialog(WelcomeActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.ipconfig);

			// set title and cancelable

			dialog.setCancelable(true);
			// dialog.setTitle("IP Connection:");

			Button IpCancleButton = (Button) dialog.findViewById(R.id.CancleButton);
			IpCancleButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			Button IpSubmitButton = (Button) dialog.findViewById(R.id.SubmitButton);
			IpSubmitButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					EditText editTextipServer = (EditText) dialog.findViewById(R.id.editTextipServer);
					serverIp = editTextipServer.getText().toString();


					if (!serverIp.equals("")) {

						IPAddressValidator addressValidator = new IPAddressValidator();
						if(addressValidator.validate(serverIp))
						{
							Log.d(TAG, "Server IP: " + serverIp);
							Toast.makeText(WelcomeActivity.this,
									"Server IP: " + serverIp, Toast.LENGTH_LONG)
									.show();
							dialog.dismiss();
						}
						else
						{
							Toast.makeText(WelcomeActivity.this,
									"Invalid IP :" + serverIp, Toast.LENGTH_LONG)
									.show();
						}
						

					} else {
						Toast.makeText(WelcomeActivity.this,
								"Enter IP :" + serverIp, Toast.LENGTH_LONG)
								.show();
					}
				}
			});
			dialog.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}// end onOptionsItemSelected
}

class IPAddressValidator{

	private Pattern pattern;
	private Matcher matcher;

	private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public IPAddressValidator(){
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	/**
	 * Validate ip address with regular expression
	 * @param ip ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public boolean validate(final String ip){		  
		matcher = pattern.matcher(ip);
		return matcher.matches();	    	    
	}
}
