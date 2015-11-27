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
package poisondog.android.content;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class ShareParameter {
	private String mSubject;
	private String mText;
	private String mType;
	private List<String> mPaths;
	private String mPath;

	public ShareParameter() {
		mSubject = "";
		mText = "";
		mType = "text/plain";
		mPaths = new ArrayList<String>();
	}

	public void setSubject(String subject) {
		mSubject = subject;
	}

	public void setText(String text) {
		mText = text;
	}

	public void setType(String type) {
		mType = type;
	}

	public void setPath(String path) {
		mPath = path;
	}

	public String getSubject() {
		return mSubject;
	}

	public ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();
		result.add(mText);
		return result;
	}

	public String getType() {
		return mType;
	}

	public String getPath() {
		return mPath;
	}

	public void addPath(String path) {
		mPaths.add(path);
	}

	public void removePath(String path) {
		mPaths.remove(path);
	}

	public List<String> getPaths() {
		return mPaths;
	}
}
