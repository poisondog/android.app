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
package poisondog.android.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import poisondog.core.Mission;
import poisondog.net.URLUtils;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
public class ShareMission implements Mission<ShareParameter> {
	private Context mContext;

	public ShareMission(Context context) {
		mContext = context;
	}

	@Override
	public Void execute(ShareParameter parameter) {
		if (parameter.getPaths().isEmpty())
			singleShare(parameter);
		else
			multiShare(parameter);
		return null;
	}

	private void singleShare(ShareParameter parameter) {
		Intent shareIntent=new Intent(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, parameter.getSubject());
		shareIntent.putExtra(Intent.EXTRA_TEXT, parameter.getText());
		shareIntent.setType(URLUtils.guessContentType(URLUtils.file(parameter.getPath())));
		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(parameter.getPath())));
		mContext.startActivity(Intent.createChooser(shareIntent, "Share..."));
	}

	private void multiShare(ShareParameter parameter) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, parameter.getSubject());
		shareIntent.putExtra(Intent.EXTRA_TEXT, parameter.getText());

		if (!parameter.getPaths().isEmpty())
			shareIntent.setType(URLUtils.guessContentType(URLUtils.file(parameter.getPaths().get(0))));

		ArrayList<Uri> files = new ArrayList<Uri>();
		for(String path : parameter.getPaths()) {
			files.add(Uri.fromFile(new File(path)));
		}
		shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
		mContext.startActivity(Intent.createChooser(shareIntent, "Share..."));
	}
}
