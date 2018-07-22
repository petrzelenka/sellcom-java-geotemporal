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

import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static java.time.Month.OCTOBER;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.geotemporal.internal.time.CalendarConversionUtils.gregorianToJulian;
import static org.sellcom.geotemporal.internal.time.CalendarConversionUtils.julianToGregorian;

import java.time.LocalDate;

import org.junit.Test;

public class CalendarConversionUtilsTest {

	@Test
	public void testGregorianToJulian() {
		assertThat(gregorianToJulian(LocalDate.of(-500, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(-500, MARCH, 5))));
		assertThat(gregorianToJulian(LocalDate.of(-500, MARCH, 1)), is(equalTo(JulianLocalDate.of(-500, MARCH, 6))));
		assertThat(gregorianToJulian(LocalDate.of(-300, FEBRUARY, 27)), is(equalTo(JulianLocalDate.of(-300, MARCH, 3))));
		assertThat(gregorianToJulian(LocalDate.of(-300, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(-300, MARCH, 4))));
		assertThat(gregorianToJulian(LocalDate.of(-300, MARCH, 1)), is(equalTo(JulianLocalDate.of(-300, MARCH, 5))));
		assertThat(gregorianToJulian(LocalDate.of(-200, FEBRUARY, 27)), is(equalTo(JulianLocalDate.of(-200, MARCH, 2))));
		assertThat(gregorianToJulian(LocalDate.of(-200, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(-200, MARCH, 3))));
		assertThat(gregorianToJulian(LocalDate.of(-200, MARCH, 1)), is(equalTo(JulianLocalDate.of(-200, MARCH, 4))));
		assertThat(gregorianToJulian(LocalDate.of(-100, FEBRUARY, 27)), is(equalTo(JulianLocalDate.of(-100, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(-100, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(-100, MARCH, 2))));
		assertThat(gregorianToJulian(LocalDate.of(-100, MARCH, 1)), is(equalTo(JulianLocalDate.of(-100, MARCH, 3))));
		assertThat(gregorianToJulian(LocalDate.of(100, FEBRUARY, 27)), is(equalTo(JulianLocalDate.of(100, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(100, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(100, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(100, MARCH, 1)), is(equalTo(JulianLocalDate.of(100, MARCH, 2))));
		assertThat(gregorianToJulian(LocalDate.of(200, FEBRUARY, 27)), is(equalTo(JulianLocalDate.of(200, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(200, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(200, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(200, MARCH, 1)), is(equalTo(JulianLocalDate.of(200, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(300, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(300, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(300, MARCH, 1)), is(equalTo(JulianLocalDate.of(300, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(300, MARCH, 2)), is(equalTo(JulianLocalDate.of(300, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(500, MARCH, 1)), is(equalTo(JulianLocalDate.of(500, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(500, MARCH, 2)), is(equalTo(JulianLocalDate.of(500, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(500, MARCH, 3)), is(equalTo(JulianLocalDate.of(500, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(600, MARCH, 2)), is(equalTo(JulianLocalDate.of(600, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(600, MARCH, 3)), is(equalTo(JulianLocalDate.of(600, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(600, MARCH, 4)), is(equalTo(JulianLocalDate.of(600, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(700, MARCH, 3)), is(equalTo(JulianLocalDate.of(700, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(700, MARCH, 4)), is(equalTo(JulianLocalDate.of(700, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(700, MARCH, 5)), is(equalTo(JulianLocalDate.of(700, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(900, MARCH, 4)), is(equalTo(JulianLocalDate.of(900, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(900, MARCH, 5)), is(equalTo(JulianLocalDate.of(900, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(900, MARCH, 6)), is(equalTo(JulianLocalDate.of(900, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1000, MARCH, 5)), is(equalTo(JulianLocalDate.of(1000, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1000, MARCH, 6)), is(equalTo(JulianLocalDate.of(1000, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1000, MARCH, 7)), is(equalTo(JulianLocalDate.of(1000, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1100, MARCH, 6)), is(equalTo(JulianLocalDate.of(1100, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1100, MARCH, 7)), is(equalTo(JulianLocalDate.of(1100, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1100, MARCH, 8)), is(equalTo(JulianLocalDate.of(1100, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1300, MARCH, 7)), is(equalTo(JulianLocalDate.of(1300, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1300, MARCH, 8)), is(equalTo(JulianLocalDate.of(1300, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1300, MARCH, 9)), is(equalTo(JulianLocalDate.of(1300, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1400, MARCH, 8)), is(equalTo(JulianLocalDate.of(1400, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1400, MARCH, 9)), is(equalTo(JulianLocalDate.of(1400, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1400, MARCH, 10)), is(equalTo(JulianLocalDate.of(1400, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1500, MARCH, 9)), is(equalTo(JulianLocalDate.of(1500, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1500, MARCH, 10)), is(equalTo(JulianLocalDate.of(1500, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1500, MARCH, 11)), is(equalTo(JulianLocalDate.of(1500, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1582, OCTOBER, 14)), is(equalTo(JulianLocalDate.of(1582, OCTOBER, 4))));
		assertThat(gregorianToJulian(LocalDate.of(1582, OCTOBER, 15)), is(equalTo(JulianLocalDate.of(1582, OCTOBER, 5))));
		assertThat(gregorianToJulian(LocalDate.of(1582, OCTOBER, 16)), is(equalTo(JulianLocalDate.of(1582, OCTOBER, 6))));
		assertThat(gregorianToJulian(LocalDate.of(1700, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(1700, FEBRUARY, 18))));
		assertThat(gregorianToJulian(LocalDate.of(1700, MARCH, 1)), is(equalTo(JulianLocalDate.of(1700, FEBRUARY, 19))));
		assertThat(gregorianToJulian(LocalDate.of(1700, MARCH, 10)), is(equalTo(JulianLocalDate.of(1700, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1700, MARCH, 11)), is(equalTo(JulianLocalDate.of(1700, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1700, MARCH, 12)), is(equalTo(JulianLocalDate.of(1700, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1800, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(1800, FEBRUARY, 17))));
		assertThat(gregorianToJulian(LocalDate.of(1800, MARCH, 1)), is(equalTo(JulianLocalDate.of(1800, FEBRUARY, 18))));
		assertThat(gregorianToJulian(LocalDate.of(1800, MARCH, 11)), is(equalTo(JulianLocalDate.of(1800, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1800, MARCH, 12)), is(equalTo(JulianLocalDate.of(1800, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1800, MARCH, 13)), is(equalTo(JulianLocalDate.of(1800, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(1900, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(1900, FEBRUARY, 16))));
		assertThat(gregorianToJulian(LocalDate.of(1900, MARCH, 1)), is(equalTo(JulianLocalDate.of(1900, FEBRUARY, 17))));
		assertThat(gregorianToJulian(LocalDate.of(1900, MARCH, 12)), is(equalTo(JulianLocalDate.of(1900, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(1900, MARCH, 13)), is(equalTo(JulianLocalDate.of(1900, FEBRUARY, 29))));
		assertThat(gregorianToJulian(LocalDate.of(1900, MARCH, 14)), is(equalTo(JulianLocalDate.of(1900, MARCH, 1))));
		assertThat(gregorianToJulian(LocalDate.of(2100, FEBRUARY, 28)), is(equalTo(JulianLocalDate.of(2100, FEBRUARY, 15))));
		assertThat(gregorianToJulian(LocalDate.of(2100, MARCH, 1)), is(equalTo(JulianLocalDate.of(2100, FEBRUARY, 16))));
		assertThat(gregorianToJulian(LocalDate.of(2100, MARCH, 13)), is(equalTo(JulianLocalDate.of(2100, FEBRUARY, 28))));
		assertThat(gregorianToJulian(LocalDate.of(2100, MARCH, 14)), is(equalTo(JulianLocalDate.of(2100, FEBRUARY, 29))));
	}

	@Test
	public void testJulianToGregorian() {
		assertThat(julianToGregorian(JulianLocalDate.of(-500, MARCH, 5)), is(equalTo(LocalDate.of(-500, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(-500, MARCH, 6)), is(equalTo(LocalDate.of(-500, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(-300, MARCH, 3)), is(equalTo(LocalDate.of(-300, FEBRUARY, 27))));
		assertThat(julianToGregorian(JulianLocalDate.of(-300, MARCH, 4)), is(equalTo(LocalDate.of(-300, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(-300, MARCH, 5)), is(equalTo(LocalDate.of(-300, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(-200, MARCH, 2)), is(equalTo(LocalDate.of(-200, FEBRUARY, 27))));
		assertThat(julianToGregorian(JulianLocalDate.of(-200, MARCH, 3)), is(equalTo(LocalDate.of(-200, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(-200, MARCH, 4)), is(equalTo(LocalDate.of(-200, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(-100, MARCH, 1)), is(equalTo(LocalDate.of(-100, FEBRUARY, 27))));
		assertThat(julianToGregorian(JulianLocalDate.of(-100, MARCH, 2)), is(equalTo(LocalDate.of(-100, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(-100, MARCH, 3)), is(equalTo(LocalDate.of(-100, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(100, FEBRUARY, 29)), is(equalTo(LocalDate.of(100, FEBRUARY, 27))));
		assertThat(julianToGregorian(JulianLocalDate.of(100, MARCH, 1)), is(equalTo(LocalDate.of(100, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(100, MARCH, 2)), is(equalTo(LocalDate.of(100, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(200, FEBRUARY, 28)), is(equalTo(LocalDate.of(200, FEBRUARY, 27))));
		assertThat(julianToGregorian(JulianLocalDate.of(200, FEBRUARY, 29)), is(equalTo(LocalDate.of(200, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(200, MARCH, 1)), is(equalTo(LocalDate.of(200, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(300, FEBRUARY, 28)), is(equalTo(LocalDate.of(300, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(300, FEBRUARY, 29)), is(equalTo(LocalDate.of(300, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(300, MARCH, 1)), is(equalTo(LocalDate.of(300, MARCH, 2))));
		assertThat(julianToGregorian(JulianLocalDate.of(500, FEBRUARY, 28)), is(equalTo(LocalDate.of(500, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(500, FEBRUARY, 29)), is(equalTo(LocalDate.of(500, MARCH, 2))));
		assertThat(julianToGregorian(JulianLocalDate.of(500, MARCH, 1)), is(equalTo(LocalDate.of(500, MARCH, 3))));
		assertThat(julianToGregorian(JulianLocalDate.of(600, FEBRUARY, 28)), is(equalTo(LocalDate.of(600, MARCH, 2))));
		assertThat(julianToGregorian(JulianLocalDate.of(600, FEBRUARY, 29)), is(equalTo(LocalDate.of(600, MARCH, 3))));
		assertThat(julianToGregorian(JulianLocalDate.of(600, MARCH, 1)), is(equalTo(LocalDate.of(600, MARCH, 4))));
		assertThat(julianToGregorian(JulianLocalDate.of(700, FEBRUARY, 28)), is(equalTo(LocalDate.of(700, MARCH, 3))));
		assertThat(julianToGregorian(JulianLocalDate.of(700, FEBRUARY, 29)), is(equalTo(LocalDate.of(700, MARCH, 4))));
		assertThat(julianToGregorian(JulianLocalDate.of(700, MARCH, 1)), is(equalTo(LocalDate.of(700, MARCH, 5))));
		assertThat(julianToGregorian(JulianLocalDate.of(900, FEBRUARY, 28)), is(equalTo(LocalDate.of(900, MARCH, 4))));
		assertThat(julianToGregorian(JulianLocalDate.of(900, FEBRUARY, 29)), is(equalTo(LocalDate.of(900, MARCH, 5))));
		assertThat(julianToGregorian(JulianLocalDate.of(900, MARCH, 1)), is(equalTo(LocalDate.of(900, MARCH, 6))));
		assertThat(julianToGregorian(JulianLocalDate.of(1000, FEBRUARY, 28)), is(equalTo(LocalDate.of(1000, MARCH, 5))));
		assertThat(julianToGregorian(JulianLocalDate.of(1000, FEBRUARY, 29)), is(equalTo(LocalDate.of(1000, MARCH, 6))));
		assertThat(julianToGregorian(JulianLocalDate.of(1000, MARCH, 1)), is(equalTo(LocalDate.of(1000, MARCH, 7))));
		assertThat(julianToGregorian(JulianLocalDate.of(1100, FEBRUARY, 28)), is(equalTo(LocalDate.of(1100, MARCH, 6))));
		assertThat(julianToGregorian(JulianLocalDate.of(1100, FEBRUARY, 29)), is(equalTo(LocalDate.of(1100, MARCH, 7))));
		assertThat(julianToGregorian(JulianLocalDate.of(1100, MARCH, 1)), is(equalTo(LocalDate.of(1100, MARCH, 8))));
		assertThat(julianToGregorian(JulianLocalDate.of(1300, FEBRUARY, 28)), is(equalTo(LocalDate.of(1300, MARCH, 7))));
		assertThat(julianToGregorian(JulianLocalDate.of(1300, FEBRUARY, 29)), is(equalTo(LocalDate.of(1300, MARCH, 8))));
		assertThat(julianToGregorian(JulianLocalDate.of(1300, MARCH, 1)), is(equalTo(LocalDate.of(1300, MARCH, 9))));
		assertThat(julianToGregorian(JulianLocalDate.of(1400, FEBRUARY, 28)), is(equalTo(LocalDate.of(1400, MARCH, 8))));
		assertThat(julianToGregorian(JulianLocalDate.of(1400, FEBRUARY, 29)), is(equalTo(LocalDate.of(1400, MARCH, 9))));
		assertThat(julianToGregorian(JulianLocalDate.of(1400, MARCH, 1)), is(equalTo(LocalDate.of(1400, MARCH, 10))));
		assertThat(julianToGregorian(JulianLocalDate.of(1500, FEBRUARY, 28)), is(equalTo(LocalDate.of(1500, MARCH, 9))));
		assertThat(julianToGregorian(JulianLocalDate.of(1500, FEBRUARY, 29)), is(equalTo(LocalDate.of(1500, MARCH, 10))));
		assertThat(julianToGregorian(JulianLocalDate.of(1500, MARCH, 1)), is(equalTo(LocalDate.of(1500, MARCH, 11))));
		assertThat(julianToGregorian(JulianLocalDate.of(1582, OCTOBER, 4)), is(equalTo(LocalDate.of(1582, OCTOBER, 14))));
		assertThat(julianToGregorian(JulianLocalDate.of(1582, OCTOBER, 5)), is(equalTo(LocalDate.of(1582, OCTOBER, 15))));
		assertThat(julianToGregorian(JulianLocalDate.of(1582, OCTOBER, 6)), is(equalTo(LocalDate.of(1582, OCTOBER, 16))));
		assertThat(julianToGregorian(JulianLocalDate.of(1700, FEBRUARY, 18)), is(equalTo(LocalDate.of(1700, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(1700, FEBRUARY, 19)), is(equalTo(LocalDate.of(1700, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(1700, FEBRUARY, 28)), is(equalTo(LocalDate.of(1700, MARCH, 10))));
		assertThat(julianToGregorian(JulianLocalDate.of(1700, FEBRUARY, 29)), is(equalTo(LocalDate.of(1700, MARCH, 11))));
		assertThat(julianToGregorian(JulianLocalDate.of(1700, MARCH, 1)), is(equalTo(LocalDate.of(1700, MARCH, 12))));
		assertThat(julianToGregorian(JulianLocalDate.of(1800, FEBRUARY, 17)), is(equalTo(LocalDate.of(1800, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(1800, FEBRUARY, 18)), is(equalTo(LocalDate.of(1800, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(1800, FEBRUARY, 28)), is(equalTo(LocalDate.of(1800, MARCH, 11))));
		assertThat(julianToGregorian(JulianLocalDate.of(1800, FEBRUARY, 29)), is(equalTo(LocalDate.of(1800, MARCH, 12))));
		assertThat(julianToGregorian(JulianLocalDate.of(1800, MARCH, 1)), is(equalTo(LocalDate.of(1800, MARCH, 13))));
		assertThat(julianToGregorian(JulianLocalDate.of(1900, FEBRUARY, 16)), is(equalTo(LocalDate.of(1900, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(1900, FEBRUARY, 17)), is(equalTo(LocalDate.of(1900, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(1900, FEBRUARY, 28)), is(equalTo(LocalDate.of(1900, MARCH, 12))));
		assertThat(julianToGregorian(JulianLocalDate.of(1900, FEBRUARY, 29)), is(equalTo(LocalDate.of(1900, MARCH, 13))));
		assertThat(julianToGregorian(JulianLocalDate.of(1900, MARCH, 1)), is(equalTo(LocalDate.of(1900, MARCH, 14))));
		assertThat(julianToGregorian(JulianLocalDate.of(2100, FEBRUARY, 15)), is(equalTo(LocalDate.of(2100, FEBRUARY, 28))));
		assertThat(julianToGregorian(JulianLocalDate.of(2100, FEBRUARY, 16)), is(equalTo(LocalDate.of(2100, MARCH, 1))));
		assertThat(julianToGregorian(JulianLocalDate.of(2100, FEBRUARY, 28)), is(equalTo(LocalDate.of(2100, MARCH, 13))));
		assertThat(julianToGregorian(JulianLocalDate.of(2100, FEBRUARY, 29)), is(equalTo(LocalDate.of(2100, MARCH, 14))));
	}

}
