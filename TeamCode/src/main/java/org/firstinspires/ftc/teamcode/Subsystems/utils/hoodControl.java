package org.firstinspires.ftc.teamcode.Subsystems.utils;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class hoodControl {

    private CRServo hood;

    // Estimated normalized position, not measured physical position.
    private double estimatedPosition = 0.5;

    private double power = 0.5;

    private boolean moving = false;
    private double targetPosition = 0;

    private long lastUpdateTime;

    // Calibration values: determine experimentally.
    public static double MIN_POSITION = 0.0;
    public static double MAX_POSITION = 1.0;

    // Estimated time for a complete servo travel in ms.
    public static double FULL_TRAVEL_MS = 1000.0;

    public void init(HardwareMap hM) {
        hood = hM.get(CRServo.class, "hood");
        hood.setDirection(DcMotorSimple.Direction.REVERSE);
        hood.setPower(0);

        lastUpdateTime = System.currentTimeMillis();
    }

    public void setEstimatedPosition(double position) {
        estimatedPosition = clamp(
                position, MIN_POSITION, MAX_POSITION
        );
    }

    public void moveTo(double position) {
        targetPosition = clamp(
                position, MIN_POSITION, MAX_POSITION
        );

        moving = true;
        lastUpdateTime = System.currentTimeMillis();
    }

    public void update() {
        if (!moving) return;

        long now = System.currentTimeMillis();
        double elapsed = now - lastUpdateTime;
        lastUpdateTime = now;

        double difference = targetPosition - estimatedPosition;

        if (Math.abs(difference) < 0.005) {
            stop();
            return;
        }

        double direction = Math.signum(difference);

        hood.setPower(direction * power);

        double travelRate =
                1.0 / (FULL_TRAVEL_MS * power);

        estimatedPosition +=
                direction * elapsed * travelRate;

        estimatedPosition = clamp(
                estimatedPosition, MIN_POSITION, MAX_POSITION
        );

        if ((direction > 0 && estimatedPosition >= targetPosition) ||
                (direction < 0 && estimatedPosition <= targetPosition)) {
            estimatedPosition = targetPosition;
            stop();
        }
    }

    public void extend() {
        hood.setPower(0.5);
    }

    public void retract() {
        hood.setPower(-0.5);
    }

    public void stop() {
        hood.setPower(0);
        moving = false;
    }

    public double getEstimatedPosition() {
        return estimatedPosition;
    }

    public boolean isMoving() {
        return moving;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}