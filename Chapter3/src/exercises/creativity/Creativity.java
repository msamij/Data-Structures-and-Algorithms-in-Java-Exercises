package exercises.creativity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import utilityClasses.GameEntry;
import utilityClasses.ScoreBoard;

public class Creativity {

	private Creativity() {
	}

	private static class ArrayNotSupportedException extends Exception {
		private final String message;

		public ArrayNotSupportedException(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return message;
		}
	}

	/**
	 * C-3.17 Let A be an array of size n ≥ 2 containing integers from 1 to n − 1
	 * inclusive, one of which is repeated. Describe an algorithm for finding the
	 * integer in A that is repeated.
	 */
	public static int creativity1(int[] A) throws ArrayNotSupportedException {
		if (A.length < 2)
			throw new ArrayNotSupportedException("Array length must be >= 2");

		int result = -1;
		HashSet<Integer> seen = new HashSet<>();
		for (int num : A) {
			if (seen.contains(num)) {
				result = num;
				break;
			}
			seen.add(num);
		}
		return result;
	}

	/**
	 * C-3.18 Let B be an array of size n ≥ 6 containing integers from 1 to n − 5
	 * inclusive, five of which are repeated. Describe an algorithm for finding the
	 * five integers in B that are repeated.
	 */
	public static void creativity2(int[] B) throws ArrayNotSupportedException {
		if (B.length < 6)
			throw new ArrayNotSupportedException("Array length must be >= 6");

		Set<Integer> repeatedNumbers = new HashSet<>();
		Set<Integer> seen = new HashSet<>();
		for (int num : B) {
			if (seen.contains(num))
				repeatedNumbers.add(num);
			else
				seen.add(num);
		}
		System.out.println("Repeated integers are: ");
		while (repeatedNumbers.iterator().hasNext()) {
			System.out.println(repeatedNumbers.iterator().next());
			repeatedNumbers.remove(repeatedNumbers.iterator().next());
		}
	}

	/**
	 * C-3.19 Give Java code for performing add(e) and remove(i) methods for the
	 * Scoreboard class, except this time don’t maintain the game entries in order.
	 * Assume that we still need to keep n entries stored in indices 0 to n − 1. You
	 * should be able to implement the methods without using any loops, so that the
	 * number of steps they perform does not depend on n.
	 */
	public static void creativity3() {
		GameEntry johnEntry = new GameEntry("John", 100);
		GameEntry ghostEntry = new GameEntry("Ghost", 200);
		GameEntry capEntry = new GameEntry("Cap Price", 5000);
		ScoreBoard scoreBoard = new ScoreBoard(5);
		scoreBoard.addCreativity3(johnEntry);
		scoreBoard.addCreativity3(ghostEntry);
		scoreBoard.addCreativity3(capEntry);
		System.out.println(Arrays.toString(scoreBoard.getBoard()));
		scoreBoard.remove(1);
		System.out.println(Arrays.toString(scoreBoard.getBoard()));
	}

	/**
	 * C-3.20 Give examples of values for a and b in the pseudorandom generator
	 * given on page 113 of this chapter such that the result is not very random
	 * looking, for n = 1000.
	 */
	public static void creativity4() {
		int next = 0, a = 2, b = 1, cur = 1, n = 1000;
		for (int i = 0; i < 5; i++) {
			next = (a * cur + b) % n;
			System.out.println(next);
			cur++;
		}
	}

	/**
	 * C-3.22 Write a method, shuffle(A), that rearranges the elements of array A so
	 * that every possible ordering is equally likely. You may rely on the
	 * nextInt(n) method of the java.util.Random class, which returns a random
	 * number between 0 and n − 1 inclusive.
	 */
	public static void creativity6(int[] array) {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int temp, rand;
		for (int i = 0; i < array.length; i++) {
			rand = random.nextInt(i + 1);
			temp = array[i];
			array[i] = array[rand];
			array[rand] = temp;
		}

		for (int i : array) {
			System.out.println(i);
		}
	}
}
