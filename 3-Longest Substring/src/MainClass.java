import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author huangjifeng
 * @date 2018/12/18
 */
public class MainClass {
    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(s.lengthOfLongestSubstring("abba"));
        System.out.println(s.lengthOfLongestSubstring("ab"));
        System.out.println(s.lengthOfLongestSubstring("abc"));
        System.out.println(s.lengthOfLongestSubstring(" "));

    }
}


/**
 * 穷举法
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        String longStr = "";
        for (int i = 0; i < s.length(); i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = i; j < s.length(); j++) {
                String appendStr = s.substring(j, j + 1);
                if (sb.toString().indexOf(appendStr) >= 0) {
                    // 元素重复
                    break;
                } else {
                    sb.append(appendStr);
                }
            }
            if (sb.toString().length() > longStr.length()) {
                longStr = sb.toString();
            }
        }
        return longStr.length();
    }
}


/**
 * 改进穷举法，元素重复时，循环从重复元素的下一位继续。
 */
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        String longStr = "";
        for (int i = 0; i < s.length(); ) {
            StringBuffer sb = new StringBuffer();
            for (int j = i; j < s.length(); j++) {
                String appendStr = s.substring(j, j + 1);
                if (sb.toString().indexOf(appendStr) >= 0) {
                    // 元素重复,设置循环的下标
                    i = i + sb.toString().indexOf(appendStr) + 1;
                    break;
                } else {
                    sb.append(appendStr);
                    if (j + 1 == s.length()) {
                        return Math.max(longStr.length(), sb.toString().length());
                    }
                }
            }

            if (sb.toString().length() > longStr.length()) {
                longStr = sb.toString();
            }
        }
        return longStr.length();
    }
}


/**
 * 穷举法，但是判断字符是否重复用了set集合
 * 穷举所有子串，下标从 i 到 j，不包括j
 */
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (allUniqe(s, i, j))
                    ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    boolean allUniqe(String s, int begin, int end) {
        Set set = new HashSet();
        for (int i = begin; i < end; i++) {
            if (set.contains(s.charAt(i)))
                return false;
        }
        return true;
    }
}


/**
 * 滑窗，左右两边不断递增
 */
class Solution4 {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, left = 0, right = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        while (left < n && right < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                ans = Math.max(ans, right - left);
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return ans;
    }
}


/**
 * 滑窗优化，left 跨步前进，
 */
class Solution5 {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0, left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        for (; right < n; right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}


/**
 * 字符集大小固定
 */
class Solution6 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[256]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
