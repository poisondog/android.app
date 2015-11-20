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
package poisondog.android.multiselector;

import android.support.v7.view.ActionMode;
import android.view.Menu;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public abstract class MultiSelectActionMode implements ActionMode.Callback {
	private MultiSelector mMultiSelector;

	public MultiSelectActionMode(MultiSelector selector) {
		mMultiSelector = selector;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		mMultiSelector.setState(MultiSelector.State.CREATE_ACTION_MODE);
		return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		mMultiSelector.clearSelections();
		mMultiSelector.setState(MultiSelector.State.DESTROY_ACTION_MODE);
	}
}
