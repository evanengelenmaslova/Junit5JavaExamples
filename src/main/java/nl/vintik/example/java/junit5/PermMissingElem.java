package nl.vintik.example.java.junit5;

import java.util.Arrays;

/**
 * An array A consisting of N different integers is given. The array contains
 * integers in the range [1..(N + 1)], which means that exactly one element is
 * missing.
 * <p>
 * Your goal is to find that missing element.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A, returns the value of the missing element.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 2 A[1] = 3 A[2] = 1 A[3] = 5 the function should return 4, as it is
 * the missing element.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000]; the elements of A are all
 * distinct; each element of array A is an integer within the range [1..(N +
 * 1)].
 *
 * @author evanengelen
 */
public class PermMissingElem {
    /**
     * An array A consisting of N different integers is given. The array
     * contains integers in the range [1..(N + 1)], which means that exactly one
     * element is missing.
     * <p>
     * Assumptions:
     * <p>
     * N is an integer within the range [0..100,000]; the elements of A are all
     * distinct; each element of array A is an integer within the range [1..(N +
     * 1)].
     *
     * @param A is array of integers within the range [1..(N +
     *          * 1)]
     * @return int result
     */
    public static int solution(int[] A) {
        // sort array in ascending order
        Arrays.sort(A);
        int result = 0;

        // when array is empty then missing element is always 1 (N = 0, N + 1 =
        // 1)
        if (A.length == 0) {
            result = 1;
        }

        // for non empty array, loop through each element
        // check that each element has a correct value (A[index] = index +1)
        // if value is not correct at index then we found missing value -
        // save missing value (index + 1) and stop loop
        // if we reached the end of the array
        // then the missing element is the biggest possible value (N + 1)
        int N = A.length;
        for (int i = 0; i <= N; i++) {
            if (i == N) {
                result = N + 1;
                break;
            } else if (A[i] > i + 1) {
                result = i + 1;
                break;
            }
        }

        return result;
    }
}
