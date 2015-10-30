package com.webtech.chidiya;

import com.fb.android.FacebookShareManager;
import com.webtech.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SuccessfulActivity extends Activity implements OnClickListener {

	private int level;
	private int score;
	private Typeface font;
	private TextView levelView;
	private String DIFFICULTY_LEVEL;

	private String APP_URL = "https://play.google.com/store/apps/details?id=com.webtech.chidiya";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_successful);

			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			levelView = (TextView) findViewById(R.id.levelView);
			levelView.setTypeface(font);

			DIFFICULTY_LEVEL = SettingPrefs.getGameLevel(this);

			level = getIntent().getIntExtra(Constants.KEY_LEVEL, 1);
			score = getIntent().getIntExtra(Constants.KEY_SCORE, score);
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				levelView.setText("Level " + level
						+ " (Hard mode) Completed Successfully !");
			} else {
				levelView.setText("Level " + level
						+ " (easy mode) Completed Successfully !");
			}

			findViewById(R.id.nextButton).setOnClickListener(this);
			findViewById(R.id.cancelButton).setOnClickListener(this);
			findViewById(R.id.facebookButton).setOnClickListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.nextButton:
			switch (level) {
			case 1:
				// startActivity(new Intent(SuccessfulActivity.this,
				// SecondLevelActivity.class));
				Intent secondIntent = new Intent(SuccessfulActivity.this,
						InstructionActivity.class);
				secondIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				secondIntent.putExtra(Constants.KEY_LEVEL, 2);
				startActivity(secondIntent);
				break;
			case 2:
				// startActivity(new Intent(SuccessfulActivity.this,
				// ThirdLevelActivity.class));
				Intent thirdIntent = new Intent(SuccessfulActivity.this,
						InstructionActivity.class);
				thirdIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				thirdIntent.putExtra(Constants.KEY_LEVEL, 3);
				startActivity(thirdIntent);
				break;
			case 3:
				// startActivity(new Intent(SuccessfulActivity.this,
				// FourthLevelActivity.class));
				Intent fourthIntent = new Intent(SuccessfulActivity.this,
						InstructionActivity.class);
				fourthIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				fourthIntent.putExtra(Constants.KEY_LEVEL, 4);
				startActivity(fourthIntent);
				break;
			case 4:
				// startActivity(new Intent(SuccessfulActivity.this,
				// FifthLevelActivity.class));
				Intent fifthIntent = new Intent(SuccessfulActivity.this,
						InstructionActivity.class);
				fifthIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				fifthIntent.putExtra(Constants.KEY_LEVEL, 5);
				startActivity(fifthIntent);
				break;
			default:
				break;
			}
			break;
		case R.id.cancelButton:
			Intent intent = new Intent(SuccessfulActivity.this,
					HomeScreenActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.facebookButton:
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				faceBookSharing("I have just scored "
						+ score
						+ " in Chidiya Udd Game in Level "
						+ level
						+ " (Hard mode).\n\n Download and Play this Game on your Android"
						+ " Phone. " + APP_URL);
			} else {
				faceBookSharing("I have just scored "
						+ score
						+ " in Chidiya Udd Game in Level "
						+ level
						+ " (Easy Mode).\n\n Download and Play this Game on your Android"
						+ " Phone. " + APP_URL);
			}
			break;

		default:
			break;
		}

	}

	private void faceBookSharing(String message) {
		FacebookShareManager fsm = new FacebookShareManager(this, this,
				Constants.FACEBOOK_APP_ID, Constants.FACEBOOK_PERMISSION);
		fsm.shareMessage(message);

	}

}
