package lab2_Game.Model.Mino;

import java.awt.*;

public class MinoT extends Mino {


    public MinoT() {
        create(Color.magenta);
    }

    public void setXY(int x, int y) {
        //   o o o          b[1] b[0] b[2]
        //     o                 b[3]
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = blocks[0].x ;
        blocks[1].y = blocks[0].y - Block.SIZE;
        blocks[2].x = blocks[0].x;
        blocks[2].y = blocks[0].y + Block.SIZE;
        blocks[3].x = blocks[0].x + Block.SIZE;
        blocks[3].y = blocks[0].y;
    }

    public void moveDown(int x, int y) {
        for (int i = 0; i < 4; i++) {
            blocks[i].x = blocks[i].x + Block.SIZE;
        }
    }

    public void turnLeft() {
        int centerX = blocks[0].x;
        int centerY = blocks[0].y;

        for (Block block : blocks) {
            int x = block.x;
            int y = block.y;

            int dx = x - centerX;
            int dy = y - centerY;

            int newX = centerX - dy;
            int newY = centerY + dx;

            block.x = newX;
            block.y = newY;
        }
    }

    public void turnRight() {
        int centerX = blocks[0].x;
        int centerY = blocks[0].y;

        for (Block block : blocks) {
            int x = block.x;
            int y = block.y;

            int dx = x - centerX;
            int dy = y - centerY;

            int newX = centerX + dy;
            int newY = centerY - dx;

            block.x = newX;
            block.y = newY;
        }
    }

}
