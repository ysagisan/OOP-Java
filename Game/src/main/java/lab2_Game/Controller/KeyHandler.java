package lab2_Game.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public static boolean xPressed, zPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Z) {
            zPressed = true;
        }
        if (key == KeyEvent.VK_X) {
            xPressed = true;

        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
