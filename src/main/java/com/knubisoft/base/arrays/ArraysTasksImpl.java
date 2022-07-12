package com.knubisoft.base.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArraysTasksImpl implements ArraysTasks {

    @Override
    public int[] reverse(int[] array) {
        int[] res = new int[array.length];
        int index = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            res[index] = array[i];
            index++;
        }
        return res;
    }

    @Override
    public int[] mergeArrays(int[] array1, int[] array2) {
       int[] res = Arrays.copyOf(array1, array1.length + array2.length);
       int index = array1.length;
        for (int number : array2) {
            res[index] = number;
            index++;
        }
        return res;
    }

    @Override
    public int[] findMax3InArray(int[] array) {
        if (array.length < 3) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return Arrays.copyOf(array, 3);
    }

    @Override
    public int findLongestIncreasingContinuesSubsequence(int[] array) {
        int firstIndex = -1;
        int maxLength = 1;
        if (array.length == 0) {
            return 0;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1] && firstIndex == -1) {
                firstIndex = i;
            }
            if (array[i] >= array[i + 1] && firstIndex != -1) {
                int currentLength = i - firstIndex + 1;
                if (maxLength < currentLength) {
                    maxLength = currentLength;
                }
                firstIndex = -1;
            }
            if ( i + 1 == array.length - 1 && array[i] < array[i + 1]) {
                if (firstIndex == -1) {
                    firstIndex = i;
                }
                int currentLength = i + 1 - firstIndex + 1;
                if (maxLength < currentLength) {
                    maxLength = currentLength;
                }
                firstIndex = -1;
            }
        }
        return maxLength;
    }

    @Override
    public int sumOfAllUniqueElements(int[] array) {
        int sum = 0;
        int[] uniq = new int[array.length];
        int uniqSize = 0;
        boolean flag = false;
        for (int number : array) {
            if (uniqSize == 0) {
                uniq[0] = number;
                uniqSize = 1;
                continue;
            }
            for (int i = 0; i < uniqSize; i++) {
                if (number == uniq[i]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                uniq[uniqSize] = number;
                uniqSize++;
            }
            flag = false;
        }
        for (int uniqNumber : uniq) {
            sum += uniqNumber;
        }
        return sum;
    }

    @Override
    public int[] moveZeroes(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] == 0) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    @Override
    public int findFinalValue(int[] nums, int original) {
        boolean flag = true;
        while(flag) {
            flag = false;
            for (int number : nums) {
                if (original == number) {
                    original = number * 2;
                    flag = true;
                }
            }
        }
        return original;
    }

    @Override
    public String longestCommonPrefix(String[] words) {
        String prefix = "";
        if (words.length == 0) {
            return prefix;
        }
        if (words.length == 1) {
            return words[0];
        }
        for (int i = words[0].length(); i >= 0 ; i--) {
            for (int j = 1; j < words.length; j++) {
                String substring = words[0].substring(0, i);
                if (!words[j].contains(substring)) {
                    prefix = "";
                    break;
                }
                prefix = substring;
            }
            if (!prefix.isEmpty()) {
                return prefix;
            }
        }

        return prefix;
    }

    @Override
    public int missingNumber(int[] array) {
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        current = array[0];
        for (int number : array) {
            if (current != number) {
                return current;
            }
            current++;
        }
        if (array[0] == 0) {
            return array[array.length - 1] + 1;
        }
        return 0;
    }

    @Override
    public boolean containsDuplicate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (j == i) {
                    continue;
                }
                if (array[i] == array[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
