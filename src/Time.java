public class Time {
    private long startingtime;

    public void start() {
        startingtime = System.currentTimeMillis();
    }

    public double getRunTime() {
        return (double) (System.currentTimeMillis() - startingtime);
    }

    public double getFrameRate() throws NullPointerException {
        long runTime = System.currentTimeMillis() - startingtime;
        return 1000 / (double) runTime;
    }
}
