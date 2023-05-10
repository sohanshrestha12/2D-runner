import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Frame extends mainHero{
    private int xOffset = 0;
    private int oxOffset = 1100;
    public JPanel startScreen;
    public int oy1 =(int) (Math.random() * 2) * 100;
    public int oy2 = (int) (Math.random() * 2) * 100;
    public Rectangle obstacle1Rec;
    public Rectangle obstacle2Rec;
    public Rectangle obstacle3Rec;
    public Rectangle obstacle4Rec;
    public Rectangle obstacle5Rec;
    public Timer timer1;
    JPanel GameOver;
    JLayeredPane mainWindow;
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
                Image backgroundImage = new ImageIcon("E:\\Game\\Assets\\ICON.png").getImage();
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
                Image backgroundImage = new ImageIcon("E:\\Game\\Assets\\10.png").getImage();
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
        Timer timer0 = new Timer(100, e -> gameWindow.repaint());
        timer0.start();
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

        mainWindow = new JLayeredPane();
        mainWindow.setBounds(0,0,1100,700);

        // background
        JPanel gameWindow = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // load the image file
//                E:\sohan\Java\JavaProjectM\2D-Runner\Game\Assets
                Image backgroundImage = new ImageIcon("E:\\Game\\Assets\\10.png").getImage();
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
                Image backgroundRoad = new ImageIcon("E:\\Game\\Assets\\Road.png").getImage();
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

                Image obstaclebackground = new ImageIcon("E:\\Game\\Assets\\car1.png").getImage();

                int height = 100;
                oxOffset -= 5;

                if (oxOffset < -frame.getWidth()-100) {
                    oxOffset = frame.getWidth();
                    oy1 = (int) (Math.random() * 2) * 100;
                }
//                System.out.println(oy1);
                obstacle2Rec = new Rectangle(oxOffset , oy1, 100, height);
                obstacle1Rec = new Rectangle(oxOffset + 800, oy1, 100, height);
//                g.setColor(Color.BLUE);
//                g.fillRect(obstacle1Rec.x, obstacle1Rec.y, obstacle1Rec.width, obstacle1Rec.height);
//                g.fillRect(obstacle2Rec.x, obstacle2Rec.y, obstacle2Rec.width, obstacle2Rec.height);

                g.drawImage(obstaclebackground, oxOffset, oy1, 150, height, null);
                g.drawImage(obstaclebackground, oxOffset+800, oy1, 150, height, null);
            }
        };
        obstacle1.setOpaque(false);
        obstacle1.setBounds(0, 445, 1100, 220);
        mainWindow.add(obstacle1, Integer.valueOf(2));


        JPanel obstacle2 = new JPanel(){


            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image obstaclebackground = new ImageIcon("E:\\sohan\\Java\\JavaProjectM\\2D-Runner\\Game\\Assets\\car2.png").getImage();
                int height = 100;
                oxOffset -= 5;

                if (oxOffset < -frame.getWidth()-100) {
                    oxOffset = frame.getWidth();
                    oy2 = (int) (Math.random() * 2) * 100;
                }
                obstacle3Rec = new Rectangle(oxOffset + 270, oy2, 100, height);
                obstacle4Rec = new Rectangle(oxOffset + 550, oy2+100, 100, height);
                obstacle5Rec = new Rectangle(oxOffset + 1040, oy2, 100, height);
                g.setColor(Color.BLUE);
                g.fillRect(obstacle3Rec.x, obstacle3Rec.y, obstacle3Rec.width, obstacle3Rec.height);
                g.fillRect(obstacle4Rec.x, obstacle4Rec.y, obstacle4Rec.width, obstacle4Rec.height);

                g.drawImage(obstaclebackground, oxOffset + 270, oy2, 150, height, null);
                g.drawImage(obstaclebackground, oxOffset + 550, oy2+100, 150, height, null);
                g.drawImage(obstaclebackground, oxOffset + 1040, oy2, 150, height, null);
            }
        };
        obstacle2.setOpaque(false);
        obstacle2.setBounds(0, 445, 1100, 220);
        mainWindow.add(obstacle2, Integer.valueOf(2));

        frame.getContentPane().add(mainWindow);
        timer1 = new Timer(100, e -> {
//            System.out.println("mh: " + character.y);
            if ((character.mainherorec.intersects(obstacle1Rec))||(character.mainherorec.intersects(obstacle2Rec))||(character.mainherorec.intersects(obstacle3Rec))||(character.mainherorec.intersects(obstacle4Rec))||(character.mainherorec.intersects(obstacle5Rec))) {
//                System.out.println("collision occured");
                handleCollision();
            }
            gameWindow.repaint();
            gameRoad.repaint();

        });
        timer1.start();
        GameOver = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Display "Game Over" message
                Font gameOverFont = new Font("Arial", Font.BOLD, 90);
                Font restartFont = new Font("Arial", Font.PLAIN, 40);
                g.setColor(Color.white);
                g.setFont(gameOverFont);
                g.drawString("Game Over!", 300, 200);

                g.setFont(restartFont);
                g.drawString("Press Space to Restart", 350, 270);
            }

        };
        GameOver.setBounds(0, 0, 1100, 700);
        GameOver.setFocusable(true);
//        GameOver.setBackground(Color.BLUE);
        GameOver.setVisible(false);
        GameOver.setOpaque(false);
        mainWindow.add(GameOver, Integer.valueOf(3));
        mainWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (GameOver.isVisible() && e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    System.out.println("good job");
                    GameOver.setVisible(false);
                    timer1.start();
                    character.characterstart();
                    oxOffset = 1100;
                    character.y=0;
                    oy1 =(int) (Math.random() * 2) * 100;
                    oy2 = (int) (Math.random() * 2) * 100;
                    character.gameCharacter.requestFocusInWindow();
                }
            }
        });



        mainChar.setFocusable(true);
        mainChar.requestFocusInWindow();
        frame.setVisible(true);
    }
    void handleCollision() {
        timer1.stop();
        character.characterstop();
        mainWindow.requestFocusInWindow();
        GameOver.setVisible(true);
    }

}
