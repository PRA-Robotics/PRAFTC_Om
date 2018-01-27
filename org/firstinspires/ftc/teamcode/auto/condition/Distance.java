package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.*;

/** A Condition for traveling a given distance.
 * Is satisfied if the average change in position of each drive motor is greater
 * than or equal to a given distance.
 * @author Chris Muller & Brooks Rady
 */
public class Distance extends Condition {
    private double goalTick;

    /** Constructor.
     * @param d distance to travel in units of motor "ticks".
     * TODO: use an actual unit of distance
     */
    public Distance(double d) {
        goalTick = d;
    }

    /** Resets chassis distance traveled.
     *
     */
    public void init() {
        chassis.resetDistance();
    }

    /** Compares distance goal with chassis's distance traveled.
     * @return True if goal distance has been traveled.
     */
    public boolean loop() {
        return (chassis.getDistance() >= goalTick);
    }
}
