package net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI @Dante
public class StandardUniforms {

	private final Uniforms<Float> floatUniforms = new Uniforms<>(Float.class);
	private final Uniforms<Integer> intUniforms = new Uniforms<>(Integer.class);
	private final Uniforms<Float[]> floatArrUniforms = new Uniforms<>(Float[].class);
	private final Uniforms<Integer[]> intArrUniforms = new Uniforms<>(Integer[].class);

	private final Collection<Uniforms> uniformsCollection; {
		uniformsCollection = new ArrayList<>();
		uniformsCollection.add(intUniforms);
		uniformsCollection.add(floatUniforms);
		uniformsCollection.add(intArrUniforms);
		uniformsCollection.add(floatArrUniforms);
	}


	@EscapyAPI public Collection<Uniforms> getUniforms() {
		return uniformsCollection;
	}


	//	----------------------- ADD -------------------------------
	@EscapyAPI public void addFloatUniform(String name, float value) {
		this.floatUniforms.addUniform(name, value);
	}
	@EscapyAPI public void addFloatArrayUniform(String name, Float[] values) {
		this.floatArrUniforms.addUniform(name, values);
	}
	@EscapyAPI public void addIntUniform(String name, int value) {
		this.intUniforms.addUniform(name, value);
	}
	@EscapyAPI public void addIntArrayUniform(String name, Integer[] values) {
		this.intArrUniforms.addUniform(name, values);
	}


	//	---------------------- GET ---------------------------------
	@EscapyAPI public float getFloatUniform(String name) {
		return floatUniforms.get(name);
	}
	@EscapyAPI public float getFloatUniform(int index) {
		return floatUniforms.get(index);
	}
	@EscapyAPI public Float[] getFloatArrayUniform(String name) {
		return floatArrUniforms.get(name);
	}
	@EscapyAPI public Float[] getFloatArrayUniform(int index) {
		return floatArrUniforms.get(index);
	}
	@EscapyAPI public int getIntUniform(String name) {
		return intUniforms.get(name);
	}
	@EscapyAPI public int getIntUniform(int index) {
		return intUniforms.get(index);
	}
	@EscapyAPI public Integer[] getIntArrayUniform(String name) {
		return intArrUniforms.get(name);
	}
	@EscapyAPI public Integer[] getIntArrayUniform(int index) {
		return intArrUniforms.get(index);
	}


	//	--------------------- SET -----------------------------------
	@EscapyAPI public void setFloatUniform(String name, float value) {
		this.floatUniforms.set(name, value);
	}
	@EscapyAPI public void setFloatUniform(int index, float value) {
		this.floatUniforms.set(index, value);
	}
	@EscapyAPI public void setFloatArrayUniform(String name, Float[] values) {
		this.floatArrUniforms.set(name, values);
	}
	@EscapyAPI public void setFloatArrayUniform(int index, Float[] values) {
		this.floatArrUniforms.set(index, values);
	}
	@EscapyAPI public void setIntUniform(String name, int value) {
		this.intUniforms.set(name, value);
	}
	@EscapyAPI public void setIntUniform(int index, int value) {
		this.intUniforms.set(index, value);
	}
	@EscapyAPI public void setIntArrayUniform(String name, Integer[] values) {
		this.intArrUniforms.set(name, values);
	}
	@EscapyAPI public void setIntArrayUniform(int index, Integer[] values) {
		this.intArrUniforms.set(index, values);
	}


}
