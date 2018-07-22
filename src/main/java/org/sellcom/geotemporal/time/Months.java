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

import org.sellcom.geotemporal.internal.Contract;

/**
 * Operations with {@link Month}s.
 *
 * @since 1.0
 */
public class Months {

	private Months() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given former month follows the given latter month.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isAfter(Month former, Month latter) {
		Contract.checkArgument(former != null, "Former month must not be null");
		Contract.checkArgument(former != null, "Latter month must not be null");

		return former.compareTo(latter) > 0;
	}

	/**
	 * Checks whether the given former month precedes the given latter month.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isBefore(Month former, Month latter) {
		Contract.checkArgument(former != null, "Former month must not be null");
		Contract.checkArgument(former != null, "Latter month must not be null");

		return former.compareTo(latter) < 0;
	}

	/**
	 * Checks whether the given month is within the range of the given months.
	 * Both the start month and the end month are inclusive and the range may wrap around the end of the year.
	 *
	 * @throws IllegalArgumentException if {@code month} is {@code null}
	 * @throws IllegalArgumentException if {@code startMonth} is {@code null}
	 * @throws IllegalArgumentException if {@code endMonth} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInRange(Month month, Month startMonth, Month endMonth) {
		Contract.checkArgument(month != null, "Date month not be null");
		Contract.checkArgument(startMonth != null, "Start month must not be null");
		Contract.checkArgument(endMonth != null, "End month must not be null");

		if (isAfter(startMonth, endMonth)) { // Wraps around the end of the year
			return !isBefore(month, startMonth) || !isAfter(month, endMonth);
		} else {
			return !isBefore(month, startMonth) && !isAfter(month, endMonth);
		}
	}

	/**
	 * Returns the month following the given month.
	 *
	 * @throws IllegalArgumentException if {@code month} is {@code null}
	 *
	 * @since 1.0
	 */
	public static Month next(Month month) {
		Contract.checkArgument(month != null, "Month must not be null");

		int value = month.getValue();
		value = (value == 12) ? 1 : value + 1;

		return Month.of(value);
	}

	/**
	 * Returns the month preceding the given month.
	 *
	 * @throws IllegalArgumentException if {@code month} is {@code null}
	 *
	 * @since 1.0
	 */
	public static Month previous(Month month) {
		Contract.checkArgument(month != null, "Month must not be null");

		int value = month.getValue();
		value = (value == 1) ? 12 : value - 1;

		return Month.of(value);
	}

}
