package net.irregular.escapy.map;

import net.irregular.escapy.map.data.parser.EscapyComponentParser;
import net.irregular.escapy.map.data.parser.XmlStreamComponentParser;
import org.junit.Test;

public class ParserTest {

	@Test
	public void parserNamespaceTest() throws Exception {
		final EscapyComponentParser parser = new XmlStreamComponentParser();
		System.out.println(System.getProperty("user.dir"));
		parser.parseComponent(System.getProperty("user.dir") + "/src/resources/TestData.xml");
	}

}