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
package org.sellcom.geotemporal.internal.time;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.temporal.Temporal;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.time.HolidayPredicate;
import org.sellcom.geotemporal.time.HolidayType;

public class HolidayPredicateUtils {

	private HolidayPredicateUtils() {
		// Utility class, not to be instantiated
	}


	public static <T extends Temporal> void testHolidayPredicate(HolidayPredicate predicate, GeoRegion region, EnumSet<HolidayType> additionalHolidayTypes, Stream<T> temporalsToTest, Set<T> temporalsToPass) {
		temporalsToTest.forEach(temporal ->
				assertThat(String.valueOf(temporal), predicate.test(temporal, region, additionalHolidayTypes), is(temporalsToPass.contains(temporal))));
	}

}
