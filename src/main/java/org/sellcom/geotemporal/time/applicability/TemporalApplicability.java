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
package org.sellcom.geotemporal.time.applicability;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.temporal.Temporal;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.internal.time.applicability.AllTemporalsApplicability;
import org.sellcom.geotemporal.internal.time.applicability.DayOfWeekAfterMonthDayApplicability;
import org.sellcom.geotemporal.internal.time.applicability.DayOfWeekBeforeMonthDayApplicability;
import org.sellcom.geotemporal.internal.time.applicability.EvenIsoWeekNumberApplicability;
import org.sellcom.geotemporal.internal.time.applicability.InverseTemporalApplicability;
import org.sellcom.geotemporal.internal.time.applicability.OddIsoWeekNumberApplicability;
import org.sellcom.geotemporal.internal.time.applicability.RangeOfDaysOfWeekApplicability;
import org.sellcom.geotemporal.internal.time.applicability.RangeOfIsoWeekNumbersApplicability;
import org.sellcom.geotemporal.internal.time.applicability.RangeOfLocalDatesApplicability;
import org.sellcom.geotemporal.internal.time.applicability.RangeOfMonthDaysApplicability;
import org.sellcom.geotemporal.internal.time.applicability.RangeOfMonthsApplicability;
import org.sellcom.geotemporal.internal.time.applicability.RangeOfYearsApplicability;
import org.sellcom.geotemporal.internal.time.applicability.SingleDayOfWeekApplicability;
import org.sellcom.geotemporal.internal.time.applicability.SingleIsoWeekNumberApplicability;
import org.sellcom.geotemporal.internal.time.applicability.SingleLocalDateApplicability;
import org.sellcom.geotemporal.internal.time.applicability.SingleMonthApplicability;
import org.sellcom.geotemporal.internal.time.applicability.SingleMonthDayApplicability;
import org.sellcom.geotemporal.internal.time.applicability.SingleYearApplicability;
import org.sellcom.geotemporal.time.DaysOfWeek;
import org.sellcom.geotemporal.time.MonthDays;
import org.sellcom.geotemporal.time.Months;
import org.sellcom.geotemporal.time.TemporalPredicate;

/**
 * Applicability at a {@code Temporal}.
 *
 * @since 1.0
 *
 * @see Temporal
 */
public abstract class TemporalApplicability extends GeoTemporalApplicability implements TemporalPredicate {

