package lab2_Game.Model.Mino;

import java.awt.*;

public class MinoSquare extends Mino {


    public MinoSquare() {
        create(Color.cyan);
    }

    public void setXY(int x, int y) {
        //  o o            b[1] b[0]
        //  o o            b[2] b[3]
        blocks[0].x = x;
        blocks[0].y = y;
        blocks[1].x = blocks[0].x ;
        blocks[1].y = blocks[0].y - Block.SIZE;
        blocks[2].x = blocks[0].x + Block.SIZE;
        blocks[2].y = blocks[0].y - Block.SIZE;
        blocks[3].x = blocks[0].x + Block.SIZE;
        blocks[3].y = blocks[0].y;
    }

    public void moveDown(int x, int y) {
        for (int i = 0; i < 4; i++) {
            blocks[i].x = blocks[i].x + Block.SIZE;
        }
    }
}
