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
package poisondog.android.os;

import poisondog.core.Mission;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class AsyncMissionTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	private Mission<Params> mMission;
	private Mission<Result> mHandler;

	public AsyncMissionTask(Mission<Params> mission) {
		this(mission, null);
	}

	public AsyncMissionTask(Mission<Params> mission, Mission<Result> handler) {
		mMission = mission;
		mHandler = handler;
	}

	@Override
	protected Result doInBackground(Params... params) {
		if (params.length > 1)
			throw new IllegalStateException("AsyncMissionTask input ONE parameter.");
		Result result = null;
		try {
			result = (Result) mMission.execute(params[0]);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(Result result) {
		if (mHandler == null)
			return;
		try {
			mHandler.execute(result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
