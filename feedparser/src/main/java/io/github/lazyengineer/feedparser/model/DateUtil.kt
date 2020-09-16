package io.github.lazyengineer.feedparser.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.pow

object DateUtil {

	private val defaultTimezone = TimeZone.getTimeZone("UTC")
	private var dateFormatParser = SimpleDateFormat("", Locale.US)

	private val patters = arrayOf(
			"dd MMM yy HH:mm:ss Z",
			"dd MMM yy HH:mm Z",
			"EEE, dd MMM yyyy HH:mm:ss Z",
			"EEE, dd MMM yyyy HH:mm:ss",

			"yyyy-MM-dd'T'HH:mm:ss.SSS Z",
			"yyyy-MM-dd'T'HH:mm:ss.SSS"
	)

	private val backupPatters = arrayOf(
			"EEE, dd MMMM yyyy HH:mm:ss Z",
			"EEE, dd MMMM yyyy HH:mm:ss",
			"EEEE, dd MMM yyyy HH:mm:ss Z",
			"EEEE, dd MMM yy HH:mm:ss Z",
			"EEEE, dd MMM yyyy HH:mm:ss",
			"EEEE, dd MMM yy HH:mm:ss",
			"EEE MMM d HH:mm:ss yyyy",
			"EEE, dd MMM yyyy HH:mm Z",
			"EEE, dd MMM yyyy HH:mm",
			"EEE, dd MMMM yyyy HH:mm Z",
			"EEE, dd MMMM yyyy HH:mm",
			"EEEE, dd MMM yyyy HH:mm Z",
			"EEEE, dd MMM yy HH:mm Z",
			"EEEE, dd MMM yyyy HH:mm",
			"EEEE, dd MMM yy HH:mm",
			"EEE MMM d HH:mm yyyy",
			"EEE d MMM yyyy HH:mm:ss 'GMT'Z (z)"
	)

	private val backupISO8601Patters = arrayOf(
			"yyyy-MM-dd'T'HH:mm:ss'Z'",
			"yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss",
			"yyyy-MM-ddZ",
			"yyyy-MM-dd"
	)

	fun parseDateString(dateString: String): Date? {
		patters.forEach {
			try {
				val date = parseDate(dateString, it)
				if (date != null) {
					return date
				}
			} catch (e: ParseException) {
				// Nothing
			}
		}

		backupPatters.forEach {
			try {
				val date = parseDate(dateString, it)
				if (date != null) {
					return date
				}
			} catch (e: ParseException) {
				// Nothing
			}
		}

		backupISO8601Patters.forEach {
			try {
				val date = parseDate(dateString, it)
				if (date != null) {
					return date
				}
			} catch (e: ParseException) {
				// Nothing
			}
		}

		return null
	}

	@Throws(ParseException::class)
	private fun parseDate(
		dateString: String,
		pattern: String
	): Date? {
		val stringToParse = dateString.replaceISO8601Timezone()
				.replace('/', '-')
				.replace("\t", " ")
				.replace("\n", " ")
				.replace("( ){2,}+".toRegex(), " ")
				.trim()

		dateFormatParser.applyPattern(pattern)
		dateFormatParser.timeZone = defaultTimezone

		return dateFormatParser.parse(stringToParse)
	}

	fun parseTimeString(time: String): Long {
		val timeParts = time.split(":".toRegex())
				.toTypedArray()
		val millisecondsParts = time.split(".")
		val milliseconds = if (millisecondsParts.size > 1) millisecondsParts.last()
				.toLong() else 0L

		val timeInMilliseconds = timeParts.reversedArray()
				.mapIndexed { index, part ->
					part.toDouble() * 60.toDouble()
							.pow((index.toDouble()))
				}
				.reduce { sum, element ->
					sum + element
				}
				.toLong() * 1000

		return timeInMilliseconds + milliseconds
	}

	private fun String.replaceISO8601Timezone(): String {
		var stringToParse = this
		val iso8601TimezoneBehindUTCRegex = "-([0-2])([0-9]):([0-5])([0-9])".toRegex()
		val iso8601TimezoneAheadUTCRegex = "\\+([0-2])([0-9]):([0-5])([0-9])".toRegex()
		stringToParse = stringToParse.replace(iso8601TimezoneBehindUTCRegex, "-$1$2$3$4")
		stringToParse = stringToParse.replace(iso8601TimezoneAheadUTCRegex, "+$1$2$3$4")

		return stringToParse
	}
}
