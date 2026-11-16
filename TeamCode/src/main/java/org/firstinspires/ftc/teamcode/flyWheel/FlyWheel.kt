package org.firstinspires.ftc.teamcode.flyWheel;


import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlyWheel {

    final private DcMotorEx motor;
    private double lastPower = 0.0;
    private double kP = 0.0;
    private double kI = 0.0;
    private double kD = 0.0;
    private double kF = 0.0;
    private double targetVelocity = 0.0;
    private PIDFController controller = new PIDFController(kP, kI, kD, kF);

    public FlyWheel(HardwareMap hw, String name) {
        motor = hw.get(DcMotorEx.class, name);
    }

    public void setPower (double velocityPerSec) {
        targetVelocity = velocityPerSec;
        applyPID();
        controller.setSetPoint(targetVelocity);
        double power = controller.calculate(motor.getVelocity());
        lastPower = clamp(power, 1.0);
        motor.setPower(lastPower);
    }

    public void stopMotor(){
        lastPower = 0.0;
        targetVelocity = 0.0;
        controller.setSetPoint(0.0);
        motor.setPower(0.0);
    }

    public double getPower() {
        return lastPower;
    }

    public boolean isRunning() {
        return lastPower != 0.0;
    }

    private static double clamp(double power, double limit) {
        return Math.max(-Math.abs(limit), Math.min(Math.abs(limit), power));
    }

    private void applyPID(){
        controller.setPIDF(kP, kI, kD, kF);
    }
}