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
package poisondog.android.content;

import android.app.Activity;
import android.content.Intent;
import poisondog.core.Mission;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class MoveActivityForResult implements Mission<Class> {
	private Activity mFrom;
	private int mState;

	public MoveActivityForResult(Activity from, int state) {
		mFrom = from;
		mState = state;
	}

	@Override
	public Boolean execute(Class to) {
		Intent intent = new Intent();
		intent.setClass(mFrom, to);
		mFrom.startActivityForResult(intent, mState);
		return true;
	}
}
