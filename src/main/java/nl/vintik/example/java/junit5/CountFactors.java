package nl.vintik.example.java.junit5;

/**
 * See <a href=
 * "https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/">
 * Count Factors</a>
 * 
 * @author evanengelen
 *
 */
public class CountFactors {

	/**
	 * 
	 * @param N
	 *            an integer within the range [1..2,147,483,647].
	 * @return number of factors
	 */
	public static int solution(int N) {
		// insure we dont acceed max integer value, therefore long primitive
		// instead of int
		long i = 1;
		int countFactors = 0;
		// handle 1
		if (N == 1) {
			return N;
		}
		// loop until we reach potential sqare root of N, and add 2 factors
		// every time there is a divisor found
		// this will also count dividing by 1 and number itself
		while (i * i <= N) {
			// check square root, only 1 factor
			if (i * i == N) {
				countFactors++;
			} else if (N % i == 0) { // all the other cases have 2 factors
				countFactors += 2;
			}
			i++;

		}
		return countFactors;
	}
}
