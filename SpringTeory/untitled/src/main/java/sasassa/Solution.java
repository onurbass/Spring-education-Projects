package sasassa;

public class Solution {
  public int solution(int N) {
	int toplam = 0;

	int tempSayi = N;

	while (tempSayi > 0) {
	  int basamak = tempSayi % 10;
	  toplam += basamak;
	  tempSayi /= 10;
	}
	if (toplam == 1) {
	  return N * 10;
	}

	int nextInt = N + 1;

	while (true) {

	  int digitSum = 0;
	  String nextIntStr = Integer.toString(nextInt);
	  for (int i = 0; i < nextIntStr.length(); i++) {
		digitSum += nextIntStr.charAt(i) - '0';
	  }

	  if (digitSum == sumOfDigits(N)) {
		return nextInt;
	  }

	  nextInt++;
	}
  }

  private int sumOfDigits(int N) {
	int digitSum = 0;
	String NStr = Integer.toString(N);
	for (int i = 0; i < NStr.length(); i++) {
	  digitSum += NStr.charAt(i) - '0';
	}
	return digitSum;
  }

  public static void main(String[] args) {
	Solution solution = new Solution();
	System.out.println(solution.solution(28));
	System.out.println(solution.solution(734));
	System.out.println(solution.solution(1990));
	System.out.println(solution.solution(1000));
  }
}
