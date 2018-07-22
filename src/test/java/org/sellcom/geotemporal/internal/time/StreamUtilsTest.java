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

import static java.time.Month.AUGUST;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeParallelStream;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.internal.time.StreamUtils.timeRangeParallelStream;
import static org.sellcom.geotemporal.internal.time.StreamUtils.timeRangeStream;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

import org.junit.Test;

public class StreamUtilsTest {

	@Test
	public void testDateRangeParallelStream() {
		LocalDate min = dateRangeParallelStream(LocalDate.of(2016, AUGUST, 5), LocalDate.of(2016, AUGUST, 21))
				.min(Comparator.naturalOrder())
				.orElse(null);
		assertThat(min, is(equalTo(LocalDate.of(2016, AUGUST, 5))));

		LocalDate max = dateRangeParallelStream(LocalDate.of(2016, AUGUST, 5), LocalDate.of(2016, AUGUST, 21))
				.max(Comparator.naturalOrder())
				.orElse(null);
		assertThat(max, is(equalTo(LocalDate.of(2016, AUGUST, 21))));
	}

	@Test
	public void testDateRangeStream() {
		LocalDate min = dateRangeStream(LocalDate.of(2016, AUGUST, 5), LocalDate.of(2016, AUGUST, 21))
				.min(Comparator.naturalOrder())
				.orElse(null);
		assertThat(min, is(equalTo(LocalDate.of(2016, AUGUST, 5))));

		LocalDate max = dateRangeStream(LocalDate.of(2016, AUGUST, 5), LocalDate.of(2016, AUGUST, 21))
				.max(Comparator.naturalOrder())
				.orElse(null);
		assertThat(max, is(equalTo(LocalDate.of(2016, AUGUST, 21))));
	}

	@Test
	public void testTimeRangeParallelStream() {
		LocalTime min = timeRangeParallelStream(LocalTime.of(6, 0), LocalTime.of(18, 0))
				.min(Comparator.naturalOrder())
				.orElse(null);
		assertThat(min, is(equalTo(LocalTime.of(6, 0))));

		LocalTime max = timeRangeParallelStream(LocalTime.of(6, 0), LocalTime.of(18, 0))
				.max(Comparator.naturalOrder())
				.orElse(null);
		assertThat(max, is(equalTo(LocalTime.of(18, 0))));
	}

	@Test
	public void testTimeRangeStream() {
		LocalTime min = timeRangeStream(LocalTime.of(6, 0), LocalTime.of(18, 0))
				.min(Comparator.naturalOrder())
				.orElse(null);
		assertThat(min, is(equalTo(LocalTime.of(6, 0))));

		LocalTime max = timeRangeStream(LocalTime.of(6, 0), LocalTime.of(18, 0))
				.max(Comparator.naturalOrder())
				.orElse(null);
		assertThat(max, is(equalTo(LocalTime.of(18, 0))));
	}

}
