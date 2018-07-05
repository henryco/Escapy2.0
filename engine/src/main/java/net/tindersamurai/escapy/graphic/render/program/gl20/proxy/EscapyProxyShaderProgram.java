package net.tindersamurai.escapy.graphic.render.program.gl20.proxy;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import lombok.experimental.Delegate;

import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * @author Henry on 22/07/17.
 */
public interface EscapyProxyShaderProgram {

	ShaderProgram getShaderProgram();

	static void invalidateAllShaderPrograms(Application app) {
		ShaderProgram.invalidateAllShaderPrograms(app);
	}

	static void clearAllShaderPrograms(Application app) {
		ShaderProgram.clearAllShaderPrograms(app);
	}

	static String getManagedStatus() {
		return ShaderProgram.getManagedStatus();
	}

	static int getNumManagedShaderPrograms() {
		return ShaderProgram.getNumManagedShaderPrograms();
	}

	String getLog();

	boolean isCompiled();

	int fetchUniformLocation(String name, boolean pedantic);

	void setUniformi(String name, int value);

	void setUniformi(int location, int value);

	void setUniformi(String name, int value1, int value2);

	void setUniformi(int location, int value1, int value2);

	void setUniformi(String name, int value1, int value2, int value3);

	void setUniformi(int location, int value1, int value2, int value3);

	void setUniformi(String name, int value1, int value2, int value3, int value4);

	void setUniformi(int location, int value1, int value2, int value3, int value4);

	void setUniformf(String name, float value);

	void setUniformf(int location, float value);

	void setUniformf(String name, float value1, float value2);

	void setUniformf(int location, float value1, float value2);

	void setUniformf(String name, float value1, float value2, float value3);

	void setUniformf(int location, float value1, float value2, float value3);

	void setUniformf(String name, float value1, float value2, float value3, float value4);

	void setUniformf(int location, float value1, float value2, float value3, float value4);

	void setUniform1fv(String name, float[] values, int offset, int length);

	void setUniform1fv(int location, float[] values, int offset, int length);

	void setUniform2fv(String name, float[] values, int offset, int length);

	void setUniform2fv(int location, float[] values, int offset, int length);

	void setUniform3fv(String name, float[] values, int offset, int length);

	void setUniform3fv(int location, float[] values, int offset, int length);

	void setUniform4fv(String name, float[] values, int offset, int length);

	void setUniform4fv(int location, float[] values, int offset, int length);

	void setUniformMatrix(String name, Matrix4 matrix);

	void setUniformMatrix(String name, Matrix4 matrix, boolean transpose);

	void setUniformMatrix(int location, Matrix4 matrix);

	void setUniformMatrix(int location, Matrix4 matrix, boolean transpose);

	void setUniformMatrix(String name, Matrix3 matrix);

	void setUniformMatrix(String name, Matrix3 matrix, boolean transpose);

	void setUniformMatrix(int location, Matrix3 matrix);

	void setUniformMatrix(int location, Matrix3 matrix, boolean transpose);

	void setUniformMatrix3fv(String name, FloatBuffer buffer, int count, boolean transpose);

	void setUniformMatrix4fv(String name, FloatBuffer buffer, int count, boolean transpose);

	void setUniformMatrix4fv(int location, float[] values, int offset, int length);

	void setUniformMatrix4fv(String name, float[] values, int offset, int length);

	void setUniformf(String name, Vector2 values);

	void setUniformf(int location, Vector2 values);

	void setUniformf(String name, Vector3 values);

	void setUniformf(int location, Vector3 values);

	void setUniformf(String name, Color values);

	void setUniformf(int location, Color values);

	void setVertexAttribute(String name, int size, int type, boolean normalize, int stride, Buffer buffer);

	void setVertexAttribute(int location, int size, int type, boolean normalize, int stride, Buffer buffer);

	void setVertexAttribute(String name, int size, int type, boolean normalize, int stride, int offset);

	void setVertexAttribute(int location, int size, int type, boolean normalize, int stride, int offset);

	void begin();

	void end();

	void dispose();

	void disableVertexAttribute(String name);

	void disableVertexAttribute(int location);

	void enableVertexAttribute(String name);

	void enableVertexAttribute(int location);

	void setAttributef(String name, float value1, float value2, float value3, float value4);

	boolean hasAttribute(String name);

	int getAttributeType(String name);

	int getAttributeLocation(String name);

	int getAttributeSize(String name);

	boolean hasUniform(String name);

	int getUniformType(String name);

	int getUniformLocation(String name);

	int getUniformSize(String name);

	String[] getAttributes();

	String[] getUniforms();

	String getVertexShaderSource();

	String getFragmentShaderSource();
}
