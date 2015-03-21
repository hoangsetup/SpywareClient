package com.hoangdv.spywareclient.Spy;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferentceHelper {

	public static void saveConfig(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"SpyConfig", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("sendInfoDevice", StatusConfig.sendInfoDevice);
		editor.putBoolean("sendAll", StatusConfig.sendAll);
		editor.putInt("smsToSend", StatusConfig.smsToSend);
		editor.commit();
	}

	public static void getConfig(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"SpyConfig", Context.MODE_PRIVATE);
		if(preferences == null)
			return;
		StatusConfig.sendInfoDevice = preferences.getBoolean("sendInfoDevice",
				false);
		StatusConfig.sendAll = preferences.getBoolean("sendAll", false);
		StatusConfig.smsToSend = preferences.getInt("smsToSend", 0);
	}
}
