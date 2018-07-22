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
import java.util.function.Predicate;

/**
 * Predicate of a temporal.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface TemporalPredicate extends Predicate<Temporal> {

	/**
	 * Evaluates this predicate at the given temporal.
	 *
	 * @throws IllegalArgumentException if {@code temporal} is {@code null}
	 *
	 * @since 1.0
	 */
	@Override
	boolean test(Temporal temporal);

}
