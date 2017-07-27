#version 330 core

in vec4 v_texCoord0;

uniform sampler2D colorMap;
uniform sampler2D maskMap;
uniform float threshold;

const vec3 c_one = vec3(1);


void main() {

	vec4 col = texture2D(colorMap, v_texCoord0.st);

	if (col.a != 0) {

        vec4 maskRGBA = texture2D(maskMap, v_texCoord0.st);
        col = max(maskRGBA, col);

        if (dot(c_one.rgb, col.rgb) <= threshold)
            col = vec4(0);
	}

	gl_FragColor = col;
}