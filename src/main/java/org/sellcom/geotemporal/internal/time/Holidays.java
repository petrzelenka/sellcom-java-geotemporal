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
import static java.time.temporal.ChronoField.YEAR;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static java.time.temporal.TemporalAdjusters.lastInMonth;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.sellcom.geotemporal.internal.time.TemporalUtils.is;
import static org.sellcom.geotemporal.internal.time.TemporalUtils.isInRange;
import static org.sellcom.geotemporal.time.HolidayType.BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.PUBLIC;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_ONLY;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.ascensionThursday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.bussUndBettag;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.cleanMonday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.corpusChristi;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.easterMonday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.easterSunday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.goodFriday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.maundyThursday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.nextDay;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.orthodoxEasterMonday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.orthodoxEasterSunday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.orthodoxGoodFriday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.orthodoxHolySaturday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.orthodoxWhitMonday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.orthodoxWhitSunday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.pastileBlajinilor;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.shroveMonday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.shroveTuesday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.storeBededag;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.whitMonday;
import static org.sellcom.geotemporal.time.MoreTemporalAdjusters.whitSunday;

import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.EnumSet;
import java.util.Set;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.geography.GeoRegionNotSupportedException;
import org.sellcom.geotemporal.geography.StandardGeoRegions;
import org.sellcom.geotemporal.geography.StandardGeoRegions.CzechRepublic;
import org.sellcom.geotemporal.geography.StandardGeoRegions.France;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Germany;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Guernsey;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Italy;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Portugal;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Slovakia;
import org.sellcom.geotemporal.geography.StandardGeoRegions.Switzerland;
import org.sellcom.geotemporal.geography.StandardGeoRegions.UnitedKingdom;
import org.sellcom.geotemporal.time.HolidayType;

public class Holidays {

	private Holidays() {
		// Utility class, not to be instantiated
	}


	public static boolean isHoliday(Temporal temporal, GeoRegion region, EnumSet<HolidayType> types) {
		switch (region.getCountryCode()) {
			case StandardGeoRegions.ANDORRA: {
				return isHoliday_andorra(temporal, region, types);
			}
			case StandardGeoRegions.AUSTRIA: {
				return isHoliday_austria(temporal, region, types);
			}
			case StandardGeoRegions.BELGIUM: {
				return isHoliday_belgium(temporal, region, types);
			}
			case StandardGeoRegions.CROATIA: {
				return isHoliday_croatia(temporal, region, types);
			}
			case StandardGeoRegions.CYPRUS: {
				return isHoliday_cyprus(temporal, region, types);
			}
			case StandardGeoRegions.CZECH_REPUBLIC: {
				return isHoliday_czechRepublic(temporal, region, types);
			}
			case StandardGeoRegions.DENMARK: {
				return isHoliday_denmark(temporal, region, types);
			}
			case StandardGeoRegions.ESTONIA: {
				return isHoliday_estonia(temporal, region, types);
			}
			case StandardGeoRegions.FAROE_ISLANDS: {
				return isHoliday_faroeIslands(temporal, region, types);
			}
			case StandardGeoRegions.FINLAND: {
				return isHoliday_finland(temporal, region, types);
			}
			case StandardGeoRegions.FRANCE: {
				return isHoliday_france(temporal, region, types);
			}
			case StandardGeoRegions.GEORGIA: {
				return isHoliday_georgia(temporal, region, types);
			}
			case StandardGeoRegions.GERMANY: {
				return isHoliday_germany(temporal, region, types);
			}
			case StandardGeoRegions.GREENLAND: {
				return isHoliday_greenland(temporal, region, types);
			}
			case StandardGeoRegions.GUERNSEY: {
				return isHoliday_guernsey(temporal, region, types);
			}
			case StandardGeoRegions.HUNGARY: {
				return isHoliday_hungary(temporal, region, types);
			}
			case StandardGeoRegions.ICELAND: {
				return isHoliday_iceland(temporal, region, types);
			}
			case StandardGeoRegions.ISLE_OF_MAN: {
				return isHoliday_isleOfMan(temporal, region, types);
			}
			case StandardGeoRegions.ITALY: {
				return isHoliday_italy(temporal, region, types);
			}
			case StandardGeoRegions.JERSEY: {
				return isHoliday_jersey(temporal, region, types);
			}
			case StandardGeoRegions.LATVIA: {
				return isHoliday_latvia(temporal, region, types);
			}
			case StandardGeoRegions.LIECHTENSTEIN: {
				return isHoliday_liechtenstein(temporal, region, types);
			}
			case StandardGeoRegions.MALTA: {
				return isHoliday_malta(temporal, region, types);
			}
			case StandardGeoRegions.MOLDOVA: {
				return isHoliday_moldova(temporal, region, types);
			}
			case StandardGeoRegions.NETHERLANDS: {
				return isHoliday_netherlands(temporal, region, types);
			}
			case StandardGeoRegions.NORWAY: {
				return isHoliday_norway(temporal, region, types);
			}
			case StandardGeoRegions.POLAND: {
				return isHoliday_poland(temporal, region, types);
			}
			case StandardGeoRegions.PORTUGAL: {
				return isHoliday_portugal(temporal, region, types);
			}
			case StandardGeoRegions.REPUBLIC_OF_IRELAND: {
				return isHoliday_republicOfIreland(temporal, region, types);
			}
			case StandardGeoRegions.ROMANIA: {
				return isHoliday_romania(temporal, region, types);
			}
			case StandardGeoRegions.SERBIA: {
				return isHoliday_serbia(temporal, region, types);
			}
			case StandardGeoRegions.SLOVAKIA: {
				return isHoliday_slovakia(temporal, region, types);
			}
			case StandardGeoRegions.SLOVENIA: {
				return isHoliday_slovenia(temporal, region, types);
			}
			case StandardGeoRegions.SWEDEN: {
				return isHoliday_sweden(temporal, region, types);
			}
			case StandardGeoRegions.SWITZERLAND: {
				return isHoliday_switzerland(temporal, region, types);
			}
			case StandardGeoRegions.UNITED_KINGDOM: {
				return isHoliday_unitedKingdom(temporal, region, types);
			}
			case StandardGeoRegions.VATICAN_CITY: {
				return isHoliday_vaticanCity(temporal, region, types);
			}
			default: {
				throw new GeoRegionNotSupportedException(String.format("Unsupported region: %s", region));
			}
		}
	}


