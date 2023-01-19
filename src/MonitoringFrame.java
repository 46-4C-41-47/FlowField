import javax.swing.*;
import java.awt.*;

public class MonitoringFrame extends JFrame {
    private JLabel fps;

    public MonitoringFrame() {
        super("FPS");
        this.setSize(200, 150);
        this.setLocation(1600, 100);
        this.init();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void init() {
        JPanel displayPanel = new JPanel();

        this.setBackground(Color.BLACK);
        fps = new JLabel();
        displayPanel.add(new JLabel("FPS : "));
        displayPanel.add(fps);

        this.setLayout(new BorderLayout());
        this.add(displayPanel, BorderLayout.CENTER);
    }

    public void refreshFps(int fps) {
        this.fps.setText("" + fps);
    }
}
