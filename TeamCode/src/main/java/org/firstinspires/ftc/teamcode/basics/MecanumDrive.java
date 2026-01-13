package org.firstinspires.ftc.teamcode.basics;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

/*

 */
public class MecanumDrive {
    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private boolean isFieldOriented;
    private IMU imu;

    public void init(HardwareMap hwMap) {
        init(hwMap, false);
    }
    public void init(HardwareMap hwMap, boolean fieldOriented) {
        isFieldOriented = fieldOriented;

        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hwMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hwMap.dcMotor.get("frontRightMotor");
        backRightMotor = hwMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if(isFieldOriented){
            imu = hwMap.get(IMU.class, "imu");

            RevHubOrientationOnRobot   revOrientation = new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

            imu.initialize(new IMU.Parameters(revOrientation));
        }
    }

    public void driveWithRobotOrientation(double forward, double strafe, double rotate) {
        double fronteftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
//        double maxSpeed = 1.0;

        maxPower = Math.max(maxPower, Math.abs(fronteftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeftMotor.setPower(backLeftPower / maxPower);
        backLeftMotor.setPower(backLeftPower / maxPower);
        frontRightMotor.setPower(frontRightPower / maxPower);
        backRightMotor.setPower(fronteftPower / maxPower);
    }

    public void driveWithFieldOrientation(double forward, double strafe, double rotate) {

    }
}
