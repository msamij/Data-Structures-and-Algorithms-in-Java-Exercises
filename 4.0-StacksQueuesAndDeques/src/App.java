import java.util.Arrays;

import stack.ArrayStack;
import stack.PatternMatching;
import stack.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Integer[] array = { 1, 2, 3, 4, 5 };
        System.out.println(Arrays.toString(array));
        reverse(array);
        System.out.println(Arrays.toString(array));
        System.out.println(PatternMatching.isMatched("{[(5 + x) - (y + z)]}"));
        System.out.println(PatternMatching.isHTMLMatched("""
                <body>
                <center>
                <h1> The Little Boat </h1>
                </center>
                <p> The storm tossed the little
                boat like a cheap sneaker in an
                old washing machine. The three
                drunken fishermen were used to
                such treatment, of course, but
                not the tree salesman, who even as
                a stowaway now felt that he
                had overpaid for the voyage. </p>
                <ol>
                <li> Will the salesman die? </li>
                <li> What color is the boat? </li>
                <li> And what about Naomi? </li>
                </ol>
                </body>
                                """));
    }

    static <E> void reverse(E[] array) {
        Stack<E> buffer = new ArrayStack<>(array.length);
        for (int i = 0; i < array.length; i++) {
            buffer.push(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = buffer.pop();
        }
    }
}
