package exercises.reinforcement;

import java.util.Arrays;
import java.util.Random;

import fundamentalDataStructures.CircularlyLinkedList;
import fundamentalDataStructures.DoublyLinkedList;
import fundamentalDataStructures.SinglyLinkedList;
import utilityClasses.TicTacToe;

public final class Reinforcement {
	private Reinforcement() {
	}

	/**
	 * R-3.1 Give the next five pseudorandom numbers generated by the process
	 * described on page 113, with a = 12, b = 5, and n = 100, and 92 as the seed
	 * for cur.
	 **/
	public static void reinforcement1() {
		int next = 0, a = 12, b = 5, cur = 92, n = 100;
		for (int i = 0; i < 5; i++) {
			next = (a * cur + b) % n;
			System.out.println(next);
			cur++;
		}
	}

	/**
	 * R-3.2 Write a Java method that repeatedly selects and removes a random entry
	 * from an array until the array holds no more entries.
	 **/
	public static void reinforcement2() {
		int[] myArray = { 1, 2, 3, 4, 5 };
		int rand = 0;
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());

		int counter = 0;
		while (counter < myArray.length) {
			rand = random.nextInt(myArray.length);
			if (myArray[rand] != 0) {
				myArray[rand] = 0;
				counter++;
			}
		}

