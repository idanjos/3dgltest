/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import com.mycompany.gltest.blocks.Air;
import com.mycompany.gltest.blocks.Dirt;
import com.mycompany.gltest.blocks.Stone;

/**
 *
 * @author mint
 */
public class Blocks {

    private static Block[] blocks = new Block[256];
    public static final Air air = new Air();
    public static final Stone stone = new Stone();
    public static final Dirt dirt = new Dirt();

    static {
        registerBlock(stone);
        registerBlock(air);
        registerBlock(dirt);
    }

    public static void registerBlock(Block block) {
        //Byte -128 -> 127
        blocks[block.getId() + 128] = block;
    }

    public static Block getBlockByID(byte id) {

        return blocks[id+128];
    }
}
