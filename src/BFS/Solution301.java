package BFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @作者: one者天下
 * @时间: 2021/5/13 19:40
 * @描述: 删除无效括号
 */
public class Solution301 {
    public List<String> removeInvalidParentheses(String s) {
        return null;
    }

    private void bfs(String s,List<String> list){
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (isLegal(cur)) {
                    list.add(cur);
                }
                int length = cur.length();
                for (int j = 0; j < length; j++) {
                    if (cur.charAt(j) == '(' || cur.charAt(j) == ')'){
                        String newStr = cur.substring(0,j) + cur.substring(j+1,length);
                        queue.add(s);
                    }
                }
            }
        }
    }

    private boolean isLegal(String s){
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0 ) return false;
        }
        return count == 0;
    }
}
