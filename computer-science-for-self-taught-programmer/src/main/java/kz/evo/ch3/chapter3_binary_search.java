package kz.evo.ch3;

// двоичный поиск работает в отсортированном массиве
public class chapter3_binary_search {
    public static void main(String[] args) {
        System.out.println(simpleNumSearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 7));
        System.out.println(simpleNumSearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 10));

        System.out.println(simpleNumSearch(new int[]{1}, 1));
        System.out.println(simpleNumSearch(new int[]{1}, 7));

        System.out.println(simpleNumSearch(new int[]{1, 2}, 1));
        System.out.println(simpleNumSearch(new int[]{1, 2}, -1));
    }

    public static boolean simpleNumSearch(int[] sortedArray, int num) {
        int lastIndex = sortedArray.length - 1;
        int firstIndex = 0;

        while (lastIndex >= firstIndex) {
            int middleIndex = (lastIndex + firstIndex) / 2;

            if (sortedArray[middleIndex] == num) {
                return true;
            } else {
                if (sortedArray[middleIndex] > num) {
                    lastIndex = middleIndex - 1;
                } else {
                    firstIndex = middleIndex + 1;
                }
            }
        }
        return false;
    }
}
