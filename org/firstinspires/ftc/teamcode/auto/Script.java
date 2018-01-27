package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.*;
import java.util.ArrayList;
import java.util.HashMap;

/** A list of instructions for autonomously controlling the robot.
 * Also shares states between instructions.
 * @author Chris Muller & Brooks Rady
 */
public class Script {
    private OmniDrive chassis;
    private ArrayList<Instruct> script;
    private HashMap<String, Integer> states;

    /** Constructor
     *
     * @param ch Base chassis for calling instructions on
     */
    public Script (OmniDrive ch) {
        script = new ArrayList<Instruct>();
        states = new HashMap<String, Integer>();
        chassis = ch;
    }

    /** Add an instruction to the end of the script.
     * Adds instruction and sets its script and chassis to this and this'
     * chassis
     * @param i New Instruction
     */
    public void add(Instruct i) {
        i.setScript(this);
        i.setChassis(chassis);
        script.add(i);
    }

    /** Called by Instructions to remember data for use by later conditions.
     * Records an Integer data point with a String key.
     * @param s String key
     * @param i Integer datapoint
     */
    public void addState(String s, Integer i) {
        states.put(s, i);
    }

    /** Called by Conditions to check the value of a previously defined
     * datapoint.
     * Returns the Integer datapoint at a given String key.
     * @param s String key
     * @return Integer datapoint
     */
    public Integer getState(String s) {
        return states.get(s);
    }

    /** Loops through instructions and moves to the next instruction when the
     * current one returns true.
     */
    public void tick() {
        if(script.size() != 0) {
            if(script.get(0).tick()) {
                script.remove(0);
            }
        }
    }
}
