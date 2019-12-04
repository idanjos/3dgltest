/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest.net;

import com.mycompany.gltest.Game;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laptop
 */
public class GameServer extends Thread{
    
 private InetAddress ipAddress;
    private DatagramSocket socket;
    private Game game;
    private String string = "127.0.0.1";
    
    public GameServer(Game game){
        this.game = game;
       
        try {
             this.socket = new DatagramSocket(3333);
        }  
         catch (SocketException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public void run(){
        while(true){
            byte[] data =  new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            String msg = new String(packet.getData());
             System.out.println("Client > "+ new String(packet.getData()));
               
            if(msg.trim().equalsIgnoreCase("ping")){
                sendData("pong".getBytes(),packet.getAddress(),packet.getPort());
            }
        }   
    }
    
    public void sendData(byte[] data, InetAddress ip, int port){
        DatagramPacket packet = new DatagramPacket(data, data.length,ip,port);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

