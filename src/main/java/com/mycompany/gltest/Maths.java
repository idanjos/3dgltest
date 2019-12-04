/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import org.joml.Matrix4f;

/**
 *
 * @author mint
 */
public class Maths {
    public static void createViewMatrix(float x, float y, float z,float rx, float ry, float rz, Matrix4f dest){
        dest.identity();
        dest.rotate(rx, 1, 0, 0);
        dest.rotate(ry, 0, 1, 0);
        dest.rotate(rz, 0, 0, 1);
        dest.translate(-x,-y,-z);
        
    }
}
