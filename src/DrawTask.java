import java.util.TimerTask;

public class DrawTask extends TimerTask {
    private Canvas canvas;
    private Time time;

    public DrawTask(Canvas c) {
        canvas = c;
        time = new Time();
    }


    @Override
    public void run() {
        time.start();
        canvas.draw(canvas.getCanvasGraphics());
        canvas.paintComponent(canvas.getGraphics());
        //System.out.println(time.getRunTime());
    }
}
