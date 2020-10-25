package nl.vintik.example.java.junit5;

import java.util.stream.IntStream;

/**
 * You are given integers K, M and a non-empty array A consisting of N integers.
 * Every element of the array is not greater than M.
 * 
 * You should divide this array into K blocks of consecutive elements. The size
 * of the block is any integer between 0 and N. Every element of the array
 * should belong to some block.
 * 
 * The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum
 * of empty block equals 0.
 * 
 * The large sum is the maximal sum of any block.
 * 
 * For example, you are given integers K = 3, M = 5 and array A such that:
 * 
 * A[0] = 2 A[1] = 1 A[2] = 5 A[3] = 1 A[4] = 2 A[5] = 2 A[6] = 2 The array can
 * be divided, for example, into the following blocks:
 * 
 * [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15; [2], [1, 5, 1, 2], [2,
 * 2] with a large sum of 9; [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
 * [2, 1], [5, 1], [2, 2, 2] with a large sum of 6. The goal is to minimize the
 * large sum. In the above example, 6 is the minimal large sum.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int K, int M, int[] A); }
 * 
 * that, given integers K, M and a non-empty array A consisting of N integers,
 * returns the minimal large sum.
 * 
 * For example, given K = 3, M = 5 and array A such that:
 * 
 * A[0] = 2 A[1] = 1 A[2] = 5 A[3] = 1 A[4] = 2 A[5] = 2 A[6] = 2 the function
 * should return 6, as explained above.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * N and K are integers within the range [1..100,000]; M is an integer within
 * the range [0..10,000]; each element of array A is an integer within the range
 * [0..M].
 * 
 * @author evanengelen
 *
 */
public class MinMaxDivision {

	/**
	 * @param K
	 *            is integer within the range [1..100,000], indicates how many
	 *            blocks to divide array into
	 * @param M
	 *            is an integer within the range [0..10,000]. Every element of
	 *            the array A is not greater than M.
	 * 
	 * 
	 * @param A
	 *            non-empty array consisting of N integers, N is an integer
	 *            within the range [1..100,000], each element of array A is an
	 *            integer within the range [0..M].
	 * 
	 * @return the minimal large sum
	 */
	public static int solution(int K, int M, int[] A) {
		int minLargeSum = 0;
		if (A != null && A.length > 0) {
			// handle array with 1 element and 1 block case
			if (A.length == 1) {
				minLargeSum = A[0];
				return minLargeSum;
			}
			if (K == 1) {
				minLargeSum = IntStream.of(A).sum();
				return minLargeSum;
			}
			// calculate min and max of the array
			// min is the largest single element, it is not the same as M
			// because:
			// "Every element of the array A is not greater than M.",
			// so M does not represent the largest element just that largest
			// elemet is not greater than M
			int min = IntStream.of(A).max().getAsInt();
			// max is the sum of all elements
			int max = IntStream.of(A).sum();

			// binary search for the best min large sum
			while (min <= max) {
				int mid = (min + max) / 2;
				if (isDivisible(K, mid, A)) {
					max = mid - 1;
					minLargeSum = mid;
				} else {
					min = mid + 1;
				}
			}

		}
		return minLargeSum;
	}

	/**
	 * Check whether array is divisible, such that large sum is not > mid
	 * 
	 * @param K
	 *            how many blocks are allowed
	 * @param mid
	 *            current middle large sum
	 * @param A
	 *            array
	 * @return true if divisible, otherwise false
	 */
	public static boolean isDivisible(int K, int mid, int[] A) {
		int blockSum = 0;
		for (int j : A) {
			blockSum += j;
			if (blockSum > mid) {
				blockSum = j;
				K--;
			}
			if (K == 0) { // run out of block so not divisible
				return false;
			}
		}
		return true;
	}

}
