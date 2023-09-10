import java.util.Arrays;
import java.util.Random;

import exercises.creativity.Creativity;
import fundamentalDataStructures.SinglyLinkedList;
import utilityClasses.CaesarCipher;
import utilityClasses.TicTacToe;

public class App {
    public static void main(String[] args) throws Exception {
        int[] array = { 1, 5, 3, 2, 1, 0, 8, 5, 2, 7, 7, 8, 3 }; // creativity 2
        Creativity.creativity13();
    }

    static void insertionSort(int[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {
            int cur = data[k];
            int j = k;
            while (j > 0 && data[j - 1] > cur) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = cur;
        }
    }

    static void javaUtilArraysMethods() {
        int[] array1 = { 4, 2, 3, 5, 1 };
        int[] array2 = { 4, 2, 3, 5, 1 };
        int[] array3 = new int[4];

        /* returns 0 since both arrays have same values in same order. */
        System.out.println(Arrays.compare(array1, array2));

        /* Fill this array in every cell with value 2. */
        Arrays.fill(array3, 2);

        /*
         * Returns an array of size n such that the first k elements of
         * this array are copied from A, where k = min{n, A.length}. If n > A.length,
         * then the last n − A.length elements in this array will be padded with default
         * values, e.g -> 0 for an array of int and null for an array of objects.
         * 
         * This create an array of the values = [4, 2, 3, 5, 1, 0, 0] since size of
         * array2 is 5 and n is 7 (n - array2.length = 2) last two elements will be
         * padded with zeros. It'll create a new array of size 7 with first 5 being same
         * as array2 and last 2 will be 0.
         */
        int[] array4 = Arrays.copyOf(array2, 7);

        /*
         * Returns an array of size t − s such that the elements of this array are
         * copied in order from A[s] to A[t − 1], where s < t, padded as with copyOf()
         * if t > A.length.
         */
        /* Result in an array of [4, 2, 3, 5, 1] */
        int[] array5 = Arrays.copyOfRange(array2, 0, 5);
    }

    static void randomArraysTest() {
        int data[] = new int[10];
        Random rand = new Random();

        // a pseudo-random number generator
        rand.setSeed(System.currentTimeMillis());

        // use current time as a seed
        // fill the data array with pseudo-random numbers from 0 to 99, inclusive
        for (int i = 0; i < data.length; i++)
            data[i] = rand.nextInt(100);

        // the next pseudo-random number
        int[] orig = Arrays.copyOf(data, data.length); // make a copy of the data array
        System.out.println("arrays equal before sort: " + Arrays.equals(data, orig));
        Arrays.sort(data);

        // sorting the data array (orig is unchanged)
        System.out.println("arrays equal after sort: " + Arrays.equals(data, orig));
        System.out.println("orig = " + Arrays.toString(orig));
        System.out.println("data = " + Arrays.toString(data));
    }

    static void caesarCipherTest() {
        CaesarCipher cipher = new CaesarCipher(3);
        System.out.println("Encryption code = " + new String(cipher.encoder));
        System.out.println("Decryption code = " + new String(cipher.decoder));
        String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S.";
        String coded = cipher.encrypt(message);
        System.out.println("Secret: " + coded);
        String answer = cipher.decrypt(coded);
        System.out.println("Message: " + answer);
    }

    static void ticTacToeTest() {
        TicTacToe game = new TicTacToe();
        game.putMark(1, 1);
        game.putMark(0, 2);
        game.putMark(2, 2);
        game.putMark(0, 0);
        game.putMark(0, 1);
        game.putMark(2, 1);
        game.putMark(1, 2);
        game.putMark(1, 0);
        game.putMark(2, 0);
        System.out.println(game);
        int winningPlayer = game.winner();
        String[] outcome = { "O wins", "Tie", "X wins" }; // rely on ordering
        System.out.println(outcome[1 + winningPlayer]);
    }

    static void equivalenceTestingWithArrays() {
        /*
         * Arrays are a reference type in Java, but not technically a class.
         */
        int[] array1 = { 1, 2, 3 };
        int[] array2 = { 1, 2, 3 };

        /*
         * a == b: Tests if a and b refer to the same underlying array instance.
         * This tests returns false as both array1 and array2 refers to different object
         * in memory.
         */
        System.out.println(array1 == array2);

        /*
         * a.equals(b): Interestingly, this is identical to a == b. Arrays are not a
         * true class type and do not override the Object.equals method.
         */
        System.out.println(array1.equals(array2));

        /*
         * This provides a more intuitive notion of equivalence, returning true if the
         * arrays have the same length and all pairs of corresponding elements are
         * “equal” to each other. More specifically, if the array elements are
         * primitives, then it uses the standard == to compare values. If elements of
         * the arrays are a reference type, then it makes pairwise comparisons
         * a[k].equals(b[k]) in evaluating the equivalence.
         */
        System.out.println(Arrays.equals(array1, array2));

        /*
         * Arrays.compare() does comparison based on lexicographic order for each array.
         * returns 0 if both are lexicographically same (if the first and second array
         * are equal and contain the same elements in the same order). returns < 0 if
         * the first array is lexicographically less than the second array and returns
         * > 0 if the first array is lexicographically greater than the second array.
         */
        System.out.println(Arrays.compare(array1, array2));

        /*
         * For most applications, the Arrays.equals behavior captures the appropriate
         * notion of equivalence. However, there is an additional complication when
         * using multidimensional arrays. The fact that two-dimensional arrays in Java
         * are really one-dimensional arrays nested inside a common one-dimensional
         * array raises an interesting issue with respect to how we think about compound
         * objects, which are objects like a two dimensional array that are made up of
         * other objects. In particular, it brings up the question of where a compound
         * object begins and ends. Thus, if we have a two-dimensional array, a, and
         * another two-dimensional array, b, that has the same entries as a, we probably
         * want to think that a is equal to b. But the one-dimensional arrays that make
         * up the rows of a and b (such as a[0] and b[0]) are stored in different memory
         * locations, even though they have the same internal content. Therefore, a call
         * to the method java.util.Arrays.equals(a,b) will return false in this case,
         * because it tests a[k].equals(b[k]), which invokes the Object class’s
         * definition of equals. To support the more natural notion of multidimensional
         * arrays being equal if they have equal contents, the class provides an
         * additional method:
         */
        int[][] mArray1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
        int[][] mArray2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
        System.out.println(mArray1.equals(mArray2)); // false
        System.out.println(Arrays.equals(mArray1, mArray2)); // false

        /*
         * Identical to Arrays.equals(a,b) except when the elements of a and b are
         * themselves arrays, in which case it calls Arrays.deepEquals(a[k],b[k]) for
         * corresponding entries, rather than a[k].equals(b[k]).
         */
        System.out.println(Arrays.deepEquals(mArray1, mArray2)); // true.
    }

    static void equivalenceTestingWithLinkedList() {
        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
        SinglyLinkedList<String> singlyLinkedList2 = new SinglyLinkedList<>();
        singlyLinkedList.addFirst("Captain Price");
        singlyLinkedList.addLast("Soap Mactavish");

        singlyLinkedList2.addFirst("Captain Price");
        singlyLinkedList2.addLast("Soap Mactavish");
        System.out.println(singlyLinkedList.equals(singlyLinkedList2));
    }

    static void cloningDataStructures() {
        /*
         * The universal Object superclass defines a method named clone, which can be
         * used to produce what is known as a shallow copy of an object. This uses the
         * standard assignment semantics to assign the value of each field of the new
         * object equal to the corresponding field of the existing object that is being
         * copied. The reason this is known as a shallow copy is because if the field is
         * a reference type, then an initialization of the form duplicate.field =
         * original.field causes the field of the new object to refer to the same
         * underlying instance as the field of the original object.
         * 
         * A shallow copy is not always appropriate for all classes, and therefore, Java
         * intentionally disables use of the clone() method by declaring it as
         * protected, and by having it throw a CloneNotSupportedException when called.
         * The author of a class must explicitly declare support for cloning by formally
         * declaring that the class implements the Cloneable interface, and by declaring
         * a public version of the clone() method. That public method can simply call
         * the protected one to do the field-by-field assignment that results in a
         * shallow copy, if appropriate. However, for many classes, the class may choose
         * to implement a deeper version of cloning, in which some of the referenced
         * objects are themselves cloned.
         */
    }

    static void cloningArrays() {
        /*
         * Although arrays support some special syntaxes such as a[k] and a.length, it
         * is important to remember that they are objects, and that array variables are
         * reference variables. This has important consequences. As a first example,
         * consider the following code:
         */

        int[] data = { 2, 3, 5, 7, 11, 13, 17, 19 };
        int[] backup;
        backup = data; // warning; not a copy
        /*
         * The assignment of variable backup to data does not create any new array; it
         * simply creates a new alias for the same array.
         */

        /*
         * Instead, if we want to make a copy of the array, data, and assign a reference
         * to the new array to variable, backup, we should write:
         */
        backup = data.clone();
        /*
         * The clone method, when executed on an array, initializes each cell of the new
         * array to the value that is stored in the corresponding cell of the original
         * array. This results in an independent array.
         */

        /*
         * If we subsequently make an assignment such as data[4] = 23 in this
         * configuration, the backup array is unaffected. There are more considerations
         * when copying an array that stores reference types rather than primitive
         * types. The clone() method produces a shallow copy of the array, producing a
         * new array whose cells refer to the same objects referenced by the first
         * array.
         */

        class Person implements Cloneable {
            private String name;

            public Person(String n) {
                name = n;
            }
        }
        /*
         * For example, if the variable contacts refers to an array of hypothetical
         * Person instances, the result of the command guests = contacts.clone()
         * produces a shallow copy.
         */
        Person[] contacts = { new Person("A"), new Person("B") };
        Person[] guests;

        /*
         * A shallow copy of an array of objects, resulting from the command
         */
        guests = contacts.clone();

        /*
         * A deep copy of the contact list can be created by iteratively cloning the
         * individual elements, as follows, but only if the Person class is declared as
         * Cloneable.
         */
        // Person[] guestsDeepCopy = new Person[contacts.length];
        // for (int k = 0; k < contacts.length; k++)
        // guests[k] = (Person) contacts[k].clone();

        /*
         * Because a two-dimensional array is really a one-dimensional array storing
         * other one-dimensional arrays, the same distinction between a shallow and deep
         * copy exists. Unfortunately, the java.util.Arrays class does not provide any
         * “deepClone” method. However, we can implement our own method by cloning the
         * individual rows of an array.
         */
        int[][] mData = { { 2, 3, 5, 7, 11, 13, 17, 19 }, { 52, 6, 1, 0 } };
        int[][] mBackup = new int[mData.length][];
        for (int k = 0; k < mData.length; k++)
            mBackup[k] = mData[k].clone();
    }
}
