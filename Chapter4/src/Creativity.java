import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public final class Creativity {
	private Creativity() {
	}

	/**
	 * C-5.11 Describe a recursive algorithm to compute the integer part of the
	 * base-two logarithm of (n) using only addition and integer division.
	 */
	public static int creativity1(final int base, final int argument) {
		if ((base == 0) || (argument == 1))
			return 0;
		else if (base < 0) {
			throw new IllegalArgumentException("Base cannot be negative.");
		} else if (argument < 1) {
			throw new IllegalArgumentException("Argument cannot be negative.");
		}
		return computeLog(base, argument);
	}

	private static int computeLog(final int base, final int argument) {
		if (argument <= 1)
			return 0;
		else {
			int recursiveCalls = 1;
			final int logArgument = (argument / base);
			return recursiveCalls += computeLog(base, logArgument);
		}
	}

	/**
	 * C-5.12 Describe an efficient recursive algorithm for solving the element
	 * uniqueness problem, which runs in time that is at most O(n^2) in the worst
	 * case without using sorting.
	 */
	public static boolean creativity2(final int[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty!");
		} else if (array.length == 1) {
			return true;
		} else {
			final HashSet<Integer> hashSet = new HashSet<>();
			return uniqueness(array, 0, hashSet);
		}
	}

	/**
	 * 
	 * @param array
	 * @param index
	 * @param hashSet
	 * @return true if all elements of array are unique false otherwise.
	 */
	private static boolean uniqueness(final int[] array, final int index, final HashSet<Integer> hashSet) {
		if (index == array.length)
			return true;
		else if (hashSet.contains(array[index]))
			return false;
		else {
			hashSet.add(array[index]);
			return uniqueness(array, index + 1, hashSet);
		}
	}

	/**
	 * C-5.13 Give a recursive algorithm to compute the product of two positive
	 * integers, m and n, using only addition and subtraction.
	 */
	public static int creativity3(final int n, final int m) {
		if ((m == 0) || n == 0)
			return 0;
		else if (m == 1)
			return n;
		else if (n == 1)
			return m;

		final int additionThreshold = (n < m ? n : m); // This'll dettermine no of recursive calls to perform.
		final int largestInteger = (n > m ? n : m); // Largest number to perform addition on itself.
		return recursiveProduct(largestInteger, additionThreshold);
	}

	private static int recursiveProduct(final int no, final int additionThreshold) {
		if (additionThreshold == 0)
			return 0;
		else {
			return (no + recursiveProduct(no, additionThreshold - 1));
		}
	}

	/**
	 * C-5.15 Write a recursive method that will output all the subsets of a set of
	 * n elements (without repeating any subsets).
	 */
	public static void creativity5(final Set<Set<?>> superSet) {
		if (superSet.isEmpty()) {
			throw new IllegalArgumentException("Set is empty!");
		} else {
			final Iterator<Set<?>> superSetiterator = superSet.iterator();
			recursiveSuperSet(superSet, superSetiterator);
		}
	}

	private static void recursiveSuperSet(final Set<Set<?>> superSet, final Iterator<Set<?>> superSetIterator) {
		if (superSetIterator.hasNext()) {
			final Set<?> subSet = superSetIterator.next();
			printSubsets(subSet);
			recursiveSuperSet(superSet, superSetIterator);
		}
	}

	private static void printSubsets(Set<?> subSet) {
		final Iterator<?> subSetIterator = subSet.iterator();
		while (subSetIterator.hasNext()) {
			Object subSetElement = subSetIterator.next();
			System.out.println(subSetElement);
		}
	}

	/**
	 * C-5.16 In the Towers of Hanoi puzzle, we are given a platform with three
	 * pegs, a, b, and c, sticking out of it. On peg a is a stack of n disks, each
	 * larger than the next, so that the smallest is on the top and the largest is
	 * on the bottom. The puzzle is to move all the disks from peg a to peg c,
	 * moving one disk at a time, so that we never place a larger disk on top of a
	 * smaller one. Describe a recursive algorithm for solving the Towers of Hanoi
	 * puzzle for arbitrary n. (Hint: Consider first the subproblem of moving all
	 * but the nth disk from peg a to another peg using the third as “temporary
	 * storage.”)
	 */
	public static <T> Stack<T> creativity6(final Stack<T> pegA) {
		if (pegA.empty()) {
			throw new IllegalArgumentException("Peg is empty!");
		} else {
			final Stack<T> pegB = new Stack<>();
			final Stack<T> pegC = new Stack<>();
			return performPegMove(pegA, pegB, pegC);
		}
	}

	private static <T> Stack<T> performPegMove(final Stack<T> pegA, final Stack<T> pegB, final Stack<T> pegC) {
		// Moves all from pegB to pegC.
		if ((pegA.empty()) && !pegB.empty()) {
			pegC.push(pegB.pop());
			performPegMove(pegA, pegB, pegC);
		}
		// Perform all moves from pegA to pegB.
		else if (pegC.empty()) {
			pegB.push(pegA.pop());
			performPegMove(pegA, pegB, pegC);
		}
		return pegC;
	}

	/**
	 * C-5.17 Write a short recursive Java method that takes a character string s
	 * and outputs its reverse. For example, the reverse of 'pots&pans' would be
	 * 'snap&stop'.
	 */
	public static StringBuilder creativity7(final String s) {
		return reverseString(s);
	}

	/**
	 * C-5.18 Write a short recursive Java method that determines if a string s is a
	 * palindrome, that is, it is equal to its reverse. Examples of palindromes
	 * include 'racecar' and 'gohangasalamiimalasagnahog'.
	 */
	public static boolean creativity8(final String s) {
		return reverseString(s).toString().equals(s);
	}

	/**
	 * 
	 * @param s string must not be empty
	 * @return string as stringBuilder instance containing reverse of string s.
	 */
	private static StringBuilder reverseString(String s) {
		if (s.length() == 0 || s == null) {
			throw new IllegalArgumentException("String is empty!");
		} else if (s.length() == 1) {
			return new StringBuilder(s);
		} else {
			final StringBuilder stringBuilder = new StringBuilder();
			return reverseString(s, stringBuilder, s.length() - 1);
		}
	}

	/**
	 * 
	 * @param string        string.
	 * @param stringBuilder stringBuilder instance.
	 * @param stringLength  length of string.
	 * @return stringBuilder instance containing reverse of string s.
	 */
	private static StringBuilder reverseString(
			final String string,
			final StringBuilder stringBuilder,
			final int stringLength) {
		if (stringLength < 0)
			return null;
		else {
			stringBuilder.append(string.charAt(stringLength));
			reverseString(string, stringBuilder, stringLength - 1);
			return stringBuilder;
		}
	}

	/**
	 * C-5.20 Write a short recursive Java method that rearranges an array of
	 * integer values so that all the even values appear before all the odd values.
	 */
	public static int[] creativity9(final int[] array) {
		if (array.length == 0)
			throw new IllegalArgumentException("Array is empty!");
		else if (array.length == 1)
			return array;
		else {
			return recursiveShuffleEvenOdd(array, 0);
		}
	}

	/**
	 * Recursively shuffles an array so that even integer's appear before odd one's.
	 * 
	 * @param array
	 * @param arrayIndex startingIndex of an array (Generally starts with index 0).
	 * @return the modified array such that all even numbers appear before odd.
	 */
	private static int[] recursiveShuffleEvenOdd(int[] array, final int arrayIndex) {
		if (arrayIndex == array.length)
			return array;
		else {
			if ((array[arrayIndex] % 2 != 0)) {
				final int evenIndex = findEvenIndex(array, arrayIndex);
				try {
					array = performArrayElementSwap(array, arrayIndex, evenIndex);
				} catch (IllegalArgumentException e) {
					// Do absolutely nothing.
				}
			}
			return recursiveShuffleEvenOdd(array, arrayIndex + 1);
		}
	}

	private static int[] iterativeShuffleEvenOdd(int[] array, final int arrayIndex) {
		int oddIndex = 0;
		int evenIndex = 0;
		while (oddIndex < array.length) {
			if ((array[oddIndex] % 2 != 0)) {
				evenIndex = findEvenIndex(array, oddIndex);
				try {
					array = performArrayElementSwap(array, arrayIndex, evenIndex);
				} catch (IllegalArgumentException e) {
					// Do absolutely nothing.
				}
			}
			oddIndex++;
		}
		return array;
	}

	/**
	 * Swaps array element with each other (replaces element at swapIndex with
	 * element at targetIndex and vice-versa).
	 * 
	 * @param array
	 * @param sourceIndex index of the element to be swapped with targetIndex.
	 * @param targetIndex index of the element to be swapped with sourceIndex.
	 * @return the modified array with elements being swapped with each other.
	 */
	private static int[] performArrayElementSwap(final int[] array, int sourceIndex, int targetIndex) {
		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty and cannot be swapped.");
		} else if ((sourceIndex < 0) || (targetIndex < 0)) {
			throw new IllegalArgumentException("Negative indicies are invalid for an array.");
		} else if (sourceIndex == targetIndex) {
			throw new IllegalArgumentException("Both indicies are equal.");
		} else {
			final int source = array[sourceIndex];
			array[sourceIndex] = array[targetIndex];
			array[targetIndex] = source;
			return array;
		}
	}

	/**
	 * Runs in O(n) in worst case if we have to traverse entire array.
	 * 
	 * @param array
	 * @param startingIndex index from where to start search from.
	 * @return index of element which is even -1 otherwise.
	 */
	private static int findEvenIndex(final int[] array, final int startingIndex) {
		int index = -1;
		for (int i = startingIndex; i < array.length; i++) {
			if ((array[i] % 2 == 0)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
