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

import java.time.DayOfWeek;

import org.sellcom.geotemporal.internal.Contract;

/**
 * Operations with days-of-week.
 *
 * @since 1.0
 */
public class DaysOfWeek {

	private DaysOfWeek() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given former day-of-week follows the given latter day-of-week.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isAfter(DayOfWeek former, DayOfWeek latter) {
		Contract.checkArgument(former != null, "Former day of week must not be null");
		Contract.checkArgument(former != null, "Latter day of week must not be null");

		return former.compareTo(latter) > 0;
	}

	/**
	 * Checks whether the given former day-of-week precedes the given latter day-of-week.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isBefore(DayOfWeek former, DayOfWeek latter) {
		Contract.checkArgument(former != null, "Former day of week must not be null");
		Contract.checkArgument(former != null, "Latter day of week must not be null");

		return former.compareTo(latter) < 0;
	}

	/**
	 * Checks whether the given day-of-week is within the range of the given days-of-week.
	 * Both the start day-of-week and the end day-of-week are inclusive and the range may wrap around the end of the week.
	 *
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 * @throws IllegalArgumentException if {@code startDayOfWeek} is {@code null}
	 * @throws IllegalArgumentException if {@code endDayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInRange(DayOfWeek dayOfWeek, DayOfWeek startDayOfWeek, DayOfWeek endDayOfWeek) {
		Contract.checkArgument(dayOfWeek != null, "Day-of-week month not be null");
		Contract.checkArgument(startDayOfWeek != null, "Start day-of-week must not be null");
		Contract.checkArgument(endDayOfWeek != null, "End day-of-week must not be null");

		if (isAfter(startDayOfWeek, endDayOfWeek)) { // Wraps around the end of the week
			return !isBefore(dayOfWeek, startDayOfWeek) || !isAfter(dayOfWeek, endDayOfWeek);
		} else {
			return !isBefore(dayOfWeek, startDayOfWeek) && !isAfter(dayOfWeek, endDayOfWeek);
		}
	}

	/**
	 * Returns the day-of-week following the given day-of-week.
	 *
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static DayOfWeek next(DayOfWeek dayOfWeek) {
		Contract.checkArgument(dayOfWeek != null, "Day of week must not be null");

		int value = dayOfWeek.getValue();
		value = (value == 7) ? 1 : value + 1;

		return DayOfWeek.of(value);
	}

	/**
	 * Returns the day-of-week preceding the given day-of-week.
	 *
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static DayOfWeek previous(DayOfWeek dayOfWeek) {
		Contract.checkArgument(dayOfWeek != null, "Day of week must not be null");

		int value = dayOfWeek.getValue();
		value = (value == 1) ? 7 : value - 1;

		return DayOfWeek.of(value);
	}

}
