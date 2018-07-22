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

import java.time.Month;
import java.time.MonthDay;

import org.sellcom.geotemporal.internal.Contract;

/**
 * Operations with {@link MonthDay}s.
 *
 * @since 1.0
 */
public class MonthDays {

	private MonthDays() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given month-day is within the range of the given month-days.
	 * Both the start month-day and the end month-day are inclusive and the range may wrap around the end of the year.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 * @throws IllegalArgumentException if {@code startMonthDay} is {@code null}
	 * @throws IllegalArgumentException if {@code endMonthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInRange(MonthDay monthDay, MonthDay startMonthDay, MonthDay endMonthDay) {
		Contract.checkArgument(monthDay != null, "Month-day must not be null");
		Contract.checkArgument(startMonthDay != null, "Start month-day must not be null");
		Contract.checkArgument(endMonthDay != null, "End month-day must not be null");

		if (startMonthDay.isAfter(endMonthDay)) { // Wraps around the end of the year
			return !monthDay.isBefore(startMonthDay) || !monthDay.isAfter(endMonthDay);
		} else {
			return !monthDay.isBefore(startMonthDay) && !monthDay.isAfter(endMonthDay);
		}
	}

	/**
	 * Returns the month-day following the given month-day.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static MonthDay next(MonthDay monthDay) {
		Contract.checkArgument(monthDay != null, "MonthDay must not be null");

		Month month = monthDay.getMonth();
		int dayOfMonth = monthDay.getDayOfMonth();

		if (dayOfMonth == month.maxLength()) {
			month = month.plus(1);

			return MonthDay.of(month, 1);
		} else {
			return MonthDay.of(month, dayOfMonth + 1);
		}
	}

	/**
	 * Returns the month-day preceding the given month-day.
	 * <p>
	 * Always returns {@code MonthDay.of(FEBRUARY, 29)} for {@code MonthDay.of(MARCH, 1)}.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static MonthDay previous(MonthDay monthDay) {
		Contract.checkArgument(monthDay != null, "MonthDay must not be null");

		Month month = monthDay.getMonth();
		int dayOfMonth = monthDay.getDayOfMonth();

		if (dayOfMonth == 1) {
			month = month.minus(1);

			return MonthDay.of(month, month.maxLength());
		} else {
			return MonthDay.of(month, dayOfMonth - 1);
		}
	}


	static boolean is(MonthDay monthDay, Month month, int dayOfMonth) {
		return (monthDay.getMonth() == month) && (monthDay.getDayOfMonth() == dayOfMonth);
	}

}
