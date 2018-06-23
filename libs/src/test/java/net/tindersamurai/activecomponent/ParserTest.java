package net.tindersamurai.activecomponent;

import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import org.junit.Test;

public class ParserTest {

	@Test
	public void parserNamespaceTest() throws Exception {
		final EscapyComponentParser parser = new XmlStreamComponentParser();
		System.out.println(System.getProperty("user.dir"));
		parser.parseComponent(System.getProperty("user.dir") + "/src/test/resources/TestData.xml");
	}

}