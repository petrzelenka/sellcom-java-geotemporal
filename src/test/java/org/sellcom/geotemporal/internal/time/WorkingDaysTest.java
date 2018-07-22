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

import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.HUNGARY;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.LATVIA;
import static org.sellcom.geotemporal.geography.StandardGeoRegions.MOLDOVA;
import static org.sellcom.geotemporal.time.Temporals.isWorkingDay;

import java.time.LocalDate;

import org.junit.Test;
import org.sellcom.geotemporal.geography.GeoRegion;

public class WorkingDaysTest {

	@Test
	public void testIsWorkingDay_Hungary_2014() {
		GeoRegion region = GeoRegion.parse(HUNGARY);

		assertThat(isWorkingDay(LocalDate.of(2014, MAY, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2014, MAY, 10), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, OCTOBER, 18), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, OCTOBER, 24), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2014, DECEMBER, 13), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, DECEMBER, 24), region), is(false));
	}

	@Test
	public void testIsWorkingDay_Hungary_2015() {
		GeoRegion region = GeoRegion.parse(HUNGARY);

		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 10), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, AUGUST, 8), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, AUGUST, 21), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2015, DECEMBER, 12), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, DECEMBER, 24), region), is(false));
	}

	@Test
	public void testIsWorkingDay_Hungary_2016() {
		GeoRegion region = GeoRegion.parse(HUNGARY);

		assertThat(isWorkingDay(LocalDate.of(2016, MARCH, 5), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2016, MARCH, 14), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2016, OCTOBER, 15), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2016, OCTOBER, 31), region), is(false));
	}

	@Test
	public void testIsWorkingDay_Hungary_2017() {
		// No exceptions to test
	}

	@Test
	public void testIsWorkingDay_Latvia_2014() {
		GeoRegion region = GeoRegion.parse(LATVIA);

		assertThat(isWorkingDay(LocalDate.of(2014, MAY, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2014, MAY, 10), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, NOVEMBER, 17), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2014, NOVEMBER, 22), region), is(true));
	}

	@Test
	public void testIsWorkingDay_Latvia_2015() {
		GeoRegion region = GeoRegion.parse(LATVIA);

		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 10), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, JUNE, 22), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2015, JUNE, 27), region), is(true));
	}

	@Test
	public void testIsWorkingDay_Latvia_2016() {
		// No exceptions to test
	}

	@Test
	public void testIsWorkingDay_Latvia_2017() {
		GeoRegion region = GeoRegion.parse(LATVIA);

		assertThat(isWorkingDay(LocalDate.of(2017, MAY, 5), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2017, MAY, 13), region), is(true));
	}

	@Test
	public void testIsWorkingDay_Moldova_2014() {
		GeoRegion region = GeoRegion.parse(MOLDOVA);

		assertThat(isWorkingDay(LocalDate.of(2014, JANUARY, 6), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2014, JANUARY, 18), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, MAY, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2014, MAY, 17), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, DECEMBER, 20), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2014, DECEMBER, 25), region), is(false));
	}

	@Test
	public void testIsWorkingDay_Moldova_2015() {
		GeoRegion region = GeoRegion.parse(MOLDOVA);

		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 9), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 17), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, JANUARY, 31), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, AUGUST, 22), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2015, AUGUST, 28), region), is(false));
	}

	@Test
	public void testIsWorkingDay_Moldova_2016() {
		GeoRegion region = GeoRegion.parse(MOLDOVA);

		assertThat(isWorkingDay(LocalDate.of(2016, MARCH, 7), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2016, MARCH, 12), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2016, AUGUST, 29), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2016, AUGUST, 30), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2016, SEPTEMBER, 24), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2016, OCTOBER, 22), region), is(true));
	}

	@Test
	public void testIsWorkingDay_Moldova_2017() {
		GeoRegion region = GeoRegion.parse(MOLDOVA);

		assertThat(isWorkingDay(LocalDate.of(2017, MAY, 8), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2017, MAY, 13), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2017, JUNE, 2), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2017, JUNE, 10), region), is(true));
		assertThat(isWorkingDay(LocalDate.of(2017, SEPTEMBER, 1), region), is(false));
		assertThat(isWorkingDay(LocalDate.of(2017, SEPTEMBER, 9), region), is(true));
	}

}
