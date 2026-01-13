package org.firstinspires.ftc.teamcode.HardWare;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Motor {
    private DcMotorEx motor;

    public Motor(HardwareMap hardwareMap, String name) {
        motor = hardwareMap.get(DcMotorEx.class, name);
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /* ---------------- BASIC POWER ---------------- */

    public void setPower(double power) {
        motor.setPower(clamp(power, -1.0, 1.0));
    }

    public double getPower() {
        return motor.getPower();
    }

    /* ---------------- DIRECTION ---------------- */

    public void setReversed(boolean reversed) {
        motor.setDirection(
                reversed ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD
        );
    }

    public boolean isReversed() {
        return motor.getDirection() == DcMotor.Direction.REVERSE;
    }

    /* ---------------- ENCODERS ---------------- */

    public int getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /* ---------------- RUN MODES ---------------- */

    public void runWithoutEncoder() {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runUsingEncoder() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runToPosition() {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /* ---------------- POSITION CONTROL ---------------- */

    public void setTargetPosition(int ticks) {
        motor.setTargetPosition(ticks);
    }

    public int getTargetPosition() {
        return motor.getTargetPosition();
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    /* ---------------- VELOCITY ---------------- */

    public void setVelocity(double ticksPerSecond) {
        motor.setVelocity(ticksPerSecond);
    }

    public double getVelocity() {
        return motor.getVelocity();
    }

    /* ---------------- BRAKE / FLOAT ---------------- */

    public void setBrake(boolean brake) {
        motor.setZeroPowerBehavior(
                brake ? DcMotor.ZeroPowerBehavior.BRAKE : DcMotor.ZeroPowerBehavior.FLOAT
        );
    }

    public boolean isBraking() {
        return motor.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.BRAKE;
    }

    /* ---------------- CURRENT & POWER ---------------- */

    public double getCurrent() {
        return motor.getCurrent(CurrentUnit.AMPS);
    }

    /* ---------------- UTIL ---------------- */

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}