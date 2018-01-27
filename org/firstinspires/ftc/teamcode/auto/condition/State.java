package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.OmniDrive;

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

    public State(String k, Comp cp, Integer v, Condition ch) {
        key = k;
        value = v;
        child = ch;
        switch(cp) {
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

    public boolean loop() {
        if(parent.getState(key).compareTo(value) == comparison) {
            return child.tick();
        }
        return true;
    }

    @Override
    public void setChassis(OmniDrive ch) {
        super.setChassis(ch);
        child.setChassis(ch);
    }
}
