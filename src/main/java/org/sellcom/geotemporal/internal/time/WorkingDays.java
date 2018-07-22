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
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.sellcom.geotemporal.internal.time.TemporalUtils.is;

import java.time.temporal.Temporal;
import java.util.Optional;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.geography.GeoRegionNotSupportedException;
import org.sellcom.geotemporal.geography.StandardGeoRegions;

public class WorkingDays {

	private WorkingDays() {
		// Utility class, not to be instantiated
	}


	// Only contains exceptions to the general rules
	public static Optional<Boolean> isWorkingDay(Temporal temporal, GeoRegion region) {
		switch (region.getCountryCode()) {
			case StandardGeoRegions.ANDORRA: {
				return Optional.empty();
			}
			case StandardGeoRegions.AUSTRIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.BELGIUM: {
				return Optional.empty();
			}
			case StandardGeoRegions.CROATIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.CYPRUS: {
				return Optional.empty();
			}
			case StandardGeoRegions.CZECH_REPUBLIC: {
				return Optional.empty();
			}
			case StandardGeoRegions.DENMARK: {
				return Optional.empty();
			}
			case StandardGeoRegions.ESTONIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.FAROE_ISLANDS: {
				return Optional.empty();
			}
			case StandardGeoRegions.FINLAND: {
				return Optional.empty();
			}
			case StandardGeoRegions.FRANCE: {
				return Optional.empty();
			}
			case StandardGeoRegions.GEORGIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.GERMANY: {
				return Optional.empty();
			}
			case StandardGeoRegions.GREENLAND: {
				return Optional.empty();
			}
			case StandardGeoRegions.GUERNSEY: {
				return Optional.empty();
			}
			case StandardGeoRegions.HUNGARY: {
				return isWorkingDay_hungary(temporal, region);
			}
			case StandardGeoRegions.ICELAND: {
				return Optional.empty();
			}
			case StandardGeoRegions.ISLE_OF_MAN: {
				return Optional.empty();
			}
			case StandardGeoRegions.ITALY: {
				return Optional.empty();
			}
			case StandardGeoRegions.JERSEY: {
				return Optional.empty();
			}
			case StandardGeoRegions.LATVIA: {
				return isWorkingDay_latvia(temporal, region);
			}
			case StandardGeoRegions.LIECHTENSTEIN: {
				return Optional.empty();
			}
			case StandardGeoRegions.MALTA: {
				return Optional.empty();
			}
			case StandardGeoRegions.MOLDOVA: {
				return isWorkingDay_moldova(temporal, region);
			}
			case StandardGeoRegions.NETHERLANDS: {
				return Optional.empty();
			}
			case StandardGeoRegions.NORWAY: {
				return Optional.empty();
			}
			case StandardGeoRegions.POLAND: {
				return Optional.empty();
			}
			case StandardGeoRegions.PORTUGAL: {
				return Optional.empty();
			}
			case StandardGeoRegions.REPUBLIC_OF_IRELAND: {
				return Optional.empty();
			}
			case StandardGeoRegions.ROMANIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.SERBIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.SLOVAKIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.SLOVENIA: {
				return Optional.empty();
			}
			case StandardGeoRegions.SWEDEN: {
				return Optional.empty();
			}
			case StandardGeoRegions.SWITZERLAND: {
				return Optional.empty();
			}
			case StandardGeoRegions.UNITED_KINGDOM: {
				return Optional.empty();
			}
			case StandardGeoRegions.VATICAN_CITY: {
				return Optional.empty();
			}
			default: {
				throw new GeoRegionNotSupportedException(String.format("Unsupported region: %s", region));
			}
		}
	}


