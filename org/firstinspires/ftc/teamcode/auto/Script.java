package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Script {
    private OmniDrive chassis;
    private ArrayList<Instruct> script;
    private HashMap<String, Integer> states;

    public Script (OmniDrive ch) {
        script = new ArrayList<Instruct>();
        states = new HashMap<String, Integer>();
        chassis = ch;
    }

    public void add(Instruct i) {
        i.setScript(this);
        i.setChassis(chassis);
        script.add(i);
    }

    public void addState(String s, Integer i) {
        states.put(s, i);
    }

    public Integer getState(String s) {
        return states.get(s);
    }

    public void tick() {
        if(script.size() != 0) {
            if(script.get(0).tick()) {
                script.remove(0);
            }
        }
    }
}
