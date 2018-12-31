/**
 * @author huangjifeng
 * @date 2018/12/31
 */
public class MainClass {
    public static void main(String[] args) {
        System.out.println(new Solution2().longestPalidromic(""));
        System.out.println(new Solution2().longestPalidromic("ac"));
        System.out.println(new Solution2().longestPalidromic("asdfgfdsa"));
        System.out.println(new Solution2().longestPalidromic("aacdc"));
    }
}


/**
 * 假设i或者(i,i+1)是回文子串的中心，向两边扩展可得到最长子串
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        int maxLen = 0;
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (maxLen < len) {
                maxLen = len;
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right);
    }

    //计算出以left,right为中心的最长回文子串的长度
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}


/**
 * 动态规划，dp[i][j]表示子串(i,j)是否回文，s[i]==s[j] ? dp[i+1][j-1]:1
 * We define P(i,j)P(i,j) as following:
 * <p>
 * P(i,j) = \begin{cases} \text{true,} &\quad\text{if the substring } S_i \dots S_j \text{ is a palindrome}\\ \text{false,} &\quad\text{otherwise.} \end{cases} P(i,j)={
 * true,
 * false,
 * ​
 * <p>
 * if the substring S
 * i
 * ​
 * …S
 * j
 * ​
 * is a palindrome
 * otherwise.
 * ​
 * <p>
 * <p>
 * Therefore,
 * <p>
 * P(i, j) = ( P(i+1, j-1) \text{ and } S_i == S_j ) P(i,j)=(P(i+1,j−1) and S
 * i
 * ​
 * ==S
 * j
 * ​
 * )
 * <p>
 * The base cases are:
 * <p>
 * P(i, i) = true P(i,i)=true
 * <p>
 * P(i, i+1) = ( S_i == S_{i+1} ) P(i,i+1)=(S
 * i
 * ​
 * ==S
 * i+1
 * ​
 * )
 */
class Solution2 {
    public String longestPalidromic(String s) {
        if (s == null || s.length() <= 1)
            return s;
        int maxLen = 1, start = 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        //设置边界值，maxLen ==(1,2)
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            if (s.charAt(i) == s.charAt(i + 1)) {
                maxLen = 2;
                dp[i][i + 1] = 1;
                start = i;
            }
        }
        //公式求的剩下的是否回文子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0, j = i + len - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (dp[i + 1][j - 1] == 1) {
                        dp[i][j] = 1;
                        maxLen = j - i + 1;
                        start = i;
                    } else {
                        dp[i][j] = 0;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
