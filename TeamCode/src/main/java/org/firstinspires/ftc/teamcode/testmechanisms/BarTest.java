package org.firstinspires.ftc.teamcode.testmechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BarTest {
    Servo bar;
    public static double POS = 0;
    public void init(HardwareMap hm) {
        bar = hm.get(Servo.class, "bar");
    }
    public void Loop() {
        bar.setPosition(POS);
    }
}
