package net.irregular.escapy.utils.memento.test;

import net.irregular.escapy.utils.memento.EscapyCloneable;
import net.irregular.escapy.utils.memento.Memento;
import org.junit.Test;

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
			System.out.println(memento.get());
			memento.save();
		}

		System.out.println("-----");

		for (int i = 0; i < 12; i++) {
			memento.revert();
			System.out.println(memento.get());
		}

	}

}
