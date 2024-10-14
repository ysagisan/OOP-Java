package lab2_Game.View;

import lab2_Game.Controller.HighScoresManager;
import lab2_Game.Controller.KeyHandler;
import lab2_Game.Model.Mino.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameField extends JPanel {

    private int rows;
    private int cols;
    private Color[][] grid;

    private Mino currentMino;
    private Timer timer1, timer2;

    private int curX = 0;
    private int curY;

    private KeyHandler keyHandler = new KeyHandler();
    private int score;
    private JLabel scoreLabel;
    private String playerName;

    HighScoresManager highScoresManager = new HighScoresManager();

    public GameField(int rows, int cols, JLabel scoreLabel, String playerName) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Color[rows][cols];
        this.scoreLabel = scoreLabel;
        this.playerName = playerName;

        setPreferredSize(new Dimension(cols * 40, rows * 40));

        currentMino = randomMino();
        curY = cols / 2 - 1;
        currentMino.setXY(curX, curY);

        setFocusable(true);
        addKeyListener(keyHandler);

        timer1 = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curY = currentMino.blocks[0].y;
                moveDown();
                checkAndClearRows();
            }
        });
        timer1.start();

        timer2 = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curX = currentMino.blocks[0].x;
                curY = currentMino.blocks[0].y;
                movement();
            }
        });
        timer2.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / cols;
        int cellHeight = getHeight() / rows;

        for (int i = 0; i < 4; i++) {
            if (grid[currentMino.blocks[i].x][currentMino.blocks[i].y] == null) {
                grid[currentMino.blocks[i].x][currentMino.blocks[i].y] = currentMino.blocks[i].c;
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                if (grid[row][col] != null) {
                    g.setColor(grid[row][col]);
                    g.fillRect(col * cellWidth + 1, row * cellHeight + 1, cellWidth - 1, cellHeight - 1);
                }
            }
        }
    }

    public void setColor(int row, int col, Color color) {
        grid[row][col] = color;
        repaint();
    }

    private void moveDown() {
        for (int i = 0; i < 4; i++) {
            if (!currentMino.nextMino(rows - 1)) {
                setColor(currentMino.blocks[i].x, currentMino.blocks[i].y, null);
            }
        }

        if (!currentMino.nextMino(rows - 1) && !checkCollisionDown()) {
            currentMino.moveDown(curX, curY);
            curX = currentMino.blocks[0].x;
            curY = currentMino.blocks[0].y;
            repaint();
            curX++;

        } else {
            addCurrentMinoToGrid();
            if (isGameOver()) {
                gameOver();
            } else {
                currentMino = randomMino();
                curX = 0;
                curY = cols / 2 - 1;
                currentMino.setXY(curX, curY);
                repaint();
            }
        }
    }

    private void movement() {
        for (int i = 0; i < 4; i++) {
            if (!currentMino.nextMino(rows - 1)) {
                setColor(currentMino.blocks[i].x, currentMino.blocks[i].y, null);
            }
        }

        if (!checkCollisionDown()) {
            currentMino.checkPressed(rows, cols);
            curX = currentMino.blocks[0].x;
            curY = currentMino.blocks[0].y;
            repaint();
        }
    }

    public Mino randomMino() {
        Mino mino = null;
        int i = new Random().nextInt(7);
        switch (i) {
            case 0: mino = new MinoL(); break;
            case 1: mino = new MinoJ(); break;
            case 2: mino = new MinoS(); break;
            case 3: mino = new MinoZ(); break;
            case 4: mino = new MinoT(); break;
            case 5: mino = new MinoSquare(); break;
            case 6: mino = new MinoStick(); break;
        }
        return mino;
    }

    private boolean checkCollisionDown() {
        for (int i = 0; i < 4; i++) {
            int x = currentMino.blocks[i].x;
            int y = currentMino.blocks[i].y;
            if (x >= rows - 1 || grid[x + 1][y] != null) {
                return true;
            }
        }
        return false;
    }

    private void addCurrentMinoToGrid() {
        for (int i = 0; i < 4; i++) {
            int x = currentMino.blocks[i].x;
            int y = currentMino.blocks[i].y;
            grid[x][y] = currentMino.blocks[i].c;
        }
    }

    private void checkAndClearRows() {
        boolean rowFull;
        for (int row = rows - 1; row >= 0; row--) {
            rowFull = true;
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == null) {
                    rowFull = false;
                    break;
                }
            }
            if (rowFull) {
                clearRow(row);
                moveRowsDown(row);
                row++;
                score += 100;
                updateScore(score);
            }
        }
    }

    private void clearRow(int row) {
        for (int col = 0; col < cols; col++) {
            grid[row][col] = null;
        }
        repaint();
    }

    private void moveRowsDown(int emptyRow) {
        for (int row = emptyRow - 1; row >= 0; row--) {
            for (int col = 0; col < cols; col++) {
                grid[row + 1][col] = grid[row][col];
                grid[row][col] = null;
            }
        }
        repaint();
    }

    public void updateScore(int newScore) {
        score = newScore;
        scoreLabel.setText("Score: " + score);
    }

    private boolean isGameOver() {
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] != null) {
                return true;
            }
        }
        return false;
    }

    private void gameOver() {
        timer1.stop();
        timer2.stop();
        ImageIcon icon = new ImageIcon("/Users/admin/IdeaProjects/Game/src/main/resources/Ayaal.jpg");
        JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE, icon);
        highScoresManager.addScore(playerName, score);
    }
}
