package com.webtech.chidiya;

import com.webtech.utils.Constants;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class LevelScreenActivity extends Activity implements OnClickListener {

	public static ImageView firstLevel;
	public static ImageView secondLevel;
	public static ImageView thirdLevel;
	public static ImageView fourthLevel;
	public static ImageView fifthLevel;

	private Typeface font;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_custom_level);

		font = Typeface.createFromAsset(getAssets(), "Arista.ttf");
		((TextView) findViewById(R.id.titleView)).setTypeface(font);
		((TextView) findViewById(R.id.firstView)).setTypeface(font);
		((TextView) findViewById(R.id.secondView)).setTypeface(font);
		((TextView) findViewById(R.id.thirdView)).setTypeface(font);
		((TextView) findViewById(R.id.fourthView)).setTypeface(font);
		((TextView) findViewById(R.id.fifthView)).setTypeface(font);
		((TextView) findViewById(R.id.comingView)).setTypeface(font);

		firstLevel = (ImageView) findViewById(R.id.firstLevel);
		firstLevel.setOnClickListener(this);

		secondLevel = (ImageView) findViewById(R.id.secondLevel);
		secondLevel.setOnClickListener(this);

		thirdLevel = (ImageView) findViewById(R.id.thirdLevel);
		thirdLevel.setOnClickListener(this);

		fourthLevel = (ImageView) findViewById(R.id.fourthLevel);
		fourthLevel.setOnClickListener(this);

		fifthLevel = (ImageView) findViewById(R.id.fifthLevel);
		fifthLevel.setOnClickListener(this);

		if (Utils.getLevelUnlocked(this, Constants.SECOND_LEVEL_UNLOCKED)) {
			secondLevel.setImageResource(R.drawable.icon_level_2_unlocked);
		}

		if (Utils.getLevelUnlocked(this, Constants.THIRD_LEVEL_UNLOCKED)) {
			thirdLevel.setImageResource(R.drawable.icon_level_3_unlocked);
		}
		if (Utils.getLevelUnlocked(this, Constants.FOURTH_LEVEL_UNLOCKED)) {
			fourthLevel.setImageResource(R.drawable.icon_level_4_unlocked);
		}
		if (Utils.getLevelUnlocked(this, Constants.FIFTH_LEVEL_UNLOCKED)) {
			fifthLevel.setImageResource(R.drawable.icon_level_5_unlocked);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.firstLevel:
			Intent firstIntent = new Intent(LevelScreenActivity.this,
					InstructionActivity.class);
			firstIntent.putExtra(Constants.KEY_LEVEL, 1);
			startActivity(firstIntent);
			break;
		case R.id.secondLevel:
			if (Utils.getLevelUnlocked(this, Constants.SECOND_LEVEL_UNLOCKED)) {
				Intent secondIntent = new Intent(LevelScreenActivity.this,
						InstructionActivity.class);
				secondIntent.putExtra(Constants.KEY_LEVEL, 2);
				startActivity(secondIntent);
			} else {

				Toast.makeText(this, "\t\tOops ! !\nLevel 2 is Locked ! !",
						Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.thirdLevel:
//			if (Utils.getLevelUnlocked(this, Constants.THIRD_LEVEL_UNLOCKED)) {
				Intent thirdIntent = new Intent(LevelScreenActivity.this,
						InstructionActivity.class);
				thirdIntent.putExtra(Constants.KEY_LEVEL, 3);
				startActivity(thirdIntent);
//			} else {
//
//				Toast.makeText(this, "\t\tOops ! !\nLevel 3 is Locked ! !",
//						Toast.LENGTH_SHORT).show();
//			}
			
			break;
		case R.id.fourthLevel:
//			if (Utils.getLevelUnlocked(this, Constants.FOURTH_LEVEL_UNLOCKED)) {
				Intent fourthIntent = new Intent(LevelScreenActivity.this,
						InstructionActivity.class);
				fourthIntent.putExtra(Constants.KEY_LEVEL, 4);
				startActivity(fourthIntent);
//			} else {
//
//				Toast.makeText(this, "\t\tOops ! !\nLevel 4 is Locked ! !",
//						Toast.LENGTH_SHORT).show();
//			}
			break;
		case R.id.fifthLevel:
//			if (Utils.getLevelUnlocked(this, Constants.FIFTH_LEVEL_UNLOCKED)) {
				Intent fifthIntent = new Intent(LevelScreenActivity.this,
						InstructionActivity.class);
				fifthIntent.putExtra(Constants.KEY_LEVEL, 5);
				startActivity(fifthIntent);
//			} else {
//
//				Toast.makeText(this, "\t\t\tOops ! !\nLevel 5 is locked ! !",
//						Toast.LENGTH_SHORT).show();
//			}
			break;
		default:
			break;
		}
		
		if(SettingPrefs.getSoundEffect(this))
		{
			Utils.play(LevelScreenActivity.this, R.raw.sound_any_click);
		}
	}

}
