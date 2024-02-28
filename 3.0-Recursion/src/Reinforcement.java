public final class Reinforcement {
	private Reinforcement() {
	}

	/**
	 * R-5.1 Describe a recursive algorithm for finding the maximum element in an
	 * array, A, of n elements. What is your running time and space usage?
	 * <p/>
	 * The algorithm runs in O(n) time and uses O(n) space.
	 */
	public static int reinforcement1(final int[] A, final int n) {
		if (A.length == 0) {
			throw new IllegalArgumentException("Array length must be >= 1.");
		}
		return ReinforcementUtility.maximumArray(A, n, A[n]);
	}

	/**
	 * R-5.2 Explain how to modify the recursive binary search algorithm so that it
	 * returns the index of the target in the sequence or −1 (if the target is not
	 * found).
	 * 
	 * <p/>
	 * Returns index of the target if the target value is found in the data array.
	 */
	public static int reinforcement2(final int[] data, final int target) {
		return ReinforcementUtility.binarySearch(data, target, 0, data.length - 1);
	}

	/**
	 * R-5.7 Describe a recursive algorithm for computing the nth Harmonic number,
	 * defined as Hn = ∑k=1 to n (1/k).
	 */
	public static double reinforcement7(int n) {
		if (n == 1)
			return n;
		else {
			return (double) 1 / n + reinforcement7(n - 1);
		}
	}

	/**
	 * R-5.8 Describe a recursive algorithm for converting a string of digits into
	 * the integer it represents. For example, '13531' represents the integer
	 * 13,531.
	 */
	public static int reinforcement8(final String digits) {
		if (digits.isEmpty()) {
			throw new IllegalArgumentException("String length is 0");
		}
		return ReinforcementUtility.stringToInteger(digits.toCharArray(), digits.length() - 1);
	}

	/**
	 * R-5.9 Develop a nonrecursive implementation of the version of the power
	 * method from Code Fragment 5.9 that uses repeated squaring.
	 *
	 * <p/>
	 * Computes the value of x raised to the nth power, for nonnegative integer n.
	 */
	public static long reinforcement9(final long x, final long n) {
		if (n == 0)
			return 1;
		if (x == 0)
			return 0;
		if (n < 0)
			throw new IllegalArgumentException("Exponent must be positive.");
		return ReinforcementUtility.nonRecursivePower(x, n);
	}

	/**
	 * R-5.10 Describe a way to use recursion to compute the sum of all the elements
	 * in an (n x n) (two-dimensional) array of integers.
	 */
	public static int reinforcement10(final int[][] array, final int n) {
		if (array.length == 0) {
			throw new IllegalArgumentException("Array must not be empty!");
		}
		int sum = 0;
		int recursiveDepth = n;
		while (recursiveDepth >= 0) {
			sum += ReinforcementUtility.linearSum(array[recursiveDepth], array[recursiveDepth].length - 1);
			recursiveDepth -= 1;
		}
		return sum;
	}

	private static final class ReinforcementUtility {
		private ReinforcementUtility() {
		}

		/**
		 * Recursively finds maximum element in the array
		 * 
		 * @param array
		 * @param currentIndex
		 * @param largest
		 * @return
		 */
		static int maximumArray(final int[] array, int currentIndex, final int largest) {
			if (currentIndex == 0)
				return array[currentIndex];
			else {
				final int maximum = maximumArray(array, currentIndex - 1, array[currentIndex]);
				return (maximum >= array[currentIndex]) ? maximum : array[currentIndex];
			}
		}

		/**
		 * Returns index of the target value if found in the indicated portion of the
		 * data array. This search only considers the array portion from data[low] to
		 * data[high] inclusive.
		 */
		static int binarySearch(final int[] data, final int target, final int low, final int high) {
			if (low > high)
				return -1;
			else {
				final int mid = (low + high) / 2;
				if (target == data[mid])
					return mid;

				else if (target < data[mid]) {
					return binarySearch(data, target, low, mid - 1);
				} else {
					return binarySearch(data, target, mid + 1, high);
				}
			}
		}

		static int stringToInteger(final char[] digits, final int n) {
			if (n == 0) {
				return Integer.parseInt(Character.toString(digits[0]));
			}
			return Integer.parseInt(Integer.toString(stringToInteger(digits, n - 1)) + digits[n]);
		}

		static long nonRecursivePower(final long x, final long n) {
			long base = x;
			long result = 1;
			long exponent = n;
			while (exponent != 0) {
				if ((exponent % 2) != 0) {
					result = (result * base);
				}
				base = (base * base);
				exponent >>= 1;
			}
			return result;
		}

		static int linearSum(final int[] array, int n) {
			if (n == 0)
				return array[n];
			else {
				return (array[n] + linearSum(array, n - 1));
			}
		}
	}
}
