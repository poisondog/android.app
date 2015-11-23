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
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import poisondog.core.HideInfo;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class PhotoView extends FrameLayout implements HideInfo, Focusable {
	private ImageView mImageView;
	private ImageView mFocuseView;
	private String mImage;
	private String mThumbnail;
	private boolean mFocusing;

	public PhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mImageView = new ImageView(context);
		addView(mImageView);

		mFocuseView = new ImageView(context);
		mFocuseView.setBackgroundColor(Color.BLUE);
		mFocuseView.setAlpha((float)0.5);
	}

	@Override
	public void focuse() {
		mFocusing = true;
		mFocuseView.setLayoutParams(mImageView.getLayoutParams());
		addView(mFocuseView);
	}

	@Override
	public void unfocuse() {
		removeView(mFocuseView);
		mFocusing = false;
	}

	@Override
	public void toggleFocuse() {
		if (mFocusing)
			unfocuse();
		else
			focuse();
	}

	public void setImageUrl(String url) {
		mImage = url;
	}

	public String getImageUrl() {
		return mImage;
	}

	public ImageView getImageView() {
		return mImageView;
	}

	public void setThumbnailUrl(String url) {
		mThumbnail = url;
	}

	public String getThumbnailUrl() {
		return mThumbnail;
	}

	@Override
	public void setInfo(String info) {
		mImage = info;
	}

	@Override
	public String getInfo() {
		return mImage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image: ");
		builder.append(mImage);
		builder.append("\n");
		builder.append("Thumbnail: ");
		builder.append(mThumbnail);
		builder.append("\n");
		return builder.toString();
	}
}
