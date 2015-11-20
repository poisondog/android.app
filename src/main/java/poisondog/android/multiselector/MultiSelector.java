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

import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import android.support.v7.view.ActionMode;
import android.support.v7.app.AppCompatActivity;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class MultiSelector {
	public enum State {CREATE_ACTION_MODE, DESTROY_ACTION_MODE}
	private Set<String> mSelected;
	private ActionMode.Callback mActionMode;
	private State mState;

	public MultiSelector() {
		clearSelections();
		setState(State.DESTROY_ACTION_MODE);
	}

	public void toggleSelected(String id) {
		if (mSelected.contains(id))
			mSelected.remove(id);
		else
			mSelected.add(id);
	}

	public void markSelected(String id) {
		mSelected.add(id);
	}

	public void clearSelections() {
		mSelected = new HashSet<String>();
	}

	public Collection<String> getSelected() {
		return mSelected;
	}

	public void startActionMode(AppCompatActivity activity) {
		if (mState == State.DESTROY_ACTION_MODE)
			activity.startSupportActionMode(mActionMode);
	}

	public void setActionMode(ActionMode.Callback mode) {
		mActionMode = mode;
	}

	public void setState(State state) {
		mState = state;
	}

	public State getState() {
		return mState;
	}
}
