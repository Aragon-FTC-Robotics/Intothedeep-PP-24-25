package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.localization.*;

@Autonomous(name = "0+4", group = "Auto")
public class Auto_0_4 {
    private final Pose STARTPOSE = new Pose(7.065,83.368, Math.toRadians(-90)); //3 arguments: x, y, heading (in radians)
    private final Pose PRELOADPOSE = new Pose(12.845, 124.217, Math.toRadians(-55));
    private final Pose INTAKE1POSE = new Pose(45.73, 103.536, Math.toRadians(90));
    private final Pose INTAKE2POSE = new Pose(45.73, 111.757, Math.toRadians(90));
    private final Pose INTAKE3POSE = new Pose(45.73, 123.318, Math.toRadians(90));
    private final Pose BUCKETPOSE = new Pose(15.157, 126.786, Math.toRadians(-45));
    private final Pose ASCENTPOSE = new Pose(60.631, 94.801, Math.toRadians(-90));
    private final Pose ASCENTCONTROL1 = new Pose(63.072, 114.326);

    
}
