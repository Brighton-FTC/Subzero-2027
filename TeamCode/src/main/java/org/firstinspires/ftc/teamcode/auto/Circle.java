package org.firstinspires.ftc.teamcode.auto;


import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Circle {

    public Circle() {
    }
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(71.1511, 23.6922, 90);
    private final Pose path1 = poseFactory.of(23.804, 69.2782, 180);
    private final Pose path1Control1 = poseFactory.of(23.6214, 23.6157, 0);
    private final Pose point2 = poseFactory.of(70.1855, 117.1138, 178.9053);
    private final Pose point2Control1 = poseFactory.of(24.0746, 118.2409, 0);
    private final Pose point3 = poseFactory.of(118.1587, 70.6816, 89.771);
    private final Pose point3Control1 = poseFactory.of(118.5908, 117.9388, 0);
    private final Pose point4 = poseFactory.of(71.3394, 23.3078, 0.3981);
    private final Pose point4Control1 = poseFactory.of(117.782, 23.3929, 0);

    private Path paths1 = Paths.curve(start, path1Control1, path1).reverseTangent();
    private Path paths2 = Paths.curve(path1, point2Control1, point2).reverseTangent();
    private Path paths3 = Paths.curve(point2, point3Control1, point3).reverseTangent();
    private Path paths4 = Paths.curve(point3, point4Control1, point4).reverseTangent();

    public Path getCircle() {
        return Paths.path(paths1, paths2, paths3, paths4);
    }
}
