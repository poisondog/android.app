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

import poisondog.rule.Rule;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.content.Context;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 
 */
public class HasPermission implements Rule<HasPermission.Parameter> {

	@Override
	public Boolean execute(HasPermission.Parameter parameter) {
//		return parameter.mContext.checkSelfPermission(parameter.mPermission) == PackageManager.PERMISSION_GRANTED;
		return ContextCompat.checkSelfPermission(parameter.mContext, parameter.mPermission) == PackageManager.PERMISSION_GRANTED;
	}

	public class Parameter {
		private Context mContext;
		private String mPermission;

		/**
		 * Constructor
		 */
		public Parameter(Context context, String permission) {
			mContext = context;
			mPermission = permission;
		}
	}
}
