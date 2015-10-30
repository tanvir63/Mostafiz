package com.webtech.chidiya;

import com.fb.android.FacebookShareManager;
import com.webtech.utils.Constants;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverActivity extends Activity implements OnClickListener {

	private ImageView restartButton;
	private ImageView facebookButton;
	private ImageView cancelButton;
	private int level;
	private String levelStr;
	private int score;
	private Typeface font;
	private TextView gameOverView;

	private String APP_URL = "https://play.google.com/store/apps/details?id=com.webtech.chidiya";
	private String DIFFICULTY_LEVEL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_game_over);

			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			restartButton = (ImageView) findViewById(R.id.restartButton);
			restartButton.setOnClickListener(this);

			DIFFICULTY_LEVEL = SettingPrefs.getGameLevel(this);

			gameOverView = (TextView) findViewById(R.id.gameOverView);
			gameOverView.setTypeface(font);

			facebookButton = (ImageView) findViewById(R.id.facebookButton);
			facebookButton.setOnClickListener(this);

			cancelButton = (ImageView) findViewById(R.id.cancelButton);
			cancelButton.setOnClickListener(this);

			level = getIntent().getIntExtra(Constants.KEY_LEVEL, 1);

			if (level == 0) {
				levelStr = "Quick Play";
			} else {
				levelStr = "" + level;
			}

			score = getIntent().getIntExtra(Constants.KEY_SCORE, 0);
			if (SettingPrefs.getSoundEffect(this)) {
				Utils.play(GameOverActivity.this, R.raw.sound_game_over);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.restartButton:
			switch (level) {
			case 0:
				Intent quickIntent = new Intent(GameOverActivity.this,
						QuickPlayActivity.class);
				quickIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(quickIntent);
				break;
			case 1:
				Intent firstIntent = new Intent(GameOverActivity.this,
						FirstLevelActivity.class);
				firstIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(firstIntent);
				break;
			case 2:
				Intent secondIntent = new Intent(GameOverActivity.this,
						SecondLevelActivity.class);
				secondIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(secondIntent);
				break;
			case 3:
				Intent thirdIntent = new Intent(GameOverActivity.this,
						ThirdLevelActivity.class);
				thirdIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(thirdIntent);
				break;
			case 4:
				Intent fourthIntent = new Intent(GameOverActivity.this,
						FourthLevelActivity.class);
				// fourthIntent.putExtra(Constants.KEY_LEVEL, 4);
				fourthIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(fourthIntent);
				break;
			case 5:
				Intent fifthIntent = new Intent(GameOverActivity.this,
						FifthLevelActivity.class);
				// fifthIntent.putExtra(Constants.KEY_LEVEL, 5);
				fifthIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(fifthIntent);
				break;
			}
			break;
		case R.id.facebookButton:
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				faceBookSharing("I have just scored "
						+ score
						+ " in Chidiya Udd Game in Level "
						+ levelStr
						+ " (Hard mode).\n\nDownload and Play this Game on your Android"
						+ " Phone. " + APP_URL);
			} else {
				faceBookSharing("I have just scored "
						+ score
						+ " in Chidiya Udd Game in Level "
						+ levelStr
						+ " (Easy Mode).\n\nDownload and Play this Game on your Android"
						+ " Phone. " + APP_URL);
			}
			break;
		case R.id.cancelButton:
			Intent intent = new Intent(GameOverActivity.this,
					HomeScreenActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
		if (SettingPrefs.getSoundEffect(this)) {
			Utils.play(GameOverActivity.this, R.raw.sound_any_click);
		}
	}

	private void faceBookSharing(String message) {
		FacebookShareManager fsm = new FacebookShareManager(this, this,
				Constants.FACEBOOK_APP_ID, Constants.FACEBOOK_PERMISSION);
		fsm.shareMessage(message);

	}

}