	/**
	 * Creates an applicability that is always satisfied.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability always() {
		return new AllTemporalsApplicability();
	}

	/**
	 * Creates an applicability satisfied in the given range of dates.
	 * Both the start date and the end date are inclusive.
	 *
	 * @throws IllegalArgumentException if {@code startDate} is {@code null}
	 * @throws IllegalArgumentException if {@code endDate} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability dateRange(LocalDate startDate, LocalDate endDate) {
		Contract.checkArgument(startDate != null, "Start date must not be null");
		Contract.checkArgument(endDate != null, "End date must not be null");

		if (startDate.equals(endDate)) {
			return new SingleLocalDateApplicability(startDate);
		} else {
			if (endDate.isBefore(startDate)) {
				return new RangeOfLocalDatesApplicability(endDate, startDate);
			} else {
				return new RangeOfLocalDatesApplicability(startDate, endDate);
			}
		}
	}

	/**
	 * Creates an applicability satisfied on the ordinal-th day-of-week after the given month-day.
	 *
	 * @throws IllegalArgumentException if {@code ordinal} is {@code 0}
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability dayOfWeekAfter(int ordinal, DayOfWeek dayOfWeek, MonthDay monthDay) {
		Contract.checkArgument(ordinal != 0, "Ordinal must not be zero");

		if (ordinal < 0) {
			return new DayOfWeekBeforeMonthDayApplicability(-ordinal, dayOfWeek, monthDay);
		} else {
			return new DayOfWeekAfterMonthDayApplicability(ordinal, dayOfWeek, monthDay);
		}
	}

	/**
	 * Creates an applicability satisfied on the ordinal-th day-of-week before the given month-day.
	 *
	 * @throws IllegalArgumentException if {@code ordinal} is {@code 0}
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability dayOfWeekBefore(int ordinal, DayOfWeek dayOfWeek, MonthDay monthDay) {
		Contract.checkArgument(ordinal != 0, "Ordinal must not be zero");

		if (ordinal < 0) {
			return new DayOfWeekAfterMonthDayApplicability(-ordinal, dayOfWeek, monthDay);
		} else {
			return new DayOfWeekBeforeMonthDayApplicability(ordinal, dayOfWeek, monthDay);
		}
	}

	/**
	 * Creates an applicability satisfied in the given days-of-week.
	 * Both the start day-of-week and the end day-of-week are inclusive and the range may wrap around the end of the week.
	 *
	 * @throws IllegalArgumentException if {@code startDayOfWeek} is {@code null}
	 * @throws IllegalArgumentException if {@code endDayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability dayOfWeekRange(DayOfWeek startDayOfWeek, DayOfWeek endDayOfWeek) {
		Contract.checkArgument(startDayOfWeek != null, "Start day of week must not be null");
		Contract.checkArgument(endDayOfWeek != null, "End day of week must not be null");

		if (startDayOfWeek.equals(endDayOfWeek)) {
			return new SingleDayOfWeekApplicability(startDayOfWeek);
		}

		if (DaysOfWeek.next(endDayOfWeek).equals(startDayOfWeek)) { // Any day of week
			return new AllTemporalsApplicability();
		}

		if (DaysOfWeek.isAfter(startDayOfWeek, endDayOfWeek)) { // Wraps around the end of the week
			return new InverseTemporalApplicability(dayOfWeekRange(DaysOfWeek.next(endDayOfWeek), DaysOfWeek.previous(startDayOfWeek)));
		} else {
			return new RangeOfDaysOfWeekApplicability(startDayOfWeek, endDayOfWeek);
		}
	}

	/**
	 * Creates an applicability satisfied in weeks with an even ISO week number.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability evenIsoWeek() {
		return new EvenIsoWeekNumberApplicability();
	}

	/**
	 * Creates an applicability satisfied in weeks with the week number in the given range.
	 * Both the start week number and the end week number are inclusive.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability isoWeekRange(int startWeekNumber, int endWeekNumber) {
		if (startWeekNumber == endWeekNumber) {
			return new SingleIsoWeekNumberApplicability(startWeekNumber);
		} else {
			if (endWeekNumber < startWeekNumber) {
				return new InverseTemporalApplicability(new RangeOfIsoWeekNumbersApplicability(endWeekNumber + 1, startWeekNumber - 1));
			} else {
				return new RangeOfIsoWeekNumbersApplicability(startWeekNumber, endWeekNumber);
			}
		}
	}

	/**
	 * Creates an applicability satisfied in the given range of month-days.
	 * Both the start month-day and the end month-day are inclusive and the range may wrap around the end of the year.
	 *
	 * @throws IllegalArgumentException if {@code startMonthDay} is {@code null}
	 * @throws IllegalArgumentException if {@code endMonthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability monthDayRange(MonthDay startMonthDay, MonthDay endMonthDay) {
		Contract.checkArgument(startMonthDay != null, "Start month-day must not be null");
		Contract.checkArgument(endMonthDay != null, "End month-day must not be null");

		if (startMonthDay.equals(endMonthDay)) {
			return new SingleMonthDayApplicability(startMonthDay);
		}

		if (MonthDays.next(endMonthDay).equals(startMonthDay)) { // Any month-day
			return new AllTemporalsApplicability();
		}

		if (startMonthDay.isAfter(endMonthDay)) { // Wraps around the end of the year
			return new InverseTemporalApplicability(monthDayRange(MonthDays.next(endMonthDay), MonthDays.previous(startMonthDay)));
		} else {
			return new RangeOfMonthDaysApplicability(startMonthDay, endMonthDay);
		}
	}

	/**
	 * Creates an applicability satisfied in the given range of months.
	 * Both the start month and the end month are inclusive and the range may wrap around the end of the year.
	 *
	 * @throws IllegalArgumentException if {@code startMonth} is {@code null}
	 * @throws IllegalArgumentException if {@code endMonth} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability monthRange(Month startMonth, Month endMonth) {
		Contract.checkArgument(startMonth != null, "Start month must not be null");
		Contract.checkArgument(endMonth != null, "End month must not be null");

		if (startMonth.equals(endMonth)) {
			return new SingleMonthApplicability(startMonth);
		}

		if (Months.next(endMonth).equals(startMonth)) { // Any month
			return new AllTemporalsApplicability();
		}

		if (Months.isAfter(startMonth, endMonth)) { // Wraps around the end of the year
			return new InverseTemporalApplicability(monthRange(Months.next(endMonth), Months.previous(startMonth)));
		} else {
			return new RangeOfMonthsApplicability(startMonth, endMonth);
		}
	}

	@Override
	public TemporalApplicability negate() {
		return new InverseTemporalApplicability(this);
	}

	/**
	 * Creates an applicability that is never satisfied.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability never() {
		return new InverseTemporalApplicability(new AllTemporalsApplicability());
	}

	/**
	 * Creates an applicability satisfied in weeks with an odd ISO week number.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability oddIsoWeek() {
		return new OddIsoWeekNumberApplicability();
	}

	/**
	 * Creates an applicability satisfied on the given date.
	 *
	 * @throws IllegalArgumentException if {@code date} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleDate(LocalDate date) {
		return new SingleLocalDateApplicability(date);
	}

	/**
	 * Creates an applicability satisfied on the given day-of-week.
	 *
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleDayOfWeek(DayOfWeek dayOfWeek) {
		return new SingleDayOfWeekApplicability(dayOfWeek);
	}

	/**
	 * Creates an applicability satisfied in weeks with the given ISO week number.
	 *
	 * @throws IllegalArgumentException if {@code weekNumber} is not positive
	 * @throws IllegalArgumentException if {@code weekNumber} is greater than {@code 53}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleIsoWeek(int weekNumber) {
		return new SingleIsoWeekNumberApplicability(weekNumber);
	}

	/**
	 * Creates an applicability satisfied in the given month.
	 *
	 * @throws IllegalArgumentException if {@code month} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleMonth(Month month) {
		return new SingleMonthApplicability(month);
	}

	/**
	 * Creates an applicability satisfied on the given month-day.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleMonthDay(MonthDay monthDay) {
		return new SingleMonthDayApplicability(monthDay);
	}

	/**
	 * Creates an applicability satisfied in the given year.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleYear(int year) {
		return new SingleYearApplicability(year);
	}

	/**
	 * Creates an applicability satisfied in the given year.
	 *
	 * @throws IllegalArgumentException if {@code year} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability singleYear(Year year) {
		return singleYear(year.getValue());
	}

	/**
	 * Checks whether this applicability is satisfied at the given temporal.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 *
	 * @since 1.0
	 */
	@Override
	public abstract boolean test(Temporal temporal);

	@Override
	public final boolean test(Temporal temporal, GeoRegion region) {
		return test(temporal);
	}

	/**
	 * Creates an applicability satisfied in the given range of years.
	 * Both the start year and the end year are inclusive.
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability yearRange(int startYear, int endYear) {
		if (startYear == endYear) {
			return new SingleYearApplicability(startYear);
		} else {
			if (endYear < startYear) {
				return new RangeOfYearsApplicability(endYear, startYear);
			} else {
				return new RangeOfYearsApplicability(startYear, endYear);
			}
		}
	}

	/**
	 * Creates an applicability satisfied in the given range of years.
	 * Both the start year and the end year are inclusive.
	 *
	 * @throws IllegalArgumentException if {@code startYear} is {@code null}
	 * @throws IllegalArgumentException if {@code endYear} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalApplicability yearRange(Year startYear, Year endYear) {
		Contract.checkArgument(startYear != null, "Start year must not be null");
		Contract.checkArgument(endYear != null, "End year must not be null");

		return yearRange(startYear.getValue(), endYear.getValue());
	}

}
