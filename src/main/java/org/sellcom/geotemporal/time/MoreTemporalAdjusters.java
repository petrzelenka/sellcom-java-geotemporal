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

import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;
import static org.sellcom.geotemporal.internal.time.CalendarConversionUtils.julianToGregorian;
import static org.sellcom.geotemporal.time.MonthDays.is;
import static org.sellcom.geotemporal.time.Temporals.isWorkingDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.internal.time.JulianLocalDate;

/**
 * More common and useful {@link TemporalAdjuster}s.
 *
 * @since 1.0
 */
public class MoreTemporalAdjusters {

	private static final Map<Integer, MonthDay> easterSundays = new HashMap<>();

	private static final Map<Integer, MonthDay> orthodoxEasterSundays = new HashMap<>();


	private MoreTemporalAdjusters() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns an adjuster returning the (First) Advent Sunday in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster adventSunday() {
		return (temporal) -> {
			return temporal
					.with(MONTH_OF_YEAR, DECEMBER.getValue())
					.with(DAY_OF_MONTH, 25)
					.with(dayOfWeekPreceding(4, SUNDAY));
		};
	}

	/**
	 * Returns an adjuster returning the Ascension Thursday (also known as Holy Thursday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxAscensionThursday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster ascensionThursday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(39, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Ash Wednesday in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster ashWednesday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(46, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the "Buß- und Bettag" (Germany) in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster bussUndBettag() {
		return (temporal) -> {
			return temporal
					.with(adventSunday())
					.minus(11, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Clean Monday (also known as Ash Monday, Green Monday, or Pure Monday) in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster cleanMonday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.minus(48, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the day of the Corpus Christi feast (also known as Corpus Domini feast) in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster corpusChristi() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(60, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the given ordinal day-of-week following the adjusted date.
	 *
	 * @throws IllegalArgumentException if {@code ordinal} is zero
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster dayOfWeekFollowing(int ordinal, DayOfWeek dayOfWeek) {
		Contract.checkArgument(ordinal != 0, "Ordinal must not be zero");
		Contract.checkArgument(dayOfWeek != null, "Day of week must not be null");

		if (ordinal < 0) {
			return dayOfWeekPreceding(-ordinal, dayOfWeek);
		} else {
			return (temporal) -> {
				return temporal
						.with(TemporalAdjusters.next(dayOfWeek))
						.plus(ordinal - 1, WEEKS);
			};
		}
	}

	/**
	 * Returns an adjuster returning the given ordinal day-of-week preceding the adjusted date.
	 *
	 * @throws IllegalArgumentException if {@code ordinal} is zero
	 * @throws IllegalArgumentException if {@code dayOfWeek} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster dayOfWeekPreceding(int ordinal, DayOfWeek dayOfWeek) {
		Contract.checkArgument(ordinal != 0, "Ordinal must not be zero");
		Contract.checkArgument(dayOfWeek != null, "Day of week must not be null");

		if (ordinal < 0) {
			return dayOfWeekFollowing(-ordinal, dayOfWeek);
		} else {
			return (temporal) -> {
				return temporal
						.with(TemporalAdjusters.previous(dayOfWeek))
						.minus(ordinal - 1, WEEKS);
			};
		}
	}

	/**
	 * Returns an adjuster returning the Easter Monday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxEasterMonday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster easterMonday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(1, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Easter Sunday in the same year as the adjusted date.
	 * <p>
	 * This method uses the 21 March in Gregorian Calendar as the starting point in determining the date of the Easter Sunday.
	 * That corresponds to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxEasterSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster easterSunday() {
		return (temporal) -> {
			MonthDay easterSunday = easterSundays.computeIfAbsent(temporal.get(YEAR), MoreTemporalAdjusters::computeEasterSunday);

			return temporal.with(easterSunday);
		};
	}

	/**
	 * Returns an adjuster returning the Good Friday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxGoodFriday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster goodFriday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(2, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Holy Saturday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxHolySaturday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster holySaturday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(1, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Holy Wednesday (also known as Spy Wednesday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxHolyWednesday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster holyWednesday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(4, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Maundy Thursday (also known as Covenant Thursday, Holy Thursday, or Sheer Thursday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxMaundyThursday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster maundyThursday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(3, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the given month-day following the adjusted date.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster next(MonthDay monthDay) {
		Contract.checkArgument(monthDay != null, "MonthDay must not be null");

		return TemporalAdjusters.ofDateAdjuster((date) -> {
			if (is(monthDay, FEBRUARY, 29)) {
				date = date.with(nextOrSameLeapYear()); // Ensure leap year

				LocalDate candidate = date.with(monthDay);

				return candidate.isAfter(date) ? candidate : candidate.with(nextLeapYear());
			} else {
				LocalDate candidate = date.with(monthDay);

				return candidate.isAfter(date) ? candidate : candidate.plusYears(1);
			}
		});
	}

	/**
	 * Returns an adjuster returning the next day of the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster nextDay() {
		return TemporalAdjusters.ofDateAdjuster((date) -> {
			return date.plus(1, DAYS);
		});
	}

	/**
	 * Returns an adjuster returning the month and day of the adjusted date in the next leap year.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster nextLeapYear() {
		return (temporal) -> {
			int year = temporal.get(YEAR) + 1;

			year = (int) (Math.ceil(year / 4.0) * 4.0);
			if (!Year.isLeap(year)) {
				year += 4;
			}

			return temporal.with(YEAR, year);
		};
	}

	/**
	 * Returns an adjuster returning the given month-day following (or same as) the adjusted date.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster nextOrSame(MonthDay monthDay) {
		Contract.checkArgument(monthDay != null, "MonthDay must not be null");

		return TemporalAdjusters.ofDateAdjuster((date) -> {
			if (is(monthDay, FEBRUARY, 29)) {
				date = date.with(nextOrSameLeapYear()); // Ensure leap year

				LocalDate candidate = date.with(monthDay);

				return candidate.isBefore(date) ? candidate.with(nextLeapYear()) : candidate;
			} else {
				LocalDate candidate = date.with(monthDay);

				return candidate.isBefore(date) ? candidate.plusYears(1) : candidate;
			}
		});
	}

	/**
	 * Returns an adjuster returning the month and day of the adjusted date in the next (or same) leap year.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster nextOrSameLeapYear() {
		return (temporal) -> {
			int year = temporal.get(YEAR);
			if (Year.isLeap(year)) {
				return temporal;
			}

			year = (int) (Math.ceil(year / 4.0) * 4.0);
			if (!Year.isLeap(year)) {
				year += 4;
			}

			return temporal.with(YEAR, year);
		};
	}

	/**
	 * Returns an adjuster returning the next or same working day in the given region.
	 *
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 *
	 * @since 1.0
	 *
	 * @see Temporals#isWorkingDay(java.time.temporal.Temporal, GeoRegion)
	 */
	public static TemporalAdjuster nextOrSameWorkingDay(GeoRegion region) {
		Contract.checkArgument(region != null, "Region must not be null");

		return (temporal) -> {
			while (!isWorkingDay(temporal, region)) {
				temporal = temporal.plus(1, ChronoUnit.DAYS);
			}

			return temporal;
		};
	}

