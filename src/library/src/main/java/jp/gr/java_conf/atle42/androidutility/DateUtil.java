package jp.gr.java_conf.atle42.androidutility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by murata_to on 2017/03/02.
 */

public class DateUtil {
	public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

	public static String toISO8601Time(long unixTime) {
		return toISO8601Time(new Date(unixTime));
	}

	public static String toISO8601Time(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_ISO8601).format(date);
	}

	public static long toUNIXTime(String iso8601Time) {
		try {
			return new SimpleDateFormat(DATE_FORMAT_ISO8601).parse(iso8601Time).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
}
