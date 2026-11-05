package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class roboCentric {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    public void init(HardwareMap hM) {
        //Instantiation
        fl = hM.get(DcMotor.class, "fl");
        fr = hM.get(DcMotor.class, "fr");
        bl = hM.get(DcMotor.class, "bl");
        br = hM.get(DcMotor.class, "br");

        //Zero Power Behavior
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Reset Encoders
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Run Modes
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set Directions
        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
    }

    public void driveRobotOriented(double f, double s, double r) {
        double flPower = f + s + r;
        double frPower = f - s - r;
        double blPower = f - s + r;
        double brPower = f + s - r;

        double max = Math.max(
                Math.max(Math.abs(flPower), Math.abs(frPower)),
                Math.max(Math.abs(blPower), Math.abs(brPower))
        );

        if (max > 1.0) {
            flPower /= max;
            frPower /= max;
            blPower /= max;
            brPower /= max;
        }
        //Your power always has to end up between 0 and 1.
        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }
}
