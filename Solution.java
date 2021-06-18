package 剑指offer;

import org.junit.Test;

import java.util.*;

/**
 * @author : one者天下
 * @时间: 2021/5/26 19:22
 * @描述: 剑指offer
 */
public class Solution {
    /**
     * 二维数组的查找
     *
     * @param target 目标
     * @param array  二维数组
     * @return 返回二维数组是否存在target
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array[0] == null || array[0].length == 0) return false;
        for (int[] ints : array) {
            if (ints[ints.length - 1] == target || ints[0] == target) return true;
            if (ints[0] < target && ints[ints.length - 1] > target) {
                int left = 0, right = ints.length - 1;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (ints[mid] == target) return true;
                    else if (ints[mid] > target) right = mid - 1;
                    else left = mid + 1;
                }
            }
        }
        return false;
    }

    @Test
    public void testFind() {
//        int[][] arrays = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int target = 22;

        int[][] arrays = new int[][]{{}, {}, {}};
        boolean find = Find(target, arrays);
        System.out.println(find);
    }

    /**
     * 替换字符串中的空格
     *
     * @param s 要替换的字符串
     * @return 返回将空格替换成%20的字符串
     */
    public String replaceSpace(String s) {
        if (" ".equals(s)) return "%20";
        if (s == null || "".equals(s)) return s;
        String res = "";
        boolean tag = true;
        if (s.charAt(s.length() - 1) == ' ') {
            s = s + "a";
            tag = false;
        }
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (i == split.length - 1) {
                if (tag) res = res + split[i];
                continue;
            }
            res = res + split[i] + "%20";
        }
//        int i = s.length()-1;
// while (i > 0 && s.charAt(i) == ' '){
//     i--;
//     res = res + "%20";
// }
        return res;
    }

    @Test
    public void testReplaceSpace() {
        String s = "";
        String s1 = replaceSpace(s);
        System.out.println(s1);
//        String s = " age   hello ";
//        String[] s1 = s.split(" ");
//        for (String s2 : s1) {
//            System.out.println(s2);
//        }
//        System.out.println(s1.length);
    }

    /**
     * 倒叙输出链表节点的值
     *
     * @param listNode 输入的链表
     * @return 返回一个List
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) return list;
        ListNode node = listNode;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            res.add(list.get(i));
        }
        return res;
    }

    /**
     * @author one者天下
     * 链表结构
     */
    private static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private final static Map<Integer, Integer> CreateTreeMap = new HashMap<>();

    /**
     * 根据前序遍历和中序遍历的数组重构一个二叉树
     *
     * @param pre 前序遍历的数组
     * @param in  中序遍历的数组
     * @return 返回二叉树的根节点
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0) return null;
//        for (int i = 0; i < in.length; i++) {
//            CreateTreeMap.put(in[i],i);
//        }
//        return createTree(pre,0,pre.length,0);
        return help(pre, in, 0, in.length - 1, 0, pre.length);
    }

    /**
     * 递归实现二叉树的重构
     *
     * @param pre      前序遍历的数组
     * @param in       当前中序遍历的数组
     * @param left     当前中序遍历的左索引
     * @param right    当前中序遍历的右索引
     * @param preStart 当前前序遍历的开始索引
     * @param preEnd   当前前序遍历的结束索引
     * @return 返回子树的根节点
     */
    private TreeNode help(int[] pre, int[] in, int left, int right, int preStart, int preEnd) {
        if (preStart == preEnd) return null;
        int rootValue = pre[preStart];
        TreeNode root = new TreeNode(rootValue);
        int leftTemp = left;
        int rightTemp = right;
        int mid = (left + right) >> 1;
        while (leftTemp < rightTemp) {
            if (in[leftTemp] == rootValue) {
                mid = leftTemp;
                break;
            }
            if (in[rightTemp] == rootValue) {
                mid = rightTemp;
                break;
            }
            mid = (leftTemp + rightTemp) >> 1;
            if (in[mid] == rootValue) break;
            else if (in[mid] < rootValue) leftTemp = mid + 1;
            else rightTemp = mid;
        }
        int count = mid - left;
        TreeNode leftNode = help(pre, in, left, mid - 1, preStart + 1, preStart + count + 1);
        TreeNode rightNode = help(pre, in, mid + 1, right, preStart + count + 1, preEnd);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    /**
     * 根据前序遍历和中序遍历递归创建一颗二叉树
     *
     * @param pre      前序遍历数组
     * @param preStart 当前前序遍历的开始索引
     * @param preEnd   当前前序遍历的结束索引
     * @param inStart  当前中序遍历的开始索引
     * @return 返回创建好的子树的根节点
     */
    private TreeNode createTree(int[] pre, int preStart, int preEnd, int inStart) {
        if (preStart == preEnd) return null;
        int rootValue = pre[preStart];
        TreeNode root = new TreeNode(rootValue);
        // 获取根节点在中序遍历数组的索引位置
        int rootIndex = CreateTreeMap.get(rootValue);
        // 计算前序遍历数组的起始索引
        int preCount = rootIndex - inStart;
        root.left = createTree(pre, preStart + 1, preStart + preCount + 1, inStart);
        root.right = createTree(pre, preStart + preCount + 1, preEnd, rootIndex + 1);
        return root;
    }

    @Test
    public void testReConstructBinaryTree() {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};
        TreeNode root = reConstructBinaryTree(pre, in);
//        System.out.println(root.val);
        dfsPrint(root);
        System.out.println();
        inPrint(root);

    }

    /**
     * @author one者天下
     * 二叉树结构
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 前序遍历二叉树
     *
     * @param root 二叉树的根节点
     */
    private void dfsPrint(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        dfsPrint(root.left);
        dfsPrint(root.right);
    }

    /**
     * 中序遍历二叉树
     *
     * @param root 二叉树的根节点
     */
    private void inPrint(TreeNode root) {
        if (root == null) return;
        inPrint(root.left);
        System.out.println(root.val);
        inPrint(root.right);
    }

    /**
     * 用两个栈模拟队列的push()和 pop()
     */
    private static class LinkQueue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            while (!stack2.isEmpty()) stack1.push(stack2.pop());
            stack1.push(node);
        }

        public int pop() {
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
            if (stack2.isEmpty()) throw new RuntimeException("队列为空");
            return stack2.pop();
        }
    }

    /**
     * 找出旋转数组中最小的数字
     *
     * @param array 旋转数组
     * @return 返回最小的数字
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 1) return array[0];
        int left = 0, right = array.length - 1, mid = 0;
        while (left < right - 1) {
            mid = (left + right) >> 1;
//            if (mid == left) break;
//            if (array[mid] > array[right]) left = mid + 1;
//            else if (array[mid] < array[right]) right = mid;
            if (array[mid] > array[left]) left = mid;
            else if (array[mid] < array[right]) right = mid;
//            else left--;
        }
        return array[right];
    }

    @Test
    public void testMinNumberInRotateArray() {
        int[] arrays = new int[]{6, 7, 8, 9, 1, 2, 3, 4, 5};
        System.out.println(minNumberInRotateArray(arrays));
    }

    // 台阶的跳法
    private int count = 0;

    /**
     * 跳台阶
     *
     * @param target n阶台阶
     * @return 返回有多少种跳法
     */
    public int jumpFloor(int target) {
//        helpJumpFloor(target);
//        return count;
        Map<Integer, Integer> map = new HashMap<>();
        return helpJumpFloor(target, map);
//        int[] dp = new int[target+1];
//        return helpJumpFloor(target,dp);
    }

    /**
     * 递归实现
     *
     * @param target n阶台阶
     */
    private void helpJumpFloor(int target) {
        if (target <= 0) {
            if (target == 0) count++;
            return;
        }
        helpJumpFloor(target - 1);
        helpJumpFloor(target - 2);
    }

    /**
     * 记忆优化的递归实现
     *
     * @param target n阶台阶
     * @param map    记录第n阶台阶的跳法总数
     * @return 返回n阶台阶的跳法总数
     */
    private int helpJumpFloor(int target, Map<Integer, Integer> map) {
        if (target == 1) map.put(1, 1);
        if (target == 2) map.put(2, 2);
        if (map.containsKey(target)) return map.get(target);
        map.put(target, helpJumpFloor(target - 1, map) + helpJumpFloor(target - 2, map));
        return map.get(target);
    }

    //    private int helpJumpFloor(int target,int[] dp){
