package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

/** An Instruction that stops all of the robot's motors and waits a given amount
 * milliseconds before continuing.
 * @author Chris Muller & Brooks Rady
 */
public class Stop extends Instruct {

    /** Constructor
     * @param c The condition for advancing the Instruction
     */
    public Stop (Condition c) {
        super(c);
    }

    /** Continously tells the robot to stop all of its motors.
     *  This function gradually decelerates the robot to a stop.
     */
    public void loop() {
        chassis.stop();
    }
}
