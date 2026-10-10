package org.firstinspires.ftc.teamcode.config;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.configurables.annotations.Configurable;


@Configurable
public class PSButtons {
    public static GamepadKeys.Button SQUARE = GamepadKeys.Button.X;
    public static GamepadKeys.Button TRIANGLE = GamepadKeys.Button.Y;
    public static GamepadKeys.Button CIRCLE = GamepadKeys.Button.B;
    public static GamepadKeys.Button CROSS = GamepadKeys.Button.A;
    public static GamepadKeys.Button OPTIONS = GamepadKeys.Button.START;
    public static GamepadKeys.Button LEFT_BUMPER = GamepadKeys.Button.LEFT_BUMPER;
    public static GamepadKeys.Button RIGHT_BUMPER = GamepadKeys.Button.RIGHT_BUMPER;
    public static GamepadKeys.Trigger LEFT_TRIGGER = GamepadKeys.Trigger.LEFT_TRIGGER;
    public static GamepadKeys.Trigger RIGHT_TRIGGER = GamepadKeys.Trigger.RIGHT_TRIGGER;

}
