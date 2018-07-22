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

import java.time.temporal.Temporal;
import java.util.function.BiPredicate;

import org.sellcom.geotemporal.geography.GeoRegion;
import org.sellcom.geotemporal.geography.GeoRegionNotSupportedException;

/**
 * Predicate of a temporal and a region.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface GeoTemporalPredicate extends BiPredicate<Temporal, GeoRegion> {

	/**
	 * Evaluates this predicate at the given temporal in the context of the given region.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 * @throws IllegalArgumentException if {@code region} is {@code null}
	 * @throws GeoRegionNotSupportedException if the given region is not supported
	 *
	 * @since 1.0
	 */
	@Override
	boolean test(Temporal temporal, GeoRegion region);

}
