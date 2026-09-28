package org.firstinspires.ftc.teamcode.flyWheel;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlyWheel {

    final private DcMotorEx motor;
    private double lastPower = 0.0;

    public FlyWheel(HardwareMap hw) {
        motor = hw.get(DcMotorEx.class, "fW");
    }

    public void setPower (double power) {
        lastPower = clamp(power);
        motor.setPower(lastPower);
    }

    public void stopMotor(){
        lastPower = 0.0;
        motor.setPower(0.0);
    }

    private static double clamp(double power) {
        return Math.max(-1.0, Math.min(1.0, power));
    }
}