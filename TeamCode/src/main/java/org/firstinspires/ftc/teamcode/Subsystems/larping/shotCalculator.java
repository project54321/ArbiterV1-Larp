package org.firstinspires.ftc.teamcode.Subsystems.larping;

public class shotCalculator {

    public static class ShotSolution {

        public final double distance;
        public final double velocity;
        public final double hoodPosition;

        public ShotSolution(
                double distance,
                double velocity,
                double hoodPosition
        ) {
            this.distance = distance;
            this.velocity = velocity;
            this.hoodPosition = hoodPosition;
        }
    }

    public static double calculateDistance(
            double robotX,
            double robotY,
            double targetX,
            double targetY
    ) {
        return Math.hypot(
                targetX - robotX,
                targetY - robotY
        );
    }

    public static ShotSolution calculate(double distance) {

        if (!shotTable.isValid()) {
            throw new IllegalStateException(
                    "Shot table is invalid or contains placeholders"
            );
        }

        shotTable.ShotPoint[] points = shotTable.POINTS;

        if (distance <= points[0].distance) {
            return convert(points[0], distance);
        }

        int last = points.length - 1;

        if (distance >= points[last].distance) {
            return convert(points[last], distance);
        }

        for (int i = 0; i < last; i++) {

            shotTable.ShotPoint a = points[i];
            shotTable.ShotPoint b = points[i + 1];

            if (distance >= a.distance &&
                    distance <= b.distance) {

                double t =
                        (distance - a.distance) /
                                (b.distance - a.distance);

                double velocity = lerp(
                        a.velocity, b.velocity, t
                );

                double hood = lerp(
                        a.hoodPosition, b.hoodPosition, t
                );

                return new ShotSolution(
                        distance,
                        velocity,
                        hood
                );
            }
        }

        throw new IllegalStateException(
                "No interpolation interval found"
        );
    }

    private static double lerp(
            double a, double b, double t
    ) {
        return a + (b - a) * t;
    }

    private static ShotSolution convert(
            shotTable.ShotPoint point,
            double requestedDistance
    ) {
        return new ShotSolution(
                requestedDistance,
                point.velocity,
                point.hoodPosition
        );
    }
}