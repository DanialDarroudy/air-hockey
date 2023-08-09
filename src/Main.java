import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Main {
    static double time = 1;
    static double timeInit = 1;
    static boolean edame = true;
    static String[][] row = new String[1000][6];
    static String[] column = new String[6];
    static Random random = new Random();
    //----------------------------frames---------------------------//
    final static JFrame frameMenu = new JFrame("Menu");
    final static JFrame frameMode = new JFrame("Select Mode");
    final static JFrame frameUser = new JFrame("User");
    final static JFrame frameGame = new JFrame("Game");
    final static JFrame frameLog = new JFrame("Recent Games");
    //----------------------------frames---------------------------//
    //----------------------------buttons---------------------------//
    final static JButton newGame = new JButton("New Game");
    final static JButton recentGames = new JButton("Recent Games");
    final static JButton exit = new JButton("Exit");
    final static JButton next = new JButton("Next");
    final static JButton enterToGame = new JButton("Entre To Game");
    //----------------------------buttons---------------------------//
    //----------------------------Others-----------------------------//
    final static JTextField player1 = new JTextField("Clear this and enter name of player1");
    final static JTextField player2 = new JTextField("Clear this and enter name of player2");
    final static JCheckBox timeLimited = new JCheckBox("Time Limited", false);
    final static JCheckBox goalLimited = new JCheckBox("Goal Limited", false);
    final static JCheckBox margin = new JCheckBox("2Margin", false);
    final static JTextField numberOfMinutes = new JTextField("Clear this and enter number of minutes");
    final static JTextField numberOfGoals = new JTextField("Clear this and enter number of goals");
    final static String[] colors = {"Blue", "Red"};
    final static JComboBox<String> color1 = new JComboBox<>(colors);
    final static JComboBox<String> color2 = new JComboBox<>(colors);
    final static JLabel error = new JLabel("Choose one or both");

    //----------------------------Others-----------------------------//
    //----------------------------Methods----------------------------//
    public static void main(String[] args) {
        menu();
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void menu() {
        time = 0;
        frameMode.setVisible(false);
        frameLog.setVisible(false);
        frameGame.setVisible(false);
        frameUser.setVisible(false);
        frameMenu.setSize(800, 1000);
        frameMenu.setLayout(null);
        newGame.setBounds(325, 350, 150, 50);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectMode();
            }
        });
        frameMenu.add(newGame);
        recentGames.setBounds(325, 450, 150, 50);
        recentGames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log();
            }
        });
        frameMenu.add(recentGames);
        exit.setBounds(325, 550, 150, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frameMenu.add(exit);
        frameMenu.setVisible(true);
    }

    public static void selectMode() {
        frameMenu.setVisible(false);
        frameLog.setVisible(false);
        frameGame.setVisible(false);
        frameUser.setVisible(false);
        frameMode.setSize(800, 1000);
        frameMode.setLayout(null);
        timeLimited.setBounds(325, 350, 100, 50);
        frameMode.add(numberOfMinutes);
        timeLimited.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    numberOfMinutes.setVisible(true);
                    numberOfMinutes.setBounds(325, 300, 300, 50);
                } else {
                    numberOfMinutes.setVisible(false);
                }
            }
        });
        frameMode.add(timeLimited);
        goalLimited.setBounds(325, 450, 100, 50);
        frameMode.add(numberOfGoals);
        frameMode.add(margin);
        goalLimited.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    numberOfGoals.setVisible(true);
                    numberOfGoals.setBounds(325, 500, 300, 50);
                    margin.setBounds(325, 800, 100, 50);
                    margin.setVisible(true);
                } else {
                    numberOfGoals.setVisible(false);
                    margin.setVisible(false);
                }
            }
        });
        frameMode.add(goalLimited);
        next.setBounds(325, 550, 100, 50);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputUsers();
            }
        });
        frameMode.add(next);
        error.setBounds(325, 150, 200, 50);
        frameMode.add(error);
        frameMode.setVisible(true);
    }

    public static void inputUsers() {
        frameMenu.setVisible(false);
        frameLog.setVisible(false);
        frameMode.setVisible(false);
        frameGame.setVisible(false);
        frameUser.setSize(800, 1000);
        frameUser.setLayout(null);
        player1.setBounds(325, 250, 300, 50);
        frameUser.add(player1);
        color1.setBounds(325, 350, 100, 50);
        frameUser.add(color1);
        player2.setBounds(325, 450, 300, 50);
        frameUser.add(player2);
        color2.setBounds(325, 550, 100, 50);
        frameUser.add(color2);
        enterToGame.setBounds(325, 650, 150, 50);
        enterToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterGame();
            }
        });
        frameUser.add(enterToGame);
        frameUser.setVisible(true);
        if (!goalLimited.isSelected() && !timeLimited.isSelected()) {
            selectMode();
        }
    }

    public static void enterGame() {
        if (timeLimited.isSelected() && goalLimited.isSelected()) {
            frameMenu.setVisible(false);
            frameLog.setVisible(false);
            frameMode.setVisible(false);
            frameUser.setVisible(false);
            frameGame.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
            frameGame.setLocationRelativeTo(null);
            frameGame.setUndecorated(true);
            GamePanel game = new GamePanel();
            Timer tm = new Timer(
                    5, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (edame) {
                        time += 5;
                        game.move();
                        game.repaint();
                    }
                    if (time >= Integer.parseInt(numberOfMinutes.getText()) * 60 * 1000 ||
                            (Math.max(game.puck.player1_score, game.puck.player2_score) >= Integer.parseInt(numberOfGoals.getText()))) {
                        if (margin.isSelected()) {
                            if (time >= Integer.parseInt(numberOfMinutes.getText()) * 60 * 1000 || Math.max(game.puck.player1_score, game.puck.player2_score) - Math.min(game.puck.player1_score, game.puck.player2_score) >= 2) {
                                File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "time and goal" + " / " + "yes");
                                System.exit(0);
                            }
                        }
                        else {
                            File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "time and goal" + " / " + "yes");
                            System.exit(0);
                            }
                    }
                }
            }
            );
            frameGame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    edame = false;
                    int ans = JOptionPane.showConfirmDialog(frameGame, "Do you want to quit the game?", "Stop", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "goal and time" + " / " + "no");
                        menu();
                    }
                    else if (ans == JOptionPane.NO_OPTION){
                        edame = true;
                    }
                }
            });
            game.setVisible(true);
            frameGame.add(game);
            frameGame.setVisible(true);
            tm.start();
        }
        else if (timeLimited.isSelected() && !goalLimited.isSelected()) {
            frameMenu.setVisible(false);
            frameLog.setVisible(false);
            frameMode.setVisible(false);
            frameUser.setVisible(false);
            frameGame.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
            frameGame.setLocationRelativeTo(null);
            frameGame.setUndecorated(true);
            GamePanel game = new GamePanel();
            Timer tm = new Timer(
                    5, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (edame) {
                        time += 5;
                        game.move();
                        game.repaint();
                    }
                    if (time >= Integer.parseInt(numberOfMinutes.getText()) * 60 * 1000) {
                        File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "time" + " / " + "yes");
                        System.exit(0);
                    }
                }
            }
            );
            frameGame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    edame = false;
                    int ans = JOptionPane.showConfirmDialog(frameGame, "Do you want to quit the game?", "Stop", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "goal" + " / " + "no");
                        menu();
                    }
                    else if (ans == JOptionPane.NO_OPTION){
                        edame = true;
                    }
                }
            });
            game.setVisible(true);
            frameGame.add(game);
            frameGame.setVisible(true);
            tm.start();
        }
        else if (!timeLimited.isSelected() && goalLimited.isSelected()) {
            frameMenu.setVisible(false);
            frameLog.setVisible(false);
            frameMode.setVisible(false);
            frameUser.setVisible(false);
            frameGame.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
            GamePanel game = new GamePanel();
            Timer timerAsli = new Timer(5, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (edame) {
                        time += 5.0;
                        game.move();
                        game.repaint();
                    }
                    if (Math.max(game.puck.player1_score, game.puck.player2_score) >= Integer.parseInt(numberOfGoals.getText())) {
                        if (margin.isSelected()) {
                            if (Math.max(game.puck.player1_score, game.puck.player2_score) - Math.min(game.puck.player1_score, game.puck.player2_score) >= 2) {
                                File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "goal" + " / " + "yes");
                                System.exit(0);
                            }
                        } else {
                            File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "goal" + " / " + "yes");
                            System.exit(0);
                        }
                    }
                }
            });
            frameGame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    edame = false;
                    int ans = JOptionPane.showConfirmDialog(frameGame, "Do you want to quit the game?", "Stop", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        File.log(player1.getText() + " / " + player2.getText() + " / " + game.puck.player1_score + " / " + game.puck.player2_score + " / " + "goal" + " / " + "no");
                        menu();
                    }
                    else if (ans == JOptionPane.NO_OPTION){
                        edame = true;
                    }
                }
            });
            game.setVisible(true);
            frameGame.add(game);
            frameGame.setVisible(true);
            timerAsli.start();
        }
    }
    public static void log() {
        //frameMenu.setVisible(false);
        //frameMode.setVisible(false);
        //frameGame.setVisible(false);
        frameUser.setVisible(false);
        JPanel panel = new JPanel();
        //-------------------------------------------------------
        column = new String[]{"Player1", "Player2", "Goal1", "Goal2", "Mode", "Finish?"};
        File.getLog();
        JTable table = new JTable(row,column);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.yellow);
        JScrollPane pane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.add(pane);
        frameLog.add(panel);
        frameLog.setSize(800,1000);
        frameLog.setUndecorated(true);
        frameLog.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frameLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLog.setVisible(true);
        //frameLog.setVisible(true);
    }
}
