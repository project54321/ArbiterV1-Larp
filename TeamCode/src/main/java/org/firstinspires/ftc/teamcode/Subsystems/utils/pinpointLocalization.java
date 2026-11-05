
package org.firstinspires.ftc.teamcode.Subsystems.utils;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class pinpointLocalization {

    private GoBildaPinpointDriver pinpoint;

    public void init(HardwareMap hardwareMap) {

        pinpoint = hardwareMap.get(
                GoBildaPinpointDriver.class,
                "pinpoint"
        );

        // Offsets are in millimeters.
        // Replace zeroes with measured mounting offsets.
        pinpoint.setOffsets(0, 0, DistanceUnit.MM);

        pinpoint.setEncoderResolution(
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
        );

        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );

        // Robot must be stationary during initialization.
        pinpoint.resetPosAndIMU();
    }

    public void update() {
        pinpoint.update();
    }

    public double getX() {
        return pinpoint.getPosition().getX(DistanceUnit.INCH);
    }

    public double getY() {
        return pinpoint.getPosition().getY(DistanceUnit.INCH);
    }

    public double getHeading() {
        return pinpoint.getPosition().getHeading(
                AngleUnit.DEGREES
        );
    }

    public Pose2D getPose() {
        return pinpoint.getPosition();
    }

    public void setFieldPosition(
            double x, double y, double heading
    ) {
        pinpoint.setPosition(
                new Pose2D(
                        DistanceUnit.INCH,
                        x,
                        y,
                        AngleUnit.DEGREES,
                        heading
                )
        );
    }
}
