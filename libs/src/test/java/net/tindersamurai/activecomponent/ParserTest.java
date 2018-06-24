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
		}
	}

	public static final class SomeObj {
		private final Short s;
		public SomeObj(Short s) {
			this.s = s;
		}

		@Override
		public String toString() {
			return "SomeObj{" +
					"s=" + s +
					'}';
		}
	}

	public static final class SomeObj2 {
		private final Short s;
		private final Integer a;
		public SomeObj2(Short s, Integer a) {
			this.s = s;
			this.a = a;
		}

		@Override
		public String toString() {
			return "SomeObj2{" +
					"s=" + s +
					", a=" + a +
					'}';
		}
	}

	@Test
	public void parserNamespaceTest() throws Exception {
		final EscapyComponentParser parser = new XmlStreamComponentParser(new TestComponent());
		System.out.println(System.getProperty("user.dir"));
		parser.parseComponent(System.getProperty("user.dir") + "/src/test/resources/test.xml");
	}

}