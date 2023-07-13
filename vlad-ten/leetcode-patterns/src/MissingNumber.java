public class MissingNumber {
    public static void main(String[] args) {
    }

    // arithmetic progress sum
    public static int missingNumber(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        return (((nums.length + 1) * nums.length) / 2)  - sum;
    }
}
