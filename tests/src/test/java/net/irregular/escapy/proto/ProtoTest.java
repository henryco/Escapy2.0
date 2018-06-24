package net.irregular.escapy.proto;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.graphic.camera.IEscapyCamera;
import net.irregular.escapy.map.model.IEscapyModel;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.*;

public class ProtoTest {

	@Test
	public void testRandomStringUUID() {
		System.out.println(UUID.randomUUID().toString());
	}

	@Test
	public void keyParseTest() {
		System.out.println(Arrays.toString("one:two:three:four".split(":")));
		System.out.println(Arrays.toString("one : two".split(":")));
		System.out.println(Arrays.toString("one".split(":")));

		System.out.println(Arrays.toString(Arrays.stream("one : two".split(":")).map(String::trim).toArray(String[]::new)));
	}

	@Test
	public void typeTest() {
		List<Integer> list = new ArrayList<>();
		System.out.println(list.getClass());
		System.out.println(Arrays.toString(list.getClass().getDeclaredMethods()));

		Collection<String> collection = Collections.EMPTY_LIST;
		for (String s : collection) {
			System.out.println(s);
		}
	}

	@Test
	public void renderModelProto() {
		IEscapyModel model = new IEscapyModel() {
			@Override
			public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {

			}
		};
	}


	private static final class A {
		private class B {}
		private static class C {}
		private static final class D {}
		public class E {}
		public static class F {}
		public static final class G {}
	}

	@Test
	public void nestedClassProto() throws Exception {
		A a = new A();
		for (Class<?> aClass : a.getClass().getDeclaredClasses()) {
			final Constructor<?>[] cons = aClass.getDeclaredConstructors();
			if (cons.length != 1) throw new RuntimeException(">1 C");
			cons[0].setAccessible(true);
			switch (cons[0].getParameterCount()) {
				case 0: cons[0].newInstance(); break;
				case 1: cons[0].newInstance(a); break;
				default: throw new RuntimeException(">1 A");
			}
		}
	}

}