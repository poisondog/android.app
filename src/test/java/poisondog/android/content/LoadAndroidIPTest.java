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
package poisondog.android.content;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.junit.Test;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
/**
 * @author Adam Huang <poisondog@gmail.com>
 */
@RunWith(RobolectricTestRunner.class)
public class LoadAndroidIPTest {

	@Test
	public void testExecute() throws Exception {
		Assert.assertEquals("0.0.0.0", new LoadAndroidIP().execute(RuntimeEnvironment.application));
	}
}
