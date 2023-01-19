import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Canvas extends JPanel {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Parameters.NUMB_OF_THREADS);
    private static final Dimension FLOW_FIELD_SIZE = new Dimension(100, 56);
    private final NoiseGenerator noise = new NoiseGenerator();
    private Vector2D[][] vectors = new Vector2D[FLOW_FIELD_SIZE.height][FLOW_FIELD_SIZE.width];
    private BufferedImage screen;
    private Dimension size;
    private Agent[] agents;


    public Canvas(Dimension d) {
        super();
        size = new Dimension(d.width - 16, d.height - 39);
        screen = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        agents = new Agent[Parameters.POPULATION];

        for (int i = 0; i < this.agents.length; i++) {
            this.agents[i] = new Agent(size);
        }

        clearScreen();
        initializeVectors();
    }

    
    private void initializeVectors() {
        double value, xoff, yoff, increment = 0.8;

        yoff = 0;
        for (int i = 0; i < FLOW_FIELD_SIZE.height; i++) {
            xoff = 0;
            for (int j = 0; j < FLOW_FIELD_SIZE.width; j++) {
                value = ((noise.noise(xoff, yoff)) + 1) / 2;
                if (1 < value) {
                    value = 1;
                } else if (value < 0) {
                    value = 0;
                }

                vectors[i][j] = new Vector2D(value * 360);
                xoff += increment;
            }
            yoff += increment;
        }

        noise.setSeed(this.noise.getSeed() + Parameters.SEED_INC);
    }


    private void clearScreen() {
        Graphics screenGraphics = screen.getGraphics();
        screenGraphics.setColor(Parameters.BACKGROUND);
        screenGraphics.fillRect(0, 0, size.width, size.height);
    }


    private void drawVectors(Graphics g) {
        double yoff = (double) (screen.getHeight()) / vectors.length, xoff = (double) (screen.getWidth()) / vectors[0].length;
        int vectorLength = (int) (yoff * 0.8);

        g.setColor(Parameters.VECTOR_COLOR);

        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[0].length; j++) {
                int x = (int) ((j * xoff) + (xoff / 2)), y = (int) ((i * yoff) + (yoff / 2));
                g.drawLine(x, y, (int) (x + vectors[i][j].getX() * vectorLength), (int) (y + vectors[i][j].getY() * vectorLength));
            }
        }
    }


    public void draw(Graphics g) {
        g.setColor(Parameters.FOREGROUND);

        int step = agents.length / Parameters.NUMB_OF_THREADS;

        for (int i = step; i < agents.length; i += step) {
            final int temp = i;
            if (i == step + (agents.length % Parameters.NUMB_OF_THREADS)) {
                EXECUTOR.submit(() -> drawFlows(g, temp - step, temp + (agents.length % Parameters.NUMB_OF_THREADS)));
            } else {
                EXECUTOR.submit(() -> drawFlows(g, temp - step, temp));
            }
        }
        //drawFlows(g, 0, 0);

        initializeVectors();
        this.repaint();
    }


    public void drawFlows(Graphics g, int startingBounds, int endBounds) {
        double yoff = (double) (screen.getHeight()) / vectors.length, xoff = (double) (screen.getWidth()) / vectors[0].length;

        for (int i = 0; i < agents.length; i++) {
            int x = (int) (agents[i].getX() / xoff), y = (int) (agents[i].getY() / yoff);
            double alpha = (vectors[y][x].getTheta() - agents[i].getVector().getTheta()) * Parameters.MAGNITUDE;

            agents[i].rotate(alpha);
            agents[i].move();
            g.drawLine(agents[i].getX(), agents[i].getY(), agents[i].getPrev_x(), agents[i].getPrev_y());
        }
    }


    public Graphics getCanvasGraphics() {return screen.getGraphics();}


    @Override
    public void paintComponent(Graphics g) {g.drawImage(this.screen, 0, 0, null);}
}
