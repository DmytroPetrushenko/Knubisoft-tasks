package com.knubisoft.base.validation.util;

import java.util.Arrays;

public class StringUtil {
    private StringUtil() {

    }

    public static String splitStringByUpperCase (String string) {
        return Arrays.stream(string.split("A-Z"))
                .map(String::toLowerCase)
                .reduce((x, y) -> x + " " + y)
                .orElseThrow();
    }
}
