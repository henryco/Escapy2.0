package net.irregular.escapy.map;

import net.irregular.escapy.map.data.comp.annotation.Arg;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponent;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponentFactory;
import net.irregular.escapy.map.data.comp.factory.EscapyComponentAnnotationFactory;
import net.irregular.escapy.map.data.comp.factory.IEscapyComponentFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class EscapyComponentFactoryTest {


	@Test
	public void annotationComponentFactoryTest() {

		@EscapyComponentFactory
		final class CompFactory {
			@EscapyComponent("some")
			public String something(@Arg("name") String name,
									@Arg("age") Integer age) {
				if (age == null) return "WOW";
				return name + "-" + age.toString();
			}
		}

		Map<String, Object> args1 = new HashMap<String, Object>() {{
			put("age", 42);
			put("name", "galaxy");
		}};
		Map<String, Object> args2 = new HashMap<String, Object>() {{
			put("age", 10);
			put("name", "null");
		}};
		Map<String, Object> args3 = new HashMap<String, Object>() {{
			put("age", 10);
		}};
		Map<String, Object> args4 = new HashMap<String, Object>() {{
			put("0", "number");
			put("age", 10);
		}};
		Map<String, Object> args5 = new HashMap<String, Object>() {{
			put("age", 10);
			put("name", "galaxy");
			put("more", new Float[5]);
		}};
		Map<String, Object> args6 = new HashMap<String, Object>() {{
			put("0", "galaxy");
			put("1", 10);
			put("2", new Float[5]);
		}};
		Map<String, Object> args7 = new HashMap<String, Object>() {{
		}};

		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new CompFactory());

		Assert.assertEquals("galaxy-42", factory.createComponent("CompFactory:some", args1).toString());
		Assert.assertEquals("null-10", factory.createComponent("CompFactory:some", args2).toString());
		Assert.assertEquals("null-10", factory.createComponent("CompFactory:some", args3).toString());
		Assert.assertEquals("number-10", factory.createComponent("CompFactory:some", args4).toString());
		Assert.assertEquals("galaxy-10", factory.createComponent("CompFactory:some", args5).toString());
		Assert.assertEquals("galaxy-10", factory.createComponent("CompFactory:some", args6).toString());
		Assert.assertEquals("WOW", factory.createComponent("CompFactory:some", args7).toString());
	}

	@Test
	public void namelessFactoryTest() {

		@EscapyComponentFactory
		final class CompFactory {
			@EscapyComponent("some")
			public String something(String name, Integer age) {
				if (age == null) return "WOW";
				return name + "-" + age.toString();
			}
		}

		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new CompFactory());
		Map<String, Object> args1 = new HashMap<String, Object>() {{
			put("0", "galaxy");
			put("1", 42);
		}};
		Assert.assertEquals("galaxy-42", factory.createComponent("CompFactory:some", args1).toString());
	}

	@Test // TODO
	public void typeTest() throws Exception {
		System.out.println(int.class.getName());
		show(int.class);
		show((Class<?>) Class.forName("java.lang.Integer").getField("TYPE").get(null));

	}

	public void show(Class<?> c) {
		System.out.println(c);
	}

}