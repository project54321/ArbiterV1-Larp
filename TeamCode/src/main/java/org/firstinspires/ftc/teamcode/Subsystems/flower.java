package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class flower {
    private CRServo flower;

    public void init(HardwareMap hM) {
        flower = hM.get(CRServo.class, "flower");
        flower.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drop() {
        flower.setPower(1);
    }

    public void retract() {
        flower.setPower(-1);
    }
}
