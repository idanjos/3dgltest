/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import de.matthiasmann.twl.utils.PNGDecoder;

/**
 *
 * @author mint
 */
public class Texture {
    private int id;
    public Texture(int id){
        this.id = id;
    }
    
    public void bind(){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
    }
    
    public static void unbind(){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
    
    public static Texture loadTexture(String dir){
        File file = new File(dir);
        try {
            PNGDecoder decoder = new PNGDecoder(new FileInputStream(file));
            ByteBuffer buffer = BufferUtils.createByteBuffer(decoder.getWidth()*decoder.getHeight()*4);
            decoder.decode(buffer, decoder.getWidth()*4, PNGDecoder.Format.RGBA);
            buffer.rewind();
            
            int id = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
            
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA , decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            return new Texture(id);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
