/*public int solution(int[] A, int K) {
   int maxNails = 0;  // To store the maximum number of nails at the same length

   int left = 0;  // Left pointer of the sliding window
   int right = 0;  // Right pointer of the sliding window

   while (right < A.length) {
	 // Calculate the number of nails needed to be hammered down to make all nails in the window equal
	 int hammerCount = (right - left + 1) * (A[right] - A[right - 1]);

	 // If the current K allows hammering down the nails within the window, update maxNails
	 while (hammerCount > K) {
	   hammerCount -= A[right] - A[left];
	   left++;
	 }

	 maxNails = Math.max(maxNails, right - left + 1);
	 right++;
   }

   return maxNails;
 }*/
public class Solution {
  public int solution(int[] A, int K) {
	int n = A.length;
	int left = 1; // minimum possible length
	int right = A[n - 1]; // maximum possible length
	int result = 1;

	while (left <= right) {
	  int mid = left + (right - left) / 2;
	  int count = 0;
	  int hammerUsed = 0;

	  for (int i = 0; i < n; i++) {
		if (A[i] >= mid) {
		  count++;
		  i += count - 1; // Jump to the next distinct length
		} else {
		  hammerUsed++;
		}
	  }

	  if (hammerUsed <= K) {
		result = count;
		left = mid + 1;
	  } else {
		right = mid - 1;
	  }
	}

	return result;
  }

  public static void main(String[] args) {
	Solution solution = new Solution();
	int[] A = {1, 1, 3, 3, 3, 4, 5, 5, 5, 5};
	int K = 2;
	System.out.println(solution.solution(A, K)); // Output should be 5
  }
}
