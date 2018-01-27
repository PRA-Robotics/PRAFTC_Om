package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.Script;

/** A Condition which checks its parent script for data.
 * Skips Instruction if state comparison is not satisfied, otherwise runs
 * instruction with child condition.
 * @author Brooks Rady & Chris Muller
 */
public class State extends Condition {
    private String key;
    private Integer value;
    private int comparison;
    private Condition child;

    public enum Comp {
        LT,
        EQ,
        GT
    }

    /** Constructor
     * @param k Key for checking parent script data
     * @param cp Enum for corresponding comparator (<, =, >)
     * @param v Value to compare datapoint against
     * @param ch Child Condition to run if comparison is satisfied
     */
    public State(String k, Comp cp, Integer v, Condition ch) {
        key = k;
        value = v;
        child = ch;
        switch(cp) { //god save us
            case LT:
                comparison = -1;
                break;
            case EQ:
                comparison = 0;
                break;
            case GT:
                comparison = 1;
                break;
        }
    }

    /** Ticks child condition if comparison succeeded.
     * @return True if comparison failed or child condition is met.
     * TODO: move evaluation to init, set boolean
     */
    public boolean loop() {
        if(parent.getState(key).compareTo(value) == comparison) {
            return child.tick();
        }
        return true;
    }

    /** Sets the parent Script that this Condition and its child belongs to.
     * @param p The parent Script.
     * TODO: Test nested state calls.
     */
    @Override
    public void setScript(Script p) {
        super.setScript(p);
        child.setScript(p);
    }

    /** Sets the hardware class that this Condition and its child reads.
     * @param ch The OmniDrive this Condition and its child reads.
     */
    @Override
    public void setChassis(OmniDrive ch) {
        super.setChassis(ch);
        child.setChassis(ch);
    }

}
