package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name="OmniDriveAuto", group="Opmode")

public class AutoTest extends OpMode{

    private OmniDrive robot;

    @Override
    public void init() {
        robot = new OmniDrive(hardwareMap);
    }

    @Override
    public void loop() {
        robot.tick();
    }
}
