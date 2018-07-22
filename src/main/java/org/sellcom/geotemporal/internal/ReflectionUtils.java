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

import java.lang.reflect.Constructor;

public class ReflectionUtils {

	private ReflectionUtils() {
		// Utility class, not to be instantiated
	}


	public static <T extends Exception> T createException(Class<T> type) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor();

			return constructor.newInstance();
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static <T extends Exception> T createException(Class<T> type, String message) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor(String.class);

			return constructor.newInstance(message);
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static <T extends Exception> T createException(Class<T> type, String message, Throwable cause) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor(String.class, Throwable.class);

			return constructor.newInstance(message, cause);
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static <T extends Exception> T createException(Class<T> type, Throwable cause) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor(Throwable.class);

			return constructor.newInstance(cause);
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

}
