package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.teamcode.mechanisms.*;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.*;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.pedroPathing.util.Timer;

@Autonomous(name = "0+4", group = "Auto")
public class Auto_0_4 extends OpMode {
    private Bar bar;
    private Claw claw;
    private Extendo extendo;
    private Intake intake;
    private IntakeWrist intakeWrist;
    private Slides slides;
    private Wrist wrist;


    private Follower follower;
    private Timer pathTime, actionTime, totalTime;
    private int pathState = 0;

    private final Pose STARTPOSE = new Pose(); //3 arguments: x, y, heading (in radians)
    private final Pose PRELOADPOSE = new Pose();
    private final Pose PRELOADCONTROL = new Pose();
    private final Pose INTAKE1POSE = new Pose();
    private final Pose INTAKE2POSE = new Pose();
    private final Pose INTAKE3POSE = new Pose();
    private final Pose BUCKETPOSE = new Pose();
    private final Pose ASCENTPOSE = new Pose();
    private final Pose ASCENTCONTROL1 = new Pose();

    private Path scorePreload, park;
    private PathChain grab1, grab2, grab3, score1, score2, score3;

    private void buildPaths() {
        //Start -> Preload bucket pos
        scorePreload = new Path(new BezierLine(new Point(STARTPOSE), new Point(PRELOADPOSE)));
        scorePreload.setLinearHeadingInterpolation(STARTPOSE.getHeading(), PRELOADPOSE.getHeading());

        //Preload bucket pos -> Intake 1
        grab1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(new Point(PRELOADPOSE), new Point(INTAKE1POSE))
                )
                .setLinearHeadingInterpolation(PRELOADPOSE.getHeading(), INTAKE1POSE.getHeading())
                .build();

        //Intake 1 -> bucket
        score1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(new Point(INTAKE1POSE), new Point(BUCKETPOSE))
                )
                .setLinearHeadingInterpolation(INTAKE1POSE.getHeading(), BUCKETPOSE.getHeading())
                .build();

        //Bucket -> intake 2
        grab2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(new Point(BUCKETPOSE), new Point(INTAKE2POSE))
                )
                .setLinearHeadingInterpolation(BUCKETPOSE.getHeading(), INTAKE2POSE.getHeading())
                .build();

        //intake 2 -> bucket
        score2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(new Point(INTAKE2POSE), new Point(BUCKETPOSE))
                )
                .setLinearHeadingInterpolation(INTAKE2POSE.getHeading(), BUCKETPOSE.getHeading())
                .build();

        //bucket -> intake 3
        grab3 = follower.pathBuilder()
                .addPath(
                        new BezierLine(new Point(BUCKETPOSE), new Point(INTAKE3POSE))
                )
                .setLinearHeadingInterpolation(BUCKETPOSE.getHeading(), INTAKE3POSE.getHeading())
                .build();

        //intake 3 -> bucket
        score3 = follower.pathBuilder()
                .addPath(
                        new BezierLine(new Point(INTAKE3POSE), new Point(BUCKETPOSE))
                )
                .setLinearHeadingInterpolation(INTAKE3POSE.getHeading(), BUCKETPOSE.getHeading())
                .build();

        //Bucket -> ascent (bezier curve)
        park = new Path(new BezierCurve(
                new Point(BUCKETPOSE),
                new Point(ASCENTCONTROL1),
                new Point(ASCENTPOSE)
            )
        );
    }
    private void updatePaths() {
        switch (pathState) {
            case 0:
                slides.setTargetPos(slides.HIGH);
                bar.setState(Bar.BarState.BUCKET);
                wrist.setState(Wrist.wristState.BUCKET);
                claw.setState(Claw.ClawState.CLOSE);
                follower.followPath(scorePreload); //Start -> preload score
                setPathState(1);
                break;
            case 1:
                if ((Math.abs(PRELOADPOSE.getX() - follower.getPose().getX()) <= 1) && (Math.abs(PRELOADPOSE.getY() - follower.getPose().getY()) <= 1)) {
                    claw.setState(Claw.ClawState.OPEN);
                    follower.followPath(grab1, true); //preload score -> samp1
                    setPathState(2);
                }
                break;
            case 2:
                if ((Math.abs(BUCKETPOSE.getX() - follower.getPose().getX()) <= 1) && (Math.abs(BUCKETPOSE.getY() - follower.getPose().getY()) <= 1)) {
                    intake.setState(Intake.intakeState.IN);
                    follower.followPath(grab1, true);
                    setPathState(2);
                }
                break;
                //Todo later: figure out how to stop intaking after x seconds

        }
    }
    private void setPathState(int n) {
        pathState = n;
        pathTime.resetTimer();
    }
    @Override
    public void init() {
        pathTime = new Timer();
        totalTime = new Timer();
        totalTime.resetTimer();
        follower = new Follower(hardwareMap);
        follower.setStartingPose(STARTPOSE);
        buildPaths();
        bar = new Bar();
        claw = new Claw();
        extendo = new Extendo();
        intake = new Intake();
        intakeWrist = new IntakeWrist();
        slides = new Slides();
        //Todo later: declare every mechanism
    }
    @Override
    public void start() {
        totalTime.resetTimer();
        setPathState(0);
    }
    @Override
    public void loop() {
        follower.update();
        updatePaths();
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }
}
