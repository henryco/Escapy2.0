package net.tindersamurai.activecomponent;

import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import org.junit.Test;

public class ParserTest {

	@EscapyComponentFactory("test")
	public static final class TestComponent {

		@EscapyComponent("main")
		public void main(Object ...args) {
			for (Object arg : args)
				System.out.println(arg);

			// todo
		}
	}


	@Test
	public void parserNamespaceTest() throws Exception {
		final EscapyComponentParser parser = new XmlStreamComponentParser();
		System.out.println(System.getProperty("user.dir"));
		parser.parseComponent(System.getProperty("user.dir") + "/src/test/resources/test.xml");
	}

}