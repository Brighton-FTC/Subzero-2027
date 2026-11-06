package org.firstinspires.ftc.teamcode.intake

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit

class Intake(hw: HardwareMap, name: String?) {
    val motor: DcMotorEx
    var power: Double = 0.0

    private val running_power = 0.85
    private val reverse_power = -0.85
    private val burnout_threshold = 7.5


    init {
        motor = hw.get<DcMotorEx>(DcMotorEx::class.java, name)
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

    }

    fun setMotor(power: Double) {
        this.power = power
        motor.power = power
    }

    fun startMotor() {
        motor.power = running_power
        this.power = running_power
    }

    fun stopMotor() {
        this.power = 0.0
        motor.power = 0.0
    }

    fun reversePower() {
        motor.power = reverse_power
        this.power = reverse_power
    }

    val isRunning: Boolean
        get() = Math.abs(this.power) > 0.05

    val isJamming: Boolean
        get() = motor.getCurrent(CurrentUnit.AMPS) > burnout_threshold

}
