package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.flower;
import org.firstinspires.ftc.teamcode.Subsystems.intake;
import org.firstinspires.ftc.teamcode.Subsystems.transfer;

@TeleOp(name = "Tele-Larp")
public class LarpModeTele extends OpMode {

    drivetrain dt = new drivetrain();
    intake in = new intake();
    transfer ts = new transfer();
    flower fl = new flower();
    double f, s, r;

    @Override
    public void init() {
        dt.init(hardwareMap);
        in.init(hardwareMap);
        ts.init(hardwareMap);
        fl.init(hardwareMap);
    }

    @Override
    public void loop() {
        f = Math.pow(-gamepad1.left_stick_y, 3);
        s = Math.pow(gamepad1.left_stick_x, 3);
        r = Math.pow(gamepad1.right_stick_x, 3);
        dt.drive(f, s, r);

        if (gamepad1.left_trigger != 0.0 && gamepad1.right_trigger != 0.0) { in.stop(); } //Precautionary
        else if (gamepad1.right_trigger != 0.0) { in.out(); }
        else if(gamepad1.left_trigger != 0.0) { in.in(); }
        else { in.stop(); }

        if (gamepad1.left_bumper && gamepad1.right_bumper) { ts.stop(); } //Precautionary
        else if (gamepad1.right_bumper) { ts.reverse(); }
        else if(gamepad1.left_bumper) { ts.push(); }
        else { ts.stop(); }

        if(gamepad1.y && gamepad1.a) { fl.retract(); } //Precautionary
        else if(gamepad1.y) { fl.drop(); }
        else if (gamepad1.a) { fl.retract(); }


    }

}
