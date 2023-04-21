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
        System.out.println(countVowels("Heello"));
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
                if (word.charAt(i) == vowels[j]) {
                    count++;
                }
            }
        }
        return count;
    }

}
