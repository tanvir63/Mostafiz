package com.fb.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class FacebookShareManager {
	private Activity parentActivity;
	private Activity childActivity;
	private FacebookConnector facebookConnector;
	
	public FacebookShareManager(Activity parentActivity,Activity childActivity,String facebookAppID,String facebookPermission) 
	{
		this.parentActivity = parentActivity;
		this.childActivity = childActivity;
		
		facebookConnector = new FacebookConnector(
				facebookAppID,
				parentActivity,
				childActivity.getApplicationContext(),
				new String[] { facebookPermission });
	}
	
	public void shareMessage(final String message){
		if (facebookConnector.getFacebook().isSessionValid()) {
			postMessageInThread(message);
		} else {
			SessionEvents.AuthListener listener = new SessionEvents.AuthListener() {

				@Override
				public void onAuthSucceed() {
					postMessageInThread(message);
				}

				@Override
				public void onAuthFail(String error) {

				}
			};
			SessionEvents.addAuthListener(listener);
			facebookConnector.login();
		}
	}
	@SuppressWarnings("unchecked")
	private void postMessageInThread(String message) {
		new UpdateFacebookStatusTask(message).execute();
	}
	@SuppressWarnings("rawtypes")
	private class UpdateFacebookStatusTask extends AsyncTask {
		private String message;
		private ProgressDialog myProgressDialog;
		public UpdateFacebookStatusTask(String message) {
			this.message = message;
		}

		@Override
		protected void onPreExecute() {
			myProgressDialog = ProgressDialog.show(parentActivity,
					"Please wait...", "Updating facebook status...", true);

		}

		@Override
		protected void onPostExecute(Object result) {
			myProgressDialog.dismiss();
			if (result == null) {
				Toast.makeText(childActivity.getBaseContext(), "Facebook updated !",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(childActivity.getBaseContext(),
						"Facebook could not be updated !", Toast.LENGTH_SHORT)
						.show();
			}
//			finish();
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			try {
				facebookConnector.postMessageOnWall(message);
			} catch (Exception ex) {
				Log.e("DREG", "Error sending msg", ex);
				return "fail";
			}
			return null;
		}
	}
}
