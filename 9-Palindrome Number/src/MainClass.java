import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author huangjifeng
 * @date 2018/12/23
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);

            boolean ret = new Solution().isPalindrome(x);


            System.out.print(ret);
        }
    }
}


/**
 * 计算出x的反转rev，比较是否相等，
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int rev = 0;
        int temp = x;
        while (x != 0) {
            int pop = x%10;
            x = x/10;
            rev = rev * 10 + pop;
        }
        if (rev != temp) return false;
        return true;

    }
}


/**
 * 只计算一半的数字就好,x是偶数位数、奇数位数，有不同
 */
class Solution1 {
    public boolean isPalindrome(int x) {
        if (x < 0 || x%10 ==0 && x != 0) return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x%10;
            x = x/10;
        }
        return x == rev || x == rev/10;
    }
}
