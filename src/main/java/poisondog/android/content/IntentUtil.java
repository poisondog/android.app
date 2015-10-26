/*
 * Copyright (C) 2013 Adam Huang <poisondog@gmail.com>
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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import poisondog.android.content.MoveActivity;
import poisondog.android.content.MoveActivityForResult;
import java.util.ArrayList;
import java.util.List;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class IntentUtil {
	private static final String KEY_BOOLEAN = "boolean.key";
	private static final String KEY_INT = "integer.key";
	private static final String KEY_STRING = "string.key";
	private static final String KEY_STRING_LIST = "string.list.key";
	private static final String KEY_SERIALIZABLE = "serializable.key";
	private static final String FILE_SERIALIZABLE = "serializable.file";

	public static void moveForResult(Activity from, Class to, int state) {
		MoveActivityForResult task = new MoveActivityForResult(from, state);
		task.execute(to);
	}

	public static void move(Context from, Class to) {
		MoveActivity task = new MoveActivity(from);
		task.execute(to);
	}

	public static void moveWithBundle(Context from, Class to, Bundle bundle) {
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(from, to);
		from.startActivity(intent);
	}

	public static void moveWithString(Context from, Class to, String value) {
		moveWithBundle(from, to, putString(createBundle(), value));
	}

	public static void moveWithSerializable(Context from, Class to, Serializable value) {
		moveWithBundle(from, to, putSerializable(createBundle(), value, from));
	}

	public static int getInt(Activity from) {
		Intent intent = from.getIntent();
		Bundle bundle = intent.getExtras();
		return bundle.getInt(KEY_INT);
	}

	public static String getString(Activity from) {
		return getString(from, KEY_STRING);
	}

	public static String getString(Activity from, String key) {
		Intent intent = from.getIntent();
		Bundle bundle = intent.getExtras();
		return bundle.getString(key);
	}

	public static List<String> getStringList(Activity from) {
		return getStringList(from, KEY_STRING_LIST);
	}

	public static List<String> getStringList(Activity from, String key) {
		Intent intent = from.getIntent();
		Bundle bundle = intent.getExtras();
		return bundle.getStringArrayList(key);
	}

	public static Serializable getSerializable(Activity from) {
		try{
			Intent intent = from.getIntent();
			Bundle bundle = intent.getExtras();
			File cache = from.getExternalCacheDir();
			FileInputStream fis = new FileInputStream(cache.getPath() + "/" + getString(from, KEY_SERIALIZABLE));
			ObjectInputStream ois = new ObjectInputStream(fis);
			Serializable result = (Serializable) ois.readObject();
			ois.close();
			return result;
		}catch(Exception e) {
		}
		return null;
	}

	public static Bundle createBundle() {
		return new Bundle();
	}

	public static Bundle putString(Bundle bundle, String value) {
		return putString(bundle, KEY_STRING, value);
	}

	public static Bundle putString(Bundle bundle, String key, String value) {
		bundle.putString(key, value);
		return bundle;
	}

	public static Bundle putStringList(Bundle bundle, List<String> value) {
		return putStringList(bundle, KEY_STRING_LIST, value);
	}

	public static Bundle putStringList(Bundle bundle, String key, List<String> value) {
		bundle. putStringArrayList(key, new ArrayList<String>(value));
		return bundle;
	}

	public static Bundle putSerializable(Bundle bundle, Serializable value, Context context) {
		try{
			File cache = context.getExternalCacheDir();
			FileOutputStream fos = new FileOutputStream(cache.getPath() + "/" + FILE_SERIALIZABLE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(value);
			os.close();
		}catch(Exception e) {
		}
		return putString(bundle, KEY_SERIALIZABLE, FILE_SERIALIZABLE);
	}

	public static Bundle putInt(Bundle bundle, int value) {
		return putInt(bundle, KEY_INT, value);
	}

	public static Bundle putInt(Bundle bundle, String key, int value) {
		bundle.putInt(key, value);
		return bundle;
	}

	public static Bundle putLong(Bundle bundle, String key, long value) {
		bundle.putLong(key, value);
		return bundle;
	}

	public static long getLong(Activity from, String key) {
		Intent intent = from.getIntent();
		Bundle bundle = intent.getExtras();
		return bundle.getLong(key);
	}

	public static Bundle putBoolean(Bundle bundle, String key, boolean value) {
		bundle.putBoolean(key, value);
		return bundle;
	}

	public static Bundle putBoolean(Bundle bundle, boolean value) {
		return putBoolean(bundle, KEY_BOOLEAN, value);
	}

	public static boolean getBoolean(Activity from) {
		return getBoolean(from, KEY_BOOLEAN);
	}

	public static boolean getBoolean(Activity from, String key) {
		Intent intent = from.getIntent();
		Bundle bundle = intent.getExtras();
		return bundle.getBoolean(key);
	}
}
