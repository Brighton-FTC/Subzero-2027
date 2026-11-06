package org.firstinspires.ftc.teamcode.flyWheel

import com.arcrobotics.ftclib.controller.PIDFController
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class FlyWheel(hw: HardwareMap, name: String?) {
    private val motor: DcMotorEx
    private var lastPower = 0.0
    private val kP = 0.0
    private val kI = 0.0
    private val kD = 0.0
    private val kF = 0.0
    private var targetVelocity = 0.0
    private val controller = PIDFController(kP, kI, kD, kF)

    init {
        motor = hw.get(DcMotorEx::class.java, name)
    }

    fun stopMotor() {
        lastPower = 0.0
        targetVelocity = 0.0
        controller.setSetPoint(0.0)
        motor.power = 0.0
    }

    var power: Double
        get() = lastPower
        set(velocityPerSec) {
            targetVelocity = velocityPerSec
            applyPID()
            controller.setSetPoint(targetVelocity)
            val power = controller.calculate(motor.velocity)
            lastPower = clamp(power, 1.0)
            motor.setPower(lastPower)
        }

    val isRunning: Boolean
        get() = lastPower != 0.0

    private fun applyPID() {
        controller.setPIDF(kP, kI, kD, kF)
    }

    companion object {
        private fun clamp(power: Double, limit: Double): Double {
            return max(-abs(limit), min(abs(limit), power))
        }
    }
}