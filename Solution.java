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
}
