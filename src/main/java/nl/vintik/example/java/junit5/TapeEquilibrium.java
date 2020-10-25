package nl.vintik.example.java.junit5;

/**
 * A non-empty array A consisting of N integers is given. Array A represents
 * numbers on a tape.
 * 
 * Any integer P, such that 0 < P < N, splits this tape into two non-empty
 * parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
 * 
 * The difference between the two parts is the value of: |(A[0] + A[1] + ... +
 * A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
 * 
 * In other words, it is the absolute difference between the sum of the first
 * part and the sum of the second part.
 * 
 * For example, consider array A such that:
 * 
 * A[0] = 3 A[1] = 1 A[2] = 2 A[3] = 4 A[4] = 3 We can split this tape in four
 * places:
 * 
 * P = 1, difference = |3 − 10| = 7 P = 2, difference = |4 − 9| = 5 P = 3,
 * difference = |6 − 7| = 1 P = 4, difference = |10 − 3| = 7 Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a non-empty array A of N integers, returns the minimal difference
 * that can be achieved.
 * 
 * For example, given:
 * 
 * A[0] = 3 A[1] = 1 A[2] = 2 A[3] = 4 A[4] = 3 the function should return 1, as
 * explained above.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * N is an integer within the range [2..100,000]; each element of array A is an
 * integer within the range [−1,000..1,000].
 * 
 * @author evanengelen
 *
 */
public class TapeEquilibrium {

	/**
	 * assumptions:
	 * 
	 * N is an integer within the range [2..100,000]; each element of array A is
	 * an integer within the range [−1,000..1,000].
	 * 
	 * @param A
	 *            non-empty array, has min 2 elements
	 * @return result
	 */
	public static int solution(int[] A) {
		int minimumDifference = Integer.MAX_VALUE;
		int currentSumLeft = 0;

		// calculate total sum of the array
		// used a simple for loop instead of Arrays.stream.sum as it saved performance
		int totalSum = 0;
		for (int x : A) {
			totalSum += x;
		}

		for (int i = 0; i < A.length - 1; i++) {
			// keep adding to current sum on the left
			currentSumLeft += A[i];
			// calculate current sum right from total sum - current sum left
			int currentSumRight = totalSum - currentSumLeft;
			// save the difference if its less than the difference we calculated
			// earlier
			minimumDifference = Math.min(minimumDifference, Math.abs(currentSumLeft - currentSumRight));
		}
		return minimumDifference;
	}
}
