/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

/**
 *
 * @author mint
 */
import org.lwjgl.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private long window;
    private int height;
    private int width;
    private String title;
    private int frames = 0;
    private boolean antialiasing = false;

    public Window(int width, int height, String title) {
        this.height = height;
        this.width = width;
        this.title = title;
        init();
    }

    public void setTitle(String title){
        GLFW.glfwSetWindowTitle(window, title);
    }
    public void update() {
        drawCrosshair();
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(window);
        frames++;

    }

    public void printFrames() {
        //System.out.println("Frames:"+(frames-1)); //-1 due to calling window.update before checking
        //System.out.println("Frames:" + frames);
        setTitle("Frames: "+frames);
        if (frames < Game.instance.fps) {
            Game.instance.offsetFrames += 10;
        } else if (frames > Game.instance.fps) {
            Game.instance.offsetFrames -= 10;
        }
        frames = 0;
    }

    public void close() {
        GLFW.glfwTerminate();
    }

    public void clear(float r, float g, float b) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        GL11.glClearColor(r, g, b, 1);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getId() {
        return window;
    }

    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GLFW.glfwSwapInterval(0);
        if (antialiasing) {
            glfwWindowHint(GLFW_SAMPLES, 4);
        }
    }

    public boolean isClosing() {
        return GLFW.glfwWindowShouldClose(window);
    }

    void drawCrosshair() {
        glEnable(GL_BLEND);
        glDisable(GL_DEPTH_TEST);
        glPushMatrix();
        glLoadIdentity();
        glBegin(GL_LINES);
        glColor4f(1f, 1f, 1f, 0);
        glVertex3f(-0.01f, 0.0f, -1.0f);
        glVertex3f(+0.01f, 0.0f, -1.0f);
        glVertex3f(0.0f, -0.01f, -1.0f);
        glVertex3f(0.0f, +0.01f, -1.0f);
        glEnd();
        glPopMatrix();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_STENCIL_TEST);
        glDisable(GL_BLEND);
    }
}
