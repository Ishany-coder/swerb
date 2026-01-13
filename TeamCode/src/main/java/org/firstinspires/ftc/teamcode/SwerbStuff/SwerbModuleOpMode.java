package org.firstinspires.ftc.teamcode.SwerbStuff;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.geometry.Vector2d;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.SwerbStuff.SwerbModule;

@TeleOp(name="Single Swerve Test")
public class SwerbModuleOpMode extends LinearOpMode {

    private SwerbModule swerve;
    private double maxPower = 1.0;
    private final PIDFCoefficients steerPID = new PIDFCoefficients(0.8, 0, 0, 0); // example values

    @Override
    public void runOpMode() {
        // Initialize the module (replace with your config names and offsets)
        swerve = new SwerbModule(
                new MotorEx(hardwareMap, "drive_motor"),
                new CRServoEx(hardwareMap, "steer_servo"),
                new Vector2d(5, 5), // offset from robot center
                20, // max speed (inches/sec)
                steerPID
        );

        waitForStart();

        while (opModeIsActive()) {
            // Adjust max power with left stick Y
            double powerAdjust = -gamepad1.left_stick_y; // up increases
            maxPower = Math.max(0.1, Math.min(1.0, maxPower + powerAdjust * 0.01));

            // Predefined positions
            if (gamepad1.a) {
                Vector2d forward = new Vector2d(maxPower, 0); // 0° forward
                swerve.updateModuleWithVelocity(forward);
            } else if (gamepad1.b) {
                Vector2d right = new Vector2d(0, maxPower); // 90° sideways
                swerve.updateModuleWithVelocity(right);
            } else {
                // Joystick control
                double x = gamepad1.right_stick_x * maxPower;
                double y = -gamepad1.right_stick_y * maxPower;

                Vector2d moveVec = new Vector2d(x, y);
                swerve.updateModuleWithVelocity(moveVec);
            }

            // Telemetry for debugging
            telemetry.addData("Max Power", maxPower);
            telemetry.addData("Swerve", swerve.getPowerTelemetry());
            telemetry.addData("Angle Error", swerve.getAngleError());
            telemetry.update();
        }
    }
}