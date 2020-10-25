package nl.vintik.example.java.junit5;

/**
 * A non-empty array A consisting of N integers is given. The consecutive
 * elements of array A represent consecutive cars on a road.
 * 
 * Array A contains only 0s and/or 1s:
 * 
 * 0 represents a car traveling east, 1 represents a car traveling west. The
 * goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P
 * < Q < N, is passing when P is traveling to the east and Q is traveling to the
 * west.
 * 
 * For example, consider array A such that:
 * 
 * A[0] = 0 A[1] = 1 A[2] = 0 A[3] = 1 A[4] = 1 We have five pairs of passing
 * cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).
 * 
 * Write a function:
 * 
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a non-empty array A of N integers, returns the number of pairs of
 * passing cars.
 * 
 * The function should return −1 if the number of pairs of passing cars exceeds
 * 1,000,000,000.
 * 
 * For example, given:
 * 
 * A[0] = 0 A[1] = 1 A[2] = 0 A[3] = 1 A[4] = 1 the function should return 5, as
 * explained above.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * N is an integer within the range [1..100,000]; each element of array A is an
 * integer that can have one of the following values: 0, 1.
 * 
 * @author evanengelen
 *
 */
public class PassingCars {
	/**
	 * A non-empty array A consisting of N integers is given. N is an integer
	 * within the range [1..100,000]; each element of array A is an integer that
	 * can have one of the following values: 0, 1.
	 * 
	 * We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P
	 * = 0 and Q = 1
	 * 
	 * @param A
	 *            contains only 0s and/or 1s
	 * @return result
	 */
	public static int solution(int[] A) {
		// total
		int countPassing = 0;
		// 0s
		int carsToPass = 0;
		// 1s

		// extreme small array, return 0 when only one element
		if (A == null || A.length < 2) {
			return countPassing;
		}
		for (int j : A) {
			// incrementally add cars to be passed
			if (j == 0) {
				carsToPass += 1;
			}
			if (j == 1) { // ones met passing car, add all cars to be passed
				// to the total
				countPassing += carsToPass;
			}
			// when acceeds limit, retunr -1
			if (countPassing > 1_000_000_000) {
				return -1;
			}
		}
		return countPassing;
	}

}
