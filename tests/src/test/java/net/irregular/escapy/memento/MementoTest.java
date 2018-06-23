package net.irregular.escapy.memento;

import net.irregular.escapy.GdxTestRunner;
import net.irregular.escapy.utils.memento.EscapyCloneable;
import net.irregular.escapy.utils.memento.Memento;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class MementoTest {

	@Test
	public void test() {

		final class TestClass implements EscapyCloneable<TestClass> {
			String someText;

			public TestClass(String text) {
				this.someText = text;
			}

			@Override
			public TestClass copy() {
				return new TestClass(this.someText);
			}

			@Override
			public String toString() {
				return "TestClass{" +
						"someText='" + someText + '\'' +
						'}';
			}
		}

		Memento<TestClass> memento = new Memento<>(new TestClass("initial"));
		memento.save();
		for (int i = 0; i < 9; i++) {
			memento.get().someText = Integer.toString(i);
			Assert.assertEquals(memento.get().toString(), "TestClass{someText='" + i  + "'}");
			memento.save();
		}

		for (int i = 0; i < 9; i++) {
			memento.revert();
			Assert.assertEquals(memento.get().toString(), "TestClass{someText='" + (8 - i)  + "'}");
		}

		for (int i = 0; i < 3; i++) {
			memento.revert();
			Assert.assertEquals(memento.get().toString(), "TestClass{someText='initial'}");
		}
	}

}
