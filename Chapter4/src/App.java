import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        RecursiveAlgorithms.diskUsage(new File("/home/muhammadsami/Public/Study/Books/OOP"));

        final int[] maxArray = { 2, 3, 5, 4, 1 };
        System.out.println("");
        System.out.println("Max element is: " + Reinforcement.reinforcement1(maxArray, maxArray.length - 1));

        System.out.println("");
        final int[] sortedArray = { 1, 2, 4, 5, 16 };
        System.out.println("Index of target is: " + Reinforcement.reinforcement2(sortedArray, 16));

        System.out.println("");
        final String stringToInt = "256798";
        System.out.println(Reinforcement.reinforcement8(stringToInt));

        System.out.println("");
        System.out.println(Reinforcement.reinforcement9(2, 5));

        final int[][] twoDimensionalArray = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        System.out.println("");
        System.out.println(Reinforcement.reinforcement10(twoDimensionalArray, twoDimensionalArray.length - 1));

        System.out.println("");
        System.out.println(Reinforcement.reinforcement7(4));

        System.out.println("");
        System.out.println(Creativity.creativity1(2, 16));

        System.out.println("");
        final int[] uniqueArray = { 1, 6, 4, 6, 3, 5, 0 };
        System.out.println(Creativity.creativity2(uniqueArray));

        System.out.println("");
        System.out.println(Creativity.creativity3(4, 2));

        System.out.println("");
        Set<Set<?>> superSet = new HashSet<>();

        HashSet<Integer> set1 = new HashSet<>();
        set1.add(0);
        set1.add(1);
        set1.add(2);
        HashSet<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        superSet.add(set1);
        superSet.add(set2);

        Creativity.creativity5(superSet);

        System.out.println("");
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        Stack<?> stack2 = Creativity.creativity6(stack);

        while (!stack2.empty()) {
            System.out.println(stack2.pop());
        }

        System.out.println("");
        System.out.println(Creativity.creativity7("pots&pans"));

        System.out.println("");
        System.out.println(Creativity.creativity8("i"));

        System.out.println("");
        final int[] oddEvenArray = { 3, 9, 2, 4, 0, 6, 8 };
        System.out.println(Arrays.toString(Creativity.creativity9(oddEvenArray)));
    }
}
