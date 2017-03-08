package jp.gr.java_conf.atle42.androidutility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by murata_to on 2017/03/02.
 */

public class SharedPreferencesUtil {

	public static SharedPreferences getSharedPreferences(Context context, String key) {
		return context.getSharedPreferences(key, Context.MODE_PRIVATE);
	}

	public static void saveBoolean(Context context, String prefKey, String valueKey, boolean value) {
		SharedPreferences.Editor editor = getSharedPreferences(context, prefKey).edit();
		editor.putBoolean(valueKey, value);
		editor.apply();
	}

	public static boolean loadBoolean(Context context, String prefKey, String valueKey, boolean defaultValue) {
		return getSharedPreferences(context, prefKey).getBoolean(valueKey, defaultValue);
	}

	public static void saveLong(Context context, String prefKey, String valueKey, long value) {
		SharedPreferences.Editor editor = getSharedPreferences(context, prefKey).edit();
		editor.putLong(valueKey, value);
		editor.apply();
	}

	public static long loadLong(Context context, String prefKey, String valueKey, long defaultValue) {
		return getSharedPreferences(context, prefKey).getLong(valueKey, defaultValue);
	}

	public static void saveFloat(Context context, String prefKey, String valueKey, float value) {
		SharedPreferences.Editor editor = getSharedPreferences(context, prefKey).edit();
		editor.putFloat(valueKey, value);
		editor.apply();
	}

	public static float loadFloat(Context context, String prefKey, String valueKey, float defaultValue) {
		return getSharedPreferences(context, prefKey).getFloat(valueKey, defaultValue);
	}

	public static void saveInt(Context context, String prefKey, String valueKey, int value) {
		SharedPreferences.Editor editor = getSharedPreferences(context, prefKey).edit();
		editor.putInt(valueKey, value);
		editor.apply();
	}

	public static int loadInt(Context context, String prefKey, String valueKey, int defaultValue) {
		return getSharedPreferences(context, prefKey).getInt(valueKey, defaultValue);
	}

	public static void saveString(Context context, String prefKey, String valueKey, String value) {
		SharedPreferences.Editor editor = getSharedPreferences(context, prefKey).edit();
		editor.putString(valueKey, value);
		editor.apply();
	}

	public static String loadString(Context context, String prefKey, String valueKey, String defaultValue) {
		return getSharedPreferences(context, prefKey).getString(valueKey, defaultValue);
	}

	public static void saveStringSet(Context context, String prefKey, String valueKey, Set<String> value) {
		SharedPreferences.Editor editor = getSharedPreferences(context, prefKey).edit();
		editor.putStringSet(valueKey, value);
		editor.apply();
	}

	public static Set<String> loadString(Context context, String prefKey, String valueKey, Set<String> defaultValue) {
		return getSharedPreferences(context, prefKey).getStringSet(valueKey, defaultValue);
	}
}
