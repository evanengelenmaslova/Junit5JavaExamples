package nl.vintik.example.java.junit5;

/**
 * You are given N counters, initially set to 0, and you have two possible
 * operations on them:
 * 
 * increase(X) − counter X is increased by 1, max counter − all counters are set
 * to the maximum value of any counter.
 * 
 * A non-empty array A of M integers is given. This array represents consecutive
 * operations:
 * 
 * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X), if A[K] =
 * N + 1 then operation K is max counter.
 * 
 * For example, given integer N = 5 and array A such that: A[0] = 3 A[1] = 4
 * A[2] = 4 A[3] = 6 A[4] = 1 A[5] = 4 A[6] = 4
 * 
 * the values of the counters after each consecutive operation will be: (0, 0,
 * 1, 0, 0) (0, 0, 1, 1, 0) (0, 0, 1, 2, 0) (2, 2, 2, 2, 2) (3, 2, 2, 2, 2) (3,
 * 2, 2, 3, 2) (3, 2, 2, 4, 2)
 * 
 * The goal is to calculate the value of every counter after all operations.
 * 
 * Write a function:
 * 
 * class Solution { public int[] solution(int N, int[] A); }
 * 
 * that, given an integer N and a non-empty array A consisting of M integers,
 * returns a sequence of integers representing the values of the counters.
 * 
 * Result array should be returned as an array of integers.
 * 
 * For example, given: A[0] = 3 A[1] = 4 A[2] = 4 A[3] = 6 A[4] = 1 A[5] = 4
 * A[6] = 4
 * 
 * the function should return [3, 2, 2, 4, 2], as explained above.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * N and M are integers within the range [1..100,000]; each element of array A
 * is an integer within the range [1..N + 1].
 * 
 * 
 * @author evanengelen
 *
 */
public class MaxCounters {
	/**
	 * 
	 * @param N
	 *            integer within the range [1..100,000]
	 * @param A
	 *            non-empty array of M integers. M is an integer within the
	 *            range [1..100,000]
	 * @return result
	 */
	public static int[] solution(int N, int[] A) {
		if (A.length == 0) {
			throw new IllegalArgumentException("array must not be empty");
		}
		int[] result = new int[N];
		int currentCounterMax = 0;
		int usedCounterMax = 0;

		for (int counterIndex : A) {
			if (counterIndex == N + 1 && currentCounterMax > 0 && currentCounterMax > usedCounterMax) {
				// keep track of the max that should be set on all elements
				// (usedCounterMax)
				// instead of setting it to save performance
				usedCounterMax = currentCounterMax;
			} else if ((counterIndex >= 1) && (counterIndex <= N)) {
				// because we are not setting the current max on all elements
				// we need to increment the max set (usedCounterMax) or the
				// counter,
				// which ever is the greatest
				int incrementedValue = Math.max(result[counterIndex - 1], usedCounterMax) + 1;
				result[counterIndex - 1] = incrementedValue;

				// set the new current max
				currentCounterMax = Math.max(currentCounterMax, incrementedValue);
			}

		}
		// only set all elements to max if there was a counterIndex == N + 1
		if (usedCounterMax > 0) {
			for (int j = 0; j < result.length; j++) {
				int currentCounterValue = result[j];
				// only override the value with the set max (usedCounterMax) if
				// the current counter is smaller
				if (currentCounterValue < usedCounterMax) {
					result[j] = usedCounterMax;
				}
			}
		}
		return result;
	}

}
