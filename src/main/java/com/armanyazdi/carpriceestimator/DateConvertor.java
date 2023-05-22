package com.armanyazdi.carpriceestimator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
public class DateConvertor {

    // Gregorian to Jalali date converter.
    public String jalaliDate() {
        LocalDate localDate = LocalDate.now();
        short gregorianYear = Short.parseShort(DateTimeFormatter.ofPattern("yyyy").format(localDate));
        byte gregorianMonth = Byte.parseByte(DateTimeFormatter.ofPattern("MM").format(localDate));
        byte gregorianDay = Byte.parseByte(DateTimeFormatter.ofPattern("dd").format(localDate));
        int[] jalaliDate = gregorianToJalali(gregorianYear, gregorianMonth, gregorianDay);
        return "%s/%s/%s".formatted(jalaliDate[0], jalaliDate[1], jalaliDate[2]);
    }

    // Day Name of Week
    public String dayName() {
        Calendar calendar = Calendar.getInstance();
        String[] weekDays = new String[] {"یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه"};
        return weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    // This method converts Gregorian date to Jalali.
    private int[] gregorianToJalali(short gy, byte gm, byte gd) {
        int[] out = {(gm > 2) ? (gy + 1) : gy, 0, 0};
        {
            int[] g_d_m = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
            out[2] = 355666 + (365 * gy) + ((out[0] + 3) / 4) - ((out[0] + 99) / 100) + ((out[0] + 399) / 400) + gd + g_d_m[gm - 1];
        }
        out[0] = -1595 + (33 * (out[2] / 12053));
        out[2] %= 12053;
        out[0] += 4 * (out[2] / 1461);
        out[2] %= 1461;
        if (out[2] > 365) {
            out[0] += (out[2] - 1) / 365;
            out[2] = (out[2] - 1) % 365;
        }
        if (out[2] < 186) {
            out[1] = 1 + (out[2] / 31);
            out[2] = 1 + (out[2] % 31);
        }
        else {
            out[1] = 7 + ((out[2] - 186) / 30);
            out[2] = 1 + ((out[2] - 186) % 30);
        }
        return out;
    }
}
