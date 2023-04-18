import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // EXERCISES 1.10

        // inputAllBaseTypes();
        // gameEntry();
        // System.out.println(isMultiple(15, 3));
        System.out.println(-18 << 1);
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
            public int score;

            public GameEntry(int score) {
                this.score = score;
            }
        }
        GameEntry[] A = { new GameEntry(0), new GameEntry(1), new GameEntry(2), new GameEntry(3) };
        GameEntry[] B = A.clone();

        // Since array A contains references to object of GameEntry any modification
        // will reflect also in array B.
        A[3].score = 550;
    }

    // Reinforcement R-1.3
    static boolean isMultiple(long n, long m) {
        return n % m == 0 ? true : false;
    }

    // Reinforcement R-1.4
    static boolean isEven(int i) {
        if (i == 2)
            return true;
        return i >> 1 > 0 ? true : false;
    }
}
