package com.jachin.blog.config;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;

import java.util.Random;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/12 15:47
 */
public class KaptchaTextCreator extends DefaultTextCreator {
    private static final String[] NUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

    @Override
    public String getText() {
        int result;
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder sb = new StringBuilder();
        int op = (int) Math.round(Math.random() * 2);
        if (op == 0) {
            result = x * y;
            sb.append(NUMBERS[x]);
            sb.append("*");
            sb.append(NUMBERS[y]);
        } else if (op == 1) {
            if (x != 0 && y % x == 0) {
                result = y / x;
                sb.append(NUMBERS[y]);
                sb.append("/");
                sb.append(NUMBERS[x]);
            } else {
                result = x + y;
                sb.append(NUMBERS[x]);
                sb.append("+");
                sb.append(NUMBERS[y]);
            }
        } else if (op == 2) {
            if (x >= y) {
                result = x - y;
                sb.append(NUMBERS[x]);
                sb.append("-");
                sb.append(NUMBERS[y]);
            } else {
                result = y - x;
                sb.append(NUMBERS[y]);
                sb.append("-");
                sb.append(NUMBERS[x]);
            }
        } else {
            result = x + y;
            sb.append(NUMBERS[x]);
            sb.append("+");
            sb.append(NUMBERS[y]);
        }
        sb.append("=?@").append(result);
        return sb.toString();
    }
}
