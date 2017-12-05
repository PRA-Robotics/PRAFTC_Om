package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.module.*;
import org.firstinspires.ftc.teamcode.auto.*;
import java.util.ArrayList;

@Autonomous(name="OmniDriveAuto", group="Opmode")

public class AutoTest extends OpMode{

    private OmniDrive drive;
    //private LinearArm arm;
    private ArrayList<Instruct> script;

    private long startTime;

    private long deltaTime;

    @Override
    public void init() {
        script = new ArrayList<Instruct>();
        drive = new OmniDrive(hardwareMap);
        //arm = new LinearArm(hardwareMap);

        script.add(new DriveDist(drive, 1, 100));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        telemetry.addData("Delta Time: ", deltaTime);
        telemetry.update();
        if(script.size() != 0) {
            if(script.get(0).tick()) {
                script.remove(0);
                deltaTime = (System.currentTimeMillis() - startTime);
            }
        }
        drive.tick();
        //arm.tick();
    }
}
