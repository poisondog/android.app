/*
 * Copyright (C)  Adam Huang <poisondog@gmail.com>
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
package poisondog.android.mission;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;
import poisondog.core.Mission;
import android.support.v4.app.ActivityCompat;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 
 */
public class RequestPermissions implements Mission<RequestPermissions.Parameter> {

	@Override
	public Parameter execute(RequestPermissions.Parameter parameter) {
//		parameter.mActivity.requestPermissions(parameter.mPermissions.toArray(new String[0]), parameter.mRequestCode);
		ActivityCompat.requestPermissions(parameter.mActivity, parameter.mPermissions.toArray(new String[0]), parameter.mRequestCode);
		return parameter;
	}

	public class Parameter {
		private Activity mActivity;
		private List<String> mPermissions;
		private int mRequestCode;

		/**
		 * Constructor
		 */
		public Parameter(Activity activity, int requestCode, String... permissions) {
			mActivity = activity;
			mRequestCode = requestCode;
			mPermissions = new LinkedList<String>();
			for (String permission : permissions) {
				mPermissions.add(permission);
			}
		}
	}

}
