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
package poisondog.android.handler;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import poisondog.android.content.CancelTask;
import poisondog.android.os.PostHandler;
import poisondog.android.util.TransportMonitor;
import poisondog.concurrent.Cancellable;
import poisondog.core.Mission;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class ProgressHandler extends BroadcastReceiver implements PostHandler, Mission<Object> {
	private Context mContext;
	private ProgressDialog mDialog;
	private DialogInterface.OnCancelListener mCancelListener;

	private ProgressHandler(Context context) {
		mContext = context;
		mDialog = new ProgressDialog(mContext);
		mDialog.setCanceledOnTouchOutside(false);
	}

	private ProgressHandler(Context context, Cancellable task) {
		this(context);
		mDialog.setOnCancelListener(new CancelTask(task));
	}

	private ProgressHandler(Context context, String title, String message) {
		this(context);
		mDialog.setTitle(title);
		mDialog.setMessage(message);
	}

	private ProgressHandler(Context context, String title, String message, Cancellable task) {
		this(context, title, message);
		mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mDialog.setOnCancelListener(new CancelTask(task));
	}

	private ProgressHandler(Context context, String title, String message, Cancellable task, String cancelText) {
		this(context, title, message, task);
		mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, cancelText, new CancelTask(task));
	}

	private void show() {
		mDialog.show();
		LocalBroadcastManager.getInstance(mContext).registerReceiver(this, new IntentFilter(TransportMonitor.PROGRESS));
	}

	public static ProgressHandler showSimple(Context context, String title, String message) {
		ProgressHandler handler = new ProgressHandler(context, title, message);
		handler.show();
		return handler;
	}

	public static ProgressHandler showSimple(Context context, Cancellable task) {
		ProgressHandler handler = new ProgressHandler(context, task);
		handler.show();
		return handler;
	}

	public static ProgressHandler showHorizontal(Context context, String title, String message) {
		ProgressHandler handler = new ProgressHandler(context, title, message, null, "");
		handler.show();
		return handler;
	}

	public static ProgressHandler showHorizontal(Context context, String title, String message, Cancellable task, String cancelText) {
		ProgressHandler handler = new ProgressHandler(context, title, message, task, cancelText);
		handler.show();
		return handler;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle b = intent.getExtras();
		int current = (int) b.getLong(TransportMonitor.CURRENT);
		int max = (int) b.getLong(TransportMonitor.MAX);
		mDialog.setProgress(current);
		mDialog.setMax(max);
	}

	@Override
	public void postExecute(Object result) {
		execute(result);
	}

	@Override
	public Object execute(Object result) {
		mDialog.dismiss();
		LocalBroadcastManager.getInstance(mContext).unregisterReceiver(this);
		return result;
	}
}
