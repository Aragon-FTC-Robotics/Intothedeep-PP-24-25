package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.*;

public class ActionHandler {
    private Slides slides;
    private Extendo extendo;
    private Bar bar;
    private Wrist wrist;
    private Intake intake;
    private Claw claw;
    private IntakeWrist intakeWrist;
    private Colorsensor colorSensor;
    private boolean intaking = false;
    private String alliance;
    private ElapsedTime timer = new ElapsedTime();
    private ActionState currentActionState = ActionState.IDLE;
    enum ActionState {
        IDLE,
        TRANSFER_STAGE_1, //intakewrist in BEFORE, stage1: claw close when wait is done
        TRANSFER_STAGE_2, //flywheel out
        TRANSFER_STAGE_3, //barwrist transfer, flywheel stop
        TRANSFER_STAGE_4, //clawopen
        TRANSFER_STAGE_5,
        CLIP2_STAGE_1, //delay to wrist move
        CLIP2_STAGE_2, //delay to claw open
        HIGHBUCKET_STAGE_1, //slides up BEFORE
        HIGHBUCKET_STAGE_2, //barwrist bucket
        //        HIGHBUCKET_STAGE_3 //claw open and extendo medium
        //slides up -> stage 1 -> barwrist -> stage2 ... etc
        //each case statement has a delay and then runs an action after....
        SLIDESDOWN_STAGE_1, //extendo in
        EJECT_STAGE_1,
        TUAH_STAGE_1, //flywehl out
        TUAH_STAGE_2, //wheel STOP
        RESETSTATE,
        NUDGE1, NUDGE2, NUDGE3
    }
    public void init(Slides s, Extendo e, Bar b, Wrist w, Intake f, Claw c, IntakeWrist iw, Colorsensor cs, String alliance) {
        slides = s;
        extendo = e;
        bar = b;
        wrist = w;
        intake = f;
        claw = c;
        intakeWrist = iw;
        colorSensor = cs;
        this.alliance = alliance;
    }

}
