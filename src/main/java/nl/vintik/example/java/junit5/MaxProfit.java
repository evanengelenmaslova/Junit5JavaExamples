package nl.vintik.example.java.junit5;

/**
 * See <a href=
 * "https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/">
 * Max Profit</a>
 * 
 * @author evanengelen
 *
 */
public class MaxProfit {

	public static int solution(int[] A) {
		int max = -1;
		int maxProfit = -1;
		// loop from end
		// substruct from last, one before, if one before last is bigger, use
		// that one
		if (A != null && A.length > 1) {
			for (int i = A.length - 1; i > 0; i--) {
				max = Math.max(max, A[i]);
				int profit = max - A[i - 1];
				maxProfit = Math.max(maxProfit, profit);
			}
		}
		return Math.max(maxProfit , 0);
	}

}
