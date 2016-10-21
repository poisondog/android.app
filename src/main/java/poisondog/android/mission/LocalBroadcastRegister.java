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
package poisondog.android.mission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import poisondog.core.Mission;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 2016-10-21
 */
public class LocalBroadcastRegister implements Mission<LocalBroadcastRegister.Parameter> {
	private LocalBroadcastManager mManager;

	/**
	 * Constructor
	 */
	public LocalBroadcastRegister(Context context) {
		mManager = LocalBroadcastManager.getInstance(context);
	}

	@Override
	public Parameter execute(LocalBroadcastRegister.Parameter parameter) {
		mManager.registerReceiver(parameter.mReceiver, new IntentFilter(parameter.mActionName));
		return parameter;
	}

	public void unregisterReceiver(BroadcastReceiver receiver) {
		mManager.unregisterReceiver(receiver);
	}

	public class Parameter {
		private String mActionName;
		private BroadcastReceiver mReceiver;
		/**
		 * Constructor
		 */
		public Parameter(String actionName, BroadcastReceiver receiver) {
			mActionName = actionName;
			mReceiver = receiver;
		}
	}
}
