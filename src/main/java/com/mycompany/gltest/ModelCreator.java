/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author mint
 */
public class ModelCreator {

    public static final ModelCreator instance = new ModelCreator();
    private static final int MAX_VERTICES = 10000000;
    private FloatBuffer positions, uvs, colors;
    private float r = 1, b = 1, g = 1, u, v;
    private int vertexCount = 0;

    public ModelCreator() {

        positions = BufferUtils.createFloatBuffer(MAX_VERTICES * 3);
        uvs = BufferUtils.createFloatBuffer(MAX_VERTICES * 2);
        colors = BufferUtils.createFloatBuffer(MAX_VERTICES * 3);
    }

    public void vertex(float x, float y, float z) {
        if (vertexCount < MAX_VERTICES - 3) {
            positions.put(x).put(y).put(z);
            colors.put(r).put(g).put(b);
            uvs.put(u).put(v);
            vertexCount++;
        }

    }

    public void color(float r, float g, float b) {
        //colors.put(r).put(b).put(g);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Model create() {

        positions.flip();
        uvs.flip();
        colors.flip();
        int vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        int positionsVBO = GL30.glGenBuffers();

        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, positionsVBO);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, positions, GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);

        int colorsVBO = GL30.glGenBuffers();

        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, colorsVBO);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, colors, GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);

        int uvsVBO = GL30.glGenBuffers();

        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, uvsVBO);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, uvs, GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);

        GL30.glBindVertexArray(0);

        positions.clear();
        colors.clear();
        uvs.clear();

        this.r = 1;
        this.g = 1;
        this.b = 1;
        this.u = 0;
        this.v = 0;
        //this.vertexCount = positions.size()/3;
        Model m = new Model(vao, vertexCount);
        this.vertexCount = 0;
        return m;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void uv(float u, float v) {
        //uvs.put(u).put(v);
        this.u = u;
        this.v = v;
    }

}
