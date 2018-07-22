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

import static java.time.Month.JANUARY;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.time.Temporals.getIsoWeekNumber;

import java.time.LocalDate;

import org.junit.Test;

public class TemporalsTest {

	@Test
	public void testGetIsoWeekNumber() {
		assertThat(getIsoWeekNumber(LocalDate.of(2000, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2001, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2002, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2003, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2004, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2005, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2006, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2007, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2008, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2009, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2010, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2011, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2012, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2013, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2014, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2015, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2016, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2017, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2018, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2019, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2020, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2021, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2022, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2023, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2024, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2025, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2026, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2027, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2028, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2029, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2030, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2031, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2032, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2033, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2034, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2035, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2036, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2037, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2038, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2039, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2040, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2041, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2042, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2043, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2044, JANUARY, 1)), is(equalTo(53)));
		assertThat(getIsoWeekNumber(LocalDate.of(2045, JANUARY, 1)), is(equalTo(52)));
		assertThat(getIsoWeekNumber(LocalDate.of(2046, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2047, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2048, JANUARY, 1)), is(equalTo(1)));
		assertThat(getIsoWeekNumber(LocalDate.of(2049, JANUARY, 1)), is(equalTo(53)));
	}

}
