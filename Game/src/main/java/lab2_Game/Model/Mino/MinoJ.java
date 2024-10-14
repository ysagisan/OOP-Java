package lab2_Game.Model.Mino;

import java.awt.*;

public class MinoJ extends Mino {


    public MinoJ() {
        create(Color.GREEN);
    }

    public void setXY(int x, int y) {
        //    o         b[1]
        //    o         b[0]
        //  o o     b[3]b[2]
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = blocks[0].x + Block.SIZE;
        blocks[1].y = blocks[0].y;
        blocks[2].x = blocks[0].x + Block.SIZE + Block.SIZE;
        blocks[2].y = blocks[0].y;
        blocks[3].x = blocks[0].x + Block.SIZE + Block.SIZE;
        blocks[3].y = blocks[0].y - Block.SIZE;
    }

    public void moveDown(int x, int y) {
        for (int i = 0; i < 4; i++) {
            blocks[i].x = blocks[i].x + Block.SIZE;
        }
    }

    public void turnLeft() {
        int centerX = blocks[1].x;
        int centerY = blocks[1].y;

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
        int centerX = blocks[1].x;
        int centerY = blocks[1].y;

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