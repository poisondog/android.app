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
package poisondog.android.dialog;

import android.content.Context;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class DialogParameter {
	private Context mContext;
	private String mTitle;
	private String mMessage;
	private String mPositiveText;
	private String mNegativeText;
	private String mNeutralText;

	public DialogParameter(Context context) {
		mContext = context;
		mTitle = "";
		mMessage = "";
		mPositiveText = "Done";
		mNegativeText = "Cancel";
		mNeutralText = "Other";
	}

	public Context getContext() {
		return mContext;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getMessage() {
		return mMessage;
	}

	public String getPositiveText() {
		return mPositiveText;
	}

	public String getNegativeText() {
		return mNegativeText;
	}

	public String getNeutralText() {
		return mNeutralText;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public void setMessage(String message) {
		mMessage = message;
	}

	public void setPositiveText(String text) {
		mPositiveText = text;
	}

	public void setNegativeText(String text) {
		mNegativeText = text;
	}

	public void setNeutralText(String text) {
		mNeutralText = text;
	}
}
