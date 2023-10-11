import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        RecursiveAlgorithms.diskUsage(new File("/home/muhammadsami/Public/Study/Books/OOP"));

        final int[] maxArray = { 2, 3, 5, 4, 16 };
        System.out.println(Reinforcement.reinforcement1(maxArray, maxArray.length - 1));

        final int[] sortedArray = { 1, 2, 4, 5, 16 };
        System.out.println(Reinforcement.reinforcement2(sortedArray, 16));

        final String stringToInt = "256798";
        System.out.println(Reinforcement.reinforcement8(stringToInt));
    }
}
