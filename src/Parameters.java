import java.awt.*;

public class Parameters {
    //Application
    public static final int NUMB_OF_THREADS = 6;
    public static final int FRAME_RATE = 60;
    public static final Dimension FRAME_SIZE = Toolkit.getDefaultToolkit().getScreenSize();


    //Simulation
    public static final int POPULATION = 10000;
    public static final int FOREGROUND_TRANSPARENCY = 5;
    public static final double SEED_INC = 0.0003;
    public static final double MAGNITUDE = 0.05;


    //Graphics
    public static final Color BACKGROUND = Color.BLACK;
    public static final Color VECTOR_COLOR = Color.WHITE;
    public static final Color FOREGROUND = new Color(0, 255, 100, FOREGROUND_TRANSPARENCY);
}
