package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.*;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.pedroPathing.util.Timer;

import org.firstinspires.ftc.teamcode.pedroPathing.localization.*;

@Autonomous(name = "0+4", group = "Auto")
public class Auto_0_4 extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    private int pathState;

    private Claw claw;

    private final Pose STARTPOSE = new Pose(7.065,83.368, Math.toRadians(-90)); //3 arguments: x, y, heading (in radians)
    private final Pose PRELOADPOSE = new Pose(12.845, 124.217, Math.toRadians(-55));
    private final Pose INTAKE1POSE = new Pose(45.73, 103.536, Math.toRadians(90));
    private final Pose INTAKE2POSE = new Pose(45.73, 111.757, Math.toRadians(90));
    private final Pose INTAKE3POSE = new Pose(45.73, 123.318, Math.toRadians(90));
    private final Pose BUCKETPOSE = new Pose(15.157, 126.786, Math.toRadians(-45));
    private final Pose ASCENTPOSE = new Pose(60.631, 94.801, Math.toRadians(-90));
    private final Pose ASCENTCONTROL = new Pose(63.072, 114.326);

    private Path scorePreload, park;
    private PathChain grab1, grab2, grab3, score1, score2, score3;

    public void buildPaths(){
        scorePreload = new Path(new BezierLine(new Point(STARTPOSE), new Point(PRELOADPOSE)));
        scorePreload.setLinearHeadingInterpolation(STARTPOSE.getHeading(), PRELOADPOSE.getHeading());

        //get sample #1 from ground
        grab1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(PRELOADPOSE), new Point(INTAKE1POSE)))
                .setLinearHeadingInterpolation(PRELOADPOSE.getHeading(), INTAKE1POSE.getHeading())
                .build();

        //score sample #1
        score1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(INTAKE1POSE), new Point(BUCKETPOSE)))
                .setLinearHeadingInterpolation(INTAKE1POSE.getHeading(), BUCKETPOSE.getHeading())
                .build();

        //get sample #2
        grab2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(BUCKETPOSE), new Point(INTAKE2POSE)))
                .setLinearHeadingInterpolation(BUCKETPOSE.getHeading(), INTAKE2POSE.getHeading())
                .build();

        //score sample #2
        score2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(INTAKE2POSE), new Point(BUCKETPOSE)))
                .setLinearHeadingInterpolation(INTAKE2POSE.getHeading(), BUCKETPOSE.getHeading())
                .build();

        //get sample #3
        grab3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(BUCKETPOSE), new Point(INTAKE3POSE)))
                .setLinearHeadingInterpolation(BUCKETPOSE.getHeading(), INTAKE3POSE.getHeading())
                .build();

        //score sample #3
        score3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(INTAKE3POSE), new Point(BUCKETPOSE)))
                .setLinearHeadingInterpolation(INTAKE3POSE.getHeading(), BUCKETPOSE.getHeading())
                .build();

        //park in sub
        park = new Path(new BezierCurve(new Point(BUCKETPOSE), new Point(ASCENTCONTROL), new Point(ASCENTPOSE)));
        park.setLinearHeadingInterpolation(BUCKETPOSE.getHeading(), ASCENTPOSE.getHeading());
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
