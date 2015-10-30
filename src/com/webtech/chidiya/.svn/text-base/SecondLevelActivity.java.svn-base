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

public class SecondLevelActivity extends Activity implements OnClickListener {

	private ImageView imageView;
	private ImageView flyButton;
	private ImageView dontFlyButton;
	private TextView scoreView;
	private TextView scoreview;

	private JSONArray ja = null;
	private Random random = new Random();
	private NoRepeatRandom nrr;
	private int count = 0;

	private Timer timer = new Timer();
	private TimerTask task;
	private int delay = 0;
	private int period = 2000;

	private int score = 0;
	private int status = 0;
	private int flyStatus = 0;

	private int firstValue;
	private int secondValue;

	private int buttonImage[] = { R.drawable.fly_button,
			R.drawable.dontfly_button };
	private String DIFFICULTY_LEVEL;

	private String flyText = "fly";
	private String dontFlyText = "dontfly";

	private boolean isClick;

	private int imagesToShow[] = new int[21];
	private int checkStatus[] = new int[21];
	private int soundsToPlay[] = new int[21];

	private int imageIndex = -1;
	private int timeDifference;
	private Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_second_level);

			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			AdView adview = (AdView) findViewById(R.id.adView);
			AdRequest re = new AdRequest();
			adview.loadAd(re);

			imageView = (ImageView) findViewById(R.id.imageView);
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
			manageAnimation(imageView, imagesToShow, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void imageSlideShow() {
		final Handler handler = new Handler();
		final Runnable updateResults = new Runnable() {
			public void run() {
				try {
					if (count <= 20) {

						if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
							firstValue = random.nextInt(2);

							if (firstValue == 0) {
								secondValue = 1;
							} else {
								secondValue = 0;
							}

							flyButton
									.setImageResource((buttonImage[firstValue]));
							dontFlyButton
									.setImageResource(buttonImage[secondValue]);
						}

						imageView.setVisibility(View.GONE);
						changeImage();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				handler.post(updateResults);
			}
		}, delay, Constants.SECOND_LEVEL_PERIOD);

	}

	private void changeImage() throws Exception {

		int position = nrr.GetRandom();
		String filename;
		int imgId = R.drawable.icon_bird;

		try {
			filename = ja.getJSONObject(position).getString("image")
					.toLowerCase();
			imgId = getResources().getIdentifier(filename, "raw",
					getClass().getPackage().getName());

			flyStatus = ja.getJSONObject(position).getInt("status");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// animate(imageView, imgId, Constants.SECOND_LEVEL_ANIMATE_TIME);
		count++;
	}

	private final void animate(final ImageView imageView, final int imgId,
			final int animTime) throws Exception {
		isClick = true;
		imageView.setVisibility(View.VISIBLE);

		BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(
				imgId);
		Bitmap bitmap = drawable.getBitmap();

		final TranslateAnimation anim = new TranslateAnimation(-2
				* Math.max(imageView.getWidth(), bitmap.getWidth()), 0, 0, 0);

		/* new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta) */

		anim.setDuration(animTime);
		anim.setFillAfter(true);
		anim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
			}
		});
		imageView.startAnimation(anim);
		imageView.setImageBitmap(bitmap);
		scoreView.setText("" + score);

		if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
			if (score >= 2250) {
				Utils.putLevelUnlocked(this, Constants.THIRD_LEVEL_UNLOCKED,
						true);
				LevelScreenActivity.thirdLevel
						.setImageResource(R.drawable.icon_level_3_unlocked);
			}

		} else {
			if (score >= 1500) {
				Utils.putLevelUnlocked(this, Constants.THIRD_LEVEL_UNLOCKED,
						true);
				LevelScreenActivity.thirdLevel
						.setImageResource(R.drawable.icon_level_3_unlocked);

			}
		}

		if (count == 20) {
			imageView.setImageBitmap(null);
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				int highestScore = Utils.getHighestScore(this,
						Constants.SECOND_HARD_HIGHEST_SCORE);
				if (score > highestScore) {
					Utils.putHighestScore(this,
							Constants.SECOND_HARD_HIGHEST_SCORE, score);
				}

				if (score < 2250) {
					showGameOverPopup();
				} else {
					Utils.showAlertMessage(SecondLevelActivity.this,
							R.drawable.icon_correct, "Successful Completion",
							"You have completed Second Level successfully");
				}
			} else {
				int highestScore = Utils.getHighestScore(this,
						Constants.SECOND_EASY_HIGHEST_SCORE);
				if (score > highestScore) {
					Utils.putHighestScore(this,
							Constants.SECOND_EASY_HIGHEST_SCORE, score);
				}
				if (score < 1500) {
					imageIndex = -1;
					showGameOverPopup();
				} else {
					Utils.showAlertMessage(SecondLevelActivity.this,
							R.drawable.icon_correct, "Successful Completion",
							"You have completed Second Level successfully");
				}
			}
		}
	}

	private void manageAnimation(final ImageView imageView, final int images[],
			final boolean forever) {

		if (count != 20) {
			if (SettingPrefs.getSoundEffect(this)) {
				Utils.play(SecondLevelActivity.this, soundsToPlay[count]);
			}

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

		isClick = true;
		imageIndex++;

		imageView.setVisibility(View.INVISIBLE);
		if (count != 20) {
			imageView.setImageResource(images[imageIndex]);
		}

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(Constants.SECOND_FADE_IN_DURATION);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setStartOffset(Constants.SECOND_FADE_IN_DURATION
				+ Constants.SECOND_TIME_BETWEEN);
		fadeOut.setDuration(Constants.SECOND_FADE_OUT_DURATION);

		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		animation.setRepeatCount(1);
		if (count != 20) {
			imageView.setAnimation(animation);
		}

		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				if (images.length - 1 > imageIndex) {
					count++;
					manageAnimation(imageView, images, forever);
				} else {
					if (forever == true) {
						manageAnimation(imageView, images, forever);

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

		if (count == 20) {
			if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
				int highestScore = Utils.getHighestScore(this,
						Constants.SECOND_HARD_HIGHEST_SCORE);
				if (score > highestScore) {
					Utils.putHighestScore(this,
							Constants.SECOND_HARD_HIGHEST_SCORE, score);
				}

				if (score < 2250) {
					Intent intent = new Intent(SecondLevelActivity.this,
							GameOverActivity.class);
					intent.putExtra(Constants.KEY_LEVEL, 2);
					intent.putExtra(Constants.KEY_SCORE, score);
					startActivity(intent);
				} else {
					Utils.putLevelUnlocked(this,
							Constants.THIRD_LEVEL_UNLOCKED, true);
					LevelScreenActivity.thirdLevel
							.setImageResource(R.drawable.icon_level_3_unlocked);
					successulCompleted();
				}
			} else {
				int highestScore = Utils.getHighestScore(this,
						Constants.SECOND_EASY_HIGHEST_SCORE);
				if (score > highestScore) {
					Utils.putHighestScore(this,
							Constants.SECOND_EASY_HIGHEST_SCORE, score);
				}
				if (score < 1500) {
					// showGameOverPopup();
					Intent intent = new Intent(SecondLevelActivity.this,
							GameOverActivity.class);
					intent.putExtra(Constants.KEY_LEVEL, 2);
					intent.putExtra(Constants.KEY_SCORE, score);
					startActivity(intent);
				} else {
					Utils.putLevelUnlocked(this,
							Constants.THIRD_LEVEL_UNLOCKED, true);
					LevelScreenActivity.thirdLevel
							.setImageResource(R.drawable.icon_level_3_unlocked);
					successulCompleted();
				}
			}
		}
	}

	private void successulCompleted() {
		Intent intent = new Intent(SecondLevelActivity.this,
				SuccessfulActivity.class);
		intent.putExtra(Constants.KEY_LEVEL, 2);
		intent.putExtra(Constants.KEY_SCORE, score);
		startActivity(intent);
	}

	private void showGameOverPopup() {
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
								imageIndex = -1;
								count = 0;
								score = 0;
								getImageToShow();
								manageAnimation(imageView, imagesToShow, false);

							}
						});
		final AlertDialog alert = builder.create();
		alert.setTitle("Oops ! !");
		alert.show();
	}

	private void getImageToShow() {

		nrr = new NoRepeatRandom(0, Constants.images.length - 1);
		for (int i = 0; i < 21; i++) {
			int index = nrr.GetRandom();
			imagesToShow[i] = Constants.images[index];
			checkStatus[i] = Constants.flyStatus[index];
			soundsToPlay[i] = Constants.sounds[index];
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flyButton:
			if (isClick) {
				// if (firstValue == 0) {
				if (flyText.equals("fly")) {
					status = 1;
				} else {
					status = 0;
				}

				if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
					if (status == checkStatus[count]) {
						score += 150;
					} else {
						score += 0;
					}
				} else {
					if (status == checkStatus[count]) {
						Log.i("DREG", "Status = " + status + " Check Status = "
								+ checkStatus[count]);
						score += 100;
					} else {
						Log.i("DREG", "Status = " + status + " Check Status = "
								+ checkStatus[count]);
						score += 0;
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
					if (status == checkStatus[count]) {
						score += 150;
					} else {
						score += 0;
					}

				} else {
					if (status == checkStatus[count]) {
						score += 100;
						Log.i("DREG", "Status = " + status + " Check Status = "
								+ checkStatus[count]);
					} else {
						score += 0;
						Log.i("DREG", "Status = " + status + " Check Status = "
								+ checkStatus[count]);
					}
				}
				isClick = false;
			}
			break;
		case R.id.exitButton:
			Intent cancelIntent = new Intent(SecondLevelActivity.this,
					HomeScreenActivity.class);
			cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(cancelIntent);
			break;
		default:
			break;
		}
		if (SettingPrefs.getSoundEffect(this)) 
		{
			Utils.play(SecondLevelActivity.this, R.raw.sound_any_click);
		}
	}

}
