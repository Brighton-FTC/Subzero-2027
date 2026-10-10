package org.firstinspires.ftc.teamcode.config;


import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class RobotConfig {

    @Configurable
    public class Drivetrain{
        private Drivetrain () {}
        public double SLOW_MODE = 0.4;
        private boolean FIELD_CENTRIC_START = true;
        private double KP = 1.5;
    }

    @Configurable
    public class Auto {
        private Auto () {}

        public boolean isBlue;
    }

    @Configurable
    public class FlyWheel {
        private FlyWheel () {}

        public double KP;
        public double KI;
        public double KD;
        public double KF;
    }
}
