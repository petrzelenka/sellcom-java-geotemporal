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

import static java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR;
import static org.sellcom.geotemporal.time.HolidayType.PUBLIC;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_AND_UNIVERSITIES_ONLY;
import static org.sellcom.geotemporal.time.HolidayType.SCHOOLS_ONLY;

import java.time.temporal.Temporal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.geography.GeoRegionNotSupportedException;
import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.internal.time.Holidays;
import org.sellcom.geotemporal.internal.time.Weekends;
import org.sellcom.geotemporal.internal.time.WorkingDays;

/**
 * Operations with {@link Temporal}s.
 *
 * @since 1.0
 */
public class Temporals {

	private static final Map<String, HolidayPredicate> customHolidayPredicates = new HashMap<>();

	private static final Map<String, GeoTemporalPredicate> customWeekendPredicates = new HashMap<>();

	private static final Map<String, GeoTemporalPredicate> customWorkingDayPredicates = new HashMap<>();


	private Temporals() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the ISO 8601 week number of the given temporal.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int getIsoWeekNumber(Temporal temporal) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");

		return temporal.get(WEEK_OF_WEEK_BASED_YEAR);
	}

	/**
	 * Checks whether the given temporal is a holiday of any of the given types in the given region.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 * @throws IllegalArgumentException if {@code types} is {@code null}
	 * @throws GeoRegionNotSupportedException if the region is not supported
	 *
	 * @since 1.0
	 */
	public static boolean isHoliday(Temporal temporal, GeoRegion region, EnumSet<HolidayType> types) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");
		Contract.checkArgument(region != null, "Region must not be null");
		Contract.checkArgument(types != null, "Holiday types must not be null");

		// Allow custom predicates to take precedence
		HolidayPredicate predicate = customHolidayPredicates.get(region.getCountryCode());
		if (predicate != null) {
			return predicate.test(temporal, region, types);
		}

		// Apply the default logic
		return Holidays.isHoliday(temporal, region, types);
	}

	/**
	 * Checks whether the given temporal is a public holiday in the given region.
	 * <p>
	 * Only considers public holidays ({@link HolidayType#PUBLIC}).
	 * To customise the holiday types, use {@link #isHoliday(Temporal, GeoRegion, EnumSet)} instead.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 * @throws GeoRegionNotSupportedException if the region is not supported
	 *
	 * @since 1.0
	 */
	public static boolean isPublicHoliday(Temporal temporal, GeoRegion region) {
		return isHoliday(temporal, region, EnumSet.of(PUBLIC));
	}

	/**
	 * Checks whether the given date is a school holiday in the given region.
	 * <p>
	 * Only considers school holidays ({@link HolidayType#SCHOOLS_AND_UNIVERSITIES_ONLY} and {@link HolidayType#SCHOOLS_ONLY}).
	 * To customise the holiday types, use {@link #isHoliday(Temporal, GeoRegion, EnumSet)} instead.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 * @throws GeoRegionNotSupportedException if the region is not supported
	 *
	 * @since 1.0
	 */
	public static boolean isSchoolHoliday(Temporal temporal, GeoRegion region) {
		return isHoliday(temporal, region, EnumSet.of(SCHOOLS_AND_UNIVERSITIES_ONLY, SCHOOLS_ONLY));
	}

	/**
	 * Checks whether the given temporal is a weekend in the given region.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 * @throws GeoRegionNotSupportedException if the region is not supported
	 *
	 * @since 1.0
	 */
	public static boolean isWeekend(Temporal temporal, GeoRegion region) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");
		Contract.checkArgument(region != null, "Region must not be null");

		// Allow custom predicates to take precedence
		GeoTemporalPredicate predicate = customWeekendPredicates.get(region.getCountryCode());
		if (predicate != null) {
			return predicate.test(temporal, region);
		}

		// Apply the default logic
		return Weekends.isWeekend(temporal, region);
	}

	/**
	 * Checks whether the given temporal is a working day in the given region.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 * @throws GeoRegionNotSupportedException if the region is not supported
	 *
	 * @since 1.0
	 */
	public static boolean isWorkingDay(Temporal temporal, GeoRegion region) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");
		Contract.checkArgument(region != null, "Region must not be null");

		// Allow custom predicates to take precedence
		GeoTemporalPredicate predicate = customWorkingDayPredicates.get(region.getCountryCode());
		if (predicate != null) {
			return predicate.test(temporal, region);
		}

		// Apply exceptions to the default logic
		Optional<Boolean> result = WorkingDays.isWorkingDay(temporal, region);
		if (result.isPresent()) {
			return result.get();
		}

		// Apply the default logic
		return !isWeekend(temporal, region) && !isPublicHoliday(temporal, region);
	}

	/**
	 * Registers a custom predicate for determining holidays in the given country.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code predicate} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void registerCustomHolidayPredicate(String countryCode, HolidayPredicate predicate) {
		Contract.checkArgument(countryCode != null, "Country code must not be null");
		Contract.checkArgument(predicate != null, "Holiday predicate must not be null");

		customHolidayPredicates.put(countryCode, predicate);
	}

	/**
	 * Registers a custom predicate for determining weekends in the given country.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code predicate} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void registerCustomWeekendPredicate(String countryCode, GeoTemporalPredicate predicate) {
		Contract.checkArgument(countryCode != null, "Country code must not be null");
		Contract.checkArgument(predicate != null, "Weekend predicate must not be null");

		customWeekendPredicates.put(countryCode, predicate);
	}

	/**
	 * Registers a custom predicate for determining working days in the given country.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code predicate} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void registerCustomWorkingDayPredicate(String countryCode, GeoTemporalPredicate predicate) {
		Contract.checkArgument(countryCode != null, "Country code must not be null");
		Contract.checkArgument(predicate != null, "Working day predicate must not be null");

		customWorkingDayPredicates.put(countryCode, predicate);
	}

}
