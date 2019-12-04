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
public abstract class Entity {
    public float x, y, z;
    public float rx,ry,rz;

    public Entity(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
    }

    @Override
    public String toString() {
        return "Entity{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
    
}
