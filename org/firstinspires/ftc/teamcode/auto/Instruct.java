package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.auto.condition.*;
import org.firstinspires.ftc.teamcode.module.*;

/** An abstract class that all other Instructions extend.
 * An Instruction can override three methods: init(), loop(), and end().
 * @author Chris Muller & Brooks Rady
 */
public abstract class Instruct {
    private boolean firstLoop = true;
    private Condition condition;
    protected Script parent;
    protected OmniDrive chassis;

    /** Constructor
     * @param c The condition the signals when this Instruction should complete.
     */
    public Instruct(Condition c) {
        condition = c;
    }

    /** Run once on the Instruction's first tick().
     * Responsible for initializing the instruction.
     */
    public void init() {};

    /** Called on every tick() of the Instruction.
     * Responsible for actually controlling the robot.
     */
    public void loop() {};

    /** Called once right before the Instruction completes.
     * Responsible for clean-up and adding values to the state.
     */
    public void end() {};

    /** Called by the Script class; this advances the Instruction and checks if
     * it's condition has been satified.
     * @return True if the condition has been met.
     */
    public boolean tick() {
        if (firstLoop) {
            init();
            firstLoop = false;
        }
        loop();
        if (condition.tick()) {
            end();
            return true;
        }
        return false;
    }

    /** Sets the parent Script that this Instruction belongs to.
     * @param p The parent Script
     */
    public void setScript(Script p) {
        condition.setScript(p);
        parent = p;
    }

    /** Sets the hardware class that this Instruction controls.
     * @param ch The OmniDrive this Instruction controls.
     */
    public void setChassis(OmniDrive ch) {
        condition.setChassis(ch);
        chassis = ch;
    }
}
