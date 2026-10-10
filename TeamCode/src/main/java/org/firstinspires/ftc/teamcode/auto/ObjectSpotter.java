package org.firstinspires.ftc.teamcode.auto;


import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class ObjectSpotter {
    private PoseFactory poseFactory = PoseFactory.degrees();
    private Pose pose = poseFactory.of(0, 0, 0);
    private boolean spotted = false;
    private Boolean isBlue;

    public ObjectSpotter(String color) {
        if (color == "Blue") {
            isBlue = true;
        } else if (color == "Red") {
            isBlue = false;
        }
    }


    public Pose getPose(){
        if (spotted) {
            return pose;
        } else {
            return null;
        }
    }
}
