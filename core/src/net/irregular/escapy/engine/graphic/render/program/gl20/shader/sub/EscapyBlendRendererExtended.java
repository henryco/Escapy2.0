package net.irregular.escapy.engine.graphic.render.program.gl20.shader.sub;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.uniform.Uniforms;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI @Dante
public class EscapyBlendRendererExtended extends EscapyBlendRenderer {

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


	@EscapyAPI @Dante public EscapyBlendRendererExtended() {}
	@EscapyAPI @Dante public EscapyBlendRendererExtended(ShaderFile shaderFile) {
		super(shaderFile);
	}
	@EscapyAPI @Dante public EscapyBlendRendererExtended(ShaderFile shaderFile, String... sourcesNames) {
		super(shaderFile, sourcesNames);
	}


	@Override
	public Collection<Uniforms> getUniforms() {
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
	@EscapyAPI public Float[] getFloatArrayUniform(String name) {
		return floatArrUniforms.get(name);
	}
	@EscapyAPI public int getIntUniform(String name) {
		return intUniforms.get(name);
	}
	@EscapyAPI public Integer[] getIntArrayUniform(String name) {
		return intArrUniforms.get(name);
	}


//	--------------------- SET -----------------------------------
	@EscapyAPI public void setFloatUniform(String name, float value) {
		this.floatUniforms.set(name, value);
	}
	@EscapyAPI public void setFloatArrayUniform(String name, Float[] values) {
		this.floatArrUniforms.set(name, values);
	}
	@EscapyAPI public void setIntUniform(String name, int value) {
		this.intUniforms.set(name, value);
	}
	@EscapyAPI public void setIntArrayUniform(String name, Integer[] values) {
		this.intArrUniforms.set(name, values);
	}


}
