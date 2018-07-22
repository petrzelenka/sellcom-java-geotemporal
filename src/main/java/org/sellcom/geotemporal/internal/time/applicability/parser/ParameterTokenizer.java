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

import java.util.regex.Pattern;

class ParameterTokenizer {

	private static final Pattern SEPARATOR_PATTERN = Pattern.compile("\\p{Space}*,\\p{Space}*");

	private String[] tokens;


	ParameterTokenizer(String rule, int prefixLength) {
		rule = rule.trim();

		tokens = SEPARATOR_PATTERN.split(rule
				.substring(prefixLength, rule.lastIndexOf(']'))
				.trim());
	}


	String tokenAt(int index) {
		return tokens[index];
	}

	int tokens() {
		return tokens.length;
	}

}
