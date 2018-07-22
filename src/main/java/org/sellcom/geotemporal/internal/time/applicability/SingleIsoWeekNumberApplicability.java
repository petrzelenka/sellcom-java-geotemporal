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

import java.time.temporal.Temporal;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.time.Temporals;
import org.sellcom.geotemporal.time.applicability.TemporalApplicability;

public class SingleIsoWeekNumberApplicability extends TemporalApplicability {

	private final int isoWeekNumber;


	public SingleIsoWeekNumberApplicability(int isoWeekNumber) {
		Contract.checkArgument(isoWeekNumber >= 1, "ISO week number must be positive: {0}", isoWeekNumber);
		Contract.checkArgument(isoWeekNumber <= 53, "ISO week number must be at most 53: {0}", isoWeekNumber);

		this.isoWeekNumber = isoWeekNumber;
	}


	@Override
	public boolean test(Temporal temporal) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");

		return Temporals.getIsoWeekNumber(temporal) == isoWeekNumber;
	}

}
