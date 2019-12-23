/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;



public class Timer {

    private static long variableYieldTime, lastFrame;
    private long lastTime;

    public Timer(){
        lastTime = getTimeNano();
    }
    public static long getTimeNano() {
        return System.nanoTime() / 1000000;
    }

    public static long getTime() {

        return getTimeNano()*1000 / 60;
    }

    public long getTimeMilli(){
        return getTimeNano() - lastTime; 
    }
    
    public void reset(){
        lastTime = getTimeNano();
    }
    
    public static float getDelta() {
        long currentTime = getTime();
        float delta =  (float)(currentTime -  lastFrame)/100;
        System.out.println(delta);
        lastFrame = getTime();
        return delta;
    }

}
