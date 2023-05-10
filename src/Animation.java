import javax.swing.*;
import java.awt.*;
public class Animation {
    private static int imageIndex = 0;
    private Timer timer;
    private JPanel characterPanel;

    public Animation(JPanel characterPanel) {
        this.characterPanel = characterPanel;
        this.timer = new Timer(100, e -> {
            imageIndex++;
            if (imageIndex > 3) {
                imageIndex = 0;
            }
            characterPanel.repaint();
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private static String getImagePath() {
        return "E:\\JavaGame\\2D-Runner\\Game\\Assets\\Char" + imageIndex + ".png";
    }
    public static Image getCurrenImage() {
        return new ImageIcon(getImagePath()).getImage();
    }
}
