import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Error: Unhandled IOException
        /*
        for (String line :UtilKt.loadData()) {
            System.out.println(line);
        }
        */
        try {
            for (String line :UtilKt.loadData()) {
                System.out.println(line);
            }
        } catch (IOException e) { // Error
            System.out.println("Can't load data");
        }
    }
}
