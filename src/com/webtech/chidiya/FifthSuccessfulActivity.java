package com.webtech.chidiya;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FifthSuccessfulActivity extends Activity implements OnClickListener {

	private Typeface font;
	private Typeface font1;

	private TextView levelView;
	private TextView messageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_successful_fifth);
			
			font = Typeface.createFromAsset(getAssets(), "Arista.ttf");
			font1 = Typeface.createFromAsset(getAssets(), "Epifania.ttf");
			
			levelView =(TextView)findViewById(R.id.levelView);
			levelView.setTypeface(font);
			
			messageView= (TextView)findViewById(R.id.messageView);
			messageView.setTypeface(font1);
			
			findViewById(R.id.playButton).setOnClickListener(this);
			findViewById(R.id.cancelButton).setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.playButton:
			Intent intent = new Intent(FifthSuccessfulActivity.this,
					SettingPrefs.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		case R.id.cancelButton:
			Intent cancelIntent = new Intent(FifthSuccessfulActivity.this,
					HomeScreenActivity.class);
			cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(cancelIntent);
			break;
		default:
			break;
		}

	}

}
