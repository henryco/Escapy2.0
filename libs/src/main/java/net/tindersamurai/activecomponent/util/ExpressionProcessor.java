package net.tindersamurai.activecomponent.util;

import java.util.function.Function;

public class ExpressionProcessor {

	public static String replace (
			String openToken, String closingToken,
			String expression, Function <String, String> onTokenReplace
	) {
		final StringBuilder builder = new StringBuilder();
		for (String token : expression.trim().split(closingToken)) {
			String[] rs = token.trim().split(openToken);
			builder.append(rs[0]);
			if (rs.length == 2)
				builder.append(onTokenReplace.apply(rs[1]));
		}
		return builder.toString();
	}
}