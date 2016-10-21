/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
package poisondog.android.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import poisondog.android.content.BundleFactory;
import poisondog.android.content.IntentUtil;
import poisondog.io.Accumulation;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class TransportMonitor extends Accumulation {
	public static final String PROGRESS = "poisondog.android.util.PROGRESS";
	public static final String FROM = "from";
	public static final String CURRENT = "current";
	public static final String MAX = "max";
	private Context mContext;
	private String mFromUrl;
	private long mMax;

	public TransportMonitor(Context context, String fromUrl) {
		mContext = context;
		mFromUrl = fromUrl;
		mMax = 100;
		onStep(0);
	}

	public void setMax(long max) {
		mMax = max;
	}

	public void onStep(int length) {
		super.onStep(length);
		updateProgress(mFromUrl, getCount(), mMax);
	}

	private void updateProgress(String fromUrl, long current, long max) {
		BundleFactory factory = new BundleFactory();
		factory.putString(FROM, fromUrl);
		factory.putLong(CURRENT, current);
		factory.putLong(MAX, max);

//		Bundle bundle = IntentUtil.createBundle();
//		bundle = IntentUtil.putString(bundle, FROM, fromUrl);
//		bundle = IntentUtil.putLong(bundle, CURRENT, current);
//		bundle = IntentUtil.putLong(bundle, MAX, max);
		Intent localIntent = new Intent(PROGRESS).putExtras(factory.execute(null));
		LocalBroadcastManager.getInstance(mContext).sendBroadcast(localIntent);
	}
}
