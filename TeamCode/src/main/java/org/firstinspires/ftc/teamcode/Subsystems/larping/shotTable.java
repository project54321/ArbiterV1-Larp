
package org.firstinspires.ftc.teamcode.Subsystems.larping;

public class shotTable {

    public static class ShotPoint {

        public final double distance;
        public final double velocity;
        public final double hoodPosition;

        public ShotPoint(
                double distance,
                double velocity,
                double hoodPosition
        ) {
            this.distance = distance;
            this.velocity = velocity;
            this.hoodPosition = hoodPosition;
        }
    }

    // Distance: inches
    // Velocity: encoder ticks per second
    // Hood position: estimated normalized position

    // Zeroes are placeholders, NOT tested shooting settings.
    public static final ShotPoint[] POINTS = {
            new ShotPoint(24, 0, 0),
            new ShotPoint(36, 0, 0),
            new ShotPoint(48, 0, 0),
            new ShotPoint(60, 0, 0),
            new ShotPoint(72, 0, 0)
    };

    public static boolean isValid() {

        if (POINTS.length < 2) return false;

        for (int i = 0; i < POINTS.length; i++) {

            ShotPoint point = POINTS[i];

            if (point.distance <= 0 ||
                    point.velocity < 0 ||
                    point.hoodPosition < 0 ||
                    point.hoodPosition > 1) {
                return false;
            }

            if (i > 0 &&
                    point.distance <= POINTS[i - 1].distance) {
                return false;
            }
        }

        return true;
    }
}