	/**
	 * Returns an adjuster returning the next working day in the given region.
	 *
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 *
	 * @since 1.0
	 *
	 * @see Temporals#isWorkingDay(java.time.temporal.Temporal, GeoRegion)
	 */
	public static TemporalAdjuster nextWorkingDay(GeoRegion region) {
		Contract.checkArgument(region != null, "Region must not be null");

		return (temporal) -> {
			do {
				temporal = temporal.plus(1, ChronoUnit.DAYS);
			} while (!isWorkingDay(temporal, region));

			return temporal;
		};
	}

	/**
	 * Returns an adjuster returning the Orthodox All Saints' Day in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxAllSaintsDay() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.plus(56, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Ascension Thursday (also known as Holy Thursday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #ascensionThursday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxAscensionThursday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.plus(39, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Easter Monday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #easterMonday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxEasterMonday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.plus(1, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Easter Sunday in the same year as the adjusted date.
	 * <p>
	 * This method uses the 21 March in Julian Calendar as the starting point in determining the date of the Easter Sunday.
	 * That corresponds to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #easterSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxEasterSunday() {
		return (temporal) -> {
			MonthDay orthodoxEasterSunday = orthodoxEasterSundays.computeIfAbsent(temporal.get(YEAR), MoreTemporalAdjusters::computeOrthodoxEasterSunday);

			return temporal.with(orthodoxEasterSunday);
		};
	}

	/**
	 * Returns an adjuster returning the Good Friday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #goodFriday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxGoodFriday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.minus(2, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Holy Saturday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #holySaturday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxHolySaturday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.minus(1, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Holy Wednesday (also known as Holy and Great Wednesday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #holyWednesday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxHolyWednesday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.minus(4, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Maundy Thursday (also known as Covenant Thursday, Holy Thursday, or Sheer Thursday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #maundyThursday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxMaundyThursday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.minus(3, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Palm Sunday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #palmSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxPalmSunday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.minus(7, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Whit(e) Monday (also known as Pentecost Monday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #whitMonday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxWhitMonday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.plus(50, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Whit(e) Sunday (also known as Pentecost Sunday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Eastern Christian churches.
	 * For most of the Western Christian churches, use {@link #whitSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster orthodoxWhitSunday() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.plus(49, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Palm Sunday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxPalmSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster palmSunday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(7, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the "Paştile Blajinilor" (Moldova) in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster pastileBlajinilor() {
		return (temporal) -> {
			return temporal
					.with(orthodoxEasterSunday())
					.plus(8, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the given month-day preceding the adjusted date.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster previous(MonthDay monthDay) {
		Contract.checkArgument(monthDay != null, "MonthDay must not be null");

		return TemporalAdjusters.ofDateAdjuster((date) -> {
			if (is(monthDay, FEBRUARY, 29)) {
				date = date.with(previousOrSameLeapYear()); // Ensure leap year

				LocalDate candidate = date.with(monthDay);

				return candidate.isBefore(date) ? candidate : candidate.with(previousLeapYear());
			} else {
				LocalDate candidate = date.with(monthDay);

				return candidate.isBefore(date) ? candidate : candidate.minusYears(1);
			}
		});
	}

	/**
	 * Returns an adjuster returning the previous day of the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster previousDay() {
		return TemporalAdjusters.ofDateAdjuster((date) -> {
			return date.minus(1, DAYS);
		});
	}

	/**
	 * Returns an adjuster returning the month and day of the adjusted date in the previous leap year.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster previousLeapYear() {
		return (temporal) -> {
			int year = temporal.get(YEAR) - 1;

			year = (int) (Math.floor(year / 4.0) * 4.0);
			if (!Year.isLeap(year)) {
				year -= 4;
			}

			return temporal.with(YEAR, year);
		};
	}

	/**
	 * Returns an adjuster returning the given month-day preceding (or same as) the adjusted date.
	 *
	 * @throws IllegalArgumentException if {@code monthDay} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster previousOrSame(MonthDay monthDay) {
		Contract.checkArgument(monthDay != null, "MonthDay must not be null");

		return TemporalAdjusters.ofDateAdjuster((date) -> {
			if (is(monthDay, FEBRUARY, 29)) {
				date = date.with(previousOrSameLeapYear()); // Ensure leap year

				LocalDate candidate = date.with(monthDay);

				return candidate.isAfter(date) ? candidate.with(previousLeapYear()) : candidate;
			} else {
				LocalDate candidate = date.with(monthDay);

				return candidate.isAfter(date) ? candidate.minusYears(1) : candidate;
			}
		});
	}

	/**
	 * Returns an adjuster returning the month and day of the adjusted date in the previous (or same) leap year.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster previousOrSameLeapYear() {
		return (temporal) -> {
			int year = temporal.get(YEAR);
			if (Year.isLeap(year)) {
				return temporal;
			}

			year = (int) (Math.floor(year / 4.0) * 4.0);
			if (!Year.isLeap(year)) {
				year -= 4;
			}

			return temporal.with(YEAR, year);
		};
	}

	/**
	 * Returns an adjuster returning the previous or same working day in the given region.
	 *
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 *
	 * @since 1.0
	 *
	 * @see Temporals#isWorkingDay(java.time.temporal.Temporal, GeoRegion)
	 */
	public static TemporalAdjuster previousOrSameWorkingDay(GeoRegion region) {
		Contract.checkArgument(region != null, "Region must not be null");

		return (temporal) -> {
			while (!isWorkingDay(temporal, region)) {
				temporal = temporal.minus(1, ChronoUnit.DAYS);
			}

			return temporal;
		};
	}

