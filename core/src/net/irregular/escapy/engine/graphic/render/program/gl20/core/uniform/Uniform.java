package net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyIndexArray;

import java.util.function.Consumer;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI @Dante
public final class Uniform<T> {

	private Consumer<ShaderProgram> loader;
	private EscapyIndexArray<T> uni;
	private EscapyIndexArray<String> str;
	private T[] uniforms;
	private String[] names;

	private Float[] fArr;
	private Integer[] iArr;
	private Float[][] fFArr;
	private Integer[][] iIArr;

	/**
	 * Created by HenryCo on 29/06/17.<br>
	 * Я блять вообще не ебу нахуй что тут происходит и да я сам в шоке от того что когда-то смог наклепать данный высер
	 * в любом случае эта хуйня нужна чтобы динамически загружать значения юниформов в шейдерах и глубокое понимание
	 * внутреннего состояния не требутся. Алсо это говно работает на рефлексии, но в основном во время инициализации или
	 * установки значений, на скорости обработки графониума это сказаться не должно.<br><br><b>Даже не пытайся понять или
	 * изменить код, просто смирись и используй и тебе понравится.</b>
	 * @param uniformType Type of uniform value to be set in java environment (can be array).
	 */ @EscapyAPI @Dante
	public Uniform(Class<T> uniformType) {

	 	this.uni = new EscapyIndexArray<T>(uniformType){};
		this.str = new EscapyIndexArray<String>(String.class){};
		this.uniforms = uni.container;
		this.names = str.container;
		try {
			if (isIndexOf(uniformType, 0)){
				iArr = (Integer[]) uniforms;
				loader = program -> {for (int i = 0; i < iArr.length; i++) program.setUniformi(names[i], iArr[i]);};
			}
			else if (isIndexOf(uniformType, 0f)){
				fArr = (Float[]) uniforms;
				loader = program -> {for (int i = 0; i < fArr.length; i++) program.setUniformf(names[i], fArr[i]);};
			}
			else if (isIndexOf(uniformType, new Float[0])) {
				fFArr = (Float[][]) uniforms;
				loader = program -> {
					for (int k = 0; k < fFArr.length; k++) for (int i = 0; i < fFArr[k].length; i++) program.setUniformf(names[k], fFArr[k][i]);
				};
			}
			else if (isIndexOf(uniformType, new Integer[0])) {
				iIArr = (Integer[][]) uniforms;
				loader = program -> {
					for (int k = 0; k < iIArr.length; k++) for (int i = 0; i < iIArr[k].length; i++) program.setUniformi(names[k], iIArr[k][i]);
				};
			}

		} catch (Exception ignored) {}
	}

	private boolean isIndexOf(Class<T> tc, Object arg1){
		try{
			return tc.isAssignableFrom(arg1.getClass());
		}catch(Exception e){
			return false;
		}
	}

	@EscapyAPI
	public Uniform addUniform(String name, T val) {
		uni.add(val);
		str.add(name);
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
		for (int i = 0; i < names.length; i++) if (names[i].equals(name)) return uniforms[i];
		return null;
	}
	@EscapyAPI public void set(int index, T val) {
		uni.container[index] = val;
	}
	@EscapyAPI public void set(String name, T val) {
		for (int i = 0; i < str.container.length; i++) if (str.container[i].equals(name)) uni.container[i] = val;
	}
}
