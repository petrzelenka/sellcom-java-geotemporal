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

import java.time.Year;

import org.sellcom.geotemporal.internal.Contract;

/**
 * Operations with {@link Year}s.
 *
 * @since 1.0
 */
public class Years {

	private Years() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given year is within the range of the given years.
	 * Both the start year and the end year are inclusive.
	 *
	 * @throws IllegalArgumentException if {@code year} is {@code null}
	 * @throws IllegalArgumentException if {@code startDate} is {@code null}
	 * @throws IllegalArgumentException if {@code endDate} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInRange(Year year, Year startYear, Year endYear) {
		Contract.checkArgument(year != null, "Year must not be null");
		Contract.checkArgument(startYear != null, "Start year must not be null");
		Contract.checkArgument(endYear != null, "End year must not be null");

		if (startYear.isAfter(endYear)) { // Swapped bounds
			return !year.isBefore(endYear) && !year.isAfter(startYear);
		} else {
			return !year.isBefore(startYear) && !year.isAfter(endYear);
		}
	}

}
