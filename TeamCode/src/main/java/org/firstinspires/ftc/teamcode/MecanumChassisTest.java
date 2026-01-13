package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Mecanum Chassis Test", group="Test")
public class MecanumChassisTest extends LinearOpMode {
    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

    public void runOpMode() {
        ElapsedTime opmodeRunTime = new ElapsedTime();

        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("All motors are initialized...");
        telemetry.update();

        waitForStart();

        telemetry.addLine("Started...");
        telemetry.update();

        while(opModeIsActive()){
            telemetry.addData("time", "while start at %.1f seconds", opmodeRunTime.seconds());
            frontLeftMotor.setPower(1.0);
            frontRightMotor.setPower(1.0);
            backLeftMotor.setPower(1.0);
            backRightMotor.setPower(1.0);
            telemetry.addData("time", "while end at %.1f seconds", opmodeRunTime.seconds());
            telemetry.update();

            idle();
        }
    }
}
