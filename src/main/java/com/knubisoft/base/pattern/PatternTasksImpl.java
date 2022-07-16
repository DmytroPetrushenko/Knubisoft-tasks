package com.knubisoft.base.pattern;

public class PatternTasksImpl implements PatternTasks {

    @Override
    public boolean haveSetOfCharacters(String text) {
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
        return null;
    }

    @Override
    public boolean validatePIN(String text) {
        return false;
    }

    @Override
    public String divideDigit(int digit) {
        return null;
    }

    @Override
    public String removeAllNonAlphanumericCharacters(String text) {
        return null;
    }

    @Override
    public boolean validatePhoneNumber(String text) {
        return false;
    }

    @Override
    public String getLastVowelsByConstraint(String text, int n) {
        return null;
    }

    @Override
    public boolean isMathematicalExpression(String text) {
        return false;
    }

    @Override
    public String insertDash(String text) {
        return null;
    }
}
