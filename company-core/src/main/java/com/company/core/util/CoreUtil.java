package com.company.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author mukulbansal
 */
public class CoreUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CoreUtil.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(Constants.DateFormats.DOB_DATE_FORMAT_PATTERN);
    private static final Pattern PATTERN = Pattern.compile(Constants.Regex.UNICODE_PERMISSIBLE_NAME_REGEX);


    public static Date getDate(String dateString) {
        Date date = null;
        try {
            date = DATE_FORMAT.parse(dateString);
        } catch (Exception e) {
            LOG.error("Exception- ", e);
        }
        return date;
    }

    public static String getDate(Date date) {
        String dateString = null;
        try {
            dateString = DATE_FORMAT.format(date);
        } catch (Exception e) {
            LOG.error("Exception- ", e);
        }
        return dateString;
    }

    public static LocalDate getLocalDate(long millis){
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static boolean isValidName(String rawString) {
        return PATTERN.matcher(rawString).matches();
    }
}
