/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import poisondog.android.log.AndroidLogger;
import poisondog.log.Logger;
import poisondog.log.LogLevel;
import poisondog.core.NoMission;
import poisondog.core.Mission;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class NetworkState extends BroadcastReceiver {
	private Logger mLogger;
	private Mission<NetworkInfo> mNetworkHandler;
	private Mission<WifiManager> mWifiHandler;

	public NetworkState() {
		mLogger = new AndroidLogger(getClass().getSimpleName());
		mNetworkHandler = new NoMission();
		mWifiHandler = new NoMission();
	}

	public void setLogger(Logger logger) {
		mLogger = logger;
	}

	public void setNetworkHandler(Mission<NetworkInfo> handler) {
		mNetworkHandler = handler;
	}

	public void setWifiHandler(Mission<WifiManager> handler) {
		mWifiHandler = handler;
	}

	@Override
	public void onReceive(final Context context, Intent intent) {
		try {
			NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			mLogger.log(LogLevel.TRACE, "Network Type: " + info.getType());
			mLogger.log(LogLevel.TRACE, "Network State: " + info.getState());
			mLogger.log(LogLevel.TRACE, "Execute Handler: " + mNetworkHandler.execute(info));

			WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			mLogger.log(LogLevel.TRACE, "WiFi SSID: " + wifiMgr.getConnectionInfo().getSSID());
			mLogger.log(LogLevel.TRACE, "WiFi Mac Address: " + wifiMgr.getConnectionInfo().getMacAddress());
			mLogger.log(LogLevel.TRACE, "Execute Handler: " + mWifiHandler.execute(wifiMgr));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
