/**
 * @author huangjifeng
 * @date 2018/12/31
 */
public class MainClass {
    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi(""));
        System.out.println(new Solution().myAtoi("   -"));
        System.out.println(new Solution().myAtoi(" "));
        System.out.println(new Solution().myAtoi("2147483646"));
        System.out.println(new Solution().myAtoi("  0000000000012345678"));
    }
}


class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int sign = 1, ans = 0, i = 0;
        while (i < str.length() && str.charAt(i) == ' ')
            i++;
        if (i == str.length())
            return 0;
        if (str.charAt(i) == '+') {
            sign = 1;
            i++;
        } else if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        while (i < str.length()) {

            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                if (ans > Integer.MAX_VALUE / 10
                    || ans == Integer.MAX_VALUE / 10 && str.charAt(i) > '7') {
                    if (sign == -1)
                        return Integer.MIN_VALUE;
                    else
                        return Integer.MAX_VALUE;
                }

                ans = ans * 10 + (str.charAt(i) - '0');
                i++;
            } else {
                return ans * sign;
            }

        }
        return ans * sign;
    }
}
