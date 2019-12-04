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
public class Chunk {

    private Model model;

    private byte[] blocks;
    private boolean change;
    public int x, z;
    private World world;

    public Chunk(int x, int z, World world) {
        this.blocks = new byte[16 * 256 * 16];
        //5,10,2
        blocks[(5) + (10 * 16 * 16) + (2 * 16)] = 13;
        model = new Model(0, 0);
        for (int i = 0; i < 16 * 256 * 16; i++) {
            blocks[i] = Blocks.air.getId();
        }
        int a = 5 << 1;
        this.x = x;
        this.z = z;
        this.world = world;
    }

    public void updateModel(ModelCreator mc) {

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 256; y++) {
                for (int z = 0; z < 16; z++) {
                    Blocks.getBlockByID(getBlock(x, y, z)).render(mc, x, y, z, this, world);

                }
            }
        }
        model = mc.create();
    }

    public byte getBlock(int x, int y, int z) {
        //System.out.println(x + y *16*16 + z *16);
        if (x < 16 && x >= 0 && y < 256 && y >= 0 && z < 16 && z >= 0) {
            return blocks[x + y * 16 * 16 + z * 16];
        }
        return Blocks.air.getId();
    }

    public byte getBlock(int i) {
        return blocks[i];
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (x < 16 && x >= 0 && y < 256 && y >= 0 && z < 16 && z >= 0) {
            blocks[x + y * 16 * 16 + z * 16] = block.getId();
            change = true;
        }
    }

    public void setBlock(int i, Block block) {
        blocks[i] = block.getId();
        change = true;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public boolean isChanged() {
        return change;
    }

}
