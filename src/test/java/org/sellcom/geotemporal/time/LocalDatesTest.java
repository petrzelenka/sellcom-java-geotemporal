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
package org.sellcom.geotemporal.time;

import static java.time.Month.AUGUST;
import static java.time.Month.JULY;
import static java.util.Collections.emptySet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeNavigableSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.internal.time.TemporalPredicateUtils.testTemporalPredicate;
import static org.sellcom.geotemporal.time.LocalDates.isInRange;

import java.time.LocalDate;

import org.junit.Test;

public class LocalDatesTest {

	@Test
	public void testIsInRange() {
		TemporalPredicate predicate = (temporal ->
				isInRange(LocalDate.from(temporal), LocalDate.of(2012, JULY, 27), LocalDate.of(2012, AUGUST, 12)));

		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2011, JULY, 1), LocalDate.of(2011, AUGUST, 30)),
				emptySet());
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2012, JULY, 1), LocalDate.of(2012, AUGUST, 30)),
				dateRangeNavigableSet(LocalDate.of(2012, JULY, 27), LocalDate.of(2012, AUGUST, 12)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2013, JULY, 1), LocalDate.of(2013, AUGUST, 30)),
				emptySet());
	}

}
