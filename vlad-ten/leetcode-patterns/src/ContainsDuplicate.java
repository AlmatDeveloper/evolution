import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{5, 7, 8, 9, 5, 54, 7, 5}));
        System.out.println(containsDuplicate1(new int[]{5, 7, 8, 9, 5, 54, 7, 5}));
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> integers = new HashSet<>();

        for (int num : nums) {
            if (integers.contains(num)) {
                return true;
            }
            integers.add(num);
        }

        return false;
    }

    public static boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }
}