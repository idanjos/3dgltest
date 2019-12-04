/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

/**
 *
 * @author mint
 */
public class Input {

    private boolean[] keys;
    private float cx, cy;

    public Input(Window window) {
        keys = new boolean[350];
        GLFW.glfwSetInputMode(Game.instance.getWindow().getId(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        GLFW.glfwSetKeyCallback(window.getId(), new GLFWKeyCallbackI() {
            @Override
            public void invoke(long window, int key, int arg2, int action, int i3) {
                //System.out.println("Hello");
                //action == GLFW.GLFW_PRESS GLFW.GLFW_RELEASE GLFW.GLFW_REPEAT
                if (key != GLFW.GLFW_KEY_UNKNOWN) {
                    keys[key] = action != GLFW.GLFW_RELEASE;
                }
            }
        });
        glfwSetCursorPosCallback(window.getId(), new GLFWCursorPosCallbackI() {
            @Override
            public void invoke(long window, double d, double d1) {
                cx = (float)d;
                cy = (float)d1;
               // System.out.println(d + "-" + d1);
            }
        });
    }
    public float getCX(){
        return cx;
    }
    public float getCY(){
        return cy;
    }
    public boolean getKey(int key) {
        return keys[key];
    }

}
