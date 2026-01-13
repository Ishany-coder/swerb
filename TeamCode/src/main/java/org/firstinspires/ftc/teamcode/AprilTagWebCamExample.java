package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.basics.AprilTagWebcam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.Optional;


@Autonomous
public class AprilTagWebCamExample extends OpMode {
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();


    @Override
    public void init() {
        aprilTagWebcam.init(hardwareMap, telemetry, "webcam_1");
    }


    @Override
    public void loop() {
        aprilTagWebcam.update();

        Optional<AprilTagDetection> id20 = aprilTagWebcam.getTagBySpecification(20);
        if (!id20.isPresent()) {
            telemetry.addLine("Detection is empty");
            telemetry.update();
        } else {
            aprilTagWebcam.displayDetectionTelemetry(id20.get());
        }

    }
}
