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
package poisondog.android.task;

import android.content.Context;
import poisondog.android.util.TransportMonitor;
import poisondog.core.Mission;
import poisondog.io.CopyTask;
import poisondog.vfs.FileFactory;
import poisondog.vfs.IData;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class MonitorCopyTask implements Mission<Context> {
	private String mFrom;
	private String mTo;

	public MonitorCopyTask(String from, String to) {
		mFrom = from;
		mTo = to;
	}

	@Override
	public Context execute(Context context) throws Exception {
		IData from = (IData)FileFactory.getFile(mFrom);
		IData to = (IData)FileFactory.getFile(mTo);

		TransportMonitor monitor = new TransportMonitor(context, from.getUrl());
		CopyTask task = new CopyTask(from.getInputStream(), to.getOutputStream());
		monitor.setMax(from.getSize());
		task.addStepListener(monitor);
		task.transport();
		return context;
	}
}
