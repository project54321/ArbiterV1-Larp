package org.firstinspires.ftc.teamcode.subsystems.tuning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class shooterTuning extends OpMode {
    public DcMotorEx flywheel;

    //P - Proportion - Determines how much power to give to the motor to accelerate
    //I - Integral - Mostly be set to 0. Really Really small value based on accumulated error;
    //D - Derivative - Dampening force to prevent it from overshooting the target velocity.
    //F - Feed Forward - Minimum force u need to keep the motor from moving by gravity.

    public double highVelocity = 1500;
    public double lowVelocity = 900;

    double curTargetVelocity = highVelocity;

    double f = 0;
    double p = 0;

    double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};

    int stepIndex = 1;

    @Override
    public void init() {
        flywheel = hardwareMap.get(DcMotorEx.class, "motor");
        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel.setDirection(DcMotorEx.Direction.REVERSE);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p, 0, 0, f);
        flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    @Override
    public void loop() {
        if (gamepad1.yWasPressed()) {
            if(curTargetVelocity == highVelocity) {
                curTargetVelocity = lowVelocity;
            }
            else {
                curTargetVelocity = highVelocity;
            }
        }

        if (gamepad1.bWasPressed()) {
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            f -= stepSizes[stepIndex];
        }

        if (gamepad1.dpadRightWasPressed()) {
            f += stepSizes[stepIndex];
        }

        if (gamepad1.dpadUpWasPressed()) {
            p += stepSizes[stepIndex];
        }

        if (gamepad1.dpadDownWasPressed()) {
            p -= stepSizes[stepIndex];
        }

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p, 0, 0, f);
        flywheel.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        flywheel.setVelocity(curTargetVelocity);

        double curVelocity = flywheel.getVelocity();
        double error = curTargetVelocity-curVelocity;

        telemetry.addData("Target Velocity", curTargetVelocity);
        telemetry.addData("Current Velocity", "%.2f", curVelocity);
        telemetry.addData("Error", "%.2f", error);
        telemetry.addData("Tuning P", "%.4f (D-Pad U/D)", p);
        telemetry.addData("Tuning F", "%.4f (D-Pad L/R)", f);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);
    }
}
