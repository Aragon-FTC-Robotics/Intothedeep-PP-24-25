package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeWrist {
    Servo intakeWrist;
    public enum intakeWristState {IN, OUT};
    public intakeWristState currentState = intakeWristState.IN;
    public static final double IN = 0.00;
    public static final double OUT = 0.30;
    public void init(HardwareMap hm) {
        intakeWrist = hm.get(Servo.class, "intakeWrist");
    }
    public void Loop() {
        switch (currentState){
            case IN:
                setPosition(IN);
                break;
            case OUT:
                setPosition(OUT);
                break;
        }
    }
    public void setPosition(double pos) {
        intakeWrist.setPosition(pos);
    }
    public void setState(intakeWristState state) {
        currentState = state;
    }
    public String getState() {
        switch (currentState) {
            case IN:
                return "IN";
            case OUT:
                return "OUT";
            default:
                return "??????????";
        }
    }
}
