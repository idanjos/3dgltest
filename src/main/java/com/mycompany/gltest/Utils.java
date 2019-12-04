/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import java.nio.FloatBuffer;
import java.util.List;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

/**
 *
 * @author mint
 */
public class Utils {

    public static int FRONT = 0, BACK = 1, TOP = 2, BOTTOM = 3, RIGHT = 4, LEFT = 5;
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(4 * 4);

    public static float[] toArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static int[] toArrayInt(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static FloatBuffer toFloatBuffer(Matrix4f matrix) {
        //FloatBuffer buffer = BufferUtils.createFloatBuffer(4*4);
        matrix.get(matrixBuffer);
        return matrixBuffer;
    }

}