	/**
	 * Returns an adjuster returning the previous working day in the given region.
	 *
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 *
	 * @since 1.0
	 *
	 * @see Temporals#isWorkingDay(java.time.temporal.Temporal, GeoRegion)
	 */
	public static TemporalAdjuster previousWorkingDay(GeoRegion region) {
		Contract.checkArgument(region != null, "Region must not be null");

		return (temporal) -> {
			do {
				temporal = temporal.minus(1, ChronoUnit.DAYS);
			} while (!isWorkingDay(temporal, region));

			return temporal;
		};
	}

	/**
	 * Returns an adjuster returning the Shrove Monday (also known as Collop Monday, Hall Monday, Merry Monday, or Rose Monday) in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster shroveMonday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(48, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Shrove Tuesday in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster shroveTuesday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.minus(47, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the "store bededag" (Denmark) in the same year as the adjusted date.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster storeBededag() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(26, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Trinity Sunday in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxWhitSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster trinitySunday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(56, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Whit(e) Monday (also known as Pentecost Monday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxWhitMonday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster whitMonday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(50, DAYS);
		};
	}

	/**
	 * Returns an adjuster returning the Whit(e) Sunday (also known as Pentecost Sunday) in the same year as the adjusted date.
	 * <p>
	 * This method uses the rules corresponding to most of the Western Christian churches.
	 * For most of the Eastern Christian churches, use {@link #orthodoxWhitSunday()} instead.
	 *
	 * @since 1.0
	 */
	public static TemporalAdjuster whitSunday() {
		return (temporal) -> {
			return temporal
					.with(easterSunday())
					.plus(49, DAYS);
		};
	}


