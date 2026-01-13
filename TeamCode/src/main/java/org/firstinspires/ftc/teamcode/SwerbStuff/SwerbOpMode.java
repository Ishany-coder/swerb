package org.firstinspires.ftc.teamcode.SwerbStuff;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.geometry.Vector2d;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
@Config
@TeleOp(name="Custom Swerve Test")
public class SwerbOpMode extends LinearOpMode {

    private SwerbModule frontLeft, frontRight, backLeft, backRight;
    private double maxPower = 1.0; // adjustable with joystick Y
    public static PIDFCoefficients steerPID = new PIDFCoefficients(0.8, 0, 0, 0); // example

    @Override
    public void runOpMode() {
        // Initialize motors and servos (replace names with your config)
        frontLeft = new SwerbModule(new MotorEx(hardwareMap, "fl_drive"),
                new CRServoEx(hardwareMap, "fl_steer"),
                new Vector2d(-5, 5), // offset from robot center
                20, steerPID);

        frontRight = new SwerbModule(new MotorEx(hardwareMap, "fr_drive"),
                new CRServoEx(hardwareMap, "fr_steer"),
                new Vector2d(5, 5),
                20, steerPID);

        backLeft = new SwerbModule(new MotorEx(hardwareMap, "bl_drive"),
                new CRServoEx(hardwareMap, "bl_steer"),
                new Vector2d(-5, -5),
                20, steerPID);

        backRight = new SwerbModule(new MotorEx(hardwareMap, "br_drive"),
                new CRServoEx(hardwareMap, "br_steer"),
                new Vector2d(5, -5),
                20, steerPID);

        waitForStart();
        ElapsedTime timer = new ElapsedTime();

        while (opModeIsActive()) {
            // ==========================
            // Power scaling with left stick Y
            // ==========================
            double powerAdjust = -gamepad1.left_stick_y; // up increases
            maxPower = Range.clip(maxPower + powerAdjust * 0.01, 0.1, 1.0); // smooth increment

            // ==========================
            // Predefined positions with A/B
            // ==========================
            if (gamepad1.a) {
                setAllModulesAngle(Math.toRadians(0));
            } else if (gamepad1.b) {
                setAllModulesAngle(Math.toRadians(90));
            } else {
                // ==========================
                // Joystick control for movement
                // ==========================
                double x = gamepad1.right_stick_x; // strafe
                double y = -gamepad1.right_stick_y; // forward/back
                double rotation = -gamepad1.left_stick_x; // rotation

                Vector2d translational = new Vector2d(x * maxPower, y * maxPower);
                Vector2d flVec = translational.plus(new Vector2d(-rotation, rotation));
                Vector2d frVec = translational.plus(new Vector2d(rotation, rotation));
                Vector2d blVec = translational.plus(new Vector2d(-rotation, -rotation));
                Vector2d brVec = translational.plus(new Vector2d(rotation, -rotation));

                frontLeft.updateModuleWithVelocity(flVec);
                frontRight.updateModuleWithVelocity(frVec);
                backLeft.updateModuleWithVelocity(blVec);
                backRight.updateModuleWithVelocity(brVec);
            }

            // ==========================
            // Telemetry
            // ==========================
            telemetry.addData("Max Power", maxPower);
            telemetry.addData("FL", frontLeft.getPowerTelemetry());
            telemetry.addData("FR", frontRight.getPowerTelemetry());
            telemetry.addData("BL", backLeft.getPowerTelemetry());
            telemetry.addData("BR", backRight.getPowerTelemetry());
            telemetry.update();
        }
    }

    private void setAllModulesAngle(double radians) {
        Vector2d vec = new Vector2d(Math.cos(radians) * maxPower, Math.sin(radians) * maxPower);
        frontLeft.updateModuleWithVelocity(vec);
        frontRight.updateModuleWithVelocity(vec);
        backLeft.updateModuleWithVelocity(vec);
        backRight.updateModuleWithVelocity(vec);
    }
}