/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.opengl.GL30;


/**
 *
 * @author mint
 */
public class Shader {
    private Map<String, Integer> locations;
    private int id;
    public Shader(String vertDir, String fragDir){
        locations = new HashMap<>();
        id = glCreateProgram();
        int vertex = loadShader("assets/shaders/"+ vertDir, GL_VERTEX_SHADER);
        int fragment = loadShader("assets/shaders/"+ fragDir, GL_FRAGMENT_SHADER);
        glAttachShader(id, vertex);
        glAttachShader(id, fragment);
        glLinkProgram(id);
        glValidateProgram(id);
    }
    
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);
        if(glGetShaderi(shaderID, GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }
    public void enable(){
        GL30.glUseProgram(id);
    }
    public int getUniformLocation(String location){
        if(!locations.containsKey(location))
            locations.put(location, GL20.glGetUniformLocation(id, location));
        return locations.get(location);
    }
    public void disable(){
        GL30.glUseProgram(0);
    }
    public void loadVector3Uniform(float x, float y, float z, String name){
        GL20.glUniform3f(getUniformLocation(name), x, y, z);
    }
    public void loadMatrixuniform(Matrix4f matrix, String name){
        GL20.glUniformMatrix4fv(GL20.glGetUniformLocation(id, name), false, Utils.toFloatBuffer(matrix));
    }
    
    
}
