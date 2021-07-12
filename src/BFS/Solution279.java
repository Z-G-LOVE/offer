package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @作者: one者天下
 * @时间: 2021/5/12 23:40
 * @描述: 完全平方数
 */
public class Solution279 {
    public int numSquares(int n) {
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Integer curNode = queue.poll();
                for (int j = 1; j <= n; j++) {
                    int node = curNode + j * j;
                    if (node > n) break;
                    if (node == n) return level;
                    if (!set.contains(node)){
                        set.add(node);
                        queue.add(node);
                    }
                }
            }
        }
        return level;
    }
    private int count = Integer.MAX_VALUE;
    // 定义一个数据结构，便于实现广度优先遍历
    private static class Point{
        int[] arr;

        public Point(int n) {
            this.arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i * i;
            }
        }
    }
    private void bfs(int n,int num){

        Queue<Point> queue = new LinkedList<>();
        Point point = new Point(n);
        queue.add(point);
        while (!queue.isEmpty()){
            Point point1 = queue.poll();
            for (int i = 0; i < point1.arr.length; i++) {
                int res = 0;
                res = res + point.arr[i];
            }
        }
    }
}
