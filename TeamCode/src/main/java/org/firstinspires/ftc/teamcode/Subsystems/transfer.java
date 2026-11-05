package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class transfer {
    private DcMotor ts;
    public void init(HardwareMap hM) {
        ts = hM.get(DcMotor.class, "transfer");
        ts.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ts.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ts.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //ts.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void push() {
        ts.setPower(1);
    }

    public void reverse() {
        ts.setPower(-1);
    }

    public void stop() {
        ts.setPower(0);
    }
}
