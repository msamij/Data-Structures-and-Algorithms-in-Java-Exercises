import java.io.File;

public final class RecursiveAlgorithms {
	private RecursiveAlgorithms() {
	}

	/**
	 * With a recursive algorithm, we will account for each operation that is
	 * performed based upon the particular activation of the method that manages the
	 * flow of control at the time it is executed. Stated another way, for each
	 * invocation of the method, we only account for the number of operations that
	 * are performed within the body of that activation. We can then account for the
	 * overall number of operations that are executed as part of the recursive
	 * algorithm by taking the sum, over all activations, of the number of
	 * operations that take place during each individual activation. (As an aside,
	 * this is also the way we analyze a nonrecursive method that calls other
	 * methods from within its body.)
	 */

	/**
	 * To compute factorial(n), we see that there are a total of n + 1 activations,
	 * as the parameter decreases from n in the first call, to n − 1 in the second
	 * call, and so on, until reaching the base case with parameter 0.
	 *
	 * <p/>
	 * It is also clear, given an examination of the method body in Code below that
	 * each individual activation of factorial executes a constant number of
	 * operations. Therefore, we conclude that the overall number of operations for
	 * computing factorial(n) is O(n), as there are n + 1 activations, each of which
	 * accounts for O(1) operations.
	 */
	public static int factorial(int n) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException();
		else if (n == 0)
			return 1;
		else
			return n * factorial(n - 1);
	}

	/**
	 * Returns true if the target value is found in the indicated portion of the
	 * data array. This search only considers the array portion from data[low] to
	 * data[high] inclusive.
	 */
	public static boolean binarySearch(int[] data, int target, int low, int high) {
		if (low > high)
			return false;
		else {
			int mid = (low + high) / 2;
			if (target == data[mid])
				return true;

			else if (target < data[mid]) {
				return binarySearch(data, target, low, mid - 1);
			} else {
				return binarySearch(data, target, mid + 1, high);
			}
		}
	}

	public static long diskUsage(File root) {
		long total = root.length(); // start with direct disk usage
		if (root.isDirectory()) { // and if this is a directory
			for (String childname : root.list()) { // then for each child
				File child = new File(root, childname); // compose full path to child
				total += diskUsage(child); // add child’s usage to total
			}
		}
		System.out.println(total + "\t" + root);
		return total;
	}

	/**
	 * Linear recursion can be a useful tool for processing a sequence, such as a
	 * Java array. Suppose, for example, that we want to compute the sum of an array
	 * of n integers. We can solve this summation problem using linear recursion by
	 * observing that if n = 0 the sum is trivially 0, and otherwise it is the sum
	 * of the first n − 1 integers in the array plus the last value in the array.
	 * 
	 * <p/>
	 * For an input of size n, the linearSum algorithm makes n + 1 method calls.
	 * Hence, it will take O(n) time, because it spends a constant amount of time
	 * performing the nonrecursive part of each call. Moreover, we can also see that
	 * the memory space used by the algorithm (in addition to the array) is also
	 * O(n), as we use a constant amount of memory space for each of the n + 1
	 * frames in the trace at the time we make the final recursive call
	 * (with n = 0).
	 */
	public static int linearSum(int[] data, int n) {
		if (n == 0)
			return 0;
		else
			return linearSum(data, n - 1) + data[n - 1];
	}

	/**
	 * We note that whenever a recursive call is made, there will be two fewer
	 * elements in the relevant portion of the array. Eventually a base case is
	 * reached when the condition low < high fails, either because low == high in
	 * the case that n is odd, or because low == high + 1 in the case that n is
	 * even.
	 * 
	 * <p/>
	 * Above argument implies that the algorithm is guranteed to run terminate after
	 * a total of 1 + floor(n/2) recursive calls. Because each call involves
	 * constant amount of work, the entire process run in O(n) time.
	 */
	public static void reverseArray(int[] array, int low, int high) {
		if (low < high) {
			int temp = array[low];
			array[low] = array[high];
			array[high] = temp;
			reverseArray(array, low + 1, high - 1);
		}
	}

	public static class RecursiveAlgorithmsForComputingPowers {
		/**
		 * Computes the value of x raised to the nth power, for nonnegative integer n.
		 * 
		 * <p/>
		 * A recursive call to this version of power(x, n) runs in O(n) time. Its
		 * recursion trace has structure very similar to that of the factorial function,
		 * with the parameter decreasing by one with each call, and constant work
		 * performed at each of n + 1 levels.
		 */
		static double power1(double x, int n) {
			if (n == 0)
				return 1;
			else
				return x * power1(x, n - 1);
		}

		/**
		 * Computes the value of x raised to the nth power, for nonnegative integer n.
		 * 
		 * <p/>
		 * To analyze the running time of the revised algorithm, we observe that the
		 * exponent in each recursive call of method power(x,n) is at most half of the
		 * preceding exponent. As we saw with the analysis of binary search, the number
		 * of times that we can divide n by two before getting to one or less is
		 * O(logn). Therefore, our new formulation of power results in O(logn) recursive
		 * calls. Each individual activation of the method uses O(1) operations
		 * (excluding the recursive call), and so the total number of operations for
		 * computing power(x,n) is O(log n). This is a significant improvement over the
		 * original O(n)-time algorithm.
		 * 
		 * <p/>
		 * The improved version also provides significant saving in reducing the memory
		 * usage. The first version has a recursive depth of O(n), and therefore, O(n)
		 * frames are simultaneously stored in memory. Because the recursive depth of
		 * the improved version is O(log n), its memory usage is O(log n) as well.
		 */
		static double power2(double x, int n) {
			if (n == 0)
				return 1;
			else {
				double partial = power2(x, n / 2);
				double result = partial * partial;
				if (n % 2 == 1)
					result *= x;
				return result;
			}
		}

		static int binarySum(int[] data, int low, int high) {
			if (low > high)
				return 0;
			else if (low == high)
				return data[low];
			else {
				int mid = (low + high) / 2;
				return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
			}
		}
	}
}
