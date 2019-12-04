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
public class Block {

    public static Texture atlas = Texture.loadTexture("assets/atlas.png");
    private static byte lastUsedId = -128;
    private byte id;
    private int texture;

    public Block() {
        this.id = lastUsedId;

        lastUsedId++;
    }

    public byte getId() {
        return id;
    }

    public int getTexture(int f) {
        return 0;
    }

    public void render(ModelCreator mc, int x, int y, int z, Chunk chunk, World world) {
        //System.out.println("Rendering block");
        float u, v;
        int texture;

        //mc.color(y / 10.0f, 0, 0);
       
        //FRONT
        int wx = x + chunk.x * 16, wz = z + chunk.z * 16;
		
		//FRONT
		if(world.getBlock(wx, y, wz + 1) == Blocks.air.getId()) {	
			texture = getTexture(Utils.FRONT);
			
			v = texture / 16;
			u = texture - v * 16;
			
			mc.uv((u + 1) / 16.0f, (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
			mc.uv((u + 1) / 16.0f, (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f, (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
			
			mc.uv((u + 1) / 16.0f, (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f, (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f, (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
		}
		
		//BACK
		if(world.getBlock(wx, y, wz - 1) == Blocks.air.getId()) {	
			
			texture = getTexture(Utils.BACK);
			
			v = texture / 16;
			u = texture - v * 16;
			
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
			
			mc.uv((u + 0) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z - 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
		}
		
		//TOP
		if(world.getBlock(wx, y + 1, wz) == Blocks.air.getId()) {	
			
			texture = getTexture(Utils.TOP);
			
			v = texture / 16;
			u = texture - v * 16;
			
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
			
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
		}
		
		//BOTTOM
		if(world.getBlock(wx, y - 1, wz) == Blocks.air.getId()) {	
			
			texture = getTexture(Utils.BOTTOM);
			
			v = texture / 16;
			u = texture - v * 16;
			
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
			
			mc.uv((u + 0) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
		}
		
		//RIGHT
		if(world.getBlock(wx - 1, y, wz) == Blocks.air.getId()) {	
			
			texture = getTexture(Utils.RIGHT);
			
			v = texture / 16;
			u = texture - v * 16;
			
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
			
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z + 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x - 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x - 0.5f, y - 0.5f, z - 0.5f);
		}
		
		//LEFT
		if(world.getBlock(wx + 1, y, wz) == Blocks.air.getId()) {	
			
			texture = getTexture(Utils.LEFT);
			
			v = texture / 16;
			u = texture - v * 16;
			
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z + 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
			
			mc.uv((u + 0) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z - 0.5f);
			mc.uv((u + 0) / 16.0f,  (v + 0) / 16.0f);
			mc.vertex(x + 0.5f, y + 0.5f, z - 0.5f);
			mc.uv((u + 1) / 16.0f,  (v + 1) / 16.0f);
			mc.vertex(x + 0.5f, y - 0.5f, z + 0.5f);
		}
    }
}