		for (int i : myArray) {
			System.out.println(i);
		}
	}

	/**
	 * R-3.3 Explain the changes that would have to be made to the program of Code
	 * Fragment 3.8 so that it could perform the Caesar cipher for messages that are
	 * written in an alphabet-based language other than English, such as Greek,
	 * Russian, or Hebrew.
	 */
	public static void reinforcement3() {
		/*
		 * We could replace everywheere first letter of english alphabet A with first
		 * letter of Greek Russian or Herbrew.
		 */
	}

	/**
	 * R-3.4 The TicTacToe class of Code Fragments 3.9 and 3.10 has a flaw, in that
	 * it allows a player to place a mark even after the game has already been won
	 * by someone. Modify the class so that the putMark method throws an
	 * IllegalStateException in that case.
	 */
	public static void reinforcement4() {
		TicTacToe game = new TicTacToe();
		game.putMark(0, 0);
		game.putMark(1, 0);
		game.putMark(1, 1);
		game.putMark(1, 2);
		game.putMark(2, 2);
		game.putMark(2, 0);

		System.out.println(game);
		int winningPlayer = game.winner();
		String[] outcome = { "O wins", "Tie", "X wins" }; // rely on ordering
		System.out.println(outcome[1 + winningPlayer]);
	}

	/**
	 * R-3.5 The removeFirst method of the SinglyLinkedList class includes a special
	 * case to reset the tail field to null when deleting the last node of a list.
	 * What are the consequences if we were to remove those two lines from the code?
	 * Explain why the class would or would not work with such a modification.
	 */
	public static void reinforcement5() {
		/*
		 * If we were to remove those two lines then tail would still point to a node,
		 * Which was also pointed by the head node. As an example if we only have a
		 * single node in the list then both head and tail would point to same node, Now
		 * when head reference was removed from that node tail must also be set to null
		 * otherwise that reference would still be there, eventhough list is now empty.
		 */
	}

	/**
	 * R-3.6 Give an algorithm for finding the second-to-last node in a singly
	 * linked list in which the last node is indicated by a null next reference.
	 */
	public static void reinforcement6() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		singlyLinkedList.addFirst(1);
		singlyLinkedList.addLast(2);
		singlyLinkedList.addLast(3);
		singlyLinkedList.addLast(4);
		singlyLinkedList.addLast(5);
		System.out.println(singlyLinkedList.secondLast());
	}

	/**
	 * R-3.7 Consider the implementation of CircularlyLinkedList.addFirst, in Code
	 * The else body of that method relies on a locally declared variable, newest.
	 * Redesign that clause to avoid use of any local variable.
	 */
	public static void reinforcement7() {
		CircularlyLinkedList<Integer> circularlyLinkedList = new CircularlyLinkedList<>();
		circularlyLinkedList.addFirst(1);
		circularlyLinkedList.addLast(2);
		circularlyLinkedList.addLast(3);
		System.out.println(circularlyLinkedList.first());
	}

	/**
	 * R-3.8 Describe a method for finding the middle node of a doubly linked list
	 * with header and trailer sentinels by “link hopping,” and without relying on
	 * explicit knowledge of the size of the list. In the case of an even number of
	 * nodes, report the node slightly left of center as the “middle.” What is the
	 * running time of this method?
	 */
	public static void reinforcement8() {
		/*
		 * Running time of this algorithm for best case is O(1), If we only have single
		 * element. In worst case it runs O(N).
		 */
		DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
		doublyLinkedList.addFirst(1);
		doublyLinkedList.addLast(2);
		doublyLinkedList.addLast(3);
		doublyLinkedList.addLast(4);
		doublyLinkedList.addLast(5);
		System.out.println(doublyLinkedList.middle());
	}

	/**
	 * R-3.9 Give an implementation of the size() method for the
	 * SingularlyLinkedList class, assuming that we did not maintain size as an
	 * instance variable.
	 */
	public static void reinforcement9() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		singlyLinkedList.addFirst(1);
		singlyLinkedList.addLast(2);
		singlyLinkedList.addLast(3);
		System.out.println(singlyLinkedList.sizeWithNoInstanceSizeVariable());
	}

	/**
	 * R-3.10 Give an implementation of the size() method for the
	 * CircularlyLinkedList class, assuming that we did not maintain size as an
	 * instance variable.
	 */
	public static void reinforcement10() {
		CircularlyLinkedList<Integer> circularlyLinkedList = new CircularlyLinkedList<>();
		circularlyLinkedList.addFirst(1);
		circularlyLinkedList.addLast(2);
		circularlyLinkedList.addFirst(0);
		circularlyLinkedList.addLast(3);
		System.out.println(circularlyLinkedList.sizeWithNoInstanceSizeVariable());
	}

	/**
	 * R-3.11 Give an implementation of the size() method for the DoublyLinkedList
	 * class, assuming that we did not maintain size as an instance variable.
	 */
	public static void reinforcement11() {
		DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
		doublyLinkedList.addFirst(1);
		doublyLinkedList.addLast(2);
		doublyLinkedList.addLast(3);
		doublyLinkedList.addLast(4);
		doublyLinkedList.addLast(5);
		System.out.println(doublyLinkedList.sizeWithNoInstanceSizeVariable());
	}

	/**
	 * R-3.12 Implement a rotate() method in the SinglyLinkedList class, which has
	 * semantics equal to addLast(removeFirst()), yet without creating any new
	 * node.
	 */
	public static void reinforcement12() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		singlyLinkedList.addFirst(1);
		singlyLinkedList.addLast(2);
		singlyLinkedList.addLast(3);
		singlyLinkedList.addFirst(0);
		singlyLinkedList.rotate();
		System.out.println(singlyLinkedList.last());
	}

	/**
	 * R-3.13 What is the difference between a shallow equality test and a deep
	 * equality test between two Java arrays, A and B, if they are one-dimensional
	 * arrays of type int? What if the arrays are two-dimensional arrays of type
	 * int?
	 */
	public static void reinforcement13() {
		/* Consider two one-dimensional arrays: */
		int[] array1 = { 1, 2, 3 };
		int[] array2 = { 1, 2, 3 };
		/*
		 * A shallow equality would be comparing each element of array1 at position i
		 * with it's corresponding element of array2 at position k where i and k are two
		 * indices of the array such that array1.length == array2.length. This equality
		 * would be true if every element in array1 at (i) is equal to it's
		 * corresponding element in array2 at (k).
		 */
		// Eg -->
		System.out.println(Arrays.equals(array1, array2)); // True

		/* Now consider two two-dimensional arrays: */
		int[][] mArray1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		int[][] mArray2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		/*
		 * If were to use the same Arrays.equals() approach this would result in a
		 * failure since multidimensional arrays are arrays inside of arrays. Eg -->
		 * [[],[],[]]. And Arrays.equals(mArray1,mArray2) would result in
		 * a[i].equals(b[i]) resulting in false since each index in a multidimensional
		 * array is not a primitive rather a reference to another array eg -->
		 * mArray1[0].equals(mArray2[0]); it would do a comparison of a row 0 of mArray1
		 * with row 0 of mArray2 which is obviously false, Since they both are at
		 * different memory locations. Therefore we use deep equality tests with
		 * Arrays.deepEquals() which would call Arrays.deepEquals() for corresponding
		 * entries rather than a[k].equals(b[k]).
		 */
		// Eg ->
		System.out.println(Arrays.deepEquals(mArray1, mArray2)); // True
	}

	/**
	 * R-3.14 Give three different examples of a single Java statement that assigns
	 * variable, backup, to a new array with copies of all int entries of an
	 * existing array, original.
	 */
	public static void reinforcement14() {
		int data[] = { 1, 2, 3, 4, 5 };
		int backup[];
		backup = data.clone();
		backup = Arrays.copyOf(data, data.length);
		backup = Arrays.copyOfRange(data, 0, data.length);
	}

	/**
	 * R-3.15 Implement the equals() method for the CircularlyLinkedList class,
	 * assuming that two lists are equal if they have the same sequence of elements,
	 * with corresponding elements currently at the front of the list.
	 */
	public static void reinforcement15() {
		CircularlyLinkedList<Integer> circularlyLinkedList1 = new CircularlyLinkedList<>();
		CircularlyLinkedList<Integer> circularlyLinkedList2 = new CircularlyLinkedList<>();
		circularlyLinkedList1.addFirst(1);
		circularlyLinkedList1.addLast(2);
		circularlyLinkedList1.addLast(3);

		circularlyLinkedList2.addFirst(1);
		circularlyLinkedList2.addLast(2);
		circularlyLinkedList2.addLast(3);
		System.out.println(circularlyLinkedList1.equals(circularlyLinkedList2));
	}

	/** R-3.16 Implement the equals( ) method for the DoublyLinkedList class. */
	public static void reinforcement16() {
		DoublyLinkedList<Integer> doublyLinkedList1 = new DoublyLinkedList<>();
		DoublyLinkedList<Integer> doublyLinkedList2 = new DoublyLinkedList<>();
		doublyLinkedList1.addFirst(1);
		doublyLinkedList1.addLast(2);
		doublyLinkedList1.addLast(3);

		doublyLinkedList2.addFirst(1);
		doublyLinkedList2.addLast(2);
		doublyLinkedList2.addLast(3);
		System.out.println(doublyLinkedList1.equals(doublyLinkedList2));
	}
}
