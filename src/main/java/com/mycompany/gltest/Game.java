/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gltest;

import com.mycompany.gltest.net.GameClient;
import com.mycompany.gltest.net.GameServer;
import com.mycompany.gltest.shaders.ChunkShader;

import entities.Player;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author mint
 */
public class Game extends Component implements Runnable {

    private Thread gameThread;
    private Window window;
    public static Game instance;
    private Input input;
    public float fps = 144f;
    public float offsetFrames = 0f;
    private GameClient gameclient;
    private GameServer gameserver;

    private World world;

    public Game() {

    }

    public synchronized void start() {
        gameThread = new Thread(this);
        gameclient = new GameClient(this, "localhost");

        if (JOptionPane.showConfirmDialog(this, "Start a new Server?") == 0) {
            gameserver = new GameServer(this);
            gameserver.start();
        }
        gameThread.start();
        gameclient.start();
        this.instance = this;
    }

    public static void main(String[] args) {

        new Game().start();

    }

    public Input getInput() {
        return input;
    }

    public World getWorld() {
        return world;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public void run() {
        System.out.println("Hello");
        window = new Window(1024, 720, "Minecraft Clone");
        // ChunkShader chunk = new ChunkShader();
        input = new Input(window);
        world = new World();
        world.SpawnPlayer();
        ChunkRenderer cr = new ChunkRenderer();
        Timer updateTime = new Timer();
        gameclient.sendData("ping".getBytes());
        while (!window.isClosing() && !input.getKey(GLFW.GLFW_KEY_ESCAPE)) {
            window.clear(0.0f, 0.2f, 0.4f);

            world.player.onUpdate();

            cr.render(world.chunks, world.player);
            window.update();
            if (updateTime.getTimeMilli() >= 1000) {
                updateTime.reset();
                window.printFrames();
            } else {
                //System.out.println((long)1/fps);
                try {

                    Thread.sleep((long) (1000 / (fps + offsetFrames)));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (gameserver != null) {
            gameserver.interrupt();
        }
        gameclient.cancel();
        window.close();
    }

}
