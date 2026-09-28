package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    final public DcMotorEx motor;
    public double lastPower;



    public Intake(HardwareMap hw) {
        motor = hw.get(DcMotorEx.class, "int");
    }

    public void setMotor(double power) {
        lastPower = power;
        motor.setPower(power);
    }

    public void startMotor() {
        motor.setPower(0.8);
        lastPower = 0.8;
    }

    public void stopMotor() {
        lastPower = 0.0;
        motor.setPower(0.0);
    }

    public void reversePower() {
        motor.setPower(-0.8);
        lastPower = -0.8;
    }

    public double getPower(){
        return lastPower;
    }

    public boolean isRunning() {
        return lastPower != 0.0;
    }
}
