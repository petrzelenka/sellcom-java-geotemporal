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
package org.sellcom.geotemporal.internal.time.applicability.parser;

class MatcherUtils {

	private MatcherUtils() {
		// Utility class, not to be instantiated
	}


	static String getPrefix(String rule) {
		int openingBracketIndex = rule.indexOf('[');
		if (openingBracketIndex >= 0) {
			return rule.substring(0, openingBracketIndex + 1);
		}

		return "";
	}

	static boolean matchPrefix(String expression, int index, String prefix) {
		return expression.regionMatches(true, index, prefix, 0, prefix.length());
	}

	static boolean matchRuleCharacter(String expression, int index) {
		char character = expression.charAt(index);
		switch (character) {
			case ',': {
				return true;
			}
			case '-': {
				return true;
			}
			case '[': {
				return true;
			}
			case ']': {
				return true;
			}
			default: {
				return Character.isLetterOrDigit(character);
			}
		}
	}

}
