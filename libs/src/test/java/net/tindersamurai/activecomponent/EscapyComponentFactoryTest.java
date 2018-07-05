package net.tindersamurai.activecomponent;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentAnnotationFactory;
import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;
import net.tindersamurai.activecomponent.core.UtilityCoreComponent;
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

		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(":", new CompFactory());

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
		Assert.assertEquals("galaxy-42", factory.createComponent("CompFactory.some", args1).toString());
	}


	@Test
	public void nestedFactoryTest() {
		@EscapyComponentFactory
		final class CompFactory {

			@EscapyComponent("some")
			public String something(String name, Integer age) {
				if (age == null) return "WOW";
				return name + "-" + age.toString();
			}

			@EscapyComponentFactory("BANG")
			final class BangFactory {
				@EscapyComponent("wow")
				public Integer show() {
					return 42;
				}
			}
		}

		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new CompFactory());
		Assert.assertEquals("42", factory.createComponent("CompFactory.BANG.wow", new HashMap<>()).toString());
	}


	@Test
	public void primitiveTypeComponentTest() {
		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new UtilityCoreComponent());
		Map<String, Object> args = new HashMap<String, Object>() {{
			put("object", 10);
		}};
		Assert.assertEquals(int.class, factory.createComponent("u.p.type", args));
	}

	@Test
	public void arrayComponentTest() {
		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new UtilityCoreComponent());
		Map<String, Object> args = new HashMap<String, Object>() {{
			put("type", Float.class);
			put("1", 10f);
			put("2", 0.5f);
			put("3", 42.2f);
		}};
		final Object array = factory.createComponent("u.array", args);
		Assert.assertEquals(Float[].class, array.getClass());
		Assert.assertArrayEquals(new Float[]{10f, 0.5f, 42.2f}, (Object[]) array);
	}

	@Test
	public void arrayComponentVarargTest() {
		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new UtilityCoreComponent());
		Map<String, Object> args = new HashMap<String, Object>() {{
			put("type", Float.class);
			put("elements", new Float[] {10f, 0.5f, 42.2f});
		}};
		final Object array = factory.createComponent("u.array", args);
		Assert.assertEquals(Float[].class, array.getClass());
		Assert.assertArrayEquals(new Float[]{10f, 0.5f, 42.2f}, (Object[]) array);
	}

	@Test
	public void arrayComponentVarargTest2() {
		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new UtilityCoreComponent());
		Map<String, Object> args = new HashMap<String, Object>() {{
			put("type", Integer.class);
			put("1", new Integer[] {10, 5, 42});
		}};

		try {
			factory.createComponent("u.array", args);
		} catch (RuntimeException e) {
			assert "Cannot create component: u.array".equals(e.getMessage());
		}

	}

	@Test
	public void arrayPrimitiveComponentTest() {
		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new UtilityCoreComponent());
		Map<String, Object> args = new HashMap<String, Object>() {{
			put("type", short.class);
			put("1", new Short((short) 40));
			put("2", new Short((short) 24));
			put("3", new Short((short) 15));
			put("4", new Short((short) 21));

		}};
		final Object array = factory.createComponent("u.array", args);

		Assert.assertEquals(short[].class, array.getClass());
		Assert.assertArrayEquals(new short[]{40, 24, 15, 21}, (short[]) array);
	}

	@Test
	public void typeTest() {
		@EscapyComponentFactory
		final class CompFactory {

			@EscapyComponent("some")
			public String something(@Arg("a") int a,
									@Arg("age") Integer age) {
				if (age == null) return "WOW";
				return a + "-" + age.toString();
			}
		}

		IEscapyComponentFactory factory = new EscapyComponentAnnotationFactory(new CompFactory());
		Map<String, Object> args = new HashMap<String, Object>() {{
			put("a", 40);
			put("age", 23);
		}};
		Assert.assertEquals("40-23", factory.createComponent("CompFactory.some", args));
	}


}