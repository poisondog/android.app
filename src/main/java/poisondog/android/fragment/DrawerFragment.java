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
package poisondog.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import poisondog.android.app.R;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class DrawerFragment extends Fragment {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ListAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.drawer_fragment_layout, container, false);

		mDrawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) rootView.findViewById(R.id.drawer_list);
		if (mAdapter != null)
			mDrawerList.setAdapter(mAdapter);

		return rootView;
	}

	public void setAdapter(ListAdapter adapter) {
		mAdapter = adapter;
		if (mDrawerList != null)
			mDrawerList.setAdapter(mAdapter);
	}

	public void setContent(Fragment fragment) {
		getFragmentManager().beginTransaction()
			.replace(R.id.content_frame, fragment)
			.commit();
	}

	public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
		if (mDrawerList != null)
			mDrawerList.setOnItemClickListener(listener);
	}
}
