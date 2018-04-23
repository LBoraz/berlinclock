package com.catalyst.interviews.berlinclock;

/**
 * The Clock Layout is:
 *
 * 0: SECONDS
 * 1: HOURS
 * 2: HOURS
 * 3: MINUTES
 * 4: MINUTES
 *
 * @author Luca Borzani <l.borzani@dodax.com>
 **/
public class BerlinClock {


  private static final String LIGHT_OFF = "O";
  private static final String YELLOW_LIGHT = "Y";
  private static final String RED_LIGHT = "R";

  // an array of five rows
  // each row has a different number of "lights" (columns)
  // row 1 = 1 light
  // row 2 = 4 lights
  // row 3 = 4 lights
  // row 4 = 11 lights
  // row 5 = 4 lights
  private final String[][] clock;

  public BerlinClock() {
    clock = new String[][] {
      {LIGHT_OFF},
      {LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF},
      {LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF},
      {LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF,
        LIGHT_OFF, LIGHT_OFF, LIGHT_OFF},
      {LIGHT_OFF, LIGHT_OFF, LIGHT_OFF, LIGHT_OFF}
    };
  }

  /**
   * Sets the seconds of the clock
   *
   * @param second
   */
  public BerlinClock seconds(int second) {
    // clock seconds
    if (second % 2 == 0) {
      clock[0][0] = YELLOW_LIGHT;
    }

    return this;
  }

  /**
   * Sets the hours of the clock
   *
   * @param hourOfDay
   */
  public BerlinClock hours(int hourOfDay) {
    // hours - line 1
    int lights = hourOfDay / 5;
    for (int i = 0; i < lights; i++) {
      clock[1][i] = RED_LIGHT;
    }
    // hours - line 2
    lights = hourOfDay % 5;
    for (int i = 0; i < lights; i++) {
      clock[2][i] = RED_LIGHT;
    }

    return this;
  }

  /**
   * Sets the minutes of the clock
   *
   * @param minute
   */
  public BerlinClock minutes(int minute) {
    // minutes - line 1
    int lights = minute / 5;
    for (int i = 0; i < lights; i++) {
      clock[3][i] = (i == 0 || ((i + 1) % 3 != 0)) ? YELLOW_LIGHT : RED_LIGHT;
    }
    // minutes - line 2
    lights = minute % 5;
    for (int i = 0; i < lights; i++) {
      clock[4][i] = YELLOW_LIGHT;
    }

    return this;
  }

  /**
   * String representation of the berlin clock
   *
   * @return
   */
  @Override
  public String toString() {
    StringBuilder repr = new StringBuilder();
    for (int row = 0; row < clock.length; row++) {
      for (int col = 0; col < clock[row].length; col++) {
        repr.append(clock[row][col]);
      }
      if (row + 1 < clock.length) {
        repr.append("\n");
      }
    }

    return repr.toString();
  }
}
