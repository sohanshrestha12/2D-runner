import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Frame extends mainHero{
    private int xOffset = 0;
    private int oxOffset = 1100;
    public JPanel startScreen;
    mainHero character=new mainHero();
    public JFrame frame = new JFrame("Runner");
    private JButton startButton,exitButton;

    public Frame(){
        frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        StartScreen();
        frame.getContentPane().add(startScreen);
    }
    public void displayStartScreen() {
        //frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
        frame.setVisible(true);
    }
    public void StartScreen() {
        startScreen = new JPanel(new BorderLayout());
        startScreen.setBackground(Color.WHITE);

        //GameName
        JPanel gameIcon=new JPanel(){
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                // load the image file
//                E:\sohan\Java\JavaProjectM\2D-Runner\Game\Assets
                Image backgroundImage = new ImageIcon("E:\\JavaGame\\2D-Runner\\Game\\Assets\\ICON.png").getImage();
                g.drawImage(backgroundImage,0,0,Color.WHITE,null);
            }
        };
        gameIcon.setOpaque(false);
        gameIcon.setPreferredSize(new Dimension(300, 100));
        gameIcon.setBounds(300, 100, 300, 100);

        //BackGround
        JPanel gameWindow = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // load the image file
//                E:\sohan\Java\JavaProjectM\2D-Runner\Game\Assets
                Image backgroundImage = new ImageIcon("E:\\JavaGame\\2D-Runner\\Game\\Assets\\10.png").getImage();
                xOffset--;
                if (xOffset < -frame.getWidth()) {
                    xOffset = 0;
                }
                g.drawImage(backgroundImage, xOffset, 0, frame.getWidth(), frame.getHeight(), null);
                g.drawImage(backgroundImage, xOffset + frame.getWidth(), 0, frame.getWidth(), frame.getHeight(), null);
            }
        };
        gameWindow.setPreferredSize(new Dimension(1100, 700));
        gameWindow.setBounds(0, 0, 1100, 700);
        Timer timer1 = new Timer(100, e -> gameWindow.repaint());
        timer1.start();
        //Start Button
        startButton = new JButton("Start");
        startButton.setBounds(500,240,100,50);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render();
            }
        });

        //Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(500,240,100,50);
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.gridy=-1;
        gbc.gridx = 0;
        gbc.gridy = -1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;
        gameWindow.add(gameIcon, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gameWindow.add(startButton, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gameWindow.add(exitButton, gbc);
        frame.add(gameWindow);

    }

    public void render() {
        /*frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);*/
        frame.getContentPane().removeAll();

        JLayeredPane mainWindow = new JLayeredPane();
        mainWindow.setBounds(0,0,1100,700);

        // background
        JPanel gameWindow = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // load the image file
//                E:\sohan\Java\JavaProjectM\2D-Runner\Game\Assets
                Image backgroundImage = new ImageIcon("E:\\JavaGame\\2D-Runner\\Game\\Assets\\10.png").getImage();
                int height = frame.getHeight() - 250;
                xOffset--;
                if (xOffset < -frame.getWidth()) {
                    xOffset = 0;
                }
                g.drawImage(backgroundImage, xOffset, 0, frame.getWidth(), height, null);
                g.drawImage(backgroundImage, xOffset + frame.getWidth(), 0, frame.getWidth(), height, null);
            }
        };
        gameWindow.setPreferredSize(new Dimension(700, 445));

        gameWindow.setBounds(0, 0, 1100, 445);
        mainWindow.add(gameWindow, Integer.valueOf(0));

        // Road
        JPanel gameRoad = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // load the image file
                Image backgroundRoad = new ImageIcon("E:\\JavaGame\\2D-Runner\\Game\\Assets\\Road.png").getImage();
                int height = frame.getHeight() / 3;
//                System.out.println(gameWindow.getHeight());
                xOffset--;
                if (xOffset < -frame.getWidth()) {
                    xOffset = 0;
                }
                g.drawImage(backgroundRoad, xOffset, 0, frame.getWidth(), height, null);
                g.drawImage(backgroundRoad, xOffset + frame.getWidth(), 0, frame.getWidth(), height, null);

            }
        };
        gameRoad.setPreferredSize(new Dimension(700, 220));
        gameRoad.setBounds(0, 445, 1100, 220);
        mainWindow.add(gameRoad, Integer.valueOf(1));
        JPanel mainChar=character.setGameCharacter();
        mainWindow.add(mainChar, Integer.valueOf(2));

        //obstacle



        JPanel obstacle1 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image obstaclebackground = new ImageIcon("E:\\JavaGame\\2D-Runner\\Game\\Assets\\car1.png").getImage();

                int height = 100;
                oxOffset-=5;
//                System.out.println(oxOffset);
                if (oxOffset < -frame.getWidth()-100) {
                    oxOffset = frame.getWidth();
                }
                Rectangle obstacle1=new Rectangle(oxOffset, 0, 100, height);
                Rectangle obstacle2=new Rectangle(oxOffset + 800, 100, 100, height);
                Rectangle hero=character.mainherorec;
                g.drawImage(obstaclebackground, oxOffset, 0, 150, height, null);
                g.drawImage(obstaclebackground, oxOffset + 800, 100, 150, height, null);
                if(obstacle1.intersects(hero)||obstacle2.intersects(hero))
                {
                    System.out.println("Collision");
                    frame.getContentPane().removeAll();
                    StartScreen();
                }



            }
        };
        obstacle1.setOpaque(false);
        obstacle1.setBounds(0, 445, 1100, 220);
        mainWindow.add(obstacle1, Integer.valueOf(2));
        JPanel obstacle2 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image obstaclebackground = new ImageIcon("E:\\JavaGame\\2D-Runner\\Game\\Assets\\car2.png").getImage();
                int height = 100;
                oxOffset-=5;
//                System.out.println(oxOffset);
                if (oxOffset < -frame.getWidth()-100) {
                    oxOffset = frame.getWidth();
                }
                Rectangle obstacle1=new Rectangle(oxOffset+270, 100, 100, height);
                Rectangle obstacle2=new Rectangle(oxOffset + 550, 0, 100, height);
                Rectangle obstacle3=new Rectangle(oxOffset + 1020, 0, 100, height);
                Rectangle hero=character.mainherorec;
                g.drawImage(obstaclebackground, oxOffset+270, 100, 150, height, null);
                g.drawImage(obstaclebackground, oxOffset + 550, 0, 150, height, null);
                g.drawImage(obstaclebackground, oxOffset + 1020, 0, 150, height, null);
                if(obstacle1.intersects(hero)||obstacle2.intersects(hero)||obstacle3.intersects(hero))
                {
                    System.out.println("Collision");
                    frame.getContentPane().removeAll();
                    StartScreen();
                }

            }
        };
        obstacle2.setOpaque(false);
        obstacle2.setBounds(0, 445, 1100, 220);
        mainWindow.add(obstacle2, Integer.valueOf(2));

        frame.getContentPane().add(mainWindow);
        Timer timer1 = new Timer(100, e -> gameWindow.repaint());
        timer1.start();
        Timer timer2 = new Timer(100, e -> gameRoad.repaint());
        timer2.start();

        Timer timer3 = new Timer(150, e -> obstacle1.repaint());
        timer3.start();

        Timer timer4 = new Timer(150, e -> obstacle2.repaint());
        timer4.start();

        mainChar.setFocusable(true);
        mainChar.requestFocusInWindow();
        frame.setVisible(true);
    }

}
