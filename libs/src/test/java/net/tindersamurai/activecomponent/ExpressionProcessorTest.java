package net.tindersamurai.activecomponent;

import net.tindersamurai.activecomponent.util.ExpressionProcessor;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionProcessorTest {

	@Test
	public void simpleExpressionTest() {
		final String openToken = "\\$\\{";
		final String closingToken = "}";

		String arg = "${resources.config-path}/animations/${some}/mco.eacxml";
		String result = ExpressionProcessor.replace(openToken, closingToken, arg,
				(String token) -> {
			if (token.equals("resources.config-path"))
				return "WOW";
			if (token.equals("some"))
				return "BANG";
			return "WRONG";
		});
		Assert.assertEquals("WOW/animations/BANG/mco.eacxml", result);
	}

	@Test
	public void noExpressionTest() {
		final String openToken = "\\$\\{";
		final String closingToken = "}";
		String arg = "WOW/animations/BANG/mco.eacxml";
		String result = ExpressionProcessor.replace(openToken, closingToken, arg,
				(String token) -> {
			throw new RuntimeException("ERROR");
		});
		Assert.assertEquals(arg, result);
	}

	@Test
	public void notExpectedExpressionTest() {
		final String openToken = "\\$\\{";
		final String closingToken = "}";

		String arg = "test_${resources.config-path}/animations/${some}/${nothing}-${dunno}/mco.eacxml";
		String result = ExpressionProcessor.replace(openToken, closingToken, arg,
				(String token) -> {
			if (token.equals("resources.config-path"))
				return "WOW";
			if (token.equals("some"))
				return "BANG";
			return "UNDEFINED";
		});
		Assert.assertEquals("test_WOW/animations/BANG/UNDEFINED-UNDEFINED/mco.eacxml", result);
	}
}