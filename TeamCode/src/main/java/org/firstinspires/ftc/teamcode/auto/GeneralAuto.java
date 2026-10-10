package org.firstinspires.ftc.teamcode.auto;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.follower.Follower;

import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.flyWheel.FlyWheel;
import org.firstinspires.ftc.teamcode.intake.Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;


@Autonomous
public class GeneralAuto extends OpMode {

    private FlyWheel flyWheel;
    private Intake intake;
    private String color;

    private Follower follower;
    private Circle circleClass;
    private ObjectSpotter objectSpotter;
    private Pose target;
    private PoseFactory poseFactory;
    private Pose startPose;
    private Path circle;

    @Override
    public void init() {
        Scheduler.reset();

        poseFactory = PoseFactory.degrees();
        startPose = poseFactory.of(24, 24, 0);

        circleClass = new Circle();
        objectSpotter = new ObjectSpotter();

        circle = circleClass.getCircle();

        follower = Constants.create(hardwareMap);
        if (follower == null) {
            telemetry.addLine("follower is null");
        }
        assert follower != null;
        follower.setPose(startPose);
    }

    @Override
    public void start() {
        schedule(follow(follower, circle));
    }

    @Override
    public void loop() {
        if (objectSpotter.getPose() != null) {
            target = objectSpotter.getPose();
        }
        follower.update();
        Scheduler.execute();
    }


}
