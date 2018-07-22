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

import static org.sellcom.geotemporal.time.HolidayType.PUBLIC;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_AND_UNIVERSITIES_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_ONLY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.Temporal;
import java.util.EnumSet;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.internal.StringUtils;
import org.sellcom.geotemporal.internal.time.applicability.HolidayApplicability;
import org.sellcom.geotemporal.internal.time.applicability.WeekendApplicability;
import org.sellcom.geotemporal.internal.time.applicability.WorkingDayApplicability;
import org.sellcom.geotemporal.internal.time.applicability.parser.GeoTemporalApplicabilityParser;
import org.sellcom.geotemporal.time.GeoTemporalPredicate;
import org.sellcom.geotemporal.time.HolidayType;

/**
 * Applicability at a {@code Temporal} in a {@code GeoRegion}.
 *
 * @since 1.0
 *
 * @see GeoRegion
 * @see Temporal
 */
public abstract class GeoTemporalApplicability implements GeoTemporalPredicate {

	private static final GeoTemporalApplicabilityParser parser = new GeoTemporalApplicabilityParser();


	/**
	 * Creates an applicability satisfied on holidays of any of the given types.
	 *
	 * @since 1.0
	 */
	public static GeoTemporalApplicability holiday(EnumSet<HolidayType> types) {
		return new HolidayApplicability(types);
	}

