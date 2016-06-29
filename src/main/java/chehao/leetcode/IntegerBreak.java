package chehao.leetcode;

import java.util.HashMap;
import java.util.Map;

public class IntegerBreak {

	Map<String, Integer> keepInteger = new HashMap<String, Integer>();

	public int integerBreakFast(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 1;
		int remainder = n % 3;

		if (remainder == 0) {
			return (int) Math.pow(3, n / 3);
		} else if (remainder == 1) {
			return (int) Math.pow(3, n / 3 - 1) * 4;
		} else {
			return (int) Math.pow(3, n / 3) * 2;
		}

	}

	public int integerBreak(int n) {
		if (n < 2)
			return 1;
		if (n == 2)
			return 1;
		int[] maxP = new int[n + 1];
		maxP[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < (i / 2) + 1; j++) {
				int a = j;
				int b = i - a;
				System.out.println("(" + a + "," + b + ")");
				int max = Math.max(a, maxP[a]) * Math.max(b, maxP[b]);
				maxP[i] = Math.max(max, maxP[i]);
			}
		}
		return maxP[n];
	}

	public int findMax(int n) {
		if (n <= 4)
			return n;

		if (keepInteger.containsKey(String.valueOf(n))) {
			return keepInteger.get(String.valueOf(n));
		}
		double end = Math.ceil(n / 2);
		int max = 0;
		for (int i = 1; i <= end; i++) {
			int a = i;
			int b = n - a;
			System.out.println("(" + a + "," + b + ")");
			int r = findMax((int) a) * findMax((int) b);
			if (r > max) {
				max = r;
			}
		}
		keepInteger.put(String.valueOf(n), max);
		return max;

	}

	public int findMaxFast(int n) {
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(1 / 2);
		IntegerBreak ib = new IntegerBreak();
		for (int i = 2; i < 11; i++) {
			long start = System.currentTimeMillis();
			System.out.println(i + " ans:" + ib.integerBreak(i) + ", " + (System.currentTimeMillis() - start));

		}
	}
}
