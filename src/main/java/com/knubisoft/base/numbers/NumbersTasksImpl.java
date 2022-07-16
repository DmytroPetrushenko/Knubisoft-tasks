package com.knubisoft.base.numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

public class NumbersTasksImpl implements NumbersTasks {

    @Override
    public int[] swapTwoNumbersWithoutUsingTemporaryVariable(int firstNumber, int secondNumber) {
        firstNumber += secondNumber;
        secondNumber = firstNumber - secondNumber;
        firstNumber -= secondNumber;
        return new int[]{firstNumber, secondNumber};
    }

    @Override
    public boolean isUglyInt(int number) {
        if (number < 1) {
            return false;
        }
        int temp;
        do {
            temp = number;
            for (int i : new int[] {2, 3, 5}) {
                if (number % i == 0) {
                    number = number / i;
                    break;
                }
            }
        } while (temp != number) ;
        return number == 1;
    }

    @Override
    public int addDigits(int number) {
        do {
            number = Arrays.stream(String.valueOf(number).split(""))
                    .map(Integer::valueOf)
                    .reduce(Integer::sum).orElseThrow();
        } while (String.valueOf(number).length() != 1);
        return number;
    }

    @Override
    public boolean isHarshadNumber(int number) {
        if (number == 0) {
            return false;
        }
        Integer sum = Arrays.stream(String.valueOf(number).split(""))
                .map(Integer::valueOf)
                .reduce(Integer::sum)
                .orElseThrow();
        return number % sum == 0;
    }

    @Override
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0 && number != i) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isArmstrongNumber(int number) {
        String[] array = String.valueOf(number).split("");
        Double sum = Arrays.stream(array)
                .map(Integer::valueOf)
                .map(e -> Math.pow(e, array.length))
                .reduce(Double::sum)
                .orElseThrow();
        return sum == number;
    }

    @Override
    public BigInteger factorial(int number) {
        return IntStream.range(1, number + 1)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElseThrow();
    }

    @Override
    public boolean palindrome(int number) {
        String string = String.valueOf(number);
        int size = string.length();
        String[] array = string.substring(size / 2, size).split("");
        StringBuilder builder = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            builder.append(array[i]);
        }
        String second = builder.toString();
        String first = string.substring(0, size / 2);
        if (string.length() % 2 == 0) {
            return first.compareTo(second) == 0;
        }
        return first.compareTo(second.substring(0, second.length() - 1)) == 0;
    }

    @Override
    public boolean isAutomorphic(int number) {
        String numberString = String.valueOf(number);
        String result = String.valueOf((long) Math.pow(number, 2));
        return numberString.compareTo(result
                .substring(result.length() - numberString.length())) == 0;
    }
}
