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
package org.sellcom.geotemporal.internal;

import java.text.MessageFormat;

public class Contract {

	private Contract() {
		// Utility class, not to be instantiated
	}


	public static <T extends RuntimeException> void check(boolean condition, Class<T> exceptionType) {
		if (exceptionType == null) {
			throw new IllegalArgumentException("Exception type must not be null");
		}

		if (!condition) {
			throw ReflectionUtils.createException(exceptionType);
		}
	}

	public static <T extends RuntimeException> void check(boolean condition, Class<T> exceptionType, String message, Object... arguments) {
		if (exceptionType == null) {
			throw new IllegalArgumentException("Exception type must not be null");
		}

		if (!condition) {
			if (arguments == null) {
				throw ReflectionUtils.createException(exceptionType, message);
			} else {
				throw ReflectionUtils.createException(exceptionType, MessageFormat.format(message, arguments));
			}
		}
	}

	public static void checkArgument(boolean condition, String message, Object... arguments) {
		check(condition, IllegalArgumentException.class, message, arguments);
	}

	public static void checkState(boolean condition, String message, Object... arguments) {
		check(condition, IllegalStateException.class, message, arguments);
	}

}
