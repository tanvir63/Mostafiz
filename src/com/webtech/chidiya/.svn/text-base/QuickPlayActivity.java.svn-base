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
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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

public class QuickPlayActivity extends Activity {

	private ImageView imageView;
	private ImageView flyButton;
	private ImageView dontFlyButton;
	private TextView scoreView;
	private TextView scoreview;

	private Random random = new Random();
	private NoRepeatRandom nrr;
	private int count = 0;

	private int score = 0;
	private int status;

	private int firstValue;
	private int secondValue;

	private int buttonImage[] = { R.drawable.fly_button,
			R.drawable.dontfly_button };
	private String DIFFICULTY_LEVEL;

	private boolean isClick;

	private int countMismatch = 0;

	private boolean isGameOver;

	private int imageIndex = -1;

	private String flyText = "fly";
	private String dontFlyText = "dontfly";

	private int imagesToShow[] = new int[118];
	private int checkStatus[] = new int[118];
	private int soundsToPlay[] = new int[118];

	private Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_quick_play_level);
			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			AdView adview = (AdView) findViewById(R.id.adView);
			AdRequest re = new AdRequest();
			adview.loadAd(re);

			imageView = (ImageView) findViewById(R.id.imageView);
			scoreView = (TextView) findViewById(R.id.scoreView);

			scoreview = (TextView) findViewById(R.id.score);

			scoreView.setTypeface(font);
			scoreview.setTypeface(font);

			flyButton = (ImageView) findViewById(R.id.flyButton);
			// flyButton.setOnClickListener(this);
			flyButton.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_DOWN:
						if (isClick) {
							if (firstValue == 0) {
								status = 1;
							} else {
								status = 0;

							}
							if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
								if (status == checkStatus[count]) {
									score += 150;
								} else {
									score += 0;
									countMismatch++;
								}
							} else {
								if (status == checkStatus[count]) {
									score += 100;
								} else {
									score += 0;
									countMismatch++;
								}
							}
							isClick = false;
						}

						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							if (flyText.equals("fly")) {
								flyButton
										.setImageResource(R.drawable.onclick_fly);
							} else {
								flyButton
										.setImageResource(R.drawable.onclick_dontfly);
							}
							return true;

						} else if (event.getAction() == MotionEvent.ACTION_UP) {
							if (flyText.equals("fly")) {
								flyButton.setImageResource(R.drawable.fly);
							} else {
								flyButton.setImageResource(R.drawable.dontfly);
							}
							return true;

						}
						break;
					default:
						break;
					}
					if (SettingPrefs.getSoundEffect(QuickPlayActivity.this)) {
						Utils.play(QuickPlayActivity.this,
								R.raw.sound_any_click);
					}
					return false;
				}
			});

			dontFlyButton = (ImageView) findViewById(R.id.dontFlyButton);
			// dontFlyButton.setOnClickListener(this);
			dontFlyButton.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_UP:
						if (isClick) {
							if (firstValue == 1) {
								status = 1;
							} else {
								status = 0;
							}
							if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
								if (status == checkStatus[count]) {
									score += 150;
								} else {
									score += 0;
									countMismatch++;
								}
							} else {
								if (status == checkStatus[count]) {
									score += 100;
								} else {
									score += 0;
									countMismatch++;
								}
							}
							isClick = false;
						}
						if (event.getAction() == MotionEvent.ACTION_DOWN) {

							if (dontFlyText.equalsIgnoreCase("dontfly")) {
								dontFlyButton
										.setImageResource(R.drawable.onclick_dontfly);
							} else {
								dontFlyButton
										.setImageResource(R.drawable.onclick_fly);
							}
							return true;

						} else if (event.getAction() == MotionEvent.ACTION_UP) {
							if (dontFlyText.equalsIgnoreCase("dontfly")) {
								dontFlyButton
										.setImageResource(R.drawable.dontfly);
							} else {
								dontFlyButton.setImageResource(R.drawable.fly);
							}
							return true;
						}
						break;
					default:
						break;
					}
					if (SettingPrefs.getSoundEffect(QuickPlayActivity.this)) {
						Utils.play(QuickPlayActivity.this,
								R.raw.sound_any_click);
					}

					return false;
				}
			});

			findViewById(R.id.exitButton).setOnClickListener(
					new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {

							Intent cancelIntent = new Intent(
									QuickPlayActivity.this,
									HomeScreenActivity.class);
							cancelIntent
									.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(cancelIntent);
						}
					});

			getImageToShow();
			DIFFICULTY_LEVEL = SettingPrefs.getGameLevel(this);
			manageAnimation(imageView, imagesToShow, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getImageToShow() {
		nrr = new NoRepeatRandom(0, Constants.images.length - 1);
		for (int i = 0; i < Constants.images.length; i++) {
			int index = nrr.GetRandom();
			imagesToShow[i] = Constants.images[index];
			checkStatus[i] = Constants.flyStatus[index];
			soundsToPlay[i] = Constants.sounds[index];

		}
	}

	private void manageAnimation(final ImageView imageView, final int images[],
			final boolean forever) {

		if (SettingPrefs.getSoundEffect(this)) {
			Utils.play(QuickPlayActivity.this, soundsToPlay[count]);
		}
		Log.i("DREG", "Count missmatch =" + countMismatch);
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

		isClick = true;
		imageIndex++;

		imageView.setVisibility(View.INVISIBLE);
		imageView.setImageResource(images[imageIndex]);

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(Constants.QUICK_FADE_IN_DURATION);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setStartOffset(Constants.QUICK_FADE_IN_DURATION
				+ Constants.QUICK_TIME_BETWEEN);
		fadeOut.setDuration(Constants.QUICK_FADE_OUT_DURATION);

		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		animation.setRepeatCount(1);
		imageView.setAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				if (countMismatch == 0) {
					if (images.length - 1 > imageIndex) {
						count++;
						manageAnimation(imageView, images, forever);

					} else {
						if (forever == true) {
							manageAnimation(imageView, images, forever);
						}
					}
				} else if (countMismatch > 0) {
					imageIndex = -1;
					if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
						int highestScore = Utils.getHighestScore(
								QuickPlayActivity.this,
								Constants.QUICK_HARD_HIGHEST_SCORE);
						if (score > highestScore) {
							Utils.putHighestScore(QuickPlayActivity.this,
									Constants.QUICK_HARD_HIGHEST_SCORE, score);
						}
					} else {
						int highestScore = Utils.getHighestScore(
								QuickPlayActivity.this,
								Constants.QUICK_EASY_HIGHEST_SCORE);
						if (score > highestScore) {
							Utils.putHighestScore(QuickPlayActivity.this,
									Constants.QUICK_EASY_HIGHEST_SCORE, score);
						}
					}
					Intent intent = new Intent(QuickPlayActivity.this,
							GameOverActivity.class);
					intent.putExtra(Constants.KEY_LEVEL, 0);
					intent.putExtra(Constants.KEY_SCORE, score);
					startActivity(intent);
				}
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			public void onAnimationStart(Animation animation) {

			}
		});
		scoreView.setText("" + score);

	}

	private void showGameOverPopup() {
		countMismatch = 0;
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage("Game Over ! !")
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						})
				.setPositiveButton("Retry",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								getImageToShow();
								count = 0;
								score = 0;
								manageAnimation(imageView, imagesToShow, false);

							}
						});
		final AlertDialog alert = builder.create();
		alert.setTitle("Oops ! !");
		alert.show();
	}

}
