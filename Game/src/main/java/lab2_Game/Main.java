package lab2_Game;


import lab2_Game.Model.Mino.Block;
import lab2_Game.View.GameField;
import lab2_Game.Controller.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TetrisWindow::new);
    }
}
