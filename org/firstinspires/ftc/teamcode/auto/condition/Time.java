package org.firstinspires.ftc.teamcode.auto.condition;

/** A Condition for running for a set time.
 * @author Chris Muller & Brooks Rady
 */
public class Time extends Condition {
    private long endTime;
    private long initTime;

    /** Constructor
     * @param t Goal time in milliseconds (probably)
     */
    public Time(long t) {
        endTime = t;
    }

    /** Records the initial System time
     *
     */
    public void init() {
        initTime = System.currentTimeMillis();
    }

    /** Compares the difference between the current System time and the Initial
     * System time and the goal duration
     * @return True if time elapsed is greater than or equal to goal duration.
     */
    public boolean loop() {
        return ((System.currentTimeMillis() - initTime) >= endTime);
    }
}
