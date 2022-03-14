package com.truonggiang.employee_management_system.utils;

import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String removeSpace(String text) {
        if (!StringUtils.isEmpty(text)) {
            text = text.trim();
            text = text.replaceAll("\\s{1,}", " ");
        }
        return text;
    }

    public static String longTimeToHHMM(long time) {
        if (time < 0) time = time * (-1);
        String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)));
        System.out.println(hms);
        return hms;
    }
}
