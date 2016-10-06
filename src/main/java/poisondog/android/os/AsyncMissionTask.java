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
import poisondog.core.NoMission;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class AsyncMissionTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
	private Mission<Progress> mProgress;
	private Mission<Params> mMission;
	private Mission<Result> mHandler;
	private Mission<Params> mCancel;
	private Params mInput;

	/**
	 * Constructor
	 */
	public AsyncMissionTask(Mission<Params> mission) {
		this(mission, new NoMission<Result>());
	}

	/**
	 * Constructor
	 */
	public AsyncMissionTask(Mission<Params> mission, Mission<Result> handler) {
		this(mission, new NoMission<Progress>(), handler);
	}

	/**
	 * Constructor
	 */
	public AsyncMissionTask(Mission<Params> mission, Mission<Progress> progress, Mission<Result> handler) {
		mMission = mission;
		mProgress = progress;
		mHandler = handler;
		mCancel = new NoMission<Params>();
	}

	@Override
	protected Result doInBackground(Params... params) {
		if (params.length > 1)
			throw new IllegalStateException("AsyncMissionTask input ONE parameter.");
		mInput = params[0];
		try {
			return (Result)mMission.execute(params[0]);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Progress... values) {
		super.onProgressUpdate(values);
		if (values.length > 1)
			throw new IllegalStateException("AsyncMissionTask input ONE parameter.");
		try {
			mProgress.execute(values[0]);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPostExecute(Result result) {
		try {
			mHandler.execute(result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		try {
			mCancel.execute(mInput);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
