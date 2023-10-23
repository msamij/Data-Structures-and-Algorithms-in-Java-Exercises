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
		return CreativityUtility.computeLog(base, argument);
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
			return CreativityUtility.uniqueness(array, 0, hashSet);
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
		return CreativityUtility.recursiveProduct(largestInteger, additionThreshold);
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
			CreativityUtility.recursiveSuperSet(superSet, superSetiterator);
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
			return CreativityUtility.performPegMove(pegA, pegB, pegC);
		}
	}

	/**
	 * C-5.17 Write a short recursive Java method that takes a character string s
	 * and outputs its reverse. For example, the reverse of 'pots&pans' would be
	 * 'snap&stop'.
	 */
	public static StringBuilder creativity7(final String s) {
		return CreativityUtility.reverseString(s);
	}

	/**
	 * C-5.18 Write a short recursive Java method that determines if a string s is a
	 * palindrome, that is, it is equal to its reverse. Examples of palindromes
	 * include 'racecar' and 'gohangasalamiimalasagnahog'.
	 */
	public static boolean creativity8(final String s) {
		return CreativityUtility.reverseString(s).toString().equals(s);
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
			return CreativityUtility.recursiveShuffleEvenOdd(array, 0);
		}
	}

	/**
	 * C-5.21 Given an unsorted array, A, of integers and an integer k, describe a
	 * recursive algorithm for rearranging the elements in A so that all elements
	 * less than or equal to k come before any elements larger than k. What is the
	 * running time of your algorithm on an array of n values?
	 */
	public static int[] creativity10(final int[] unsortedArray, int k) {
		if (unsortedArray.length == 0) {
			throw new IllegalArgumentException("Array is empty!");
		}
		return CreativityUtility.recursiveShuffle(unsortedArray, 0, k);
	}

	/**
	 * C-5.22 Suppose you are given an array, A, containing n distinct integers that
	 * are listed in increasing order. Given a number k, describe a recursive
	 * algorithm to find two integers in A that sum to k, if such a pair exists.
	 * What is the running time of your algorithm?
	 */
	public static int[] creativity11(final int[] sortedArray, final int k) {
		if (sortedArray.length == 0) {
			throw new IllegalArgumentException("Array is empty!");
		} else if (sortedArray.length == 1) {
			throw new IllegalArgumentException("Array contains only one element");
		}
		return CreativityUtility.digitsOfSum(sortedArray, 0, sortedArray.length - 1, k);
	}

	/**
	 * C-5.23 Describe a recursive algorithm that will check if an array A of
	 * integers contains an integer A[i] that is the sum of two integers that appear
	 * earlier in A, that is, such that A[i] = A[j] + A[k] for j, k < i.
	 */
	public static int creativity12(final int[] A) {
		if (A.length == 0) {
			throw new IllegalArgumentException("Array is empty");
		} else if (A.length < 2) {
			throw new IllegalArgumentException("Array must contain more than two elements!");
		}
		return CreativityUtility.sumOfTwoPrevious(A, 2, 0, 1);
	}

	private static final class CreativityUtility {
		private CreativityUtility() {
		}

		/**
		 * Recursively computes log.
		 * 
		 * @param base
		 * @param argument
		 * @return
		 */
		static int computeLog(final int base, final int argument) {
			if (argument <= 1)
				return 0;
			else {
				int recursiveCalls = 1;
				final int logArgument = (argument / base);
				return recursiveCalls += computeLog(base, logArgument);
			}
		}

		/**
		 * 
		 * @param array
		 * @param index
		 * @param hashSet
		 * @return true if all elements of array are unique false otherwise.
		 */
		static boolean uniqueness(final int[] array, final int index, final HashSet<Integer> hashSet) {
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
		 * Recursively computes product by employing repeated addition.
		 * 
		 * @param no                an integer to which it'll perform repeated addition
		 *                          on itself.
		 * 
		 * @param additionThreshold no of times recursion take place. (This dettermines
		 *                          no of times (no) parameter perform addition on
		 *                          itself.
		 * 
		 * @return an integer which'll be product of given integer no.
		 */
		static int recursiveProduct(final int no, final int additionThreshold) {
			if (additionThreshold == 0)
				return 0;
			else {
				return (no + recursiveProduct(no, additionThreshold - 1));
			}
		}

		static void recursiveSuperSet(final Set<Set<?>> superSet, final Iterator<Set<?>> superSetIterator) {
			if (superSetIterator.hasNext()) {
				final Set<?> subSet = superSetIterator.next();
				printSubsets(subSet);
				recursiveSuperSet(superSet, superSetIterator);
			}
		}

		static void printSubsets(Set<?> subSet) {
			final Iterator<?> subSetIterator = subSet.iterator();
			while (subSetIterator.hasNext()) {
				Object subSetElement = subSetIterator.next();
				System.out.println(subSetElement);
			}
		}

		static <T> Stack<T> performPegMove(final Stack<T> pegA, final Stack<T> pegB, final Stack<T> pegC) {
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

		static int[] recursiveShuffle(int[] array, final int currentIndex, final int k) {
			if (currentIndex == array.length)
				return array;
			else {
				if (array[currentIndex] > k) {
					final int smallerIntegerIndex = findSmallestIntegerIndex(array, currentIndex,
							k);
					try {
						array = performArrayElementSwap(array, currentIndex,
								smallerIntegerIndex);
					} catch (IllegalArgumentException e) {
						// Do absolutely nothing.
					}
				}
				return recursiveShuffle(array, currentIndex + 1, k);
			}
		}

		/**
		 * Recursively shuffles an array so that even integer's appear before odd one's.
		 * 
		 * @param array
		 * @param currentIndex startingIndex of an array, generally starts with index 0.
		 * @return the modified array such that all even numbers appear before odd.
		 */
		static int[] recursiveShuffleEvenOdd(int[] array, final int currentIndex) {
			if (currentIndex == array.length)
				return array;
			else {
				if ((array[currentIndex] % 2 != 0)) {
					final int evenIndex = findEvenIndex(array, currentIndex);
					try {
						array = performArrayElementSwap(array, currentIndex, evenIndex);
					} catch (IllegalArgumentException e) {
						// Do absolutely nothing.
					}
				}
				return recursiveShuffleEvenOdd(array, currentIndex + 1);
			}
		}

		static int[] iterativeShuffleEvenOdd(int[] array, final int arrayIndex) {
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
		 * Swaps array element with each other (replaces element at sourceIndex with
		 * element at targetIndex and vice-versa).
		 * 
		 * @param array
		 * @param sourceIndex index of the element to be swapped with targetIndex.
		 * @param targetIndex index of the element to be swapped with sourceIndex.
		 * @return the modified array with elements being swapped with each other.
		 */
		static int[] performArrayElementSwap(final int[] array, int sourceIndex, int targetIndex) {
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
		 * Finds and returns the index of the even integer in the given array.
		 * <p/>
		 * Runs in O(n) in worst case if we have to traverse entire array.
		 * 
		 * @param array
		 * @param startingIndex index from where to start search.
		 * @return index of element which is even -1 otherwise.
		 */
		static int findEvenIndex(final int[] array, final int startingIndex) {
			int index = -1;
			for (int i = startingIndex; i < array.length; i++) {
				if ((array[i] % 2 == 0)) {
					index = i;
					break;
				}
			}
			return index;
		}

		/**
		 * Finds and returns the index of integer which is <= integer k.
		 * 
		 * @param array
		 * @param startingIndex index from where to start search.
		 * @param k             an integer for we wish to find a value <= that of.
		 * @return the index of an integer which is <= k, -1 otherwise.
		 */
		static int findSmallestIntegerIndex(final int[] array, final int startingIndex, final int k) {
			int smallestIntIndex = -1;
			for (int i = startingIndex; i < array.length; i++) {
				if (array[i] <= k) {
					smallestIntIndex = i;
					break;
				}
			}
			return smallestIntIndex;
		}

		/**
		 * 
		 * @param s string must not be empty
		 * @return string as stringBuilder instance containing reverse of string s.
		 */
		static StringBuilder reverseString(String s) {
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
		static StringBuilder reverseString(
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
		 * Finds and returns an array of two digits that sum to integer k
		 * <p/>
		 * Algorithm works by maintaining two pointers one at start of an array while
		 * other one at end. Comparison takes place at each recursive call if it has
		 * found a match it simply returns the array containing those two digits.
		 * <p/>
		 * Otherwise pointers gets updated and recursive calls takes place until both
		 * pointers meet. In that case no match was found and null is returned.
		 * 
		 * <p/>
		 * In worst case algorithm runs in O(n) and uses O(n) space.
		 * 
		 * @param sortedArray
		 * @param startingIndex
		 * @param lastIndex
		 * @param k
		 * @return an array containing two digits that add up to integer k. null
		 *         otherwise
		 */
		private static int[] digitsOfSum(final int[] sortedArray,
				final int startingIndex,
				final int lastIndex,
				final int k) {

			if (startingIndex == lastIndex)
				return null;
			else if ((sortedArray[startingIndex]) + (sortedArray[lastIndex]) == k) {
				final int[] matchedPairs = { sortedArray[startingIndex], sortedArray[lastIndex] };
				return matchedPairs;
			} else if ((sortedArray[startingIndex]) + (sortedArray[lastIndex]) > k) {
				return digitsOfSum(sortedArray, startingIndex, lastIndex - 1, k);
			} else if ((sortedArray[startingIndex]) + (sortedArray[lastIndex]) < k) {
				return digitsOfSum(sortedArray, startingIndex + 1, lastIndex, k);
			}
			return null;
		}

		/**
		 * 
		 * @param array
		 * @param i     index i which is > j, k.
		 * @param j     index which is < i.
		 * @param k     index which is < i.
		 * @return returns the index of an integer which the sum of two previous
		 *         integers -1 otherwise.
		 */
		private static int sumOfTwoPrevious(final int[] array, final int i, final int j, final int k) {
			if (i == array.length)
				return -1;
			else if (array[i] == (array[j]) + (array[k]))
				return i;
			else {
				return sumOfTwoPrevious(array, i + 1, j + 1, k + 1);
			}
		}
	}
}
