package leetcode;

// https://leetcode.com/problems/sqrtx/submissions/
public class MySqrt_69 {
	// 只击败了5%
	public int mySqrt(int x) {
		int i = 1;
		int sqrt = 0;
		while (i <= x) {
			if (i * i == x) {
				sqrt = i;
				break;
			}
			if (i <= x / i && (i + 1) > x / (i + 1)) {
				sqrt = i;
				break;
			}
			i++;
		}
		return sqrt;
	}

	// 优化：用二分法来查找,击败了100%， log(n)
	public int mySqrtOpt(int x) {
		int sqrt = 0;
		int low = 1;
		int high = x;
		while (low <= high) {
			int mid = low + (high - low) / 2; // 这里最好不要写成mid = (low + high) /2，可能会造成溢出
			if (mid * mid == x) {
				sqrt = mid;
				break;
			} else if (mid * mid > 0) {
				if (mid <= x / mid &&  (mid + 1) > x / (mid + 1)) {
					sqrt = mid;
					break;
				} else if ((mid + 1) <= x / (mid + 1)){
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			} else {
				high = mid - 1;
			}
		}

		return sqrt;
	}

	// leetcode上给出的标准答案
	// 需要考虑溢出Int
	public int mySqrtLeetcode(int x) {
		int sqrt = 0;
		int low = 1;
		int high = x;
		while (low <= high) {
			int mid = low + (high - low) / 2; // 这里最好不要写成mid = (low + high) /2，可能会造成溢出
			long value = (long) mid * mid;
			if (mid * mid == x) {
				sqrt = mid;
				break;
			} else {
				long next = (long) (mid + 1) * (mid + 1);
				if (value < x && next > x) {
					sqrt = mid;
					break;
				} else  if (value > x) {
					high = mid - 1;
				} else low = mid + 1;
			}
		}

		return sqrt;
	}


	public static void main(String[] args) {
		int x = 4;
		int res = new MySqrt_69().mySqrtLeetcode(Integer.MAX_VALUE);
		System.out.println(res);
		System.out.println(Integer.MAX_VALUE);
	}
}
