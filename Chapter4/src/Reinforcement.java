public final class Reinforcement {
	private Reinforcement() {
	}

	/**
	 * R-5.1 Describe a recursive algorithm for finding the maximum element in an
	 * array, A, of n elements. What is your running time and space usage?
	 */
	public static int reinforcement1(final int[] A, final int n) {
		if (A.length == 0) {
			throw new IllegalArgumentException("Array length must be >= 1.");
		}
		return maximumArray(A, n, A[n]);
	}

	private static int maximumArray(final int[] A, int n, final int largest) {
		if (n == 0)
			return A[n];
		else {
			int maximum = maximumArray(A, n - 1, A[n]);
			return (maximum >= A[n]) ? maximum : A[n];
		}
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
		return binarySearch(data, target, 0, data.length - 1);
	}

	/**
	 * Returns true if the target value is found in the indicated portion of the
	 * data array. This search only considers the array portion from data[low] to
	 * data[high] inclusive.
	 */
	private static int binarySearch(final int[] data, final int target, final int low, final int high) {
		if (low > high)
			return -1;
		else {
			int mid = (low + high) / 2;
			if (target == data[mid])
				return mid;

			else if (target < data[mid]) {
				return binarySearch(data, target, low, mid - 1);
			} else {
				return binarySearch(data, target, mid + 1, high);
			}
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
		return stringToInteger(digits.toCharArray(), digits.length() - 1);
	}

	private static int stringToInteger(final char[] digits, final int n) {
		if (n == 0) {
			return Integer.parseInt(Character.toString(digits[0]));
		}
		return Integer.parseInt(Integer.toString(stringToInteger(digits, n - 1)) + digits[n]);
	}
}
