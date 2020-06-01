package leetcode;

// https://leetcode.com/problemset/all/?topicSlugs=sliding-window
public class MaxTurbulenceSize_978 {

	// 我的思路：击败了93%
	public int maxTurbulenceSize(int[] A) {
		if (A.length == 1) return 1;
		int lessThan = 0;
		// -1表示A[i] < A[i-1]
		// 1表示A[i] > A[i-1]
		int maxWindowLen = 1;
		int start = 0;
		for (int i = 1; i < A.length; i++) {
			if (A[i] == A[i - 1]) {
				start = i;
				lessThan = 0;
			} else {
				if (lessThan == 0) {
					lessThan = A[i] < A[i - 1]? -1: 1;
					maxWindowLen = Math.max(i - start + 1, maxWindowLen);
				} else {
					if (lessThan == 1 && A[i] < A[i - 1]) {
						maxWindowLen = Math.max(i - start + 1, maxWindowLen);
						lessThan = -lessThan;
					} else if (lessThan == -1 && A[i] > A[i - 1] ) {
						maxWindowLen = Math.max(i - start + 1, maxWindowLen);
						lessThan = -lessThan;
					} else {
						start = i - 1;
						i = start;
						lessThan = 0;
					}
				}
			}
		}
		return maxWindowLen;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{9,4,2,10,7,8,8,1,9};
		int res = new MaxTurbulenceSize_978().maxTurbulenceSize(nums);
		System.out.println(res);
	}

}
