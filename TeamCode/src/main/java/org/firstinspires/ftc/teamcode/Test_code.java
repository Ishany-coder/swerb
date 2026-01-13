package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Nishant_Servo")
public class Test_code extends LinearOpMode {

     private DcMotor motor;

     @Override
     public void runOpMode() {

          motor = hardwareMap.get(DcMotor.class, "motor");

          waitForStart();
          if (opModeIsActive()) {
               while(opModeIsActive()) {
                    motor.setPower(1.0);
                    sleep(100);
                    motor.setPower(-1.0);
                    sleep(100);

               }
          }













     }







     }
