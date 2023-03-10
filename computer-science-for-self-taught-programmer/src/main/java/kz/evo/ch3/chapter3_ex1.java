package kz.evo.ch3;

// дан список слов в алфавитном порядке
// напишите функцию, которая выполнит двоичный поиск слова и вернет ответ о том, имеется ли оно в списке
public class chapter3_ex1 {
    public static void main(String[] args) {
        System.out.println(simpleWordSearch(new String[]{"aaa", "bbb", "ccc", "ddd"}, "ddd"));
        System.out.println(simpleWordSearch(new String[]{"aaa", "bbb", "ccc", "ddd"}, "bbb"));
        System.out.println(simpleWordSearch(new String[]{"aaa", "bbb", "ccc", "ddd"}, "aaa"));
        System.out.println(simpleWordSearch(new String[]{"aaa", "bbb", "ccc", "ddd"}, "qqq"));
    }

    public static boolean simpleWordSearch(String[] sortedWords, String word) {
        int firstIndex = 0;
        int lastIndex = sortedWords.length - 1;

        while (lastIndex >= firstIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;

            if (sortedWords[middleIndex].equals(word)) {
                return true;
            } else {
                if (isSorted(word, sortedWords[middleIndex])) {
                    lastIndex = middleIndex - 1;
                } else {
                    firstIndex = middleIndex + 1;
                }
            }
        }

        return false;
    }

    private static boolean isSorted(String firstWord, String secondWord) {
        int minLength = Math.min(firstWord.length(), secondWord.length());

        for (int i = 0; i < minLength; ++i) {
            if (firstWord.charAt(i) < secondWord.charAt(i)) {
                return true;
            }
            if (firstWord.charAt(i) > secondWord.charAt(i)) {
                return false;
            }
        }

        return firstWord.length() < secondWord.length();
    }
}
