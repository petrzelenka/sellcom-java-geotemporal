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

import java.text.MessageFormat;

import org.sellcom.geotemporal.time.applicability.GeoTemporalApplicabilityParseException;

class Tokenizer {

	private static final String AND_OPERATOR = "AND";

	private static final String LEFT_PARENTHESIS = "(";

	private static final String NOT_OPERATOR = "NOT";

	private static final String OR_OPERATOR = "OR";

	private static final String RIGHT_PARENTHESIS = ")";

	private static final String SPACE = " ";

	private final String expression;

	private int index = 0;

	private Token token;


	Tokenizer(String expression) {
		this.expression = expression;

		token = prepareNextToken();
	}


	void discardExpectedToken(TokenType expectedType) {
		if (token.getType() != expectedType) {
			throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Expected {0} but found {1} instead", expectedType, token.getType()));
		}

		token = prepareNextToken();
	}

	Token peekToken() {
		return token;
	}

	Token pollToken() {
		try {
			return token;
		} finally {
			token = prepareNextToken();
		}
	}


	private Token prepareNextToken() {
		while (index < expression.length()) {

			/*
			 * STEP 1: Match punctuation
			 */

			if (MatcherUtils.matchPrefix(expression, index, LEFT_PARENTHESIS)) {
				index += LEFT_PARENTHESIS.length();

				return new Token(TokenType.LEFT_PARENTHESIS, "");
			}
			if (MatcherUtils.matchPrefix(expression, index, RIGHT_PARENTHESIS)) {
				index += RIGHT_PARENTHESIS.length();

				return new Token(TokenType.RIGHT_PARENTHESIS, "");
			}
			if (MatcherUtils.matchPrefix(expression, index, SPACE)) {
				index += SPACE.length();

				continue;
			}

			/*
			 * STEP 2: Match logical operators
			 */

			if (MatcherUtils.matchPrefix(expression, index, AND_OPERATOR)) {
				index += AND_OPERATOR.length();
				if (Character.isLetterOrDigit(expression.charAt(index))) {
					throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Expression \"{0}\" could not be parsed at index %d", expression, index));
				}

				return new Token(TokenType.AND_OPERATOR, "");
			}
			if (MatcherUtils.matchPrefix(expression, index, NOT_OPERATOR)) {
				index += NOT_OPERATOR.length();
				if (Character.isLetterOrDigit(expression.charAt(index))) {
					throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Expression \"{0}\" could not be parsed at index %d", expression, index));
				}

				return new Token(TokenType.NOT_OPERATOR, "");
			}
			if (MatcherUtils.matchPrefix(expression, index, OR_OPERATOR)) {
				index += OR_OPERATOR.length();
				if (Character.isLetterOrDigit(expression.charAt(index))) {
					throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Expression \"{0}\" could not be parsed at index %d", expression, index));
				}

				return new Token(TokenType.OR_OPERATOR, "");
			}

			/*
			 * STEP 3: Match rules
			 */

			if (MatcherUtils.matchRuleCharacter(expression, index)) {
				int startIndex = index;
				index += 1;
				while ((index < expression.length()) && MatcherUtils.matchRuleCharacter(expression, index)) {
					index += 1;
				}

				int endIndex = index;
				String rule = expression.substring(startIndex, endIndex);

				return new Token(TokenType.RULE, rule);
			} else {
				throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Expression \"{0}\" could not be parsed at index %d", expression, index));
			}
		}

		return new Token(TokenType.END_OF_EXPRESSION, "");
	}

}
