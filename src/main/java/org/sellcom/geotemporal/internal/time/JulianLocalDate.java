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

import java.time.Month;
import java.util.Objects;

public class JulianLocalDate {

	private final int dayOfMonth;

	private final Month month;

	private final int year;


	private JulianLocalDate(int year, Month month, int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
	}


	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof JulianLocalDate) {
			JulianLocalDate otherCast = (JulianLocalDate) other;

			return Objects.equals(getYear(), otherCast.getYear())
				&& Objects.equals(getMonth(), otherCast.getMonth())
				&& Objects.equals(getDayOfMonth(), otherCast.getDayOfMonth());
		}

		return false;
	}


	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public Month getMonth() {
		return month;
	}

	public int getMonthValue() {
		return month.getValue();
	}

	public int getYear() {
		return year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(year, month, dayOfMonth);
	}

	public static JulianLocalDate of(int year, int month, int dayOfMonth) {
		return new JulianLocalDate(year, Month.of(month), dayOfMonth);
	}

	public static JulianLocalDate of(int year, Month month, int dayOfMonth) {
		return new JulianLocalDate(year, month, dayOfMonth);
	}

}
