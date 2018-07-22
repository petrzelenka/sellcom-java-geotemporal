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

import java.time.LocalDate;

import org.sellcom.geotemporal.internal.Contract;

/**
 * Operations with {@link LocalDate}s.
 *
 * @since 1.0
 */
public class LocalDates {

	private LocalDates() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given date is within the range of the given dates.
	 * Both the start date and the end date are inclusive.
	 *
	 * @throws IllegalArgumentException if {@code date} is {@code null}
	 * @throws IllegalArgumentException if {@code startDate} is {@code null}
	 * @throws IllegalArgumentException if {@code endDate} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
		Contract.checkArgument(date != null, "Date must not be null");
		Contract.checkArgument(startDate != null, "Start date must not be null");
		Contract.checkArgument(endDate != null, "End date must not be null");

		if (startDate.isAfter(endDate)) { // Swapped bounds
			return !date.isBefore(endDate) && !date.isAfter(startDate);
		} else {
			return !date.isBefore(startDate) && !date.isAfter(endDate);
		}
	}

}
