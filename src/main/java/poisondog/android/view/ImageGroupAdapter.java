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
package poisondog.android.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.support.v7.app.AppCompatActivity;
import org.apache.commons.lang.builder.HashCodeBuilder;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import poisondog.android.app.R;
import android.support.v7.view.ActionMode;
import poisondog.android.image.ImageCache;
import poisondog.android.image.ImageFetcher;
import poisondog.android.util.GetDisplayWidth;
import poisondog.android.util.GetExternalCacheDir;
import poisondog.android.view.PhotoView;
import poisondog.core.HideInfo;
import poisondog.core.Mission;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import poisondog.android.multiselector.MultiSelector;
import poisondog.android.multiselector.MarkSelected;
import com.bignerdranch.android.multiselector.SwappingHolder;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class ImageGroupAdapter extends BaseAdapter implements SectionIndexer, StickyListHeadersAdapter {
	private final int ROW_COUNT = 3;
	private Activity mActivity;
	private ImageFetcher mFetcher;
	private List<String> mGroupNames;
	private List<List<HideInfo>> mItemRows;
	private List<LinearLayout> mRowLayouts;
	private int mPosition;
	private List<Integer> mSectionIndices;
	private View.OnClickListener mItemClickListener;
//	private View.OnLongClickListener mItemLongClickListener;
	private Mission<HideInfo> mImageUrlFactory;
	private Mission<HideInfo> mThumbnailUrlFactory;
	private Mission<Activity> mWidthCalculator;
	private MultiSelector mMultiSelector;

	public ImageGroupAdapter(Activity activity, Map<String, List<HideInfo>> files) throws Exception {
		super();
		mActivity = activity;
		mWidthCalculator = new GetDisplayWidth();

		GetExternalCacheDir task = new GetExternalCacheDir();
		String cachePath = task.execute(activity);
		mFetcher = new ImageFetcher(activity, 96, 96, cachePath);
		mFetcher.setImageCache(new ImageCache(activity, cachePath));

		mGroupNames = new ArrayList<String>(files.keySet());
		mItemRows = convertViews(files);
		calculateSection(files);

		initRowLayoutsList();
	}
	
	private void initRowLayoutsList(){
		mRowLayouts = new ArrayList<LinearLayout>();
		for(int i = 0; i < mItemRows.size(); i++)
			mRowLayouts.add(new LinearLayout(mActivity));
	}

	private List<List<HideInfo>> convertViews(Map<String, List<HideInfo>> map) {
		List<List<HideInfo>> views = new ArrayList<List<HideInfo>>();
		for (String key : map.keySet()) {
			List<HideInfo> row = new ArrayList<HideInfo>(ROW_COUNT);
			for (HideInfo v : map.get(key)) {
				if(row.size() == ROW_COUNT){
					views.add(row);
					row = new ArrayList<HideInfo>(ROW_COUNT);
				}
				row.add(v);
			}
			if(!row.isEmpty())
				views.add(row);
		}
		return views;
	}

	private void calculateSection(Map<String, List<HideInfo>> map) {
		mSectionIndices = new ArrayList<Integer>();
		int index = 0;
		for (String key : map.keySet()) {
			mSectionIndices.add(index);
			if (map.get(key).size() % ROW_COUNT > 0)
				index += map.get(key).size() / ROW_COUNT + 1;
			else
				index += map.get(key).size() / ROW_COUNT;
		}
	}

	@Override
	public boolean isEmpty() {
		return mItemRows.isEmpty();
	}

	@Override
	public int getCount() {
		return mItemRows.size();
	}

	@Override
	public Object getItem(int position) {
		return mItemRows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object obj = getItem(position);
		mPosition = position;
		if(obj instanceof String)
			return createGroupName((String)obj);
		if(obj instanceof List)
			return createImageRow((List<HideInfo>)obj);
		return null;
	}

	private void adjustImageRowWidth(LinearLayout list) {
		for (int i = 0; i < list.getChildCount(); i++) {
			int padding = 5;
			View layout = (View) list.getChildAt(i);
			if(i == list.getChildCount()-1)
				layout.setPadding(0,0,0,padding);
			else
				layout.setPadding(0,0,padding,padding);
			View image = (View) layout.findViewById(R.id.image_item);
			int width = (int)((getDisplayWidth()-padding*(list.getChildCount()-1))/list.getChildCount());
			ViewGroup.LayoutParams params = image.getLayoutParams();
			params.height = width;
			params.width = width;
		}
	}

	private View createImageRow(List<HideInfo> photos) {
		LinearLayout list = new LinearLayout(mActivity);
		list.setOrientation(LinearLayout.HORIZONTAL);

		for (int i = 0; i < ROW_COUNT; i++) {
			View layout = createImageItem();
			list.addView(layout);

			if (i >= photos.size())
				continue;

			ProgressBar progress = (ProgressBar) layout.findViewById(R.id.circle_progress_item);
			progress.setVisibility(View.VISIBLE);
			
			HideInfo item = photos.get(i);
			PhotoView image = (PhotoView) layout.findViewById(R.id.image_item);
			String url = "";
			String thumbnail = "";
			try {
				url = mImageUrlFactory.execute(item).toString();
				thumbnail = mThumbnailUrlFactory.execute(item).toString();
			}catch(Exception e) {
			}

			MarkSelected mark = new MarkSelected(mMultiSelector);
			mark.setClickListener(mItemClickListener);

			image.setImageUrl(url);
			image.setThumbnailUrl(thumbnail);
			image.setOnClickListener(mark);
			image.setOnLongClickListener(mark);
			image.setLongClickable(true);
//			image.setOnLongClickListener(mItemLongClickListener);

			if (mMultiSelector!= null && mMultiSelector.contains(image.getInfo()))
				image.focuse();
			mFetcher.loadThumbnail(image);
		}
		adjustImageRowWidth(list);

		if(mRowLayouts.size() > mPosition)
			mRowLayouts.remove(mPosition);
		mRowLayouts.add(mPosition, list);
		return list;
	}

	public void setMultiSelector(MultiSelector selector) {
		mMultiSelector = selector;
	}

	public void setImageUrlFactory(Mission<HideInfo> factory) {
		mImageUrlFactory = factory;
	}

	public void setThumbnailUrlFactory(Mission<HideInfo> factory) {
		mThumbnailUrlFactory = factory;
	}

	public void setItemClickListener(View.OnClickListener listener) {
		mItemClickListener = listener;
	}

//	public void setItemLongClickListener(View.OnLongClickListener listener) {
//		mItemLongClickListener = listener;
//	}

	private View createImageItem() {
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		View row = (View) inflater.inflate(R.layout.photo_grid_item, null);
		return row;
	}

	private int getDisplayWidth() {
		int result = new GetDisplayWidth().execute(mActivity);
		try {
			result = (int)mWidthCalculator.execute(mActivity);
		} catch(Exception e) {
		}
		return result;
	}

	public void setWidthCalculator(Mission<Activity> calculator) {
		mWidthCalculator = calculator;
	}

	private View createGroupName(String name) {
		TextView view = new TextView(mActivity);
		view.setTextSize(20);
		view.setBackgroundColor(Color.parseColor("#CC272727"));
		view.setText(name);
		return view;
	}

	@Override
	public int getPositionForSection(int section) {
		if (section >= mGroupNames.size()) {
			return mGroupNames.size() - 1;
		} else if (section < 0) {
			return 0;
		}
		return mSectionIndices.get(section);
	}

	@Override
	public int getSectionForPosition(int position) {
		for (int i = 0; i < mSectionIndices.size(); i++) {
			if (position < mSectionIndices.get(i)) {
				return i - 1;
			}
		}
		return mSectionIndices.size() - 1;
	}

	@Override
	public Object[] getSections() {
		return mGroupNames.toArray(new String[0]);
	}

	@Override
	public long getHeaderId(int position) {
		return getSectionForPosition(position);
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		return createGroupName(mGroupNames.get(getSectionForPosition(position)));
	}

	public List<LinearLayout> getRowLayoutList() {
		return mRowLayouts;
	}

	public int getRowPosition(String absolutePath) {
		for(int i = 0; i < mItemRows.size(); i++)
			for(HideInfo item : mItemRows.get(i))
				if(absolutePath.endsWith(item.getInfo()))
					return i;
		return -1;
	}

	public int getColumnPosition(String absolutePath) {
		for(int i = 0; i < mItemRows.size(); i++)
			for(int j = 0; j < mItemRows.get(i).size(); j++)
				if(absolutePath.endsWith(mItemRows.get(i).get(j).getInfo()))
					return j;
		return -1;
	}
}
