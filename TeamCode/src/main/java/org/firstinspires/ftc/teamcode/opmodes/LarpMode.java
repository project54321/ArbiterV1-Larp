package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.roboCentric;

@TeleOp(name= "LarpMode-v1")
public class LarpMode extends OpMode {
    roboCentric dt = new roboCentric();
    //intake in = new intake();
    //transfer ts = new transfer();
    //shooter out = new shooter();

    double f, s, r;

    @Override
    public void init() {
        dt.init(hardwareMap);
        //in.init(hardwareMap);
        //ts.init(hardwareMap);
        //out.init(hardwareMap);
    }

    @Override
    public void loop () {
        f = -gamepad1.left_stick_y;
        s = gamepad1.left_stick_x;
        r = gamepad1.right_stick_x;

        dt.driveRobotOriented(f, s, r);
    }
}
