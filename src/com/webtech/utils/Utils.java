package com.webtech.utils;

import android.media.SoundPool.OnLoadCompleteListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.preference.PreferenceManager;

public class Utils {

	public static void showAlertMessage(Context context, int icon,
			String title, String message) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(icon);
		builder.setMessage(message).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {

					}
				});
		final AlertDialog alert = builder.create();
		alert.setTitle(title);
		alert.show();
	}
	
	public static void showAlertMessage(Context context,String title, String message) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							final int id) {

					}
				});
		final AlertDialog alert = builder.create();
		alert.setTitle(title);
		alert.show();
	}

	private static SoundPool soundPool;
	private static boolean loaded = false;
	private static int soundId;

	public static void playSound(Context context, int resource,
			OnCompletionListener ocl) {
		stop(context);
		mp = MediaPlayer.create(context, resource);
		if (ocl != null) {
			mp.setOnCompletionListener(ocl);
		}
		// mp.setLooping(false);
		mp.start();

	}

	private static MediaPlayer mp = null;

	/** Stop old song and start new one */
	public static void play(Context context, int resource) {
		stop(context);
		mp = MediaPlayer.create(context, resource);
		mp.setLooping(false);
		mp.start();
	}

	/** Stop the music */
	public static void stop(Context context) {
		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
		}
	}

	public static byte[] getFileData(Context context, String fileName) {
		byte[] ret = null;
		try {
			InputStream is = context.getAssets().open(fileName);
			byte[] data = new byte[2048];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read;
			while ((read = is.read(data)) != -1) {
				baos.write(data, 0, read);
			}
			is.close();
			ret = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Bitmap combineImages(Bitmap sourceImage, Bitmap arrow) {

		Bitmap resizedArrow = getResizedBitmap(arrow, arrow.getWidth() / 2,
				arrow.getHeight() / 2);
		Bitmap cs = null;
		int width, height = 0;

		height = sourceImage.getHeight() + resizedArrow.getHeight();
		width =sourceImage.getWidth();

		cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(cs);

		canvas.drawBitmap(sourceImage, 0f, resizedArrow.getHeight(), null);
		canvas.drawBitmap(resizedArrow, sourceImage.getWidth() / 2, 0f, null);

		return cs;
	}

	public static Bitmap combineTwoImages(Bitmap sourceImage,
			Bitmap inscribedImage) {

		Bitmap cs = null;
		int width, height = 0;

		width = sourceImage.getWidth();
		height = sourceImage.getHeight();

		int resizedWidht = inscribedImage.getWidth();
		int resizedHeight = inscribedImage.getHeight();

		cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(cs);

		canvas.drawBitmap(sourceImage, 0f, 0f, null);
		canvas.drawBitmap(inscribedImage, (width - resizedWidht) / 2,
				(height - resizedHeight) / 2, null);

		return cs;
	}

	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);

		return resizedBitmap;

	}

	public static final boolean putLevelUnlocked(Context context, String key,
			boolean value) {
		return PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putBoolean(key, value).commit();
	}

	public static final boolean getLevelUnlocked(Context context, String key) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(key, false);
	}

	public static final boolean putGameOverAlert(Context context, String key,
			boolean value) {
		return PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putBoolean(key, value).commit();
	}

	public static final boolean getGameOverAlert(Context context, String key) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(key, true);
	}

	public static final boolean putHighestScore(Context context, String key,
			int value) {
		return PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putInt(key, value).commit();
	}

	public static final int getHighestScore(Context context, String key) {
		return PreferenceManager.getDefaultSharedPreferences(context).getInt(
				key, 0);
	}

}
