package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.module.*;
import org.firstinspires.ftc.teamcode.auto.*;
import org.firstinspires.ftc.teamcode.auto.condition.*;
import java.util.ArrayList;

@Autonomous(name="AutoTest", group="Opmode")

public class AutoTest extends OpMode{

    private OmniDrive chassis;
    //private LinearArm arm;
    private ArrayList<Instruct> script;

    private long startTime;

    private long deltaTime;

    @Override
    public void init() {
        script = new ArrayList<Instruct>();
        chassis = new OmniDrive(hardwareMap);
        //arm = new LinearArm(hardwareMap);

        script.add(new MoveEye(chassis, MoveEye.Direction.DOWN, new EyeAtPosition(chassis)));
        script.add(new Jewel(chassis, Jewel.Color.BLUE, new Time(50));
        script.add(new MoveEye(chassis, MoveEye.Direction.UP, new EyeAtPosition(chassis)));
        //script.add(new Drive(chassis, OmniDrive.Direction.F, new Distance(chassis, 1806.0)));
        //script.add(new Wait(chassis, new Time(100)));
        //script.add(new Drive(chassis, OmniDrive.Direction.B, new Distance(chassis, 1806.0)));
        //script.add(new Wait(chassis, new Time(100)));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        if(script.size() != 0) {
            if(script.get(0).tick()) {
                script.remove(0);
            }
        }
        telemetry.addData("Arm: ", chassis.tick());
        //arm.tick();
    }
}
