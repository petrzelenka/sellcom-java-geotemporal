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
import static org.sellcom.geotemporal.geography.StandardGeoRegions.ANDORRA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.AUSTRIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.BELGIUM;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.CROATIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.CYPRUS;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.CZECH_REPUBLIC;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.DENMARK;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.ESTONIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.FAROE_ISLANDS;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.FINLAND;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.FRANCE;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.GEORGIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.GERMANY;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.GREENLAND;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.HUNGARY;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.ICELAND;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.ISLE_OF_MAN;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.ITALY;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.JERSEY;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.LATVIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.LIECHTENSTEIN;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.MALTA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.MOLDOVA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.NETHERLANDS;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.NORWAY;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.POLAND;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.PORTUGAL;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.REPUBLIC_OF_IRELAND;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.ROMANIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.SERBIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.SLOVAKIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.SLOVENIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.SWEDEN;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.SWITZERLAND;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.VATICAN_CITY;
import static org.sellcom.geotemporal.internal.time.HolidayPredicateUtils.testHolidayPredicate;
import static org.sellcom.geotemporal.internal.time.StreamUtils.dateRangeStream;
import static org.sellcom.geotemporal.time.HolidayType.BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.PUBLIC;

import java.time.LocalDate;
import java.time.Month;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.geography.StandardGeoRegions.France;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Germany;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Guernsey;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Italy;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Portugal;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Switzerland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.UnitedKingdom;

public class HolidaysTest {