	private static MonthDay computeEasterSunday(int year) {
		/*
		 * This code implements the algorithm designed by Claus Tøndering (1998),
		 * based in part on the algorithm by Jean-Marie Oudin (1940).
		 *
		 * @see http://www.tondering.dk/claus/cal/easter.php
		 */

		int a = year % 19;
		int b = year / 100;
		int c = ((b - (b / 4) - (((8 * b) + 13) / 25)) + (19 * a) + 15) % 30;
		int d = c - ((c / 28) * (1 - ((29 / (c + 1)) * ((21 - a) / 11))));
		int e = (((year + (year / 4) + d + 2) - b) + (b / 4)) % 7;
		int f = d - e;

		int month = 3 + ((f + 40) / 44);
		int day = (f + 28) - (31 * (month / 4));

		return MonthDay.of(month, day);
	}

	private static MonthDay computeOrthodoxEasterSunday(int year) {
		/*
		 * This code implements the algorithm designed by Claus Tøndering (1998),
		 * based in part on the algorithm by Jean-Marie Oudin (1940).
		 *
		 * @see http://www.tondering.dk/claus/cal/easter.php
		 */

		int a = year % 19;
		int b = ((19 * a) + 15) % 30;
		int c = (year + (year / 4) + b) % 7;
		int d = b - c;

		int month = 3 + ((d + 40) / 44);
		int day = (d + 28) - (31 * (month / 4));

		return MonthDay.from(julianToGregorian(JulianLocalDate.of(year, month, day)));
	}

}
