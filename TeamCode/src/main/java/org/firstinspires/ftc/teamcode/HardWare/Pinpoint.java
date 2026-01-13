package org.firstinspires.ftc.teamcode.HardWare;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

public class Pinpoint {

    private pinpointDriver odo;

    public Pinpoint(HardwareMap hardwareMap, String name) {
        odo = hardwareMap.get(pinpointDriver.class, name);
    }

    /* ---------------- SETUP ---------------- */

    public void setOffsets(double xOffset, double yOffset, DistanceUnit unit) {
        odo.setOffsets(xOffset, yOffset, unit);
    }

    public void setPodType(pinpointDriver.GoBildaOdometryPods podType) {
        odo.setEncoderResolution(podType);
    }

    public void setCustomEncoderResolution(double ticksPerUnit, DistanceUnit unit) {
        odo.setEncoderResolution(ticksPerUnit, unit);
    }

    public void setEncoderDirections(
            pinpointDriver.EncoderDirection xDir,
            pinpointDriver.EncoderDirection yDir
    ) {
        odo.setEncoderDirections(xDir, yDir);
    }

    /* ---------------- UPDATE ---------------- */

    public void update() {
        odo.update();
    }

    public void updateHeadingOnly() {
        odo.update(pinpointDriver.ReadData.ONLY_UPDATE_HEADING);
    }

    /* ---------------- POSITION ---------------- */

    public Pose2D getPose() {
        return odo.getPosition();
    }

    public double getX(DistanceUnit unit) {
        return odo.getPosition().getX(unit);
    }

    public double getY(DistanceUnit unit) {
        return odo.getPosition().getY(unit);
    }

    public double getHeading(AngleUnit unit) {
        return odo.getPosition().getHeading(unit);
    }

    /* ---------------- VELOCITY ---------------- */

    public double getXVelocity(DistanceUnit unit) {
        return odo.getVelX(unit);
    }

    public double getYVelocity(DistanceUnit unit) {
        return odo.getVelY(unit);
    }

    public double getHeadingVelocity(UnnormalizedAngleUnit unit) {
        return odo.getHeadingVelocity(unit);
    }

    /* ---------------- RESET / CALIBRATION ---------------- */

    public void resetPoseAndIMU() {
        odo.resetPosAndIMU();
    }

    public void recalibrateIMU() {
        odo.recalibrateIMU();
    }

    /* ---------------- DEVICE INFO ---------------- */

    public Object getStatus() {
        return odo.getDeviceStatus();
    }

    public double getFrequency() {
        return odo.getFrequency();
    }

    public int getDeviceVersion() {
        return odo.getDeviceVersion();
    }

    public double getXOffset(DistanceUnit unit) {
        return odo.getXOffset(unit);
    }

    public double getYOffset(DistanceUnit unit) {
        return odo.getYOffset(unit);
    }

    public double getYawScalar() {
        return odo.getYawScalar();
    }
}