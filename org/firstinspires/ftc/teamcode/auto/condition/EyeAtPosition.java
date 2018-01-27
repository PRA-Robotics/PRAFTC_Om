package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.OmniDrive;

/** A Condition for use with MoveEye() which checks that the movement is done.
 * @author Brooks Rady & Chris Muller
 */
public class EyeAtPosition extends Condition {

    /** Constructor
     * Empty constructor
     */
    public EyeAtPosition() {
    }

    /** Checks that eye movement is done.
     * @return True if eye movement is done.
     */
    public boolean loop() {
        return chassis.eyeDone();
    }
}
