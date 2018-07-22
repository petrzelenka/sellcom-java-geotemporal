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

/**
 * Types of public holidays.
 *
 * @since 1.0
 */
public enum HolidayType {

	/**
	 * Holiday observed only by banks and (most) financial institutions.
	 *
	 * @since 1.0
	 */
	BANKS_AND_FINANCIAL_INSTITUTIONS_ONLY,

	/**
	 * Holiday observed only by government services.
	 *
	 * @since 1.0
	 */
	GOVERNMENT_SERVICES_ONLY,

	/**
	 * Holiday observed globally.
	 *
	 * @since 1.0
	 */
	PUBLIC,

	/**
	 * Holiday observed only by schools and universities.
	 *
	 * @since 1.0
	 */
	SCHOOLS_AND_UNIVERSITIES_ONLY,

	/**
	 * Holiday observed only by schools.
	 *
	 * @since 1.0
	 */
	SCHOOLS_ONLY,

	;

}
