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

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.internal.CollectionUtils.singletonSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeNavigableSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.internal.time.TemporalPredicateUtils.testTemporalPredicate;
import static org.sellcom.geotemporal.time.MonthDays.isInRange;
import static org.sellcom.geotemporal.time.MonthDays.next;
import static org.sellcom.geotemporal.time.MonthDays.previous;

import java.time.LocalDate;
import java.time.MonthDay;

import org.junit.Test;

public class MonthDaysTest {

	@Test
	public void testIsInRange_notWrappingAroundTheEndOfTheYear() {
		TemporalPredicate predicate = (temporal ->
				isInRange(MonthDay.from(temporal), MonthDay.of(DECEMBER, 24), MonthDay.of(DECEMBER, 26)));

		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, DECEMBER, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 24), LocalDate.of(2014, DECEMBER, 26)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, DECEMBER, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 24), LocalDate.of(2015, DECEMBER, 26)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, DECEMBER, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 24), LocalDate.of(2016, DECEMBER, 26)));
	}

	@Test
	public void testIsInRange_wrappingAroundTheEndOfTheYear() {
		TemporalPredicate predicate = (temporal ->
				isInRange(MonthDay.from(temporal), MonthDay.of(DECEMBER, 31), MonthDay.of(JANUARY, 1)));

		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 31)),
				singletonSet(LocalDate.of(2014, JANUARY, 1)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, DECEMBER, 1), LocalDate.of(2014, DECEMBER, 31)),
				singletonSet(LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 31)),
				singletonSet(LocalDate.of(2015, JANUARY, 1)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, DECEMBER, 1), LocalDate.of(2015, DECEMBER, 31)),
				singletonSet(LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JANUARY, 31)),
				singletonSet(LocalDate.of(2016, JANUARY, 1)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, DECEMBER, 1), LocalDate.of(2016, DECEMBER, 31)),
				singletonSet(LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testNext_monthDay_notWrappingAroundTheEndOfTheMonth() {
		assertThat(next(MonthDay.of(FEBRUARY, 1)), is(equalTo(MonthDay.of(FEBRUARY, 2))));
		assertThat(next(MonthDay.of(FEBRUARY, 2)), is(equalTo(MonthDay.of(FEBRUARY, 3))));
		assertThat(next(MonthDay.of(FEBRUARY, 3)), is(equalTo(MonthDay.of(FEBRUARY, 4))));
		assertThat(next(MonthDay.of(FEBRUARY, 4)), is(equalTo(MonthDay.of(FEBRUARY, 5))));
		assertThat(next(MonthDay.of(FEBRUARY, 5)), is(equalTo(MonthDay.of(FEBRUARY, 6))));
		assertThat(next(MonthDay.of(FEBRUARY, 6)), is(equalTo(MonthDay.of(FEBRUARY, 7))));
		assertThat(next(MonthDay.of(FEBRUARY, 7)), is(equalTo(MonthDay.of(FEBRUARY, 8))));
		assertThat(next(MonthDay.of(FEBRUARY, 8)), is(equalTo(MonthDay.of(FEBRUARY, 9))));
		assertThat(next(MonthDay.of(FEBRUARY, 9)), is(equalTo(MonthDay.of(FEBRUARY, 10))));
		assertThat(next(MonthDay.of(FEBRUARY, 10)), is(equalTo(MonthDay.of(FEBRUARY, 11))));
		assertThat(next(MonthDay.of(FEBRUARY, 11)), is(equalTo(MonthDay.of(FEBRUARY, 12))));
		assertThat(next(MonthDay.of(FEBRUARY, 12)), is(equalTo(MonthDay.of(FEBRUARY, 13))));
		assertThat(next(MonthDay.of(FEBRUARY, 13)), is(equalTo(MonthDay.of(FEBRUARY, 14))));
		assertThat(next(MonthDay.of(FEBRUARY, 14)), is(equalTo(MonthDay.of(FEBRUARY, 15))));
		assertThat(next(MonthDay.of(FEBRUARY, 15)), is(equalTo(MonthDay.of(FEBRUARY, 16))));
		assertThat(next(MonthDay.of(FEBRUARY, 16)), is(equalTo(MonthDay.of(FEBRUARY, 17))));
		assertThat(next(MonthDay.of(FEBRUARY, 17)), is(equalTo(MonthDay.of(FEBRUARY, 18))));
		assertThat(next(MonthDay.of(FEBRUARY, 18)), is(equalTo(MonthDay.of(FEBRUARY, 19))));
		assertThat(next(MonthDay.of(FEBRUARY, 19)), is(equalTo(MonthDay.of(FEBRUARY, 20))));
		assertThat(next(MonthDay.of(FEBRUARY, 20)), is(equalTo(MonthDay.of(FEBRUARY, 21))));
		assertThat(next(MonthDay.of(FEBRUARY, 21)), is(equalTo(MonthDay.of(FEBRUARY, 22))));
		assertThat(next(MonthDay.of(FEBRUARY, 22)), is(equalTo(MonthDay.of(FEBRUARY, 23))));
		assertThat(next(MonthDay.of(FEBRUARY, 23)), is(equalTo(MonthDay.of(FEBRUARY, 24))));
		assertThat(next(MonthDay.of(FEBRUARY, 24)), is(equalTo(MonthDay.of(FEBRUARY, 25))));
		assertThat(next(MonthDay.of(FEBRUARY, 25)), is(equalTo(MonthDay.of(FEBRUARY, 26))));
		assertThat(next(MonthDay.of(FEBRUARY, 26)), is(equalTo(MonthDay.of(FEBRUARY, 27))));
		assertThat(next(MonthDay.of(FEBRUARY, 27)), is(equalTo(MonthDay.of(FEBRUARY, 28))));
		assertThat(next(MonthDay.of(FEBRUARY, 28)), is(equalTo(MonthDay.of(FEBRUARY, 29))));
	}

	@Test
	public void testNext_monthDay_wrappingAroundTheEndOfTheMonth() {
		assertThat(next(MonthDay.of(JANUARY, 31)), is(equalTo(MonthDay.of(FEBRUARY, 1))));
		assertThat(next(MonthDay.of(FEBRUARY, 29)), is(equalTo(MonthDay.of(MARCH, 1))));
		assertThat(next(MonthDay.of(MARCH, 31)), is(equalTo(MonthDay.of(APRIL, 1))));
		assertThat(next(MonthDay.of(APRIL, 30)), is(equalTo(MonthDay.of(MAY, 1))));
		assertThat(next(MonthDay.of(MAY, 31)), is(equalTo(MonthDay.of(JUNE, 1))));
		assertThat(next(MonthDay.of(JUNE, 30)), is(equalTo(MonthDay.of(JULY, 1))));
		assertThat(next(MonthDay.of(JULY, 31)), is(equalTo(MonthDay.of(AUGUST, 1))));
		assertThat(next(MonthDay.of(AUGUST, 31)), is(equalTo(MonthDay.of(SEPTEMBER, 1))));
		assertThat(next(MonthDay.of(SEPTEMBER, 30)), is(equalTo(MonthDay.of(OCTOBER, 1))));
		assertThat(next(MonthDay.of(OCTOBER, 31)), is(equalTo(MonthDay.of(NOVEMBER, 1))));
		assertThat(next(MonthDay.of(NOVEMBER, 30)), is(equalTo(MonthDay.of(DECEMBER, 1))));
		assertThat(next(MonthDay.of(DECEMBER, 31)), is(equalTo(MonthDay.of(JANUARY, 1))));
	}

	@Test
	public void testPrevious_monthDay_notWrappingAroundTheEndOfTheMonth() {
		assertThat(previous(MonthDay.of(FEBRUARY, 29)), is(equalTo(MonthDay.of(FEBRUARY, 28))));
		assertThat(previous(MonthDay.of(FEBRUARY, 28)), is(equalTo(MonthDay.of(FEBRUARY, 27))));
		assertThat(previous(MonthDay.of(FEBRUARY, 27)), is(equalTo(MonthDay.of(FEBRUARY, 26))));
		assertThat(previous(MonthDay.of(FEBRUARY, 26)), is(equalTo(MonthDay.of(FEBRUARY, 25))));
		assertThat(previous(MonthDay.of(FEBRUARY, 25)), is(equalTo(MonthDay.of(FEBRUARY, 24))));
		assertThat(previous(MonthDay.of(FEBRUARY, 24)), is(equalTo(MonthDay.of(FEBRUARY, 23))));
		assertThat(previous(MonthDay.of(FEBRUARY, 23)), is(equalTo(MonthDay.of(FEBRUARY, 22))));
		assertThat(previous(MonthDay.of(FEBRUARY, 22)), is(equalTo(MonthDay.of(FEBRUARY, 21))));
		assertThat(previous(MonthDay.of(FEBRUARY, 21)), is(equalTo(MonthDay.of(FEBRUARY, 20))));
		assertThat(previous(MonthDay.of(FEBRUARY, 20)), is(equalTo(MonthDay.of(FEBRUARY, 19))));
		assertThat(previous(MonthDay.of(FEBRUARY, 19)), is(equalTo(MonthDay.of(FEBRUARY, 18))));
		assertThat(previous(MonthDay.of(FEBRUARY, 18)), is(equalTo(MonthDay.of(FEBRUARY, 17))));
		assertThat(previous(MonthDay.of(FEBRUARY, 17)), is(equalTo(MonthDay.of(FEBRUARY, 16))));
		assertThat(previous(MonthDay.of(FEBRUARY, 16)), is(equalTo(MonthDay.of(FEBRUARY, 15))));
		assertThat(previous(MonthDay.of(FEBRUARY, 15)), is(equalTo(MonthDay.of(FEBRUARY, 14))));
		assertThat(previous(MonthDay.of(FEBRUARY, 14)), is(equalTo(MonthDay.of(FEBRUARY, 13))));
		assertThat(previous(MonthDay.of(FEBRUARY, 13)), is(equalTo(MonthDay.of(FEBRUARY, 12))));
		assertThat(previous(MonthDay.of(FEBRUARY, 12)), is(equalTo(MonthDay.of(FEBRUARY, 11))));
		assertThat(previous(MonthDay.of(FEBRUARY, 11)), is(equalTo(MonthDay.of(FEBRUARY, 10))));
		assertThat(previous(MonthDay.of(FEBRUARY, 10)), is(equalTo(MonthDay.of(FEBRUARY, 9))));
		assertThat(previous(MonthDay.of(FEBRUARY, 9)), is(equalTo(MonthDay.of(FEBRUARY, 8))));
		assertThat(previous(MonthDay.of(FEBRUARY, 8)), is(equalTo(MonthDay.of(FEBRUARY, 7))));
		assertThat(previous(MonthDay.of(FEBRUARY, 7)), is(equalTo(MonthDay.of(FEBRUARY, 6))));
		assertThat(previous(MonthDay.of(FEBRUARY, 6)), is(equalTo(MonthDay.of(FEBRUARY, 5))));
		assertThat(previous(MonthDay.of(FEBRUARY, 5)), is(equalTo(MonthDay.of(FEBRUARY, 4))));
		assertThat(previous(MonthDay.of(FEBRUARY, 4)), is(equalTo(MonthDay.of(FEBRUARY, 3))));
		assertThat(previous(MonthDay.of(FEBRUARY, 3)), is(equalTo(MonthDay.of(FEBRUARY, 2))));
		assertThat(previous(MonthDay.of(FEBRUARY, 2)), is(equalTo(MonthDay.of(FEBRUARY, 1))));
	}

	@Test
	public void testPrevious_monthDay_wrappingAroundTheEndOfTheMonth() {
		assertThat(previous(MonthDay.of(JANUARY, 1)), is(equalTo(MonthDay.of(DECEMBER, 31))));
		assertThat(previous(MonthDay.of(FEBRUARY, 1)), is(equalTo(MonthDay.of(JANUARY, 31))));
		assertThat(previous(MonthDay.of(MARCH, 1)), is(equalTo(MonthDay.of(FEBRUARY, 29))));
		assertThat(previous(MonthDay.of(APRIL, 1)), is(equalTo(MonthDay.of(MARCH, 31))));
		assertThat(previous(MonthDay.of(MAY, 1)), is(equalTo(MonthDay.of(APRIL, 30))));
		assertThat(previous(MonthDay.of(JUNE, 1)), is(equalTo(MonthDay.of(MAY, 31))));
		assertThat(previous(MonthDay.of(JULY, 1)), is(equalTo(MonthDay.of(JUNE, 30))));
		assertThat(previous(MonthDay.of(AUGUST, 1)), is(equalTo(MonthDay.of(JULY, 31))));
		assertThat(previous(MonthDay.of(SEPTEMBER, 1)), is(equalTo(MonthDay.of(AUGUST, 31))));
		assertThat(previous(MonthDay.of(OCTOBER, 1)), is(equalTo(MonthDay.of(SEPTEMBER, 30))));
		assertThat(previous(MonthDay.of(NOVEMBER, 1)), is(equalTo(MonthDay.of(OCTOBER, 31))));
		assertThat(previous(MonthDay.of(DECEMBER, 1)), is(equalTo(MonthDay.of(NOVEMBER, 30))));
	}

}
