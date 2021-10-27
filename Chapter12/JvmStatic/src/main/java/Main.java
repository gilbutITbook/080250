import java.io.ByteArrayInputStream;

public class Main {
    public static void main(String[] args) {
        Application.setStdin(new ByteArrayInputStream("hello".getBytes()));
        Application.exit();
    }
}

