package com.webtech.chidiya;

import com.webtech.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private String userName;
	private String password;	
	private int uId;
	private int pId;
	private SharedPreferences prefs;
	
    /** Called when the activity is first created. */
	private TextView counterView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	try {
    		 super.onCreate(savedInstanceState);
//            setContentView(R.layout.layout_splash);
            showTimes();
		} catch (Exception e) {
			e.printStackTrace();
		}       
    }
    
    private void showTimes(){
    	try {
			new AsyncTask<String, String, String>() {
				@Override
				protected void onPreExecute() {
					try {
											
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				@Override
				protected String doInBackground(String... arg0) {
					try {
						Thread.sleep(Constants.SPLASH_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(String unused) {
					Intent intent=new Intent(SplashActivity.this,HomeScreenActivity.class);
					startActivity(intent);
				}
			}.execute();

		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
    }
    
    
}