package nl.vintik.example.java.junit5;

import java.util.Arrays;

/**
 * An array A consisting of N integers is given. The dominator of array A is the
 * value that occurs in more than half of the elements of A.
 * 
 * For example, consider array A such that
 * 
 * A[0] = 3 A[1] = 4 A[2] = 3 A[3] = 2 A[4] = 3 A[5] = -1 A[6] = 3 A[7] = 3 The
 * dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in
 * those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.
 * 
 * Write a function
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given an array A consisting of N integers, returns index of any element
 * of array A in which the dominator of A occurs. The function should return −1
 * if array A does not have a dominator.
 * 
 * For example, given array A such that
 * 
 * A[0] = 3 A[1] = 4 A[2] = 3 A[3] = 2 A[4] = 3 A[5] = -1 A[6] = 3 A[7] = 3 the
 * function may return 0, 2, 4, 6 or 7, as explained above.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * N is an integer within the range [0..100,000]; each element of array A is an
 * integer within the range [−2,147,483,648..2,147,483,647].
 * 
 * @author evanengelen
 *
 */
public class Dominator {

	/**
	 * 
	 * @param A
	 *            array consisting of N integers. N is an integer within the
	 *            range [0..100,000]; each element of array A is an integer
	 *            within the range [−2,147,483,648..2,147,483,647].
	 * @return index of any element of array A in which the dominator of A
	 *         occurs. The function should return −1 if array A does not have a
	 *         dominator.
	 */
	public static int solution(int[] A) {
		int resultIndex = -1;
		int potentialDominatorIndex;
		int potentialDominatorElementCount = 0;
		if (A == null || A.length == 0) {
			return resultIndex;
		}
		if (A.length == 1) { // only element wins
			resultIndex = 0;
			return resultIndex;
		}

		// clone array in order to sort it and thus not loose the original order
		int[] copy = A.clone();
		// sort array so that we can pick the potential dominator
		Arrays.sort(copy);
		// get the middle element - thats a dominator, if we have one
		int middleIndex = copy.length / 2;
		int potentialDominator = copy[middleIndex];
		// loop through the original array and count potential dominator
		for (int i = 0; i < A.length; i++) {
			if (A[i] == potentialDominator) {
				potentialDominatorElementCount++;
				potentialDominatorIndex = i;
				// if we already found a dominator no need to loop further
				if (potentialDominatorElementCount > middleIndex) {
					resultIndex = potentialDominatorIndex;
					break;
				}
			}
		}
		return resultIndex;
	}

}
