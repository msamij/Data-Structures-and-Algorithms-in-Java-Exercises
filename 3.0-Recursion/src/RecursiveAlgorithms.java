import java.io.File;
import java.io.FileNotFoundException;

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
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0)
			return 1;
		else {
			return n * factorial(n - 1);
		}
	}

	/** Returns true if the target value is found in the data array. */
	public static boolean binarySearch(int[] data, int target) {
		return binarySearch(data, target, 0, data.length - 1);
	}

	/**
	 * Returns true if the target value is found in the indicated portion of the
	 * data array. This search only considers the array portion from data[low] to
	 * data[high] inclusive.
	 */
	private static boolean binarySearch(int[] data, int target, int low, int high) {
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

	public static long diskUsage(File root) throws FileNotFoundException {
		if (!root.exists()) {
			throw new FileNotFoundException(root.getName() + " file or directory doesn't exists.");
		}

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
		else {
			return linearSum(data, n - 1) + data[n - 1];
		}
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

	public static final class RecursiveAlgorithmsForComputingPowers {
		/**
		 * Computes the value of x raised to the nth power, for nonnegative integer n.
		 * 
		 * <p/>
		 * A recursive call to this version of power(x, n) runs in O(n) time. Its
		 * recursion trace has structure very similar to that of the factorial function,
		 * with the parameter decreasing by one with each call, and constant work
		 * performed at each of n + 1 levels.
		 */
		public static double power1(double x, int n) {
			if (n == 0)
				return 1;
			else {
				return x * power1(x, n - 1);
			}
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
		 * original O(n) time algorithm.
		 * 
		 * <p/>
		 * The improved version also provides significant saving in reducing the memory
		 * usage. The first version has a recursive depth of O(n), and therefore, O(n)
		 * frames are simultaneously stored in memory. Because the recursive depth of
		 * the improved version is O(log n), its memory usage is O(log n) as well.
		 */
		public static double power2(double x, int n) {
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

		/**
		 * Returns the sum of subarray data[low] through data[high] inclusive.
		 * 
		 * <p/>
		 * To analyze algorithm binarySum, we consider, for simplicity, the case where
		 * n is a power of two. The size of the range is divided in half at each
		 * recursive call, and so the depth of the recursion is 1 + log2(n). Therefore,
		 * binarySum uses O(logn) amount of additional space, which is a big improvement
		 * over the O(n) space used by the linearSum method. However, the running time
		 * of binarySum is O(n), as there are 2n − 1 method calls, each requiring
		 * constant time.
		 */

		public static int binarySum(int[] data, int low, int high) {
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

	public static final class RecursionRunAmok {
		/**
		 * Although recursion is a very powerful tool, it can easily be misused in
		 * various ways. In this section, we examine several cases in which a poorly
		 * implemented recursion causes drastic inefficiency, and we discuss some
		 * strategies for recognizing and avoid such pitfalls. We begin by revisiting
		 * the element uniqueness problem, We can use the following recursive
		 * formulation to determine if all n elements of a sequence are unique. As a
		 * base case, when n = 1, the elements are trivially unique. For n ≥ 2, the
		 * elements are unique if and only if the first n − 1 elements are unique, the
		 * last n − 1 items are unique, and the first and last elements are different
		 * (as that is the only pair that was not already checked as a subcase).
		 * A recursive implementation based on this idea is given in Code named unique3
		 * (to differentiate it from unique1 and unique2 from Chapter 4).
		 * 
		 * Unfortunately, this is a terribly inefficient use of recursion. The
		 * nonrecursive part of each call uses O(1) time, so the overall running time
		 * will be proportional to the total number of recursive invocations. To analyze
		 * the problem, we let n denote the number of entries under consideration, that
		 * is, let n = 1 + high − low. If n = 1, then the running time of unique3 is
		 * O(1), since there are no recursive calls for this case. In the general case,
		 * the important observation is that a single call to unique3 for a problem of
		 * size n may result in two recursive calls on problems of size n − 1. Those two
		 * calls with size n − 1 could in turn result in four calls (two each) with a
		 * range of size n − 2, and thus eight calls with size n − 3 and so on. Thus, in
		 * the worst case, the total number of method calls is given by the geometric
		 * summation 1 + 2 + 4 + · · · + 2 n − 1 , which is equal to 2^(n − 1) Thus, the
		 * running time of method unique3 is O(2^n). This is an incredibly inefficient
		 * method for solving the element uniqueness problem. Its inefficiency comes not
		 * from the fact that it uses recursion it comes from the fact that it uses
		 * recursion poorly.
		 */

		/**
		 * Returns true if there are no duplicate values from data[low] through
		 * data[high].
		 */
		public static boolean unique3(int[] data, int low, int high) {
			if (low >= high) {
				return true; // at most one item
			} else if (!unique3(data, low, high - 1)) {
				return false; // duplicate in first n − 1
			} else if (!unique3(data, low + 1, high)) {
				return false; // duplicate in last n − 1
			} else {
				return (data[low] != data[high]); // do first and last differ?
			}
		}

		/**
		 * Unfortunately, such a direct implementation of the Fibonacci formula results
		 * in a terribly inefficient method. Computing the n th Fibonacci number in this
		 * way requires an exponential number of calls to the method. Specifically, let
		 * cn denote the number of calls performed in the execution of fibonacciBad(n).
		 * Then, we have the following values for the cn’s:
		 * 
		 * <p/>
		 * c0 = 1
		 * 
		 * <p/>
		 * c1 = 1
		 * 
		 * <p/>
		 * c2 = 1 + c0 + c1 = 1 + 1 + 1 = 3
		 * 
		 * <p/>
		 * c3 = 1 + c1 + c2 = 1 + 1 + 3 = 5
		 * 
		 * <p/>
		 * c4 = 1 + c2 + c3 = 1 + 3 + 5 = 9
		 * 
		 * <p/>
		 * c5 = 1 + c3 + c4 = 1 + 5 + 9 = 15
		 * 
		 * <p/>
		 * 
		 * c6 = 1 + c4 + c5 = 1 + 9 + 15 = 25
		 * 
		 * <p/>
		 * c7 = 1 + c5 + c6 = 1 + 15 + 25 = 41
		 * 
		 * <p/>
		 * c8 = 1 + c6 + c7 = 1 + 25 + 41 = 67
		 * 
		 * <p/>
		 * If we follow the pattern forward, we see that the number of calls more than
		 * doubles for each two consecutive indices. That is, c4 is more than twice c2,
		 * c5 is more than twice c3 , c6 is more than twice c4 , and so on. Thus,
		 * (cn > 2^n/2) , which means that fibonacciBad(n) makes a number of calls that
		 * is exponential in n.
		 * 
		 * <p/>
		 *
		 * Returns the nth Fibonacci number (inefficiently).
		 */
		public static long fibonacciBad(int n) {
			if (n <= 1) {
				return n;
			} else {
				return fibonacciBad(n - 2) + fibonacciBad(n - 1);
			}
		}

		/**
		 * We were tempted into using the bad recursive formulation because of the way
		 * the nth Fibonacci number, Fn , depends on the two previous values, Fn−2
		 * and Fn−1 . But notice that after computing Fn−2 , the call to compute Fn−1
		 * requires its own recursive call to compute Fn−2 , as it does not have
		 * knowledge of the value of Fn−2 that was computed at the earlier level of
		 * recursion. That is duplicative work. Worse yet, both of those calls will need
		 * to (re)compute the value of Fn−3 , as will the computation of Fn−1 . This
		 * snowballing effect is what leads to the exponential running time of
		 * fibonacciBad.
		 * 
		 * <p/>
		 * In terms of efficiency, the difference between the bad and good recursions
		 * for this problem is like night and day. The fibonacciBad method uses
		 * exponential time. We claim that the execution of method fibonacciGood(n) runs
		 * in O(n) time. Each recursive call to fibonacciGood decreases the argument n
		 * by 1, therefore, a recursion trace includes a series of n method calls.
		 * Because the nonrecursive work for each call uses constant time, the overall
		 * computation executes in O(n) time.
		 * 
		 * <p/>
		 * Returns array containing the pair of Fibonacci numbers, F(n) and F(n - 1).
		 */
		public static long[] fibonacciGood(int n) {
			if (n <= 1) {
				long[] answer = { n, 0 };
				return answer;

			} else {
				long[] temp = fibonacciGood(n - 1); // returns {Fn − 1 , Fn − 2 }
				long[] answer = { temp[0] + temp[1], temp[0] }; // we want {Fn , Fn − 1 }
				return answer;
			}
		}
	}
}
