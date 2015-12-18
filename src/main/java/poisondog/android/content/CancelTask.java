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

import android.content.DialogInterface;
import poisondog.concurrent.Cancellable;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class CancelTask implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
	private Cancellable mTask;

	public CancelTask(Cancellable task) {
		mTask = task;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		if (mTask != null)
			mTask.cancel(false);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (mTask != null)
			mTask.cancel(false);
	}
}
