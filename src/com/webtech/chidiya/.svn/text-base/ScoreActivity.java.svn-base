package com.webtech.chidiya;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.webtech.utils.Constants;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ScoreActivity extends Activity {

	private TextView hardView1;
	private TextView hardView2;
	private TextView hardView3;
	private TextView hardView4;
	private TextView hardView5;

	private TextView easyView1;
	private TextView easyView2;
	private TextView easyView3;
	private TextView easyView4;
	private TextView easyView5;

	private TextView quickHardView;
	private TextView quickEasyView;

	private Typeface font;

	private TextView levelView;
	private TextView easyView;
	private TextView hardView;

	private TextView level1View;
	private TextView level2View;
	private TextView level3View;
	private TextView level4View;
	private TextView level5View;
	private TextView quickView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_score);
			
			AdView adview = (AdView) findViewById(R.id.adView);
			AdRequest re = new AdRequest();
			adview.loadAd(re);

			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");

			levelView = (TextView) findViewById(R.id.levelView);
			easyView = (TextView) findViewById(R.id.easyView);
			hardView = (TextView) findViewById(R.id.hardView);

			level1View = (TextView) findViewById(R.id.level1View);
			level2View = (TextView) findViewById(R.id.level2View);
			level3View = (TextView) findViewById(R.id.level3View);
			level4View = (TextView) findViewById(R.id.level4View);
			level5View = (TextView) findViewById(R.id.level5View);
			quickView = (TextView) findViewById(R.id.quickView);

			levelView.setTypeface(font);
			easyView.setTypeface(font);
			hardView.setTypeface(font);

			level1View.setTypeface(font);
			level2View.setTypeface(font);
			level3View.setTypeface(font);
			level4View.setTypeface(font);
			level5View.setTypeface(font);
			quickView.setTypeface(font);

			hardView1 = (TextView) findViewById(R.id.hardView1);
			hardView2 = (TextView) findViewById(R.id.hardView2);
			hardView3 = (TextView) findViewById(R.id.hardView3);
			hardView4 = (TextView) findViewById(R.id.hardView4);
			hardView5 = (TextView) findViewById(R.id.hardView5);

			hardView1.setTypeface(font);
			hardView2.setTypeface(font);
			hardView3.setTypeface(font);
			hardView4.setTypeface(font);
			hardView5.setTypeface(font);

			easyView1 = (TextView) findViewById(R.id.easyView1);
			easyView2 = (TextView) findViewById(R.id.easyView2);
			easyView3 = (TextView) findViewById(R.id.easyView3);
			easyView4 = (TextView) findViewById(R.id.easyView4);
			easyView5 = (TextView) findViewById(R.id.easyView5);

			easyView1.setTypeface(font);
			easyView2.setTypeface(font);
			easyView3.setTypeface(font);
			easyView4.setTypeface(font);
			easyView5.setTypeface(font);

			quickHardView = (TextView) findViewById(R.id.quickHardView);
			quickEasyView = (TextView) findViewById(R.id.quickEasyView);

			quickHardView.setTypeface(font);
			quickEasyView.setTypeface(font);

			int firstHardHighestScore = Utils.getHighestScore(this,
					Constants.FIRST_HARD_HIGHEST_SCORE);
			int firstEasyHighestScore = Utils.getHighestScore(this,
					Constants.FIRST_EASY_HIGHEST_SCORE);

			int secondHardHighestScore = Utils.getHighestScore(this,
					Constants.SECOND_HARD_HIGHEST_SCORE);
			int secondEasyHighestScore = Utils.getHighestScore(this,
					Constants.SECOND_EASY_HIGHEST_SCORE);

			int thirdEasyHighestScore = Utils.getHighestScore(this,
					Constants.THIRD_EASY_HIGHEST_SCORE);
			int thirdHardHighestScore = Utils.getHighestScore(this,
					Constants.THIRD_HARD_HIGHEST_SCORE);

			int fourthEasyHighestScore = Utils.getHighestScore(this,
					Constants.FOURTH_EASY_HIGHEST_SCORE);
			int fourthHardHighestScore = Utils.getHighestScore(this,
					Constants.FOURTH_HARD_HIGHEST_SCORE);

			int fifthHardHighestScore = Utils.getHighestScore(this,
					Constants.FIFTH_HARD_HIGHEST_SCORE);
			int fifthEasyHighestScore = Utils.getHighestScore(this,
					Constants.FIFTH_EASY_HIGHEST_SCORE);

			int quickHardHighestScore = Utils.getHighestScore(this,
					Constants.QUICK_HARD_HIGHEST_SCORE);
			int quickEasyHighestScore = Utils.getHighestScore(this,
					Constants.QUICK_EASY_HIGHEST_SCORE);

			hardView1.setText("" + firstHardHighestScore);
			hardView2.setText("" + secondHardHighestScore);
			hardView3.setText("" + thirdHardHighestScore);
			hardView4.setText("" + fourthHardHighestScore);
			hardView5.setText("" + fifthHardHighestScore);

			easyView1.setText("" + firstEasyHighestScore);
			easyView2.setText("" + secondEasyHighestScore);
			easyView3.setText("" + thirdEasyHighestScore);
			easyView4.setText("" + fourthEasyHighestScore);
			easyView5.setText("" + fifthEasyHighestScore);

			quickHardView.setText("" + quickHardHighestScore);
			quickEasyView.setText("" + quickEasyHighestScore);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
