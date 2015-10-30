package com.webtech.chidiya;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.webtech.utils.Constants;
import com.webtech.utils.NoRepeatRandom;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdLevelActivity extends Activity implements OnClickListener {

	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView flyButton;
	private ImageView dontFlyButton;
	private TextView scoreView;
	private TextView scoreview;

	private Random random = new Random();
	private NoRepeatRandom nrr;
	private int count = 0;

	private int score = 0;
	private int status = 0;
	private int flyStatus = 0;

	private int randomValue;

	private int buttonImage[] = { R.drawable.fly_button,
			R.drawable.dontfly_button };
	
	private String flyText = "fly";
	private String dontFlyText = "dontfly";
	
	private String DIFFICULTY_LEVEL;
	private int firstValue;
	private int secondValue;
	private boolean isClick;

	private int firstImagesToShow[] = new int[21];
	private int secondImagesToShow[] = new int[21];
	private int checkFirstStatus[] = new int[21];
	private int checkSecondStatus[] = new int[21];
	private int imageIndex = -1;

	private Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_third_level);

			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			AdView adview = (AdView) findViewById(R.id.adView);
			AdRequest re = new AdRequest();
			adview.loadAd(re);

			imageView1 = (ImageView) findViewById(R.id.imageView1);
			imageView2 = (ImageView) findViewById(R.id.imageView2);

			scoreView = (TextView) findViewById(R.id.scoreView);
			scoreview = (TextView) findViewById(R.id.score);

			scoreView.setTypeface(font);
			scoreview.setTypeface(font);

			findViewById(R.id.exitButton).setOnClickListener(this);

			flyButton = (ImageView) findViewById(R.id.flyButton);
			flyButton.setOnClickListener(this);

			dontFlyButton = (ImageView) findViewById(R.id.dontFlyButton);
			dontFlyButton.setOnClickListener(this);

			DIFFICULTY_LEVEL = SettingPrefs.getGameLevel(this);

			getImageToShow();
			manageAnimation(imageView1, imageView2, firstImagesToShow,
					secondImagesToShow, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getImageToShow() {

		nrr = new NoRepeatRandom(0, Constants.images.length - 1);
		for (int i = 0; i < 21; i++) {
			int index = nrr.GetRandom();
			firstImagesToShow[i] = Constants.images[index];
			checkFirstStatus[i] = Constants.flyStatus[index];

		}

		for (int i = 0; i < 21; i++) {
			int index = nrr.GetRandom();
			secondImagesToShow[i] = Constants.images[index];
			checkSecondStatus[i] = Constants.flyStatus[index];

		}

	}

	private void manageAnimation(final ImageView imageView1,
			final ImageView imageView2, final int firsImages[],
			final int secondImages[], final boolean forever) {

		randomValue = random.nextInt(2);

		BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(
				R.drawable.arrow);
		Bitmap arrow = drawable1.getBitmap();

		if (count != 20) {
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				firstValue = random.nextInt(2);

				if (firstValue == 0) {
					flyText = "fly";
					dontFlyText = "dontfly";
					secondValue = 1;
				} else {
					flyText = "dontfly";
					dontFlyText = "fly";
					secondValue = 0;
				}

				flyButton.setImageResource(buttonImage[firstValue]);
				dontFlyButton.setImageResource(buttonImage[secondValue]);
			}
		}

		imageIndex++;
		isClick = true;

		Bitmap leftBitmap = BitmapFactory.decodeResource(getResources(),
				firsImages[imageIndex]);
		int leftWidth = leftBitmap.getWidth();
		int leftHeight = leftBitmap.getHeight();

		Bitmap leftResized = Utils.getResizedBitmap(leftBitmap,
				(leftWidth * 3) / 4, (leftHeight * 3) / 4);

		Bitmap rightBitmap = BitmapFactory.decodeResource(getResources(),
				secondImages[imageIndex]);

		Bitmap rightResized = Utils.getResizedBitmap(rightBitmap,
				(leftWidth * 3) / 4, (leftHeight * 3) / 4);

		imageView1.setVisibility(View.INVISIBLE);
		imageView2.setVisibility(View.INVISIBLE);
		if (count != 20) {
			if (randomValue == 0) {
				Bitmap combined = Utils.combineImages(leftResized, arrow);
				imageView1.setImageBitmap(combined);
				imageView2.setImageBitmap(rightResized);

			} else {
				imageView1.setImageBitmap(leftResized);
				Bitmap combined = Utils.combineImages(rightResized, arrow);
				imageView2.setImageBitmap(combined);
			}
		}

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(Constants.THIRD_FADE_IN_DURATION);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setStartOffset(Constants.THIRD_FADE_IN_DURATION
				+ Constants.THIRD_TIME_BETWEEN);
		fadeOut.setDuration(Constants.THIRD_FADE_OUT_DURATION);

		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		animation.setRepeatCount(1);
		if (count != 20) {
			imageView1.setAnimation(animation);
			imageView2.setAnimation(animation);
		}

		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				if (firsImages.length - 1 > imageIndex) {
					count++;
					manageAnimation(imageView1, imageView2, firstImagesToShow,
							secondImagesToShow, forever);

				} else {
					if (forever == true) {
						manageAnimation(imageView1, imageView2,
								firstImagesToShow, secondImagesToShow, forever);
					}
				}
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			public void onAnimationStart(Animation animation) {

			}
		});
		scoreView.setText("" + score);
		Log.i("DREG", "Count =" + count);

		if (count == 20) {
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				int highestScore = Utils.getHighestScore(this,
						Constants.THIRD_HARD_HIGHEST_SCORE);
				if (score > highestScore) {
					Utils.putHighestScore(this,
							Constants.THIRD_HARD_HIGHEST_SCORE, score);
				}

				if (score < 2250) {
					// showGameOverPopup();
					Intent intent = new Intent(ThirdLevelActivity.this,
							GameOverActivity.class);
					intent.putExtra(Constants.KEY_LEVEL, 3);
					intent.putExtra(Constants.KEY_SCORE, score);
					startActivity(intent);

				} else {
					Utils.putLevelUnlocked(this,
							Constants.FOURTH_LEVEL_UNLOCKED, true);
					LevelScreenActivity.fourthLevel
							.setImageResource(R.drawable.icon_level_4_unlocked);
					successulCompleted();
				}
			} else {
				int highestScore = Utils.getHighestScore(this,
						Constants.THIRD_EASY_HIGHEST_SCORE);
				if (score > highestScore) {
					Utils.putHighestScore(this,
							Constants.THIRD_EASY_HIGHEST_SCORE, score);
				}
				if (score < 1500) {
					// showGameOverPopup();
					Intent intent = new Intent(ThirdLevelActivity.this,
							GameOverActivity.class);
					intent.putExtra(Constants.KEY_LEVEL, 3);
					intent.putExtra(Constants.KEY_SCORE, score);
					startActivity(intent);
				} else {
					Utils.putLevelUnlocked(this,
							Constants.FOURTH_LEVEL_UNLOCKED, true);
					LevelScreenActivity.fourthLevel
							.setImageResource(R.drawable.icon_level_4_unlocked);
					successulCompleted();
				}
			}
		}
	}

	private void successulCompleted() {
		Intent intent = new Intent(ThirdLevelActivity.this,
				SuccessfulActivity.class);
		intent.putExtra(Constants.KEY_LEVEL, 3);
		intent.putExtra(Constants.KEY_SCORE, score);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flyButton:
			if (isClick) {
//				if (firstValue == 0) {
				if (flyText.equals("fly")) {
					status = 1;
				} else {
					status = 0;
				}

				if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
					if (randomValue == 0) {
						if (status == checkFirstStatus[count]) {
							score += 150;
						} else {
							score += 0;
						}
					} else {
						if (status == checkSecondStatus[count]) {
							score += 150;
						} else {
							score += 0;
						}
					}

				} else {

					if (randomValue == 0) {
						if (status == checkFirstStatus[count]) {
							score += 100;
						} else {
							score += 0;
						}
					} else {
						if (status == checkSecondStatus[count]) {
							score += 100;
						} else {
							score += 0;
						}
					}

				}
				isClick = false;
			}
			break;
		case R.id.dontFlyButton:
			if (isClick) {
//				if (firstValue == 1) {
				if(dontFlyText.equalsIgnoreCase("dontfly"))	
				{
					status = 0;
				} else {
					status = 1;
				}

				if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
					if (randomValue == 0) {
						if (status == checkFirstStatus[count]) {
							score += 150;
						} else {
							score += 0;
						}
					} else {
						if (status == checkSecondStatus[count]) {
							score += 150;
						} else {
							score += 0;
						}
					}

				} else {

					if (randomValue == 0) {
						if (status == checkFirstStatus[count]) {
							score += 100;
						} else {
							score += 0;
						}
					} else {
						if (status == checkSecondStatus[count]) {
							score += 100;
						} else {
							score += 0;
						}
					}

				}
				isClick = false;
			}
			break;
		case R.id.exitButton:
			Intent cancelIntent = new Intent(ThirdLevelActivity.this,
					HomeScreenActivity.class);
			cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(cancelIntent);
			break;
		default:
			break;
		}
		if (SettingPrefs.getSoundEffect(this)) 
		{
			Utils.play(ThirdLevelActivity.this, R.raw.sound_any_click);
		}
	}

}
