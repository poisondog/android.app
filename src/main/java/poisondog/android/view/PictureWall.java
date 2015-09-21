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
package poisondog.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class PictureWall extends StickyListHeadersListView {
//	private View.OnClickListener mClickListener;

	public PictureWall(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

//	private void setClickListener(View.OnClickListener listener) {
//		mClickListener = listener;
//		setOnItemClickListener(new TempItemClickListener());
//	}
}
//class TempItemClickListener implements AdapterView.OnItemClickListener {
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		System.out.println("PictureWall: " + view);
//	}
//}