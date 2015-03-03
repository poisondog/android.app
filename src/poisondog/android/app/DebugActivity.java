/*
 * Copyright (C) 2014 Adam Huang
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
package poisondog.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import poisondog.android.log.AndroidLogger;
import poisondog.log.Logger;
import poisondog.log.LogLevel;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class DebugActivity extends ActionBarActivity {
	protected Logger mLogger;

	public DebugActivity() {
		mLogger = new AndroidLogger(getClass().getSimpleName());
		mLogger.log(LogLevel.TRACE, "constructor");
	}

	public void printClassMethodName() {
		Exception e = new Exception();
		e.fillInStackTrace();
		mLogger.log(LogLevel.TRACE, "Method Name: " + e.getStackTrace()[1].getMethodName());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		printClassMethodName();
	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		printClassMethodName();
	}

//	@Override
//	public void onContentChanged() {
//		super.onContentChanged();
//		printClassMethodName();
//	}

	@Override
	protected void onStart() {
		super.onStart();
		printClassMethodName();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		printClassMethodName();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		printClassMethodName();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		printClassMethodName();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		printClassMethodName();
	}

	@Override
	protected void onResume() {
		super.onResume();
		printClassMethodName();
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		printClassMethodName();
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		printClassMethodName();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		printClassMethodName();
		return result;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean result = super.onPrepareOptionsMenu(menu);
		printClassMethodName();
		return result;
	}

	@Override
	public void onUserInteraction() {
		super.onUserInteraction();
		printClassMethodName();
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		printClassMethodName();
	}

	@Override
	protected void onPause() {
		super.onPause();
		printClassMethodName();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		printClassMethodName();
	}

	@Override
	protected void onStop() {
		super.onStop();
		printClassMethodName();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		printClassMethodName();
	}

	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		super.onTitleChanged(title, color);
		printClassMethodName();
	}
}
