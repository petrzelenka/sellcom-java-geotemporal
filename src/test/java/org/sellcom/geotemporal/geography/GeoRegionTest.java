/*
 * Copyright (c) 2015-2018 Petr Zelenka <petr.zelenka@sellcom.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sellcom.geotemporal.geography;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.geography.GeoRegion.ROOT;
import static org.sellcom.geotemporal.geography.GeoRegion.parse;

import org.junit.Test;

public class GeoRegionTest {

	@Test
	public void testGetParent() {
		assertThat(new GeoRegion("CZ").getParent(), is(equalTo(ROOT)));
		assertThat(new GeoRegion("CZ", "031").getParent(), is(equalTo(new GeoRegion("CZ"))));
		assertThat(new GeoRegion("CZ", "031", "1").getParent(), is(equalTo(new GeoRegion("CZ", "031"))));
		assertThat(new GeoRegion("CZ", "031", "1", "544256").getParent(), is(equalTo(new GeoRegion("CZ", "031", "1"))));
	}

	@Test
	public void testParse() {
		assertThat(parse("CZ"), is(equalTo(new GeoRegion("CZ"))));
		assertThat(parse("CZ,031"), is(equalTo(new GeoRegion("CZ", "031"))));
		assertThat(parse("CZ,031,1"), is(equalTo(new GeoRegion("CZ", "031", "1"))));
		assertThat(parse("CZ,031,1,544256"), is(equalTo(new GeoRegion("CZ", "031", "1", "544256"))));
	}

}
