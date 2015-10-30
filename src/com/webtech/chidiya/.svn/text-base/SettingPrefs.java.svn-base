package com.webtech.chidiya;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.webtech.chidiya.R;
import com.webtech.utils.Utils;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class SettingPrefs extends PreferenceActivity {
	// Option names and default values
	private static final String OPTION_SOUND = "sound_effect";
	private static final boolean OPTION_SOUND_DEFAULT = true;

	private static final String OPTION_LEVEL = "game_level";
	public static final String OPTION_LEVEL_DEFAULT = "easy_game";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
//		setContentView(R.layout.layout_prefrence);
//
//		AdView adview = (AdView) findViewById(R.id.adView);
//		AdRequest re = new AdRequest();
//		adview.loadAd(re);

		Preference soundPreference = (Preference) findPreference(getResources()
				.getString(R.string.sound_key));
		soundPreference
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					@Override
					public boolean onPreferenceClick(Preference arg0) {
						Utils.play(SettingPrefs.this, R.raw.sound_any_click);
						return false;
					}
				});

		Preference levelPreference = (Preference) findPreference("game_level");
		levelPreference
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					@Override
					public boolean onPreferenceClick(Preference preference) {
						if (getSoundEffect(SettingPrefs.this)) {
							Utils.play(SettingPrefs.this, R.raw.sound_any_click);
						}
						return false;
					}
				});

		// getWindow().setBackgroundDrawableResource(R.color.background_color);
		//
		// PreferenceScreen b = (PreferenceScreen)
		// findPreference("setting_preferencescreen_key");
		// b.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		// @Override
		// public boolean onPreferenceClick(Preference preference) {
		// PreferenceScreen a = (PreferenceScreen) preference;
		// a.getDialog()
		// .getWindow()
		// .setBackgroundDrawableResource(R.color.background_color);
		// return false;
		// }
		// });

	}

	/** Get the current value of the music option */
	public static boolean getSoundEffect(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPTION_SOUND, OPTION_SOUND_DEFAULT);
	}

	public static String getGameLevel(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(OPTION_LEVEL, OPTION_LEVEL_DEFAULT);
	}

}
