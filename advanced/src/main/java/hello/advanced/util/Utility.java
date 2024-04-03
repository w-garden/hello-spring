package hello.advanced.util;

public class Utility {
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ;
        }
    }
}
