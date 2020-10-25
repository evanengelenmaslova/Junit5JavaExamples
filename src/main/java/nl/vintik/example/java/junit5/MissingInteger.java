package nl.vintik.example.java.junit5;

import java.util.Arrays;

/**
 * This is a demo task.
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given an array A of N integers, returns the smallest positive integer
 * (greater than 0) that does not occur in A.
 * 
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 * 
 * Given A = [1, 2, 3], the function should return 4.
 * 
 * Given A = [−1, −3], the function should return 1.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * N is an integer within the range [1..100,000]; each element of array A is an
 * integer within the range [−1,000,000..1,000,000].
 * 
 * @author evanengelen
 *
 */
public class MissingInteger {
	/**
	 * N is an integer within the range [1..100,000]; each element of array A is
	 * an integer within the range [−1,000,000..1,000,000].
	 * 
	 * @param A
	 *            array of N integers
	 * @return result
	 */
	public static int solution(int[] A) {
		if (A != null && A.length > 0) {
			// sort array
			Arrays.sort(A);

			// check edge elements, if first element is greater than 1 than 1 is
			// missing
			// if all negatives (last element < 1, then 1 is missing
			if (A[0] > 1 || A[A.length - 1] < 1) {
				return 1;
			}

			boolean firstPositiveChecked = false;
			for (int i = 0; i < A.length - 1; i++) {
				// loop until first positive is found
				// if first positive integer <> 1 then 1 is the missing integer
				if (A[i] > 0) {
					if (!firstPositiveChecked) {
						if (A[i] != 1) {
							return 1;
						}
						// flag that first positive has been checked
						firstPositiveChecked = true;
					}
					// if first positive was 1 then search for missing element
					if (A[i + 1] - A[i] > 1) {
						return A[i] + 1;

					}
				}
			}
			// missing element not found, therefore
			// check last element,
			if (!firstPositiveChecked) {// if there was no positive foud yet
				if (A[A.length - 1] != 1) {// if last element is not 1 then 1 is
											// missing
					return 1;
				}
			}
			// still no missing int found, therefore int at the end of the
			// sequence is missing
			// last element + 1
			return A[A.length - 1] + 1;

		}
		//return default 1
		return 1;
	}
}
