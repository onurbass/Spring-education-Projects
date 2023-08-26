package com.deneme;

import java.util.HashMap;
import java.util.Map;

public class _09Decimal {
  /*
   * 0-9 arası sayılardan oluşan String ifade alacak bir metot
   * metot amacı gelen ifadeyi palindrom yapmak
   * sayının içinde 0 olamaz ilk başta tüm sıfırları sileceğiz***
   * sayı olabilecek en büyük ifade olacak 9838 varsa bile cevap 898 olmalı
   *39878 -> 898
   * 00900 9
   *54321
   *
   * */
  public static String solution(String S) {
	int i = 0;
	int max = Integer.MIN_VALUE;
	int count1 = 0;
	while (i < S.length()) {
	  if (S.charAt(i) == '0') {
		S = S.substring(0,i) + S.substring(i + 1);
	  } else {
		i++;
	  }
	}
	Map<Character, Integer> charCountMap = new HashMap<>();

	for (char c : S.toCharArray()) {
	  charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
	}

	for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
	  if (entry.getValue() > 1) {
		System.out.println("'" + entry.getKey() + "' karakteri " + entry.getValue() + " kez tekrar ediyor.");
	  }
	}

	return S;
  }

  public static void main(String[] args) {
	solution("39878888");
  }
}
