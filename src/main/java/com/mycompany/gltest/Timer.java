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
public class Timer {
    private long lastTime, offset;
   
    public Timer(){
        lastTime = System.nanoTime();
        
    }
    public long getTimeNano (){
        return System.nanoTime() - lastTime;
    }
    public long getTimeMilli(){
       
        return getTimeNano()/1000000;
    }
    public void reset(){
        //System.out.println("Frames: "+frames);
        
        lastTime = System.nanoTime();
    }
}
