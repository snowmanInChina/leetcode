/**
 * @author huangjifeng
 * @date 2018/12/25
 */
public class MainClass {
    public static void main(String[] args) {
        String[] strs = new String[] {""};
        String ans = new Soluction2().longest3(strs);
        System.out.println(ans);
    }
}


/**
 * my answer,hard to understand
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        if ((strs[0].length()) == 0)
            return "";
        int i = 0;
        for (i = 0; ; ) {
            if (strs[0].length() <= i)
                break;
            char valOfIndex = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != valOfIndex)
                    return strs[0].substring(0, i);
            }
            i++;
        }
        return strs[0].substring(0, i);
    }
}


/**
 * 最长，逐渐缩短
 */
class Solution1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length());
                if (prefix.length() == 0)
                    return "";
            }
        }
        return prefix;
    }
}


/**
 * 垂直扫描
 */
class Soluction2 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char val = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != val) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 分治法,
     *
     * @return
     */
    public String longest(String[] strs) {
        if (strs.length == 0)
            return "";
        return longest(strs, 0, strs.length - 1);
    }

    private String longest(String[] args, int l, int r) {
        if (l == r)
            return args[l];
        int mid = (l + r) / 2;
        String leftStr = longest(args, l, mid);
        String rightStr = longest(args, mid + 1, r);
        return commonLongest(leftStr, rightStr);
    }

    private String commonLongest(String leftStr, String rightStr) {
        int len = Math.min(leftStr.length(), rightStr.length());
        for (int i = 0; i < len; i++) {
            if (leftStr.charAt(i) != rightStr.charAt(i)) {
                return leftStr.substring(0, i);
            }
        }
        return leftStr.substring(0, len);
    }

    /**
     * 二分搜索，
     */
    public String longest3(String[] strs) {
        if (strs.length == 0)
            return "";
        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        minLen = minLen < 0 ? 0 : minLen;
        return strs[0].substring(0, binarySearch(strs, 0, minLen - 1) + 1);
    }

    private int binarySearch(String[] strs, int l, int r) {
        if (l == r) {
            char valOfL = strs[0].charAt(l);
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].charAt(l) != valOfL)
                    return l - 1;
            }
            return l;
        }
        int mid = (l + r) / 2;
        String tmp = strs[0].substring(0, mid + 1);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(tmp)) {
                return binarySearch(strs, l, mid);
            }
        }
        return binarySearch(strs, mid + 1, r);

    }
}

