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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

import org.sellcom.geotemporal.time.LocalDates;
import org.sellcom.geotemporal.time.MonthDays;

public class TemporalUtils {

	private TemporalUtils() {
		// Utility class, not to be instantiated
	}


	public static boolean is(Temporal temporal, DayOfWeek dayOfWeek) {
		return DayOfWeek.from(temporal) == dayOfWeek;
	}

	public static boolean is(Temporal temporal, int year, Month month, int dayOfMonth) {
		LocalDate date = LocalDate.from(temporal);
		LocalDate referenceDate = LocalDate.of(year, month, dayOfMonth);

		return date.equals(referenceDate);
	}

	public static boolean is(Temporal temporal, Month month, int dayOfMonth) {
		MonthDay monthDay = MonthDay.from(temporal);
		MonthDay referenceMonthDay = MonthDay.of(month, dayOfMonth);

		return monthDay.equals(referenceMonthDay);
	}

	public static boolean is(Temporal temporal, TemporalAdjuster adjuster) {
		return temporal.equals(temporal.with(adjuster));
	}

	public static boolean is(Temporal temporal, TemporalAdjuster adjuster1, TemporalAdjuster adjuster2) {
		return temporal.equals(temporal.with(adjuster1).with(adjuster2));
	}

	public static boolean is(Temporal temporal, TemporalAdjuster adjuster1, TemporalAdjuster adjuster2, TemporalAdjuster adjuster3) {
		return temporal.equals(temporal.with(adjuster1).with(adjuster2).with(adjuster3));
	}

	public static boolean isAnyOf(Temporal temporal, DayOfWeek dayOfWeek1, DayOfWeek dayOfWeek2) {
		DayOfWeek dayOfWeek = DayOfWeek.from(temporal);

		return (dayOfWeek == dayOfWeek1) || (dayOfWeek == dayOfWeek2);
	}

	public static boolean isInRange(Temporal temporal, int startYear, Month startMonth, int startDayOfMonth, int endYear, Month endMonth, int endDayOfMonth) {
		LocalDate date = LocalDate.from(temporal);
		LocalDate startDate = LocalDate.of(startYear, startMonth, startDayOfMonth);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDayOfMonth);

		return LocalDates.isInRange(date, startDate, endDate);
	}

	public static boolean isInRange(Temporal temporal, Month startMonth, int startDayOfMonth, Month endMonth, int endDayOfMonth) {
		MonthDay monthDay = MonthDay.from(temporal);
		MonthDay startMonthDay = MonthDay.of(startMonth, startDayOfMonth);
		MonthDay endMonthDay = MonthDay.of(endMonth, endDayOfMonth);

		return MonthDays.isInRange(monthDay, startMonthDay, endMonthDay);
	}

}
