import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        int frameRate = 60;

        Frame frame = new Frame();
        Timer timer = new Timer(true);
        DrawTask drawTask = new DrawTask(frame.getCanvas());
        timer.scheduleAtFixedRate(drawTask, 0, 1000/frameRate);
    }
}