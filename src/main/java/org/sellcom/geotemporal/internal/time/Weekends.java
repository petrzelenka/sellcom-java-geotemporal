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

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.sellcom.geotemporal.internal.time.TemporalUtils.isAnyOf;

import java.time.temporal.Temporal;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.geography.GeoRegionNotSupportedException;
import org.sellcom.geotemporal.geography.StandardGeoRegions;

public class Weekends {

	private Weekends() {
		// Utility class, not to be instantiated
	}


	public static boolean isWeekend(Temporal temporal, GeoRegion region) {
		switch (region.getCountryCode()) {
			case StandardGeoRegions.ANDORRA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.AUSTRIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.BELGIUM: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.CROATIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.CYPRUS: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.CZECH_REPUBLIC: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.DENMARK: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.ESTONIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.FAROE_ISLANDS: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.FINLAND: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.FRANCE: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.GEORGIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.GERMANY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.GREENLAND: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.GUERNSEY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.HUNGARY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.ICELAND: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.ISLE_OF_MAN: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.ITALY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.JERSEY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.LATVIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.LIECHTENSTEIN: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.MALTA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.MOLDOVA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.NETHERLANDS: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.NORWAY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.POLAND: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.PORTUGAL: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.REPUBLIC_OF_IRELAND: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.ROMANIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.SERBIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.SLOVAKIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.SLOVENIA: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.SWEDEN: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.SWITZERLAND: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.UNITED_KINGDOM: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			case StandardGeoRegions.VATICAN_CITY: {
				return isAnyOf(temporal, SATURDAY, SUNDAY);
			}
			default: {
				throw new GeoRegionNotSupportedException(String.format("Unsupported region: %s", region));
			}
		}
	}

}
