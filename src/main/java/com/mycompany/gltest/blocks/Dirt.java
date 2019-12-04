/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest.blocks;

import com.mycompany.gltest.Block;
import com.mycompany.gltest.Utils;

/**
 *
 * @author mint
 */
public class Dirt extends Block{
    
    public Dirt() {
        super();
    }
    @Override
    public int getTexture(int f){
        switch(f){ // public static int FRONT = 0, BACK = 1, TOP = 2, BOTTOM = 3, RIGHT = 4, LEFT = 5;
            case 0:
            case 1:
            case 4:
            case 5:
                return 3;
            case 2:
                return 0;
            case 3:
                return 2;
                
        }
        return 2;
    }
}
