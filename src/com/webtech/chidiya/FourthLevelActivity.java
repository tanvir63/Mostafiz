package com.webtech.chidiya;

import java.util.Random;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.webtech.utils.Constants;
import com.webtech.utils.NoRepeatRandom;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FourthLevelActivity extends Activity implements OnClickListener {

	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView flyButton;
	private ImageView dontFlyButton;
	private TextView scoreView;
	private TextView scoreview;

	private int firstImagesToShow[] = new int[21];
	private int secondImagesToShow[] = new int[21];
	private int checkFirstStatus[] = new int[21];
	private int checkSecondStatus[] = new int[21];

	private int hintShape[] = { R.drawable.circle, R.drawable.square,
			R.drawable.hexagon };

	private Random random = new Random();
	private NoRepeatRandom nrr;
	private int count = -1;

	private int buttonImage[] = { R.drawable.fly_button,
			R.drawable.dontfly_button };

	private String flyText = "fly";
	private String dontFlyText = "dontfly";

	private String DIFFICULTY_LEVEL;
	private int firstValue;
	private int secondValue;
	private boolean isClick;

	private int score = 0;
	private int status = 0;
	private int flyStatus = 0;
	private int imageIndex = -1;

	private int randomValue;
	private int randomShape;

	private ImageView shapeImageView;
	private TextView nextView;

	private RelativeLayout mainLayout;
	private Typeface font;

	private boolean isExit;

	private long extraFadeInTime = 100;
	private long extradFadeOutTime = 100;
	private long extraTimeBetween = 200;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			isExit = true;
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_fourth_level);

			mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

			AdView adview = (AdView) findViewById(R.id.adView);
			AdRequest re = new AdRequest();
			adview.loadAd(re);

			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			findViewById(R.id.exitButton).setOnClickListener(this);

			imageView1 = (ImageView) findViewById(R.id.imageView1);
			imageView2 = (ImageView) findViewById(R.id.imageView2);
			shapeImageView = (ImageView) findViewById(R.id.shapeImageView);
			nextView = (TextView) findViewById(R.id.nextView);
			nextView.setTypeface(font);

			scoreView = (TextView) findViewById(R.id.scoreView);
			scoreview = (TextView) findViewById(R.id.score);

			scoreView.setTypeface(font);
			scoreview.setTypeface(font);

			flyButton = (ImageView) findViewById(R.id.flyButton);
			flyButton.setOnClickListener(this);

			dontFlyButton = (ImageView) findViewById(R.id.dontFlyButton);
			dontFlyButton.setOnClickListener(this);

			DIFFICULTY_LEVEL = SettingPrefs.getGameLevel(this);

			getImageToShow();
			showAnimationWithHint();

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

		shapeImageView.setVisibility(View.INVISIBLE);
		nextView.setVisibility(View.GONE);
		flyButton.setEnabled(true);
		dontFlyButton.setEnabled(true);

		imageIndex++;

		randomValue = random.nextInt(2);

		BitmapDrawable drawable1;
		BitmapDrawable drawable2;
		int secondShape;
		if (randomShape == 0) {
			drawable1 = (BitmapDrawable) getResources().getDrawable(
					R.drawable.circle);
			secondShape = (new Random()).nextInt(2);
			if (secondShape == 0) {
				drawable2 = (BitmapDrawable) getResources().getDrawable(
						R.drawable.square);
			} else {
				drawable2 = (BitmapDrawable) getResources().getDrawable(
						R.drawable.hexagon);
			}
		} else if (randomShape == 1) {
			drawable1 = (BitmapDrawable) getResources().getDrawable(
					R.drawable.square);
			secondShape = (new Random()).nextInt(2);
			if (secondShape == 0) {
				drawable2 = (BitmapDrawable) getResources().getDrawable(
						R.drawable.circle);
			} else {
				drawable2 = (BitmapDrawable) getResources().getDrawable(
						R.drawable.hexagon);
			}
		} else {
			drawable1 = (BitmapDrawable) getResources().getDrawable(
					R.drawable.hexagon);
			secondShape = (new Random()).nextInt(2);
			if (secondShape == 0) {
				drawable2 = (BitmapDrawable) getResources().getDrawable(
						R.drawable.circle);
			} else {
				drawable2 = (BitmapDrawable) getResources().getDrawable(
						R.drawable.square);
			}
		}

		Bitmap targetShape = drawable1.getBitmap();
		Bitmap unTargetShape = drawable2.getBitmap();

		int shapeWidth = targetShape.getWidth();
		int shapeHeight = targetShape.getHeight();

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

		isClick = true;

		Bitmap leftBitmap = BitmapFactory.decodeResource(getResources(),
				firsImages[imageIndex]);
		Bitmap leftResized = Utils.getResizedBitmap(leftBitmap,
				(shapeWidth * 3) / 4, (shapeHeight * 3) / 4);

		Bitmap rightBitmap = BitmapFactory.decodeResource(getResources(),
				secondImages[imageIndex]);
		Bitmap rightResized = Utils.getResizedBitmap(rightBitmap,
				(shapeWidth * 3) / 4, (shapeHeight * 3) / 4);

		imageView1.setVisibility(View.INVISIBLE);
		imageView2.setVisibility(View.INVISIBLE);
		if (count != 20) {
			if (randomValue == 0) {
				imageView1.setImageBitmap(Utils.combineTwoImages(targetShape,
						leftResized));
				imageView2.setImageBitmap(Utils.combineTwoImages(unTargetShape,
						rightResized));

			} else {
				imageView1.setImageBitmap(Utils.combineTwoImages(unTargetShape,
						leftResized));
				imageView2.setImageBitmap(Utils.combineTwoImages(targetShape,
						rightResized));
			}
		}

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {

			fadeIn.setDuration((Constants.FOURTH_FADE_IN_DURATION + extraFadeInTime));
		} else {
			fadeIn.setDuration((Constants.FOURTH_FADE_IN_DURATION));
		}

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());

		if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
			fadeOut.setStartOffset((Constants.FOURTH_FADE_IN_DURATION + extraFadeInTime)
					+ Constants.FOURTH_TIME_BETWEEN + extraTimeBetween);
			fadeOut.setDuration(Constants.FOURTH_FADE_OUT_DURATION
					+ extradFadeOutTime);
		} else {
			fadeOut.setStartOffset((Constants.FOURTH_FADE_IN_DURATION)
					+ Constants.FOURTH_TIME_BETWEEN);
			fadeOut.setDuration(Constants.FOURTH_FADE_OUT_DURATION);
		}

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
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationStart(Animation animation) {

			}
		});
		Log.i("DREG", "Count =" + count);
		if (count == 20) {
			gameOver();
		}

	}

	private void showAnimationWithHint() {
		try {
			new AsyncTask<String, String, String>() {

				private boolean isPreAnimatoin;

				@Override
				protected void onPreExecute() {
					try {
						flyButton.setEnabled(false);
						dontFlyButton.setEnabled(false);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				protected void onProgressUpdate(String... arg0) {
					if (isPreAnimatoin) {
						if (count < 19) {
							flyButton.setEnabled(false);
							dontFlyButton.setEnabled(false);

							imageView1.setVisibility(View.GONE);
							imageView2.setVisibility(View.GONE);
							randomShape = random.nextInt(3);
							shapeImageView
									.setImageResource(hintShape[randomShape]);
							shapeImageView.setVisibility(View.VISIBLE);
							nextView.setVisibility(View.VISIBLE);

						}

					} else {
						count++;
						manageAnimation(imageView1, imageView2,
								firstImagesToShow, secondImagesToShow, false);
					}
				}

				@Override
				protected String doInBackground(String... arg0) {
					try {
						while ((firstImagesToShow.length - 1 > imageIndex)
								&& (!isExit)) {
							if (count <= 20) {
								isPreAnimatoin = true;
								publishProgress("");
								Thread.sleep(Constants.FOURTH_HINT_DURATION);
								isPreAnimatoin = false;
								publishProgress("");

								if (DIFFICULTY_LEVEL
										.equalsIgnoreCase("hard_game")) {
									Thread.sleep(Constants.FOURTH_FADE_IN_DURATION
											+extraFadeInTime+ Constants.FOURTH_TIME_BETWEEN+extraTimeBetween
											+ Constants.FOURTH_FADE_OUT_DURATION+extradFadeOutTime);
								}
								else
								{
									Thread.sleep(Constants.FOURTH_FADE_IN_DURATION
											+ Constants.FOURTH_TIME_BETWEEN
											+ Constants.FOURTH_FADE_OUT_DURATION);
								}
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				}

				@Override
				protected void onPostExecute(String unused) {
				}
			}.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void successulCompleted() {
		Intent intent = new Intent(FourthLevelActivity.this,
				SuccessfulActivity.class);
		intent.putExtra(Constants.KEY_LEVEL, 4);
		intent.putExtra(Constants.KEY_SCORE, score);
		startActivity(intent);
	}

	private void gameOver() {
		if (DIFFICULTY_LEVEL.equalsIgnoreCase("hard_game")) {
			int highestScore = Utils.getHighestScore(this,
					Constants.FOURTH_HARD_HIGHEST_SCORE);
			if (score > highestScore) {
				Utils.putHighestScore(this,
						Constants.FOURTH_HARD_HIGHEST_SCORE, score);
			}

			if (score < 2250) {
				Intent intent = new Intent(FourthLevelActivity.this,
						GameOverActivity.class);
				intent.putExtra(Constants.KEY_LEVEL, 4);
				intent.putExtra(Constants.KEY_SCORE, score);
				startActivity(intent);

			} else {
				Utils.putLevelUnlocked(this, Constants.FIFTH_LEVEL_UNLOCKED,
						true);
				LevelScreenActivity.fifthLevel
						.setImageResource(R.drawable.icon_level_5_unlocked);
				successulCompleted();
			}
		} else {
			int highestScore = Utils.getHighestScore(this,
					Constants.FOURTH_EASY_HIGHEST_SCORE);
			if (score > highestScore) {
				Utils.putHighestScore(this,
						Constants.FOURTH_EASY_HIGHEST_SCORE, score);
			}
			if (score < 1500) {
				Intent intent = new Intent(FourthLevelActivity.this,
						GameOverActivity.class);
				intent.putExtra(Constants.KEY_LEVEL, 4);
				intent.putExtra(Constants.KEY_SCORE, score);
				startActivity(intent);
			} else {
				Utils.putLevelUnlocked(this, Constants.FIFTH_LEVEL_UNLOCKED,
						true);
				LevelScreenActivity.fifthLevel
						.setImageResource(R.drawable.icon_level_5_unlocked);
				successulCompleted();
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flyButton:
			if (isClick) {
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
				scoreView.setText("" + score);
				isClick = false;
			}
			break;
		case R.id.dontFlyButton:
			if (isClick) {
				if (dontFlyText.equalsIgnoreCase("dontfly")) {
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
				scoreView.setText("" + score);
				isClick = false;
			}
			break;
		case R.id.exitButton:
			isExit = true;
			Intent cancelIntent = new Intent(FourthLevelActivity.this,
					HomeScreenActivity.class);
			cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(cancelIntent);
			break;
		default:
			break;
		}
		if (SettingPrefs.getSoundEffect(this)) {
			Utils.play(FourthLevelActivity.this, R.raw.sound_any_click);
		}
	}

}
