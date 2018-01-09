package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Vector;
import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;

public class HeadControl{
    DcMotor neck;

    public HeadControl(HardwareMap map){
        neck = (DcMotor)map.get("neck");
    }

    public void moveHead(int headState){
        switch(headState){
            case 0:
                neck.setPower(0);
                break;
            case 1:
                neck.setPower(0.1);
                break;
            case 2:
                neck.setPower(-0.1);
                break;
        }
    }
}
