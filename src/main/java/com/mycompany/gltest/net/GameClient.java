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

public class GameClient extends Thread{
    private InetAddress ipAddress;
    private DatagramSocket socket;
    private Game game;
    private String string = "127.0.0.1";
    
    public GameClient(Game game, String ipAddress){
        this.game = game;
       
        try {
             this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run(){
        while(!interrupted()){
            byte[] data =  new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Server > "+ new String(packet.getData()));
        }   
    }
    public void cancel(){
        interrupt();
    }
    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length,ipAddress,3333);
        try {
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
