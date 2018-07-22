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
import java.util.HashMap;
import java.util.Map;

import org.sellcom.geotemporal.internal.time.applicability.IntersectionOfTemporalApplicabilities;
import org.sellcom.geotemporal.internal.time.applicability.InverseGeoTemporalApplicability;
import org.sellcom.geotemporal.internal.time.applicability.UnionOfTemporalApplicabilities;
import org.sellcom.geotemporal.time.applicability.GeoTemporalApplicability;
import org.sellcom.geotemporal.time.applicability.GeoTemporalApplicabilityParseException;

public class GeoTemporalApplicabilityParser {

	private static final Map<TokenType, InfixParselet> infixParselets = new HashMap<>();

	static {
		infixParselets.put(TokenType.AND_OPERATOR, new AndOperatorParselet());
		infixParselets.put(TokenType.OR_OPERATOR, new OrOperatorParselet());
	}

	private static final Map<TokenType, PrefixParselet> prefixParselets = new HashMap<>();

	static {
		prefixParselets.put(TokenType.LEFT_PARENTHESIS, new GroupParselet());
		prefixParselets.put(TokenType.NOT_OPERATOR, new NotOperatorParselet());
		prefixParselets.put(TokenType.RULE, new RuleParselet());
	}


	public GeoTemporalApplicability parse(String expression) {
		Tokenizer tokenizer = new Tokenizer(expression);

		return parse(tokenizer, 0);
	}


	private int evaluatePrecedence(Token nextToken) {
		InfixParselet infixParselet = infixParselets.get(nextToken.getType());

		return (infixParselet == null) ?  0 : infixParselet.getPrecedence();
	}

	private GeoTemporalApplicability parse(Tokenizer tokenizer, int precedence) {
		GeoTemporalApplicability result;

		Token firstToken = tokenizer.pollToken();
		PrefixParselet prefixParselet = prefixParselets.get(firstToken.getType());
		if (prefixParselet == null) {
			throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" could not be parsed", firstToken.getValue()));
		}

		result = prefixParselet.parse(this, firstToken, tokenizer);

		while (precedence < evaluatePrecedence(tokenizer.peekToken())) {
			Token nextToken = tokenizer.pollToken();
			InfixParselet infixParselet = infixParselets.get(nextToken.getType());
			if (infixParselet == null) {
				throw new GeoTemporalApplicabilityParseException(MessageFormat.format("Token \"{0}\" could not be parsed", nextToken.getValue()));
			}

			result = infixParselet.parse(this, result, nextToken, tokenizer);
		}

		return result;
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private final static class AndOperatorParselet implements InfixParselet {

		@Override
		public int getPrecedence() {
			return 2;
		}

		@Override
		public GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, GeoTemporalApplicability leftOperand, Token token, Tokenizer tokenizer) {
			GeoTemporalApplicability rightOperand = parser.parse(tokenizer, getPrecedence());

			if (leftOperand instanceof IntersectionOfTemporalApplicabilities) {
				((IntersectionOfTemporalApplicabilities) leftOperand).add(rightOperand);

				return leftOperand;
			} else {
				IntersectionOfTemporalApplicabilities result = new IntersectionOfTemporalApplicabilities();
				result.add(leftOperand);
				result.add(rightOperand);

				return result;
			}
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private final static class GroupParselet implements PrefixParselet {

		@Override
		public int getPrecedence() {
			return 0;
		}

		@Override
		public GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, Token token, Tokenizer tokenizer) {
			GeoTemporalApplicability result = parser.parse(tokenizer, getPrecedence());
			tokenizer.discardExpectedToken(TokenType.RIGHT_PARENTHESIS);

			return result;
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static interface InfixParselet {

		int getPrecedence();

		GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, GeoTemporalApplicability leftOperand, Token token, Tokenizer tokenizer);

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private final static class NotOperatorParselet implements PrefixParselet {

		@Override
		public int getPrecedence() {
			return 3;
		}

		@Override
		public GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, Token token, Tokenizer tokenizer) {
			return new InverseGeoTemporalApplicability(parser.parse(tokenizer, getPrecedence()));
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private final static class OrOperatorParselet implements InfixParselet {

		@Override
		public int getPrecedence() {
			return 1;
		}

		@Override
		public GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, GeoTemporalApplicability leftOperand, Token token, Tokenizer tokenizer) {
			GeoTemporalApplicability rightOperand = parser.parse(tokenizer, getPrecedence());

			if (leftOperand instanceof UnionOfTemporalApplicabilities) {
				((UnionOfTemporalApplicabilities) leftOperand).add(rightOperand);

				return leftOperand;
			} else {
				UnionOfTemporalApplicabilities result = new UnionOfTemporalApplicabilities();
				result.add(leftOperand);
				result.add(rightOperand);

				return result;
			}
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static interface PrefixParselet {

		int getPrecedence();

		GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, Token token, Tokenizer tokenizer);

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private final static class RuleParselet implements PrefixParselet {

		@Override
		public int getPrecedence() {
			return 0;
		}

		@Override
		public GeoTemporalApplicability parse(GeoTemporalApplicabilityParser parser, Token token, Tokenizer tokenizer) {
			return ParserUtils.parseLocalDateApplicability(token.getValue());
		}

	}

}
