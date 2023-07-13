import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }


    // cyclic sort
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();

        // cyclic sort
        for (int i = 0; i < nums.length; i++) {
            int correctPosition = nums[i] - 1;

            if (nums[i] != nums[correctPosition]) {
                nums[i] = nums[i] + nums[correctPosition];
                nums[correctPosition] = nums[i] - nums[correctPosition];
                nums[i] = nums[i] - nums[correctPosition];
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }
}
