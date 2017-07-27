#version 330 core
attribute vec4 a_color;
attribute vec2 a_texCoord0;
attribute vec3 a_position;

out vec4 v_color;
out vec2 v_texCoord0;

uniform mat4 u_projTrans;

void main(){
	v_color = a_color;
	v_texCoord0 = a_texCoord0;
	gl_Position = u_projTrans * vec4(a_position, 1.0);

}