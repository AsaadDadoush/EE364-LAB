import java.util.Scanner;

public class InputListener implements Runnable {

    private volatile String input = "";
    private volatile boolean hasNew;
    private final Scanner in;
    private boolean stop;
    private boolean pause;

    public InputListener() {
        in = new Scanner(System.in);
    }

    @Override
    public void run() {
        while(!stop) {
            if (!pause){
                input = in.next();
                hasNew = true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopped input listener");
    }

    public void stop() {
        stop = true;
    }

    public String getInput() {
        hasNew = false;
        return input;
    }

    public boolean hasNew() {
        return hasNew;
    }

    public void pause() {
        pause = true;
    }

    public void unpause() {
        pause = false;
    }
}
