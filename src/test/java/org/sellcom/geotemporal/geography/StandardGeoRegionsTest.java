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
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Austria;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Belgium;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Brazil;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Canada;
import org.sellcom.geotemporal.geography.StandardGeoRegions.China;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Croatia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.CzechRepublic;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Denmark;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Estonia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Finland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.France;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Georgia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Germany;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Guernsey;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Hungary;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Iceland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Iraq;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Israel;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Italy;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Malta;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Moldova;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Netherlands;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Norway;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Poland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Portugal;
import org.sellcom.geotemporal.geography.StandardGeoRegions.RepublicOfIreland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Romania;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Russia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Serbia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Slovakia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Slovenia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Spain;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Sweden;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Switzerland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Ukraine;
import org.sellcom.geotemporal.geography.StandardGeoRegions.UnitedKingdom;
import org.sellcom.geotemporal.geography.StandardGeoRegions.UnitedStates;

public class StandardGeoRegionsTest {

	@Test
	public void testCodes_austria() {
		testGeoRegionCodes(Austria.class);
	}

	@Test
	public void testCodes_belgium() {
		testGeoRegionCodes(Belgium.class);
	}

	@Test
	public void testCodes_brazil() {
		testGeoRegionCodes(Brazil.class);
	}

	@Test
	public void testCodes_canada() {
		testGeoRegionCodes(Canada.class);
	}

	@Test
	public void testCodes_china() {
		testGeoRegionCodes(China.class);
	}

	@Test
	public void testCodes_croatia() {
		testGeoRegionCodes(Croatia.class);
	}

	@Test
	public void testCodes_czechRepublic() {
		testGeoRegionCodes(CzechRepublic.class);
	}

	@Test
	public void testCodes_denmark() {
		testGeoRegionCodes(Denmark.class);
	}

	@Test
	public void testCodes_estonia() {
		testGeoRegionCodes(Estonia.class);
	}

	@Test
	public void testCodes_finland() {
		testGeoRegionCodes(Finland.class);
	}

	@Test
	public void testCodes_france() {
		testGeoRegionCodes(France.class);
	}

	@Test
	public void testCodes_georgia() {
		testGeoRegionCodes(Georgia.class);
	}

	@Test
	public void testCodes_germany() {
		testGeoRegionCodes(Germany.class);
	}

	@Test
	public void testCodes_guernsey() {
		testGeoRegionCodes(Guernsey.class);
	}

	@Test
	public void testCodes_hungary() {
		testGeoRegionCodes(Hungary.class);
	}

	@Test
	public void testCodes_iceland() {
		testGeoRegionCodes(Iceland.class);
	}

	@Test
	public void testCodes_iraq() {
		testGeoRegionCodes(Iraq.class);
	}

	@Test
	public void testCodes_israel() {
		testGeoRegionCodes(Israel.class);
	}

	@Test
	public void testCodes_italy() {
		testGeoRegionCodes(Italy.class);
	}

	@Test
	public void testCodes_malta() {
		testGeoRegionCodes(Malta.class);
	}

	@Test
	public void testCodes_moldova() {
		testGeoRegionCodes(Moldova.class);
	}

	@Test
	public void testCodes_netherlands() {
		testGeoRegionCodes(Netherlands.class);
	}

	@Test
	public void testCodes_norway() {
		testGeoRegionCodes(Norway.class);
	}

	@Test
	public void testCodes_poland() {
		testGeoRegionCodes(Poland.class);
	}

	@Test
	public void testCodes_portugal() {
		testGeoRegionCodes(Portugal.class);
	}

	@Test
	public void testCodes_republicOfOreland() {
		testGeoRegionCodes(RepublicOfIreland.class);
	}

	@Test
	public void testCodes_romania() {
		testGeoRegionCodes(Romania.class);
	}

	@Test
	public void testCodes_russia() {
		testGeoRegionCodes(Russia.class);
	}

	@Test
	public void testCodes_serbia() {
		testGeoRegionCodes(Serbia.class);
	}

	@Test
	public void testCodes_slovakia() {
		testGeoRegionCodes(Slovakia.class);
	}

	@Test
	public void testCodes_slovenia() {
		testGeoRegionCodes(Slovenia.class);
	}

	@Test
	public void testCodes_spain() {
		testGeoRegionCodes(Spain.class);
	}

	@Test
	public void testCodes_sweden() {
		testGeoRegionCodes(Sweden.class);
	}

	@Test
	public void testCodes_switzerland() {
		testGeoRegionCodes(Switzerland.class);
	}

	@Test
	public void testCodes_ukraine() {
		testGeoRegionCodes(Ukraine.class);
	}

	@Test
	public void testCodes_unitedKingdom() {
		testGeoRegionCodes(UnitedKingdom.class);
	}

	@Test
	public void testCodes_unitedStates() {
		testGeoRegionCodes(UnitedStates.class);
	}


	private static Collection<String> extractGeoRegionCodes(Class<?> namespace) {
		return Arrays.stream(namespace.getFields())
				.map(currentField -> {
					try {
						return (String) currentField.get(null);
					} catch (Exception e) {
						throw new AssertionError(String.format("Cannot extract georegion code %s#%s", namespace.getName(), currentField.getName()));
					}
				})
				.collect(Collectors.toList());
	}

	private static void testGeoRegionCodes(Class<?> namespace) {
		Set<String> codesEncountered = new HashSet<>();

		extractGeoRegionCodes(namespace).forEach(currentCode ->
				assertThat(String.format("Code is not unique: %s", currentCode), codesEncountered.add(currentCode), is(true)));
	}

}
