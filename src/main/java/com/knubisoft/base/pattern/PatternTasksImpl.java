package com.knubisoft.base.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTasksImpl implements PatternTasks {

    @Override
    public boolean haveSetOfCharacters(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException();
        }
        return text.matches("[a-zA-Z\\d]*");
    }

    @Override
    public String matchByCharacters(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        return !text.isEmpty() && text.matches("[pq]*") ? "Found a match!" : "Not matched!";
    }

    @Override
    public String matchStartAndEnd(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        return text.matches(".*\\w+g\\w+.*") ? "Found a match!" : "Not matched!";
    }

    @Override
    public String matchIpAddress(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException();
        }

        return text.replaceAll("\\b0+", "");
    }

    @Override
    public String matchVowels(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException();
        }
        return text.replaceAll("[QEYUIOAqeyuioa]", "");
    }

    @Override
    public boolean validatePIN(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException();
        }
        return text.matches("(\\b(\\d{4})|(\\d{6})|(\\d{8})\\b)");
    }

    @Override
    public String divideDigit(int digit) {
        String value = String.valueOf(digit);
        return value.replaceAll("000\\b", "#000");
    }

    @Override
    public String removeAllNonAlphanumericCharacters(String text) {
        return text.replaceAll("(([^a-zA-z])|(\\^))", "");
    }

    @Override
    public boolean validatePhoneNumber(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException();
        }
        return text
                .matches("((\\(\\d{3}\\)\\d{3}-?\\d{4})|(\\d{3}-?\\d{3}-?\\d{4}))");
    }

    @Override
    public String getLastVowelsByConstraint(String text, int n) {
        if (text == null || text.isBlank() || n < 1 || text.length() < n) {
            throw new RuntimeException();
        }
        String value = text.replaceAll("[wrtpsdfghklzxcvbnmjWRTPSDFGHJKLZXCVBNM]", "");
        return value.toLowerCase().substring(value.length() - n);
    }

    @Override
    public boolean isMathematicalExpression(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException();
        }
        return text.matches("(\\d+(\\s[+-/%*]\\s\\d+)*)");
    }

    @Override
    public String insertDash(String text) {
        Pattern pattern = Pattern.compile("\\b[A-Z]");
        Matcher matcher = pattern.matcher(text);
        List<Integer> places = new ArrayList<>();
        while (matcher.find()) {
            places.add(matcher.end());
        }
        for (int i = 0; i < places.size(); i++) {
            text = text.substring(0, places.get(i) + i) + "-" + text.substring(places.get(i) + i);
        }
        return text;
    }
}