	private static boolean isHoliday_andorra(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MARCH, 14)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 8)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, shroveMonday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_austria(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, OCTOBER, 26)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
			if (is(temporal, corpusChristi())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_belgium(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JULY, 21)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 11)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_croatia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 22)) {
				return true;
			}
			if (is(temporal, JUNE, 25)) {
				return true;
			}
			if (is(temporal, AUGUST, 5)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, OCTOBER, 8)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, corpusChristi())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_cyprus(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MARCH, 25)) {
				return true;
			}
			if (is(temporal, APRIL, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, OCTOBER, 1)) {
				return true;
			}
			if (is(temporal, OCTOBER, 28)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, cleanMonday())) {
				return true;
			}
			if (is(temporal, orthodoxGoodFriday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterMonday())) {
				return true;
			}
			if (is(temporal, orthodoxWhitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Fixed holidays
			// None

			// Movable holidays
			if (is(temporal, orthodoxEasterMonday(), nextDay())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_czechRepublic(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 8)) {
				return true;
			}
			if (isInRange(temporal, JULY, 5, JULY, 6)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 28)) {
				return true;
			}
			if (is(temporal, OCTOBER, 28)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 17)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 24, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday()) && (temporal.get(YEAR) >= 2016)) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		if (types.contains(SCHOOLS_ONLY)) {
			// 2014
			if (isInRange(temporal, 2014, JANUARY, 1, 2014, JANUARY, 5)) {
				return true;
			}
			if (is(temporal, 2014, JANUARY, 31)) {
				return true;
			}
			if (isInRange(temporal, 2014, APRIL, 17, 2014, APRIL, 18)) {
				return true;
			}
			if (isInRange(temporal, 2014, JUNE, 28, 2014, AUGUST, 31)) {
				return true;
			}

			// 2014/2015
			if (is(temporal, 2014, OCTOBER, 27)) {
				return true;
			}
			if (is(temporal, 2014, OCTOBER, 29)) {
				return true;
			}
			if (isInRange(temporal, 2014, DECEMBER, 22, 2015, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, 2015, JANUARY, 30)) {
				return true;
			}
			if (isInRange(temporal, 2015, APRIL, 2, 2015, APRIL, 3)) {
				return true;
			}
			if (isInRange(temporal, 2015, JULY, 1, 2015, AUGUST, 31)) {
				return true;
			}

			// 2015/2016
			if (isInRange(temporal, 2015, OCTOBER, 29, 2015, OCTOBER, 30)) {
				return true;
			}
			if (isInRange(temporal, 2015, DECEMBER, 23, 2016, JANUARY, 3)) {
				return true;
			}
			if (is(temporal, 2016, JANUARY, 29)) {
				return true;
			}
			if (isInRange(temporal, 2016, MARCH, 24, 2016, MARCH, 25)) {
				return true;
			}
			if (isInRange(temporal, 2016, JULY, 1, 2016, AUGUST, 31)) {
				return true;
			}

			// 2016/2017
			if (isInRange(temporal, 2016, OCTOBER, 26, 2016, OCTOBER, 27)) {
				return true;
			}
			if (isInRange(temporal, 2016, DECEMBER, 23, 2017, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, 2017, FEBRUARY, 3)) {
				return true;
			}
			if (is(temporal, 2017, APRIL, 13)) {
				return true;
			}
			if (isInRange(temporal, 2017, JULY, 1, 2017, SEPTEMBER, 1)) {
				return true;
			}

			// 2017/2018
			if (isInRange(temporal, 2017, OCTOBER, 26, 2017, OCTOBER, 27)) {
				return true;
			}
			if (isInRange(temporal, 2017, DECEMBER, 23, 2018, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, 2018, FEBRUARY, 2)) {
				return true;
			}
			if (is(temporal, 2018, MARCH, 29)) {
				return true;
			}
			if (isInRange(temporal, 2018, JULY, 2, 2018, AUGUST, 31)) {
				return true;
			}

			// 2018/2019
			if (isInRange(temporal, 2018, OCTOBER, 29, 2018, OCTOBER, 30)) {
				return true;
			}
			if (isInRange(temporal, 2018, DECEMBER, 22, 2019, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, 2019, FEBRUARY, 1)) {
				return true;
			}
			if (is(temporal, 2019, APRIL, 18)) {
				return true;
			}
			if (isInRange(temporal, 2019, JUNE, 29, 2019, SEPTEMBER, 1)) {
				return true;
			}

			// 2019/2020
			if (isInRange(temporal, 2019, OCTOBER, 29, 2019, OCTOBER, 30)) {
				return true;
			}
			if (isInRange(temporal, 2019, DECEMBER, 23, 2020, JANUARY, 3)) {
				return true;
			}
			if (is(temporal, 2020, JANUARY, 31)) {
				return true;
			}
			if (is(temporal, 2020, APRIL, 9)) {
				return true;
			}
			if (isInRange(temporal, 2020, JULY, 1, 2020, AUGUST, 31)) {
				return true;
			}


			// Regional holidays
			String regionCode = region.atLevel(2).getCode();
			switch (regionCode) {
				case CzechRepublic.OKRES_MLADA_BOLESLAV:
				case CzechRepublic.OKRES_PRIBRAM:
				case CzechRepublic.OKRES_TABOR:
				case CzechRepublic.OKRES_PRACHATICE:
				case CzechRepublic.OKRES_STRAKONICE:
				case CzechRepublic.OKRES_USTI_NAD_LABEM:
				case CzechRepublic.OKRES_CHOMUTOV:
				case CzechRepublic.OKRES_MOST:
				case CzechRepublic.OKRES_JICIN:
				case CzechRepublic.OKRES_RYCHNOV_NAD_KNEZNOU:
				case CzechRepublic.OKRES_OLOMOUC:
				case CzechRepublic.OKRES_SUMPERK:
				case CzechRepublic.OKRES_OPAVA:
				case CzechRepublic.OKRES_JESENIK:
				{
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 3, 2014, FEBRUARY, 9)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 9, 2015, FEBRUARY, 15)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 15, 2016, FEBRUARY, 21)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, FEBRUARY, 27, 2017, MARCH, 5)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, MARCH, 5, 2018, MARCH, 11)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, MARCH, 11, 2019, MARCH, 17)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, FEBRUARY, 3, 2020, FEBRUARY, 9)) {
						return true;
					}

					break;
				}

				case CzechRepublic.OKRES_BENESOV:
				case CzechRepublic.OKRES_BEROUN:
				case CzechRepublic.OKRES_ROKYCANY:
				case CzechRepublic.OKRES_CESKE_BUDEJOVICE:
				case CzechRepublic.OKRES_CESKY_KRUMLOV:
				case CzechRepublic.OKRES_KLATOVY:
				case CzechRepublic.OKRES_TRUTNOV:
				case CzechRepublic.OKRES_PARDUBICE:
				case CzechRepublic.OKRES_CHRUDIM:
				case CzechRepublic.OKRES_SVITAVY:
				case CzechRepublic.OKRES_USTI_NAD_ORLICI:
				case CzechRepublic.OKRES_OSTRAVA_MESTO:
				{
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 10, 2014, FEBRUARY, 16)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 16, 2015, FEBRUARY, 22)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 22, 2016, FEBRUARY, 28)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, MARCH, 6, 2017, MARCH, 12)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, MARCH, 12, 2018, MARCH, 18)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 4, 2019, FEBRUARY, 10)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, FEBRUARY, 10, 2020, FEBRUARY, 16)) {
						return true;
					}

					break;
				}

				case CzechRepublic.MESTSKA_CAST_PRAHA_1:
				case CzechRepublic.MESTSKA_CAST_PRAHA_2:
				case CzechRepublic.MESTSKA_CAST_PRAHA_3:
				case CzechRepublic.MESTSKA_CAST_PRAHA_4:
				case CzechRepublic.MESTSKA_CAST_PRAHA_5:
				case CzechRepublic.MESTSKA_CAST_PRAHA_11:
				case CzechRepublic.MESTSKA_CAST_PRAHA_12:
				case CzechRepublic.MESTSKA_CAST_PRAHA_13:
				case CzechRepublic.MESTSKA_CAST_PRAHA_16:
				case CzechRepublic.MESTSKA_CAST_PRAHA_KUNRATICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_LIBUS:
				case CzechRepublic.MESTSKA_CAST_PRAHA_LIPENCE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_LOCHKOV:
				case CzechRepublic.MESTSKA_CAST_PRAHA_REPORYJE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_SLIVENEC:
				case CzechRepublic.MESTSKA_CAST_PRAHA_SEBEROV:
				case CzechRepublic.MESTSKA_CAST_PRAHA_UJEZD:
				case CzechRepublic.MESTSKA_CAST_PRAHA_VELKA_CHUCHLE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_ZBRASLAV:
				case CzechRepublic.MESTSKA_CAST_PRAHA_ZLICIN:

				case CzechRepublic.OKRES_BLANSKO:
				case CzechRepublic.OKRES_BRNO_MESTO:
				case CzechRepublic.OKRES_BRNO_VENKOV:
				case CzechRepublic.OKRES_BRECLAV:
				case CzechRepublic.OKRES_HODONIN:
				case CzechRepublic.OKRES_VYSKOV:
				case CzechRepublic.OKRES_ZNOJMO:
				case CzechRepublic.OKRES_DOMAZLICE:
				case CzechRepublic.OKRES_TACHOV:
				case CzechRepublic.OKRES_LOUNY:
				case CzechRepublic.OKRES_KARVINA:
				{
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 17, 2014, FEBRUARY, 23)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 23, 2015, MARCH, 1)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 29, 2016, MARCH, 6)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, MARCH, 13, 2017, MARCH, 19)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, FEBRUARY, 5, 2018, FEBRUARY, 11)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 11, 2019, FEBRUARY, 17)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, FEBRUARY, 17, 2020, FEBRUARY, 23)) {
						return true;
					}

					break;
				}

				case CzechRepublic.MESTSKA_CAST_PRAHA_6:
				case CzechRepublic.MESTSKA_CAST_PRAHA_7:
				case CzechRepublic.MESTSKA_CAST_PRAHA_8:
				case CzechRepublic.MESTSKA_CAST_PRAHA_9:
				case CzechRepublic.MESTSKA_CAST_PRAHA_10:
				case CzechRepublic.MESTSKA_CAST_PRAHA_14:
				case CzechRepublic.MESTSKA_CAST_PRAHA_15:
				case CzechRepublic.MESTSKA_CAST_PRAHA_17:
				case CzechRepublic.MESTSKA_CAST_PRAHA_18:
				case CzechRepublic.MESTSKA_CAST_PRAHA_19:
				case CzechRepublic.MESTSKA_CAST_PRAHA_20:
				case CzechRepublic.MESTSKA_CAST_PRAHA_21:
				case CzechRepublic.MESTSKA_CAST_PRAHA_22:
				case CzechRepublic.MESTSKA_CAST_PRAHA_BECHOVICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_BENICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_BREZINEVES:
				case CzechRepublic.MESTSKA_CAST_PRAHA_CAKOVICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_DABLICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_DOLNI_CHABRY:
				case CzechRepublic.MESTSKA_CAST_PRAHA_DOLNI_MECHOLUPY:
				case CzechRepublic.MESTSKA_CAST_PRAHA_DOLNI_POCERNICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_DUBEC:
				case CzechRepublic.MESTSKA_CAST_PRAHA_KLANOVICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_KOLODEJE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_KOLOVRATY:
				case CzechRepublic.MESTSKA_CAST_PRAHA_KRALOVICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_KRESLICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_LYSOLAJE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_NEBUSICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_NEDVEZI:
				case CzechRepublic.MESTSKA_CAST_PRAHA_PETROVICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_PREDNI_KOPANINA:
				case CzechRepublic.MESTSKA_CAST_PRAHA_SATALICE:
				case CzechRepublic.MESTSKA_CAST_PRAHA_SUCHDOL:
				case CzechRepublic.MESTSKA_CAST_PRAHA_STERBOHOLY:
				case CzechRepublic.MESTSKA_CAST_PRAHA_TROJA:
				case CzechRepublic.MESTSKA_CAST_PRAHA_VINOR:

				case CzechRepublic.OKRES_CHEB:
				case CzechRepublic.OKRES_KARLOVY_VARY:
				case CzechRepublic.OKRES_SOKOLOV:
				case CzechRepublic.OKRES_NYMBURK:
				case CzechRepublic.OKRES_JINDRICHUV_HRADEC:
				case CzechRepublic.OKRES_LITOMERICE:
				case CzechRepublic.OKRES_DECIN:
				case CzechRepublic.OKRES_PREROV:
				case CzechRepublic.OKRES_FRYDEK_MISTEK:
				{
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 24, 2014, MARCH, 2)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, MARCH, 2, 2015, MARCH, 8)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, MARCH, 7, 2016, MARCH, 13)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, FEBRUARY, 6, 2017, FEBRUARY, 12)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, FEBRUARY, 12, 2018, FEBRUARY, 18)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 18, 2019, FEBRUARY, 24)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, FEBRUARY, 24, 2020, MARCH, 1)) {
						return true;
					}

					break;
				}

				case CzechRepublic.OKRES_KROMERIZ:
				case CzechRepublic.OKRES_UHERSKE_HRADISTE:
				case CzechRepublic.OKRES_VSETIN:
				case CzechRepublic.OKRES_ZLIN:
				case CzechRepublic.OKRES_PRAHA_VYCHOD:
				case CzechRepublic.OKRES_PRAHA_ZAPAD:
				case CzechRepublic.OKRES_MELNIK:
				case CzechRepublic.OKRES_RAKOVNIK:
				case CzechRepublic.OKRES_PLZEN_MESTO:
				case CzechRepublic.OKRES_PLZEN_SEVER:
				case CzechRepublic.OKRES_PLZEN_JIH:
				case CzechRepublic.OKRES_HRADEC_KRALOVE:
				case CzechRepublic.OKRES_TEPLICE:
				case CzechRepublic.OKRES_NOVY_JICIN:
				{
					// 2014
					if (isInRange(temporal, 2014, MARCH, 3, 2014, MARCH, 9)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, MARCH, 9, 2015, MARCH, 15)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 1, 2016, FEBRUARY, 7)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, FEBRUARY, 13, 2017, FEBRUARY, 19)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, FEBRUARY, 19, 2018, FEBRUARY, 25)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 25, 2019, MARCH, 3)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, MARCH, 2, 2020, MARCH, 8)) {
						return true;
					}

					break;
				}

				case CzechRepublic.OKRES_CESKA_LIPA:
				case CzechRepublic.OKRES_JABLONEC_NAD_NISOU:
				case CzechRepublic.OKRES_LIBEREC:
				case CzechRepublic.OKRES_SEMILY:
				case CzechRepublic.OKRES_HAVLICKUV_BROD:
				case CzechRepublic.OKRES_JIHLAVA:
				case CzechRepublic.OKRES_PELHRIMOV:
				case CzechRepublic.OKRES_TREBIC:
				case CzechRepublic.OKRES_ZDAR_NAD_SAZAVOU:
				case CzechRepublic.OKRES_KLADNO:
				case CzechRepublic.OKRES_KOLIN:
				case CzechRepublic.OKRES_KUTNA_HORA:
				case CzechRepublic.OKRES_PISEK:
				case CzechRepublic.OKRES_NACHOD:
				case CzechRepublic.OKRES_BRUNTAL:
				{
					// 2014
					if (isInRange(temporal, 2014, MARCH, 10, 2014, MARCH, 16)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 2, 2015, FEBRUARY, 8)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 8, 2016, FEBRUARY, 14)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, FEBRUARY, 20, 2017, FEBRUARY, 26)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, FEBRUARY, 26, 2018, MARCH, 4)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, MARCH, 4, 2019, MARCH, 10)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, MARCH, 9, 2020, MARCH, 15)) {
						return true;
					}

					break;
				}

				case CzechRepublic.OKRES_PROSTEJOV: { // Changed starting 2017
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 17, 2014, FEBRUARY, 23)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 23, 2015, MARCH, 1)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 29, 2016, MARCH, 6)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, MARCH, 6, 2017, MARCH, 12)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, MARCH, 12, 2018, MARCH, 18)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 4, 2019, FEBRUARY, 10)) {
						return true;
					}
					// 2020
					if (isInRange(temporal, 2020, FEBRUARY, 10, 2020, FEBRUARY, 16)) {
						return true;
					}

					break;
				}
			}
		}

		return false;
	}

	private static boolean isHoliday_denmark(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 5)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 24, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, maundyThursday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, storeBededag())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Fixed holidays
			// None

			// Movable holidays
			if (is(temporal, ascensionThursday(), nextDay())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_estonia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 24)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 23)) {
				return true;
			}
			if (is(temporal, JUNE, 24)) {
				return true;
			}
			if (is(temporal, AUGUST, 20)) {
				return true;
			}
			if (is(temporal, DECEMBER, 24)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_faroeIslands(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, APRIL, 25)) {
				return true;
			}
			if (is(temporal, JULY, 29)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 24, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, maundyThursday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, storeBededag())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_finland(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 6)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, MonthDay.of(JUNE, 19), next(SATURDAY))) { // First Saturday after 19 June
				return true;
			}
			if (is(temporal, MonthDay.of(OCTOBER, 30), next(SATURDAY))) { // First Saturday after 30 October
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_france(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 8)) {
				return true;
			}
			if (is(temporal, JULY, 14)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 11)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(2).getCode();
			switch (regionCode) {
				case France.DEPARTEMENT_DE_LA_MOSELLE:
				case France.DEPARTEMENT_DU_BAS_RHIN:
				case France.DEPARTEMENT_DU_HAUT_RHIN: {
					// Fixed holidays
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_georgia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (isInRange(temporal, JANUARY, 1, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, JANUARY, 7)) {
				return true;
			}
			if (is(temporal, JANUARY, 19)) {
				return true;
			}
			if (is(temporal, MARCH, 3)) {
				return true;
			}
			if (is(temporal, MARCH, 8)) {
				return true;
			}
			if (is(temporal, APRIL, 9)) {
				return true;
			}
			if (is(temporal, MAY, 9)) {
				return true;
			}
			if (is(temporal, MAY, 12)) {
				return true;
			}
			if (is(temporal, MAY, 26)) {
				return true;
			}
			if (is(temporal, AUGUST, 28)) {
				return true;
			}
			if (is(temporal, OCTOBER, 14)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 23)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, orthodoxGoodFriday())) {
				return true;
			}
			if (is(temporal, orthodoxHolySaturday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterSunday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_germany(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, OCTOBER, 3)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}

			// One-time holidays
			if (is(temporal, 2017, OCTOBER, 31)) {
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Germany.FREISTAAT_BAYERN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) { // Only communities with mostly Catholic populations
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Germany.FREISTAAT_SACHSEN: {
					// Fixed holidays
					if (is(temporal, OCTOBER, 31)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, corpusChristi())) { // Only some localities
						return true;
					}
					if (is(temporal, bussUndBettag())) {
						return true;
					}

					break;
				}
				case Germany.FREISTAAT_THUERINGEN: {
					// Fixed holidays
					if (is(temporal, OCTOBER, 31)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, corpusChristi())) { // Only some localities
						return true;
					}

					break;
				}
				case Germany.LAND_BADEN_WUERTTEMBERG: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Germany.LAND_BRANDENBURG: {
					// Fixed holidays
					if (is(temporal, OCTOBER, 31)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, easterSunday())) {
						return true;
					}
					if (is(temporal, whitSunday())) {
						return true;
					}

					break;
				}
				case Germany.LAND_HESSEN: {
					// Fixed holidays
					// None

					// Movable holidays
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Germany.LAND_MECKLENBURG_VORPOMMERN: {
					// Fixed holidays
					if (is(temporal, OCTOBER, 31)) {
						return true;
					}

					// Movable holidays
					// None

					break;
				}
				case Germany.LAND_NORDRHEIN_WESTFALEN:
				case Germany.LAND_RHEINLAND_PFALZ: {
					// Fixed holidays
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Germany.SAARLAND: {
					// Fixed holidays
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Germany.LAND_SACHSEN_ANHALT: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) {
						return true;
					}
					if (is(temporal, OCTOBER, 31)) {
						return true;
					}

					// Movable holidays
					// None

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Fixed holidays
			if (is(temporal, DECEMBER, 24)) {
				return true;
			}
			if (is(temporal, DECEMBER, 31)) {
				return true;
			}

			// Movable holidays
			// None
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_greenland(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, JUNE, 21)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 24, DECEMBER, 26)) {
				return true;
			}
			if (is(temporal, DECEMBER, 31)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, maundyThursday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, storeBededag())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_guernsey(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 2) && is(temporal.with(MonthDay.of(JANUARY, 1)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 1)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}
			if (is(temporal, DECEMBER, 26)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SUNDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SUNDAY)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, MAY, firstInMonth(MONDAY))) { // First Monday in May
				return true;
			}
			if (is(temporal, MAY, lastInMonth(MONDAY))) { // Last Monday in May
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Guernsey.JURISDICTION_OF_ALDERNEY: {
					// Fixed holidays
					if (is(temporal, DECEMBER, 15)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, AUGUST, firstInMonth(MONDAY))) { // First Monday in August
						return true;
					}

					break;
				}
				case Guernsey.JURISDICTION_OF_GUERNSEY:
				case Guernsey.JURISDICTION_OF_SARK: {
					// Fixed holidays
					if (is(temporal, MAY, 9)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, AUGUST, lastInMonth(MONDAY))) { // Last Monday in August
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_hungary(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MARCH, 15)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 20)) {
				return true;
			}
			if (is(temporal, OCTOBER, 23)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday()) && (temporal.get(YEAR) >= 2017)) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_iceland(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 17)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, maundyThursday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
			if (is(temporal, MonthDay.of(APRIL, 18), next(THURSDAY))) { // First Thursday after 18 April
				return true;
			}
			if (is(temporal, AUGUST, firstInMonth(MONDAY))) { // First Monday of August
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_isleOfMan(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 2) && is(temporal.with(MonthDay.of(JANUARY, 1)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 1)), SATURDAY)) {
				return true;
			}
			if (is(temporal, JULY, 5)) {
				return true;
			}
			if (is(temporal, JULY, 6) && is(temporal.with(MonthDay.of(JULY, 5)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JULY, 7) && is(temporal.with(MonthDay.of(JULY, 5)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}
			if (is(temporal, DECEMBER, 26)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SUNDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SUNDAY)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, MAY, firstInMonth(MONDAY))) { // First Monday in May
				return true;
			}
			if (is(temporal, MAY, lastInMonth(MONDAY))) { // Last Monday in May
				return true;
			}
			if (is(temporal, MonthDay.of(JUNE, 5), next(FRIDAY))) { // First Friday after 5 June
				return true;
			}
			if (is(temporal, AUGUST, lastInMonth(MONDAY))) { // Last Monday in August
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_italy(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, APRIL, 25)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 2)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Italy.REGIONE_AUTONOMA_TRENTINO_ALTO_ADIGE: {
					// Fixed holidays
					// None

					// Movable holidays
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_jersey(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 2) && is(temporal.with(MonthDay.of(JANUARY, 1)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 1)), SATURDAY)) {
				return true;
			}
			if (is(temporal, MAY, 9)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}
			if (is(temporal, DECEMBER, 26)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SUNDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SUNDAY)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, MAY, firstInMonth(MONDAY))) { // First Monday in May
				return true;
			}
			if (is(temporal, MAY, lastInMonth(MONDAY))) { // Last Monday in May
				return true;
			}
			if (is(temporal, AUGUST, lastInMonth(MONDAY))) { // Last Monday in August
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_latvia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 4)) {
				return true;
			}
			if (isInRange(temporal, JUNE, 23, JUNE, 24)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 18)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 24, DECEMBER, 26)) {
				return true;
			}
			if (is(temporal, DECEMBER, 31)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_liechtenstein(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 2)) {
				return true;
			}
			if (is(temporal, MARCH, 19)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 8)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
			if (is(temporal, corpusChristi())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, DECEMBER, 24)) {
				return true;
			}
			if (is(temporal, DECEMBER, 31)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, shroveTuesday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_malta(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 10)) {
				return true;
			}
			if (is(temporal, MARCH, 19)) {
				return true;
			}
			if (is(temporal, MARCH, 31)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 7)) {
				return true;
			}
			if (is(temporal, JUNE, 29)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 8)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 21)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (is(temporal, DECEMBER, 13)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Fixed holidays
			if (is(temporal, MARCH, 28)) {
				return true;
			}
			if (is(temporal, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			// None
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_moldova(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (isInRange(temporal, JANUARY, 7, JANUARY, 8)) {
				return true;
			}
			if (is(temporal, MARCH, 8)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 9)) {
				return true;
			}
			if (is(temporal, JUNE, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 27)) {
				return true;
			}
			if (is(temporal, AUGUST, 31)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, orthodoxEasterSunday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterMonday())) {
				return true;
			}
			if (is(temporal, pastileBlajinilor())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_netherlands(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, APRIL, 26) && is(temporal.with(MonthDay.of(APRIL, 27)), SUNDAY)) {
				return true;
			}
			if (is(temporal, APRIL, 27) && !is(temporal, SUNDAY)) {
				return true;
			}
			if (is(temporal, MAY, 5) && ((temporal.get(ChronoField.YEAR) % 5) == 0)) { // Every year ending in 0 or 5
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Fixed holidays
			// None

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_norway(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 17)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, maundyThursday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, whitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_poland(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 3)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 11)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, corpusChristi())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_portugal(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, APRIL, 25)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 10)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, shroveTuesday())) {
				return true;
			}
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Portugal.REGIAO_AUTONOMA_DA_MADEIRA: {
					// Fixed holidays
					if (is(temporal, JULY, 1)) {
						return true;
					}

					// Movable holidays
					// None

					break;
				}
				case Portugal.REGIAO_AUTONOMA_DOS_ACORES: {
					// Fixed holidays
					// None

					// Movable holidays
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_republicOfIreland(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, MARCH, 17)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, MAY, firstInMonth(MONDAY))) { // First Monday in May
				return true;
			}
			if (is(temporal, JUNE, firstInMonth(MONDAY))) { // First Monday in June
				return true;
			}
			if (is(temporal, AUGUST, firstInMonth(MONDAY))) { // First Monday in August
				return true;
			}
			if (is(temporal, OCTOBER, firstInMonth(MONDAY))) { // First Monday in October
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_romania(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, JANUARY, 24)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (isInRange(temporal, NOVEMBER, 30, DECEMBER, 1)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, orthodoxEasterSunday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterMonday())) {
				return true;
			}
			if (is(temporal, orthodoxWhitSunday())) {
				return true;
			}
			if (is(temporal, orthodoxWhitMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_serbia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 2)) {
				return true;
			}
			if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 1)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 2)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JANUARY, 7)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 15)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 16)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 17) && is(temporal.with(MonthDay.of(FEBRUARY, 15)), SUNDAY)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 17) && is(temporal.with(MonthDay.of(FEBRUARY, 16)), SUNDAY)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 2)) {
				return true;
			}
			if (is(temporal, MAY, 3) && is(temporal.with(MonthDay.of(MAY, 1)), SUNDAY)) {
				return true;
			}
			if (is(temporal, MAY, 3) && is(temporal.with(MonthDay.of(MAY, 2)), SUNDAY)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 11)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 12) && is(temporal.with(MonthDay.of(NOVEMBER, 11)), SUNDAY)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, orthodoxGoodFriday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterSunday())) {
				return true;
			}
			if (is(temporal, orthodoxEasterMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_slovakia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, MAY, 8)) {
				return true;
			}
			if (is(temporal, JULY, 5)) {
				return true;
			}
			if (is(temporal, AUGUST, 29)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 1)) {
				return true;
			}
			if (is(temporal, SEPTEMBER, 15)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 17)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 24, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// see https://www.minedu.sk/terminy-prazdnin/
		if (types.contains(SCHOOLS_ONLY)) {
			// 2014
			if (isInRange(temporal, 2014, JANUARY, 1, 2014, JANUARY, 7)) {
				return true;
			}
			if (is(temporal, 2014, FEBRUARY, 3)) {
				return true;
			}
			if (isInRange(temporal, 2014, APRIL, 17, 2014, APRIL, 22)) {
				return true;
			}
			if (isInRange(temporal, 2014, JUNE, 30, 2014, AUGUST, 29)) {
				return true;
			}

			// 2014/2015
			if (isInRange(temporal, 2014, OCTOBER, 30, 2014, OCTOBER, 31)) {
				return true;
			}
			if (isInRange(temporal, 2014, DECEMBER, 22, 2015, JANUARY, 7)) {
				return true;
			}
			if (is(temporal, 2015, FEBRUARY, 2)) {
				return true;
			}
			if (isInRange(temporal, 2015, APRIL, 2, 2015, APRIL, 7)) {
				return true;
			}
			if (isInRange(temporal, 2015, JULY, 1, 2015, AUGUST, 31)) {
				return true;
			}

			// 2015/2016
			if (isInRange(temporal, 2015, OCTOBER, 29, 2015, OCTOBER, 30)) {
				return true;
			}
			if (isInRange(temporal, 2015, DECEMBER, 23, 2016, JANUARY, 7)) {
				return true;
			}
			if (is(temporal, 2016, FEBRUARY, 1)) {
				return true;
			}
			if (isInRange(temporal, 2016, MARCH, 24, 2016, MARCH, 29)) {
				return true;
			}
			if (isInRange(temporal, 2016, JULY, 1, 2016, SEPTEMBER, 2)) {
				return true;
			}

			// 2016/2017
			if (isInRange(temporal, 2016, OCTOBER, 28, 2016, OCTOBER, 31)) {
				return true;
			}
			if (isInRange(temporal, 2016, DECEMBER, 23, 2017, JANUARY, 5)) {
				return true;
			}
			if (is(temporal, 2017, FEBRUARY, 3)) {
				return true;
			}
			if (isInRange(temporal, 2017, APRIL, 13, 2017, APRIL, 18)) {
				return true;
			}
			if (isInRange(temporal, 2017, JULY, 3, 2017, AUGUST, 31)) {
				return true;
			}

			// 2017/2018
			if (isInRange(temporal, 2017, OCTOBER, 30, 2017, OCTOBER, 31)) {
				return true;
			}
			if (isInRange(temporal, 2017, DECEMBER, 23, 2018, JANUARY, 5)) {
				return true;
			}
			if (is(temporal, 2018, FEBRUARY, 2)) {
				return true;
			}
			if (isInRange(temporal, 2018, MARCH, 29, 2018, APRIL, 3)) {
				return true;
			}
			if (isInRange(temporal, 2018, JULY, 2, 2018, AUGUST, 31)) {
				return true;
			}

			// 2018/2019
			if (isInRange(temporal, 2018, OCTOBER, 31, 2018, NOVEMBER, 2)) {
				return true;
			}
			if (isInRange(temporal, 2018, DECEMBER, 22, 2019, JANUARY, 7)) {
				return true;
			}
			if (is(temporal, 2019, FEBRUARY, 1)) {
				return true;
			}
			if (isInRange(temporal, 2019, APRIL, 18, 2019, APRIL, 23)) {
				return true;
			}
			if (isInRange(temporal, 2019, JULY, 1, 2019, AUGUST, 31)) {
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Slovakia.BANSKOBYSTRICKY_KRAJ:
				case Slovakia.ZILINSKY_KRAJ:
				case Slovakia.TRENCIANSKY_KRAJ:
				{
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 17, 2014, FEBRUARY, 21)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 16, 2015, FEBRUARY, 20)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 29, 2016, MARCH, 4)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, FEBRUARY, 27, 2017, MARCH, 3)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, FEBRUARY, 19, 2018, FEBRUARY, 23)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, MARCH, 4, 2018, MARCH, 9)) {
						return true;
					}

					break;
				}

				case Slovakia.BRATISLAVSKY_KRAJ:
				case Slovakia.NITRIANSKY_KRAJ:
				case Slovakia.TRNAVSKY_KRAJ:
				{
					// 2014
					if (isInRange(temporal, 2014, FEBRUARY, 24, 2014, FEBRUARY, 28)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, MARCH, 2, 2015, MARCH, 6)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 22, 2016, FEBRUARY, 26)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, FEBRUARY, 20, 2017, FEBRUARY, 24)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, MARCH, 5, 2018, MARCH, 9)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 25, 2018, MARCH, 1)) {
						return true;
					}

					break;
				}

				case Slovakia.KOSICKY_KRAJ:
				case Slovakia.PRESOVSKY_KRAJ:
				{
					// 2014
					if (isInRange(temporal, 2014, MARCH, 3, 2014, MARCH, 7)) {
						return true;
					}
					// 2015
					if (isInRange(temporal, 2015, FEBRUARY, 23, 2015, FEBRUARY, 27)) {
						return true;
					}
					// 2016
					if (isInRange(temporal, 2016, FEBRUARY, 15, 2016, FEBRUARY, 19)) {
						return true;
					}
					// 2017
					if (isInRange(temporal, 2017, MARCH, 6, 2017, MARCH, 10)) {
						return true;
					}
					// 2018
					if (isInRange(temporal, 2018, FEBRUARY, 26, 2018, MARCH, 2)) {
						return true;
					}
					// 2019
					if (isInRange(temporal, 2019, FEBRUARY, 18, 2018, FEBRUARY, 22)) {
						return true;
					}

					break;
				}
			}
		}

		return false;
	}

	private static boolean isHoliday_slovenia(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 8)) {
				return true;
			}
			if (is(temporal, APRIL, 27)) {
				return true;
			}
			if (isInRange(temporal, MAY, 1, MAY, 2)) {
				return true;
			}
			if (is(temporal, JUNE, 25)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, OCTOBER, 31)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_sweden(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 6)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
			if (is(temporal, ascensionThursday())) {
				return true;
			}
			if (is(temporal, whitSunday())) {
				return true;
			}
			if (is(temporal, MonthDay.of(JUNE, 19), next(SATURDAY))) { // First Saturday after 19 June
				return true;
			}
			if (is(temporal, MonthDay.of(OCTOBER, 30), next(SATURDAY))) { // First Saturday after 30 October
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_switzerland(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, AUGUST, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, ascensionThursday())) {
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Switzerland.CANTON_DE_VAUD: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, SEPTEMBER, dayOfWeekInMonth(3, SUNDAY), next(MONDAY))) { // Monday after third Sunday in September
						return true;
					}

					break;
				}
				case Switzerland.CANTON_DU_VALAIS: {
					// Fixed holidays
					if (is(temporal, MARCH, 19)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.ETAT_DE_FRIBOURG: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MAY, 1)) { // Only from 12:00
						return true;
					}
					if (is(temporal, AUGUST, 15)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 8)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) { // Only in some municipalities
						return true;
					}

					break;
				}
				case Switzerland.KANTON_AARGAU: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, MAY, 1)) { // If not Monday, only from 12:00
						return true;
					}
					if (is(temporal, AUGUST, 15)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 8)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 26)) { // Only in some municipalities
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) { // Only in some municipalities
						return true;
					}
					if (is(temporal, whitMonday())) { // Only in some municipalities
						return true;
					}
					if (is(temporal, corpusChristi())) { // Only in some municipalities
						return true;
					}

					break;
				}
				case Switzerland.KANTON_APPENZELL_AUSSERRHODEN: {
					// Fixed holidays
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_APPENZELL_INNERRHODEN: {
					// Fixed holidays
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, SEPTEMBER, 22)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_BASEL_LANDSCHAFT: {
					// Fixed holidays
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) { // Only in some municipalities
						return true;
					}

					break;
				}
				case Switzerland.KANTON_BASEL_STADT: {
					// Fixed holidays
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_BERN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_GLARUS: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, APRIL, firstInMonth(THURSDAY))) { // First Thursday in April
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_GRAUBUENDEN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, MARCH, 19)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, JUNE, 29)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, AUGUST, 15)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 8)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) { // Only in some municipalities
						return true;
					}

					break;
				}
				case Switzerland.KANTON_LUZERN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MARCH, 19)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, JUNE, 29)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_NIDWALDEN: {
					// Fixed holidays
					if (is(temporal, MARCH, 19)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_OBWALDEN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, SEPTEMBER, 25)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_SANKT_GALLEN: {
					// Fixed holidays
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_SCHAFFHAUSEN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_SCHWYZ: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) {
						return true;
					}
					if (is(temporal, MARCH, 19)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_SOLOTHURN: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MARCH, 19)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, MAY, 1)) { // Only from 12:00
						return true;
					}
					if (is(temporal, AUGUST, 15)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 8)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, DECEMBER, 26)) { // Only in some municipalities
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) { // Only in some municipalities
						return true;
					}
					if (is(temporal, whitMonday())) { // Only in some municipalities
						return true;
					}
					if (is(temporal, corpusChristi())) { // Only in some municipalities
						return true;
					}

					break;
				}
				case Switzerland.KANTON_THURGAU: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_URI: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) {
						return true;
					}
					if (is(temporal, MARCH, 19)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_ZUERICH: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}

					break;
				}
				case Switzerland.KANTON_ZUG: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MARCH, 19)) { // Only in some municipalities
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.REPUBBLICA_E_CANTONE_TICINO: {
					// Fixed holidays
					if (is(temporal, JANUARY, 6)) {
						return true;
					}
					if (is(temporal, MARCH, 19)) {
						return true;
					}
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, JUNE, 29)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}
					if (is(temporal, DECEMBER, 8)) {
						return true;
					}
					if (is(temporal, DECEMBER, 26)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
				case Switzerland.REPUBLIQUE_ET_CANTON_DE_GENEVE: {
					// Fixed holidays
					if (is(temporal, DECEMBER, 31)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, MonthDay.of(SEPTEMBER, 1), nextOrSame(SUNDAY), next(THURSDAY))) { // First Thursday after first Sunday in September
						return true;
					}

					break;
				}
				case Switzerland.REPUBLIQUE_ET_CANTON_DE_NEUCHATEL: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MARCH, 1)) {
						return true;
					}
					if (is(temporal, MAY, 1)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) { // Only in some municipalities
						return true;
					}

					break;
				}
				case Switzerland.REPUBLIQUE_ET_CANTON_DU_JURA: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, MAY, 1)) {
						return true;
					}
					if (is(temporal, JUNE, 23)) {
						return true;
					}
					if (is(temporal, AUGUST, 15)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 1)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, goodFriday())) {
						return true;
					}
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, whitMonday())) {
						return true;
					}
					if (is(temporal, corpusChristi())) {
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		if (types.contains(BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY)) {
			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case Switzerland.CANTON_DU_VALAIS: {
					// Fixed holidays
					// None

					// Movable holidays
					if (is(temporal, SEPTEMBER, dayOfWeekInMonth(3, SUNDAY), next(MONDAY))) { // Monday after third Sunday in September
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	// see https://www.gov.uk/bank-holidays
	private static boolean isHoliday_unitedKingdom(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 2) && is(temporal.with(MonthDay.of(JANUARY, 1)), SUNDAY)) {
				return true;
			}
			if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 1)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 25)) {
				return true;
			}
			if (is(temporal, DECEMBER, 26)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 27) && is(temporal.with(MonthDay.of(DECEMBER, 25)), SUNDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SATURDAY)) {
				return true;
			}
			if (is(temporal, DECEMBER, 28) && is(temporal.with(MonthDay.of(DECEMBER, 26)), SUNDAY)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, goodFriday())) {
				return true;
			}
			if (is(temporal, MAY, firstInMonth(MONDAY))) { // First Monday in May
				return true;
			}
			if (is(temporal, MAY, lastInMonth(MONDAY))) { // Last Monday in May
				return true;
			}

			// Regional holidays
			String regionCode = region.atLevel(1).getCode();
			switch (regionCode) {
				case UnitedKingdom.COUNTRY_OF_ENGLAND:
				case UnitedKingdom.COUNTRY_OF_WALES: {
					// Fixed holidays
					// None

					// Movable holidays
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, AUGUST, lastInMonth(MONDAY))) { // Last Monday in August
						return true;
					}

					break;
				}
				case UnitedKingdom.COUNTRY_OF_NORTHERN_IRELAND: {
					// Fixed holidays
					if (is(temporal, MARCH, 17)) {
						return true;
					}
					if (is(temporal, MARCH, 18) && is(temporal.with(MonthDay.of(MARCH, 17)), SUNDAY)) {
						return true;
					}
					if (is(temporal, MARCH, 19) && is(temporal.with(MonthDay.of(MARCH, 17)), SATURDAY)) {
						return true;
					}
					if (is(temporal, JULY, 12)) {
						return true;
					}
					if (is(temporal, JULY, 13) && is(temporal.with(MonthDay.of(JULY, 12)), SUNDAY)) {
						return true;
					}
					if (is(temporal, JULY, 14) && is(temporal.with(MonthDay.of(JULY, 12)), SATURDAY)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, easterMonday())) {
						return true;
					}
					if (is(temporal, AUGUST, lastInMonth(MONDAY))) { // Last Monday in August
						return true;
					}

					break;
				}
				case UnitedKingdom.COUNTRY_OF_SCOTLAND: {
					// Fixed holidays
					if (is(temporal, JANUARY, 2)) {
						return true;
					}
					if (is(temporal, JANUARY, 3) && is(temporal.with(MonthDay.of(JANUARY, 2)), MONDAY)) {
						return true;
					}
					if (is(temporal, JANUARY, 4) && is(temporal.with(MonthDay.of(JANUARY, 2)), SUNDAY)) {
						return true;
					}
					if (is(temporal, JANUARY, 4) && is(temporal.with(MonthDay.of(JANUARY, 2)), SATURDAY)) {
						return true;
					}
					if (is(temporal, NOVEMBER, 30)) {
						return true;
					}
					if (is(temporal, DECEMBER, 1) && is(temporal.with(MonthDay.of(NOVEMBER, 30)), SUNDAY)) {
						return true;
					}
					if (is(temporal, DECEMBER, 2) && is(temporal.with(MonthDay.of(NOVEMBER, 30)), SATURDAY)) {
						return true;
					}

					// Movable holidays
					if (is(temporal, AUGUST, firstInMonth(MONDAY))) { // First Monday in August
						return true;
					}

					break;
				}
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

	private static boolean isHoliday_vaticanCity(Temporal temporal, GeoRegion region, Set<HolidayType> types) {
		/*
		 * HOLIDAYS OBSERVED GLOBALLY
		 */
		if (types.contains(PUBLIC)) {
			// Fixed holidays
			if (is(temporal, JANUARY, 1)) {
				return true;
			}
			if (is(temporal, JANUARY, 6)) {
				return true;
			}
			if (is(temporal, FEBRUARY, 11)) {
				return true;
			}
			if (is(temporal, MARCH, 13)) {
				return true;
			}
			if (is(temporal, MARCH, 19)) {
				return true;
			}
			if (is(temporal, APRIL, 23)) {
				return true;
			}
			if (is(temporal, MAY, 1)) {
				return true;
			}
			if (is(temporal, JUNE, 29)) {
				return true;
			}
			if (is(temporal, AUGUST, 14)) {
				return true;
			}
			if (is(temporal, AUGUST, 15)) {
				return true;
			}
			if (is(temporal, NOVEMBER, 1)) {
				return true;
			}
			if (is(temporal, DECEMBER, 8)) {
				return true;
			}
			if (isInRange(temporal, DECEMBER, 25, DECEMBER, 26)) {
				return true;
			}

			// Movable holidays
			if (is(temporal, easterSunday())) {
				return true;
			}
			if (is(temporal, easterMonday())) {
				return true;
			}
		}

		/*
		 * HOLIDAYS OBSERVED ONLY BY BANKS AND (MOST) FINANCIAL INSTITUTIONS
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY GOVERNMENT SERVICES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS AND UNIVERSITIES
		 */
		// None

		/*
		 * HOLIDAYS OBSERVED ONLY BY SCHOOLS
		 */
		// None

		return false;
	}

}
