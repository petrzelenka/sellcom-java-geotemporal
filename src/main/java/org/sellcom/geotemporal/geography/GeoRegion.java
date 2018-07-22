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

import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;

import org.sellcom.geotemporal.internal.Contract;
import org.sellcom.geotemporal.internal.StringUtils;

/**
 * Representation of a specific geographical or political region.
 *
 * <h3>Structure of a GeoRegion</h3>
 * Every region is defined by a country code and up to five codes of administrative units at different subdivision levels.
 * The overall region code is composed of the individual codes separated by a comma.
 * <p>
 * Regions are strictly hierarchical, however the administrative units at some subdivision levels may be skipped.
 * This situation can be found in Germany, for instance, where the subdivision level of "governmental districts" (orig. "Regierungsbezirke") is defined only in some of the "federal states" (orig. "Bundesländer").
 *
 * <h3>Predefined GeoRegions</h3>
 * Region codes defined by this library can be found in the {@link StandardGeoRegions} class and its inner classes.
 * Custom-defined region codes shall be compatible with these codes.
 *
 * <h3>Default GeoRegion</h3>
 * Similarly to {@link Locale}, this class defines a default region.
 * The default region is set at class loading time from the {@code "user.georegion"} system property.
 * If the property is not defined or if its value is empty, a qualified guess of the default region is made from the default locale.
 * If the qualified guess fails, a {@link GeoRegionException} is thrown.
 * The default region can be changed at any time using the {@link #setDefault(GeoRegion)} method.
 *
 * @since 1.0
 */
public class GeoRegion {

	/**
	 * The root region.
	 *
	 * @since 1.0
	 */
	public static final GeoRegion ROOT = new GeoRegion("");

	private static GeoRegion defaultGeoRegion;

	static {
		initDefault();
	}

	private final String countryCode;

	private final String level1SubdivisionCode;

	private final String level2SubdivisionCode;

	private final String level3SubdivisionCode;

	private final String level4SubdivisionCode;

	private final String level5SubdivisionCode;


	/**
	 * Creates a region for the given country code.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 *
	 * @since 1.0
	 */
	public GeoRegion(String countryCode) {
		this(countryCode, "", "", "", "", "");
	}

	/**
	 * Creates a region for the given country code and the codes at the 1st subdivision level.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level1SubdivisionCode} is {@code null}
	 *
	 * @since 1.0
	 */
	public GeoRegion(String countryCode, String level1SubdivisionCode) {
		this(countryCode, level1SubdivisionCode, "", "", "", "");
	}

	/**
	 * Creates a region for the given country code and the codes at the 1st and 2nd subdivision levels.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level1SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level2SubdivisionCode} is {@code null}
	 *
	 * @since 1.0
	 */
	public GeoRegion(String countryCode, String level1SubdivisionCode, String level2SubdivisionCode) {
		this(countryCode, level1SubdivisionCode, level2SubdivisionCode, "", "", "");
	}

	/**
	 * Creates a region for the given country code and the codes at the 1st to 3rd subdivision levels.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level1SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level2SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level3SubdivisionCode} is {@code null}
	 *
	 * @since 1.0
	 */
	public GeoRegion(String countryCode, String level1SubdivisionCode, String level2SubdivisionCode, String level3SubdivisionCode) {
		this(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, "", "");
	}

	/**
	 * Creates a region for the given country code and the codes at the 1st to 4th subdivision levels.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level1SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level2SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level3SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level4SubdivisionCode} is {@code null}
	 *
	 * @since 1.0
	 */
	public GeoRegion(String countryCode, String level1SubdivisionCode, String level2SubdivisionCode, String level3SubdivisionCode, String level4SubdivisionCode) {
		this(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, level4SubdivisionCode, "");
	}

	/**
	 * Creates a region for the given country code and the codes at the 1st to 5th subdivision levels.
	 *
	 * @throws IllegalArgumentException if {@code countryCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level1SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level2SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level3SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level4SubdivisionCode} is {@code null}
	 * @throws IllegalArgumentException if {@code level5SubdivisionCode} is {@code null}
	 *
	 * @since 1.0
	 */
	public GeoRegion(String countryCode, String level1SubdivisionCode, String level2SubdivisionCode, String level3SubdivisionCode, String level4SubdivisionCode, String level5SubdivisionCode) {
		Contract.checkArgument(countryCode != null, "Country code must not be null");
		Contract.checkArgument(level1SubdivisionCode != null, "Subdivision code at level 1 must not be null");
		Contract.checkArgument(level2SubdivisionCode != null, "Subdivision code at level 2 must not be null");
		Contract.checkArgument(level3SubdivisionCode != null, "Subdivision code at level 3 must not be null");
		Contract.checkArgument(level4SubdivisionCode != null, "Subdivision code at level 4 must not be null");
		Contract.checkArgument(level5SubdivisionCode != null, "Subdivision code at level 5 must not be null");

		this.countryCode = countryCode;
		this.level1SubdivisionCode = level1SubdivisionCode;
		this.level2SubdivisionCode = level2SubdivisionCode;
		this.level3SubdivisionCode = level3SubdivisionCode;
		this.level4SubdivisionCode = level4SubdivisionCode;
		this.level5SubdivisionCode = level5SubdivisionCode;
	}


