import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        RecursiveAlgorithms.diskUsage(new File("/home/muhammadsami/Public/Study/Books/OOP"));
        System.out.println(IterativeAlgorithms.fibonacci(8));
    }
}
