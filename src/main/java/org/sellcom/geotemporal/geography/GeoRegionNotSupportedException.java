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
package org.sellcom.geotemporal.geography;

/**
 * Indicator that a {@code GeoRegion} is not supported.
 *
 * @since 1.0
 */
public class GeoRegionNotSupportedException extends GeoRegionException {

	private static final long serialVersionUID = -2937154296148926247L;


	/**
	 * Creates a new exception with the given message.
	 *
	 * @since 1.0
	 */
	public GeoRegionNotSupportedException(String message) {
		super(message);
	}

	/**
	 * Creates a new exception with the given message and cause.
	 *
	 * @since 1.0
	 */
	public GeoRegionNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

}
