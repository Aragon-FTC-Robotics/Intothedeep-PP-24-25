package org.firstinspires.ftc.teamcode.mechanisms;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
/*
    In actionhandler, use setTargetPos
    ex. extendo.setTargetPos(Extendo.MAX)
    This allows the position to be changed to numbers outside of the three final ints for fine adjustment etc
 */

public class Extendo {
    private PIDController controller; //from arcrobotics
    private double pid, power;
    public static double p=0.010, i=0, d=0.0001, f=0;
    private DcMotorEx extendo;
//    public enum extendoState {IN, OUT, MED};
    private final int MAX = -200;
    private final int MIN = 600;
    private final int MED = 300;
    private int targetPos = 0;
    public void init(HardwareMap hm) {
        controller = new PIDController(p,i,d);
        extendo = hm.get(DcMotorEx.class, "extendo");
        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void Loop() {
        controller.setPID(p,i,d);
        pid = controller.calculate(extendo.getCurrentPosition(), targetPos);
        double ff = f;
        power = pid + ff;
        extendo.setPower(power);
    }
    public void setTargetPos(int targetPos) {
        this.targetPos = targetPos;
    }
    public double getPos() {
        return extendo.getCurrentPosition();
    }
    public void DANGEROUS_RESET_ENCODERS() {
        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        targetPos = MIN;
    }
}
