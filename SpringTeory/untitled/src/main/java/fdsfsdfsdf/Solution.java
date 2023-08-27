package fdsfsdfsdf;

import java.util.*;

class Solution {
  /*
  *
  you are given a string consisting of lowercase letters of the English alphabet. You must split this string into a minimal number of substrings in such a way that no letter occurs more than once in each substring.
  For example here are some correct splits of the string "abacdec" : ('a','bac','dec'),('a','bacd','ec') and ('ab','ac','dec'). write a function class Solution { public int solution(String S);}  that given a string S of length N, returns the minimum number of substrings into which the string has to be split.
  examples :
  given 'world' your function should return 1. there is no need to split the string into substring as all letters occur just one.
  given 'dddd' your function should return 4. The result can be achieved by splitting the string into four substrings (d,d,d,d)
  given 'cycle' your function should return 2 . the result can be achieved by splitting the string into two substrings (cy,cle) or (c,ycle).
  for abba should return 2 */
  public int solution(String S) {

	Set<Character> set = new HashSet<>();
	for (int i = 0; i < S.length(); i++) {
	  set.add(S.charAt(i));
	}
	if (set.size() == S.length()) {
	  return 1;
	}

	HashMap<Character, Integer> frequencyMap = new HashMap<>();

	for (char c : S.toCharArray()) {
	  frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
	}

	int maxFrequency = 0;
	char maxKey = '\0';

	for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
	  if (entry.getValue() > maxFrequency) {
		maxFrequency = entry.getValue();
		maxKey = entry.getKey();
	  }
	}

	if (S.length() % 2 == 1) {
	  return maxFrequency + 1;
	} else {
	  return maxFrequency;
	}

  }

  public static void main(String[] args) {
	Solution solution = new Solution();
	System.out.println(solution.solution("world"));
	System.out.println(solution.solution("dddd"));
	System.out.println(solution.solution("cycle"));
	System.out.println(solution.solution("abba"));

  }
}