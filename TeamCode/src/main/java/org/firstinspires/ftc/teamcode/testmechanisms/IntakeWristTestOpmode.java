package org.firstinspires.ftc.teamcode.testmechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="IntakeWrist", group = "testing")
public class IntakeWristTestOpmode extends LinearOpMode {
    public IntakeWristTest intakeWrist = new IntakeWristTest();
    @Override
    public void waitForStart() {
        super.waitForStart();
    }
    @Override
    public void runOpMode() {
        intakeWrist.init(hardwareMap);

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            intakeWrist.Loop();
            telemetry.addData("Last commanded position:", "%.3f", intakeWrist.POS);
            telemetry.update();
        }
    }
}
