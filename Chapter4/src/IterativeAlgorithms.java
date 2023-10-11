public final class IterativeAlgorithms {
	private IterativeAlgorithms() {
	}

	/** Returns true if the target value is found in the data array. */
	public static boolean binarySearch(int[] data, int target) {
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (target == data[mid]) // found a match
				return true;
			else if (target < data[mid]) {// only consider values left of mid
				high = mid - 1;
			} else {
				low = mid + 1; // only consider values right of mid
			}
		}
		return false; // loop ended without success
	}

	/**
	 * Computes factorial in an iterative way.
	 * 
	 * @param n
	 * @return factorial of argument n.
	 * @throws IllegalArgumentException
	 */
	public static int factorial(int n) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException();
		int multiple = n;
		int factorial = 1;
		while (multiple != 1) {
			factorial = (factorial * multiple);
			multiple -= 1;
		}
		return factorial;
	}

	/** Reverses the contents of the given array. */
	public static void reverseArray(int[] data) {
		int low = 0, high = data.length - 1;
		while (low < high) {
			int temp = data[low];
			data[low++] = data[high];
			data[high--] = temp;
		}
	}

	/**
	 * 
	 * @param n must be non-negative and non-zero.
	 * @return nth fibonacci number.
	 * @throws IllegalArgumentException if n <= 0
	 */
	public static int fibonacci(int n) throws IllegalArgumentException {
		if (n <= 0)
			throw new IllegalArgumentException();
		else if (n == 1)
			return 0;
		else if (n == 2 || n == 3)
			return 1;
		int result = 0;
		int fibPrev = 0;
		int fibNext = 1;
		for (int i = 2; i < n; i++) {
			result = (fibPrev + fibNext);
			fibPrev = fibNext;
			fibNext = result;
		}
		return result;
	}

	/**
	 * 
	 * @param data
	 * @param n
	 * @return sum of the data array.
	 */
	public static int linearSum(int[] data, int n) {
		if (n == 0)
			return 0;
		int result = 0;
		for (int i = 0; i < data.length; i++) {
			result += data[i];
		}
		return result;
	}
}
