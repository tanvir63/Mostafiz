package com.webtech.chidiya;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HowToPlayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_how_to_play);
			
			AdView adview = (AdView) findViewById(R.id.adView);
			AdRequest re = new AdRequest();
			adview.loadAd(re);
			
			((TextView) findViewById(R.id.howView)).setTypeface(Typeface
					.createFromAsset(getAssets(), "Arista.ttf"));
			((TextView) findViewById(R.id.instrctionView))
					.setTypeface(Typeface.createFromAsset(getAssets(),
							"Epifania.ttf"));
			((TextView) findViewById(R.id.instrctionView1))
			.setTypeface(Typeface.createFromAsset(getAssets(),
					"Epifania.ttf"));
			((TextView) findViewById(R.id.instrctionView2))
			.setTypeface(Typeface.createFromAsset(getAssets(),
					"Epifania.ttf"));
			((TextView) findViewById(R.id.instrctionView2))
			.setTypeface(Typeface.createFromAsset(getAssets(),
					"Epifania.ttf"));
			((TextView) findViewById(R.id.instrctionView3))
			.setTypeface(Typeface.createFromAsset(getAssets(),
					"Epifania.ttf"));
			((TextView) findViewById(R.id.instrctionView4))
			.setTypeface(Typeface.createFromAsset(getAssets(),
					"Epifania.ttf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
