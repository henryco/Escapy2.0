#version 330 core

in vec4 v_color;
in vec2 v_texCoord0;

uniform sampler2D u_sampler2D;

void main(){
	vec4 color = texture2D(u_sampler2D, v_texCoord0) * v_color;
	color.rgb = vec3(1.0) - color.rgb;
	gl_FragColor = color;
}