package com.deneme;

/*
 * 0-9 arası sayılardan oluşan String ifade alacak bir metot
 * metot amacı gelen ifadeyi palindrom yapmak
 * sayının içinde 0 olamaz ilk başta tüm sıfırları sileceğiz***
 * sayı olabilecek en büyük ifade olacak 9838 varsa bile cevap 898 olmalı
 *3398798 -> 898
 * 00900 9
 *54321
 *
 * */
// 0-9 arası sayılar için tutulan dizi
//yapacağımız şey tekrar eden sayıları elimizde tutmak ve tekrar edenlerden en büyüğünü bulmak. tekrar etmeyen sayılardan en büyüğünü bulmak
//eğer tekrar yoksa tekrar eden en büyük olanı dönmek
public class Soru2 {

  public String solution(String S) {

	int max = Integer.MIN_VALUE;
	int count = 0;
	S = S.replaceAll("0","");

	int[] counts = new int[10];

	for (int i = 0; i < S.length(); i++) {
	  char digitChar = S.charAt(i);
	  int digit = Character.getNumericValue(digitChar);
	  counts[digit]++;
	}

	for (int i = 0; i < counts.length; i++) {
	  int evenCount = counts[i];
	  if (evenCount % 2 != 0) {
		max = i;
	  }else{

	  }
	}
	System.out.println("Max sayı = "+max);
	return S;
  }

  public static void main(String[] args) {
	Soru2 solution = new Soru2();
	solution.solution("388886666");
  }
}
