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
package poisondog.android.os;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import poisondog.core.Mission;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class MissionHandler extends Handler {
	private Mission<Message> mMission;

	public MissionHandler(Mission<Message> mission) {
		super(Looper.getMainLooper());
		mMission = mission;
	}

	public MissionHandler(Looper looper, Mission<Message> mission) {
		super(looper);
		mMission = mission;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		try {
			mMission.execute(msg);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