	/**
	 * Returns this region limited to the given administrative level.
	 *
	 * @throws IllegalArgumentException if {@code level} is negative
	 * @throws IllegalArgumentException if {@code level} is greater than 5
	 *
	 * @since 1.0
	 */
	public GeoRegion atLevel(int level) {
		Contract.checkArgument(level >= 0, "Level must not be negative: {0}", level);

		switch (level) {
			case 0: {
				return new GeoRegion(countryCode);
			}
			case 1: {
				return new GeoRegion(countryCode, level1SubdivisionCode);
			}
			case 2: {
				return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode);
			}
			case 3: {
				return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode);
			}
			case 4: {
				return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, level4SubdivisionCode);
			}
			case 5: {
				return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, level4SubdivisionCode, level5SubdivisionCode);
			}
			default: {
				throw new IllegalArgumentException(String.format("Invalid level: %d", level));
			}
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof GeoRegion) {
			GeoRegion otherCast = (GeoRegion) other;

			return Objects.equals(countryCode, otherCast.countryCode)
				&& Objects.equals(level1SubdivisionCode, otherCast.level1SubdivisionCode)
				&& Objects.equals(level2SubdivisionCode, otherCast.level2SubdivisionCode)
				&& Objects.equals(level3SubdivisionCode, otherCast.level3SubdivisionCode)
				&& Objects.equals(level4SubdivisionCode, otherCast.level4SubdivisionCode)
				&& Objects.equals(level5SubdivisionCode, otherCast.level5SubdivisionCode);
		}

		return false;
	}

	/**
	 * Return the overall code of this region.
	 *
	 * @since 1.0
	 */
	public String getCode() {
		if (!level5SubdivisionCode.isEmpty()) {
			return String.join(",", countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, level4SubdivisionCode, level5SubdivisionCode);
		} else {
			if (!level4SubdivisionCode.isEmpty()) {
				return String.join(",", countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, level4SubdivisionCode);
			} else {
				if (!level3SubdivisionCode.isEmpty()) {
					return String.join(",", countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode);
				} else {
					if (!level2SubdivisionCode.isEmpty()) {
						return String.join(",", countryCode, level1SubdivisionCode, level2SubdivisionCode);
					} else {
						if (!level1SubdivisionCode.isEmpty()) {
							return String.join(",", countryCode, level1SubdivisionCode);
						} else {
							return countryCode;
						}
					}
				}
			}
		}
	}

	/**
	 * Return the country code of this region.
	 *
	 * @since 1.0
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Returns the default region.
	 *
	 * @since 1.0
	 */
	public static GeoRegion getDefault() {
		return defaultGeoRegion;
	}

	/**
	 * Returns the immediate parent of this region.
	 *
	 * @since 1.0
	 */
	public GeoRegion getParent() {
		if (!StringUtils.isNullOrEmpty(level5SubdivisionCode)) {
			return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode, level4SubdivisionCode);
		}
		if (!StringUtils.isNullOrEmpty(level4SubdivisionCode)) {
			return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode, level3SubdivisionCode);
		}
		if (!StringUtils.isNullOrEmpty(level3SubdivisionCode)) {
			return new GeoRegion(countryCode, level1SubdivisionCode, level2SubdivisionCode);
		}
		if (!StringUtils.isNullOrEmpty(level2SubdivisionCode)) {
			return new GeoRegion(countryCode, level1SubdivisionCode);
		}
		if (!StringUtils.isNullOrEmpty(level1SubdivisionCode)) {
			return new GeoRegion(countryCode);
		}

		return ROOT;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				countryCode,
				level1SubdivisionCode,
				level2SubdivisionCode,
				level3SubdivisionCode,
				level4SubdivisionCode,
				level5SubdivisionCode);
	}

	/**
	 * Returns a region corresponding to the given code.
	 *
	 * @throws IllegalArgumentException if {@code code} is {@code null}
	 * @throws GeoRegionParseException if the region code cannot be parsed
	 *
	 * @since 1.0
	 */
	public static GeoRegion parse(String code) {
		Contract.checkArgument(code != null, "Region code must not be null");

		StringTokenizer tokenizer = new StringTokenizer(code, ",");
		switch (tokenizer.countTokens()) {
			case 1: {
				return new GeoRegion(tokenizer.nextToken());
			}
			case 2: {
				return new GeoRegion(tokenizer.nextToken(), tokenizer.nextToken());
			}
			case 3: {
				return new GeoRegion(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
			}
			case 4: {
				return new GeoRegion(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
			}
			case 5: {
				return new GeoRegion(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
			}
			case 6: {
				return new GeoRegion(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
			}
			default: {
				throw new GeoRegionParseException(String.format("Invalid code: %s", code));
			}
		}
	}

	/**
	 * Sets the default region.
	 *
	 * @since 1.0
	 */
	public static void setDefault(GeoRegion region) {
		Contract.checkArgument(region != null, "Region must not be null");

		defaultGeoRegion = region;
	}

	@Override
	public String toString() {
		return getCode();
	}


	private static void initDefault() {
		String regionCode;

		regionCode = System.getProperty("user.georegion");
		if (!StringUtils.isNullOrEmpty(regionCode)) {
			defaultGeoRegion = parse(regionCode);

			return;
		}

		regionCode = GeoRegionUtils.regionCodeFromCountry();
		if (!StringUtils.isNullOrEmpty(regionCode)) {
			defaultGeoRegion = parse(regionCode);

			return;
		}

		regionCode = GeoRegionUtils.regionCodeFromLanguage();
		if (!StringUtils.isNullOrEmpty(regionCode)) {
			defaultGeoRegion = parse(regionCode);

			return;
		}

		throw new GeoRegionException("Cannot determine default GeoRegion. Please set the 'user.georegion' system property.");
	}

}
