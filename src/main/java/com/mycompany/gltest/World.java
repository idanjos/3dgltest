/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import entities.Player;
import java.util.Random;

/**
 *
 * @author mint
 */
public class World {

    private final int MAX_LOADED_CHUNKS_WIDTH = 16; // 16x16 each
    public static int SPAWN = 8 * 8 / 2;
    public static int Y = 30;
    public Chunk[] chunks;
    public Player player;
    private WorldGenerator generator;

    public World() {

        generator = new WorldGenerator(System.currentTimeMillis(), false);
        chunks = new Chunk[MAX_LOADED_CHUNKS_WIDTH * MAX_LOADED_CHUNKS_WIDTH];
        for (int x = 0; x < MAX_LOADED_CHUNKS_WIDTH; x++) {
            for (int z = 0; z < MAX_LOADED_CHUNKS_WIDTH; z++) {
                chunks[x + z * MAX_LOADED_CHUNKS_WIDTH] = new Chunk(x, z, this);
                generator.generateChunk(chunks[x + z * MAX_LOADED_CHUNKS_WIDTH]);
                chunks[x + z * MAX_LOADED_CHUNKS_WIDTH].updateModel(ModelCreator.instance);

            }
        }

    }

    public void SpawnPlayer() {
        player = randomSpawn();
    }

    public void tick() {

    }

    public Chunk getChunk(int i) {
        return chunks[i];
    }

    public Chunk getChunk(int x, int z) {
        if (x >= 0 && x < MAX_LOADED_CHUNKS_WIDTH && z >= 0 && z < MAX_LOADED_CHUNKS_WIDTH) {
            return chunks[x + z * MAX_LOADED_CHUNKS_WIDTH];
        }

        return null;
    }

    public byte getBlock(int x, int y, int z) {
        Chunk chunk = getChunk(x / 16, z / 16);
        if (chunk == null) {

            return Blocks.air.getId();

        }
        return chunk.getBlock(x - chunk.x * 16, y, z - chunk.z * 16);

    }

    private Player randomSpawn() {
        Random r = new Random();
        int x = (int) (r.nextFloat() * MAX_LOADED_CHUNKS_WIDTH * 16);
        int z = (int) (r.nextFloat() * MAX_LOADED_CHUNKS_WIDTH * 16);
        System.out.println("x:" + x / 16 + "z:" + z / 16);
        int y = 0;
     
        for (int i = 0; i < 256; i++) {
            if (getBlock(x, i, z) == Blocks.dirt.getId()) {
                System.out.println(i);
                y = i + 3; //Body size
                break;
            }
        }

        return new Player(x, y, z);
    }
}
