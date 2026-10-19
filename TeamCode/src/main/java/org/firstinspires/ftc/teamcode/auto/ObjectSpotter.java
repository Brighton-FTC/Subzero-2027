package org.firstinspires.ftc.teamcode.auto;


import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class ObjectSpotter {
    protected PoseFactory poseFactory = PoseFactory.degrees();
    protected Pose pose = poseFactory.of(0, 0, 0);
    protected boolean spotted = false;


    public ObjectSpotter() {
    }


    public Pose getPose() {
        return null;
    }



    public static class BlueObjectSpotter extends ObjectSpotter{
        public BlueObjectSpotter () {}

        @Override
        public Pose getPose() {
            return null;
        }

    }

    public static class RedObjectSpotter extends ObjectSpotter{
        public RedObjectSpotter () {}

        @Override
        public Pose getPose(){
            return null;
        }

    }
}
