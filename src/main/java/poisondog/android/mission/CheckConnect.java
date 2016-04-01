/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
package poisondog.android.mission;

import java.net.HttpURLConnection;
import poisondog.net.http.HttpGet;
import poisondog.net.http.HttpParameter;
import poisondog.net.http.HttpResponse;
import poisondog.rule.Rule;

/**
 * @author Adam Huang <poisondog@gmail.com>
 * @since 2016-03-14
 */
public class CheckConnect implements Rule<String> {

	@Override
	public Boolean execute(String url) {
		HttpParameter para = new HttpParameter();
		para.setUrl(url);
		HttpGet get = new HttpGet();
		try {
			HttpResponse response = get.execute(para);
			System.out.println(response.getResponseCode());
			return response.getResponseCode() >= 200 && response.getResponseCode() < 300;
		} catch(Exception e) {
		}
		return false;
	}
}
