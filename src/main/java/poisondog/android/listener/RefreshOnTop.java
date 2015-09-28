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
package poisondog.android.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class RefreshOnTop implements AbsListView.OnScrollListener {
	private SwipeRefreshLayout mSwipeRefreshLayout;

	public RefreshOnTop(SwipeRefreshLayout layout) {
		mSwipeRefreshLayout = layout;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		int topPos = -1;
		if(view.getChildAt(0) != null)
			topPos = view.getChildAt(0).getTop();
		if(firstVisibleItem == 0 && topPos == 0) {
			mSwipeRefreshLayout.setEnabled(true);
		}else{
			mSwipeRefreshLayout.setEnabled(false);
		}
	}
}
