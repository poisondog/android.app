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
import android.widget.ImageView;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class PhotoView extends ImageView {
	private String mImage;
	private String mThumbnail;

	public PhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setImageUrl(String url) {
		mImage = url;
	}

	public String getImageUrl() {
		return mImage;
	}

	public void setThumbnailUrl(String url) {
		mThumbnail = url;
	}

	public String getThumbnailUrl() {
		return mThumbnail;
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
