package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.auto.*;
import org.firstinspires.ftc.teamcode.module.*;

/** An abstract class that all other Conditions extend.
 * A Condition can override init() and must override loop().
 * @author Chris Muller & Brooks Rady
 */
public abstract class Condition {
    private boolean firstLoop = true;
    protected Script parent;
    protected OmniDrive chassis;

    /** Run once on the Condition's first tick().
     * Responsible for initializing the Condition.
     */
    public void init() {};

    /** Called on every tick() of the Instruction; returns True when the
     * condition is satisfied.
     * Responsible for evaluating the condition.
     * @return True if the condition has been met.
     */
    public abstract boolean loop();

    /** Called by the Instruction.tick() method; this advances the Condition
     * and checks if it has been satified.
     * @return True if condition has been met.
     */
    public boolean tick() {
        if (firstLoop) {
            init();
            firstLoop = false;
        }
        return loop();
    }

    /** Sets the parent Script that this Condition belongs to.
     * @param p The parent Script
     */
    public void setScript(Script p) {
        parent = p;
    }

    /** Sets the hardware class that this Condition reads.
     * @param ch The OmniDrive this Condition reads.
     */
    public void setChassis(OmniDrive ch) {
        chassis = ch;
    }
}
