package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class shooter {
    private DcMotorEx flywheel;

    // Update these with values tuned from your flywheelTuning OpMode
    public static double P = 0.0;
    public static double I = 0.0;
    public static double D = 0.0;
    public static double F = 0.0;

    public void init(HardwareMap hardwareMap) {
        flywheel = hardwareMap.get(DcMotorEx.class, "shooter");
        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel.setDirection(DcMotorEx.Direction.REVERSE);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, I, D, F);
        flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    public void setVelocity(double velocity) {
        flywheel.setVelocity(velocity);
    }

    public void stop() {
        flywheel.setPower(0);
    }

    public double getVelocity() {
        return flywheel.getVelocity();
    }

    public boolean isAtVelocity(double targetVelocity, double tolerance) {
        return Math.abs(flywheel.getVelocity() - targetVelocity) <= tolerance;
    }
}