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

import java.time.Year;
import java.time.temporal.Temporal;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.time.Years;
import org.sellcom.geotemporal.time.applicability.TemporalApplicability;

public final class RangeOfYearsApplicability extends TemporalApplicability {

	private final Year endYear;

	private final Year startYear;


	public RangeOfYearsApplicability(int startYear, int endYear) {
		this.startYear = Year.of(startYear);
		this.endYear = Year.of(endYear);
	}


	@Override
	public boolean test(Temporal date) {
		Contract.checkArgument(date != null, "Temporal must not be null");

		return Years.isInRange(Year.from(date), startYear, endYear);
	}

}
