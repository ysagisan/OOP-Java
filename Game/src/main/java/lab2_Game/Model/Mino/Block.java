package lab2_Game.Model.Mino;

import java.awt.*;

public class Block extends Rectangle {
    public int x, y;
    public static final int SIZE = 1;
    public Color c;

    public Block(Color c) {
        this.c = c;
    }

}
