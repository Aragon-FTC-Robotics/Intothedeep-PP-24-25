package org.firstinspires.ftc.teamcode.testmechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawTest {
    Servo claw;
    public static double POS = 0.737;
    public void init(HardwareMap hm) {
        claw = hm.get(Servo.class, "intakeWrist");
    }
    public void Loop() {
        claw.setPosition(POS);
    }
}
