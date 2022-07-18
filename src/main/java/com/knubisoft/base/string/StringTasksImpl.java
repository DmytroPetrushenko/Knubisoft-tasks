package com.knubisoft.base.string;

import java.util.*;

public class StringTasksImpl implements StringTasks {

    @Override
    public String reverseString(String original) {
        StringBuilder builder = new StringBuilder();
        String[] array = Optional.ofNullable(original).orElseThrow(IllegalArgumentException::new)
                .split("");
        for (int i = array.length - 1; i >= 0; i--) {
            builder.append(array[i]);
        }
        return builder.toString();
    }

    @Override
    public String insertStringInMiddle(String original, String toInsert) {
        if (original == null || toInsert == null || original.isEmpty() || toInsert.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int length = original.length();
        return original.substring(0, length / 2) + toInsert
                + original.substring(length / 2, length);
    }

    @Override
    public String insertSymbolInString(String original, char toInsert, int position) {
        return null;
    }

    @Override
    public String appendToString(StringBuilder original, String toAppend) {
        return null;
    }

    @Override
    public boolean isPalindrome(String palindrome) {
        return false;
    }

    @Override
    public boolean hasLowerCase(String str) {
        return false;
    }

    @Override
    public String uniqueCharacters(String str) {
        String[] array = Optional.ofNullable(str).orElseThrow(IllegalArgumentException::new)
                .toLowerCase().split("");
        return Arrays.stream(array)
                .filter(value -> Arrays.stream(array).filter(value::equals).count() == 1)
                .reduce((x, y) -> x + y)
                .orElseThrow();
    }

    @Override
    public String removeAllCharacters(String str, char charToRemove) {
        return null;
    }

    @Override
    public String toCamelCase(String str) {
        return null;
    }

    @Override
    public String getCountRepeatableString(String str) {
        return null;
    }

    @Override
    public String sortStringCharactersAsc(String str) {
        return null;
    }
}
