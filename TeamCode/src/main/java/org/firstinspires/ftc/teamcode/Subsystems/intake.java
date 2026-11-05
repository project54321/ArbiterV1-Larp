package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {
    private DcMotor in;
    public void init(HardwareMap hM) {
        in = hM.get(DcMotor.class, "intake");
        in.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        in.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        in.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        in.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void in() {
        in.setPower(1);
    }

    public void out() {
        in.setPower(-1);
    }

    public void stop() {
        in.setPower(0);
    }
}