//        dp[1] = 1;
//        dp[2] = 2;
//        for (int i = 3; i < dp.length; i++) {
//            dp[i] = dp[i-1] + dp[i-2];
//        }
//        return dp[target];
//    }
    @Test
    public void testJumpFloor() {
        System.out.println(jumpFloor(9));
    }

    /**
     * @param target n阶台阶
     * @return 返回跳法的总数
     */
    public int jumpFloorII(int target) {
        if (target == 1) return 1;
        return 2 << (target - 2);
    }

    @Test
    public void testJumpFloorII() {
        System.out.println(jumpFloorII(2));
//        int i = 2;
//        i = i << 1;
//        System.out.println(i);
    }

    /**
     * 矩阵覆盖
     *
     * @param target 矩阵的宽度
     * @return 返回矩阵的覆盖的种类
     */
    public int rectCover(int target) {
        Map<Integer, Integer> map = new HashMap<>();
        return helpRectCover(target, map);
    }

    /**
     * 递归实现矩阵覆盖
     *
     * @param target 矩阵的宽度
     * @param map    用于记忆优化,防止重复计算
     * @return 返回覆盖的方法数
     */
    private int helpRectCover(int target, Map<Integer, Integer> map) {
        if (target == 1) map.put(1, 1);
        if (target == 2) map.put(2, 2);
        if (target == 0) map.put(0, 0);
        if (map.containsKey(target)) return map.get(target);
        map.put(target, helpRectCover(target - 1, map) + helpRectCover(target - 2, map));
        return map.get(target);
    }

    @Test
    public void testRectCover() {
        System.out.println(rectCover(0));
        System.out.println(rectCover(1));
        System.out.println(rectCover(4));
        System.out.println(rectCover(5));
    }

    /**
     * 二进制中1的个数
     * @param n 十进制数
     * @return 返回n的32位二进制数的1的个数
     */
    public int NumberOf1(int n) {
        if (n == 0) return 0;
        else if (n == Integer.MIN_VALUE) return 1;
        else {
            int num = Math.abs(n);
            int count = 0;
            while (num > 0) {
                count ++;
                num &= (num-1);
            }
            if (n > 0) return count;
            else if ((n & 1) == 1) return 32-count + 1;
            else return 32 - count ;
        }
    }
    @Test
    public void testNumberOf1(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(NumberOf1(-1234567));
        System.out.println(-1 & 1);
    }
    @Test
    public void testDouble(){
        double num = 2.00000;
        double res = (1/num) * (1/num);
        System.out.println(res);
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * @param base 乘数
     * @param exponent 阶方
     * @return 返回 base的exponent次方
     */
    public double Power(double base, int exponent) {
        if (base == 0) return 0;
        if (exponent < 0) {
            exponent = -exponent;
            base = 1 / base;
        }
        return helpPower(base,exponent);
    }

    /**
     * 递归实现n次阶方
     * @param base 乘数
     * @param exponent 阶方
     * @return 返回base 的 exponent 次阶方
     */
    private double helpPower(double base,int exponent){
        if (exponent == 0) return 1;
        double res = helpPower(base,exponent >> 1);
        if ((exponent&1) == 1) return res * res * base;
        return res * res;
    }

    /**
     * 拆解法解决阶方问题
     * @param base 乘数
     * @param exponent 阶方
     * @return 返回 base 的 exponent 次阶方
     */
    public double power(double base,int exponent){
        if (exponent == 0) return 1;
        if (base == 0) return 0;
        if (exponent < 0) {
            exponent = -exponent;
            base = 1 / base;
        }
        double res = 1.0;
        double x = base;
        while (exponent != 0){
            if ((exponent&1) == 1){
                res *= x;
            }
            x *= x;
            exponent = exponent >> 1;
        }
        return res;
    }

    @Test
    public void testPower(){
        double base = 2.10000;
        int exponent = 3;
        System.out.println(power(base,exponent));
        System.out.println(Power(base,exponent));
    }

    /**
     * 调整数组顺序使奇数位于偶数前面
     * @param array 需要调整的数组
     * @return 返回一个按要求排列的数组
     */
    public int[] reOrderArray (int[] array) {
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();
        for (int i : array) {
            if ((i & 1) == 1) odd.add(i);
            else even.add(i);
        }
        for (int i = 0; i < odd.size(); i++) {
            array[i] = odd.get(i);
        }
        for (int i = odd.size(),j = 0; j < even.size(); j++,i++) {
            array[i] = even.get(j);
        }
        return array;
    }

    /**
     * 插入法解决调整数组顺序使奇数位于偶数前面
     * @param array 需要调整的数组
     * @return 返回按要求排列的数组
     */
    public int[] ReOrderArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            if ((array[i]&1) == 0) continue;
            int temp = i;
            int tempValue  = array[i];
            while (i>0 && (array[i-1]&1)==0){
                array[i] = array[i-1];
                i--;
            }
            if(temp != i){
                array[i] = tempValue;
                i = temp;
            }
        }
        return array;
    }

    /**
     * 使用头尾指针解决调整数组顺序使奇数位于偶数前面
     * @param array 需要调整的数组
     * @return 返回按要求排列的数组
     */
    public int[] ReOrderArrayHeardTail(int[] array){
        int[] res = new int[array.length];
        int head = 0;// 头指针
        int tail = array.length-1; // 尾指针
        int resHead = head; // 结果数组的头指针
        int resTail = tail; // 结果数组的尾指针
        while (head < array.length && tail >= 0){
            if ((array[head] & 1) == 1){
                res[resHead] = array[head];
                resHead ++ ;
            }
            head ++;
            if ((array[tail] & 1) == 0){
                res[resTail] = array[tail];
                resTail -- ;
            }
            tail --;
        }
        return res;
    }
    @Test
    public void testReOrderArray(){
        int[] arrays = new int[]{2,4,5,6,7,4,5,22,46,3};
//        int[] ints = reOrderArray(arrays);
        int[] ints1 = ReOrderArray(arrays);
//        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(ints1));
        int[] ints = ReOrderArrayHeardTail(arrays);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 链表中倒数第k个结点
     * @param pHead 链表的头指针
     * @param k 倒数的n个节点
     * @return 返回倒数的n个节点的值
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        if (pHead == null) return null;
        int length = listLength(pHead);
        if (length < k) return null;
        else if (length == k) return pHead;
        int index = length - k + 1;
        int i = 1;
        ListNode temp = pHead;
        while (i < index){
            temp = temp.next;
            i ++;
        }
        return temp;
    }

    /**
     * 递归实现合并两个排序的链表
     * @param pHead 链表的头指针
     * @param k 倒数第k个节点
     * @return 返回链表的倒数第k个节点的值
     */
    public ListNode findKthToTail(ListNode pHead,int k){
        ListNode temp = pHead;
        for (int i = 0; i < k; i++) {
            if (temp == null) return null;
            temp = temp.next;
        }
        ListNode res = pHead;
        while (temp != null){
            temp = temp.next;
            res = res.next;
        }
        return res;
    }
    /**
     * 链表的长度
     * @param head 链表的头指针
     * @return 返回链表的长度
     */
    private static int listLength(ListNode head){
        ListNode temp = head;
        int length = 0;
        while (temp != null){
            length ++;
            temp = temp.next;
        }
        return length;
    }
    @Test
    public void testListLength(){
        ListNode head = new ListNode(0);
        head.next = new ListNode(0);
        head.next.next = new ListNode(0);
        int listLength = listLength(head);
        System.out.println(listLength);
    }
    @Test
    public void testFindKthToTail(){
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        ListNode listNode = FindKthToTail(head, 2);
        System.out.println(listNode.val);
        ListNode kthToTail = findKthToTail(head, 2);
        System.out.println(kthToTail.val);
    }

    /**
     * 递归实现合并两个排序的链表
     * @param list1 链表一
     * @param list2 链表二
     * @return 返回合并后的链表
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
        else if (list1.val <= list2.val) {
            list1.next = Merge(list1.next,list2);
            return list1;
        }else{
            list2.next = Merge(list1,list2.next);
            return list2;
        }
    }

    /**
     * 树的子结构
     * @param root1 大树
     * @param root2 小树
     * @return 返回小树是否为大树的子结构
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return helpHasSubtree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }

    /**
     * 判断两棵树是否结构一样
     * @param root1 树一
     * @param root2 树二
     * @return 返回boolean
     */
    private boolean helpHasSubtree(TreeNode root1,TreeNode root2){
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val ) return false;
        return helpHasSubtree(root1.left,root2.left) && helpHasSubtree(root1.right,root2.right);
    }
    public TreeNode Mirror (TreeNode pRoot) {
        if (pRoot == null) return null;
        return  helpMirror(pRoot);
    }
    private TreeNode helpMirror(TreeNode pRoot){
        if (pRoot == null) return null;
        TreeNode left = helpMirror(pRoot.left);
        pRoot.left = helpMirror(pRoot.right);
        pRoot.right = left;
        return pRoot;
    }

    /**
     * 顺时针打印矩阵
     * @param matrix 数组
     * @return 返回顺时针打印的集合
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix == null) return new ArrayList<Integer>();
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix.length == 1 && matrix[0].length == 1)  {
            list.add(matrix[0][0]);
            return list;
        }
        boolean[][] mat = new boolean[matrix.length][matrix[0].length];
        LinkedList<Integer> queue = new LinkedList<>();
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        queue.add(-1);
        queue.add(0);
        queue.add(1);
        queue.add(2);
        int count = 0;
        int rowLeft  = 0;
        int colDown = matrix[0].length-1;
        int rowRight = matrix.length-1;
        int colUp = 0;
        while (count < rowLength * colLength){
            int dir = queue.poll();
            if (dir == -1 && rowLeft < rowLength){
                for (int i = 0; i < matrix[0].length; i++) {
                    if (mat[rowLeft][i]) continue;
                    list.add(matrix[rowLeft][i]);
                    mat[rowLeft][i] = true;
                    count ++;
                }
                queue.addLast(dir);
                rowLeft++;
            }else if (dir == 0 && colDown >=0){
                for (int i = 0; i < matrix.length ; i++) {
                    if (mat[i][colDown]) continue;
                    list.add(matrix[i][colDown]);
                    mat[i][colDown] = true;
                    count ++ ;
                }
                queue.addLast(dir);
                colDown--;
            }else if (dir == 1 &&  rowRight >= 0){
                for (int i = colLength-1; i >= 0 ; i--) {
                    if (mat[rowRight][i]) continue;
                    list.add(matrix[rowRight][i]);
                    mat[rowRight][i] = true;
                    count ++;
                }
                queue.addLast(dir);
                rowRight--;
            }else if (colUp < colLength){
                for (int i = matrix.length - 1; i >= 0  ; i--) {
                    if (mat[i][colUp]) continue;
                    list.add(matrix[i][colUp]);
                    mat[i][colUp] = true;
                    count ++;
                }
                queue.addLast(dir);
                colUp ++ ;
            }
        }
        return list;
    }
    @Test
    public void testPrintMatrix(){
        int[][] arr = new int[][]{
                {1,2,3,4}
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16}
        };
        ArrayList<Integer> list = printMatrix(arr);
        System.out.println(list);
    }

    /**
     * @author one者天下
     * 创建一个包含min函数的栈
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））
     */
    private static class NewStack{
        private final Stack<Integer> stack1 = new Stack<>();
        private final Stack<Integer> stack2 = new Stack<>();
        private final Stack<Integer> stack3 = new Stack<>();
        public void push(int node) {
            stack3.push(node);
            if (stack1.isEmpty()) stack1.push(node);
            else if (node <= stack1.peek()){
                stack1.push(node);
            }else {
                while (!stack1.isEmpty() && node > stack1.peek()){
                    stack2.push(stack1.pop());
                }
                stack1.push(node);
                while (!stack2.isEmpty()){
                    stack1.push(stack2.pop());
                }
            }
        }

        public void pop() {
            if (!stack3.isEmpty()){
                Integer popValue = stack3.pop();
                while (!stack1.isEmpty() && !stack1.peek().equals(popValue)){
                    stack2.push(stack1.pop());
                }
                if (!stack1.isEmpty()) stack1.pop();
                while (!stack2.isEmpty()) stack1.push(stack2.pop());
            }
            else throw new RuntimeException("栈为空");
        }

        public int top() {
            return stack3.peek();
        }

        public int min() {
            return stack1.peek();
        }
    }
    @Test
    public void testNewStack(){
        NewStack newStack = new NewStack();
        newStack.push(3);
        System.out.println(newStack.min());
        newStack.push(4);
        System.out.println(newStack.min());
        newStack.push(2);
        System.out.println(newStack.min());
        newStack.push(3);
        System.out.println(newStack.min());
        newStack.pop();
        System.out.println(newStack.min());
        newStack.pop();
        System.out.println(newStack.min());
        newStack.pop();
        System.out.println(newStack.min());
        newStack.push(0);
        System.out.println(newStack.min());
    }

    /**
     * 栈的压入、弹出序列
     * @param pushA 压栈顺序
     * @param popA 弹栈顺序
     * @return 返回是否弹栈的顺序是否为栈的弹出顺序
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length != popA.length ||popA.length == 0) return false;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int item : pushA) {
            stack.push(item);
            while (!stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
    @Test
    public void testIsPopOrder(){
        int[] pushA = new int[]{1,2,3,4,5};
        int[] popA = new int[]{4,3,5,1,2};
        System.out.println(IsPopOrder(pushA,popA));
    }

    public int findNumber(int[] nums,int target){
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return Integer.MAX_VALUE;
    }
    @Test
    public void testFindNumber(){
        int[] num = new int[]{1,2,3,4,5};
        System.out.println(findNumber(num,3));
    }
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode != null){
                    list.add(treeNode.val);
                    queue.add(treeNode.left);
                    queue.add(treeNode.right);
                }
            }
        }
        return list;
    }
    @Test
    public void testPrintFromTopToBottom(){
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(1);
        root.left = node1;
        node1.left = node2;
        node2.left = node3;
        node3.right = node4;
        ArrayList<Integer> list = PrintFromTopToBottom(root);
        System.out.println(list);

    }

    /**
     * 二叉搜索树的后序遍历序列
     * @param sequence 测试的数组
     * @return 若是数的后续遍历，返回真，否则返回假。
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return helpVerifySquenceOfBST(sequence,0,sequence.length-1);
    }

    /**
     * 递归实现二叉搜索树的后序遍历序列
     * @param sequence 测试的数组序列
     * @param start 数组遍历的开始索引
     * @param root 数的根节点
     * @return 若是后序遍历，则返回真，否则返回假
     */
    private boolean helpVerifySquenceOfBST(int[] sequence ,int start,int root ){
        if (start >= root) return true;
        int i = start;
        // 确认左右子树边界
        for (;i < root;i++)
            if (sequence[i] > sequence[root]) break;
        // 确认右子树是否有小于节点的值
        for (int j = i; j < root; j++)
            if (sequence[j] < sequence[root]) return false;
        return helpVerifySquenceOfBST(sequence,0,i-1) && helpVerifySquenceOfBST(sequence,i,root-1);
    }
    @Test
    public void testVerifySquenceOfBST(){
        int[] s = new int[]{4,8,6,12,16,14,10};
        System.out.println(VerifySquenceOfBST(s));
    }

    private final ArrayList<Integer> FindPathList = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> FindPathRes = new ArrayList<>();

    /**
     *
     * 二叉树中和为某一值的路径
     * @param root 根节点
     * @param target 目标值
     * @return 返回的路径集合
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if (root == null) return FindPathRes;
        helpFindPath(root,target);
        return FindPathRes;
    }

    /**
     * 回溯解决二叉树中和为某一值的路径
     * @param root 根节点
     * @param target 目标值
     */
    private void helpFindPath(TreeNode  root,int target){
        if (root == null) return;
        // 递归到叶子节点
        if (root.left == null && root.right == null){
            if (root.val == target) {
                // 找到目标路径
                FindPathList.add(root.val);
                FindPathRes.add(new ArrayList<>(FindPathList));
                // 回溯
                FindPathList.remove(FindPathList.size()-1);
            }
            return;
        }
        if (root.val > target) return;
        FindPathList.add(root.val);
        // 遍历左子树
        helpFindPath(root.left,target - root.val);
        // 遍历右子树
        helpFindPath(root.right,target - root.val);
        // 回溯
        FindPathList.remove(FindPathList.size()-1);
    }
    @Test
    public void testFindPath(){
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        ArrayList<ArrayList<Integer>> lists = FindPath(root, 22);
        System.out.println(lists);
    }

    /**
     * @author one者天下
     * 复杂链表
     */
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 复杂链表的深拷贝
     * @param pHead 链表的头结点
     * @return 返回拷贝后的链表的头结点
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode head = new RandomListNode(pHead.label);

        RandomListNode temp = pHead;
        RandomListNode headTemp = head;

        Map<RandomListNode,RandomListNode> map = new HashMap<>();

        while (temp != null){
            map.put(temp,new RandomListNode(temp.label));
            temp = temp.next;
        }

        while (pHead != null){
            headTemp.next = map.get(pHead.next);
            headTemp.random = map.get(pHead.random);
            headTemp = headTemp.next;
            pHead = pHead.next;
        }
        return head;
    }

    private final List<TreeNode> convertList = new ArrayList<>();
    /**
     * 二叉搜索树与双向链表
     * @param pRootOfTree 二叉搜索树的根节点
     * @return 返回链表的头结点
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        inorder(pRootOfTree);
        for (int i = 0; i < convertList.size()-1; i++) {
            TreeNode nodeLeft = convertList.get(i);
            TreeNode nodeRight = convertList.get(i + 1);
            nodeLeft.right = nodeRight;
            nodeRight.left = nodeLeft;
        }
        return convertList.get(0);
    }


    private TreeNode inorderPass(TreeNode pRootOfTree){
        if (pRootOfTree == null) return null;
        TreeNode left = inorderPass(pRootOfTree.left);
        TreeNode root = new TreeNode(pRootOfTree.val);
        if (left != null)
            left.right = root;
        root.left = left;
        TreeNode right = inorderPass(pRootOfTree.right);
        root.right = right;
        if (right != null)
            right.left = root;
        return root.right;
    }

    private void inorder(TreeNode pRootOfTree){
        if (pRootOfTree == null) return;
        inorder(pRootOfTree.left);
        convertList.add(new TreeNode(pRootOfTree.val));
        inorder(pRootOfTree.right);
    }


    @Test
    public void testConvert(){
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(14);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(12);
        TreeNode node6 = new TreeNode(16);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        TreeNode convert = Convert(root);

        System.out.println(convert.val);
        System.out.println(convert.left);
        System.out.println(convert.right.val);

    }

    private final ArrayList<String> permutationList = new ArrayList<>();

    /**
     * 字符串的排列
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        if ("".equals(str) || str == null) return permutationList;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        boolean[] tag = new boolean[chars.length];
        StringBuilder stringBuilder = new StringBuilder();
        helpPermutation(chars,stringBuilder,tag);
        return permutationList;
    }
    private void helpPermutation(char[] str,StringBuilder stringBuilder,boolean[] tag){
        if (stringBuilder.length() == str.length){
            permutationList.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (i > 0 && str[i] == str[i-1] && tag[i-1]) continue;
            if (!tag[i]){
                stringBuilder.append(str[i]);
                tag[i] = true;
                helpPermutation(str,stringBuilder,tag);
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                tag[i] = false;
            }

        }
    }
    @Test
    public void testHelpPermutation(){
        char[] str = new char[]{'a','b','c'};
        boolean[] tag = new boolean[str.length];
        StringBuilder stringBuilder = new StringBuilder();
        helpPermutation(str,stringBuilder,tag);
        System.out.println(permutationList);
    }
    @Test
    public void testPermutation(){
        String str = "aab";
        ArrayList<String> permutation = Permutation(str);
        System.out.println(permutation);
    }

    /**
     * 连续子数组的最大和
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        if (array.length == 1) return array[0];
        int[] dp = new int[array.length];
        int res = Integer.MIN_VALUE;
        dp[0] = array[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1] + array[i],array[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * 整数中1出现的次数（从1到n整数中1出现的次数）
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int base = 1;
        int count = 0;
        int num = n;
        while (num != 0){
            int curCount = num / 10 * base;
            if (num % 10 == 1) curCount = curCount + (n % base) + 1;
            if (num % 10 > 1) curCount = curCount + base;
            num /= 10;
            base *= 10;
            count = count + curCount;
        }
        return count;
    }

    /**
     * 拼接最小数
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        if (numbers.length == 1) return "" + numbers[0];
        Arrays.sort(numbers);
        long[] dp = new long[numbers.length];
        dp[0] = numbers[0];
        for (int i = 1; i < dp.length; i++) {
            String one = "" + dp[i-1] + numbers[i];
            String second = "" + numbers[i] + dp[i-1];
            dp[i] = Math.min(Long.parseLong(one),Long.parseLong(second));
        }
        return "" + dp[dp.length-1];
    }
    @Test
    public void testStringToInt(){
        System.out.println(""+1+2);
    }
    @Test
    public void testPrintMinNumber(){
        int[] numbers = new int[]{3334,3,3333332};
        String s = PrintMinNumber(numbers);
        System.out.println(s);
    }

    /**
     * 丑数
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int pow2 = 0,pow3 = 0,pow5 = 0;
        int[] res = new int[index];
        res[0] = 1;
        for (int i = 1; i < index; i++) {
            res[i] = Math.min(res[pow2] * 2,Math.min(res[pow3] * 3,res[pow5] * 5));
            if (res[i] == res[pow2] * 2) pow2++;
            if (res[i] == res[pow3] * 3) pow3++;
            if (res[i] == res[pow5] * 5) pow5++;
        }
        return res[index-1];
    }
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) return -1;
        int res = -1;
        if (str.length() == 1) return 0;
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (map.containsKey(aChar)){
                map.put(aChar,map.get(aChar) + 1);
            }else map.put(aChar,1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                res = i;
                break;
            }
        }
        return res;
    }
    @Test
    public void testFirstNotRepeatingChar(){
        String str = "gbk";
        int i = FirstNotRepeatingChar(str);
        System.out.println(i);
    }
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode temp = pHead1;
        ListNode res = null;
        while (temp != null){
            set.add(temp);
            temp = temp.next;
        }
        temp = pHead2;
        while (temp != null){
            if (set.contains(temp)){
                res = temp;
                break;
            }
            temp = temp.next;
        }
        return res;
    }
    public int GetNumberOfK(int [] array , int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : array) {
            if (map.containsKey(i)) map.put(i,map.get(i)+1);
            else map.put(i,1);
        }
        return map.getOrDefault(k, 0);
    }
    private int resTreeDepth = 0;
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        helpTreeDepth(root,0);
        return resTreeDepth;
    }

    private void helpTreeDepth(TreeNode root,int count){
        if (root == null){
            resTreeDepth = Math.max(resTreeDepth,count);
            return;
        }
        helpTreeDepth(root.left,count+1);
        helpTreeDepth(root.right,count+1);
    }
    @Test
    public void testTreeDepth(){
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.left = node7;
//        node7.right = new TreeNode(8);
        int i = TreeDepth(root);
        System.out.println(i);
    }
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right)
                && Math.abs(TreeHeight(root.left,0) - TreeHeight(root.right,0)) <= 1;
    }
    private int TreeHeight(TreeNode root,int count){
        if (root == null) return count;
        return Math.max(TreeHeight(root.left,count+1),TreeHeight(root.right,count+1));
    }
    @Test
    public void testTreeHeight(){
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.left = node7;
        node7.right = new TreeNode(8);
        System.out.println(TreeHeight(root,0));
    }
    @Test
    public void testIsBalanced_Solution(){
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.left = node7;
        node7.right = new TreeNode(8);
        boolean b = IsBalanced_Solution(root);
        System.out.println(b);
    }

    public int[] FindNumsAppearOnce (int[] array) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : array) {
            if (map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }
            else map.put(i,1);
        }
        int[] res = new int[2];
        int count = 0;
        for (int i : array) {
            if (map.get(i) == 1) {
                res[count] = i;
                count++;
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum <= 0 ) return res;
        int left = 1;
        int right = 2;
        int count = left + right;
        while (right <= (sum+1) >> 1){
            if (count < sum){
                right++;
                count += right;
            }else if (count > sum){
                count -= left;
                left++;
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left ; i <= right ; i++){
                    list.add(i);
                }
                res.add(list);
                count -= left;
                left++;
            }
        }
        return res;
    }

    @Test
    public void testFindContinuousSequence(){
        ArrayList<ArrayList<Integer>> arrayLists = FindContinuousSequence(100);
        arrayLists.forEach(System.out::println);
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if (array == null || array.length == 0) return res;
        Map<Integer,Integer> map = new HashMap<>();
        int[] temp = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};

        int resCount = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            map.put(array[i],i);
        }
        for (int item : array){
            if (map.containsKey(sum-item)){
                if (resCount > (sum-item) * item){
                    resCount = (sum-item) * item;
                    temp[0] = item;
                    temp[1] = sum - item;
                }
            }
        }
        if (temp[0] != Integer.MAX_VALUE || temp[1] != Integer.MAX_VALUE){
            res.add(temp[0]);
            res.add(temp[1]);
        }
        return res;
    }
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0) return "";
        if (n<=0) return str;
        LinkedList<Character> queue = new LinkedList<>();
        char[] chars = str.toCharArray();
        for (char c : chars){
            queue.add(c);
        }
        while (n > 0){
            Character poll = queue.poll();
            queue.addLast(poll);
            n--;
        }
        String res = "";
        while (!queue.isEmpty()){
            res += queue.poll();
        }
        return res;
    }

    @Test
    public void testLeftRotateString(){
        String str = "nowcoder. a am I";
        String s = LeftRotateString(str, 8);
        System.out.println(s);
    }
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) return "";
        if (str.charAt(str.length()-1) == '.') return str;
        LinkedList<String> queue = new LinkedList<>();
        String[] strs = str.split(" ");
        Collections.addAll(queue, strs);
        String res = "";
        while (!queue.isEmpty()){
            String s = queue.peekLast();
            if (s.charAt(s.length()-1) != '.'){
                res = res + queue.pollLast() + " ";
            }else {
                res = res + queue.pollFirst() + " ";
            }
        }
        return res.substring(0,res.length()-1);
    }
    @Test
    public void testReverseSentence(){
        String str = "Gdut. from, student a am I";
        String s = ReverseSentence(str);
        System.out.println(s);
        System.out.println(s.length() == str.length());
    }
    public static void QuickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int midValue = arr[(l+r)>>1];
        while (l<r){
            while (arr[l] < midValue) l++;
            while (arr[r] > midValue) r--;
            if (l >= r) break;
            arr[l] = arr[r] ^ arr[l];
            arr[r] = arr[r] ^ arr[l];
            arr[l] = arr[r] ^ arr[l];
            if (arr[l] == midValue) r--;
            if (arr[r] == midValue) l++;
        }
        if (l == r){
            l++;
            r--;
        }
        if (left < r) QuickSort(arr,left,r);
        if (l < right) QuickSort(arr,l,right);
    }
    @Test
    public void testQuickSort(){
        int[] arr = new int[]{2,1,4,3,1,45,32,42,11,34};
        QuickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    private static void Merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <=mid && j <= right){
            if (arr[i] > arr[j]) temp[index++] = arr[j++];
            else temp[index++] = arr[i++];
        }
        while (i <= mid) temp[index++] = arr[i++];
        while (j <= right) temp[index++] = arr[j++];

        // 拷贝
        index = 0;
        int leftIndex = left;
        while (leftIndex <= right) arr[leftIndex++] = temp[index++];
    }
    public static void MergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) >> 1;
            // 分解
            MergeSort(arr,left,mid,temp);
            MergeSort(arr,mid+1,right,temp);
            // 合并
            Merge(arr,left,mid,right,temp);
        }
    }
    @Test
    public void testMergeSort(){
        int[] arr = new int[]{2,1,4,3,1,45,32,42,11,34};
        int[] temp = new int[arr.length];
        MergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    public static void BucketSort(int[] arr){
        int[][] bucket = new int[10][arr.length];
        int[] bucketCount = new int[arr.length];
        int max = arr[0];
        for (int i : arr) {
            max = Math.max(max,i);
        }
        int MaxLength = (max+"").length();
        for (int i = 0,n = 1; i < MaxLength; i++,n *= 10) {
            for (int i1 : arr) {
                int BucketIndex = i1 / n % 10;
                bucket[BucketIndex][bucketCount[BucketIndex]++] = i1;
            }
            int index = 0;
            for (int i1 = 0; i1 < bucket.length; i1++) {
                if (bucketCount[i1] != 0){
                    for (int i2 = 0; i2 < bucketCount[i1]; i2++) {
                        arr[index++] = bucket[i1][i2];
                    }
                    bucketCount[i1] = 0;
                }
            }
        }
    }

    @Test
    public void testBucketSort(){
        int[] arr = new int[]{2,1,4,3,1,45,32,42,11,34};
        BucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void adjustment(int[] arr,int i,int length){
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j+1 < length && arr[j] > arr[j+1]) j = j + 1;
            if (arr[j] < temp){
                arr[i] = arr[j];
                i = j;
            }else break;
        }
        arr[i] = temp;
    }

    public static void HeapSort(int[] arr){
        // 构建大顶堆
        for (int i = (arr.length>>1)-1; i >= 0 ; i--) {
            adjustment(arr,i,arr.length);
        }
        for (int i = arr.length-1 ; i > 0 ; i--){
            arr[i]  = arr[i] ^ arr[0];
            arr[0]  = arr[i] ^ arr[0];
            arr[i]  = arr[i] ^ arr[0];
            adjustment(arr,0,i);
        }
    }

    @Test
    public void testHeapSort(){
        int[] arr = new int[]{2,1,4,3,1,45,32,42,11,34};
        HeapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    // DCL单例模式
    private static class LazyDemo{
        private LazyDemo(){}
        private static volatile LazyDemo lazyDemo = null;
        public static LazyDemo getInstance(){
            if (lazyDemo == null){
                synchronized (LazyDemo.class){
                    if (lazyDemo == null) lazyDemo = new LazyDemo();
                }
            }
            return lazyDemo;
        }
    }
    // 饿汉式
    private static class Hungry{
        private Hungry(){}
        private static final Hungry hungry = new Hungry();
        public static Hungry getInstance(){
            return hungry;
        }
    }
    // 枚举单例
    private enum EnumerationSingleton{
        ENUMERATION_SINGLETON;
        public static EnumerationSingleton getInstance(){
            return ENUMERATION_SINGLETON;
        }
    }
    // 生产者消费者问题
    private static class Producer_Consumer{
        int num = 0;

        public synchronized void increase() throws InterruptedException {
            while (num != 0) this.wait();
            num++;
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            this.notifyAll();
        }

        public synchronized void cut() throws Exception{
            while (num == 0) this.wait();
            num--;
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        Producer_Consumer producer_consumer = new Producer_Consumer();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    producer_consumer.increase();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    producer_consumer.cut();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
    public int[] LRU (int[][] operators, int k) {
        // write code here
        Map<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        for (int[] arr : operators){
            if (arr[0] == 1){
                if (map.containsKey(arr[1]) && map.get(arr[1]) != -1){// 元素已经出现过并且没有被移除,则覆盖元素值
                    map.put(arr[1],arr[2]);
                    // 找到对应的元素key,将其置位最常使用
                    while (!stack1.isEmpty() && stack1.peek() != arr[1]){
                        stack2.push(stack1.pop());
                    }
                    // 找到了重复的元素key
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                    stack1.push(arr[1]);

                }else {// 元素第一次加入
                    map.put(arr[1],arr[2]);
                    if (stack1.size() < k) { // 元素个数没有超过k,直接push
                        stack1.push(arr[1]);
                    }else { // 移除最久没有使用的
                        while (!stack1.isEmpty()){
                            stack2.push(stack1.pop());
                        }
                        int key = stack2.pop();// 移除最久没有使用的元素
                        map.put(key,-1);// 移除后元素值置-1
                        while(!stack2.isEmpty()){
                            stack1.push(stack2.pop());
                        }
                        stack1.push(arr[1]);
                    }
                }
            }else {
                // 获取一个元素key的值
                // 没有出现过或已经被移除
                if (!map.containsKey(arr[1]) || map.get(arr[1]) == -1){
                    list.add(-1);
                }
                // 获取值,并将key置位常用
                else {
                    list.add(map.get(arr[1]));
                    while (!stack1.isEmpty() && stack1.peek() != arr[1]){
                        stack2.push(stack1.pop());
                    }
                    int temp = stack1.pop();
                    while (!stack2.isEmpty()) {
                        stack1.push(stack2.pop());
                    }
                    stack1.push(temp);
                }
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int j : list){
            res[i++] = j;
        }
        return res;
    }
    @Test
    public void testLRU(){
        int[][] arr = new int[][]{
                {1,1,1},
                {1,2,2},
                {1,3,2},
                {2,1},
                {1,4,4},
                {2,2}
        };
        int[] lru = LRU(arr, 3);
        System.out.println(Arrays.toString(lru));

    }
}
