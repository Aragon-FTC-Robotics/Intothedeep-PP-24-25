package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    private Servo wrist;
    public enum wristState {TRANSFER, BUCKET, WALL, CLIP, NEUTRAL}
    public wristState currentState = wristState.NEUTRAL;

    public final double transfer = 0.078;
    public final double wall = 0.464;
    public final double bucket = 1.000;
    public final double clip = 0.332;
    public final double neutral = 0.070;

    public void init(HardwareMap hm) {
        wrist = hm.get(Servo.class, "wrist");
    }

    public void Loop() {
        switch(currentState) {
            case TRANSFER:
                setPos(transfer);
                break;
            case WALL:
                setPos(wall);
                break;
            case CLIP:
                setPos(clip);
                break;
            case BUCKET:
                setPos(bucket);
                break;
            case NEUTRAL:
                setPos(neutral);
                break;
            default:
                setPos(neutral);
                break;
        }
    }
    public void setPos(double pos) {
        wrist.setPosition(pos);
    }

    public void setState(wristState state) {
        currentState = state;
    }

    public String getState(){
        return currentState.name();
    }
}
