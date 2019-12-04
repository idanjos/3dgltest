/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.mycompany.gltest.Blocks;
import com.mycompany.gltest.Entity;
import com.mycompany.gltest.Game;
import com.mycompany.gltest.Input;
import com.mycompany.gltest.Physics;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author mint
 */
public class Player extends Entity {

    private Physics physics;
    private float vision = 0.95f; // 0-1
    private float speed = 0.01f;
    private float movementSpeed = speed * 0.5f;
    private float jumpSpeed = speed * 0.7f;
    private float jumpHeight = 1.8f;
    private float rotateSpeed = 0.001f;
    private boolean creativeMode = true;
    private float gravity = speed * 1f;
    private boolean isGrounded = false;
    private boolean isJumping = false;
    private float jumpCount = 0.0f;
    private float cx, cy;

    public Player(float x, float y, float z) {
        super(x, y, z);
        physics = new Physics();
        cx = Game.instance.getInput().getCX();
        cy = Game.instance.getInput().getCY();
    }

    public void onUpdate() {
        float x = 0, y = 0, z = 0;
        Input input = Game.instance.getInput();
        float fpsSpeed = (1000/Game.instance.fps) ;
        movementSpeed = movementSpeed;
        jumpSpeed = jumpSpeed;
        if (input.getKey(GLFW.GLFW_KEY_W)) {
            z -= Math.cos(ry) * movementSpeed;//cos 1
            x += Math.sin(ry) * movementSpeed;//sin
        }
        if (input.getKey(GLFW.GLFW_KEY_A)) {
            z -= Math.sin(ry) * movementSpeed;//cos 1
            x -= Math.cos(ry) * movementSpeed;//sin
        }
        if (input.getKey(GLFW.GLFW_KEY_S)) {
            z += Math.cos(ry) * movementSpeed; //cos -1
            x -= Math.sin(ry) * movementSpeed;//sin
        }
        if (input.getKey(GLFW.GLFW_KEY_D)) {
            z += Math.sin(ry) * movementSpeed; //cos -1
            x += Math.cos(ry) * movementSpeed;//sin
        }
        if (input.getKey(GLFW.GLFW_KEY_SPACE)) {
            //if(isGrounded){
            //
            if (!creativeMode) {
                if (!isJumping && isGrounded) {
                    jumpCount = 0;

                    isJumping = true;
                } else if (jumpCount < jumpHeight) {
                    y += jumpSpeed;
                    jumpCount += jumpSpeed;
                }
            } else {
                y += speed;
            }

        } else {
            if (!creativeMode) {
                if (isJumping && jumpCount < jumpHeight) {
                    y += jumpSpeed;
                    jumpCount += jumpSpeed;
                }

                if (jumpCount != 0) {
                    //System.out.println(jumpCount);
                }
                if (isGrounded) {
                    isJumping = false;
                    jumpCount = 0;
                }
            }
        }
        if (input.getKey(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            if (creativeMode) {
                y -= speed;
            }

        }

        if (input.getKey(GLFW.GLFW_KEY_F10) && creativeMode) {

            creativeMode = !creativeMode;
            System.out.println("Toggled" + creativeMode);
        }
        if (input.getKey(GLFW.GLFW_KEY_F9) && !creativeMode) {

            creativeMode = !creativeMode;
            System.out.println("Toggled" + creativeMode);
        }
        if (!creativeMode) {

            if (Game.instance.getWorld().getBlock((int) Math.floor(this.x), (int) Math.floor(this.y - 2.0f), (int) Math.floor(this.z)) == Blocks.air.getId()) {
                if (!isJumping || jumpCount >= jumpHeight) {
                    y -= gravity;
                }

                isGrounded = false;
            } else if (!isGrounded) {
                isGrounded = true;
            }

            if (Game.instance.getWorld().getBlock((int) Math.floor(this.x + 0.1f), (int) Math.floor(this.y - 1.0f), (int) Math.floor(this.z)) != Blocks.air.getId() && x > 0) {
                x = 0;
            }

            if (Game.instance.getWorld().getBlock((int) Math.floor(this.x - 0.1f), (int) Math.floor(this.y - 1.0f), (int) Math.floor(this.z)) != Blocks.air.getId() && x < 0) {
                x = 0;
            }

            if (Game.instance.getWorld().getBlock((int) Math.floor(this.x), (int) Math.floor(this.y - 1.0f), (int) Math.floor(this.z + 0.5f)) != Blocks.air.getId() && z > 0) {
                z = 0;
            }

            if (Game.instance.getWorld().getBlock((int) Math.floor(this.x), (int) Math.floor(this.y - 1.0f), (int) Math.floor(this.z - 0.5f)) != Blocks.air.getId() && z < 0) {
                z = 0;
            }

        }
        ry -= (cx - input.getCX()) * rotateSpeed;
        if (Math.sin(rx - (cy - input.getCY()) * rotateSpeed) <= vision && Math.sin(rx - (cy - input.getCY()) * rotateSpeed) >= -vision) {
            rx -= (cy - input.getCY()) * rotateSpeed;
        }

        cx = input.getCX();
        cy = input.getCY();
        this.x += x * fpsSpeed;
        this.y += y;
        this.z += z * fpsSpeed;

        //System.out.println(this);
        //System.out.println("Rotx:" +rx*54.5f+"-Roty:"+ry*50);
    }

    private void printpos() {
        System.out.println("X:" + x);
        System.out.println("Z:" + z);
    }

}
