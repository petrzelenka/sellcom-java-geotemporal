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

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.OCTOBER;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.internal.time.TemporalPredicateUtils.testTemporalPredicate;
import static org.sellcom.geotemporal.time.DaysOfWeek.isInRange;
import static org.sellcom.geotemporal.time.DaysOfWeek.next;
import static org.sellcom.geotemporal.time.DaysOfWeek.previous;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class DaysOfWeekTest {

	@Test
	public void testIsInRange_notWrappingAroundTheEndOfTheWeek() {
		TemporalPredicate predicate = (temporal ->
				isInRange(DayOfWeek.from(temporal), FRIDAY, SATURDAY));

		Set<LocalDate> fridaysAndSaturdaysInOctober2014 = new HashSet<>();
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 3));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 4));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 10));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 11));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 17));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 18));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 24));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 25));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 31));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				fridaysAndSaturdaysInOctober2014);

		Set<LocalDate> fridaysAndSaturdaysInOctober2015 = new HashSet<>();
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 2));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 3));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 9));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 10));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 16));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 17));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 23));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 24));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 30));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 31));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				fridaysAndSaturdaysInOctober2015);

		Set<LocalDate> fridaysAndSaturdaysInOctober2016 = new HashSet<>();
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 1));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 7));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 8));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 14));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 15));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 21));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 22));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 28));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 29));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				fridaysAndSaturdaysInOctober2016);
	}

	@Test
	public void testIsInRange_wrappingAroundTheEndOfTheWeek() {
		TemporalPredicate predicate = (temporal ->
				isInRange(DayOfWeek.from(temporal), SUNDAY, THURSDAY));

		Set<LocalDate> sundaysToThursdaysInOctober2014 = new HashSet<>();
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 1));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 2));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 5));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 6));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 7));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 8));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 9));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 12));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 13));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 14));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 15));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 16));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 19));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 20));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 21));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 22));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 23));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 26));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 27));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 28));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 29));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 30));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				sundaysToThursdaysInOctober2014);

		Set<LocalDate> sundaysToThursdaysInOctober2015 = new HashSet<>();
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 1));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 4));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 5));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 6));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 7));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 8));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 11));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 12));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 13));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 14));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 15));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 18));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 19));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 20));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 21));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 22));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 25));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 26));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 27));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 28));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 29));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				sundaysToThursdaysInOctober2015);

		Set<LocalDate> sundaysToThursdaysInOctober2016 = new HashSet<>();
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 2));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 3));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 4));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 5));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 6));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 9));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 10));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 11));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 12));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 13));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 16));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 17));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 18));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 19));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 20));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 23));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 24));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 25));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 26));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 27));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 30));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 31));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				sundaysToThursdaysInOctober2016);
	}

	@Test
	public void testNext() {
		assertThat(next(MONDAY), is(equalTo(TUESDAY)));
		assertThat(next(TUESDAY), is(equalTo(WEDNESDAY)));
		assertThat(next(WEDNESDAY), is(equalTo(THURSDAY)));
		assertThat(next(THURSDAY), is(equalTo(FRIDAY)));
		assertThat(next(FRIDAY), is(equalTo(SATURDAY)));
		assertThat(next(SATURDAY), is(equalTo(SUNDAY)));
		assertThat(next(SUNDAY), is(equalTo(MONDAY)));
	}

	@Test
	public void testPrevious() {
		assertThat(previous(MONDAY), is(equalTo(SUNDAY)));
		assertThat(previous(TUESDAY), is(equalTo(MONDAY)));
		assertThat(previous(WEDNESDAY), is(equalTo(TUESDAY)));
		assertThat(previous(THURSDAY), is(equalTo(WEDNESDAY)));
		assertThat(previous(FRIDAY), is(equalTo(THURSDAY)));
		assertThat(previous(SATURDAY), is(equalTo(FRIDAY)));
		assertThat(previous(SUNDAY), is(equalTo(SATURDAY)));
	}

}
