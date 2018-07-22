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
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeNavigableSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.internal.time.TemporalPredicateUtils.testTemporalPredicate;
import static org.sellcom.geotemporal.time.Months.isInRange;
import static org.sellcom.geotemporal.time.Months.next;
import static org.sellcom.geotemporal.time.Months.previous;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class MonthsTest {

	@Test
	public void testIsInRange_notWrappingAroundTheEndOfTheYear() {
		TemporalPredicate predicate = (temporal ->
				isInRange(Month.from(temporal), JUNE, JULY));

		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, MAY, 1), LocalDate.of(2014, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, JUNE, 1), LocalDate.of(2014, JULY, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, MAY, 1), LocalDate.of(2015, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, JUNE, 1), LocalDate.of(2015, JULY, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, MAY, 1), LocalDate.of(2016, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, JUNE, 1), LocalDate.of(2016, JULY, 31)));
	}

	@Test
	public void testIsInRange_wrappingAroundTheEndOfTheYear() {
		TemporalPredicate predicate = (temporal ->
			isInRange(Month.from(temporal), AUGUST, MAY));

		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, MAY, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2014, JULY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, AUGUST, 1), LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, MAY, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2015, JULY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 1), LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, MAY, 31)));
		testTemporalPredicate(predicate,
				dateRangeStream(LocalDate.of(2016, JULY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 1), LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testNext_month() {
		assertThat(next(JANUARY), is(equalTo(FEBRUARY)));
		assertThat(next(FEBRUARY), is(equalTo(MARCH)));
		assertThat(next(MARCH), is(equalTo(APRIL)));
		assertThat(next(APRIL), is(equalTo(MAY)));
		assertThat(next(MAY), is(equalTo(JUNE)));
		assertThat(next(JUNE), is(equalTo(JULY)));
		assertThat(next(JULY), is(equalTo(AUGUST)));
		assertThat(next(AUGUST), is(equalTo(SEPTEMBER)));
		assertThat(next(SEPTEMBER), is(equalTo(OCTOBER)));
		assertThat(next(OCTOBER), is(equalTo(NOVEMBER)));
		assertThat(next(NOVEMBER), is(equalTo(DECEMBER)));
		assertThat(next(DECEMBER), is(equalTo(JANUARY)));
	}

	@Test
	public void testPrevious_month() {
		assertThat(previous(JANUARY), is(equalTo(DECEMBER)));
		assertThat(previous(FEBRUARY), is(equalTo(JANUARY)));
		assertThat(previous(MARCH), is(equalTo(FEBRUARY)));
		assertThat(previous(APRIL), is(equalTo(MARCH)));
		assertThat(previous(MAY), is(equalTo(APRIL)));
		assertThat(previous(JUNE), is(equalTo(MAY)));
		assertThat(previous(JULY), is(equalTo(JUNE)));
		assertThat(previous(AUGUST), is(equalTo(JULY)));
		assertThat(previous(SEPTEMBER), is(equalTo(AUGUST)));
		assertThat(previous(OCTOBER), is(equalTo(SEPTEMBER)));
		assertThat(previous(NOVEMBER), is(equalTo(OCTOBER)));
		assertThat(previous(DECEMBER), is(equalTo(NOVEMBER)));
	}

}
