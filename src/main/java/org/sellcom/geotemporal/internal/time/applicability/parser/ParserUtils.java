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
package org.sellcom.geotemporal.internal.time.applicability.parser;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.sellcom.geotemporal.time.HolidayType.BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.GOVERNMENT_SERVICES_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.PUBLIC;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_AND_UNIVERSITIES_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_ONLY;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeParseException;
import java.util.EnumSet;

import org.sellcom.geotemporal.time.HolidayType;
import org.sellcom.geotemporal.time.applicability.GeoTemporalApplicability;
import org.sellcom.geotemporal.time.applicability.GeoTemporalApplicabilityParseException;
import org.sellcom.geotemporal.time.applicability.TemporalApplicability;

class ParserUtils {

	private ParserUtils() {
		// Utility class, not to be instantiated
	}


	static GeoTemporalApplicability parseLocalDateApplicability(String rule) {
		try {
			String prefix = MatcherUtils.getPrefix(rule);
			switch (prefix) {
				/*
				 * Range of dates
				 *
				 * example: dateRange[2015-10-15,2015-10-29]
				 */
				case "dateRange[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String startDateExpression = parameterTokenizer.tokenAt(0);
					LocalDate startDate = LocalDate.parse(startDateExpression);

					String endDateExpression = parameterTokenizer.tokenAt(1);
					LocalDate endDate = LocalDate.parse(endDateExpression);

					return TemporalApplicability.dateRange(startDate, endDate);
				}

				/*
				 * Day of week after
				 *
				 * example: dayOfWeekAfter[4,Thu,10-31]
				 */
				case "dayOfWeekAfter[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String ordinalExpression = parameterTokenizer.tokenAt(0);
					int ordinal = Integer.parseInt(ordinalExpression);

					String dayOfWeekExpression = parameterTokenizer.tokenAt(1);
					DayOfWeek dayOfWeek = parseDayOfWeek(dayOfWeekExpression);

					String monthDayExpression = parameterTokenizer.tokenAt(2);
					MonthDay monthDay = MonthDay.parse("--" + monthDayExpression);

					return TemporalApplicability.dayOfWeekAfter(ordinal, dayOfWeek, monthDay);
				}

				/*
				 * Day of week before
				 *
				 * example: dayOfWeekBefore[1,Sun,12-25]
				 */
				case "dayOfWeekBefore[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String ordinalExpression = parameterTokenizer.tokenAt(0);
					int ordinal = Integer.parseInt(ordinalExpression);

					String dayOfWeekExpression = parameterTokenizer.tokenAt(1);
					DayOfWeek dayOfWeek = parseDayOfWeek(dayOfWeekExpression);

					String monthDayExpression = parameterTokenizer.tokenAt(2);
					MonthDay monthDay = MonthDay.parse("--" + monthDayExpression);

					return TemporalApplicability.dayOfWeekBefore(ordinal, dayOfWeek, monthDay);
				}

				/*
				 * Range of days of week
				 *
				 * example: dayOfWeekRange[Fri,Sun]
				 */
				case "dayOfWeekRange[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String startDayOfWeekExpression = parameterTokenizer.tokenAt(0);
					DayOfWeek startDayOfWeek = parseDayOfWeek(startDayOfWeekExpression);

					String endDayOfWeekExpression = parameterTokenizer.tokenAt(1);
					DayOfWeek endDayOfWeek = parseDayOfWeek(endDayOfWeekExpression);

