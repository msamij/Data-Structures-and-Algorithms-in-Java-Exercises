package exercises.creativity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import fundamentalDataStructures.DoublyLinkedList;
import fundamentalDataStructures.SinglyLinkedList;
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

	/**
	 * C-3.23 Suppose you are designing a multiplayer game that has n ≥ 1000
	 * players, numbered 1 to n, interacting in an enchanted forest. The winner of
	 * this game is the first player who can meet all the other players at least
	 * once (ties are allowed). Assuming that there is a method meet(i, j), which is
	 * called each time a player i meets a player j (with i != j), describe a way
	 * to keep track of the pairs of meeting players and who is the winner.
	 */
	public static void creativity7() {
		class Player {
			final int playerNo;
			int meetCount;

			Player(int playerNo) {
				this.playerNo = playerNo;
				this.meetCount = 0;
			}
		}
		class PlayerPair {
			final Player player1;
			final Player player2;

			PlayerPair(Player player1, Player player2) {
				this.player1 = player1;
				this.player2 = player2;
			}
		}
		class MultiplayerGame {
			final Player[] players;
			private final Stack<PlayerPair> playerPairs;

			MultiplayerGame() {
				this.players = new Player[10];
				this.playerPairs = new Stack<>();
			}

			void initializePlayers() {
				for (int i = 0; i < players.length; i++) {
					players[i] = new Player(i + 1);
				}
			}

			void playGame(int startingPlayer) {
				Player i = players[startingPlayer - 1];
				Player j = null;

				for (int k = 0; k < players.length; k++) {
					// (startingPlayer - 1) since player starting from non-zero value.
					if (k != startingPlayer - 1) {
						j = players[k];
						meet(i, j);
					}
				}
			}

			void isWinner(int playerNo) {
				PlayerPair poppedPlayer = playerPairs.pop();
				if (poppedPlayer.player1.playerNo == playerNo) {
					if (poppedPlayer.player1.meetCount == players.length - 1)
						System.out.println("Winner is player: " + playerNo);

				} else if (poppedPlayer.player2.playerNo == playerNo) {
					if (poppedPlayer.player2.meetCount == players.length - 1)
						System.out.println("Winner is player: " + playerNo);
				} else
					System.out.println("There's a tie!");
			}

			private void meet(Player i, Player j) {
				i.meetCount++;
				j.meetCount++;
				playerPairs.add(new PlayerPair(i, j));
			}
		}

		MultiplayerGame multiplayerGame = new MultiplayerGame();
		multiplayerGame.initializePlayers();
		multiplayerGame.playGame(2);
		multiplayerGame.isWinner(2);
	}

	/**
	 * C-3.24 Write a Java method that takes two three-dimensional integer arrays
	 * and adds them componentwise.
	 */
	public static void creativity8(int x, int rows, int columns) {
		int[][][] array1 = new int[x][rows][columns];
		int[][][] array2 = new int[x][rows][columns];
		int[][][] resultArray = new int[x][rows][columns];

		if (x == 0)
			throw new IllegalArgumentException("x must be > 0");
		if (rows != columns)
			throw new IllegalArgumentException("Rows and columns must be of equal length");

		int count = 1;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < columns; k++) {
					array1[i][j][k] = count;
					count++;
				}
			}
		}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < columns; k++) {
					array2[i][j][k] = count;
					count++;
				}
			}
		}

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < columns; k++) {
					resultArray[i][j][k] = array1[i][j][k] + array2[i][j][k];
					count++;
				}
			}
		}

		// Printing the 3D array
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < columns; k++) {
					System.out.print(resultArray[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	/**
	 * C-3.25 Describe an algorithm for concatenating two singly linked lists L and
	 * M, into a single list L′ that contains all the nodes of L followed by all
	 * the nodes of M.
	 */
	public static void creativity9() {
		SinglyLinkedList<Integer> L = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> M = new SinglyLinkedList<>();
		L.addFirst(1);
		L.addLast(2);
		L.addLast(3);

		M.addFirst(4);
		M.addLast(5);
		M.addLast(6);

		SinglyLinkedList<Integer> concatenateLinkedList = new SinglyLinkedList<>();
		concatenateLinkedList.concatenateList(L, M);

		while (L.getHead() != null) {
			System.out.println(L.getHead().getElement());
			L.setHead(L.getHead().getNext());
		}
	}

	/**
	 * C-3.26 Give an algorithm for concatenating two doubly linked lists L and M,
	 * with header and trailer sentinel nodes, into a single list L′ .
	 */
	public static void creativity10() {
		DoublyLinkedList<Integer> L = new DoublyLinkedList<>();
		DoublyLinkedList<Integer> M = new DoublyLinkedList<>();
		L.addFirst(1);
		L.addLast(2);
		L.addLast(3);

		M.addFirst(4);
		M.addLast(5);
		M.addLast(6);
		DoublyLinkedList<Integer> concatenateList = new DoublyLinkedList<>();
		concatenateList.concatenateLinkedList(L, M);

	}

	/**
	 * C-3.27 Describe in detail how to swap two nodes x and y (and not just their
	 * contents) in a singly linked list L given references only to x and y. Repeat
	 * this exercise for the case when L is a doubly linked list. Which algorithm
	 * takes more time?
	 */
	public static void creativity11() {
		SinglyLinkedList<Integer> L = new SinglyLinkedList<>();
		L.addFirst(1);
		L.addLast(2);
		L.addLast(3);
		L.addLast(4);

		// Clearly the algorithm for swapping nodes in singly linkedlist takes more time
		// than of doubly linkedlist as we have to traverse the list to find parent node
		// for x and y. Which in worse case would be O(n).
		L.swapNodes(L.getTail(), L.getHead());

		DoublyLinkedList<Integer> M = new DoublyLinkedList<>();
		M.addFirst(1);
		M.addLast(2);
		M.addLast(3);
		M.addLast(4);

		M.swapNodes(M.getHeader().getNext(), M.getTrailer().getPrev());

		System.out.println(M.getHeader().getNext().getElement());
		System.out.println(M.getTrailer().getPrev().getElement());
	}

	/**
	 * C-3.28 Describe in detail an algorithm for reversing a singly linked list L
	 * using only a constant amount of additional space.
	 */
	public static void creativity12() {
		SinglyLinkedList<Integer> L = new SinglyLinkedList<>();
		L.addFirst(1);
		L.addLast(2);
		L.addLast(3);
		L.addLast(4);

		L.reverseList();
		while (L.getHead() != null) {
			System.out.println(L.getHead().getElement());
			L.setHead(L.getHead().getNext());
		}
	}

	/**
	 * C-3.29 Suppose you are given two circularly linked lists, L and M. Describe
	 * an algorithm for telling if L and M store the same sequence of elements (but
	 * perhaps with different starting points).
	 */
	public static void creativity13() {
		//
	}
}
