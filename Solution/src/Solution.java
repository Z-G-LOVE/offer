/**
 * @作者: Administrator
 * @时间: 2021/7/11 20:27
 * @描述: LeetCode刷题记录
 */
public class Solution {
    public int magicalString(int n){
        int index = 1;
        StringBuilder s = new StringBuilder();
        s.append(1);
        while (s.length() < n){
            // 字符串索引越界，需要根据字符串的前一个位置确定下一个字符的个数
            if (index == s.length()){
                s.append(s.charAt(s.length()-1) == '1' ? 22 : 1 );
                index++;
            }else {
                // 索引没越界，使用字符串的最后一个位置确定是'1'还是'2'
                // 根据索引处的字符确定该字符的个数
                if (s.charAt(s.length()-1) == '1'){
                    s.append(s.charAt(index++) == '1' ? 2 : 22);
                }else {
                    s.append(s.charAt(index++) == '1' ? 1 : 11 );
                }
            }
        }
        // 进行循环遍历生成的字符串，计算 '1' 的个数
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
        }
        return count;
    }

    public String convert(String s, int numRows) {
        int row = numRows,col = s.length();
        char[][] c = new char[row][col];
        boolean tag = false;
        int index = 0;
        int count = 0;
        int i = 0,j = 0;
        while (count < s.length()){
            count++;
            if (i == 0) tag = false;
            if (i == row-1) tag = true;
            if (!tag){
                c[i++][j] = s.charAt(index++);
            }else {
                c[i][j] = s.charAt(index++);
                i = i-1;
                j = j+1;
            }
        }
        for (int k = 0; k < row; k++) {
            for (int l = 0; l < col; l++) {
                System.out.print(c[i][j] + "\t");
            }
            System.out.println();
        }
        return null;
    }
}
