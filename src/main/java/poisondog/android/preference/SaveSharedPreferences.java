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
package poisondog.android.preference;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import poisondog.android.log.AndroidLogger;
import poisondog.core.Mission;
import poisondog.log.Logger;
import poisondog.log.LogLevel;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class SaveSharedPreferences implements Mission<Map<String, String>> {
	private Logger mLogger;
	private Context mContext;
	private int mMode;
	private String mPropertiesFileName;

	public SaveSharedPreferences(Context context, String fileName) {
		mLogger = new AndroidLogger(getClass().getSimpleName());
		mLogger.log(LogLevel.TRACE, "constructor");
		mContext = context;
		mMode = context.MODE_PRIVATE;
		mPropertiesFileName = fileName;
	}

	public void setMode(int mode) {
		mMode = mode;
	}

	@Override
	public Map<String, String> execute(Map<String, String> setting) {
		mLogger.log(LogLevel.TRACE, "execute: " + setting);
		SharedPreferences settings = mContext.getSharedPreferences(mPropertiesFileName, mMode);
		for (String key : setting.keySet()) {
			settings.edit().putString(key, setting.get(key)).commit();
		}
		return setting;
	}
}
