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
package poisondog.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import poisondog.android.log.AndroidLogger;
import poisondog.log.Logger;
import poisondog.log.LogLevel;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class DebugFragment extends Fragment {
	protected Logger mLogger;

	public DebugFragment() {
		mLogger = new AndroidLogger(getClass().getSimpleName());
		mLogger.log(LogLevel.TRACE, "constructor");
	}

	public void printClassMethodName() {
		Exception e = new Exception();
		e.fillInStackTrace();
		mLogger.log(LogLevel.TRACE, "Method Name: " + e.getStackTrace()[1].getMethodName());
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		printClassMethodName();
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		printClassMethodName();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View result = super.onCreateView(inflater, container, savedInstanceState);
		printClassMethodName();
		return result;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		printClassMethodName();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		printClassMethodName();
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		printClassMethodName();
	}

	@Override
	public void onStart () {
		super.onStart();
		printClassMethodName();
	}

	@Override
	public void onResume () {
		super.onResume();
		printClassMethodName();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		printClassMethodName();
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		printClassMethodName();
	}

	@Override
	public void onPause() {
		super.onPause();
		printClassMethodName();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		printClassMethodName();
	}

	@Override
	public void onStop() {
		super.onStop();
		printClassMethodName();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		printClassMethodName();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		printClassMethodName();
	}

	@Override
	public void onDetach () {
		super.onDetach();
		printClassMethodName();
	}
}
