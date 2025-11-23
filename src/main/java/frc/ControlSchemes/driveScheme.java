package frc.ControlSchemes;

import frc.robot.subsystems.drive.DriveTrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class driveScheme {
    public static double driveSpeed;

    private static boolean invertLeft = true;
    private static boolean invertRight = false;

    public static RunCommand createDriveCommand(DriveTrain driveTrain, double forward, double turn) {
        return new RunCommand(() -> {
            driveTrain.differentialDrive(
                    (invertLeft) ? -1 * forward : forward,
                    (invertRight) ? -1 * turn : turn);
            System.out.println("Driving!");
        }, driveTrain);
    }

}