					return TemporalApplicability.dayOfWeekRange(startDayOfWeek, endDayOfWeek);
				}

				/*
				 * Constant 'false'
				 */
				case "false": {
					return TemporalApplicability.never();
				}

				/*
				 * Holidays
				 *
				 * example: holiday[P]
				 */
				case "holiday[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					EnumSet<HolidayType> types = EnumSet.noneOf(HolidayType.class);
					for (int i = 0, j = parameterTokenizer.tokens(); i < j; i += 1) {
						String type = parameterTokenizer.tokenAt(i);
						switch (type) {
							case "B": {
								types.add(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY);

								break;
							}
							case "G": {
								types.add(GOVERNMENT_SERVICES_ONLY);

								break;
							}
							case "P": {
								types.add(PUBLIC);

								break;
							}
							case "S": {
								types.add(SCHOOLS_ONLY);

								break;
							}
							case "U": {
								types.add(SCHOOLS_AND_UNIVERSITIES_ONLY);

								break;
							}
							default: {
								throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" contains unsupported holiday type \"{1}\"", rule, type));
							}
						}
					}

					if (types.isEmpty()) {
						throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" contains no holiday types", rule));
					}

					return GeoTemporalApplicability.holiday(types);
				}

				/*
				 * Even or odd ISO weeks
				 *
				 * example: isoWeek[even]
				 * example: isoWeek[odd]
				 */
				case "isoWeek[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());
					switch (parameterTokenizer.tokenAt(0)) {
						case "even" : {
							return TemporalApplicability.evenIsoWeek();
						}
						case "odd" : {
							return TemporalApplicability.oddIsoWeek();
						}
						default: {
							throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" could not be parsed", rule));
						}
					}
				}

				/*
				 * Range of ISO weeks
				 *
				 * example: isoWeekRange[7,8]
				 */
				case "isoWeekRange[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String startWeekNumberExpression = parameterTokenizer.tokenAt(0);
					int startWeekNumber = Integer.parseInt(startWeekNumberExpression);

					String endTimeExpression = parameterTokenizer.tokenAt(1);
					int endWeekNumber = Integer.parseInt(endTimeExpression);

					return TemporalApplicability.isoWeekRange(startWeekNumber, endWeekNumber);
				}

				/*
				 * Range of month-days
				 *
				 * example: monthDayRange[10-15,10-29]
				 */
				case "monthDayRange[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String startMonthDayExpression = parameterTokenizer.tokenAt(0);
					MonthDay startMonthDay = MonthDay.parse("--" + startMonthDayExpression);

					String endMonthDayExpression = parameterTokenizer.tokenAt(1);
					MonthDay endMonthDay = MonthDay.parse("--" + endMonthDayExpression);

					return TemporalApplicability.monthDayRange(startMonthDay, endMonthDay);
				}

				/*
				 * Range of months
				 *
				 * example: monthRange[10,12]
				 */
				case "monthRange[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String startMonthExpression = parameterTokenizer.tokenAt(0);
					Month startMonth = Month.of(Integer.parseInt(startMonthExpression, 10));

					String endMonthExpression = parameterTokenizer.tokenAt(1);
					Month endMonth = Month.of(Integer.parseInt(endMonthExpression, 10));

					return TemporalApplicability.monthRange(startMonth, endMonth);
				}

				/*
				 * Public holidays
				 *
				 * example: publicHoliday[]
				 */
				case "publicHoliday[": {
					return GeoTemporalApplicability.publicHoliday();
				}

				/*
				 * School holidays
				 *
				 * example: schoolHoliday[]
				 */
				case "schoolHoliday[": {
					return GeoTemporalApplicability.schoolHoliday();
				}

				/*
				 * Single date
				 *
				 * example: singleDate[2015-10-15]
				 */
				case "singleDate[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String dateExpression = parameterTokenizer.tokenAt(0);
					LocalDate date = LocalDate.parse(dateExpression);

					return TemporalApplicability.singleDate(date);
				}


				/*
				 * Single day of week
				 *
				 * example: singleDayOfWeek[Fri]
				 */
				case "singleDayOfWeek[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String dayOfWeekExpression = parameterTokenizer.tokenAt(0);
					DayOfWeek dayOfWeek = parseDayOfWeek(dayOfWeekExpression);

					return TemporalApplicability.singleDayOfWeek(dayOfWeek);
				}

				/*
				 * Single ISO week
				 *
				 * example: singleIsoWeek[42]
				 */
				case "singleIsoWeek[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String argumentExpression = parameterTokenizer.tokenAt(0);
					int weekNumber = Integer.parseInt(argumentExpression);

					return TemporalApplicability.singleIsoWeek(weekNumber);
				}

				/*
				 * Single month
				 *
				 * example: singleMonth[10]
				 */
				case "singleMonth[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String monthExpression = parameterTokenizer.tokenAt(0);
					Month month = Month.of(Integer.parseInt(monthExpression, 10));

					return TemporalApplicability.singleMonth(month);
				}

				/*
				 * Single month-day
				 *
				 * example: singleMonthDay[10-15]
				 */
				case "singleMonthDay[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String monthDayExpression = parameterTokenizer.tokenAt(0);
					MonthDay monthDay = MonthDay.parse("--" + monthDayExpression);

					return TemporalApplicability.singleMonthDay(monthDay);
				}

				/*
				 * Single year
				 *
				 * example: singleYear[2015]
				 */
				case "singleYear[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String yearExpression = parameterTokenizer.tokenAt(0);
					int year = Integer.parseInt(yearExpression, 10);

					return TemporalApplicability.singleYear(year);
				}

				/*
				 * Constant 'true'
				 */
				case "true": {
					return TemporalApplicability.always();
				}

				/*
				 * Weekends
				 *
				 * example: weekend[]
				 */
				case "weekend[": {
					return GeoTemporalApplicability.weekend();
				}

				/*
				 * Working days
				 *
				 * example: workingDay[]
				 */
				case "workingDay[": {
					return GeoTemporalApplicability.workingDay();
				}

				/*
				 * Range of years
				 *
				 * example: yearRange[2014,2015]
				 */
				case "yearRange[": {
					ParameterTokenizer parameterTokenizer = new ParameterTokenizer(rule, prefix.length());

					String startYearExpression = parameterTokenizer.tokenAt(0);
					int startYear = Integer.parseInt(startYearExpression, 10);

					String endYearExpression = parameterTokenizer.tokenAt(1);
					int endYear = Integer.parseInt(endYearExpression, 10);

					return TemporalApplicability.yearRange(startYear, endYear);
				}

				default: {
					throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" could not be parsed", rule));
				}
			}
		} catch (ArrayIndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
			throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" could not be parsed", rule), e);
		}
	}


	private static DayOfWeek parseDayOfWeek(String rule) {
		switch (rule) {
			case "Mon": {
				return MONDAY;
			}
			case "Tue": {
				return TUESDAY;
			}
			case "Wed": {
				return WEDNESDAY;
			}
			case "Thu": {
				return THURSDAY;
			}
			case "Fri": {
				return FRIDAY;
			}
			case "Sat": {
				return SATURDAY;
			}
			case "Sun": {
				return SUNDAY;
			}
			default: {
				throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" could not be parsed", rule));
			}
		}
	}

}
