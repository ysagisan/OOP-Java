package lab2_Game.Model.Mino;

import lab2_Game.Controller.KeyHandler;
import java.awt.*;

public class Mino {

    public Block[] blocks = new Block[4];

    public void create(Color c) {
        blocks[0] = new Block(c);
        blocks[1] = new Block(c);
        blocks[2] = new Block(c);
        blocks[3] = new Block(c);
    }

    public void setXY(int x, int y) {}

    public void moveDown (int x, int y){}

    public void turnLeft () {}

    public void turnRight () {}

    public int checkBorder (int x, int y) {
        for (Block block : blocks) {
            if (block.y == 0) {
                return 1;
            }
            if (block.y == y - 1) {
                return 2;
            }
            if (block.x == x - 1) {
                return 3;
            }
        }
        return 0;
    }

    public boolean nextMino (int x) {
        for (Block block : blocks) {
            if (block.x == x) {
                return true;
            }
        }
        return false;
    }

    public void checkPressed(int x, int y) {

        if (KeyHandler.zPressed) {
            turnLeft();
            KeyHandler.zPressed = false;
        }

        if (KeyHandler.xPressed) {
            turnRight();
            KeyHandler.xPressed = false;
        }

        if (KeyHandler.downPressed) {
            if (checkBorder(x, y) != 3) {
                blocks[0].x += Block.SIZE;
                blocks[1].x += Block.SIZE;
                blocks[2].x += Block.SIZE;
                blocks[3].x += Block.SIZE;
            }

            KeyHandler.downPressed = false;
        }

        if (KeyHandler.leftPressed) {
            if (checkBorder(x, y) != 1) {
                blocks[0].y -= Block.SIZE;
                blocks[1].y -= Block.SIZE;
                blocks[2].y -= Block.SIZE;
                blocks[3].y -= Block.SIZE;
            }

            KeyHandler.leftPressed = false;
        }

        if (KeyHandler.rightPressed) {
            if (checkBorder(x, y) != 2) {
                blocks[0].y += Block.SIZE;
                blocks[1].y += Block.SIZE;
                blocks[2].y += Block.SIZE;
                blocks[3].y += Block.SIZE;
            }


            KeyHandler.rightPressed = false;
        }
    }

}
