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

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.time.LocalTimes;

public class StreamUtils {

	private StreamUtils() {
		// Utility class, not to be instantiated
	}


	public static NavigableSet<LocalDate> dateRangeNavigableSet(LocalDate startDate, LocalDate endDate) {
		return _dateRangeStream(startDate, endDate, false)
				.collect(Collectors.toCollection(TreeSet::new));
	}

	public static Stream<LocalDate> dateRangeParallelStream(LocalDate startDate, LocalDate endDate) {
		return _dateRangeStream(startDate, endDate, true);
	}

	public static Stream<LocalDate> dateRangeStream(LocalDate startDate, LocalDate endDate) {
		return _dateRangeStream(startDate, endDate, false);
	}

	public static NavigableSet<LocalTime> timeRangeNavigableSet(LocalTime startTime, LocalTime endTime) {
		return _timeRangeStream(startTime, endTime, false)
				.collect(Collectors.toCollection(TreeSet::new));
	}

	public static Stream<LocalTime> timeRangeParallelStream(LocalTime startTime, LocalTime endTime) {
		return _timeRangeStream(startTime, endTime, true);
	}

	public static Stream<LocalTime> timeRangeStream(LocalTime startTime, LocalTime endTime) {
		return _timeRangeStream(startTime, endTime, false);
	}


	private static Stream<LocalDate> _dateRangeStream(LocalDate startDate, LocalDate endDate, boolean parallel) {
		Contract.checkArgument(startDate != null, "Start date must not be null");
		Contract.checkArgument(endDate != null, "End date must not be null");

		if (endDate.isBefore(startDate)) {
			return _dateRangeStream(endDate, startDate, parallel);
		}

		Iterable<LocalDate> dateRangeIterable = (() -> new DateRangeIterator(startDate, endDate));

		return StreamSupport.stream(dateRangeIterable.spliterator(), parallel);
	}

	private static Stream<LocalTime> _timeRangeStream(LocalTime startTime, LocalTime endTime, boolean parallel) {
		Contract.checkArgument(startTime != null, "Start time must not be null");
		Contract.checkArgument(endTime != null, "End time must not be null");

		if (endTime.isBefore(startTime)) {
			return _timeRangeStream(endTime, startTime, parallel);
		}

		Iterable<LocalTime> timeRangeIterable = (() -> new TimeRangeIterator(startTime, endTime));

		return StreamSupport.stream(timeRangeIterable.spliterator(), parallel);
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class DateRangeIterator implements Iterator<LocalDate> {

		private LocalDate currentDate;

		private final LocalDate endDate;

		private final LocalDate startDate;


		public DateRangeIterator(LocalDate startDate, LocalDate endDate) {
			this.startDate = (currentDate = startDate);
			this.endDate = endDate;
		}


		@Override
		public boolean hasNext() {
			if (currentDate.isAfter(endDate)) {
				return false;
			}

			return true;
		}

		@Override
		public LocalDate next() {
			try {
				if (currentDate.isAfter(endDate)) {
					throw new NoSuchElementException();
				}

				return currentDate;
			} finally {
				currentDate = currentDate.plus(1, DAYS);
			}
		}

		@Override
		public String toString() {
			return String.format("%s[%s--%s: %s]",
					getClass().getSimpleName(),
					startDate,
					endDate,
					currentDate);
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class TimeRangeIterator implements Iterator<LocalTime> {

		private LocalTime currentTime;

		private final LocalTime endTime;

		private final LocalTime startTime;


		public TimeRangeIterator(LocalTime startDate, LocalTime endDate) {
			startTime = (currentTime = startDate);
			endTime = endDate;
		}


		@Override
		public boolean hasNext() {
			if (LocalTimes.isInRange(currentTime, LocalTime.of(23, 59), LocalTime.MAX)) { // Prevent wrapping over the end of the day
				return false;
			}
			if (currentTime.isAfter(endTime)) {
				return false;
			}

			return true;
		}

		@Override
		public LocalTime next() {
			try {
				if (LocalTimes.isInRange(currentTime, LocalTime.of(23, 59), LocalTime.MAX)) { // Prevent wrapping over the end of the day
					throw new NoSuchElementException();
				}
				if (currentTime.isAfter(endTime)) {
					throw new NoSuchElementException();
				}

				return currentTime;
			} finally {
				currentTime = currentTime.plus(1, MINUTES);
			}
		}

		@Override
		public String toString() {
			return String.format("%s[%s--%s: %s]",
					getClass().getSimpleName(),
					startTime,
					endTime,
					currentTime);
		}

	}

}
