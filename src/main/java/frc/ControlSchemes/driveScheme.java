package frc.ControlSchemes;

import frc.robot.subsystems.drive.DriveTrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class driveScheme {
    private static CommandXboxController controller;
    private static DoubleSupplier driveSpeed = () -> 1.0;

    public void configure(DriveTrain driveTrain, int port) {
        controller = new CommandXboxController(port);

        // why are these here
        SlewRateLimiter xLimit = new SlewRateLimiter(0.5);
        SlewRateLimiter yLimit = new SlewRateLimiter(0.5);

        // Set drivetrain command (default) - need to tell it what to do on controller
        // input basically
        configureButtons(driveTrain, port);
    }

    private static void configureButtons(DriveTrain drivetrain, int port) {
        RunCommand drive = new RunCommand(() -> {
            drivetrain.drive(controller.getLeftY(), controller.getRightX());
        }, drivetrain);

        drivetrain.setDefaultCommand(drive);
    }
}
