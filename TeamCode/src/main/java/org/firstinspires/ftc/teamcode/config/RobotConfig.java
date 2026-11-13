package org.firstinspires.ftc.teamcode.config;


import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class RobotConfig {

    @Configurable
    public static class Drivetrain{
        public Drivetrain () {}
        public static double SLOW_MODE = 0.4;
        public static boolean FIELD_CENTRIC_START = true;
        public static double KP = 1.5;
    }

    @Configurable
    public static class Auto {
        public Auto () {}

        public static boolean isBlue;
    }

    @Configurable
    public static class FlyWheel {
        private FlyWheel () {}

        public static double KP;
        public static double KI;
        public static double KD;
        public static double KF;
    }
}
