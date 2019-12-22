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

    private static long variableYieldTime, lastTime;
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
    public static void sync(int fps) {
        if (fps <= 0) return;
         
        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000*1000));
        long overSleep = 0; // time the sync goes over by
         
        try {
            while (true) {
                long t = System.nanoTime() - lastTime;
                 
                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                }else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                }else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }finally{
        	lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);
           
            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200*1000, sleepTime);
            }
            else if (overSleep < variableYieldTime - 200*1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2*1000, 0);
            }
        }
    }
}