	/**
	 * Creates an applicability satisfied when the given expression evaluates to {@code true}.
	 * The expression must be a Boolean expression composed of a combination of the Boolean constants "{@code true}" and "{@code false}", temporal predicates (see below), Boolean operators "{@code and}", "{@code or}", and "{@code not}", and parentheses "{@code (}" and "{@code )}".
	 *
	 * <h3>Examples</h3>
	 * <table>
	 * <caption>Example geo-temporal expressions</caption>
	 * <thead>
	 * <tr class="tableSubHeadingColor">
	 *     <th>Expression</th>
	 *     <th>Meaning</th>
	 * </tr>
	 * </thead>
	 * <tbody>
	 * <tr class="rowColor">
	 *     <td>{@code (singleMonth[Sep] or singleMonth[Dec]) and singleDayOfWeek[Sat]}</td>
	 *     <td>Saturdays in September and December</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code monthDayRange[10-15,10-29] and singleDayOfWeek[Fri]}</td>
	 *     <td>Fridays between 15 October and 29 October (both dates inclusive)</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 *
	 * <h3>Supported date predicates</h3>
	 * <table>
	 * <caption>Predicates supported in geo-temporal expressions</caption>
	 * <thead>
	 * <tr class="tableSubHeadingColor">
	 *     <th>Syntax</th>
	 *     <th>Description</th>
	 *     <th>Example</th>
	 *     <th>Meaning</th>
	 * </tr>
	 * </thead>
	 * <tbody>
	 * <tr class="rowColor">
	 *     <td>{@code dateRange[YYYY-MM-DD,YYYY-MM-DD]}</td>
	 *     <td>range of dates<br>(see {@link TemporalApplicability#dateRange(LocalDate, LocalDate)})</td>
	 *     <td>{@code dateRange[2015-10-15,2015-10-29]}</td>
	 *     <td>15 October 2015 to 29 October 2015</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code dayOfWeekAfter[N,U,MM-DD]}</td>
	 *     <td>n<sup>th</sup> day-of-week after a month-day<br>(see {@link TemporalApplicability#dayOfWeekAfter(int, DayOfWeek, MonthDay)})</td>
	 *     <td>{@code dayOfWeekAfter[4,Thu,10-31]}</td>
	 *     <td>fourth Thursday after 31 October, fourth Thursday in November</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code dayOfWeekBefore[N,U,MM-DD]}</td>
	 *     <td>n<sup>th</sup> day-of-week before a month-day<br>(see {@link TemporalApplicability#dayOfWeekBefore(int, DayOfWeek, MonthDay)})</td>
	 *     <td>{@code dayOfWeekBefore[1,Sun,12-25]}</td>
	 *     <td>first Sunday before 25 December</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code dayOfWeekRange[U,U]}</td>
	 *     <td>range of days-of-week<br>(see {@link TemporalApplicability#dayOfWeekRange(DayOfWeek, DayOfWeek)})</td>
	 *     <td>{@code dayOfWeekRange[Fri,Sun]}</td>
	 *     <td>Friday to Sunday</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code false}</td>
	 *     <td>never<br>(see {@link TemporalApplicability#never()})</td>
	 *     <td>{@code false}</td>
	 *     <td>never</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code holiday[T...]}</td>
	 *     <td>holiday of any of the given types<br>(see {@link GeoTemporalApplicability#holiday(EnumSet)})</td>
	 *     <td>{@code holiday[P]}</td>
	 *     <td>public holidays</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code isoWeek[even]}</td>
	 *     <td>weeks with even ISO week number<br>(see {@link TemporalApplicability#evenIsoWeek()})</td>
	 *     <td>{@code isoWeek[even]}</td>
	 *     <td>weeks with even ISO week number</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code isoWeek[odd]}</td>
	 *     <td>weeks with odd ISO week number<br>(see {@link TemporalApplicability#oddIsoWeek()})</td>
	 *     <td>{@code isoWeek[odd]}</td>
	 *     <td>weeks with odd ISO week number</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code isoWeekRange[W,W]}</td>
	 *     <td>weeks with ISO week numbers from the given range<br>(see {@link TemporalApplicability#isoWeekRange(int, int)})</td>
	 *     <td>{@code isoWeekRange[5,9]}</td>
	 *     <td>weeks with ISO week numbers 5 to 9</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code monthDayRange[MM-DD,MM-DD]}</td>
	 *     <td>range of month-days<br>(see {@link TemporalApplicability#monthDayRange(MonthDay, MonthDay)})</td>
	 *     <td>{@code monthDayRange[10-15,10-29]}</td>
	 *     <td>15 October to 29 October</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code monthRange[MM,MM]}</td>
	 *     <td>range of months-of-year<br>(see {@link TemporalApplicability#monthRange(Month, Month)})</td>
	 *     <td>{@code monthRange[10,12]}</td>
	 *     <td>October to December</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code publicHoliday[]}</td>
	 *     <td>single date<br>(see {@link GeoTemporalApplicability#publicHoliday()})</td>
	 *     <td>{@code publicHoliday[]}</td>
	 *     <td>public holidays</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code schoolHoliday[]}</td>
	 *     <td>single date<br>(see {@link GeoTemporalApplicability#schoolHoliday()})</td>
	 *     <td>{@code schoolHoliday[]}</td>
	 *     <td>school holidays</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code singleDate[YYYY-MM-DD]}</td>
	 *     <td>single date<br>(see {@link TemporalApplicability#singleDate(LocalDate)})</td>
	 *     <td>{@code singleDate[2015-10-15]}</td>
	 *     <td>15 October 2015</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code singleDayOfWeek[U]}</td>
	 *     <td>single day-of-week<br>(see {@link TemporalApplicability#singleDayOfWeek(DayOfWeek)})</td>
	 *     <td>{@code singleDayOfWeek[Fri]}</td>
	 *     <td>Friday</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code singleIsoWeek[W]}</td>
	 *     <td>weeks with ISO week number<br>(see {@link TemporalApplicability#singleIsoWeek(int)})</td>
	 *     <td>{@code singleIsoWeek[42]}</td>
	 *     <td>weeks with ISO week number 42</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code singleMonth[MM]}</td>
	 *     <td>single month-of-year<br>(see {@link TemporalApplicability#singleMonth(Month)})</td>
	 *     <td>{@code singleMonth[10]}</td>
	 *     <td>October</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code singleMonthDay[MM-DD]}</td>
	 *     <td>single month-day<br>(see {@link TemporalApplicability#singleMonthDay(MonthDay)})</td>
	 *     <td>{@code singleMonthDay[10-15]}</td>
	 *     <td>15 October</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code singleYear[YYYY]}</td>
	 *     <td>single year<br>(see {@link TemporalApplicability#singleYear(int)})</td>
	 *     <td>{@code singleYear[2015]}</td>
	 *     <td>2015</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code true}</td>
	 *     <td>always<br>(see {@link TemporalApplicability#always()})</td>
	 *     <td>{@code true}</td>
	 *     <td>always</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code weekend[]}</td>
	 *     <td>weekend days<br>(see {@link TemporalApplicability#weekend()})</td>
	 *     <td>{@code weekend[]}</td>
	 *     <td>weekend days</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>{@code workingDay[]}</td>
	 *     <td>working days<br>(see {@link TemporalApplicability#workingDay()})</td>
	 *     <td>{@code workingDay[]}</td>
	 *     <td>working days</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>{@code yearRange[YYYY,YYYY]}</td>
	 *     <td>range of years<br>(see {@link TemporalApplicability#yearRange(int, int)})</td>
	 *     <td>{@code year[2014,2015]}</td>
	 *     <td>2014 to 2015</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 *
	 * <h3>Constants for holiday types</h3>
	 * <table>
	 * <caption>Constants for holiday types supported in geo-temporal predicates</caption>
	 * <thead>
	 * <tr class="tableSubHeadingColor">
	 *     <th>Constant</th>
	 *     <th>Meaning</th>
	 * </tr>
	 * </thead>
	 * <tbody>
	 * <tr class="rowColor">
	 *     <td>B</td>
	 *     <td>holidays observed only by banks and (most) financial institutions<br>(see {@link HolidayType#BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY})</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>G</td>
	 *     <td>holidays observed only by government services<br>(see {@link HolidayType#GOVERNMENT_SERVICES_ONLY})</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>P</td>
	 *     <td>holidays observed globally<br>(see {@link HolidayType#PUBLIC})</td>
	 * </tr>
	 * <tr class="altColor">
	 *     <td>S</td>
	 *     <td>holidays observed only by schools<br>(see {@link HolidayType#SCHOOLS_ONLY})</td>
	 * </tr>
	 * <tr class="rowColor">
	 *     <td>U</td>
	 *     <td>holidays observed only by schools and universities<br>(see {@link HolidayType#SCHOOLS_AND_UNIVERSITIES_ONLY})</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 *
	 * @throws GeoTemporalApplicabilityParseException if the expression cannot be parsed
	 * @throws IllegalArgumentException if {@code expression} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static GeoTemporalApplicability parse(String expression) {
		Contract.checkArgument(!StringUtils.isNullOrEmpty(expression), "Expression must not be null or empty");

		return parser.parse(expression);
	}

	/**
	 * Creates an applicability satisfied on public holidays.
	 * <p>
	 * Only considers public holidays ({@link HolidayType#PUBLIC}).
	 * To customise the holiday types, use {@link #holiday(EnumSet)} instead.
	 *
	 * @since 1.0
	 */
	public static GeoTemporalApplicability publicHoliday() {
		return new HolidayApplicability(EnumSet.of(PUBLIC));
	}

	/**
	 * Creates an applicability satisfied on school holidays.
	 * <p>
	 * Only considers school holidays ({@link HolidayType#SCHOOLS_AND_UNIVERSITIES_ONLY} and {@link HolidayType#SCHOOLS_ONLY}).
	 * To customise the holiday types, use {@link #holiday(EnumSet)} instead.
	 *
	 * @since 1.0
	 */
	public static GeoTemporalApplicability schoolHoliday() {
		return new HolidayApplicability(EnumSet.of(SCHOOLS_AND_UNIVERSITIES_ONLY, SCHOOLS_ONLY));
	}

	/**
	 * Checks whether this applicability is satisfied at the given temporal in the given region.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 *
	 * @since 1.0
	 */
	@Override
	public abstract boolean test(Temporal temporal, GeoRegion region);

	/**
	 * Creates an applicability satisfied on weekends.
	 *
	 * @since 1.0
	 */
	public static GeoTemporalApplicability weekend() {
		return new WeekendApplicability();
	}

	/**
	 * Creates an applicability satisfied on working days.
	 *
	 * @since 1.2
	 */
	public static GeoTemporalApplicability workingDay() {
		return new WorkingDayApplicability();
	}

}
