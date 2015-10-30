package com.webtech.chidiya;

import java.util.Random;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.webtech.utils.Constants;
import com.webtech.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InstructionActivity extends Activity implements OnClickListener {

	private int level;
	private TextView instructionView;
	private TextView howView;

	private String firstInstruction = "In the first level, Images will keep coming on your "
			+ "screen one by one, if the object or character flies, press "
			+ "the “Fly” button, if it doesn’t then press “Don’t Fly” button. Simple as it is!"
			+ "\n\nTap “Play” to start playing the first level";
	private String secondInstruction = "In the second level, Images will "
			+ "keep coming on your screen but little faster than the first level, "
			+ "if the object or character flies, press the “Fly” button, if it "
			+ "doesn’t then press “Don’t Fly” button. Concentrate on the game thoroughly and "
			+ "start conquering!"
			+ "\n\nTap “Play” to start playing the second level";
	private String thirdInstruction = "In the third level, there will be two images shuffling at a time,"
			+ " one image will always have an arrow or a mark on the top of it, it might be on left or right,"
			+ " totally random. You have to identify "
			+ "the property of only that image, which has arrow over it, ignore the other image."
			+ "\n\nTap “Play” to start playing the third level";

	private String fourthInstruction = "In the fourth level, there would be two images at a time " +
			"inscribed in any one of the three shapes (square, hexagon or circle)." +
			" The idea behind this is that the screen would first show hint symbol " +
			"which would be the main shape for the next set of images, for example if " +
			"hint symbol shows square for the next set of images, then user has to " +
			"identify only that image which is in square, and ignore the other image." +
			"  Again 15 images have to be identified correctly to unblock the next level."
			+ "\n\nTap “Play” to start playing the fourth level.";

	private String fifthInstruction = "The fifth level is very much similar to the fourth level " +
			"except that instead of shapes, it will contain coloured squares with images " +
			"inside it. The colour will be told in the hint just before the set of " +
			"images appear and user has to identify according to that colour, and ignore the " +
			"other image."
			+ "\n\nTap “Play” to start playing the fifth level.";
	private int shape;
	private int color;
//	private LinearLayout shapeLayout;
	private TextView shapeView;
	private ImageView shapeImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_instruction);

//			shapeLayout = (LinearLayout) findViewById(R.id.shapeLayout);
			instructionView = (TextView) findViewById(R.id.instrctionView);
			shapeImageView = (ImageView) findViewById(R.id.shapeImageView);
			shapeView = (TextView) findViewById(R.id.shapeView);

			howView = (TextView) findViewById(R.id.howView);
			level = getIntent().getIntExtra(Constants.KEY_LEVEL, 1);

			instructionView.setTypeface(Typeface.createFromAsset(getAssets(),
					"Epifania.ttf"));
			howView.setTypeface(Typeface.createFromAsset(getAssets(),
					"Arista.ttf"));
			shape = (new Random()).nextInt(3);
			color = (new Random()).nextInt(3);
			switch (level) {
			case 1:
				instructionView.setText(firstInstruction);
//				shapeLayout.setVisibility(View.GONE);
				break;
			case 2:
				instructionView.setText(secondInstruction);
//				shapeLayout.setVisibility(View.GONE);
				break;
			case 3:
				instructionView.setText(thirdInstruction);
//				shapeLayout.setVisibility(View.GONE);
				break;
			case 4:
				instructionView.setText(fourthInstruction);
				if (shape == 0) {
					shapeImageView.setImageResource(R.drawable.circle);
				} else if (shape == 1) {
					shapeImageView.setImageResource(R.drawable.square);
				} else {
					shapeImageView.setImageResource(R.drawable.hexagon);
				}
//				shapeLayout.setVisibility(View.VISIBLE);
				break;
			case 5:
				instructionView.setText(fifthInstruction);
				shapeView.setText("Next Color:");
				if (color == 0) {
					shapeImageView.setImageResource(R.drawable.green);
				} else if (color == 1) {
					shapeImageView.setImageResource(R.drawable.grey);
				} else {
					shapeImageView.setImageResource(R.drawable.pink);
				}
//				shapeLayout.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}

			findViewById(R.id.continueButton).setOnClickListener(this);
			findViewById(R.id.cancelButton).setOnClickListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.continueButton:
			switch (level) {
			case 1:
				startActivity(new Intent(InstructionActivity.this,
						FirstLevelActivity.class));
				break;
			case 2:
				startActivity(new Intent(InstructionActivity.this,
						SecondLevelActivity.class));
				break;
			case 3:
				startActivity(new Intent(InstructionActivity.this,
						ThirdLevelActivity.class));
				break;
			case 4:
				Intent intent = new Intent(InstructionActivity.this,
						FourthLevelActivity.class);
//				intent.putExtra(Constants.KEY_SHAPE, shape);
				startActivity(intent);
				break;

			case 5:
				Intent fifthIntent = new Intent(InstructionActivity.this,
						FifthLevelActivity.class);
//				fifthIntent.putExtra(Constants.KEY_COLOR, color);
				startActivity(fifthIntent);
				break;
			default:
				break;
			}

			break;
		case R.id.cancelButton:
			finish();
			break;
		default:
			break;
		}
		if (SettingPrefs.getSoundEffect(this)) {
			Utils.play(InstructionActivity.this, R.raw.sound_any_click);
		}

	}

}
