package net.irregular.escapy.engine.graphic.render.program.shader;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyArray;

import java.util.function.Consumer;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public final class Uniforms <T> {

	private Consumer<ShaderProgram> loader;
	private EscapyArray<T> uni;
	private EscapyArray<String> str;
	private T[] uniforms;
	private String[] names;

	private Float[] fArr;
	private Integer[] iArr;
	private Float[][] fFArr;
	private Integer[][] iIArr;

	@EscapyAPI public Uniforms(Class<T> objClass) {
		this.uni = new EscapyArray<T>(objClass){};
		this.str = new EscapyArray<String>(String.class){};
		this.uniforms = uni.container;
		this.names = str.container;
		try {
			if (indexOf(objClass, 0)){
				iArr = (Integer[]) uniforms;
				loader = program -> {for (int i = 0; i < iArr.length; i++) program.setUniformi(names[i], iArr[i]);};
			}
			else if (indexOf(objClass, 0f)){
				fArr = (Float[]) uniforms;
				loader = program -> {for (int i = 0; i < fArr.length; i++) program.setUniformf(names[i], fArr[i]);};
			}
			else if (indexOf(objClass, new Float[0])) {
				fFArr = (Float[][]) uniforms;
				loader = program -> {
					for (int k = 0; k < fFArr.length; k++) for (int i = 0; i < fFArr[k].length; i++) program.setUniformf(names[k], fFArr[k][i]);
				};
			}
			else if (indexOf(objClass, new Integer[0])) {
				iIArr = (Integer[][]) uniforms;
				loader = program -> {
					for (int k = 0; k < iIArr.length; k++) for (int i = 0; i < iIArr[k].length; i++) program.setUniformi(names[k], iIArr[k][i]);
				};
			}

		} catch (Exception ignored) {}
	}

	private boolean indexOf(Class<T> tc, Object arg1){
		try{
			return tc.isAssignableFrom(arg1.getClass());
		}catch(Exception e){
			return false;
		}
	}

	@EscapyAPI public Uniforms addUniform(String name, T val) {
		uni.addSource(val);
		str.addSource(name);
		uniforms = uni.container;
		names = str.container;

		try {
			iArr = (Integer[]) uniforms;
		} catch (Exception a) {
			try {
				fArr = (Float[]) uniforms;
			} catch (Exception b) {
				try {
					fFArr = (Float[][]) uniforms;
				} catch (Exception c) {
					try {
						iIArr = (Integer[][]) uniforms;
					} catch (Exception ignored) {}
				}
			}
		}
		return this;
	}

	@EscapyAPI public void loadUniforms(ShaderProgram program) {
		loader.accept(program);
	}

	@EscapyAPI public T get(int index) {
		return uniforms[index];
	}
	@EscapyAPI public T get(String name) {
		for (int i = 0; i < names.length; i++) if (names[i].equalsIgnoreCase(name)) return uniforms[i];
		return null;
	}
	@EscapyAPI public void set(int index, T val) {
		uni.container[index] = val;
	}
	@EscapyAPI public void set(String name, T val) {
		for (int i = 0; i < str.container.length; i++) if (str.container[i].equalsIgnoreCase(name)) uni.container[i] = val;
	}
}
