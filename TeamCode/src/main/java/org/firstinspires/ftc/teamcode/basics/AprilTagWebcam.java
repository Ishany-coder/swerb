package org.firstinspires.ftc.teamcode.basics;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AprilTagWebcam {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    private List<AprilTagDetection> detectedTags = new ArrayList<>();

    private Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry, String cameraName) {
        this.telemetry = telemetry;

        aprilTagProcessor = new AprilTagProcessor.Builder().setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();

        visionPortal = new VisionPortal.Builder().
                setCamera(hardwareMap.get(WebcamName.class, cameraName))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(aprilTagProcessor).build();

    }

    public void update() {
        detectedTags = aprilTagProcessor.getDetections();
    }

    public List<AprilTagDetection> getDetectedTags() {
        return detectedTags;
    }

    public void displayDetectionTelemetry(AprilTagDetection detectionId) {
        if (detectionId == null) {
            return;
        }

        // Step through the list of detections and display info for each one.
        if (detectionId.metadata != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", detectionId.id, detectionId.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detectionId.ftcPose.x, detectionId.ftcPose.y, detectionId.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detectionId.ftcPose.pitch, detectionId.ftcPose.roll, detectionId.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detectionId.ftcPose.range, detectionId.ftcPose.bearing, detectionId.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detectionId.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detectionId.center.x, detectionId.center.y));
        }
    }

    public Optional<AprilTagDetection> getTagBySpecification(int id) {
        return detectedTags.stream()
                .filter(detection -> detection.id == id)
                .findFirst();
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}
