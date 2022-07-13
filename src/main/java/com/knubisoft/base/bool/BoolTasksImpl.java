package com.knubisoft.base.bool;

public class BoolTasksImpl implements BoolTasks {

    @Override
    public Boolean isTrueAutobox(boolean value) {
        if (value) {
            Boolean booleanWrap = value;
            return booleanWrap;
        }
        return false;
    }

    @Override
    public Boolean isFalseAutobox(boolean value) {
        if (!value) {
            Boolean booleanWrap = value;
            return booleanWrap;
        }
        return true;
    }

    @Override
    public boolean isTrueUnbox(Boolean value) {
        if (value) {
            boolean bol = value;
            return bol;
        }
        return false;
    }

    @Override
    public Boolean isFalseUnbox(boolean value) {
        if (!value) {
            boolean bol = value;
            return bol;
        }
        return value;
    }

    @Override
    public boolean andFunction(int digit, String string) {
        try {
            if (string != null && !string.isBlank() && digit == Integer.parseInt(string)) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean orFunction(int digit, String string) {
        try {
            if (string == null || string.isBlank() || Integer.parseInt(string) != digit) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean andComplexFunction(int generatedFirstDigit, double generatedSecondDigit, int range) {
        if (Math.round(generatedSecondDigit) == generatedFirstDigit
                && generatedFirstDigit <= range
                && generatedSecondDigit <= range) {
            return true;
        }
        return false;
    }

    @Override
    public boolean orComplexFunction(int generatedFirstDigit, double generatedSecondDigit,
                                     double generatedThirdDigit, int range) {
        if (generatedFirstDigit == Math.round(generatedSecondDigit)
                || generatedFirstDigit == Math.round(generatedThirdDigit)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSameSizeArray(Object[] firstArray, Object... secondArray) {
        int[] lengthArrays = new int[2];
        int count = 0;
        for (Object[] array : new Object[][] {firstArray, secondArray}) {
            if (array == null) {
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Object obj : array) {
                stringBuilder.append(obj);
            }
            lengthArrays[count] = stringBuilder.toString().length();
            count++;
        }
        return lengthArrays[0] == lengthArrays[1];
    }

    @Override
    public boolean isSameCharactersCount(String username, String name, int maxLength) {
        if (username == null || name == null || maxLength == 0 || username.isEmpty()
                || name.isEmpty() || username.length() > maxLength || name.length() > maxLength) {
            return false;
        }
        return true;
    }
}
