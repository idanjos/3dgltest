#version 400 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 in_color;
layout (location = 2) in vec2 in_uvs;
out vec3 color;
out vec2 uv;
uniform mat4 projection;
uniform mat4 view;
uniform vec3 translation;
void main(){
    uv = in_uvs;
    color = in_color;
    gl_Position = projection * view * vec4(position.x + translation.x , position.y+translation.y, position.z+translation.z, 1.0);
    
}
