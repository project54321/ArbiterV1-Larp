package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class drivetrain {
    private DcMotor fl, fr, bl, br;
    private double flP, frP, blP, brP, max;

    public void init(HardwareMap hM) {
        fl = hM.get(DcMotor.class, "fl");
        fr = hM.get(DcMotor.class, "fr");
        bl = hM.get(DcMotor.class, "bl");
        br = hM.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void drive(double f, double s, double r) {
        flP = f + s + r;
        frP = f - s - r;
        blP = f - s + r;
        brP = f + s - r;

        max = Math.max(
                Math.max(Math.abs(flP), Math.abs(frP)),
                Math.max(Math.abs(blP), Math.abs(brP))
        );

        if (max > 1.0) {
            flP /= max;
            frP /= max;
            blP /= max;
            brP /= max;
        }

        fl.setPower(flP);
        fr.setPower(frP);
        bl.setPower(blP);
        br.setPower(brP);
    }
}
