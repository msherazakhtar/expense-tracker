package com.expense.tracker.utilities;

public class GeneralUtilities {
    public static String generateVerificationCode()
    {
        Integer verificationCode = 100000 + (int) (Math.random() * 900000);
        return verificationCode.toString();
    }
}
