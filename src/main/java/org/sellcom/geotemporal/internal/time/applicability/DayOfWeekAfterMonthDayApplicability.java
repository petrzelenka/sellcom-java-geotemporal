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
package org.sellcom.geotemporal.internal.time.applicability;

import static java.time.temporal.ChronoUnit.WEEKS;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.time.applicability.TemporalApplicability;

public final class DayOfWeekAfterMonthDayApplicability extends TemporalApplicability {

	private final DayOfWeek dayOfWeek;

	private final MonthDay monthDay;

	private final int ordinal;


	public DayOfWeekAfterMonthDayApplicability(int ordinal, DayOfWeek dayOfWeek, MonthDay monthDay) {
		Contract.checkArgument(ordinal > 0, "Ordinal must be positive: {0}", ordinal);
		Contract.checkArgument(dayOfWeek != null, "Day of week must not be null");
		Contract.checkArgument(monthDay != null, "Month-day must not be null");

		this.ordinal = ordinal;
		this.dayOfWeek = dayOfWeek;
		this.monthDay = monthDay;
	}


	@Override
	public boolean test(Temporal temporal) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");

		Temporal targetTemporal = temporal
				.with(monthDay)
				.with(TemporalAdjusters.next(dayOfWeek))
				.plus(ordinal - 1, WEEKS);

		return temporal.equals(targetTemporal);
	}

}
