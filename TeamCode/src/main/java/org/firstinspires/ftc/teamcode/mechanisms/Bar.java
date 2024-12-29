package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bar {
    private Servo barServoRight;
    public enum BarState {TRANSFER, WALL, BUCKET, CLIP, NEUTRAL}
    public BarState currentState = BarState.NEUTRAL;

    public final double transfer = 0.818;
    public final double wall = 0.864;
    public final double bucket = 0.262;
    public final double clip = 0.403;
    public final double neutral = 0.723;

    public void init(HardwareMap hm) {
        barServoRight = hm.get(Servo.class, "bar");
        barServoRight.setDirection(Servo.Direction.REVERSE);
    }

    public void Loop() {
        switch(currentState) {
            case TRANSFER:
                setPos(transfer);
                break;
            case BUCKET:
                setPos(bucket);
                break;
            case WALL:
                setPos(wall);
                break;
            case CLIP:
                setPos(clip);
                break;
            case NEUTRAL:
                setPos(neutral);
                break;
            default:
                setPos(neutral);
                break;
        }
    }

    private void setPos(double pos) {
        barServoRight.setPosition(pos);
    }

    public void setState(BarState state) {
        this.currentState = state;
    }

    public String getState(){
        return currentState.name();
    }
}
