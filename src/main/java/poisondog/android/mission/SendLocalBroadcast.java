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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import poisondog.core.Mission;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 2016-10-20
 */
public class SendLocalBroadcast implements Mission<SendLocalBroadcast.Parameter> {
	private Context mContext;

	/**
	 * Constructor
	 */
	public SendLocalBroadcast(Context context) {
		mContext = context;
	}

	@Override
	public SendLocalBroadcast.Parameter execute(SendLocalBroadcast.Parameter parameter) {
		Intent localIntent = new Intent(parameter.mActionName);
		localIntent.putExtras(parameter.mBundle);
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(localIntent);
		return parameter;
	}

	public class Parameter {
		private String mActionName;
		private Bundle mBundle;
		/**
		 * Constructor
		 */
		public Parameter(String actionName, Bundle bundle) {
			mActionName = actionName;
			mBundle = bundle;
		}
	}
}
