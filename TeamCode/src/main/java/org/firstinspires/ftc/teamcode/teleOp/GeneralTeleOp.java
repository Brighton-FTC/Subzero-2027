package org.firstinspires.ftc.teamcode.teleOp;


import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.TriggerReader;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.config.PSButtons;
import org.firstinspires.ftc.teamcode.flyWheel.FlyWheel;
import org.firstinspires.ftc.teamcode.intake.Intake;



/*
    General section for TeleOp.

    Controls:
        Sorted into 2 categories - driver (gamepad 1) and operator (gamepad 1)
        Driver:
            left stick - driving bot
            right stick X - rotation
            options - reset IMU
            cross - toggle slowmode
            circle - toggle driverCentric
        Operator:
            Left Trigger - Intake (held)
            Left Bumper - Reverse & Declog Intake (held)
            Right Trigger - Start FlyWheel (held)

 */

@TeleOp
public class GeneralTeleOp extends OpMode {
    private GamepadEx driver;
    private GamepadEx operator;

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    private boolean isFieldCentric;
    private boolean slowMode;
    double speedMultiplier;

    private GoBildaPinpointDriver pinpoint;

    private TriggerReader leftTrigger;
    private TriggerReader rightTrigger;

    private FlyWheel flyWheel;
    private Intake intake;

    private boolean intaking;
    private boolean shooting;


    @Override
    public void init() {
//        flyWheel = new FlyWheel(hardwareMap, "fW");
//        intake = new Intake(hardwareMap, "int");
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        pinpoint.setOffsets(0.0, 0.0, DistanceUnit.MM);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        leftTrigger = new TriggerReader(operator, PSButtons.LEFT_TRIGGER);
        rightTrigger = new TriggerReader(operator, PSButtons.RIGHT_TRIGGER);
    }

    @Override
    public void loop() {
        pinpoint.update();
        speedMultiplier = slowMode ? 0.4 : 1.0;

        driver.readButtons();
        operator.readButtons();
        leftTrigger.readValue();
        rightTrigger.readValue();

        handleDriveTrain();
        checkForInput();

        if (intake != null) {
            if (intaking) {
                intake.startMotor();
            } else {
                intake.stopMotor();
            }
        }

        if (flyWheel != null){
            if (shooting) {
                flyWheel.setPower(1.0);
            } else {
                flyWheel.stopMotor();
            }
        }

        updateTelemetry();
    }


    private double deadzone(double v) {
        return Math.abs(v) < 0.05 ? 0 : v;
    }


    private void checkForInput(){
        if (driver.wasJustPressed(PSButtons.OPTIONS)) {
            pinpoint.resetPosAndIMU();
        }
        if (driver.wasJustPressed(PSButtons.CROSS)) {
            isFieldCentric = !isFieldCentric;
        }
        if (driver.wasJustPressed(PSButtons.CIRCLE)) {
            slowMode = !slowMode;
        }
        shooting = rightTrigger.isDown();
        intaking = leftTrigger.isDown();
        if (operator.wasJustPressed(PSButtons.LEFT_BUMPER) && intake != null) {
            intake.reversePower();
        }
    }


    public void handleDriveTrain() {
        double ly = deadzone(driver.getLeftY()) * speedMultiplier;
        double lx = deadzone(driver.getLeftX()) * speedMultiplier;
        double rx = deadzone(driver.getRightX()) * speedMultiplier;

        double botHeading = pinpoint.getHeading(AngleUnit.RADIANS);

        double rotX = lx * Math.cos(-botHeading) - ly * Math.sin(-botHeading);
        double rotY = lx * Math.sin(-botHeading) + ly * Math.cos(-botHeading);
        rotX *= 1.1;
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double robotDenominator = Math.max(Math.abs(lx) + Math.abs(ly) + Math.abs(rx), 1);

        if (isFieldCentric) {
            fl.setPower((rotY + rotX + rx) / denominator);
            fr.setPower((rotY - rotX - rx) / denominator);
            bl.setPower((rotY - rotX + rx) / denominator);
            br.setPower((rotY + rotX - rx) / denominator);
        } else {
            fl.setPower((ly + lx + rx) / robotDenominator);
            fr.setPower((ly - lx - rx) / robotDenominator);
            bl.setPower((ly - lx + rx) / robotDenominator);
            br.setPower((ly + lx - rx) / robotDenominator);
        }
    }


    public void updateTelemetry() {
        telemetry.addData("teleOp type", isFieldCentric ? "fieldTeleOp" : "robotTeleOp");
        telemetry.addData("slowMode enabled", slowMode);
        telemetry.addData("fl power", fl.getPower());
        telemetry.addData("fr power", fr.getPower());
        telemetry.addData("bl power", bl.getPower());
        telemetry.addData("br power", br.getPower());
        telemetry.addData("intaking", intaking);
        telemetry.addData("outtaking", shooting);
//        telemetry.addData("flyWheel rpm", flyWheel.getPower());
        telemetry.update();
    }
}
