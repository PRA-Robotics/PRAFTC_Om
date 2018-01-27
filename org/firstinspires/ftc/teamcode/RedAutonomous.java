package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.module.*;
import org.firstinspires.ftc.teamcode.auto.*;
import org.firstinspires.ftc.teamcode.auto.condition.*;
import java.util.ArrayList;

@Autonomous(name="RedAutonomous", group="Opmode")

public class RedAutonomous extends OpMode{

    private OmniDrive chassis;
    private Script script;

    @Override
    public void init() {
        chassis = new OmniDrive(hardwareMap);
        script = new Script(chassis);

        script.add(new MoveEye(MoveEye.Direction.DOWN, new EyeAtPosition()));
        script.add(new Jewel(Jewel.Color.BLUE, new Time(1000)));
        script.add(new Drive(OmniDrive.Direction.CC, new State("turnLeft", State.Comp.EQ, 1, new Distance(200))));
        script.add(new Drive(OmniDrive.Direction.C, new State("turnLeft", State.Comp.EQ, 0, new Distance(200))));
        script.add(new Wait(new Time(200)));
        script.add(new MoveEye(MoveEye.Direction.UP, new EyeAtPosition()));
        script.add(new Drive(OmniDrive.Direction.C, new State("turnLeft", State.Comp.EQ, 1, new Distance(200))));
        script.add(new Drive(OmniDrive.Direction.CC, new State("turnLeft", State.Comp.EQ, 0, new Distance(200))));
        script.add(new Wait(new Time(200)));
        script.add(new Drive(OmniDrive.Direction.R, new Distance(800)));
        script.add(new Drive(OmniDrive.Direction.F, new Distance(100)));

        script.add(new Wait(new Time(1000)));
    }

    @Override
    public void loop() {
        script.tick();
        telemetry.addData("Is Blue?", chassis.isBlue());
        telemetry.addData("Distance", chassis.getDistance());
    }
}
