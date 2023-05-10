import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class mainHero implements KeyListener{
    public int x = 0;
    public int y = 0;
    public Animation characterAnimation;
    JPanel gameCharacter;

    public Rectangle mainherorec = new Rectangle(x,y,75,100);
    public Point getPosition() {
        return new Point(x, y);
    }
    public void characterstop(){
        characterAnimation.stop();
    }  public void characterstart(){
        characterAnimation.start();
    }

    public JPanel setGameCharacter()
    {
        gameCharacter = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // load the image file
                Image Character = Animation.getCurrenImage();

//                g.setColor(Color.BLUE);
//                g.fillRect(mainherorec.x, mainherorec.y, mainherorec.width, mainherorec.height);
                g.drawImage(Character, x, y, 80, 700 / 7, null);
            }
        };
        gameCharacter.setFocusable(true);
        gameCharacter.addKeyListener(this);
        gameCharacter.setOpaque(false);
        gameCharacter.setPreferredSize(new Dimension(80, 700 / 6));
        gameCharacter.setBounds(10, 445, 100, 220);
        characterAnimation=new Animation(gameCharacter);
        characterAnimation.start();
        return gameCharacter;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y =100;
            mainherorec.setLocation(x,y);
            gameCharacter.repaint();

        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            y=0;
            mainherorec.setLocation(x,y);
            gameCharacter.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
