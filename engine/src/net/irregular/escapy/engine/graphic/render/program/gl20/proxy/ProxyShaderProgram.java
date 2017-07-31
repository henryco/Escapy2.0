package net.irregular.escapy.engine.graphic.render.program.gl20.proxy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * @author Henry on 22/07/17.
 */
public class ProxyShaderProgram implements EscapyProxyShaderProgram {


	public final ShaderProgram shaderProgram;
	public ProxyShaderProgram(ShaderProgram shaderProgram) {
		this.shaderProgram = shaderProgram;
	}


	@Override
	public ShaderProgram getShaderProgram() {
		return shaderProgram;
	}

	@Override public String getLog() {
		return shaderProgram.getLog();
	}

	@Override public boolean isCompiled() {
		return shaderProgram.isCompiled();
	}

	@Override public int fetchUniformLocation(String name, boolean pedantic) {
		return shaderProgram.fetchUniformLocation(name, pedantic);
	}

	@Override public void setUniformi(String name, int value) {
		shaderProgram.setUniformi(name, value);
	}

	@Override public void setUniformi(int location, int value) {
		shaderProgram.setUniformi(location, value);
	}

	@Override public void setUniformi(String name, int value1, int value2) {
		shaderProgram.setUniformi(name, value1, value2);
	}

	@Override public void setUniformi(int location, int value1, int value2) {
		shaderProgram.setUniformi(location, value1, value2);
	}

	@Override public void setUniformi(String name, int value1, int value2, int value3) {
		shaderProgram.setUniformi(name, value1, value2, value3);
	}

	@Override public void setUniformi(int location, int value1, int value2, int value3) {
		shaderProgram.setUniformi(location, value1, value2, value3);
	}

	@Override public void setUniformi(String name, int value1, int value2, int value3, int value4) {
		shaderProgram.setUniformi(name, value1, value2, value3, value4);
	}

	@Override public void setUniformi(int location, int value1, int value2, int value3, int value4) {
		shaderProgram.setUniformi(location, value1, value2, value3, value4);
	}

	@Override public void setUniformf(String name, float value) {
		shaderProgram.setUniformf(name, value);
	}

	@Override public void setUniformf(int location, float value) {
		shaderProgram.setUniformf(location, value);
	}

	@Override public void setUniformf(String name, float value1, float value2) {
		shaderProgram.setUniformf(name, value1, value2);
	}

	@Override public void setUniformf(int location, float value1, float value2) {
		shaderProgram.setUniformf(location, value1, value2);
	}

	@Override public void setUniformf(String name, float value1, float value2, float value3) {
		shaderProgram.setUniformf(name, value1, value2, value3);
	}

	@Override public void setUniformf(int location, float value1, float value2, float value3) {
		shaderProgram.setUniformf(location, value1, value2, value3);
	}

	@Override public void setUniformf(String name, float value1, float value2, float value3, float value4) {
		shaderProgram.setUniformf(name, value1, value2, value3, value4);
	}

	@Override public void setUniformf(int location, float value1, float value2, float value3, float value4) {
		shaderProgram.setUniformf(location, value1, value2, value3, value4);
	}

	@Override public void setUniform1fv(String name, float[] values, int offset, int length) {
		shaderProgram.setUniform1fv(name, values, offset, length);
	}

	@Override public void setUniform1fv(int location, float[] values, int offset, int length) {
		shaderProgram.setUniform1fv(location, values, offset, length);
	}

	@Override public void setUniform2fv(String name, float[] values, int offset, int length) {
		shaderProgram.setUniform2fv(name, values, offset, length);
	}

	@Override public void setUniform2fv(int location, float[] values, int offset, int length) {
		shaderProgram.setUniform2fv(location, values, offset, length);
	}

	@Override public void setUniform3fv(String name, float[] values, int offset, int length) {
		shaderProgram.setUniform3fv(name, values, offset, length);
	}

	@Override public void setUniform3fv(int location, float[] values, int offset, int length) {
		shaderProgram.setUniform3fv(location, values, offset, length);
	}

	@Override public void setUniform4fv(String name, float[] values, int offset, int length) {
		shaderProgram.setUniform4fv(name, values, offset, length);
	}

	@Override public void setUniform4fv(int location, float[] values, int offset, int length) {
		shaderProgram.setUniform4fv(location, values, offset, length);
	}

	@Override public void setUniformMatrix(String name, Matrix4 matrix) {
		shaderProgram.setUniformMatrix(name, matrix);
	}

	@Override public void setUniformMatrix(String name, Matrix4 matrix, boolean transpose) {
		shaderProgram.setUniformMatrix(name, matrix, transpose);
	}

	@Override public void setUniformMatrix(int location, Matrix4 matrix) {
		shaderProgram.setUniformMatrix(location, matrix);
	}

	@Override public void setUniformMatrix(int location, Matrix4 matrix, boolean transpose) {
		shaderProgram.setUniformMatrix(location, matrix, transpose);
	}

