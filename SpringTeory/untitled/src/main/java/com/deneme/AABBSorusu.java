package com.deneme;

public class AABBSorusu {
  /*
   * Bir S string ifade verilcek ve sadece A ve B harflerinden oluşacak
   * Amaç String formatında A..AB..B elde etmek tüm a lar b den önce gelecek
   * BBAAABBAB //AAABBB //3
   * AAABBB
   * Bunuda string ifadeden harfleri silerek sağlayacağız
   * İşlem yapılan String Sadece A veya sadece B den oluşabilir (AAA) (BBB)
   * solution metodu minumum silenen harf sayısını dönecek
   *
   *
   * */
  public static void main(String[] args) {
	System.out.println(solution("BABAABBA"));//AAABB
  }

  public static int solution(String S) {
	int count = 0;
	int aCount = 0;
	int bCount = 0;
	if (S.length() == 0) {
	  return 0;
	}
	for (int i = 0; i < S.length(); i++) {
	  if (S.charAt(i) == 'A') {
		aCount++;
	  } else {
		bCount++;
	  }
	}
	if (aCount == S.length() || bCount == S.length()) {
	  return 0;
	}

	for (int i = 0; i < S.length(); i++) {
	  for (int j = i + 1; j < S.length(); j++) {
		if (S.charAt(i) == 'B' && S.charAt(j) == 'A') {
		  if (i < j) {
			count++;
			S = S.substring(0,i) + S.substring(i + 1);
			//
		  }
		}
		if (S.charAt(i) == 'A' && S.charAt(j) == 'B') {
		  if (i > j) {
			count++;
			S = S.substring(0,i) + S.substring(i + 1);
		  }

		}

	  }

	}
	return count;
  }

}



