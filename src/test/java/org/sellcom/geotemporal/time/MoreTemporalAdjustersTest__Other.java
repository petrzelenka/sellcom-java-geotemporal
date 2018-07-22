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
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.CZECH_REPUBLIC;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.bussUndBettag;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.corpusChristi;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.dayOfWeekFollowing;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.dayOfWeekPreceding;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.next;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.nextLeapYear;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.nextOrSame;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.nextOrSameLeapYear;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.nextOrSameWorkingDay;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.nextWorkingDay;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.previous;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.previousLeapYear;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.previousOrSame;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.previousOrSameLeapYear;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.previousOrSameWorkingDay;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.previousWorkingDay;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.storeBededag;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.whitSunday;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

import org.junit.Test;
import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.internal.time.TemporalAdjusterUtils;

public class MoreTemporalAdjustersTest__Other {

	@Test
	public void testBussUndBettag() {
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2000, bussUndBettag()), is(equalTo(LocalDate.of(2000, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2001, bussUndBettag()), is(equalTo(LocalDate.of(2001, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2002, bussUndBettag()), is(equalTo(LocalDate.of(2002, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2003, bussUndBettag()), is(equalTo(LocalDate.of(2003, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2004, bussUndBettag()), is(equalTo(LocalDate.of(2004, NOVEMBER, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2005, bussUndBettag()), is(equalTo(LocalDate.of(2005, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2006, bussUndBettag()), is(equalTo(LocalDate.of(2006, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2007, bussUndBettag()), is(equalTo(LocalDate.of(2007, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2008, bussUndBettag()), is(equalTo(LocalDate.of(2008, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2009, bussUndBettag()), is(equalTo(LocalDate.of(2009, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2010, bussUndBettag()), is(equalTo(LocalDate.of(2010, NOVEMBER, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2011, bussUndBettag()), is(equalTo(LocalDate.of(2011, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2012, bussUndBettag()), is(equalTo(LocalDate.of(2012, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2013, bussUndBettag()), is(equalTo(LocalDate.of(2013, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2014, bussUndBettag()), is(equalTo(LocalDate.of(2014, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2015, bussUndBettag()), is(equalTo(LocalDate.of(2015, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2016, bussUndBettag()), is(equalTo(LocalDate.of(2016, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2017, bussUndBettag()), is(equalTo(LocalDate.of(2017, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2018, bussUndBettag()), is(equalTo(LocalDate.of(2018, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2019, bussUndBettag()), is(equalTo(LocalDate.of(2019, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2020, bussUndBettag()), is(equalTo(LocalDate.of(2020, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2021, bussUndBettag()), is(equalTo(LocalDate.of(2021, NOVEMBER, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2022, bussUndBettag()), is(equalTo(LocalDate.of(2022, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2023, bussUndBettag()), is(equalTo(LocalDate.of(2023, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2024, bussUndBettag()), is(equalTo(LocalDate.of(2024, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2025, bussUndBettag()), is(equalTo(LocalDate.of(2025, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2026, bussUndBettag()), is(equalTo(LocalDate.of(2026, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2027, bussUndBettag()), is(equalTo(LocalDate.of(2027, NOVEMBER, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2028, bussUndBettag()), is(equalTo(LocalDate.of(2028, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2029, bussUndBettag()), is(equalTo(LocalDate.of(2029, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2030, bussUndBettag()), is(equalTo(LocalDate.of(2030, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2031, bussUndBettag()), is(equalTo(LocalDate.of(2031, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2032, bussUndBettag()), is(equalTo(LocalDate.of(2032, NOVEMBER, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2033, bussUndBettag()), is(equalTo(LocalDate.of(2033, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2034, bussUndBettag()), is(equalTo(LocalDate.of(2034, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2035, bussUndBettag()), is(equalTo(LocalDate.of(2035, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2036, bussUndBettag()), is(equalTo(LocalDate.of(2036, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2037, bussUndBettag()), is(equalTo(LocalDate.of(2037, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2038, bussUndBettag()), is(equalTo(LocalDate.of(2038, NOVEMBER, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2039, bussUndBettag()), is(equalTo(LocalDate.of(2039, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2040, bussUndBettag()), is(equalTo(LocalDate.of(2040, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2041, bussUndBettag()), is(equalTo(LocalDate.of(2041, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2042, bussUndBettag()), is(equalTo(LocalDate.of(2042, NOVEMBER, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2043, bussUndBettag()), is(equalTo(LocalDate.of(2043, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2044, bussUndBettag()), is(equalTo(LocalDate.of(2044, NOVEMBER, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2045, bussUndBettag()), is(equalTo(LocalDate.of(2045, NOVEMBER, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2046, bussUndBettag()), is(equalTo(LocalDate.of(2046, NOVEMBER, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2047, bussUndBettag()), is(equalTo(LocalDate.of(2047, NOVEMBER, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2048, bussUndBettag()), is(equalTo(LocalDate.of(2048, NOVEMBER, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2049, bussUndBettag()), is(equalTo(LocalDate.of(2049, NOVEMBER, 17))));
	}

	@Test
	public void testDayOfWeekFollowing() {
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2000, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2000, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2001, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2001, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2002, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2002, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2003, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2003, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2004, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2004, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2005, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2005, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2006, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2006, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2007, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2007, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2008, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2008, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2009, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2009, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2010, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2010, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2011, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2011, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2012, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2012, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2013, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2013, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2014, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2014, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2015, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2015, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2016, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2016, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2017, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2017, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2018, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2018, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2019, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2019, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2020, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2020, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2021, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2021, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2022, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2022, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2023, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2023, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2024, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2024, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2025, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2025, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2026, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2026, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2027, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2027, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2028, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2028, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2029, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2029, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2030, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2030, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2031, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2031, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2032, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2032, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2033, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2033, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2034, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2034, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2035, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2035, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2036, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2036, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2037, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2037, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2038, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2038, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2039, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2039, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2040, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2040, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2041, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2041, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2042, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2042, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2043, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2043, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2044, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2044, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2045, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2045, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2046, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2046, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2047, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2047, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2048, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2048, corpusChristi()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2049, whitSunday()).with(dayOfWeekFollowing(2, THURSDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2049, corpusChristi()))));
	}

	@Test
	public void testDayOfWeekPreceding() {
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2000, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2000, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2001, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2001, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2002, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2002, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2003, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2003, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2004, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2004, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2005, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2005, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2006, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2006, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2007, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2007, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2008, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2008, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2009, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2009, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2010, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2010, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2011, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2011, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2012, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2012, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2013, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2013, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2014, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2014, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2015, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2015, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2016, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2016, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2017, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2017, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2018, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2018, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2019, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2019, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2020, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2020, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2021, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2021, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2022, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2022, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2023, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2023, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2024, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2024, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2025, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2025, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2026, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2026, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2027, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2027, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2028, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2028, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2029, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2029, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2030, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2030, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2031, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2031, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2032, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2032, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2033, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2033, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2034, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2034, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2035, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2035, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2036, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2036, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2037, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2037, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2038, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2038, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2039, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2039, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2040, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2040, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2041, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2041, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2042, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2042, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2043, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2043, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2044, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2044, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2045, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2045, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2046, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2046, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2047, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2047, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2048, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2048, whitSunday()))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2049, corpusChristi()).with(dayOfWeekPreceding(2, SUNDAY)), is(equalTo(TemporalAdjusterUtils.applyTemporalAdjuster(2049, whitSunday()))));
	}

	@Test
	public void testNext_monthDay() { // Saint George's Day in England
		assertThat(LocalDate.of(2012, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2013, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2014, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2015, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2016, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2017, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2018, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2019, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2020, JANUARY, 1).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));

		assertThat(LocalDate.of(2012, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2013, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2014, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2015, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2016, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2017, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2018, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2019, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));
		assertThat(LocalDate.of(2020, APRIL, 23).with(next(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2021, APRIL, 23))));
	}

	@Test
	public void testNext_monthDay_february29() {
		assertThat(LocalDate.of(2012, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2013, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2014, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2015, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2017, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2018, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2019, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, JANUARY, 1).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));

		assertThat(LocalDate.of(2012, FEBRUARY, 29).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, FEBRUARY, 29).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, FEBRUARY, 29).with(next(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2024, FEBRUARY, 29))));
	}

	@Test
	public void testNextLeapYear() {
		assertThat(Year.of(2000).with(nextLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2001).with(nextLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2002).with(nextLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2003).with(nextLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2004).with(nextLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2005).with(nextLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2006).with(nextLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2007).with(nextLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2008).with(nextLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2009).with(nextLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2010).with(nextLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2011).with(nextLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2012).with(nextLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2013).with(nextLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2014).with(nextLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2015).with(nextLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2016).with(nextLeapYear()), is(equalTo(Year.of(2020))));
	}

	@Test
	public void testNextOrSame_monthDay() { // Saint George's Day in England
		assertThat(LocalDate.of(2012, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2013, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2014, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2015, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2016, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2017, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2018, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2019, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2020, JANUARY, 1).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));

		assertThat(LocalDate.of(2012, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2013, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2014, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2015, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2016, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2017, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2018, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2019, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2020, APRIL, 23).with(nextOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));
	}

	@Test
	public void testNextOrSame_monthDay_february29() {
		assertThat(LocalDate.of(2012, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2013, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2014, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2015, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2017, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2018, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2019, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, JANUARY, 1).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));

		assertThat(LocalDate.of(2012, FEBRUARY, 29).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, FEBRUARY, 29).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, FEBRUARY, 29).with(nextOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
	}

	@Test
	public void testNextOrSameLeapYear() {
		assertThat(Year.of(2000).with(nextOrSameLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2001).with(nextOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2002).with(nextOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2003).with(nextOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2004).with(nextOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2005).with(nextOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2006).with(nextOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2007).with(nextOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2008).with(nextOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2009).with(nextOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2010).with(nextOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2011).with(nextOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2012).with(nextOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2013).with(nextOrSameLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2014).with(nextOrSameLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2015).with(nextOrSameLeapYear()), is(equalTo(Year.of(2016))));
		assertThat(Year.of(2016).with(nextOrSameLeapYear()), is(equalTo(Year.of(2016))));
	}

	@Test
	public void testNextOrSameWorkingDay() {
		GeoRegion region = GeoRegion.parse(CZECH_REPUBLIC);

		assertThat(LocalDate.of(2016, JULY, 1).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 2).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 3).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 4).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 5).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 6).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 7).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 8).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 8))));
		assertThat(LocalDate.of(2016, JULY, 9).with(nextOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 11))));
	}

	@Test
	public void testNextWorkingDay() {
		GeoRegion region = GeoRegion.parse(CZECH_REPUBLIC);

		assertThat(LocalDate.of(2016, JULY, 1).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 2).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 3).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 4).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 5).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 6).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 7).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 8))));
		assertThat(LocalDate.of(2016, JULY, 8).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 11))));
		assertThat(LocalDate.of(2016, JULY, 9).with(nextWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 11))));
	}

	@Test
	public void testPrevious_monthDay() { // Saint George's Day in England
		assertThat(LocalDate.of(2012, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2013, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2014, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2015, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2016, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2017, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2018, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2019, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2020, DECEMBER, 31).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));

		assertThat(LocalDate.of(2012, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2011, APRIL, 23))));
		assertThat(LocalDate.of(2013, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2014, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2015, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2016, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2017, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2018, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2019, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2020, APRIL, 23).with(previous(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
	}

	@Test
	public void testPrevious_monthDay_february29() {
		assertThat(LocalDate.of(2012, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2013, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2014, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2015, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2017, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2018, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2019, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, DECEMBER, 31).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));

		assertThat(LocalDate.of(2012, FEBRUARY, 29).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2008, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, FEBRUARY, 29).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, FEBRUARY, 29).with(previous(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
	}

	@Test
	public void testPreviousLeapYear() {
		assertThat(Year.of(2000).with(previousLeapYear()), is(equalTo(Year.of(1996))));
		assertThat(Year.of(2001).with(previousLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2002).with(previousLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2003).with(previousLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2004).with(previousLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2005).with(previousLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2006).with(previousLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2007).with(previousLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2008).with(previousLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2009).with(previousLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2010).with(previousLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2011).with(previousLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2012).with(previousLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2013).with(previousLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2014).with(previousLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2015).with(previousLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2016).with(previousLeapYear()), is(equalTo(Year.of(2012))));
	}

	@Test
	public void testPreviousOrSame_monthDay() { // Saint George's Day in England
		assertThat(LocalDate.of(2012, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2013, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2014, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2015, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2016, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2017, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2018, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2019, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2020, DECEMBER, 31).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));

		assertThat(LocalDate.of(2012, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2012, APRIL, 23))));
		assertThat(LocalDate.of(2013, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2013, APRIL, 23))));
		assertThat(LocalDate.of(2014, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2014, APRIL, 23))));
		assertThat(LocalDate.of(2015, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2015, APRIL, 23))));
		assertThat(LocalDate.of(2016, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2016, APRIL, 23))));
		assertThat(LocalDate.of(2017, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2017, APRIL, 23))));
		assertThat(LocalDate.of(2018, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2018, APRIL, 23))));
		assertThat(LocalDate.of(2019, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2019, APRIL, 23))));
		assertThat(LocalDate.of(2020, APRIL, 23).with(previousOrSame(MonthDay.of(APRIL, 23))), is(equalTo(LocalDate.of(2020, APRIL, 23))));
	}

	@Test
	public void testPreviousOrSame_monthDay_feburary29() {
		assertThat(LocalDate.of(2012, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2013, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2014, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2015, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2017, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2018, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2019, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, DECEMBER, 31).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));

		assertThat(LocalDate.of(2012, FEBRUARY, 29).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2012, FEBRUARY, 29))));
		assertThat(LocalDate.of(2016, FEBRUARY, 29).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2016, FEBRUARY, 29))));
		assertThat(LocalDate.of(2020, FEBRUARY, 29).with(previousOrSame(MonthDay.of(FEBRUARY, 29))), is(equalTo(LocalDate.of(2020, FEBRUARY, 29))));
	}

	@Test
	public void testPreviousOrSameLeapYear() {
		assertThat(Year.of(2000).with(previousOrSameLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2001).with(previousOrSameLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2002).with(previousOrSameLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2003).with(previousOrSameLeapYear()), is(equalTo(Year.of(2000))));
		assertThat(Year.of(2004).with(previousOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2005).with(previousOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2006).with(previousOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2007).with(previousOrSameLeapYear()), is(equalTo(Year.of(2004))));
		assertThat(Year.of(2008).with(previousOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2009).with(previousOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2010).with(previousOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2011).with(previousOrSameLeapYear()), is(equalTo(Year.of(2008))));
		assertThat(Year.of(2012).with(previousOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2013).with(previousOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2014).with(previousOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2015).with(previousOrSameLeapYear()), is(equalTo(Year.of(2012))));
		assertThat(Year.of(2016).with(previousOrSameLeapYear()), is(equalTo(Year.of(2016))));
	}

	@Test
	public void testPreviousOrSameWorkingDay() {
		GeoRegion region = GeoRegion.parse(CZECH_REPUBLIC);

		assertThat(LocalDate.of(2016, JULY, 1).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 2).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 3).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 4).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 5).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 6).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 7).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 8).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 8))));
		assertThat(LocalDate.of(2016, JULY, 9).with(previousOrSameWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 8))));
	}

	@Test
	public void testPreviousWorkingDay() {
		GeoRegion region = GeoRegion.parse(CZECH_REPUBLIC);

		assertThat(LocalDate.of(2016, JULY, 1).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JUNE, 30))));
		assertThat(LocalDate.of(2016, JULY, 2).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 3).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 4).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 1))));
		assertThat(LocalDate.of(2016, JULY, 5).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 6).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 7).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 4))));
		assertThat(LocalDate.of(2016, JULY, 8).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 7))));
		assertThat(LocalDate.of(2016, JULY, 9).with(previousWorkingDay(region)), is(equalTo(LocalDate.of(2016, JULY, 8))));
	}

	@Test
	public void testStoreBededag() {
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2000, storeBededag()), is(equalTo(LocalDate.of(2000, MAY, 19))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2001, storeBededag()), is(equalTo(LocalDate.of(2001, MAY, 11))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2002, storeBededag()), is(equalTo(LocalDate.of(2002, APRIL, 26))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2003, storeBededag()), is(equalTo(LocalDate.of(2003, MAY, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2004, storeBededag()), is(equalTo(LocalDate.of(2004, MAY, 7))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2005, storeBededag()), is(equalTo(LocalDate.of(2005, APRIL, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2006, storeBededag()), is(equalTo(LocalDate.of(2006, MAY, 12))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2007, storeBededag()), is(equalTo(LocalDate.of(2007, MAY, 4))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2008, storeBededag()), is(equalTo(LocalDate.of(2008, APRIL, 18))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2009, storeBededag()), is(equalTo(LocalDate.of(2009, MAY, 8))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2010, storeBededag()), is(equalTo(LocalDate.of(2010, APRIL, 30))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2011, storeBededag()), is(equalTo(LocalDate.of(2011, MAY, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2012, storeBededag()), is(equalTo(LocalDate.of(2012, MAY, 4))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2013, storeBededag()), is(equalTo(LocalDate.of(2013, APRIL, 26))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2014, storeBededag()), is(equalTo(LocalDate.of(2014, MAY, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2015, storeBededag()), is(equalTo(LocalDate.of(2015, MAY, 1))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2016, storeBededag()), is(equalTo(LocalDate.of(2016, APRIL, 22))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2017, storeBededag()), is(equalTo(LocalDate.of(2017, MAY, 12))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2018, storeBededag()), is(equalTo(LocalDate.of(2018, APRIL, 27))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2019, storeBededag()), is(equalTo(LocalDate.of(2019, MAY, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2020, storeBededag()), is(equalTo(LocalDate.of(2020, MAY, 8))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2021, storeBededag()), is(equalTo(LocalDate.of(2021, APRIL, 30))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2022, storeBededag()), is(equalTo(LocalDate.of(2022, MAY, 13))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2023, storeBededag()), is(equalTo(LocalDate.of(2023, MAY, 5))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2024, storeBededag()), is(equalTo(LocalDate.of(2024, APRIL, 26))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2025, storeBededag()), is(equalTo(LocalDate.of(2025, MAY, 16))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2026, storeBededag()), is(equalTo(LocalDate.of(2026, MAY, 1))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2027, storeBededag()), is(equalTo(LocalDate.of(2027, APRIL, 23))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2028, storeBededag()), is(equalTo(LocalDate.of(2028, MAY, 12))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2029, storeBededag()), is(equalTo(LocalDate.of(2029, APRIL, 27))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2030, storeBededag()), is(equalTo(LocalDate.of(2030, MAY, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2031, storeBededag()), is(equalTo(LocalDate.of(2031, MAY, 9))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2032, storeBededag()), is(equalTo(LocalDate.of(2032, APRIL, 23))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2033, storeBededag()), is(equalTo(LocalDate.of(2033, MAY, 13))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2034, storeBededag()), is(equalTo(LocalDate.of(2034, MAY, 5))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2035, storeBededag()), is(equalTo(LocalDate.of(2035, APRIL, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2036, storeBededag()), is(equalTo(LocalDate.of(2036, MAY, 9))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2037, storeBededag()), is(equalTo(LocalDate.of(2037, MAY, 1))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2038, storeBededag()), is(equalTo(LocalDate.of(2038, MAY, 21))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2039, storeBededag()), is(equalTo(LocalDate.of(2039, MAY, 6))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2040, storeBededag()), is(equalTo(LocalDate.of(2040, APRIL, 27))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2041, storeBededag()), is(equalTo(LocalDate.of(2041, MAY, 17))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2042, storeBededag()), is(equalTo(LocalDate.of(2042, MAY, 2))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2043, storeBededag()), is(equalTo(LocalDate.of(2043, APRIL, 24))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2044, storeBededag()), is(equalTo(LocalDate.of(2044, MAY, 13))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2045, storeBededag()), is(equalTo(LocalDate.of(2045, MAY, 5))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2046, storeBededag()), is(equalTo(LocalDate.of(2046, APRIL, 20))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2047, storeBededag()), is(equalTo(LocalDate.of(2047, MAY, 10))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2048, storeBededag()), is(equalTo(LocalDate.of(2048, MAY, 1))));
		assertThat(TemporalAdjusterUtils.applyTemporalAdjuster(2049, storeBededag()), is(equalTo(LocalDate.of(2049, MAY, 14))));
	}

}
