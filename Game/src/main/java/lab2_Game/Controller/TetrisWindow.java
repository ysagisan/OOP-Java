package lab2_Game.Controller;

import lab2_Game.View.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TetrisWindow extends JFrame {
    public String player;

    public TetrisWindow() {
        createMenu();
        setSize(400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenu() {
        JPanel menuPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("/Users/admin/IdeaProjects/Game/src/main/resources/IMG_6384 4.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        ImageIcon icon = new ImageIcon("/Users/admin/IdeaProjects/Game/src/main/resources/Ayaal2.png");
        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(e -> {

            String playerName = JOptionPane.showInputDialog(this,"Enter your name:");
            if (playerName != null && !playerName.isEmpty()) {
                player = playerName;
                startGame(playerName);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid name.", "Login Failed", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        });

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setPreferredSize(new Dimension(200, 50));
        highScoresButton.addActionListener(e -> showHighScores());

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(startButton);
        buttonsPanel.add(highScoresButton);

        menuPanel.add(buttonsPanel, BorderLayout.CENTER);

        getContentPane().add(menuPanel, BorderLayout.CENTER);
    }
    private void startGame(String playerName) {
        // Здесь вызывайте код, который запускает вашу игру Tetris
        // Например, создание экземпляра GameField и добавление его к фрейму
        JFrame frame = new JFrame("Tetris");
        JLabel scoreLabel = new JLabel("Score: 0"); // Начальное значение счета
        GameField gameField = new GameField(20, 10, scoreLabel, player); // Создание поля размером 20x10 клеток

        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(gameField, BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void showHighScores() {
        JFrame highScoresFrame = new JFrame("High Scores");
        // Создаем таблицу с данными
        List<String[]> scores = readScoresFromFile("/Users/admin/IdeaProjects/Game/src/main/resources/high_scores.txt");
        String[] columnNames = {"ИГРОК", "СЧЁТ"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (String[] score : scores) {
            tableModel.addRow(score);
        }
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        highScoresFrame.add(scrollPane, BorderLayout.CENTER);

        highScoresFrame.setSize(400, 800);
        highScoresFrame.setLocationRelativeTo(null);
        highScoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highScoresFrame.setVisible(true);
    }

    private List<String[]> readScoresFromFile(String filePath) {
        List<String[]> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(":");
                if (data.length == 2) {
                    scores.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
