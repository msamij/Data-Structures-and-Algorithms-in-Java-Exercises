import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // EXERCISES 1.10

        // inputAllBaseTypes();
        // gameEntry();
        // System.out.println(isMultiple(15, 3));
        // System.out.println(isEven(1));
        // System.out.println(sumOfPositiveIntegers(4));
        // System.out.println(sumOfOddPositiveIntegers(5));
        // System.out.println(sumOfSquaresOfPositiveIntegers(5));
        // System.out.println(countVowels("Heello"));
        // System.out.println(removePunctuations("Let’s try, Mike!"));

        // int[] array = { 3, 2, 1 };
        // System.out.println(reverseArrayOfIntegers(array));

        // int[] array = { 563, 12, 3, 1, 19, 0, 968 };
        // findSmallestAndLargestInAndArray(array);
        // arithmeticFormula(2, 3, 6);
        // int[] array = { 563, 12, 3, 1, 19 };
        // System.out.println(evenProductPair(array));
        // int[] array = { 4, 3 };
        // System.out.println(pNormVector(array, 2));
        // System.out.println(divideWhileRemainderGreatorThanTwo(15));
        // float[] array = { 1, 2, 3, 4, 1 };
        // distinctFloatValues(array);
        // reverseInput();
        punishmentSentence();
    }

    // Reinforcement R-1.1
    static void inputAllBaseTypes() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input byte value:");
            byte inputByte = scanner.nextByte();
            System.out.println("Input short value:");
            short inputShort = scanner.nextShort();
            System.out.println("Input int value:");
            int inputInt = scanner.nextInt();
            System.out.println("Input float value:");
            float inputFloat = scanner.nextFloat();
            System.out.println("Input double value:");
            double inputDouble = scanner.nextDouble();
            System.out.println("Input long value:");
            long inputLong = scanner.nextLong();
            System.out.println("Input boolean value:");
            boolean inputBoolean = scanner.nextBoolean();

            System.out.println("Byte value: " + inputByte);
            System.out.println("Short value: " + inputShort);
            System.out.println("Integer value: " + inputInt);
            System.out.println("Float value: " + inputFloat);
            System.out.println("Double value: " + inputDouble);
            System.out.println("Long value: " + inputLong);
            System.out.println("Boolean value: " + inputBoolean);
        }
    }

    // Reinforcement R-1.2
    static void gameEntry() {
        class GameEntry {
            int score;

            GameEntry(int score) {
                this.score = score;
            }
        }
        GameEntry[] A = { new GameEntry(0), new GameEntry(1), new GameEntry(2), new GameEntry(3) };
        // GameEntry[] B = A.clone();

        // Since array A contains references to object of GameEntry any modification to
        // it will reflect also in array B.
        A[3].score = 550;
    }

    // Reinforcement R-1.3
    static boolean isMultiple(long n, long m) {
        return n % m == 0 ? true : false;
    }

    // Reinforcement R-1.4
    static boolean isEven(int i) {
        return (i >> 1) << 1 == i ? true : false;
    }

    // Reinforcement R-1.5
    static int sumOfPositiveIntegers(int n) {
        if (n < 0)
            return -1;
        return n = (n * (n + 1)) / 2;
        // Another possible solution.
        // return (int) (Math.pow(n, 2) + n) / 2;
    }

    // Reinforcement R-1.6
    static int sumOfOddPositiveIntegers(int n) {
        if (n < 0)
            return -1;
        int sum = 0;
        while (n != 0) {
            if (n % 2 != 0)
                sum += n;
            n -= 1;
        }
        return sum;
    }

    // Reinforcement R-1.7
    static int sumOfSquaresOfPositiveIntegers(int n) {
        if (n < 0)
            return -1;
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n, 2);
            n -= 1;
        }
        return sum;
    }

    // Reinforcement R-1.8
    static int countVowels(String word) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < vowels.length; j++) {
                if (word.charAt(i) == vowels[j])
                    count++;

            }
        }
        return count;
    }

    // Reinforcement R-1.9
    static String removePunctuations(String s) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Creativity C-1.14
    static int[] reverseArrayOfIntegers(int[] array) {
        /*
         * Pseudo-Code:
         * 
         * int temp = 0
         * int startIndex = 0
         * int endIndex = array.length - 1
         * 
         * for startIndex <= endIndex
         * ** do
         * *** temp = array[startIndex]
         * *** array[startIndex] = array[endIndex]
         * *** array[endIndex] = temp
         * return array
         * 
         */

        int temp = 0;
        int startIndex;
        int endIndex = array.length - 1;

        for (startIndex = 0; startIndex <= endIndex; startIndex++) {
            temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
            endIndex -= 1;
        }
        return array;
    }

    // Creativity C-1.15
    static void findSmallestAndLargestInAndArray(int[] array) {
        /*
         * Pseudo-Code:
         * 
         * int smallest, largest = array[0]
         * for i -> array.length - 1
         * *** do
         * *** if smallest > array[i]
         * ****** smallest = array[i]
         * *** else if largest < array[i]
         * ****** largest = array[i]
         * 
         */
        int smallest = array[0];
        int largest = array[0];

        for (int i = 0; i < array.length; i++) {
            if (smallest > array[i])
                smallest = array[i];
            else if (largest < array[i])
                largest = array[i];
        }
        System.out.println("Smallest element in array: " + smallest);
        System.out.println("Largest element in array: " + largest);
    }

    // Creativity C-1.16
    static void arithmeticFormula(int a, int b, int c) {
        if ((a + b) == c) {
            System.out.println("a + b = c: " + c);
        } else if (a == (b - c)) {
            System.out.println("a = b - c: " + c);
        } else if ((a * b) == c) {
            System.out.println("a * b = c: " + c);
        }
    }

    // Creativity C-1.17
    static int evenProductPair(int[] array) {
        int even = 0;
        int evenProduct = 0;
        if (array.length > 1) {
            for (int i = 0; i < array.length; i++) {
                if (even != 0 && array[i] != even) {
                    evenProduct = even * array[i];
                    break;
                } else if (array[i] % 2 == 0 && array[i] != even)
                    even = array[i];
            }
        }
        return evenProduct % 2 == 0 ? evenProduct : -1;
    }

    // Creativity C-1.18
    static int pNormVector(int[] v, int p) {
        int result = 0;
        int vector = 0;
        for (int i = 0; i < v.length; i++)
            result += (int) Math.pow(v[i], p);
        vector = (int) Math.sqrt(result);
        return vector;
    }

    // Creativity C-1.19
    static int divideWhileRemainderGreatorThanTwo(int n) {
        if (n < 2)
            return -1;
        int count = 0;
        while (n > 2) {
            n /= 2;
            count++;
        }
        return count;
    }

    // Creativity C-1.20
    static boolean distinctFloatValues(float[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (j != i)
                    if (array[j] == array[i])
                        return false;
            }
        }
        return true;
    }

    // Creativity C-1.21
    static void shuffleArray(int[] array) {
        return;
    }

    // Creativity C-1.22
    static void stringFormation() {
        char[] letters = { 'c', 'a', 't', 'd', 'o', 'g' };
    }

    // Creativity C-1.23
    static int[] dotProduct(int[] a, int[] b) {
        // Assuming both array of same length.
        int[] c = new int[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;
    }

    // Projects P-1.26
    static void reverseInput() {
        int input;
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();

        System.out.println("Enter number press 0 to out:");
        while (!quit) {
            input = scanner.nextInt();
            if (input == 0) {
                quit = true;
                scanner.close();
            } else
                list.add(input);
        }

        for (int i = list.size() - 1; i > -1; i--) {
            System.out.println(list.get(i));
        }
    }

    // Projects P-1.28
    static void punishmentSentence() {
        String sentence = "I will never spam my friends again.";
        typos(sentence);
        // for (int i = 0; i < 10; i++) {
        // System.out.println(i + 1 + ": " + typos(sentence));
        // }
    }

    static String typos(String sentence) {
        Random random = new Random();
        int r1, r2;
        for (int i = 0; i <= 8; i++) {
            r1 = random.nextInt(0, sentence.length());
            r2 = random.nextInt(0, sentence.length());
            System.out.println(sentence.replace(sentence.charAt(r1), sentence.charAt(r2)));
        }
        return sentence;
    }
}
