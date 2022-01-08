package com.example.ondc.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by GAGAN.HV on 08/01/22
 */
@Slf4j
public class DateUtils {

    public static boolean isLessOrEqualDate(TimeZone zone, String stringDate1, Date date2) {
        try {
            if (StringUtils.isEmpty(stringDate1)) {
                return true;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone((ObjectUtils.isEmpty(zone)) ? TimeZone.getDefault() : zone);
            return sdf.parse(stringDate1).compareTo(date2) >= 0;
        } catch (ParseException e) {
            log.error(e.getMessage());
            return false;
        }

    }

    public static boolean isGreaterOrEqualDate(TimeZone zone, String stringDate1, Date date2) {
        try {
            if (StringUtils.isEmpty(stringDate1)) {
                return true;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone((ObjectUtils.isEmpty(zone)) ? TimeZone.getDefault() : zone);
            return sdf.parse(stringDate1).compareTo(date2) <= 0;

        } catch (ParseException e) {
            log.error(e.getMessage());
            return false;
        }

    }

    public static boolean isLessOrEqualTime(TimeZone zone, String time, Date date2) {
        try {
            if (StringUtils.isEmpty(time)) {
                return true;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setTimeZone((ObjectUtils.isEmpty(zone)) ? TimeZone.getDefault() : zone);
            return sdf.parse(time).compareTo(date2) >= 0;
        } catch (ParseException e) {
            log.error(e.getMessage());
            return false;
        }

    }

    public static boolean isGreaterOrEqualTime(TimeZone zone, String time, Date date2) {
        try {
            if (StringUtils.isEmpty(time)) {
                return true;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setTimeZone((ObjectUtils.isEmpty(zone)) ? TimeZone.getDefault() : zone);
            return sdf.parse(time).compareTo(date2) <= 0;
        } catch (ParseException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public static double isMultiple(Double weightge, Double percentage) {
        if (ObjectUtils.isEmpty(weightge) || ObjectUtils.isEmpty(percentage)) {
            return 0d;
        }
        return weightge * percentage;
    }

    public static double isMultiple(Double weightge, int percentage) {
        if (ObjectUtils.isEmpty(weightge)) {
            return 0d;
        }
        return weightge * percentage;
    }
}
