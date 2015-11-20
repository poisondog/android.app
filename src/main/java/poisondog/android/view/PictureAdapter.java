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
import android.widget.Toast;
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
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import android.support.v7.widget.RecyclerView;
import java.util.LinkedList;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
/*
public class PictureAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter {
	private Activity mActivity;
	private Map<String, List<HideInfo>> mMap;
	private List<HideInfo> mInfos;
	private Mission<HideInfo> mImageUrlFactory;
	private Mission<HideInfo> mThumbnailUrlFactory;
	private View.OnClickListener mItemClickListener;
	private ImageFetcher mFetcher;
	private MultiSelector mMultiSelector;
	private ActionMode.Callback mActionMode;
	private SwappingHolder mHolder;

	public PictureAdapter(Activity activity, Map<String, List<HideInfo>> map) throws Exception {
		mActivity = activity;
		mMap = map;

		GetExternalCacheDir task = new GetExternalCacheDir();
		String cachePath = task.execute(activity);
		mFetcher = new ImageFetcher(activity, 96, 96, cachePath);
		mFetcher.setImageCache(new ImageCache(activity, cachePath));

		mInfos = new LinkedList<HideInfo>();
		for (List<HideInfo> list : mMap.values()) {
			mInfos.addAll(list);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_grid_item, null);
		ViewHolder viewHolder = new ViewHolder(itemLayoutView);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
		if (!(viewHolder instanceof ViewHolder))
			return;
		ViewHolder holder = (ViewHolder)viewHolder;
		HideInfo item = mInfos.get(position);
		String url = "";
		String thumbnail = "";
		try {
			url = mImageUrlFactory.execute(item).toString();
			thumbnail = mThumbnailUrlFactory.execute(item).toString();
		}catch(Exception e) {
		}

		holder.getPhotoView().setImageUrl(url);
		holder.getPhotoView().setThumbnailUrl(thumbnail);
		holder.getPhotoView().setOnClickListener(mItemClickListener);
		mHolder = new SwappingHolder(holder.getPhotoView(), mMultiSelector);
		holder.getPhotoView().setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (!(mActivity instanceof AppCompatActivity))
					return false;
				AppCompatActivity activity = (AppCompatActivity)mActivity;
				activity.startSupportActionMode(mActionMode);
				mMultiSelector.setSelected(mHolder, true);
				return true;
			}
		});
		holder.getPhotoView().setLongClickable(true);
		mFetcher.loadThumbnail(holder.getPhotoView());
	}

	@Override
	public int getItemCount() {
		return mInfos.size();
	}

	@Override
	public long getHeaderId(int position) {
		int id = 0;
		for (String key : mMap.keySet()) {
			id += mMap.get(key).size();
			if (id > position)
				return id;
		}
		return id;
	}

	@Override
	public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
		TextView text = new TextView(parent.getContext());
		text.setText("Title");
		return new HeaderHolder(text);
	}

	@Override
	public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
	}

	public static class HeaderHolder extends RecyclerView.ViewHolder {
		public HeaderHolder(View view) {
			super(view);
		}
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private ProgressBar progress;
		private PhotoView image;
		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);
			itemLayoutView.setOnClickListener(this);

			progress = (ProgressBar) itemLayoutView.findViewById(R.id.circle_progress_item);
			progress.setVisibility(View.VISIBLE);
			image = (PhotoView) itemLayoutView.findViewById(R.id.image_item);
		}

		@Override
		public void onClick(View view) {
			Toast.makeText(view.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
		}

		public PhotoView getPhotoView() {
			return image;
		}
	}

	public void setMultiSelector(MultiSelector selector) {
		mMultiSelector = selector;
	}

	public void setActionMode(ActionMode.Callback mode) {
		mActionMode = mode;
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
}
*/
