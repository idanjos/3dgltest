/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import com.mycompany.gltest.shaders.ChunkShader;
import entities.Player;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author mint
 */
public class ChunkRenderer {

    private ChunkShader shader;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;

    public ChunkRenderer() {
        this.shader = new ChunkShader();
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
        Window window = Game.instance.getWindow();
        projectionMatrix.perspective((float) Math.toRadians(90), (float) window.getWidth() / (float) window.getHeight(), 0.1f, 1000);
    }

    public void render(Chunk chunk, Player player) {
        Maths.createViewMatrix(player.x, player.y, player.z, player.rx, player.ry, player.rz, viewMatrix);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL13.GL_TEXTURE0);
        shader.enable();

        Block.atlas.bind();
        shader.loadMatrixuniform(projectionMatrix, "projection");
        shader.loadMatrixuniform(viewMatrix, "view");
        GL30.glBindVertexArray(chunk.getModel().getVao());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);

        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, chunk.getModel().getVertexCount());

        //GL30.glDrawElements(GL30.GL_TRIANGLES, chunk.getModel().getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
        GL30.glBindVertexArray(0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        Texture.unbind();
        shader.disable();
    }

    public void render(Chunk[] chunks, Player player) {
        Maths.createViewMatrix(player.x, player.y, player.z, player.rx, player.ry, player.rz, viewMatrix);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL13.GL_TEXTURE0);
        shader.enable();

        Block.atlas.bind();
        shader.loadMatrixuniform(projectionMatrix, "projection");
        shader.loadMatrixuniform(viewMatrix, "view");

        for (Chunk chunk : chunks) {
            shader.loadVector3Uniform(chunk.x*16,0, chunk.z*16, "translation");
            GL30.glBindVertexArray(chunk.getModel().getVao());
            GL30.glEnableVertexAttribArray(0);
            GL30.glEnableVertexAttribArray(1);
            GL30.glEnableVertexAttribArray(2);

            GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, chunk.getModel().getVertexCount());

            //GL30.glDrawElements(GL30.GL_TRIANGLES, chunk.getModel().getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
            GL30.glBindVertexArray(0);
            GL30.glDisableVertexAttribArray(0);
            GL30.glDisableVertexAttribArray(1);
            GL30.glDisableVertexAttribArray(2);
        }

        Texture.unbind();

        shader.disable();
    }
}
