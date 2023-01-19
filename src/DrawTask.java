import java.util.TimerTask;

public class DrawTask extends TimerTask {
    private MonitoringFrame monitoringFrame;
    private Canvas canvas;
    private Time time;

    public DrawTask(Canvas c, MonitoringFrame mf) {
        canvas = c;
        time = new Time();
        monitoringFrame = mf;
    }


    @Override
    public void run() {
        time.start();
        canvas.draw(canvas.getCanvasGraphics());
        canvas.paintComponent(canvas.getGraphics());
        monitoringFrame.refreshFps((int) time.getFrameRate());
        //System.out.println(time.getRunTime());
    }
}
