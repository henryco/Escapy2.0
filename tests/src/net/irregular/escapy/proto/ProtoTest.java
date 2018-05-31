package net.irregular.escapy.proto;

import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

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

}