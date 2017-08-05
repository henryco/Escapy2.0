package net.irregular.escapy.graphic.render.program.gl20.core.uniform;

import net.irregular.escapy.context.annotation.Dante;
import net.irregular.escapy.context.annotation.EscapyAPI;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
@Dante
public class StandardUniforms {

	private final Uniform<Float> floatUniform = new Uniform<>(Float.class);
	private final Uniform<Integer> intUniform = new Uniform<>(Integer.class);
	private final Uniform<Float[]> floatArrUniform = new Uniform<>(Float[].class);
	private final Uniform<Integer[]> intArrUniform = new Uniform<>(Integer[].class);

	private final Collection<Uniform> uniformCollection; {
		uniformCollection = new ArrayList<>();
		uniformCollection.add(intUniform);
		uniformCollection.add(floatUniform);
		uniformCollection.add(intArrUniform);
		uniformCollection.add(floatArrUniform);
	}


	@EscapyAPI public Collection<Uniform> getUniforms() {
		return uniformCollection;
	}


	//	----------------------- ADD -------------------------------
	@EscapyAPI public void addFloatUniform(String name) {
		this.addFloatUniform(name, 0f);
	}
	@EscapyAPI public void addFloatUniform(String name, float value) {
		this.floatUniform.addUniform(name, value);
	}
	@EscapyAPI public void addFloatArrayUniform(String name, Float... values) {
		this.floatArrUniform.addUniform(name, values);
	}
	@EscapyAPI public void addFloatArrayUniform(String name) {
		this.addFloatArrayUniform(name, new Float[0]);
	}
	@EscapyAPI public void addIntUniform(String name, int value) {
		this.intUniform.addUniform(name, value);
	}
	@EscapyAPI public void addIntUniform(String name) {
		this.addIntUniform(name, 0);
	}
	@EscapyAPI public void addIntArrayUniform(String name, Integer... values) {
		this.intArrUniform.addUniform(name, values);
	}
	@EscapyAPI public void addIntArrayUniform(String name) {
		this.addIntArrayUniform(name, new Integer[0]);
	}

	//	---------------------- GET ---------------------------------
	@EscapyAPI public float getFloatUniform(String name) {
		return floatUniform.get(name);
	}
	@EscapyAPI public float getFloatUniform(int index) {
		return floatUniform.get(index);
	}
	@EscapyAPI public Float[] getFloatArrayUniform(String name) {
		return floatArrUniform.get(name);
	}
	@EscapyAPI public Float[] getFloatArrayUniform(int index) {
		return floatArrUniform.get(index);
	}
	@EscapyAPI public int getIntUniform(String name) {
		return intUniform.get(name);
	}
	@EscapyAPI public int getIntUniform(int index) {
		return intUniform.get(index);
	}
	@EscapyAPI public Integer[] getIntArrayUniform(String name) {
		return intArrUniform.get(name);
	}
	@EscapyAPI public Integer[] getIntArrayUniform(int index) {
		return intArrUniform.get(index);
	}


	//	--------------------- SET -----------------------------------
	@EscapyAPI public void setFloatUniform(String name, float value) {
		this.floatUniform.set(name, value);
	}
	@EscapyAPI public void setFloatUniform(int index, float value) {
		this.floatUniform.set(index, value);
	}
	@EscapyAPI public void setFloatArrayUniform(String name, Float... values) {
		this.floatArrUniform.set(name, values);
	}
	@EscapyAPI public void setFloatArrayUniform(int index, Float... values) {
		this.floatArrUniform.set(index, values);
	}
	@EscapyAPI public void setIntUniform(String name, int value) {
		this.intUniform.set(name, value);
	}
	@EscapyAPI public void setIntUniform(int index, int value) {
		this.intUniform.set(index, value);
	}
	@EscapyAPI public void setIntArrayUniform(String name, Integer... values) {
		this.intArrUniform.set(name, values);
	}
	@EscapyAPI public void setIntArrayUniform(int index, Integer... values) {
		this.intArrUniform.set(index, values);
	}


}
