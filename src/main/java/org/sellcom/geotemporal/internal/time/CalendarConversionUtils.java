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

import static java.time.temporal.JulianFields.JULIAN_DAY;

import java.time.LocalDate;

public class CalendarConversionUtils {

	private CalendarConversionUtils() {
		// Utility class, not to be instantiated
	}


	public static JulianLocalDate gregorianToJulian(LocalDate date) {
		return julianDayToJulianDate(Math.toIntExact(date.getLong(JULIAN_DAY)));
	}

	public static LocalDate julianToGregorian(JulianLocalDate date) {
		return LocalDate.ofEpochDay(0).with(JULIAN_DAY, julianDateToJulianDay(date));
	}


	private static int julianDateToJulianDay(JulianLocalDate date) {
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();

		int a = (14 - month) / 12;
		int b = (month + (12 * a)) - 3;
		int c = (year + 4800) - a;

		return (day + (((153 * b) + 2) / 5) + (365 * c) + (c / 4)) - 32083;
	}

	private static JulianLocalDate julianDayToJulianDate(int julianDay) {
		int a = julianDay + 32082;
		int b = ((4 * a) + 3) / 1461;
		int c = a - ((1461 * b) / 4);
		int d = ((5 * c) + 2) / 153;
		int e = d / 10;

		int day = (c - (((153 * d) + 2) / 5)) + 1;
		int month = (d + 3) - (12 * e);
		int year = (b - 4800) + e;

		return JulianLocalDate.of(year, month, day);
	}

}
