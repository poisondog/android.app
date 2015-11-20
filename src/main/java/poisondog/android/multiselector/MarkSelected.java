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

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import poisondog.core.HideInfo;
import poisondog.android.view.Focusable;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class MarkSelected implements View.OnClickListener, View.OnLongClickListener {
	private MultiSelector mMultiSelector;
	private View.OnClickListener mClickListener;

	public MarkSelected(MultiSelector selector) {
		mMultiSelector = selector;
		mClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			}
		};
	}

	public void setClickListener(View.OnClickListener listener) {
		mClickListener = listener;
	}

	@Override
	public void onClick(View view) {
		if (!(view instanceof HideInfo))
			throw new IllegalArgumentException("this listener only handle class implements HideInfo");
		if (mMultiSelector == null || mMultiSelector.getState() != MultiSelector.State.CREATE_ACTION_MODE) {
			mClickListener.onClick(view);
			return;
		}
		HideInfo info = (HideInfo)view;
		mMultiSelector.toggleSelected(info.getInfo());

		if (view instanceof Focusable) {
			Focusable f = (Focusable)view;
			f.toggleFocuse();
		}
	}

	@Override
	public boolean onLongClick(View view) {
		if (!(view.getContext() instanceof AppCompatActivity))
			throw new IllegalArgumentException("the Context isn't AppCompatActivity");
		if (!(view instanceof HideInfo))
			throw new IllegalArgumentException("the View isn't HideInfo");

		if (mMultiSelector == null)
			return false;
		mMultiSelector.startActionMode((AppCompatActivity)view.getContext());
		HideInfo info = (HideInfo)view;
		mMultiSelector.markSelected(info.getInfo());

		if (view instanceof Focusable) {
			Focusable f = (Focusable)view;
			f.focuse();
		}
		return true;
	}
}
