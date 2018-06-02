package net.irregular.escapy.proto;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.graphic.camera.IEscapyCamera;
import net.irregular.escapy.graphic.render.model.IEscapyModel;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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

	@Test
	public void typeTest() {
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

}