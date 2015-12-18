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
package poisondog.android.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import poisondog.android.dialog.DialogParameter;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class SelectDialog extends AlertDialog {
	private DialogInterface.OnClickListener empty;
	private DialogParameter mParameter;

	public SelectDialog(DialogParameter parameter) {
		super(parameter.getContext());
		mParameter = parameter;
		empty = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface di, int whichButton) {
			}
		};
		this.setTitle(parameter.getTitle());
		this.setMessage(parameter.getMessage());
		this.setButton(DialogInterface.BUTTON_POSITIVE, parameter.getPositiveText(), empty);
		this.setButton(DialogInterface.BUTTON_NEGATIVE, parameter.getNegativeText(), empty);
		this.setButton(DialogInterface.BUTTON_NEUTRAL, parameter.getNeutralText(), empty);
	}

	public void setPositiveListener(DialogInterface.OnClickListener listener) {
		this.setButton(DialogInterface.BUTTON_POSITIVE, mParameter.getPositiveText(), listener);
	}

	public void setNeutralListener(DialogInterface.OnClickListener listener) {
		this.setButton(DialogInterface.BUTTON_NEUTRAL, mParameter.getNeutralText(), listener);
	}

	public void setNegativeListener(DialogInterface.OnClickListener listener) {
		this.setButton(DialogInterface.BUTTON_NEGATIVE, mParameter.getNegativeText(), listener);
	}
}
