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
import java.util.ArrayList;
import java.util.List;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.time.applicability.GeoTemporalApplicability;

public final class UnionOfTemporalApplicabilities extends GeoTemporalApplicability {

	private final List<GeoTemporalApplicability> applicabilities = new ArrayList<>();


	public void add(GeoTemporalApplicability applicability) {
		Contract.checkArgument(applicability != null, "Applicability must not be null");

		applicabilities.add(applicability);
	}


	@Override
	public boolean test(Temporal temporal, GeoRegion region) {
		Contract.checkArgument(temporal != null, "Temporal must not be null");
		Contract.checkArgument(region != null, "Region must not be null");

		return applicabilities.stream()
				.anyMatch(applicability -> applicability.test(temporal, region));
	}

}
