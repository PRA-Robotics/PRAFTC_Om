package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.module.*;

@Autonomous(name="SpeedTest", group="Opmode")
public class SpeedTest extends OpMode {
    private Motor wheel;

    @Override
    public void init() {
        wheel = new Motor((DcMotor)hardwareMap.get("A"));
    }

    @Override public void loop() {
        telemetry.addData("Memes: ", wheel.updateSpeed(1));

    }
}
