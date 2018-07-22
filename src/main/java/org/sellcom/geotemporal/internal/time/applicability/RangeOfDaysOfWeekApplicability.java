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

import java.time.DayOfWeek;
import java.time.temporal.Temporal;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.time.DaysOfWeek;
import org.sellcom.geotemporal.time.applicability.TemporalApplicability;

public final class RangeOfDaysOfWeekApplicability extends TemporalApplicability {

	private final DayOfWeek endDayOfWeek;

	private final DayOfWeek startDayOfWeek;


	public RangeOfDaysOfWeekApplicability(DayOfWeek startDayOfWeek, DayOfWeek endDayOfWeek) {
		Contract.checkArgument(startDayOfWeek != null, "Start day of week must not be null");
		Contract.checkArgument(endDayOfWeek != null, "End day of week must not be null");

		this.startDayOfWeek = startDayOfWeek;
		this.endDayOfWeek = endDayOfWeek;
	}


	@Override
	public boolean test(Temporal temporal) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");

		return DaysOfWeek.isInRange(DayOfWeek.from(temporal), startDayOfWeek, endDayOfWeek);
	}

}
