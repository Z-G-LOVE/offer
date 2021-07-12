package 回溯算法;

/**
 * @作者: one者天下
 * @时间: 2021/5/3 23:25
 * @描述: 排列序列
 */
public class Solution60 {
//    private final List<Integer> list = new ArrayList<>();
//    private final List<String> list_res = new ArrayList<>();

    public String getPermutation(int n, int k) {
        int[] nums = new int[n+1];// 记录阶乘数
        count(nums);
        boolean[] used = new boolean[n+1];
        StringBuilder path = new StringBuilder();
        find(path,0,n,k,nums,used);
        return path.toString();

    }
    // 计算阶乘数组
    private void count(int[] nums){
        nums[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i-1] * i;
        }
    }
    // 递归寻找n的阶乘的第k个排列
    private void find(StringBuilder path,int index,int n,int k,int[] nums,boolean[] used){
        if (index == n) return;
        int cur_count = nums[n-1-index];// 当前能产生的排列数
        for (int i = 1; i <= n; i++) {
            if (used[i]) continue;
            if (cur_count < k){
                k -= cur_count;
                continue;
            }
            path.append(i);
            used[i] = true;
            find(path,index+1,n,k,nums,used);
            return;
        }
    }

//    private void dfs(int start,int n ,StringBuilder stringBuilder){
//        if (start == n) {
//            list_res.add(stringBuilder.toString());
//            return;
//        }
//        int l = stringBuilder.length();
//        for (int i = 1; i < list.size(); i++) {
//            int temp = list.get(i);
//            stringBuilder.append(temp);
//            list.remove(Integer.valueOf(temp));
//            dfs(start+1,n,stringBuilder);
//            list.add(temp);
//            stringBuilder.deleteCharAt(stringBuilder.length()-1);
//        }
//    }
//    @Test
//    public void test(){
//        int n = 3;
//        for (int i = 0; i <= n; i++) {
//            list.add(i);
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        dfs(0,3,stringBuilder);
//        for (String list_re : list_res) {
//            System.out.println(list_re);
//        }
//    }

}
