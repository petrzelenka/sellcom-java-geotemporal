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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.internal.time.StreamUtils.timeRangeNavigableSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.timeRangeStream;
import static org.sellcom.geotemporal.internal.time.TemporalPredicateUtils.testTemporalPredicate;
import static org.sellcom.geotemporal.time.LocalTimes.isInRange;
import static org.sellcom.geotemporal.time.LocalTimes.parse;

import java.time.LocalTime;

import org.junit.Test;

public class LocalTimesTest {

	@Test
	public void testIsInRange_notWrappingTheEndOfTheDay() {
		TemporalPredicate predicate = (temporal ->
				isInRange(LocalTime.from(temporal), LocalTime.of(7, 30), LocalTime.of(8, 15)));

		testTemporalPredicate(predicate,
				timeRangeStream(LocalTime.of(7, 0), LocalTime.of(9, 0)),
				timeRangeNavigableSet(LocalTime.of(7, 30), LocalTime.of(8, 15)));
	}

	@Test
	public void testIsInRange_wrappingTheEndOfTheDay() {
		TemporalPredicate predicate = (temporal ->
				isInRange(LocalTime.from(temporal), LocalTime.of(23, 45), LocalTime.of(0, 15)));

		testTemporalPredicate(predicate,
				timeRangeStream(LocalTime.of(0, 0), LocalTime.of(1, 0)),
				timeRangeNavigableSet(LocalTime.of(0, 0), LocalTime.of(0, 15)));
		testTemporalPredicate(predicate,
				timeRangeStream(LocalTime.of(23, 0), LocalTime.of(23, 59)),
				timeRangeNavigableSet(LocalTime.of(23, 45), LocalTime.of(23, 59)));
	}

	@Test
	public void testParse() {
		assertThat(parse("24:00"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.0"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.00"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.000"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.0000"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.00000"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.000000"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.0000000"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.00000000"), is(equalTo(LocalTime.MAX)));
		assertThat(parse("24:00:00.000000000"), is(equalTo(LocalTime.MAX)));
	}

}
