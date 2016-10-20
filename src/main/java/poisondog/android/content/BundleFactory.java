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
package poisondog.android.content;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import poisondog.core.Mission;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 2016-10-20
 */
public class BundleFactory implements Mission<Object> {
	private Bundle mResult;

	/**
	 * Constructor
	 */
	public BundleFactory() {
		mResult = new Bundle();
	}

	public void putString(String key, String value) {
		mResult.putString(key, value);
	}

	public void putStringList(String key, List<String> value) {
		mResult. putStringArrayList(key, new ArrayList<String>(value));
	}

	public void putInt(String key, int value) {
		mResult.putInt(key, value);
	}

	public void putLong(String key, long value) {
		mResult.putLong(key, value);
	}

	public void putBoolean(String key, boolean value) {
		mResult.putBoolean(key, value);
	}

	public void putFloat(String key, float value) {
		mResult.putFloat(key, value);
	}

	@Override
	public Bundle execute(Object none) {
		Bundle result = mResult;
		mResult = new Bundle();
		return result;
	}
}
