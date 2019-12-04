#version 400 core

in vec3 color;
in vec2 uv;
out vec4 out_color;
uniform sampler2D sampler;

void main(){
    
    out_color = texture(sampler, uv) * vec4(color, 1.0f);
   
}

