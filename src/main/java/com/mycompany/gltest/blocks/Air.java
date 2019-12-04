/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest.blocks;

import com.mycompany.gltest.Block;
import com.mycompany.gltest.Chunk;
import com.mycompany.gltest.ModelCreator;
import com.mycompany.gltest.World;

/**
 *
 * @author mint
 */
public class Air extends Block {
    
    public Air() {
        super();
    }
    
    @Override
    public int getTexture(int f){
        
        return 14;
    }
    
    @Override
    public void render(ModelCreator mc, int x, int y, int z, Chunk chunk, World world) {
        
    }
    
}
