package com.webtech.chidiya;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.webtech.utils.Constants;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeScreenActivity extends Activity implements OnClickListener {
	
	private ImageView newGameView;
	private ImageView settingsView;
	private ImageView quickView;
	private ImageView scoreView;
	private ImageView instructionView;
	private ImageView rateView;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			showExitPopup();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home_screen);

		newGameView = (ImageView) findViewById(R.id.newGameView);
		newGameView.setOnClickListener(this);

		settingsView = (ImageView) findViewById(R.id.settingsView);
		settingsView.setOnClickListener(this);

		quickView = (ImageView) findViewById(R.id.quickView);
		quickView.setOnClickListener(this);

		scoreView = (ImageView) findViewById(R.id.scoreView);
		scoreView.setOnClickListener(this);

		instructionView = (ImageView) findViewById(R.id.helpView);
		instructionView.setOnClickListener(this);

		rateView = (ImageView) findViewById(R.id.rateView);
		rateView.setOnClickListener(this);

		String game_level = SettingPrefs.getGameLevel(this);
		Log.i("DREG", "Game Level =" + game_level);
		Log.i("DREG", "Fly status size =" + Constants.flyStatus.length);
		Log.i("DREG", "Images sizes = " + Constants.images.length);
		Log.i("DREG", "Sounds sizes =" + Constants.sounds.length);
	}

	private void runAnimation(ImageView ImageView) {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.set);
		anim.reset();
		ImageView.clearAnimation();
		ImageView.startAnimation(anim);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.newGameView:
			startActivity(new Intent(HomeScreenActivity.this,
					LevelScreenActivity.class));
			break;
		case R.id.settingsView:
			Intent intent = new Intent(HomeScreenActivity.this, SettingPrefs.class);
			startActivity(intent);
			break;
		case R.id.quickView:
			startActivity(new Intent(HomeScreenActivity.this,
					QuickPlayActivity.class));

			break;
		case R.id.scoreView:
			startActivity(new Intent(HomeScreenActivity.this, ScoreActivity.class));
			break;
		case R.id.helpView:
			startActivity(new Intent(HomeScreenActivity.this,
					HowToPlayActivity.class));
			break;
		case R.id.rateView:
			rateApplication();
			break;
		default:
			break;
		}
		if (SettingPrefs.getSoundEffect(this)) {
			Utils.play(HomeScreenActivity.this, R.raw.sound_any_click);
		}

	}

	private void rateApplication() {
		Uri uri = Uri.parse("market://details?id=" + getPackageName());
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
			startActivity(goToMarket);
		} catch (Exception e) {
			Toast.makeText(this,
					"\t\t\tOops ! !\n\nThe application is not in market ! !",
					Toast.LENGTH_LONG).show();
		}
	}

	private void showExitPopup() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you want to exit?")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {
						finish();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								dialog.cancel();

							}
						});

		final AlertDialog alert = builder.create();
		alert.show();
	}
}