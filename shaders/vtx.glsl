#version 440 core
layout (location = 0) in vec2 aPos;
layout (location = 2) in vec2 aTexCoord;

out vec2 TexCoord;
uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
void main()
{
   gl_Position =  projection * view * model * vec4(aPos,0.0, 1.0);
   TexCoord = aTexCoord;
}