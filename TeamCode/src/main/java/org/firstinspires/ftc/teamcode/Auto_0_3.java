package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.localization.*;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.*;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.*;
import org.firstinspires.ftc.teamcode.pedroPathing.util.*;
import org.firstinspires.ftc.teamcode.mechanisms.*;

@Autonomous(name = "0+3", group = "Auto")
public class Auto_0_3 {
    private final Pose STARTPOSE = new Pose(); //3 arguments: x, y, heading (in radians)
    private final Pose PRELOADPOSE = new Pose();
    private final Pose PRELOADCONTROL = new Pose();
    private final Pose INTAKE1POSE = new Pose();
    private final Pose INTAKE2POSE = new Pose();
    private final Pose INTAKE3POSE = new Pose();
    private final Pose BUCKETPOSE = new Pose();
    private final Pose ASCENTPOSE = new Pose();
    private final Pose ASCENTCONTROL1 = new Pose();
}
