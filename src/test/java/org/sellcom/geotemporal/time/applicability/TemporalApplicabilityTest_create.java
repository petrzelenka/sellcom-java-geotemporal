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

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.util.Collections.emptySet;
import static org.sellcom.geotemporal.internal.CollectionUtils.singletonSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeNavigableSet;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.internal.time.TemporalPredicateUtils.testTemporalPredicate;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.dateRange;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.dayOfWeekAfter;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.dayOfWeekRange;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.evenIsoWeek;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.isoWeekRange;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.monthDayRange;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.monthRange;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.oddIsoWeek;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.singleDate;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.singleDayOfWeek;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.singleIsoWeek;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.singleMonth;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.singleMonthDay;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.singleYear;
import static org.sellcom.geotemporal.time.applicability.TemporalApplicability.yearRange;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TemporalApplicabilityTest_create {

	@Test
	public void testDateRange_notSame() {
		TemporalApplicability applicability = dateRange(LocalDate.of(2012, JULY, 27), LocalDate.of(2012, AUGUST, 12)); // Official dates of 2012 Summer Olympics in London

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2011, JULY, 1), LocalDate.of(2011, AUGUST, 30)),
				emptySet());
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2012, JULY, 1), LocalDate.of(2012, AUGUST, 30)),
				dateRangeNavigableSet(LocalDate.of(2012, JULY, 27), LocalDate.of(2012, AUGUST, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2013, JULY, 1), LocalDate.of(2013, AUGUST, 30)),
				emptySet());
	}

	@Test
	public void testDateRange_same() {
		TemporalApplicability applicability = dateRange(LocalDate.of(2005, JULY, 6), LocalDate.of(2005, JULY, 6)); // London selected as the host city of 2012 Summer Olympics

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2004, JULY, 1), LocalDate.of(2004, JULY, 31)),
				emptySet());
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2005, JULY, 1), LocalDate.of(2005, JULY, 31)),
				singletonSet(LocalDate.of(2005, JULY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2006, JULY, 1), LocalDate.of(2006, JULY, 31)),
				emptySet());
	}

	@Test
	public void testDayOfWeekAfter() {
		TemporalApplicability applicability = dayOfWeekAfter(4, THURSDAY, MonthDay.of(OCTOBER, 31)); // Thanksgiving Day in the United States

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2000, NOVEMBER, 1), LocalDate.of(2000, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2000, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2001, NOVEMBER, 1), LocalDate.of(2001, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2001, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2002, NOVEMBER, 1), LocalDate.of(2002, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2002, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2003, NOVEMBER, 1), LocalDate.of(2003, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2003, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2004, NOVEMBER, 1), LocalDate.of(2004, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2004, NOVEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2005, NOVEMBER, 1), LocalDate.of(2005, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2005, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2006, NOVEMBER, 1), LocalDate.of(2006, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2006, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2007, NOVEMBER, 1), LocalDate.of(2007, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2007, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2008, NOVEMBER, 1), LocalDate.of(2008, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2008, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2009, NOVEMBER, 1), LocalDate.of(2009, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2009, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2010, NOVEMBER, 1), LocalDate.of(2010, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2010, NOVEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2011, NOVEMBER, 1), LocalDate.of(2011, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2011, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2012, NOVEMBER, 1), LocalDate.of(2012, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2012, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2013, NOVEMBER, 1), LocalDate.of(2013, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2013, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, NOVEMBER, 1), LocalDate.of(2014, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2014, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, NOVEMBER, 1), LocalDate.of(2015, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2015, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, NOVEMBER, 1), LocalDate.of(2016, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2016, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2017, NOVEMBER, 1), LocalDate.of(2017, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2017, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2018, NOVEMBER, 1), LocalDate.of(2018, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2018, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2019, NOVEMBER, 1), LocalDate.of(2019, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2019, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2020, NOVEMBER, 1), LocalDate.of(2020, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2020, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2021, NOVEMBER, 1), LocalDate.of(2021, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2021, NOVEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2022, NOVEMBER, 1), LocalDate.of(2022, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2022, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2023, NOVEMBER, 1), LocalDate.of(2023, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2023, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2024, NOVEMBER, 1), LocalDate.of(2024, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2024, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2025, NOVEMBER, 1), LocalDate.of(2025, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2025, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2026, NOVEMBER, 1), LocalDate.of(2026, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2026, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2027, NOVEMBER, 1), LocalDate.of(2027, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2027, NOVEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2028, NOVEMBER, 1), LocalDate.of(2028, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2028, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2029, NOVEMBER, 1), LocalDate.of(2029, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2029, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2030, NOVEMBER, 1), LocalDate.of(2030, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2030, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2031, NOVEMBER, 1), LocalDate.of(2031, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2031, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2032, NOVEMBER, 1), LocalDate.of(2032, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2032, NOVEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2033, NOVEMBER, 1), LocalDate.of(2033, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2033, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2034, NOVEMBER, 1), LocalDate.of(2034, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2034, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2035, NOVEMBER, 1), LocalDate.of(2035, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2035, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2036, NOVEMBER, 1), LocalDate.of(2036, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2036, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2037, NOVEMBER, 1), LocalDate.of(2037, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2037, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2038, NOVEMBER, 1), LocalDate.of(2038, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2038, NOVEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2039, NOVEMBER, 1), LocalDate.of(2039, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2039, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2040, NOVEMBER, 1), LocalDate.of(2040, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2040, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2041, NOVEMBER, 1), LocalDate.of(2041, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2041, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2042, NOVEMBER, 1), LocalDate.of(2042, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2042, NOVEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2043, NOVEMBER, 1), LocalDate.of(2043, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2043, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2044, NOVEMBER, 1), LocalDate.of(2044, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2044, NOVEMBER, 24)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2045, NOVEMBER, 1), LocalDate.of(2045, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2045, NOVEMBER, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2046, NOVEMBER, 1), LocalDate.of(2046, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2046, NOVEMBER, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2047, NOVEMBER, 1), LocalDate.of(2047, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2047, NOVEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2048, NOVEMBER, 1), LocalDate.of(2048, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2048, NOVEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2049, NOVEMBER, 1), LocalDate.of(2049, NOVEMBER, 30)),
				singletonSet(LocalDate.of(2049, NOVEMBER, 25)));
	}

	@Test
	public void testDayOfWeekBefore() {
		TemporalApplicability applicability = TemporalApplicability.dayOfWeekBefore(2, SUNDAY, MonthDay.of(DECEMBER, 25)); // Gaudete Sunday

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2000, DECEMBER, 1), LocalDate.of(2000, DECEMBER, 31)),
				singletonSet(LocalDate.of(2000, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2001, DECEMBER, 1), LocalDate.of(2001, DECEMBER, 31)),
				singletonSet(LocalDate.of(2001, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2002, DECEMBER, 1), LocalDate.of(2002, DECEMBER, 31)),
				singletonSet(LocalDate.of(2002, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2003, DECEMBER, 1), LocalDate.of(2003, DECEMBER, 31)),
				singletonSet(LocalDate.of(2003, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2004, DECEMBER, 1), LocalDate.of(2004, DECEMBER, 31)),
				singletonSet(LocalDate.of(2004, DECEMBER, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2005, DECEMBER, 1), LocalDate.of(2005, DECEMBER, 31)),
				singletonSet(LocalDate.of(2005, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2006, DECEMBER, 1), LocalDate.of(2006, DECEMBER, 31)),
				singletonSet(LocalDate.of(2006, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2007, DECEMBER, 1), LocalDate.of(2007, DECEMBER, 31)),
				singletonSet(LocalDate.of(2007, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2008, DECEMBER, 1), LocalDate.of(2008, DECEMBER, 31)),
				singletonSet(LocalDate.of(2008, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2009, DECEMBER, 1), LocalDate.of(2009, DECEMBER, 31)),
				singletonSet(LocalDate.of(2009, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2010, DECEMBER, 1), LocalDate.of(2010, DECEMBER, 31)),
				singletonSet(LocalDate.of(2010, DECEMBER, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2011, DECEMBER, 1), LocalDate.of(2011, DECEMBER, 31)),
				singletonSet(LocalDate.of(2011, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2012, DECEMBER, 1), LocalDate.of(2012, DECEMBER, 31)),
				singletonSet(LocalDate.of(2012, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2013, DECEMBER, 1), LocalDate.of(2013, DECEMBER, 31)),
				singletonSet(LocalDate.of(2013, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, DECEMBER, 1), LocalDate.of(2014, DECEMBER, 31)),
				singletonSet(LocalDate.of(2014, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, DECEMBER, 1), LocalDate.of(2015, DECEMBER, 31)),
				singletonSet(LocalDate.of(2015, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, DECEMBER, 1), LocalDate.of(2016, DECEMBER, 31)),
				singletonSet(LocalDate.of(2016, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2017, DECEMBER, 1), LocalDate.of(2017, DECEMBER, 31)),
				singletonSet(LocalDate.of(2017, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2018, DECEMBER, 1), LocalDate.of(2018, DECEMBER, 31)),
				singletonSet(LocalDate.of(2018, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2019, DECEMBER, 1), LocalDate.of(2019, DECEMBER, 31)),
				singletonSet(LocalDate.of(2019, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2020, DECEMBER, 1), LocalDate.of(2020, DECEMBER, 31)),
				singletonSet(LocalDate.of(2020, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2021, DECEMBER, 1), LocalDate.of(2021, DECEMBER, 31)),
				singletonSet(LocalDate.of(2021, DECEMBER, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2022, DECEMBER, 1), LocalDate.of(2022, DECEMBER, 31)),
				singletonSet(LocalDate.of(2022, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2023, DECEMBER, 1), LocalDate.of(2023, DECEMBER, 31)),
				singletonSet(LocalDate.of(2023, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2024, DECEMBER, 1), LocalDate.of(2024, DECEMBER, 31)),
				singletonSet(LocalDate.of(2024, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2025, DECEMBER, 1), LocalDate.of(2025, DECEMBER, 31)),
				singletonSet(LocalDate.of(2025, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2026, DECEMBER, 1), LocalDate.of(2026, DECEMBER, 31)),
				singletonSet(LocalDate.of(2026, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2027, DECEMBER, 1), LocalDate.of(2027, DECEMBER, 31)),
				singletonSet(LocalDate.of(2027, DECEMBER, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2028, DECEMBER, 1), LocalDate.of(2028, DECEMBER, 31)),
				singletonSet(LocalDate.of(2028, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2029, DECEMBER, 1), LocalDate.of(2029, DECEMBER, 31)),
				singletonSet(LocalDate.of(2029, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2030, DECEMBER, 1), LocalDate.of(2030, DECEMBER, 31)),
				singletonSet(LocalDate.of(2030, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2031, DECEMBER, 1), LocalDate.of(2031, DECEMBER, 31)),
				singletonSet(LocalDate.of(2031, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2032, DECEMBER, 1), LocalDate.of(2032, DECEMBER, 31)),
				singletonSet(LocalDate.of(2032, DECEMBER, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2033, DECEMBER, 1), LocalDate.of(2033, DECEMBER, 31)),
				singletonSet(LocalDate.of(2033, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2034, DECEMBER, 1), LocalDate.of(2034, DECEMBER, 31)),
				singletonSet(LocalDate.of(2034, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2035, DECEMBER, 1), LocalDate.of(2035, DECEMBER, 31)),
				singletonSet(LocalDate.of(2035, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2036, DECEMBER, 1), LocalDate.of(2036, DECEMBER, 31)),
				singletonSet(LocalDate.of(2036, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2037, DECEMBER, 1), LocalDate.of(2037, DECEMBER, 31)),
				singletonSet(LocalDate.of(2037, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2038, DECEMBER, 1), LocalDate.of(2038, DECEMBER, 31)),
				singletonSet(LocalDate.of(2038, DECEMBER, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2039, DECEMBER, 1), LocalDate.of(2039, DECEMBER, 31)),
				singletonSet(LocalDate.of(2039, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2040, DECEMBER, 1), LocalDate.of(2040, DECEMBER, 31)),
				singletonSet(LocalDate.of(2040, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2041, DECEMBER, 1), LocalDate.of(2041, DECEMBER, 31)),
				singletonSet(LocalDate.of(2041, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2042, DECEMBER, 1), LocalDate.of(2042, DECEMBER, 31)),
				singletonSet(LocalDate.of(2042, DECEMBER, 14)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2043, DECEMBER, 1), LocalDate.of(2043, DECEMBER, 31)),
				singletonSet(LocalDate.of(2043, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2044, DECEMBER, 1), LocalDate.of(2044, DECEMBER, 31)),
				singletonSet(LocalDate.of(2044, DECEMBER, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2045, DECEMBER, 1), LocalDate.of(2045, DECEMBER, 31)),
				singletonSet(LocalDate.of(2045, DECEMBER, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2046, DECEMBER, 1), LocalDate.of(2046, DECEMBER, 31)),
				singletonSet(LocalDate.of(2046, DECEMBER, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2047, DECEMBER, 1), LocalDate.of(2047, DECEMBER, 31)),
				singletonSet(LocalDate.of(2047, DECEMBER, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2048, DECEMBER, 1), LocalDate.of(2048, DECEMBER, 31)),
				singletonSet(LocalDate.of(2048, DECEMBER, 13)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2049, DECEMBER, 1), LocalDate.of(2049, DECEMBER, 31)),
				singletonSet(LocalDate.of(2049, DECEMBER, 12)));
	}

	@Test
	public void testDayOfWeekRange_coveringWholeRange() {
		TemporalApplicability applicability = dayOfWeekRange(MONDAY, SUNDAY); // Whole week (start on Monday as in Europe)

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)));
	}

	@Test
	public void testDayOfWeekRange_notWrappingAroundTheEndOfTheWeek() {
		TemporalApplicability applicability = dayOfWeekRange(FRIDAY, SATURDAY); // Weekend in Israel

		Set<LocalDate> fridaysAndSaturdaysInOctober2014 = new HashSet<>();
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 3));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 4));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 10));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 11));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 17));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 18));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 24));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 25));
		fridaysAndSaturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 31));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				fridaysAndSaturdaysInOctober2014);

		Set<LocalDate> fridaysAndSaturdaysInOctober2015 = new HashSet<>();
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 2));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 3));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 9));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 10));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 16));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 17));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 23));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 24));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 30));
		fridaysAndSaturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 31));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				fridaysAndSaturdaysInOctober2015);

		Set<LocalDate> fridaysAndSaturdaysInOctober2016 = new HashSet<>();
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 1));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 7));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 8));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 14));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 15));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 21));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 22));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 28));
		fridaysAndSaturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 29));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				fridaysAndSaturdaysInOctober2016);
	}

	@Test
	public void testDayOfWeekRange_same() {
		TemporalApplicability applicability = dayOfWeekRange(SATURDAY, SATURDAY); // End of Jewish Sabbath

		Set<LocalDate> saturdaysInOctober2014 = new HashSet<>();
		saturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 4));
		saturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 11));
		saturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 18));
		saturdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 25));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				saturdaysInOctober2014);

		Set<LocalDate> saturdaysInOctober2015 = new HashSet<>();
		saturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 3));
		saturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 10));
		saturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 17));
		saturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 24));
		saturdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 31));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				saturdaysInOctober2015);

		Set<LocalDate> saturdaysInOctober2016 = new HashSet<>();
		saturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 1));
		saturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 8));
		saturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 15));
		saturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 22));
		saturdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 29));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				saturdaysInOctober2016);
	}

	@Test
	public void testDayOfWeekRange_wrappingAroundTheEndOfTheWeek() {
		TemporalApplicability applicability = dayOfWeekRange(SUNDAY, THURSDAY); // Workweek in Israel

		Set<LocalDate> sundaysToThursdaysInOctober2014 = new HashSet<>();
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 1));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 2));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 5));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 6));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 7));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 8));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 9));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 12));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 13));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 14));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 15));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 16));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 19));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 20));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 21));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 22));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 23));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 26));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 27));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 28));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 29));
		sundaysToThursdaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 30));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				sundaysToThursdaysInOctober2014);

		Set<LocalDate> sundaysToThursdaysInOctober2015 = new HashSet<>();
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 1));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 4));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 5));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 6));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 7));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 8));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 11));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 12));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 13));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 14));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 15));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 18));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 19));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 20));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 21));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 22));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 25));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 26));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 27));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 28));
		sundaysToThursdaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 29));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				sundaysToThursdaysInOctober2015);

		Set<LocalDate> sundaysToThursdaysInOctober2016 = new HashSet<>();
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 2));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 3));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 4));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 5));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 6));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 9));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 10));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 11));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 12));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 13));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 16));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 17));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 18));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 19));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 20));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 23));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 24));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 25));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 26));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 27));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 30));
		sundaysToThursdaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 31));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				sundaysToThursdaysInOctober2016);
	}

	@Test
	public void testEvenIsoWeek() {
		TemporalApplicability applicability = evenIsoWeek();

		Set<LocalDate> evenIsoWeekDaysIn2014 = new HashSet<>();
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 6), LocalDate.of(2014, JANUARY, 12)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 20), LocalDate.of(2014, JANUARY, 26)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, FEBRUARY, 3), LocalDate.of(2014, FEBRUARY, 9)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, FEBRUARY, 17), LocalDate.of(2014, FEBRUARY, 23)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MARCH, 3), LocalDate.of(2014, MARCH, 9)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MARCH, 17), LocalDate.of(2014, MARCH, 23)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MARCH, 31), LocalDate.of(2014, APRIL, 6)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, APRIL, 14), LocalDate.of(2014, APRIL, 20)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, APRIL, 28), LocalDate.of(2014, MAY, 4)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MAY, 12), LocalDate.of(2014, MAY, 18)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MAY, 26), LocalDate.of(2014, JUNE, 1)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JUNE, 9), LocalDate.of(2014, JUNE, 15)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JUNE, 23), LocalDate.of(2014, JUNE, 29)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JULY, 7), LocalDate.of(2014, JULY, 13)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JULY, 21), LocalDate.of(2014, JULY, 27)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, AUGUST, 4), LocalDate.of(2014, AUGUST, 10)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, AUGUST, 18), LocalDate.of(2014, AUGUST, 24)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, SEPTEMBER, 1), LocalDate.of(2014, SEPTEMBER, 7)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, SEPTEMBER, 15), LocalDate.of(2014, SEPTEMBER, 21)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, SEPTEMBER, 29), LocalDate.of(2014, OCTOBER, 5)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, OCTOBER, 13), LocalDate.of(2014, OCTOBER, 19)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, OCTOBER, 27), LocalDate.of(2014, NOVEMBER, 2)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, NOVEMBER, 10), LocalDate.of(2014, NOVEMBER, 16)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, NOVEMBER, 24), LocalDate.of(2014, NOVEMBER, 30)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 8), LocalDate.of(2014, DECEMBER, 14)));
		evenIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 22), LocalDate.of(2014, DECEMBER, 28)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				evenIsoWeekDaysIn2014);

		Set<LocalDate> evenIsoWeekDaysIn2015 = new HashSet<>();
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 5), LocalDate.of(2015, JANUARY, 11)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 19), LocalDate.of(2015, JANUARY, 25)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, FEBRUARY, 2), LocalDate.of(2015, FEBRUARY, 8)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, FEBRUARY, 16), LocalDate.of(2015, FEBRUARY, 22)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MARCH, 2), LocalDate.of(2015, MARCH, 8)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MARCH, 16), LocalDate.of(2015, MARCH, 22)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MARCH, 30), LocalDate.of(2015, APRIL, 5)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, APRIL, 13), LocalDate.of(2015, APRIL, 19)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, APRIL, 27), LocalDate.of(2015, MAY, 3)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MAY, 11), LocalDate.of(2015, MAY, 17)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MAY, 25), LocalDate.of(2015, MAY, 31)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JUNE, 8), LocalDate.of(2015, JUNE, 14)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JUNE, 22), LocalDate.of(2015, JUNE, 28)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JULY, 6), LocalDate.of(2015, JULY, 12)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JULY, 20), LocalDate.of(2015, JULY, 26)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 3), LocalDate.of(2015, AUGUST, 9)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 17), LocalDate.of(2015, AUGUST, 23)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 31), LocalDate.of(2015, SEPTEMBER, 6)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, SEPTEMBER, 14), LocalDate.of(2015, SEPTEMBER, 20)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, SEPTEMBER, 28), LocalDate.of(2015, OCTOBER, 4)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, OCTOBER, 12), LocalDate.of(2015, OCTOBER, 18)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, OCTOBER, 26), LocalDate.of(2015, NOVEMBER, 1)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, NOVEMBER, 9), LocalDate.of(2015, NOVEMBER, 15)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, NOVEMBER, 23), LocalDate.of(2015, NOVEMBER, 29)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 7), LocalDate.of(2015, DECEMBER, 13)));
		evenIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 21), LocalDate.of(2015, DECEMBER, 27)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				evenIsoWeekDaysIn2015);

		Set<LocalDate> evenIsoWeekDaysIn2016 = new HashSet<>();
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 11), LocalDate.of(2016, JANUARY, 17)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 25), LocalDate.of(2016, JANUARY, 31)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 8), LocalDate.of(2016, FEBRUARY, 14)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 22), LocalDate.of(2016, FEBRUARY, 28)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MARCH, 7), LocalDate.of(2016, MARCH, 13)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MARCH, 21), LocalDate.of(2016, MARCH, 27)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, APRIL, 4), LocalDate.of(2016, APRIL, 10)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, APRIL, 18), LocalDate.of(2016, APRIL, 24)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MAY, 2), LocalDate.of(2016, MAY, 8)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MAY, 16), LocalDate.of(2016, MAY, 22)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MAY, 30), LocalDate.of(2016, JUNE, 5)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JUNE, 13), LocalDate.of(2016, JUNE, 19)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JUNE, 27), LocalDate.of(2016, JULY, 3)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JULY, 11), LocalDate.of(2016, JULY, 17)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JULY, 25), LocalDate.of(2016, JULY, 31)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 8), LocalDate.of(2016, AUGUST, 14)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 22), LocalDate.of(2016, AUGUST, 28)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, SEPTEMBER, 5), LocalDate.of(2016, SEPTEMBER, 11)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, SEPTEMBER, 19), LocalDate.of(2016, SEPTEMBER, 25)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, OCTOBER, 3), LocalDate.of(2016, OCTOBER, 9)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, OCTOBER, 17), LocalDate.of(2016, OCTOBER, 23)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, OCTOBER, 31), LocalDate.of(2016, NOVEMBER, 6)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, NOVEMBER, 14), LocalDate.of(2016, NOVEMBER, 20)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, NOVEMBER, 28), LocalDate.of(2016, DECEMBER, 4)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 12), LocalDate.of(2016, DECEMBER, 18)));
		evenIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 26), LocalDate.of(2016, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				evenIsoWeekDaysIn2016);
	}

	@Test
	public void testIsoWeekRange_coveringWholeRange() {
		TemporalApplicability applicability = isoWeekRange(1, 53);

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testIsoWeekRange_notWrappingAroundTheEndOfTheYear() {
		TemporalApplicability applicability = isoWeekRange(7, 8);

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, FEBRUARY, 10), LocalDate.of(2014, FEBRUARY, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, FEBRUARY, 9), LocalDate.of(2015, FEBRUARY, 22)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 15), LocalDate.of(2016, FEBRUARY, 28)));
	}

	@Test
	public void testIsoWeekRange_same() {
		TemporalApplicability applicability = isoWeekRange(7, 7);

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, FEBRUARY, 10), LocalDate.of(2014, FEBRUARY, 16)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, FEBRUARY, 9), LocalDate.of(2015, FEBRUARY, 15)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 15), LocalDate.of(2016, FEBRUARY, 21)));
	}

	@Test
	public void testIsoWeekRange_wrappingAroundTheEndOfTheYear() {
		TemporalApplicability applicability = isoWeekRange(51, 2);

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 12)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JULY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 15), LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 11)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JULY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 14), LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JANUARY, 17)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JULY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 19), LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testMonthDayRange_coveringWholeRange() {
		TemporalApplicability applicability = monthDayRange(MonthDay.of(SEPTEMBER, 1), MonthDay.of(AUGUST, 31)); // Official school year in the Czech Republic

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, SEPTEMBER, 1), LocalDate.of(2014, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, SEPTEMBER, 1), LocalDate.of(2014, AUGUST, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, SEPTEMBER, 1), LocalDate.of(2015, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, SEPTEMBER, 1), LocalDate.of(2015, AUGUST, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, SEPTEMBER, 1), LocalDate.of(2016, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, SEPTEMBER, 1), LocalDate.of(2016, AUGUST, 31)));
	}

	@Test
	public void testMonthDayRange_notWrappingAroundTheEndOfTheYear() {
		TemporalApplicability applicability = monthDayRange(MonthDay.of(DECEMBER, 24), MonthDay.of(DECEMBER, 26)); // Christmas-related public holidays in the Czech Republic

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, DECEMBER, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 24), LocalDate.of(2014, DECEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, DECEMBER, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 24), LocalDate.of(2015, DECEMBER, 26)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, DECEMBER, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 24), LocalDate.of(2016, DECEMBER, 26)));
	}

	@Test
	public void testMonthDayRange_same() {
		TemporalApplicability applicability = monthDayRange(MonthDay.of(JANUARY, 1), MonthDay.of(JANUARY, 1)); // New Year's Day

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 31)),
				singletonSet(LocalDate.of(2014, JANUARY, 1)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 31)),
				singletonSet(LocalDate.of(2015, JANUARY, 1)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JANUARY, 31)),
				singletonSet(LocalDate.of(2016, JANUARY, 1)));
	}

	@Test
	public void testMonthDayRange_wrappingAroundTheEndOfTheYear() {
		TemporalApplicability applicability = monthDayRange(MonthDay.of(DECEMBER, 31), MonthDay.of(JANUARY, 1)); // New Year's Eve and New Year's Day

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 31)),
				singletonSet(LocalDate.of(2014, JANUARY, 1)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, DECEMBER, 1), LocalDate.of(2014, DECEMBER, 31)),
				singletonSet(LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 31)),
				singletonSet(LocalDate.of(2015, JANUARY, 1)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, DECEMBER, 1), LocalDate.of(2015, DECEMBER, 31)),
				singletonSet(LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JANUARY, 31)),
				singletonSet(LocalDate.of(2016, JANUARY, 1)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, DECEMBER, 1), LocalDate.of(2016, DECEMBER, 31)),
				singletonSet(LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testMonthRange_coveringWholeRange() {
		TemporalApplicability applicability = monthRange(JANUARY, DECEMBER); // Calendar year

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testMonthRange_notWrappingAroundTheEndOfTheYear() {
		TemporalApplicability applicability = monthRange(JUNE, JULY); // The Championships in Wimbledon takes place

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, MAY, 1), LocalDate.of(2014, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, JUNE, 1), LocalDate.of(2014, JULY, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, MAY, 1), LocalDate.of(2015, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, JUNE, 1), LocalDate.of(2015, JULY, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, MAY, 1), LocalDate.of(2016, AUGUST, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, JUNE, 1), LocalDate.of(2016, JULY, 31)));
	}

	@Test
	public void testMonthRange_same() {
		TemporalApplicability applicability = monthRange(JUNE, JUNE); // Glastonbury Festival takes place

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, MAY, 1), LocalDate.of(2014, JULY, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, JUNE, 1), LocalDate.of(2014, JUNE, 30)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, MAY, 1), LocalDate.of(2015, JULY, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, JUNE, 1), LocalDate.of(2015, JUNE, 30)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, MAY, 1), LocalDate.of(2016, JULY, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, JUNE, 1), LocalDate.of(2016, JUNE, 30)));
	}

	@Test
	public void testMonthRange_wrappingAroundTheEndOfTheYear() {
		TemporalApplicability applicability = monthRange(AUGUST, MAY); // Premier League season

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, MAY, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JULY, 1), LocalDate.of(2014, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, AUGUST, 1), LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, MAY, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JULY, 1), LocalDate.of(2015, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 1), LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JUNE, 30)),
				dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, MAY, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JULY, 1), LocalDate.of(2016, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 1), LocalDate.of(2016, DECEMBER, 31)));
	}

	@Test
	public void testOddIsoWeek() {
		TemporalApplicability applicability = oddIsoWeek();

		Set<LocalDate> oddIsoWeekDaysIn2014 = new HashSet<>();
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 5)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 13), LocalDate.of(2014, JANUARY, 19)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 27), LocalDate.of(2014, FEBRUARY, 2)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, FEBRUARY, 10), LocalDate.of(2014, FEBRUARY, 16)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, FEBRUARY, 24), LocalDate.of(2014, MARCH, 2)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MARCH, 10), LocalDate.of(2014, MARCH, 16)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MARCH, 24), LocalDate.of(2014, MARCH, 30)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, APRIL, 7), LocalDate.of(2014, APRIL, 13)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, APRIL, 21), LocalDate.of(2014, APRIL, 27)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MAY, 5), LocalDate.of(2014, MAY, 11)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, MAY, 19), LocalDate.of(2014, MAY, 25)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JUNE, 2), LocalDate.of(2014, JUNE, 8)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JUNE, 16), LocalDate.of(2014, JUNE, 22)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JUNE, 30), LocalDate.of(2014, JULY, 6)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JULY, 14), LocalDate.of(2014, JULY, 20)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, JULY, 28), LocalDate.of(2014, AUGUST, 3)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, AUGUST, 11), LocalDate.of(2014, AUGUST, 17)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, AUGUST, 25), LocalDate.of(2014, AUGUST, 31)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, SEPTEMBER, 8), LocalDate.of(2014, SEPTEMBER, 14)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, SEPTEMBER, 22), LocalDate.of(2014, SEPTEMBER, 28)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, OCTOBER, 6), LocalDate.of(2014, OCTOBER, 12)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, OCTOBER, 20), LocalDate.of(2014, OCTOBER, 26)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, NOVEMBER, 3), LocalDate.of(2014, NOVEMBER, 9)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, NOVEMBER, 17), LocalDate.of(2014, NOVEMBER, 23)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 1), LocalDate.of(2014, DECEMBER, 7)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 15), LocalDate.of(2014, DECEMBER, 21)));
		oddIsoWeekDaysIn2014.addAll(dateRangeNavigableSet(LocalDate.of(2014, DECEMBER, 29), LocalDate.of(2014, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				oddIsoWeekDaysIn2014);

		Set<LocalDate> oddIsoWeekDaysIn2015 = new HashSet<>();
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 4)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 12), LocalDate.of(2015, JANUARY, 18)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 26), LocalDate.of(2015, FEBRUARY, 1)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, FEBRUARY, 9), LocalDate.of(2015, FEBRUARY, 15)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, FEBRUARY, 23), LocalDate.of(2015, MARCH, 1)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MARCH, 9), LocalDate.of(2015, MARCH, 15)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MARCH, 23), LocalDate.of(2015, MARCH, 29)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, APRIL, 6), LocalDate.of(2015, APRIL, 12)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, APRIL, 20), LocalDate.of(2015, APRIL, 26)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MAY, 4), LocalDate.of(2015, MAY, 10)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, MAY, 18), LocalDate.of(2015, MAY, 24)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JUNE, 1), LocalDate.of(2015, JUNE, 7)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JUNE, 15), LocalDate.of(2015, JUNE, 21)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JUNE, 29), LocalDate.of(2015, JULY, 5)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JULY, 13), LocalDate.of(2015, JULY, 19)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, JULY, 27), LocalDate.of(2015, AUGUST, 2)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 10), LocalDate.of(2015, AUGUST, 16)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, AUGUST, 24), LocalDate.of(2015, AUGUST, 30)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, SEPTEMBER, 7), LocalDate.of(2015, SEPTEMBER, 13)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, SEPTEMBER, 21), LocalDate.of(2015, SEPTEMBER, 27)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, OCTOBER, 5), LocalDate.of(2015, OCTOBER, 11)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, OCTOBER, 19), LocalDate.of(2015, OCTOBER, 25)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, NOVEMBER, 2), LocalDate.of(2015, NOVEMBER, 8)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, NOVEMBER, 16), LocalDate.of(2015, NOVEMBER, 22)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, NOVEMBER, 30), LocalDate.of(2015, DECEMBER, 6)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 14), LocalDate.of(2015, DECEMBER, 20)));
		oddIsoWeekDaysIn2015.addAll(dateRangeNavigableSet(LocalDate.of(2015, DECEMBER, 28), LocalDate.of(2015, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				oddIsoWeekDaysIn2015);

		Set<LocalDate> oddIsoWeekDaysIn2016 = new HashSet<>();
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JANUARY, 3)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 4), LocalDate.of(2016, JANUARY, 10)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 18), LocalDate.of(2016, JANUARY, 24)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 1), LocalDate.of(2016, FEBRUARY, 7)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 15), LocalDate.of(2016, FEBRUARY, 21)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, FEBRUARY, 29), LocalDate.of(2016, MARCH, 6)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MARCH, 14), LocalDate.of(2016, MARCH, 20)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MARCH, 28), LocalDate.of(2016, APRIL, 3)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, APRIL, 11), LocalDate.of(2016, APRIL, 17)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, APRIL, 25), LocalDate.of(2016, MAY, 1)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MAY, 9), LocalDate.of(2016, MAY, 15)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, MAY, 23), LocalDate.of(2016, MAY, 29)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JUNE, 6), LocalDate.of(2016, JUNE, 12)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JUNE, 20), LocalDate.of(2016, JUNE, 26)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JULY, 4), LocalDate.of(2016, JULY, 10)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, JULY, 18), LocalDate.of(2016, JULY, 24)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 1), LocalDate.of(2016, AUGUST, 7)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 15), LocalDate.of(2016, AUGUST, 21)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, AUGUST, 29), LocalDate.of(2016, SEPTEMBER, 4)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, SEPTEMBER, 12), LocalDate.of(2016, SEPTEMBER, 18)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, SEPTEMBER, 26), LocalDate.of(2016, OCTOBER, 2)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, OCTOBER, 10), LocalDate.of(2016, OCTOBER, 16)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, OCTOBER, 24), LocalDate.of(2016, OCTOBER, 30)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, NOVEMBER, 7), LocalDate.of(2016, NOVEMBER, 13)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, NOVEMBER, 21), LocalDate.of(2016, NOVEMBER, 27)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 5), LocalDate.of(2016, DECEMBER, 11)));
		oddIsoWeekDaysIn2016.addAll(dateRangeNavigableSet(LocalDate.of(2016, DECEMBER, 19), LocalDate.of(2016, DECEMBER, 25)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				oddIsoWeekDaysIn2016);
	}

	@Test
	public void testSingleDate() {
		TemporalApplicability applicability = singleDate(LocalDate.of(1863, JANUARY, 10)); // London Underground began operation

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1862, JANUARY, 1), LocalDate.of(1862, JANUARY, 31)),
				emptySet());
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1863, JANUARY, 1), LocalDate.of(1863, JANUARY, 31)),
				singletonSet(LocalDate.of(1863, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1864, JANUARY, 1), LocalDate.of(1864, JANUARY, 31)),
				emptySet());
	}

	@Test
	public void testSingleDayOfWeek() {
		TemporalApplicability applicability = singleDayOfWeek(FRIDAY); // Begin of Jewish Sabbath

		Set<LocalDate> fridaysInOctober2014 = new HashSet<>();
		fridaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 3));
		fridaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 10));
		fridaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 17));
		fridaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 24));
		fridaysInOctober2014.add(LocalDate.of(2014, OCTOBER, 31));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, OCTOBER, 1), LocalDate.of(2014, OCTOBER, 31)),
				fridaysInOctober2014);

		Set<LocalDate> fridaysInOctober2015 = new HashSet<>();
		fridaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 2));
		fridaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 9));
		fridaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 16));
		fridaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 23));
		fridaysInOctober2015.add(LocalDate.of(2015, OCTOBER, 30));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, OCTOBER, 1), LocalDate.of(2015, OCTOBER, 31)),
				fridaysInOctober2015);

		Set<LocalDate> fridaysInOctober2016 = new HashSet<>();
		fridaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 7));
		fridaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 14));
		fridaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 21));
		fridaysInOctober2016.add(LocalDate.of(2016, OCTOBER, 28));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, OCTOBER, 1), LocalDate.of(2016, OCTOBER, 31)),
				fridaysInOctober2016);
	}

	@Test
	public void testSingleIsoWeek() {
		TemporalApplicability applicability = singleIsoWeek(1);

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2000, JANUARY, 1), LocalDate.of(2000, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2000, JANUARY, 3), LocalDate.of(2000, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2001, JANUARY, 1), LocalDate.of(2001, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2001, JANUARY, 1), LocalDate.of(2001, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2002, JANUARY, 1), LocalDate.of(2002, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2002, JANUARY, 1), LocalDate.of(2002, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2003, JANUARY, 1), LocalDate.of(2003, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2003, JANUARY, 1), LocalDate.of(2003, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2004, JANUARY, 1), LocalDate.of(2004, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2004, JANUARY, 1), LocalDate.of(2004, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2005, JANUARY, 1), LocalDate.of(2005, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2005, JANUARY, 3), LocalDate.of(2005, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2006, JANUARY, 1), LocalDate.of(2006, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2006, JANUARY, 2), LocalDate.of(2006, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2007, JANUARY, 1), LocalDate.of(2007, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2007, JANUARY, 1), LocalDate.of(2007, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2008, JANUARY, 1), LocalDate.of(2008, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2008, JANUARY, 1), LocalDate.of(2008, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2009, JANUARY, 1), LocalDate.of(2009, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2009, JANUARY, 1), LocalDate.of(2009, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2010, JANUARY, 1), LocalDate.of(2010, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2010, JANUARY, 4), LocalDate.of(2010, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2011, JANUARY, 1), LocalDate.of(2011, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2011, JANUARY, 3), LocalDate.of(2011, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2012, JANUARY, 1), LocalDate.of(2012, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2012, JANUARY, 2), LocalDate.of(2012, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2013, JANUARY, 1), LocalDate.of(2013, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2013, JANUARY, 1), LocalDate.of(2013, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, JANUARY, 4), LocalDate.of(2016, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2017, JANUARY, 1), LocalDate.of(2017, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2017, JANUARY, 2), LocalDate.of(2017, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2019, JANUARY, 1), LocalDate.of(2019, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2019, JANUARY, 1), LocalDate.of(2019, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2020, JANUARY, 1), LocalDate.of(2020, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2020, JANUARY, 1), LocalDate.of(2020, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2021, JANUARY, 1), LocalDate.of(2021, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2021, JANUARY, 4), LocalDate.of(2021, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2022, JANUARY, 1), LocalDate.of(2022, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2022, JANUARY, 3), LocalDate.of(2022, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2023, JANUARY, 1), LocalDate.of(2023, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2023, JANUARY, 2), LocalDate.of(2023, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2024, JANUARY, 1), LocalDate.of(2024, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2024, JANUARY, 1), LocalDate.of(2024, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2025, JANUARY, 1), LocalDate.of(2025, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2025, JANUARY, 1), LocalDate.of(2025, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2026, JANUARY, 1), LocalDate.of(2026, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2026, JANUARY, 1), LocalDate.of(2026, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2027, JANUARY, 1), LocalDate.of(2027, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2027, JANUARY, 4), LocalDate.of(2027, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2028, JANUARY, 1), LocalDate.of(2028, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2028, JANUARY, 3), LocalDate.of(2028, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2029, JANUARY, 1), LocalDate.of(2029, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2029, JANUARY, 1), LocalDate.of(2029, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2030, JANUARY, 1), LocalDate.of(2030, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2030, JANUARY, 1), LocalDate.of(2030, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2031, JANUARY, 1), LocalDate.of(2031, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2031, JANUARY, 1), LocalDate.of(2031, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2032, JANUARY, 1), LocalDate.of(2032, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2032, JANUARY, 1), LocalDate.of(2032, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2033, JANUARY, 1), LocalDate.of(2033, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2033, JANUARY, 3), LocalDate.of(2033, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2034, JANUARY, 1), LocalDate.of(2034, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2034, JANUARY, 2), LocalDate.of(2034, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2035, JANUARY, 1), LocalDate.of(2035, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2035, JANUARY, 1), LocalDate.of(2035, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2036, JANUARY, 1), LocalDate.of(2036, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2036, JANUARY, 1), LocalDate.of(2036, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2037, JANUARY, 1), LocalDate.of(2037, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2037, JANUARY, 1), LocalDate.of(2037, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2038, JANUARY, 1), LocalDate.of(2038, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2038, JANUARY, 4), LocalDate.of(2038, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2039, JANUARY, 1), LocalDate.of(2039, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2039, JANUARY, 3), LocalDate.of(2039, JANUARY, 9)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2040, JANUARY, 1), LocalDate.of(2040, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2040, JANUARY, 2), LocalDate.of(2040, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2041, JANUARY, 1), LocalDate.of(2041, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2041, JANUARY, 1), LocalDate.of(2041, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2042, JANUARY, 1), LocalDate.of(2042, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2042, JANUARY, 1), LocalDate.of(2042, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2043, JANUARY, 1), LocalDate.of(2043, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2043, JANUARY, 1), LocalDate.of(2043, JANUARY, 4)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2044, JANUARY, 1), LocalDate.of(2044, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2044, JANUARY, 4), LocalDate.of(2044, JANUARY, 10)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2045, JANUARY, 1), LocalDate.of(2045, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2045, JANUARY, 2), LocalDate.of(2045, JANUARY, 8)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2046, JANUARY, 1), LocalDate.of(2046, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2046, JANUARY, 1), LocalDate.of(2046, JANUARY, 7)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2047, JANUARY, 1), LocalDate.of(2047, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2047, JANUARY, 1), LocalDate.of(2047, JANUARY, 6)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2048, JANUARY, 1), LocalDate.of(2048, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2048, JANUARY, 1), LocalDate.of(2048, JANUARY, 5)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2049, JANUARY, 1), LocalDate.of(2049, JANUARY, 31)),
				dateRangeNavigableSet(LocalDate.of(2049, JANUARY, 4), LocalDate.of(2049, JANUARY, 10)));
	}

	@Test
	public void testSingleMonth() {
		TemporalApplicability applicability = singleMonth(APRIL); // London Marathon takes place

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, MARCH, 1), LocalDate.of(2014, MAY, 31)),
				dateRangeNavigableSet(LocalDate.of(2014, APRIL, 1), LocalDate.of(2014, APRIL, 30)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, MARCH, 1), LocalDate.of(2015, MAY, 31)),
				dateRangeNavigableSet(LocalDate.of(2015, APRIL, 1), LocalDate.of(2015, APRIL, 30)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, MARCH, 1), LocalDate.of(2016, MAY, 31)),
				dateRangeNavigableSet(LocalDate.of(2016, APRIL, 1), LocalDate.of(2016, APRIL, 30)));
	}

	@Test
	public void testSingleMonthDay() {
		TemporalApplicability applicability = singleMonthDay(MonthDay.of(APRIL, 23)); // St George's Day (Patron saint of England)

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2014, APRIL, 1), LocalDate.of(2014, APRIL, 30)),
				singletonSet(LocalDate.of(2014, APRIL, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2015, APRIL, 1), LocalDate.of(2015, APRIL, 30)),
				singletonSet(LocalDate.of(2015, APRIL, 23)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2016, APRIL, 1), LocalDate.of(2016, APRIL, 30)),
				singletonSet(LocalDate.of(2016, APRIL, 23)));
	}

	@Test
	public void testSingleYear() {
		TemporalApplicability applicability = singleYear(2000); // Formation of Transport for London

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1999, JANUARY, 1), LocalDate.of(1999, DECEMBER, 31)),
				emptySet());
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2000, JANUARY, 1), LocalDate.of(2000, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(2000, JANUARY, 1), LocalDate.of(2000, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(2001, JANUARY, 1), LocalDate.of(2001, DECEMBER, 31)),
				emptySet());
	}

	@Test
	public void testYearRange_notSame() {
		TemporalApplicability applicability = yearRange(1851, 1852); // Construction of London King's Cross railway station

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1850, JANUARY, 1), LocalDate.of(1850, DECEMBER, 31)),
				emptySet());
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1851, JANUARY, 1), LocalDate.of(1851, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(1851, JANUARY, 1), LocalDate.of(1851, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1852, JANUARY, 1), LocalDate.of(1852, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(1852, JANUARY, 1), LocalDate.of(1852, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1853, JANUARY, 1), LocalDate.of(1853, DECEMBER, 31)),
				emptySet());
	}

	@Test
	public void testYearRange_same() {
		TemporalApplicability applicability = yearRange(1852, 1852); // Official opening of London King's Cross railway station

		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1851, JANUARY, 1), LocalDate.of(1851, DECEMBER, 31)),
				emptySet());
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1852, JANUARY, 1), LocalDate.of(1852, DECEMBER, 31)),
				dateRangeNavigableSet(LocalDate.of(1852, JANUARY, 1), LocalDate.of(1852, DECEMBER, 31)));
		testTemporalPredicate(applicability,
				dateRangeStream(LocalDate.of(1853, JANUARY, 1), LocalDate.of(1853, DECEMBER, 31)),
				emptySet());
	}

}
