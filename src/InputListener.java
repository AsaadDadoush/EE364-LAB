import java.util.Scanner;

public class InputListener implements Runnable {

    private volatile String input = "";
    private volatile boolean hasNew;
    private final Scanner in;
    private boolean stop;

    public InputListener() {
        in = new Scanner(System.in);
    }

    @Override
    public void run() {
        while(!stop) {
            input = in.next();
            hasNew = true;
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
}
