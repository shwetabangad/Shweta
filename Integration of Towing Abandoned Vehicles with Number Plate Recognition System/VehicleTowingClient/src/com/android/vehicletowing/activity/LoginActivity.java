package com.android.vehicletowing.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.vehicletowing.bean.TowingAgentBean;
import com.android.vehicletowing.webService.WebServiceParser;

public class LoginActivity extends Activity {
	//static int trafficPoliceID;
	private EditText pass_word;
	private EditText user_name;
	private Button cancleButton;
	private Button loginButton;
	private static final String TAG = "com.android.trafficpolice.activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		user_name = (EditText) findViewById(R.id.editTextUserName);
		pass_word = (EditText) findViewById(R.id.editTextPassword);

		cancleButton = (Button) findViewById(R.id.CancleButton);
		cancleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				LoginActivity.this.finish();
				// finish();
			}
		});

		loginButton = (Button) findViewById(R.id.LoginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (user_name.getText().toString().equals("")) {
					Toast.makeText(LoginActivity.this,
							"Please Enter User Name", Toast.LENGTH_SHORT)
							.show();
				} else if (pass_word.getText().toString().equals("")) {
					Toast.makeText(LoginActivity.this, "Please Enter Password",
							Toast.LENGTH_SHORT).show();
				} else {
					TowingAgentTask task = new TowingAgentTask();
					task.execute(new String[] {});
				}
			}
		});

	}

	private class TowingAgentTask extends
	AsyncTask<String, Void, TowingAgentBean> {


		private final ProgressDialog dialog = new ProgressDialog(
				LoginActivity.this);

		// can use UI thread here
		protected void onPreExecute() {
			this.dialog.setMessage("Please Wait...");
			this.dialog.show();
		}

		@Override
		protected TowingAgentBean doInBackground(String... urls) {

			// String response = urls[0];
			System.out.println("IP  " + WelcomeActivity.serverIp);
			WebServiceParser webServiceParser = new WebServiceParser(
					LoginActivity.this, "http://" + WelcomeActivity.serverIp
					+ ":8080/VehicleTowingServer/rest/webService/");
			Map<String, String> params = new HashMap<String, String>();
			params.put("UserName", user_name.getText().toString());
			params.put("PassWord", pass_word.getText().toString());

			TowingAgentBean agentbean = webServiceParser.TowingAgentValidate(params);
			((ApplicationContext)LoginActivity.this.getApplicationContext()).setTowingAgentBean(agentbean);

			return agentbean;
		}

		@Override
		protected void onPostExecute(TowingAgentBean agentbean) {

			this.dialog.cancel();

			if (agentbean != null & agentbean.getTowing_agent_id()>0) {

				//	int aid=agentbean.getTowing_agent_id();
				//	Log.d(TAG, "AGENTID"+aid);

				ApplicationContext appcontext =(ApplicationContext) getApplicationContext();
				TowingAgentBean towintAgentbean = appcontext.getTowingAgentBean();
				appcontext.setTowingAgentBean(towintAgentbean);




				Intent intent = new Intent(LoginActivity.this,TowingAgentActivity.class);
				startActivity(intent);
			} else
				Toast.makeText(LoginActivity.this, "Login failed.",
						Toast.LENGTH_SHORT).show();
			user_name.setText("");
			pass_word.setText("");

		}
	}

}