	// Lookup "munkanap-áthelyezések"
	private static Optional<Boolean> isWorkingDay_hungary(Temporal temporal, GeoRegion region) {
		// 2014
		if (is(temporal, 2014, MAY, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2014, MAY, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, OCTOBER, 18)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, OCTOBER, 24)) {
			return Optional.of(false);
		}
		if (is(temporal, 2014, DECEMBER, 13)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, DECEMBER, 24)) {
			return Optional.of(false);
		}

		// 2015
		if (is(temporal, 2015, JANUARY, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2015, JANUARY, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, AUGUST, 8)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, AUGUST, 21)) {
			return Optional.of(false);
		}
		if (is(temporal, 2015, DECEMBER, 12)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, DECEMBER, 24)) {
			return Optional.of(false);
		}

		// 2016
		if (is(temporal, 2016, MARCH, 5)) {
			return Optional.of(true);
		}
		if (is(temporal, 2016, MARCH, 14)) {
			return Optional.of(false);
		}
		if (is(temporal, 2016, OCTOBER, 15)) {
			return Optional.of(true);
		}
		if (is(temporal, 2016, OCTOBER, 31)) {
			return Optional.of(false);
		}

		// 2017
		// No exceptions

		// 2018
		if (is(temporal, 2018, MARCH, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, MARCH, 16)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, APRIL, 21)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, APRIL, 30)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, OCTOBER, 13)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, OCTOBER, 22)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, NOVEMBER, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, NOVEMBER, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, DECEMBER, 1)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, DECEMBER, 15)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, DECEMBER, 24)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, DECEMBER, 31)) {
			return Optional.of(false);
		}

		return Optional.empty();
	}

	// Lookup "darba dienu pārcelšanu"
	private static Optional<Boolean> isWorkingDay_latvia(Temporal temporal, GeoRegion region) {
		// 2014
		if (is(temporal, 2014, MAY, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2014, MAY, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, NOVEMBER, 17)) {
			return Optional.of(false);
		}
		if (is(temporal, 2014, NOVEMBER, 22)) {
			return Optional.of(true);
		}

		// 2015
		if (is(temporal, 2015, JANUARY, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2015, JANUARY, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, JUNE, 22)) {
			return Optional.of(false);
		}
		if (is(temporal, 2015, JUNE, 27)) {
			return Optional.of(true);
		}

		// 2016
		// No exceptions

		// 2017
		if (is(temporal, 2017, MAY, 5)) {
			return Optional.of(false);
		}
		if (is(temporal, 2017, MAY, 13)) {
			return Optional.of(true);
		}

		// 2018
		if (is(temporal, 2018, APRIL, 21)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, APRIL, 30)) {
			return Optional.of(false);
		}

		return Optional.empty();
	}

	// Lookup "transferul unei zile de odihnă"
	private static Optional<Boolean> isWorkingDay_moldova(Temporal temporal, GeoRegion region) {
		// 2014
		if (is(temporal, 2014, JANUARY, 6)) {
			return Optional.of(false);
		}
		if (is(temporal, 2014, JANUARY, 18)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, MAY, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2014, MAY, 17)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, DECEMBER, 20)) {
			return Optional.of(true);
		}
		if (is(temporal, 2014, DECEMBER, 25)) {
			return Optional.of(false);
		}

		// 2015
		if (is(temporal, 2015, JANUARY, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2015, JANUARY, 9)) {
			return Optional.of(false);
		}
		if (is(temporal, 2015, JANUARY, 17)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, JANUARY, 31)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, AUGUST, 22)) {
			return Optional.of(true);
		}
		if (is(temporal, 2015, AUGUST, 28)) {
			return Optional.of(false);
		}

		// 2016
		if (is(temporal, 2016, MARCH, 7)) {
			return Optional.of(false);
		}
		if (is(temporal, 2016, MARCH, 12)) {
			return Optional.of(true);
		}
		if (is(temporal, 2016, AUGUST, 29)) {
			return Optional.of(false);
		}
		if (is(temporal, 2016, AUGUST, 30)) {
			return Optional.of(false);
		}
		if (is(temporal, 2016, SEPTEMBER, 24)) {
			return Optional.of(true);
		}
		if (is(temporal, 2016, OCTOBER, 22)) {
			return Optional.of(true);
		}

		// 2017
		if (is(temporal, 2017, MAY, 8)) {
			return Optional.of(false);
		}
		if (is(temporal, 2017, MAY, 13)) {
			return Optional.of(true);
		}
		if (is(temporal, 2017, JUNE, 2)) {
			return Optional.of(false);
		}
		if (is(temporal, 2017, JUNE, 10)) {
			return Optional.of(true);
		}
		if (is(temporal, 2017, SEPTEMBER, 1)) {
			return Optional.of(false);
		}
		if (is(temporal, 2017, SEPTEMBER, 9)) {
			return Optional.of(true);
		}

		// 2018
		if (is(temporal, 2018, MARCH, 3)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, MARCH, 9)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, APRIL, 21)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, APRIL, 30)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, JUNE, 1)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, JUNE, 23)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, DECEMBER, 8)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, DECEMBER, 15)) {
			return Optional.of(true);
		}
		if (is(temporal, 2018, DECEMBER, 24)) {
			return Optional.of(false);
		}
		if (is(temporal, 2018, DECEMBER, 31)) {
			return Optional.of(false);
		}

		return Optional.empty();
	}

}
