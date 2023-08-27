package com.deneme.benimSinav;

import java.util.ArrayList;
import java.lang.Math;
class Solution {

  public int solution(String s) {
	ArrayList<Integer> reds = new ArrayList<>();
	for (int i = 0; i < s.length(); i++) {
	  if (s.charAt(i) == 'R') {
		reds.add(i);
	  }
	}
	int n = reds.size();
	if (n == 0) {
	  return 0;
	}
	else if(n == 1){
	  return -1;
	}
	int startPtr = 0;
	int endPtr = n - 1;
	int count = 0;
	while (startPtr < endPtr) {
	  count += reds.get(endPtr) - reds.get(startPtr) - endPtr + startPtr;
	  startPtr++;
	  endPtr--;
	}
	return count > Math.pow(10, 9) ? -1 : count;
  }
  public static void main(String[] args) {
	Solution solution = new Solution();

	System.out.println(solution.solution("WRRWWR"));      // Output: 2
	System.out.println(solution.solution("WWRWWWRWR"));  // Output: 4
	System.out.println(solution.solution("WWW"));        // Output: 0
	System.out.println(solution.solution("RW"));         // Output: -1
}
}