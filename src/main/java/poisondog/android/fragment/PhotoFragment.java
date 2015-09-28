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
package poisondog.android.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import poisondog.android.app.DebugFragment;
import poisondog.android.app.R;
import poisondog.android.view.PictureWall;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class PhotoFragment extends DebugFragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
	private PictureWall mPictureWall;
	private SwipeRefreshLayout mSwipeRefreshLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.photo_grid, container, false);
		mPictureWall = (PictureWall)v.findViewById(R.id.list);
		mPictureWall.setFastScrollEnabled(true);
		mPictureWall.setOnScrollListener(this);
		mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.photo_grid_swipe_layout);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mSwipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_red_light,
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light);
		return v;
	}

	public SwipeRefreshLayout getSwipeRefreshLayout() {
		return mSwipeRefreshLayout;
	}

	public PictureWall getPictureWall() {
		return mPictureWall;
	}

	@Override
	public void onRefresh() {
		mSwipeRefreshLayout.setRefreshing(true);
		mSwipeRefreshLayout.setRefreshing(false);
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
