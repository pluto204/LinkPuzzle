package com.hai.link.utils;

import com.hai.link.R;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefClass {

	private static Context context;
	private static SharedPreferences pref;
	private static SharedPreferences.Editor edit;

	public PrefClass(Context context) {
		PrefClass.context = context;
		pref = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
	}

	public static void saveColor(int color) {
		edit = pref.edit();
		edit.putInt("COLOR", color);
		edit.commit();
	}

	public static void saveHint(int hint) {
		edit = pref.edit();
		edit.putInt("HINT", hint);
		edit.commit();
	}

	public static int getTheme() {
		return pref.getInt("THEME", 0);
	}

	public static void setTheme(int theme) {
		edit = pref.edit();
		edit.putInt("THEME", theme);
		edit.commit();
	}

	public static int getHint() {
		return pref.getInt("HINT", 5);
	}

	public static int getFileIndex() {
		return pref.getInt("FILE", 1);
	}

	public static void setFileIndex(int value) {
		edit = pref.edit();
		edit.putInt("FILE", value);
		edit.commit();
	}

	public static int getColor() {
		return pref.getInt("COLOR", context.getResources().getColor(R.color.pack5));
	}

	public static boolean getSound() {
		return pref.getBoolean("SOUND", true);
	}

	public static void setSound(boolean sound) {
		edit = pref.edit();
		edit.putBoolean("SOUND", sound);
		edit.commit();
	}
}
