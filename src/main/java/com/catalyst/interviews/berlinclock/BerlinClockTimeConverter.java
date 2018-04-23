package com.catalyst.interviews.berlinclock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Converts a time string in a 24h format to a Berlin Clock timestamp
 * 
 * Example input is: 23:59:59
 * 
 * Example output:
 * 
 * Y
 * RRRR
 * RRRR
 * OOOOOOOOOOO
 * OOOO
 * 
 * where:
 * Y = yellow light on
 * R = red light on
 * O = light is off (this is the letter 'O' not the number zero 0)
 * 
 * 
 * The Berlin Clock timestamp will be output on 5 lines.
 * 
 * Clock Tick (every 2 secs)
 * ---
 * Hours (O-2O)
 * Hours (remaining 4, 21-24)
 * ---
 * Minutes (O-55)
 * Minutes (remaining 4, 56-59)
 * 
 * Line 1: One Light => Y/O -> has value=Y on even seconds
 * 
 * Line 2: Four Lights => R/O -> each light is worth 5 hours, and they add together
 * 
 * Line 3: Four Lights => R/O -> each light is worth 1 hour, and they add together
 * Line 2 and Line 2 sum up to 24 hours
 * 
 * Line 4: Eleven Lights => Y/R/O -> each light is worth 5 minutes, 15/3O/45 mins are marked with a red light
 * 
 * Line 5: Four Lights => Y/O -> each light is worth 1 minute
 **/
public class BerlinClockTimeConverter implements TimeConverter {

  private static final String TIME_PATTERN = "kk:mm:ss";

  public BerlinClockTimeConverter() {
  }


  /**
   * Converts a time string in a 24h format to a Berlin Clock timestamp
   *
   * @param time - a time string in HH:mm:ss format
   * @return a string representation of the Berlin Clock
   */
  @Override
  public String convertTime(String time) {
    Objects.requireNonNull(time, "The Time string can not be null");


    Time timeOfDay = parseTime(time);

    return new BerlinClock()
      .seconds(timeOfDay.seconds)
      .hours(timeOfDay.hours)
      .minutes(timeOfDay.minutes)
      .toString();
  }

  /**
   * Parse the time expression and return a Time object
   * that holds the hours/minutes/seconds separately with the appropriate values
   * 
   * @param time
   * @return a Time object
   */
  private Time parseTime(final String time) {

    try {
      // simple date format is not thread safe so we need a new instance every time
      Date timeToConvert = new SimpleDateFormat(TIME_PATTERN).parse(time);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(timeToConvert);

      // hour 24 is set to zero - so we must manually inspect the string
      // by this time we know for sure the string is valid so it's safe

      final int hourOfDay = time.startsWith("24") ? 24 : calendar.get(Calendar.HOUR_OF_DAY);
      final int minute = calendar.get(Calendar.MINUTE);
      final int second = calendar.get(Calendar.SECOND);

      return new Time (hourOfDay, minute, second);

    } catch (ParseException e) {
      throw new IllegalArgumentException(
        "The given time [" + time + "] does not match the " + TIME_PATTERN + " format");
    }
  }

  class Time {
    
    final int hours;
    final int minutes;
    final int seconds;

    public Time(int hours, int minutes, int seconds) {
      this.hours = hours;
      this.minutes = minutes;
      this.seconds = seconds;
    }
  }
}
