
package com.stifler.basecommonmodule.demo.service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.stifler.basecommonmodule.demo.base.config.Constant;


public class NetWorkStatusChangeReceiver extends BroadcastReceiver {

	public static final String TAG = "NetWorkStatusChangeReceiver";
	private Context mContext = null;
	
	@Override
	public void onReceive(Context context, Intent intent)
	{	
		mContext = context;
//		EventBus.getDefault().post(new NetWorkStatusChangeReceiver());
//		if(Utils.isNetValid()){
//			if(!IMConnectionHandler.getInstance().isXmppAvailable()) {
//				IMConnectionHandler.getInstance().loginToIMServer();
//			}
//		}

		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			String type = networkInfo.getTypeName();
			if (type.equalsIgnoreCase("WIFI")) {
				Constant.PHONE_NETWORK_TYPE = "wifi";
			} else if (type.equalsIgnoreCase("MOBILE")) {
				String proxyHost = android.net.Proxy.getDefaultHost();
				Constant.PHONE_NETWORK_TYPE = "mobile";
			}
		} else {
			Constant.PHONE_NETWORK_TYPE = "mobile";
		}
	}
	
}