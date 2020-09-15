@file:Suppress("NonAsciiCharacters")

package io.github.lazyengineer.feedparser.date

import io.github.lazyengineer.feedparser.model.DateUtil
import org.amshove.kluent.`should equal`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.TimeZone

@RunWith(MockitoJUnitRunner::class)
class DateUtilTest {

	@Test
	fun `parse simple date`() {
		val calendar = GregorianCalendar(2014, Calendar.OCTOBER, 8, 9, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		DateUtil.parseDateString("Thu, 8 Oct 2014 09:00:00 GMT") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC822 example dates`() {
		val calendar = GregorianCalendar(2002, Calendar.OCTOBER, 2, 13, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Wed, 02 Oct 2002 08:00:00 EST") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Wed, 02 Oct 2002 13:00:00 GMT") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Wed, 02 Oct 2002 15:00:00 +0200") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with 52 milliseconds`() {
		val calendar = GregorianCalendar(1985, Calendar.APRIL, 12, 23, 20, 50)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		// This represents 20 minutes and 50.52 seconds after the 23rd hour of April 12th, 1985 in UTC.
		DateUtil.parseDateString("1985-04-12T23:20:50.52Z") `should equal` Date(calendar.timeInMillis + 52)
	}

	@Test
	fun `parse RFC3339 date with an offset of -0800 dates`() {
		val calendar = GregorianCalendar(1996, Calendar.DECEMBER, 19, 16, 39, 57)
		calendar.timeZone = TimeZone.getTimeZone("PST")

		// This represents 39 minutes and 57 seconds after the 16th hour of December 19th, 1996 with an offset of -08:00 from UTC (Pacific
		// Standard Time).  Note that this is equivalent to 1996-12-20T00:39:57Z in UTC.
		DateUtil.parseDateString("1996-12-19T16:39:57-08:00") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with leap second inserted at the end of 1990`() {
		val calendar = GregorianCalendar(1990, Calendar.DECEMBER, 31, 23, 59, 60)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		// This represents the leap second inserted at the end of 1990.
		DateUtil.parseDateString("1990-12-31T23:59:60Z") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with same leap second in Pacific Standard Time 8 hours behind UTC`() {
		val calendar = GregorianCalendar(1990, Calendar.DECEMBER, 31, 23, 59, 60)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		// This represents the same leap second in Pacific Standard Time, 8 hours behind UTC.
		DateUtil.parseDateString("1990-12-31T15:59:60-08:00") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse special event RFC3339 date`() {
		val calendar = GregorianCalendar(1937, Calendar.JANUARY, 1, 12, 0, 27)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		// This represents the same instant of time as noon, January 1, 1937, Netherlands time. Standard time in the Netherlands was exactly 19
		// minutes and 32.13 seconds ahead of UTC by law from 1909-05-01 through 1937-06-30. This time zone cannot be represented exactly using the
		// HH:MM format, and this timestamp uses the closest representable UTC offset.
		DateUtil.parseDateString("1937-01-01T12:00:27.87+00:20") `should equal` Date(calendar.timeInMillis + 87)
	}

	@Test
	fun `parse RFC3339 date`() {
		val calendar = GregorianCalendar(1937, Calendar.JANUARY, 1, 12, 0, 27)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("1937-01-01T12:00:27") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse simple RFC3339 date`() {
		val calendar = GregorianCalendar(1937, Calendar.JANUARY, 1)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("1937-01-01") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with offset of -0300`() {
		val calendar = GregorianCalendar(1937, Calendar.JANUARY, 1, 3, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("1937-01-01-03:00") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with offset of +0300`() {
		val calendar = GregorianCalendar(1936, Calendar.DECEMBER, 31, 21, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("1937-01-01+03:00") `should equal` Date(calendar.timeInMillis)
	}

	/**
	 * UTC offset for standard time and Daylight saving time (DST) Italics: historical or unofficial
	 * https://en.wikipedia.org/wiki/UTC%E2%88%9200:25:21
	 */
	@Test
	fun `parse RFC3339 date with historical or unofficial offsets 180° to 90°W`() {
		val calendar = GregorianCalendar(2002, Calendar.OCTOBER, 2, 13, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("2002-10-02T01:00:00-12:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T02:00:00-11:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T02:30:00-10:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T03:00:00-10:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T03:30:00-09:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T04:00:00-09:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T04:30:00-08:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T05:00:00-08:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T06:00:00-07:00") `should equal` Date(calendar.timeInMillis)

		DateUtil.parseDateString("2002-10-03T01:00:00+12:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-03T01:45:00+12:45") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-03T02:00:00+13:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-03T02:45:00+13:45") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-03T03:00:00+14:00") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with historical or unofficial offsets 90°W to 0°`() {
		val calendar = GregorianCalendar(2002, Calendar.OCTOBER, 2, 13, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("2002-10-02T07:00:00-06:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T08:00:00-05:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T08:30:00-04:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T09:00:00-04:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T09:30:00-03:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T10:00:00-03:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T10:30:00-02:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T11:00:00-02:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T12:00:00-01:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T12:16:00-00:44") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T12:35:00-00:25:21") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with historical or unofficial offsets 0° to 90°E`() {
		val calendar = GregorianCalendar(2002, Calendar.OCTOBER, 2, 13, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("2002-10-02T13:00:00+00:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T13:20:00+00:20") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T13:30:00+00:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T14:00:00+01:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T14:24:00+01:24") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T14:30:00+01:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T15:00:00+02:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T15:30:00+02:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T16:00:00+03:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T16:30:00+03:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T17:00:00+04:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T17:30:00+04:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T17:51:00+04:51") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T18:00:00+05:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T18:30:00+05:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T18:40:00+05:40") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T18:45:00+05:45") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse RFC3339 date with historical or unofficial offsets 90°E to 180°`() {
		val calendar = GregorianCalendar(2002, Calendar.OCTOBER, 2, 13, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("2002-10-02T19:00:00+06:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T19:30:00+06:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T20:00:00+07:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T20:20:00+07:20") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T20:30:00+07:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T21:00:00+08:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T21:30:00+08:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T21:45:00+08:45") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T22:00:00+09:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T22:30:00+09:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T22:45:00+09:45") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T23:00:00+10:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T23:30:00+10:30") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-03T00:00:00+11:00") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2002-10-02T24:30:00+11:30") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse date with microseconds`() {
		val calendar = GregorianCalendar(2020, Calendar.JULY, 27, 17, 31, 4)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("2020-07-27T17:31:04.123") `should equal` Date(calendar.timeInMillis + 123)
	}

	@Test
	fun `parse date with two timezones`() {
		val calendar = GregorianCalendar(2015, Calendar.MARCH, 1, 1, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("GMT-4")

		DateUtil.parseDateString("Sun 01 Mar 2015 01:00:00 GMT-0400 (EDT)") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse date with North America timezones`() {
		val calendar = GregorianCalendar(2008, Calendar.MAY, 17, 11, 42, 16)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Sat, 17 May 2008 06:42:16 EST") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 07:42:16 EDT") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 05:42:16 CST") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 06:42:16 CDT") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 04:42:16 MST") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 05:42:16 MDT") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 03:42:16 PST") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat, 17 May 2008 04:42:16 PDT") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse date with four digit timezones`() {
		val calendar = GregorianCalendar(2008, Calendar.AUGUST, 17, 13, 53, 44)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Sun, 17 Aug 2008 10:23:44 -0330") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sun, 17 Aug 2008 15:53:44 +0200") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Mon, 18 Aug 2008 1:53:44 +1200") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sun, 17 Aug 2008 1:53:44 -1200") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sun, 17 Aug 2008 13:53:44 -0000") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sun, 17 Aug 2008 13:53:44 +0000") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse date with consecutive whitespaces`() {
		val calendar = GregorianCalendar(2008, Calendar.AUGUST, 17, 13, 53, 44)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Sun,      17     Aug      2008    10:23:44    -0330") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Sat,   17     Aug      2008     08:53:44   EST") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse date with tabs and line breaks`() {
		val calendar = GregorianCalendar(2008, Calendar.AUGUST, 17, 13, 53, 22)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Thu, 17 Aug 2008 13:53:22 UTC\n") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("\nThu, 17 Aug 2008 13:53:22 UTC") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Thu, 17 Aug\t2008 13:53:22 UTC") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("Thu, 17\n Aug 2008 13:53:22 UTC") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("\nThu, 17\n Aug\t2008\n\t 13:53:22 UTC\n") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse leap year date`() {
		val calendar = GregorianCalendar(2008, Calendar.FEBRUARY, 29, 22, 36, 44)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Fri, 29 Feb 2008 22:36:44 UTC") `should equal` Date(calendar.timeInMillis)
		DateUtil.parseDateString("2008-03-01T01:36:44+03:00") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse 29th february but not leap year return 1st march`() {
		val calendar = GregorianCalendar(2009, Calendar.MARCH, 1, 22, 36, 44)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("Mon, 29 Feb 2009 22:36:44 UTC") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse date with invalid date delimiter`() {
		val calendar = GregorianCalendar(1985, Calendar.APRIL, 12, 23, 20, 50)
		calendar.timeZone = TimeZone.getTimeZone("UTC")

		DateUtil.parseDateString("1985/04/12T23:20:50.52Z") `should equal` Date(calendar.timeInMillis + 52)
	}

	@Test
	fun `parse simple date trim whitespaces`() {
		val calendar = GregorianCalendar(2014, Calendar.OCTOBER, 8, 9, 0, 0)
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		DateUtil.parseDateString("        Thu, 8 Oct 2014 09:00:00 GMT     ") `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse simple time with hours, minutes and seconds to milliseconds`() {
		val timeString = "01:34:21"

		DateUtil.parseTimeString(timeString) `should equal` (1 * 60 * 60 * 1000) + (34 * 60 * 1000) + (21 * 1000).toLong()
	}

	@Test
	fun `parse simple time with minutes and seconds to milliseconds`() {
		val timeString = "34:21"

		DateUtil.parseTimeString(timeString) `should equal` (34 * 60 * 1000) + (21 * 1000).toLong()
	}

	@Test
	fun `parse simple time with minutes and seconds but hours zero to milliseconds`() {
		val timeString = "00:34:21"

		DateUtil.parseTimeString(timeString) `should equal` (34 * 60 * 1000) + (21 * 1000).toLong()
	}

	@Test
	fun `parse simple time with seconds but hours and minutes zero to milliseconds`() {
		val timeString = "00:00:42"

		DateUtil.parseTimeString(timeString) `should equal` (42 * 1000).toLong()
	}

	@Test
	fun `parse simple time with hours, minutes and seconds is zero to milliseconds`() {
		val timeString = "00:00:00"

		DateUtil.parseTimeString(timeString) `should equal` (0).toLong()
	}

	@Test
	fun `parse simple time with hours but minutes and seconds is zero to milliseconds`() {
		val timeString = "07:00:00"

		DateUtil.parseTimeString(timeString) `should equal` (7 * 60 * 60 * 1000).toLong()
	}

	@Test
	fun `parse simple time with minutes but hours and seconds is zero to milliseconds`() {
		val timeString = "00:59:00"

		DateUtil.parseTimeString(timeString) `should equal` (59 * 60 * 1000).toLong()
	}
}