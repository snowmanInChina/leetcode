import java.util.Arrays;

/**
 * @author huangjifeng
 * @date 2018/12/31
 */
public class MainClass {
    public static void main(String[] args) {
        System.out.println("PAHNAPLSIIGYIR".equals(new Solution().convert("PAYPALISHIRING", 3)));
        System.out.println("PINALSIGYAHRPI".equals(new Solution().convert("PAYPALISHIRING", 4)));
    }
}


class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s == "" || numRows == 1)
            return s;
        int hex = (s.length() + 1) / 2;
        int[][] vals = new int[numRows][hex];
        for (int i = 0; i < numRows; i++)
            Arrays.fill(vals[i], -1);
        int h = 0, z = 0;
        for (int i = 0; i < s.length(); i++) {
            vals[z][h] = i;
            if (h % (numRows - 1) == 0) {
                //z递增
                if (z < numRows - 1) {
                    z = z + 1;
                } else {
                    h++;
                    z--;
                }
            } else {
                h++;
                z--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < hex; j++)
                if (vals[i][j] >= 0)
                    sb.append(s.charAt(vals[i][j]));
        }
        return sb.toString();
    }
}
