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

            int ret = new Solution().reverse(x);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}

class Solution {
    public int reverse(int x) {
        int ans = 0;
        while(x != 0) {
            int pop = x%10;
            x = x/10;

            if (ans > Integer.MAX_VALUE || ans == Integer.MAX_VALUE && pop > 0) return 0;
            if (ans < Integer.MIN_VALUE || ans == Integer.MIN_VALUE && pop < 0) return 0;
            ans = ans * 10 + pop;

        }
        return ans;
    }
}
