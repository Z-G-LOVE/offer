import org.junit.Test;

/**
 * @作者: Administrator
 * @时间: 2021/7/11 21:37
 * @描述: TODO
 */
public class SolutionTest {

    Solution solution = new Solution();

    @Test
    public void magicalString() {
        int i = solution.magicalString(6);
        System.out.println(i);
    }
    @Test
    public void convert(){
        solution.convert("PAYPALISHIRING",3);
        System.out.println('a');
    }
}
