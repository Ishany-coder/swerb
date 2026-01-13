package org.firstinspires.ftc.teamcode.HardWare;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CrServo {

    private CRServoImplEx servo;
    private double cachingTolerance = 0.0;
    private double lastPower = 0.0;

    public CrServo(HardwareMap hardwareMap, String name) {
        servo = hardwareMap.get(CRServoImplEx.class, name);
        servo.setPower(0);
    }

    /* ---------------- BASIC CONTROL ---------------- */

    public void setSpeed(double power) {
        power = clamp(power, -1.0, 1.0);

        if (Math.abs(power - lastPower) >= cachingTolerance) {
            servo.setPower(power);
            lastPower = power;
        }
    }

    public double getSpeed() {
        return lastPower;
    }

    public void stop() {
        setSpeed(0);
    }

    /* ---------------- DIRECTION ---------------- */

    public void setReversed(boolean reversed) {
        servo.setDirection(
                reversed ? CRServo.Direction.REVERSE : CRServo.Direction.FORWARD
        );
    }

    public boolean isReversed() {
        return servo.getDirection() == CRServo.Direction.REVERSE;
    }

    /* ---------------- CACHING ---------------- */

    public void setCachingTolerance(double tolerance) {
        cachingTolerance = Math.abs(tolerance);
    }

    /* ---------------- UTIL ---------------- */

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}