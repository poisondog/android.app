/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
import android.os.Bundle;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import poisondog.android.log.AndroidLogger;
import poisondog.android.util.TransportMonitor;
import poisondog.log.Logger;
import poisondog.log.LogLevel;
import poisondog.core.NoMission;
import poisondog.core.Mission;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 2016-10-14
 */
public class SpeedRate extends BroadcastReceiver {
	private Logger mLogger;

	/**
	 * Constructor
	 */
	public SpeedRate() {
		mLogger = new AndroidLogger(getClass().getSimpleName());
	}

	@Override
	public void onReceive(final Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String target = bundle.getString(TransportMonitor.FROM);
		int current = (int) bundle.getLong(TransportMonitor.CURRENT);

	}
}