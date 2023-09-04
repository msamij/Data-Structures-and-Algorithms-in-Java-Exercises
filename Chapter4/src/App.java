import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
    }

    static void experimentalStudies() {
        long startTime = System.currentTimeMillis();

        String answer1 = repeat1('*', 100_000);

        String answer2 = repeat2('*', 100_000); // runs much faster.

        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;

        System.out.println(elapsed);
    }

    static void asymtoticAnalysis() {
        /**
         * In algorithm analysis, we focus on the growth rate of the running time as a
         * function of the input size n, taking a “big-picture” approach. For example,
         * it is often enough just to know that the running time of an algorithm grows
         * proportionally to n. We analyze algorithms using a mathematical notation for
         * functions that disregards constant factors. Namely, we characterize the
         * running times of algorithms by using functions that map the size of the
         * input, n, to values that correspond to the main factor that determines the
         * growth rate in terms of n.
         */
    }

    static void bigOhNotation() {
        /**
         * Let f (n) and g(n) be functions mapping positive integers to positive real
         * numbers. We say that f (n) is O(g(n)) if there is a real constant c > 0 and
         * an integer constant n 0 >= 1 such that f (n) <= c * g(n), for n >= n0.
         * This definition is often referred to as the “big-Oh” notation, for it is
         * sometimes pronounced as “f(n) is big-Oh of g(n)”.
         * 
         * Example: The function 8n + 5 is O(n).
         * Justification: By the big-Oh definition, we need to find a real constant c >
         * 0 and an integer constant n0 >= 1 such that 8n + 5 <= cn for every integer n
         * >= n0. It is easy to see that a possible choice is c = 9 and n0 = 5.
         * Indeed, this is one of infinitely many choices available because there is a
         * trade-off between c and n0. For example, we could rely on constants c = 13
         * and n0 = 1.
         * 
         * The big-Oh notation allows us to say that a function f (n) is “less than or
         * equal to” another function g(n) up to a constant factor and in the asymptotic
         * sense as n grows toward infinity. This ability comes from the fact that the
         * definition uses “<=” to compare f(n) to a g(n) times a constant, c, for the
         * asymptotic cases when n >= n 0. However, it is considered poor taste to say “
         * f (n) <= O(g(n)),” since the big-Oh already denotes the
         * “less-than-or-equal-to” concept. Likewise, although common,
         * it is not fully correct to say “ f (n) = O(g(n)),” with the usual
         * understanding of the “=” relation, because there is no way to make sense of
         * the symmetric statement, “O(g(n)) = f (n).” It is best to say, “ f (n) is
         * O(g(n)).”
         */

    }

    static void bigOmegaNotation() {
        /**
         * Just as the big-Oh notation provides an asymptotic way of saying that a
         * function is “less than or equal to” another function, the following notations
         * provide an asymptotic way of saying that a function grows at a rate that is
         * “greater than or equal to” that of another. Let f(n) and g(n) be functions
         * mapping positive integers to positive real numbers. We say that f (n) is
         * Ω(g(n)), pronounced “f(n) is big-Omega of g(n),” if g(n) is O(f(n)), that
         * is, there is a real constant c > 0 and an integer constant n0 ≥ 1 such that
         * f(n) ≥ cg(n), for n ≥ n0. This definition allows us to say asymptotically
         * that one function is greater than or equal to another, up to a constant
         * factor.
         * 
         * Example: 3nlogn − 2n is Ω(nlog n) .
         * Justification: 3nlogn − 2n = nlogn + 2n(logn − 1) ≥ nlog n for n ≥ 2;
         * hence, we can take c = 1 and n0 = 2 in this case.
         */
    }

    static void bigThetaNotation() {
        /**
         * In addition, there is a notation that allows us to say that two functions
         * grow at the same rate, up to constant factors. We say that f(n) is Θ(g(n)),
         * pronounced“ f(n) is big-Theta of g(n),” if f(n) is O(g(n)) and f(n) is
         * Ω(g(n)), that is, there are real constants c′ > 0 and c′′ > 0, and an integer
         * constant n 0 ≥ 1 such that c′ g(n) ≤ f (n) ≤ c′′ g(n), for n ≥ n0.
         * 
         * Example: 3nlog n + 4n + 5 logn is Θ(n log n) .
         * Justification: 3nlog n ≤ 3nlogn + 4n + 5 log n ≤ (3 + 4 + 5) nlogn for n
         * ≥ 2.
         */
    }

    /**
     * Uses repeated concatenation to compose a String with n copies of character c.
     * 
     * The most important aspect of this implementation is that strings in Java are
     * immutable objects. Once created, an instance cannot be modified. The command,
     * answer += c, is shorthand for answer = (answer + c). This command does not
     * cause a new character to be added to the existing String instance; instead it
     * produces a new String with the desired sequence of characters, and then it
     * reassigns the variable, answer, to refer to that new string. In terms of
     * efficiency, the problem with this interpretation is that the creation
     * of a new string as a result of a concatenation, requires time that is
     * proportional to the length of the resulting string. The first time through
     * this loop, the result has length 1, the second time through the loop the
     * result has length 2, and so on, until we reach the final string of length n.
     * Therefore, the overall time taken by this algorithm is proportional to
     * 1 + 2 + · · · + n, which we recognize as the familiar O(n^2) Therefore, the
     * total time complexity of the repeat1 algorithm is O(n^2).
     */
    static String repeat1(char c, int n) {
        String answer = "";
        for (int j = 0; j < n; j++)
            answer += c;
        return answer;
    }

    /**
     * Uses StringBuilder to compose a String with n copies of character c.
     * 
     * In contrast, the running time for the repeat2 algorithm, which
     * uses Java’s StringBuilder class, demonstrate a trend of approximately
     * doubling each time the problem size doubles. The StringBuilder class relies
     * on an advanced technique with a worst-case running time of O(n) for composing
     * a string of length n.
     */
    static String repeat2(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++)
            sb.append(c);
        return sb.toString();
    }

    /**
     * The algorithm, arrayMax , for computing the maximum element of an array of n
     * numbers, runs in O(n) time.
     * 
     * <p>
     * Justification: The initialization at lines 3 and 4 and the return statement
     * at line 8 require only a constant number of primitive operations. Each
     * iteration of the loop also requires only a constant number of primitive
     * operations, and the loop executes n − 1 times. Therefore, we account for the
     * number of primitive operations being c′ * (n−1) + c′′ for appropriate
     * constants c′ and c′′ that reflect, respectively, the work performed inside
     * and outside the loop body. Because each primitive operation runs in constant
     * time, we have that the running time of algorithm arrayMax on an input of size
     * n is at most c′ · (n − 1) + c′′ = c′ * n + (c′′ − c′ ) <= c′ * n if we
     * assume, without loss of generality, that c′′ <= c′ . We conclude that the
     * running time of algorithm arrayMax is O(n).
     */
    static double arrayMax(double[] data) {
        int n = data.length;
        double currentMax = data[0];
        for (int j = 1; j < n; j++) {
            if (data[j] > currentMax) {
                currentMax = data[j];
            }
        }
        return currentMax;
    }

    static void threeWayDisjointness() {
        /**
         * Suppose we are given three sets, A, B, and C, stored in three different
         * integer arrays. We will assume that no individual set contains duplicate
         * values, but that there may be some numbers that are in two or three of the
         * sets. The three-way set disjointness problem is to determine if the
         * intersection of the three sets is empty, namely, that there is no element x
         * such that x ∈ A, x ∈ B, and x ∈ C. A simple Java method to determine this
         * property is given in Code disjoint1.
         */
        class DisjointAlgorithm {
            /**
             * Returns true if there is no element common to all three arrays.
             * 
             * This simple algorithm loops through each possible triple of values from the
             * three sets to see if those values are equivalent. If each of the original
             * sets has size n, then the worst-case running time of this method is O(n^3).
             */
            static boolean disjoint1(int[] groupA, int[] groupB, int[] groupC) {
                for (int a : groupA) {
                    for (int b : groupB) {
                        for (int c : groupC) {
                            if ((a == b) && (b == c))
                                return false;
                        }
                    }
                }
                return true;
            }

            /**
             * We can improve upon the asymptotic performance with a simple observation.
             * Once inside the body of the loop over B, if selected elements a and b do not
             * match each other, it is a waste of time to iterate through all values of C
             * looking for a matching triple. An improved solution to this problem, taking
             * advantage of this observation, is presented in Code.
             * 
             * In the improved version, it is not simply that we save time if we get lucky.
             * We claim that the worst-case running time for disjoint2 is O(n^2).
             */
            static boolean disjoint2(int[] groupA, int[] groupB, int[] groupC) {
                for (int a : groupA) {
                    for (int b : groupB) {
                        if (a == b) { // only check C when we find match from A and B
                            for (int c : groupC) {
                                if (a == c)
                                    return false;
                            }
                        }
                    }
                }
                return true;
            }
        }
    }

    static void elementUniqueness() {
        /**
         * A problem that is closely related to the three-way set disjointness problem
         * is the element uniqueness problem. In the former, we are given three sets and
         * we presumed that there were no duplicates within a single set. In the element
         * uniqueness problem, we are given an array with n elements and asked whether
         * all elements of that collection are distinct from each other.
         */
        class UniqueElementAlgorithm {
            /**
             * Our first solution to this problem uses a straightforward iterative
             * algorithm. The unique1 method, given in Code, solves the element uniqueness
             * problem by looping through all distinct pairs of indices j < k, checking if
             * any of those pairs refer to elements that are equivalent to each other. It
             * does this using two nested for loops, such that the first iteration of the
             * outer loop causes n − 1 iterations of the inner loop, the second iteration of
             * the outer loop causes n − 2 iterations of the inner loop, and so on. Thus,
             * the worst-case running time of this method is proportional to
             * (n − 1) + (n − 2) + · · · + 2 + 1, which we recognize as the familiar O(n^2).
             */
            static boolean unique1(int[] data) {
                int n = data.length;
                for (int j = 0; j < n - 1; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (data[j] == data[k])
                            return false;
                    }
                }
                return true;
            }

            /**
             * An even better algorithm for the element uniqueness problem is based on using
             * sorting as a problem-solving tool. In this case, by sorting the array of
             * elements, we are guaranteed that any duplicate elements will be placed next
             * to each other. Thus, to determine if there are any duplicates, all we need to
             * do is perform a single pass over the sorted array, looking for consecutive
             * duplicates.
             * 
             * The best sorting algorithms (including those used by Array.sort in Java)
             * guarantee a worst-case running time of O(nlogn). Once the data is sorted,
             * the subsequent loop runs in O(n) time, and so the entire unique2 algorithm
             * runs in O(nlogn) time.
             */
            static boolean unique2(int[] data) {
                int n = data.length;
                int[] temp = Arrays.copyOf(data, n);
                Arrays.sort(temp);

                for (int j = 0; j < n - 1; j++) {
                    if (temp[j] == temp[j + 1])
                        return false;
                }
                return true;
            }
        }
    }

    static void prefixAverages() {
        /** */
    }
}
