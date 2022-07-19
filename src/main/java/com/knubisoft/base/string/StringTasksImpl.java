package com.knubisoft.base.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        if (original == null || original.isBlank() || original.length() < position || position < 0) {
            throw new IllegalArgumentException();
        }
        return original.substring(0, position) + toInsert + original.substring(position);
    }

    @Override
    public String appendToString(StringBuilder original, String toAppend) {
        if (original == null || original.length() == 0 || toAppend == null
                || toAppend.isBlank() ) {
            throw new NoSuchElementException();
        }
        return original.append(toAppend).toString();
    }

    @Override
    public boolean isPalindrome(String palindrome) {
        if (palindrome == null) {
            throw new RuntimeException();
        }
        int length = palindrome.length();
        String second;
        if (length % 2 == 0) {
            second = palindrome.substring(length / 2);
        } else {
            second = palindrome.substring(length / 2 + 1);
        }
        StringBuilder builder = new StringBuilder();
        String[] secondArray = second.split("");
        for (int i = second.length() - 1; i >= 0; i--) {
            builder.append(secondArray[i]);
        }
        return palindrome.substring(0, length / 2).equals(builder.toString());
    }

    @Override
    public boolean hasLowerCase(String str) {
        String[] checked = Arrays.stream(Optional.ofNullable(str)
                        .orElseThrow(IllegalArgumentException::new).split(""))
                .filter(value -> value.equals(value.toLowerCase()))
                .toArray(String[]::new);
        return checked.length == str.length();
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
        return Arrays.stream(Optional.ofNullable(str).orElseThrow(IllegalArgumentException::new)
                        .split(""))
                .filter(value -> !value.equals(String.valueOf(charToRemove)))
                .reduce((x, y) -> x + y)
                .orElseThrow();
    }

    @Override
    public String toCamelCase(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException();
        }
        boolean flag = false;
        StringBuilder builder = new StringBuilder();
        for (String value: str.split("")) {
            if (builder.length() == 0) {
                builder.append(value.toLowerCase());
                continue;
            }
            if (value.matches("[-_\\s]")) {
                flag = true;
                continue;
            }
            if (flag ) {
                builder.append(value.toUpperCase());
                flag = false;
            }
            else {
                builder.append(value);
            }
        }
        return builder.toString();
    }

    @Override
    public String getCountRepeatableString(String str) {
        if (str == null) throw new IllegalArgumentException();
        if(str.isEmpty()) return "";
        String[] split = str.split("");
        StringBuilder builder = new StringBuilder();
        int count;
        for (int i = 0; i < split.length; i++) {
            count = 1;
            for (int j = i; j < split.length; j++) {
                if (split[i].equals(split[j])) {
                    if (count > 9) {
                        count = 1;
                    }
                    builder.append(count);
                    count++;
                }
            }
        }
        return builder.toString();
    }

    @Override
    public String sortStringCharactersAsc(String str) {
        return Arrays.stream(Optional.ofNullable(str).orElseThrow(IllegalArgumentException::new)
                        .split(""))
                .sorted(Comparator.naturalOrder())
                .reduce((x, y) -> x + y)
                .orElseThrow();
    }
}
