package org.firstinspires.ftc.teamcode.HardWare;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Servo {

    private com.qualcomm.robotcore.hardware.Servo servo;

    // Optional limits (good for protecting mechanisms)
    private double minPos = 0.0;
    private double maxPos = 1.0;

    public Servo(HardwareMap hardwareMap, String name) {
        servo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, name);
    }

    // Set servo using raw position (0.0 - 1.0)
    public void setPosition(double position) {
        position = clamp(position, minPos, maxPos);
        servo.setPosition(position);
    }

    // Get current servo position
    public double getPosition() {
        return servo.getPosition();
    }

    // Set safe movement range
    public void setLimits(double min, double max) {
        minPos = clamp(min, 0.0, 1.0);
        maxPos = clamp(max, 0.0, 1.0);
    }

    // Utility clamp
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}