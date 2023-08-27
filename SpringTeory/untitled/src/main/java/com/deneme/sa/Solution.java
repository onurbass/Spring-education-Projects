package com.deneme.sa;

public class Solution {
  public String solution(String S) {
	// Adım 1: Tüm sıfırları sil
	S = S.replaceAll("0", "");

	// Adım 2: En büyük karakterin bir önceki sayıyı bul
	char maxChar = '0';
	for (char c : S.toCharArray()) {
	  if (c > maxChar) {
		maxChar = c;
	  }
	}
	int targetNumber = maxChar - '0' - 1;

	// Adım 3: Tekrar eden sayıları bul
	StringBuilder repeatedNumbers = new StringBuilder();
	for (char c : S.toCharArray()) {
	  int count = countOccurrences(S, c);
	  if (count == 2 && repeatedNumbers.indexOf(Character.toString(c)) == -1) {
		repeatedNumbers.append(c);
	  }
	}

	// Adım 4: Palindrom olmayı engelleyen karakterleri sil
	StringBuilder remainingNumbers = new StringBuilder();
	for (char c : S.toCharArray()) {
	  if (c != targetNumber && repeatedNumbers.indexOf(Character.toString(c)) == -1) {
		remainingNumbers.append(c);
	  }
	}

	// Adım 5: Kalan sayılardan en büyük palindromu üret
	StringBuilder palindromeBuilder = new StringBuilder(remainingNumbers);
	palindromeBuilder.append(targetNumber);
	palindromeBuilder.append(remainingNumbers.reverse());
	String palindrome = palindromeBuilder.toString();

	return palindrome;
  }

  private static int countOccurrences(String str, char target) {
	int count = 0;
	for (char c : str.toCharArray()) {
	  if (c == target) {
		count++;
	  }
	}
	return count;
  }

  public static void main(String[] args) {
/*	Solution solution = new Solution();
	String input = "04980983";
	String largestPalindrome = generateLargestPalindrome(input);
	System.out.println("Input: " + input);
	System.out.println("Largest Palindrome: " + largestPalindrome);*/
  }
}
