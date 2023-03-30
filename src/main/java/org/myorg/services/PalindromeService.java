package org.myorg.services;

import java.util.ArrayList;
import java.util.List;

public class PalindromeService {
    public static String isPalindrome(String word) {
        String reverseWord = reverseWord(List.of(word.split("")));
        System.out.println(reverseWord);

        return reverseWord.equals(word) ? "It's palindrome" : "It isn't palindrome";
    }

    private static String reverseWord(List<String> word) {
        StringBuilder reverseWord = new StringBuilder();

        for (int index = word.size() - 1; index >= 0; index--) {
            reverseWord.append(word.get(index));
        }

        return String.valueOf(reverseWord);
    }
}