	@Override public void setUniformMatrix(String name, Matrix3 matrix) {
		shaderProgram.setUniformMatrix(name, matrix);
	}

	@Override public void setUniformMatrix(String name, Matrix3 matrix, boolean transpose) {
		shaderProgram.setUniformMatrix(name, matrix, transpose);
	}

	@Override public void setUniformMatrix(int location, Matrix3 matrix) {
		shaderProgram.setUniformMatrix(location, matrix);
	}

	@Override public void setUniformMatrix(int location, Matrix3 matrix, boolean transpose) {
		shaderProgram.setUniformMatrix(location, matrix, transpose);
	}

	@Override public void setUniformMatrix3fv(String name, FloatBuffer buffer, int count, boolean transpose) {
		shaderProgram.setUniformMatrix3fv(name, buffer, count, transpose);
	}

	@Override public void setUniformMatrix4fv(String name, FloatBuffer buffer, int count, boolean transpose) {
		shaderProgram.setUniformMatrix4fv(name, buffer, count, transpose);
	}

	@Override public void setUniformMatrix4fv(int location, float[] values, int offset, int length) {
		shaderProgram.setUniformMatrix4fv(location, values, offset, length);
	}

	@Override public void setUniformMatrix4fv(String name, float[] values, int offset, int length) {
		shaderProgram.setUniformMatrix4fv(name, values, offset, length);
	}

	@Override public void setUniformf(String name, Vector2 values) {
		shaderProgram.setUniformf(name, values);
	}

	@Override public void setUniformf(int location, Vector2 values) {
		shaderProgram.setUniformf(location, values);
	}

	@Override public void setUniformf(String name, Vector3 values) {
		shaderProgram.setUniformf(name, values);
	}

	@Override public void setUniformf(int location, Vector3 values) {
		shaderProgram.setUniformf(location, values);
	}

	@Override public void setUniformf(String name, Color values) {
		shaderProgram.setUniformf(name, values);
	}

	@Override public void setUniformf(int location, Color values) {
		shaderProgram.setUniformf(location, values);
	}

	@Override public void setVertexAttribute(String name, int size, int type, boolean normalize, int stride, Buffer buffer) {
		shaderProgram.setVertexAttribute(name, size, type, normalize, stride, buffer);
	}

	@Override public void setVertexAttribute(int location, int size, int type, boolean normalize, int stride, Buffer buffer) {
		shaderProgram.setVertexAttribute(location, size, type, normalize, stride, buffer);
	}

	@Override public void setVertexAttribute(String name, int size, int type, boolean normalize, int stride, int offset) {
		shaderProgram.setVertexAttribute(name, size, type, normalize, stride, offset);
	}

	@Override public void setVertexAttribute(int location, int size, int type, boolean normalize, int stride, int offset) {
		shaderProgram.setVertexAttribute(location, size, type, normalize, stride, offset);
	}

	@Override public void begin() {
		shaderProgram.begin();
	}

	@Override public void end() {
		shaderProgram.end();
	}

	@Override public void dispose() {
		shaderProgram.dispose();
	}

	@Override public void disableVertexAttribute(String name) {
		shaderProgram.disableVertexAttribute(name);
	}

	@Override public void disableVertexAttribute(int location) {
		shaderProgram.disableVertexAttribute(location);
	}

	@Override public void enableVertexAttribute(String name) {
		shaderProgram.enableVertexAttribute(name);
	}

	@Override public void enableVertexAttribute(int location) {
		shaderProgram.enableVertexAttribute(location);
	}

	@Override public void setAttributef(String name, float value1, float value2, float value3, float value4) {
		shaderProgram.setAttributef(name, value1, value2, value3, value4);
	}

	@Override public boolean hasAttribute(String name) {
		return shaderProgram.hasAttribute(name);
	}

	@Override public int getAttributeType(String name) {
		return shaderProgram.getAttributeType(name);
	}

	@Override public int getAttributeLocation(String name) {
		return shaderProgram.getAttributeLocation(name);
	}

	@Override public int getAttributeSize(String name) {
		return shaderProgram.getAttributeSize(name);
	}

	@Override public boolean hasUniform(String name) {
		return shaderProgram.hasUniform(name);
	}

	@Override public int getUniformType(String name) {
		return shaderProgram.getUniformType(name);
	}

	@Override public int getUniformLocation(String name) {
		return shaderProgram.getUniformLocation(name);
	}

	@Override public int getUniformSize(String name) {
		return shaderProgram.getUniformSize(name);
	}

	@Override public String[] getAttributes() {
		return shaderProgram.getAttributes();
	}

	@Override public String[] getUniforms() {
		return shaderProgram.getUniforms();
	}

	@Override public String getVertexShaderSource() {
		return shaderProgram.getVertexShaderSource();
	}

	@Override public String getFragmentShaderSource() {
		return shaderProgram.getFragmentShaderSource();
	}

}