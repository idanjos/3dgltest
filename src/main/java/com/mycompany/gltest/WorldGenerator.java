/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import org.joml.SimplexNoise;

/**
 *
 * @author mint
 */
public class WorldGenerator {

    private long seed;
    private boolean isflatWorld;

    public WorldGenerator(long seed, boolean isflatWorld) {
        this.seed = seed;
        this.isflatWorld = isflatWorld;
    }

    public void generateChunk(Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                //System.out.println("Setting stone");
                int y = (int) (SimplexNoise.noise((x + chunk.x * 16) / 100.0f, (z + chunk.z * 16) / 100.0f) * 16) + 60;
                chunk.setBlock(x, y, z, Blocks.dirt);
                for (int i = y - 1; i >= 0; i--) {
                    chunk.setBlock(x, i, z, Blocks.stone);
                }
            }
        }
    }

}