	@Test
	public void testIsPublicHoliday_andorra_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, MARCH, 3));
		publicHolidays.add(LocalDate.of(2014, MARCH, 14));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ANDORRA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_andorra_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 16));
		publicHolidays.add(LocalDate.of(2015, MARCH, 14));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ANDORRA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_andorra_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 8));
		publicHolidays.add(LocalDate.of(2016, MARCH, 14));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ANDORRA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_austria_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 26));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(AUSTRIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_austria_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 26));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(AUSTRIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_austria_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 26));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(AUSTRIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_belgium_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JULY, 21));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(BELGIUM),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_belgium_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JULY, 21));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(BELGIUM),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_belgium_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JULY, 21));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(BELGIUM),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_croatia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, JUNE, 22));
		publicHolidays.add(LocalDate.of(2014, JUNE, 25));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 5));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 8));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CROATIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_croatia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 22));
		publicHolidays.add(LocalDate.of(2015, JUNE, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 5));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 8));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CROATIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_croatia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, JUNE, 22));
		publicHolidays.add(LocalDate.of(2016, JUNE, 25));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 5));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 8));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CROATIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_cyprus_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, APRIL, 22));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CYPRUS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_cyprus_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, MARCH, 3));
		publicHolidays.add(LocalDate.of(2014, MARCH, 25));
		publicHolidays.add(LocalDate.of(2014, APRIL, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 1));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 28));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CYPRUS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_cyprus_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, APRIL, 14));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CYPRUS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_cyprus_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 23));
		publicHolidays.add(LocalDate.of(2015, MARCH, 25));
		publicHolidays.add(LocalDate.of(2015, APRIL, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 10));
		publicHolidays.add(LocalDate.of(2015, APRIL, 13));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 1));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 28));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CYPRUS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_cyprus_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, MAY, 3));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CYPRUS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_cyprus_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 14));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, APRIL, 1));
		publicHolidays.add(LocalDate.of(2016, APRIL, 29));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, JUNE, 20));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 1));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 28));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CYPRUS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_czechRepublic_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 8));
		publicHolidays.add(LocalDate.of(2014, JULY, 5));
		publicHolidays.add(LocalDate.of(2014, JULY, 6));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 28));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 28));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 17));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CZECH_REPUBLIC),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_czechRepublic_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 8));
		publicHolidays.add(LocalDate.of(2015, JULY, 5));
		publicHolidays.add(LocalDate.of(2015, JULY, 6));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 28));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 28));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 17));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CZECH_REPUBLIC),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_czechRepublic_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 8));
		publicHolidays.add(LocalDate.of(2016, JULY, 5));
		publicHolidays.add(LocalDate.of(2016, JULY, 6));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 28));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 28));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 17));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(CZECH_REPUBLIC),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_denmark_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, MAY, 30));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(DENMARK),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_denmark_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 16));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 5));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(DENMARK),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_denmark_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, MAY, 15));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(DENMARK),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_denmark_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 5));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(DENMARK),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_denmark_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, MAY, 6));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(DENMARK),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_denmark_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 24));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 22));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JUNE, 5));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(DENMARK),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_estonia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 24));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 23));
		publicHolidays.add(LocalDate.of(2014, JUNE, 24));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ESTONIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_estonia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 24));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, JUNE, 23));
		publicHolidays.add(LocalDate.of(2015, JUNE, 24));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ESTONIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_estonia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 24));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, JUNE, 23));
		publicHolidays.add(LocalDate.of(2016, JUNE, 24));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ESTONIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_faroeIslands_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 25));
		publicHolidays.add(LocalDate.of(2014, MAY, 16));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JULY, 29));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FAROE_ISLANDS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_faroeIslands_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 25));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JULY, 29));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FAROE_ISLANDS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_faroeIslands_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 24));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 22));
		publicHolidays.add(LocalDate.of(2016, APRIL, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JULY, 29));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FAROE_ISLANDS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_finland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 21));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 6));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FINLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_finland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, JUNE, 20));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 6));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FINLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_finland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, JUNE, 25));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 5));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 6));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FINLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_france_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 8));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JULY, 14));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FRANCE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_france_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 8));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JULY, 14));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FRANCE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_france_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 8));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JULY, 14));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(FRANCE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_france_selectedRegions_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 8));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JULY, 14));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DE_LA_MOSELLE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DU_BAS_RHIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DU_HAUT_RHIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_france_selectedRegions_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 8));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JULY, 14));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DE_LA_MOSELLE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DU_BAS_RHIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DU_HAUT_RHIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_france_selectedRegions_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 8));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JULY, 14));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DE_LA_MOSELLE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DU_BAS_RHIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(France.DEPARTEMENT_DU_HAUT_RHIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_georgia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 19));
		publicHolidays.add(LocalDate.of(2014, MARCH, 3));
		publicHolidays.add(LocalDate.of(2014, MARCH, 8));
		publicHolidays.add(LocalDate.of(2014, APRIL, 9));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 9));
		publicHolidays.add(LocalDate.of(2014, MAY, 12));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 28));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 14));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 23));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GEORGIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_georgia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 19));
		publicHolidays.add(LocalDate.of(2015, MARCH, 3));
		publicHolidays.add(LocalDate.of(2015, MARCH, 8));
		publicHolidays.add(LocalDate.of(2015, APRIL, 9));
		publicHolidays.add(LocalDate.of(2015, APRIL, 10));
		publicHolidays.add(LocalDate.of(2015, APRIL, 11));
		publicHolidays.add(LocalDate.of(2015, APRIL, 12));
		publicHolidays.add(LocalDate.of(2015, APRIL, 13));
		publicHolidays.add(LocalDate.of(2015, MAY, 9));
		publicHolidays.add(LocalDate.of(2015, MAY, 12));
		publicHolidays.add(LocalDate.of(2015, MAY, 26));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 28));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 14));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 23));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GEORGIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_georgia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 3));
		publicHolidays.add(LocalDate.of(2016, MARCH, 8));
		publicHolidays.add(LocalDate.of(2016, APRIL, 9));
		publicHolidays.add(LocalDate.of(2016, APRIL, 29));
		publicHolidays.add(LocalDate.of(2016, APRIL, 30));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 9));
		publicHolidays.add(LocalDate.of(2016, MAY, 12));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 28));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 14));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 23));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GEORGIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GERMANY),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GERMANY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GERMANY),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GERMANY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GERMANY),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GERMANY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_badenWuerttemberg_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_BADEN_WUERTTEMBERG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_badenWuerttemberg_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_BADEN_WUERTTEMBERG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_badenWuerttemberg_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_BADEN_WUERTTEMBERG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_bayern_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_BAYERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_bayern_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_BAYERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_bayern_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_BAYERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_brandenburg_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_BRANDENBURG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_brandenburg_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_BRANDENBURG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_brandenburg_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_BRANDENBURG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_hessen_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_HESSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_hessen_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_HESSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_hessen_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_HESSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_mecklenburgVorpommern_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_MECKLENBURG_VORPOMMERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_mecklenburgVorpommern_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_MECKLENBURG_VORPOMMERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_mecklenburgVorpommern_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_MECKLENBURG_VORPOMMERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_nordrheinWestfalenAndRheinlandPfalz_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_NORDRHEIN_WESTFALEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_RHEINLAND_PFALZ),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_nordrheinWestfalenAndRheinlandPfalz_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_NORDRHEIN_WESTFALEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_RHEINLAND_PFALZ),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_nordrheinWestfalenAndRheinlandPfalz_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_NORDRHEIN_WESTFALEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_RHEINLAND_PFALZ),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_saarland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.SAARLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_saarland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.SAARLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_saarland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.SAARLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_sachsen_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 19));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_SACHSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_sachsen_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 18));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_SACHSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_sachsen_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 16));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_SACHSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_sachsenAnhalt_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_SACHSEN_ANHALT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_sachsenAnhalt_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_SACHSEN_ANHALT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_sachsenAnhalt_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.LAND_SACHSEN_ANHALT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_thueringen_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_THUERINGEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_thueringen_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_THUERINGEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_germany_thueringen_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Germany.FREISTAAT_THUERINGEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_greenland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 16));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 21));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GREENLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_greenland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 21));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GREENLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_greenland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 24));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 22));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JUNE, 21));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(GREENLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_guernsey_alderney_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 4));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 15));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_ALDERNEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_guernsey_alderney_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 3));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 15));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_ALDERNEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_guernsey_alderney_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 15));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_ALDERNEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_guernsey_selectedRegions_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 9));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_GUERNSEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_SARK),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_guernsey_selectedRegions_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 9));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_GUERNSEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_SARK),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_guernsey_selectedRegions_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 9));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_GUERNSEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Guernsey.JURISDICTION_OF_SARK),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_hungary_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 15));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 23));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(HUNGARY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_hungary_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, MARCH, 15));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 23));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(HUNGARY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_hungary_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 15));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 23));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(HUNGARY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_hungary_2017_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2017, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2017, MARCH, 15));
		publicHolidays.add(LocalDate.of(2017, APRIL, 14));
		publicHolidays.add(LocalDate.of(2017, APRIL, 16));
		publicHolidays.add(LocalDate.of(2017, APRIL, 17));
		publicHolidays.add(LocalDate.of(2017, MAY, 1));
		publicHolidays.add(LocalDate.of(2017, JUNE, 4));
		publicHolidays.add(LocalDate.of(2017, JUNE, 5));
		publicHolidays.add(LocalDate.of(2017, AUGUST, 20));
		publicHolidays.add(LocalDate.of(2017, OCTOBER, 23));
		publicHolidays.add(LocalDate.of(2017, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2017, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2017, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(HUNGARY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2017, JANUARY, 1), LocalDate.of(2017, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_iceland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 24));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 17));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 4));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ICELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_iceland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 23));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 17));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 3));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ICELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_iceland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 24));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 21));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JUNE, 17));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ICELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_isleOfMan_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, JUNE, 6));
		publicHolidays.add(LocalDate.of(2014, JULY, 5));
		publicHolidays.add(LocalDate.of(2014, JULY, 7)); // Substitute day for 5 July
		publicHolidays.add(LocalDate.of(2014, AUGUST, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ISLE_OF_MAN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_isleOfMan_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 12));
		publicHolidays.add(LocalDate.of(2015, JULY, 5));
		publicHolidays.add(LocalDate.of(2015, JULY, 6)); // Substitute day for 5 July
		publicHolidays.add(LocalDate.of(2015, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28)); // Substitute day for 26 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ISLE_OF_MAN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_isleOfMan_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, JUNE, 10));
		publicHolidays.add(LocalDate.of(2016, JULY, 5));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27)); // Substitute day for 25 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ISLE_OF_MAN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_italy_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 25));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 2));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ITALY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_italy_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 25));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 2));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ITALY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_italy_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, JUNE, 2));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ITALY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_italy_trentinoAltoAdige_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 25));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 2));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Italy.REGIONE_AUTONOMA_TRENTINO_ALTO_ADIGE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_italy_trentinoAltoAdige_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 25));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 2));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Italy.REGIONE_AUTONOMA_TRENTINO_ALTO_ADIGE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_italy_trentinoAltoAdige_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JUNE, 2));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Italy.REGIONE_AUTONOMA_TRENTINO_ALTO_ADIGE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_jersey_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 9));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(JERSEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_jersey_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 9));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(JERSEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_jersey_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 9));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(JERSEY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_latvia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 4));
		publicHolidays.add(LocalDate.of(2014, JUNE, 23));
		publicHolidays.add(LocalDate.of(2014, JUNE, 24));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 18));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LATVIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_latvia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 23));
		publicHolidays.add(LocalDate.of(2015, JUNE, 24));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 18));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LATVIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_latvia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 4));
		publicHolidays.add(LocalDate.of(2016, JUNE, 23));
		publicHolidays.add(LocalDate.of(2016, JUNE, 24));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 18));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LATVIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_liechtenstein_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, MARCH, 4));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LIECHTENSTEIN),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_liechtenstein_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 2));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LIECHTENSTEIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_liechtenstein_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LIECHTENSTEIN),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_liechtenstein_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 2));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LIECHTENSTEIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_liechtenstein_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 9));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LIECHTENSTEIN),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_liechtenstein_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(LIECHTENSTEIN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_malta_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, MARCH, 28));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MALTA),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_malta_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 10));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, MARCH, 31));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 7));
		publicHolidays.add(LocalDate.of(2014, JUNE, 29));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 21));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 13));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MALTA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_malta_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, MARCH, 28));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MALTA),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_malta_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 10));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, MARCH, 31));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 7));
		publicHolidays.add(LocalDate.of(2015, JUNE, 29));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 21));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 13));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MALTA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_malta_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MALTA),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_malta_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 10));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 31));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, JUNE, 7));
		publicHolidays.add(LocalDate.of(2016, JUNE, 29));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 21));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 13));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MALTA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_moldova_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 8));
		publicHolidays.add(LocalDate.of(2014, MARCH, 8));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 28));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 27));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MOLDOVA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_moldova_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 8));
		publicHolidays.add(LocalDate.of(2015, MARCH, 8));
		publicHolidays.add(LocalDate.of(2015, APRIL, 12));
		publicHolidays.add(LocalDate.of(2015, APRIL, 13));
		publicHolidays.add(LocalDate.of(2015, APRIL, 20));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 9));
		publicHolidays.add(LocalDate.of(2015, JUNE, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 27));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MOLDOVA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_moldova_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 8));
		publicHolidays.add(LocalDate.of(2016, MARCH, 8));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 9));
		publicHolidays.add(LocalDate.of(2016, JUNE, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 27));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(MOLDOVA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_netherlands_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NETHERLANDS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_netherlands_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 26)); // Substitute day for 27 April
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NETHERLANDS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_netherlands_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NETHERLANDS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_netherlands_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 27));
		publicHolidays.add(LocalDate.of(2015, MAY, 5));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NETHERLANDS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_netherlands_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NETHERLANDS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_netherlands_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 27));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NETHERLANDS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_norway_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 17));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NORWAY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_norway_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 17));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NORWAY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_norway_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 24));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 17));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(NORWAY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_poland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, Month.JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, Month.JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, Month.APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, Month.APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, Month.MAY, 1));
		publicHolidays.add(LocalDate.of(2014, Month.MAY, 3));
		publicHolidays.add(LocalDate.of(2014, Month.JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, Month.JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, Month.AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, Month.NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, Month.NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2014, Month.DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, Month.DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(POLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_poland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, Month.JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, Month.JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, Month.APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, Month.APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, Month.MAY, 1));
		publicHolidays.add(LocalDate.of(2015, Month.MAY, 3));
		publicHolidays.add(LocalDate.of(2015, Month.MAY, 24));
		publicHolidays.add(LocalDate.of(2015, Month.JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, Month.AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, Month.NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, Month.NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2015, Month.DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, Month.DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(POLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_poland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, Month.JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, Month.JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, Month.MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, Month.MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, Month.MAY, 1));
		publicHolidays.add(LocalDate.of(2016, Month.MAY, 3));
		publicHolidays.add(LocalDate.of(2016, Month.MAY, 15));
		publicHolidays.add(LocalDate.of(2016, Month.MAY, 26));
		publicHolidays.add(LocalDate.of(2016, Month.AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, Month.NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, Month.NOVEMBER, 11));
		publicHolidays.add(LocalDate.of(2016, Month.DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, Month.DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(POLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 4));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 25));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 10));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(PORTUGAL),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 25));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 10));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(PORTUGAL),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 9));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, APRIL, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, JUNE, 10));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(PORTUGAL),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_acores_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 4));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 25));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 10));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Portugal.REGIAO_AUTONOMA_DOS_ACORES),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_acores_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 25));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 10));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Portugal.REGIAO_AUTONOMA_DOS_ACORES),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_acores_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 9));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, APRIL, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, JUNE, 10));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Portugal.REGIAO_AUTONOMA_DOS_ACORES),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_madeira_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 4));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 25));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 10));
		publicHolidays.add(LocalDate.of(2014, JULY, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Portugal.REGIAO_AUTONOMA_DA_MADEIRA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_madeira_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 25));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 10));
		publicHolidays.add(LocalDate.of(2015, JULY, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Portugal.REGIAO_AUTONOMA_DA_MADEIRA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_portugal_madeira_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 9));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, APRIL, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, JUNE, 10));
		publicHolidays.add(LocalDate.of(2016, JULY, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Portugal.REGIAO_AUTONOMA_DA_MADEIRA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_republicOfIreland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, JUNE, 2));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 4));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 6));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(REPUBLIC_OF_IRELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_republicOfIreland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, MARCH, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 3));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 5));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(REPUBLIC_OF_IRELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_republicOfIreland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 17));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, JUNE, 6));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 3));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(REPUBLIC_OF_IRELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_romania_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 24));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 30));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ROMANIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_romania_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 24));
		publicHolidays.add(LocalDate.of(2015, APRIL, 12));
		publicHolidays.add(LocalDate.of(2015, APRIL, 13));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 31));
		publicHolidays.add(LocalDate.of(2015, JUNE, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 30));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ROMANIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_romania_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 24));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, JUNE, 19));
		publicHolidays.add(LocalDate.of(2016, JUNE, 20));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 30));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(ROMANIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_serbia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 15));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 16));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 2));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 11));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SERBIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_serbia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 15));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 16));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 10));
		publicHolidays.add(LocalDate.of(2015, APRIL, 12));
		publicHolidays.add(LocalDate.of(2015, APRIL, 13));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 2));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 11));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SERBIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_serbia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 7));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 15));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 16));
		publicHolidays.add(LocalDate.of(2016, APRIL, 29));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 3));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 11));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SERBIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_slovakia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 8));
		publicHolidays.add(LocalDate.of(2014, JULY, 5));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 17));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SLOVAKIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_slovakia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 8));
		publicHolidays.add(LocalDate.of(2015, JULY, 5));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 17));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SLOVAKIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_slovakia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 8));
		publicHolidays.add(LocalDate.of(2016, JULY, 5));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 17));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 24));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SLOVAKIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_slovenia_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 8));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 27));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 2));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 25));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SLOVENIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_slovenia_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 8));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 27));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 2));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, JUNE, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SLOVENIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_slovenia_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 8));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 27));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, JUNE, 25));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SLOVENIA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_sweden_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 6));
		publicHolidays.add(LocalDate.of(2014, JUNE, 8));
		publicHolidays.add(LocalDate.of(2014, JUNE, 21));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SWEDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_sweden_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 24));
		publicHolidays.add(LocalDate.of(2015, JUNE, 6));
		publicHolidays.add(LocalDate.of(2015, JUNE, 20));
		publicHolidays.add(LocalDate.of(2015, OCTOBER, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SWEDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_sweden_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 15));
		publicHolidays.add(LocalDate.of(2016, JUNE, 6));
		publicHolidays.add(LocalDate.of(2016, JUNE, 25));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 5));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SWEDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SWITZERLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SWITZERLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(SWITZERLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_aargau_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_AARGAU),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_aargau_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_AARGAU),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_aargau_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_AARGAU),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_appenzellAusserrhoden_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_APPENZELL_AUSSERRHODEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_appenzellAusserrhoden_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_APPENZELL_AUSSERRHODEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_appenzellAusserrhoden_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_APPENZELL_AUSSERRHODEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_appenzellInnerrhoden_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 22));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_APPENZELL_INNERRHODEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_appenzellInnerrhoden_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 22));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_APPENZELL_INNERRHODEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_appenzellInnerrhoden_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 22));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_APPENZELL_INNERRHODEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_baselLandschaft_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BASEL_LANDSCHAFT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_baselLandschaft_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BASEL_LANDSCHAFT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_baselLandschaft_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BASEL_LANDSCHAFT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_baselStadt_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BASEL_STADT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_baselStadt_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BASEL_STADT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_baselStadt_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BASEL_STADT),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_bern_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_bern_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_bern_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_BERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_fribourg_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.ETAT_DE_FRIBOURG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_fribourg_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.ETAT_DE_FRIBOURG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_fribourg_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.ETAT_DE_FRIBOURG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_geneve_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 11));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DE_GENEVE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_geneve_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 10));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DE_GENEVE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_geneve_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 31));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DE_GENEVE),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_glarus_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 3));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_GLARUS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_glarus_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_GLARUS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_glarus_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 7));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_GLARUS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_graubuenden_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, JUNE, 29));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_GRAUBUENDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_graubuenden_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 29));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_GRAUBUENDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_graubuenden_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, JUNE, 29));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_GRAUBUENDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_jura_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, JUNE, 23));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DU_JURA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_jura_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 23));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DU_JURA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_jura_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, JUNE, 23));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DU_JURA),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_luzern_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, JUNE, 29));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_LUZERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_luzern_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 29));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_LUZERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_luzern_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, JUNE, 29));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_LUZERN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_neuchatel_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, MARCH, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DE_NEUCHATEL),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_neuchatel_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, MARCH, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DE_NEUCHATEL),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_neuchatel_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBLIQUE_ET_CANTON_DE_NEUCHATEL),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_nidwalden_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_NIDWALDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_nidwalden_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_NIDWALDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_nidwalden_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_NIDWALDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_obwalden_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_OBWALDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_obwalden_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_OBWALDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_obwalden_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_OBWALDEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_sanktGallen_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SANKT_GALLEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_sanktGallen_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SANKT_GALLEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_sanktGallen_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SANKT_GALLEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_schaffhausen_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SCHAFFHAUSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_schaffhausen_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SCHAFFHAUSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_schaffhausen_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SCHAFFHAUSEN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_schwyz_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SCHWYZ),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_schwyz_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SCHWYZ),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_schwyz_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SCHWYZ),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_solothurn_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SOLOTHURN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_solothurn_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SOLOTHURN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_solothurn_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_SOLOTHURN),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_thurgau_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_THURGAU),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_thurgau_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_THURGAU),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_thurgau_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_THURGAU),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_ticino_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, JUNE, 29));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBBLICA_E_CANTONE_TICINO),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_ticino_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, JUNE, 29));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBBLICA_E_CANTONE_TICINO),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_ticino_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, JUNE, 29));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.REPUBBLICA_E_CANTONE_TICINO),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_uri_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_URI),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_uri_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_URI),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_uri_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_URI),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_valais_2014_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 22));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DU_VALAIS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_valais_2015_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 21));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DU_VALAIS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_valais_2016_banksAndFinancialInstitutions() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 19));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DU_VALAIS),
				EnumSet.of(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_valais_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DU_VALAIS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_valais_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DU_VALAIS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_valais_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DU_VALAIS),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_vaud_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, SEPTEMBER, 22));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DE_VAUD),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_vaud_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, SEPTEMBER, 21));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DE_VAUD),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_vaud_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, SEPTEMBER, 19));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.CANTON_DE_VAUD),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_zuerich_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_ZUERICH),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_zuerich_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_ZUERICH),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_zuerich_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_ZUERICH),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_zug_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 29));
		publicHolidays.add(LocalDate.of(2014, JUNE, 9));
		publicHolidays.add(LocalDate.of(2014, JUNE, 19));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_ZUG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_zug_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 14));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JUNE, 4));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_ZUG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_switzerland_zug_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 5));
		publicHolidays.add(LocalDate.of(2016, MAY, 16));
		publicHolidays.add(LocalDate.of(2016, MAY, 26));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(Switzerland.KANTON_ZUG),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_englandAndWales_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_ENGLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_WALES),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_englandAndWales_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28)); // Substitute day for 26 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_ENGLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_WALES),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_englandAndWales_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27)); // Substitute day for 25 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_ENGLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_WALES),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_northernIreland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, MARCH, 17));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, JULY, 12));
		publicHolidays.add(LocalDate.of(2014, JULY, 14)); // Substitute day for 12 July
		publicHolidays.add(LocalDate.of(2014, AUGUST, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_NORTHERN_IRELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_northernIreland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, MARCH, 17));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, JULY, 12));
		publicHolidays.add(LocalDate.of(2015, JULY, 13)); // Substitute day for 12 July
		publicHolidays.add(LocalDate.of(2015, AUGUST, 31));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28)); // Substitute day for 26 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_NORTHERN_IRELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_northernIreland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, MARCH, 17));
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, JULY, 12));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 29));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27)); // Substitute day for 25 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_NORTHERN_IRELAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_scotland_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2014, APRIL, 18));
		publicHolidays.add(LocalDate.of(2014, MAY, 5));
		publicHolidays.add(LocalDate.of(2014, MAY, 26));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 4));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 30));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 1)); // Substitute day for 30 November
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_SCOTLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_scotland_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2015, APRIL, 3));
		publicHolidays.add(LocalDate.of(2015, MAY, 4));
		publicHolidays.add(LocalDate.of(2015, MAY, 25));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 3));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 30));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 28)); // Substitute day for 26 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_SCOTLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_unitedKingdom_scotland_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 2));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 4)); // Substitute day for 2 January
		publicHolidays.add(LocalDate.of(2016, MARCH, 25));
		publicHolidays.add(LocalDate.of(2016, MAY, 2));
		publicHolidays.add(LocalDate.of(2016, MAY, 30));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 1));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 30));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 27)); // Substitute day for 25 December

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(UnitedKingdom.COUNTRY_OF_SCOTLAND),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_vaticanCity_2014_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2014, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2014, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2014, FEBRUARY, 11));
		publicHolidays.add(LocalDate.of(2014, MARCH, 13));
		publicHolidays.add(LocalDate.of(2014, MARCH, 19));
		publicHolidays.add(LocalDate.of(2014, APRIL, 20));
		publicHolidays.add(LocalDate.of(2014, APRIL, 21));
		publicHolidays.add(LocalDate.of(2014, APRIL, 23));
		publicHolidays.add(LocalDate.of(2014, MAY, 1));
		publicHolidays.add(LocalDate.of(2014, JUNE, 29));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 14));
		publicHolidays.add(LocalDate.of(2014, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2014, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2014, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(VATICAN_CITY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2014, JANUARY, 1), LocalDate.of(2014, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_vaticanCity_2015_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2015, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2015, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2015, FEBRUARY, 11));
		publicHolidays.add(LocalDate.of(2015, MARCH, 13));
		publicHolidays.add(LocalDate.of(2015, MARCH, 19));
		publicHolidays.add(LocalDate.of(2015, APRIL, 5));
		publicHolidays.add(LocalDate.of(2015, APRIL, 6));
		publicHolidays.add(LocalDate.of(2015, APRIL, 23));
		publicHolidays.add(LocalDate.of(2015, MAY, 1));
		publicHolidays.add(LocalDate.of(2015, JUNE, 29));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 14));
		publicHolidays.add(LocalDate.of(2015, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2015, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2015, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(VATICAN_CITY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2015, JANUARY, 1), LocalDate.of(2015, DECEMBER, 31)),
				publicHolidays);
	}

	@Test
	public void testIsPublicHoliday_vaticanCity_2016_global() {
		Set<LocalDate> publicHolidays = new HashSet<>();
		publicHolidays.add(LocalDate.of(2016, JANUARY, 1));
		publicHolidays.add(LocalDate.of(2016, JANUARY, 6));
		publicHolidays.add(LocalDate.of(2016, FEBRUARY, 11));
		publicHolidays.add(LocalDate.of(2016, MARCH, 13));
		publicHolidays.add(LocalDate.of(2016, MARCH, 19));
		publicHolidays.add(LocalDate.of(2016, MARCH, 27));
		publicHolidays.add(LocalDate.of(2016, MARCH, 28));
		publicHolidays.add(LocalDate.of(2016, APRIL, 23));
		publicHolidays.add(LocalDate.of(2016, MAY, 1));
		publicHolidays.add(LocalDate.of(2016, JUNE, 29));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 14));
		publicHolidays.add(LocalDate.of(2016, AUGUST, 15));
		publicHolidays.add(LocalDate.of(2016, NOVEMBER, 1));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 8));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 25));
		publicHolidays.add(LocalDate.of(2016, DECEMBER, 26));

		testHolidayPredicate(Holidays::isHoliday,
				GeoRegion.parse(VATICAN_CITY),
				EnumSet.of(PUBLIC),
				dateRangeStream(LocalDate.of(2016, JANUARY, 1), LocalDate.of(2016, DECEMBER, 31)),
				publicHolidays);
	}

}
