package org.firstinspires.ftc.teamcode.auto;


import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class auto extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();


    @Override
    public void init() {
        Follower follower = Constants.create(hardwareMap);

    }
    @Override
    public void loop() {

    }

}
