package net.tindersamurai.activecomponent;

import net.tindersamurai.activecomponent.obj.IEscapyObject;
import net.tindersamurai.activecomponent.obj.IEscapyObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map.Entry;

public class EscapyObjectFactoryTest {

	final static class SomeClass {
		private final int i;
		public SomeClass(int i) {
			this.i = i;
		}
		public int add(int a, int b) {
			return i + a + b;
		}
	}

	@Test
	public void objectInstanceTest() {
		char[] args = {'a', 'b', '-' ,'c'};

		IEscapyObjectFactory factory = IEscapyObjectFactory.Default();
		final IEscapyObject object = factory.createObject("java.lang.String", args);
		Assert.assertEquals("ab-c", object.getObject());

		String[] o = object.invokeMethod("split", "-");
		Assert.assertEquals("ab", o[0]);
		Assert.assertEquals("c", o[1]);
		Assert.assertEquals(2, o.length);
	}

	@Test
	public void nestedObjectInstanceTest() {

		String className = "net.tindersamurai.activecomponent.EscapyObjectFactoryTest$SomeClass";
		Assert.assertEquals(className, SomeClass.class.getName());

		IEscapyObjectFactory factory = IEscapyObjectFactory.Default();
		Entry[] CArgs = {new AbstractMap.SimpleEntry<>(Integer.TYPE, 0)};
		Entry[] args = {
				new AbstractMap.SimpleEntry<>(Integer.TYPE, 40),
				new AbstractMap.SimpleEntry<>(Integer.TYPE, 2)
		};

		final IEscapyObject object = factory.createObject(className, CArgs);
		int result = ((int) object.invokeMethod("add", args));

		Assert.assertEquals(42, result);
	}

}