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

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.internal.StringUtils;

/**
 * Operations with {@link LocalTime}s.
 *
 * @since 1.0
 */
public class LocalTimes {

	private static final Pattern END_OF_THE_DAY_PATTERN = Pattern.compile("24:00(:00)?(\\.0{1,9})?");


	private LocalTimes() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given time is within the range of the given times.
	 * Both the start time and the end time are inclusive and the range may wrap around the end of the day.
	 *
	 * @throws IllegalArgumentException if {@code time} is {@code null}
	 * @throws IllegalArgumentException if {@code startTime} is {@code null}
	 * @throws IllegalArgumentException if {@code endTime} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInRange(LocalTime time, LocalTime startTime, LocalTime endTime) {
		Contract.checkArgument(time != null, "Time not be null");
		Contract.checkArgument(startTime != null, "Start time must not be null");
		Contract.checkArgument(endTime != null, "End time must not be null");

		if (startTime.isAfter(endTime)) { // Wraps around the end of the day
			return !time.isBefore(startTime) || !time.isAfter(endTime);
		} else {
			return !time.isBefore(startTime) && !time.isAfter(endTime);
		}
	}

	/**
	 * Parses the given local time.
	 * Returns {@link LocalTime#MAX} if the time corresponds to {@code 24:00}.
	 *
	 * @throws IllegalArgumentException if {@code time} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static LocalTime parse(String time) {
		Contract.checkArgument(!StringUtils.isNullOrEmpty(time), "Time must not be null or empty");

		Matcher matcher = END_OF_THE_DAY_PATTERN.matcher(time);

		return matcher.matches() ? LocalTime.MAX : LocalTime.parse(time);
	}

	/**
	 * Returns the string representing the given time
	 * Returns {@code 24:00} if the time equals to {@link LocalTime#MAX}.
	 *
	 * @throws IllegalArgumentException if {@code time} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String toString(LocalTime time) {
		Contract.checkArgument(time != null, "Time not be null");

		return time.equals(LocalTime.MAX) ? "24:00" : time.toString();
	}

}
